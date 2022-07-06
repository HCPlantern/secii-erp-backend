package com.nju.edu.erp.salaryStrategy.Impl;

import com.nju.edu.erp.dao.SaleSheetDao;
import com.nju.edu.erp.model.po.JobPO;
import com.nju.edu.erp.model.po.SalarySheetPO;
import com.nju.edu.erp.salaryStrategy.SalaryCalculationStrategy;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.Date;

public class DeductStrategy implements SalaryCalculationStrategy {

    private SaleSheetDao saleSheetDao;

    @Autowired
    public void setSaleSheetDao(SaleSheetDao saleSheetDao) {
        this.saleSheetDao = saleSheetDao;
    }

    @Override
    public BigDecimal calculate(SalarySheetPO salarySheetPO, JobPO jobPO
            , Date beginDate, Date endDate) {
        return null;
    }
}
