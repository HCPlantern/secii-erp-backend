package com.nju.edu.erp.service.Impl;

import com.nju.edu.erp.dao.*;
import com.nju.edu.erp.enums.Role;
import com.nju.edu.erp.enums.sheetState.SalarySheetState;
import com.nju.edu.erp.model.po.EmployeePO;
import com.nju.edu.erp.model.po.JobPO;
import com.nju.edu.erp.model.po.SalarySheetPO;
import com.nju.edu.erp.model.po.TaxPO;
import com.nju.edu.erp.model.vo.humanResource.EmployeeVO;
import com.nju.edu.erp.model.vo.humanResource.JobVO;
import com.nju.edu.erp.salaryStrategy.SalaryContext;
import com.nju.edu.erp.service.SalaryService;
import com.nju.edu.erp.utils.IdGenerator;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class SalaryServiceImpl implements SalaryService {

    private final JobDao jobDao;
    private final SalarySheetDao salarySheetDao;
    private final EmployeeDao employeeDao;
    private final UserDao userDao;
    private final TaxDao taxDao;


    @Autowired
    public SalaryServiceImpl(JobDao jobDao, SalarySheetDao salarySheetDao
            , EmployeeDao employeeDao, UserDao userDao, TaxDao taxDao) {
        this.jobDao = jobDao;
        this.salarySheetDao = salarySheetDao;
        this.employeeDao = employeeDao;
        this.userDao = userDao;
        this.taxDao = taxDao;
    }

    /**
     * 查询所有岗位薪资信息
     *
     * @return 岗位信息列表
     */
    public List<JobPO> queryAllJobs() {
        return jobDao.queryAllJobs();
    }

    /**
     * 根据岗位id更新岗位薪资信息
     *
     * @param jobVO 岗位信息
     */
    public void updateJobById(JobVO jobVO) {
        JobPO jobPO = new JobPO();
        BeanUtils.copyProperties(jobVO, jobPO);
        jobDao.updateJobById(jobPO);
    }

    /**
     * 生成工资单
     */
    public void generateSalarySheet(String beginDateStr, String endDateStr) {
        List<EmployeePO> allEmployees = employeeDao.findAllEmployees();
        EmployeeVO employeeVO = new EmployeeVO();
        SalaryContext salaryContext = new SalaryContext();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date beginDate, endDate;
        try {
            beginDate = sdf.parse(beginDateStr);
            endDate = sdf.parse(endDateStr);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        //对每个员工计算薪资
        for (EmployeePO employeePO : allEmployees) {
            BeanUtils.copyProperties(employeePO, employeeVO);
            JobPO jobPO = jobDao.findJobByName(employeePO.getJob());
            SalarySheetPO salarySheetPO = new SalarySheetPO(); //对应该员工待生成的一条工资单
            //设置id,其中日期字段为endDate
            SalarySheetPO latest = salarySheetDao.getLatestSheet();
            salarySheetPO.setId(IdGenerator.generateSheetIdWithTime(latest == null ? null : latest.getId(), "GZD", endDate));
            //设置员工、工作相关信息
            salarySheetPO.setEmployeeId(employeePO.getId());
            salarySheetPO.setEmployeeName(employeePO.getName());
            salarySheetPO.setJob(employeePO.getJob());
            salarySheetPO.setState(SalarySheetState.PENDING_LEVEL_1);
            salarySheetPO.setCreateTime(new Date());
            salarySheetPO.setSalaryAccount(employeePO.getSalaryAccount());
            //策略模式计算基本工资和岗位工资（或提成）
            salaryContext.setSalaryStrategy(jobPO.getSalaryCalculationMethod(), jobPO.getSalaryPaymentMethod());
            salaryContext.getSalary(salarySheetPO, beginDate, endDate);
            //缺勤扣除基本工资
            if (employeePO.getJob() != Role.GM) {
                int attendance = userDao.getAttendanceByEmployeeId(employeePO.getId());
                salarySheetPO.setBaseWage(salarySheetPO.getBaseWage().multiply(
                        BigDecimal.valueOf(attendance)).divide(BigDecimal.valueOf(30), RoundingMode.HALF_UP));
            }
            //失业保险，住房公积金
            salarySheetPO.setInsurance(jobPO.getInsurance());
            salarySheetPO.setHousingFund(jobPO.getHousingFund());
            //计算税前总工资=基本工资+岗位工资（或提成）-失业保险-住房公积金
            BigDecimal unTaxedSalary = salarySheetPO.getBaseWage().add(salarySheetPO.getPostWage()
                    .subtract(salarySheetPO.getInsurance()).subtract(salarySheetPO.getHousingFund()));
            salarySheetPO.setTotalSalary(unTaxedSalary);
            //表驱动计算税款 = 减去起征点的未税工资*税率-速算扣除数
            List<TaxPO> taxTable = taxDao.findTax(); //已升序过,第0个的base是起征点（负数）
            BigDecimal netUnTaxedSalary = unTaxedSalary.add(taxTable.get(0).getBase()); //减去起征点的未税工资
            int level = -1; //在for循环中,由于工资>=0, level一定会>=0
            for (TaxPO taxPO : taxTable) {
                if (netUnTaxedSalary.compareTo(taxPO.getBase()) >= 0)
                    level++; //达该级税前所得基线
                else break;
            }
            BigDecimal tax = netUnTaxedSalary.multiply(taxTable.get(level).getRate())
                    .subtract(taxTable.get(level).getQuickDeduction());
            salarySheetPO.setTax(tax);
            salarySheetPO.setTaxedSalary(unTaxedSalary.subtract(tax));
            salarySheetDao.createSalarySheet(salarySheetPO);
        }
    }

    /**
     * 根据时间查询工资单
     *
     * @param beginTime 开始时间
     * @param endTime   结束时间
     * @return 工资单
     */
    @Override
    public List<SalarySheetPO> getSalarySheetByTime(String beginTime, String endTime) {
        return salarySheetDao.getSalarySheetByTime(beginTime, endTime);
    }

    /**
     * 根据id查询工资单
     *
     * @param id 工资单id
     * @return 工资单
     */
    @Override
    public SalarySheetPO getSalarySheetById(String id) {
        return salarySheetDao.getSalarySheetById(id);
    }

    @Override
    public List<SalarySheetPO> getSalarySheetByState(SalarySheetState state) {
        if (state == null) {
            return salarySheetDao.getAllSalarySheet();
        } else {
            return salarySheetDao.getSalarySheetByState(state);
        }
    }
}
