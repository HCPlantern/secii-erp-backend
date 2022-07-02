package com.nju.edu.erp.service;

import com.nju.edu.erp.model.vo.finance.FinanceVO;

public interface FinanceService {
    FinanceVO getFinancialReport(String beginDateStr, String endDateStr);
}
