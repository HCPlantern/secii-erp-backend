package com.nju.edu.erp.salaryStrategy.Impl;

import com.nju.edu.erp.dao.EmployeeDao;
import com.nju.edu.erp.dao.JobDao;
import com.nju.edu.erp.model.po.JobPO;
import com.nju.edu.erp.model.po.SalarySheetPO;
import com.nju.edu.erp.salaryStrategy.SalaryCalculationStrategy;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.Date;

public class PostStrategy implements SalaryCalculationStrategy {

    private JobDao jobDao;

    private EmployeeDao employeeDao;

    @Autowired
    public void setDao(JobDao jobDao, EmployeeDao employeeDao) {
        this.jobDao = jobDao;
        this.employeeDao = employeeDao;
    }

    @Override
    public void calculate(SalarySheetPO salarySheetPO, Date beginDate, Date endDate) {
        JobPO jobPO = jobDao.findJobByName(salarySheetPO.getJob());
        int grade = employeeDao.getGradeById(salarySheetPO.getEmployeeId());
        salarySheetPO.setBaseWage(jobPO.getBaseWage());
        //实际岗位工资设计为岗位工资*岗位级别*岗位级别加薪率
        salarySheetPO.setPostWage(jobPO.getPostWage().multiply(BigDecimal.valueOf(grade))
                .multiply(jobPO.getGradeRate()));
    }
}
