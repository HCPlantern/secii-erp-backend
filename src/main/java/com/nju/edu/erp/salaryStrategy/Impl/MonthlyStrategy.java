package com.nju.edu.erp.salaryStrategy.Impl;

import com.nju.edu.erp.model.po.SalarySheetPO;
import com.nju.edu.erp.salaryStrategy.SalaryPaymentStrategy;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class MonthlyStrategy implements SalaryPaymentStrategy {
    @Override
    public void calculate(SalarySheetPO salarySheetPO, Date beginDate, Date endDate) {
        //月薪制，没有特别的。
    }
}
