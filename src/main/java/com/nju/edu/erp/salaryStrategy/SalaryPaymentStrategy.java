package com.nju.edu.erp.salaryStrategy;

import com.nju.edu.erp.model.po.SalarySheetPO;

import java.util.Date;

/**
 * 涉及年终奖的发放
 */
public interface SalaryPaymentStrategy {
    public void calculate(SalarySheetPO salarySheetPO, Date beginDate, Date endDate);
}
