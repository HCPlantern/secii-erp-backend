package com.nju.edu.erp.salaryStrategy;

import com.nju.edu.erp.model.po.EmployeePO;
import com.nju.edu.erp.model.po.JobPO;
import com.nju.edu.erp.model.po.SalarySheetPO;

import java.math.BigDecimal;
import java.util.Date;

public interface SalaryCalculationStrategy {
    public BigDecimal calculate(SalarySheetPO salarySheetPO, JobPO jobPO,
                                Date beginDate, Date endDate);
}
