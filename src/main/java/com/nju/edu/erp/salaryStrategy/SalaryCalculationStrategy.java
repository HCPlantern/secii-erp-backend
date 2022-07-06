package com.nju.edu.erp.salaryStrategy;

import com.nju.edu.erp.model.po.SalarySheetPO;

import java.util.Date;

public interface SalaryCalculationStrategy {
    public void calculate(SalarySheetPO salarySheetPO, Date beginDate, Date endDate);
}
