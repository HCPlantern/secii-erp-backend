package com.nju.edu.erp.salaryStrategy.Impl;

import com.nju.edu.erp.dao.JobDao;
import com.nju.edu.erp.dao.SaleSheetDao;
import com.nju.edu.erp.model.po.JobPO;
import com.nju.edu.erp.model.po.SalarySheetPO;
import com.nju.edu.erp.salaryStrategy.SalaryCalculationStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Date;

@Component
public class DeductStrategy implements SalaryCalculationStrategy {
    private static SaleSheetDao saleSheetDao;
    private static JobDao jobDao;

    @Autowired
    public void setDao(SaleSheetDao saleSheetDao, JobDao jobDao) {
        this.saleSheetDao = saleSheetDao;
        this.jobDao = jobDao;
    }

    @Override
    public void calculate(SalarySheetPO salarySheetPO, Date beginDate, Date endDate) {
        JobPO jobPO = jobDao.findJobByName(salarySheetPO.getJob());
        salarySheetPO.setBaseWage(jobPO.getBaseWage());
        //计算提成=总销售业绩*提成率,岗位薪资=提成*(1+岗位级别加薪率)
        BigDecimal totalAmount = saleSheetDao.calTotalAmountOfSalesman(salarySheetPO.getEmployeeName(), beginDate, endDate);
        totalAmount = totalAmount == null ? BigDecimal.ZERO : totalAmount; //避免没有业绩，改null为0
        BigDecimal deduct = totalAmount.multiply(jobPO.getDeductRate());
        salarySheetPO.setPostWage(deduct.multiply(jobPO.getGradeRate().add(BigDecimal.ONE)));
    }
}
