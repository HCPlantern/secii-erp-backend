package com.nju.edu.erp.salaryStrategy.Impl;

import com.nju.edu.erp.dao.JobDao;
import com.nju.edu.erp.model.po.JobPO;
import com.nju.edu.erp.model.po.SalarySheetPO;
import com.nju.edu.erp.salaryStrategy.SalaryPaymentStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Date;

@Component
public class AnnuallyStrategy implements SalaryPaymentStrategy {

    private static JobDao jobDao;

    @Autowired
    public void setDao(JobDao jobDao) {
        this.jobDao = jobDao;
    }

    @Override
    public void calculate(SalarySheetPO salarySheetPO, Date beginDate, Date endDate) {
        //12月份发放的工资，要加年终奖，算在岗位工资里
        if (beginDate.getMonth() == Calendar.DECEMBER) {
            JobPO jobPO = jobDao.findJobByName(salarySheetPO.getJob());
            salarySheetPO.setPostWage(salarySheetPO.getPostWage().add(jobPO.getAnnualBonus()));
        }
    }
}
