package com.nju.edu.erp.salaryStrategy.Impl;

import com.nju.edu.erp.dao.JobDao;
import com.nju.edu.erp.dao.SaleSheetDao;
import com.nju.edu.erp.model.po.JobPO;
import com.nju.edu.erp.model.po.SalarySheetPO;
import com.nju.edu.erp.salaryStrategy.SalaryCalculationStrategy;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.Date;

public class DeductStrategy implements SalaryCalculationStrategy {

    private SaleSheetDao saleSheetDao;
    private JobDao jobDao;

    @Autowired
    public void setDao(SaleSheetDao saleSheetDao, JobDao jobDao) {
        this.saleSheetDao = saleSheetDao;
        this.jobDao = jobDao;
    }

    @Override
    public void calculate(SalarySheetPO salarySheetPO, Date beginDate, Date endDate) {
        JobPO jobPO = jobDao.findJobByName(salarySheetPO.getJob());
        salarySheetPO.setBaseWage(jobPO.getBaseWage());
        //计算提成=总销售业绩*提成率
        BigDecimal totalAmount = saleSheetDao.calTotalAmountOfSalesman(salarySheetPO.getEmployeeId(), beginDate, endDate);
        BigDecimal deduct = totalAmount.multiply(jobPO.getDeductRate());
        salarySheetPO.setPostWage(deduct);
    }
}
