package com.nju.edu.erp.salaryStrategy;

import com.nju.edu.erp.model.po.SalarySheetPO;

import java.util.Date;

/**
 * 涉及岗位工资和提成的计算
 */
public interface SalaryCalculationStrategy {
    public void calculate(SalarySheetPO salarySheetPO, Date beginDate, Date endDate);
}
