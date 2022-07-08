package com.nju.edu.erp.service.Impl;

import com.nju.edu.erp.dao.*;
import com.nju.edu.erp.enums.sheetState.*;
import com.nju.edu.erp.exception.MyServiceException;
import com.nju.edu.erp.model.po.*;
import com.nju.edu.erp.model.queryObject.*;
import com.nju.edu.erp.model.vo.finance.FinanceVO;
import com.nju.edu.erp.service.FinanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class FinanceServiceImpl implements FinanceService {

    private final SaleSheetDao saleSheetDao;

    private final SaleReturnsSheetDao saleReturnsSheetDao;

    private final PurchaseSheetDao purchaseSheetDao;

    private final PurchaseReturnsSheetDao purchaseReturnsSheetDao;

    private final SalarySheetDao salarySheetDao;

    @Autowired
    public FinanceServiceImpl(SaleSheetDao saleSheetDao, SaleReturnsSheetDao saleReturnsSheetDao, PurchaseSheetDao purchaseSheetDao, PurchaseReturnsSheetDao purchaseReturnsSheetDao, SalarySheetDao salarySheetDao) {
        this.saleSheetDao = saleSheetDao;
        this.saleReturnsSheetDao = saleReturnsSheetDao;
        this.purchaseSheetDao = purchaseSheetDao;
        this.purchaseReturnsSheetDao = purchaseReturnsSheetDao;
        this.salarySheetDao = salarySheetDao;
    }

    @Override
    public FinanceVO getFinancialReport(String beginDateStr, String endDateStr) {
        BigDecimal saleIncome = BigDecimal.ZERO;
        BigDecimal discount = BigDecimal.ZERO;
        List<SaleSheetPO> saleSheetPOList = saleSheetDao.findAllSheet(SaleSheetQuery.builder().beginTime(beginDateStr).endTime(endDateStr).state(SaleSheetState.SUCCESS).build());
        for (SaleSheetPO saleSheetPO : saleSheetPOList) {
            saleIncome = saleIncome.add(saleSheetPO.getFinalAmount());
            discount = discount.add(saleSheetPO.getRawTotalAmount().multiply(BigDecimal.ONE.subtract(saleSheetPO.getDiscount())));
            List<SaleReturnsSheetPO> saleReturnsSheetPOList = saleReturnsSheetDao.findAll(SaleReturnSheetQuery.builder().saleSheetId(saleSheetPO.getId()).state(SaleReturnsSheetState.SUCCESS).build());
            for (SaleReturnsSheetPO saleReturnsSheetPO : saleReturnsSheetPOList) {
                saleIncome = saleIncome.subtract(saleReturnsSheetPO.getFinalAmount());
                discount = discount.subtract(saleReturnsSheetPO.getRawTotalAmount().multiply(BigDecimal.ONE.subtract(saleReturnsSheetPO.getDiscount())));
            }
        }
        BigDecimal commodityIncome = BigDecimal.ZERO;
        BigDecimal finalIncome = saleIncome.add(commodityIncome);

        BigDecimal saleOutcome = BigDecimal.ZERO;
        BigDecimal commodityOutcome = BigDecimal.ZERO;

        // 工资的成本
        BigDecimal humanResourceOutcome = BigDecimal.ZERO;
        List<SalarySheetPO> salarySheetPOList = salarySheetDao.find(SalarySheetQuery.builder().beginTime(beginDateStr).endTime(endDateStr).state(SalarySheetState.SUCCESS).build());
        if (salarySheetPOList == null) {
            throw new MyServiceException("B0001", "没有工资单");
        }
        // 人力成本为：工资单中的基本工资+岗位工资
        for (SalarySheetPO po : salarySheetPOList) {
            humanResourceOutcome = humanResourceOutcome.add(po.getBaseWage().add(po.getPostWage()));
        }

        List<PurchaseSheetPO> purchaseSheetPOList = purchaseSheetDao.findAll(PurchaseSheetQuery.builder().beginTime(beginDateStr).endTime(endDateStr).state(PurchaseSheetState.SUCCESS).build());
        for (PurchaseSheetPO purchaseSheetPO : purchaseSheetPOList) {
            saleOutcome = saleOutcome.add(purchaseSheetPO.getTotalAmount());
            List<PurchaseReturnsSheetPO> purchaseReturnsSheetPOList = purchaseReturnsSheetDao.findAll(PurchaseReturnSheetQuery.builder().purchaseSheetId(purchaseSheetPO.getId()).state(PurchaseReturnsSheetState.SUCCESS).build());
            for (PurchaseReturnsSheetPO purchaseReturnsSheetPO : purchaseReturnsSheetPOList) {
                saleOutcome = saleOutcome.subtract(purchaseReturnsSheetPO.getTotalAmount());
            }
        }
        BigDecimal finalOutcome = saleOutcome.add(commodityOutcome).add(humanResourceOutcome);
        return FinanceVO.builder()
                .saleIncome(saleIncome)
                .finalIncome(finalIncome)
                .discount(discount)
                .commodityIncome(commodityIncome)
                .saleOutcome(saleOutcome)
                .commodityOutcome(commodityOutcome)
                .humanResourceOutcome(humanResourceOutcome)
                .finalOutcome(finalOutcome)
                .profit(finalIncome.subtract(finalOutcome))
                .build();

    }
}
