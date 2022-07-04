package com.nju.edu.erp.service.Impl;

import com.nju.edu.erp.dao.EmployeeDao;
import com.nju.edu.erp.dao.JobDao;
import com.nju.edu.erp.dao.SalarySheetDao;
import com.nju.edu.erp.dao.UserDao;
import com.nju.edu.erp.enums.sheetState.SalarySheetState;
import com.nju.edu.erp.model.po.EmployeePO;
import com.nju.edu.erp.model.po.JobPO;
import com.nju.edu.erp.model.po.SalarySheetPO;
import com.nju.edu.erp.model.vo.humanResource.EmployeeVO;
import com.nju.edu.erp.model.vo.humanResource.JobVO;
import com.nju.edu.erp.service.SalaryService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SalaryServiceImpl implements SalaryService {

    private final JobDao jobDao;
    private final SalarySheetDao salarySheetDao;
    private final EmployeeDao employeeDao;
    private final UserDao userDao;


    @Autowired
    public SalaryServiceImpl(JobDao jobDao, SalarySheetDao salarySheetDao
            , EmployeeDao employeeDao, UserDao userDao) {
        this.jobDao = jobDao;
        this.salarySheetDao = salarySheetDao;
        this.employeeDao = employeeDao;
        this.userDao = userDao;
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
    public void generateSalarySheet() {
        List<EmployeePO> allEmployees = employeeDao.findAllEmployees();
        EmployeeVO employeeVO = new EmployeeVO();
        for (EmployeePO employeePO : allEmployees) {
            BeanUtils.copyProperties(employeePO, employeeVO);
            //策略模式

        }

        //最后扣税，扣除缺勤基本工资

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
    public SalarySheetPO getSalarySheetById(Integer id) {
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
