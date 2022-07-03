package com.nju.edu.erp.salaryStrategy;

import com.nju.edu.erp.dao.SaleSheetDao;
import com.nju.edu.erp.model.po.JobPO;
import com.nju.edu.erp.model.po.SalarySheetPO;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;

public class DeductStrategy implements SalaryCalculationStrategy {

    private final SaleSheetDao saleSheetDao;

    @Autowired
    public DeductStrategy(SaleSheetDao saleSheetDao) {
        this.saleSheetDao = saleSheetDao;
    }

    @Override
    public BigDecimal calculate(SalarySheetPO salarySheetPO, JobPO jobPO) {
        return null;
    }
}
