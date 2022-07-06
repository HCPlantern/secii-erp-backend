package com.nju.edu.erp.salaryStrategy.Impl;

import com.nju.edu.erp.model.po.JobPO;
import com.nju.edu.erp.model.po.SalarySheetPO;
import com.nju.edu.erp.salaryStrategy.SalaryCalculationStrategy;

import java.math.BigDecimal;
import java.util.Date;

public class PostStrategy implements SalaryCalculationStrategy {
    @Override
    public BigDecimal calculate(SalarySheetPO salarySheetPO, JobPO jobPO, Date beginDate, Date endDate) {
        return null;
    }
}
