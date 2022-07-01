package com.nju.edu.erp.service.Impl;

import com.nju.edu.erp.dao.PurchaseReturnsSheetDao;
import com.nju.edu.erp.dao.PurchaseSheetDao;
import com.nju.edu.erp.dao.SaleReturnsSheetDao;
import com.nju.edu.erp.dao.SaleSheetDao;
import com.nju.edu.erp.model.po.PurchaseReturnsSheetPO;
import com.nju.edu.erp.model.po.PurchaseSheetPO;
import com.nju.edu.erp.model.po.SaleReturnsSheetPO;
import com.nju.edu.erp.model.po.SaleSheetPO;
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

    @Autowired
    public FinanceServiceImpl(SaleSheetDao saleSheetDao, SaleReturnsSheetDao saleReturnsSheetDao, PurchaseSheetDao purchaseSheetDao, PurchaseReturnsSheetDao purchaseReturnsSheetDao) {
        this.saleSheetDao = saleSheetDao;
        this.saleReturnsSheetDao = saleReturnsSheetDao;
        this.purchaseSheetDao = purchaseSheetDao;
        this.purchaseReturnsSheetDao = purchaseReturnsSheetDao;
    }

    @Override
    public FinanceVO getFinancialReport(String beginDateStr, String endDateStr) {
        BigDecimal saleIncome = BigDecimal.ZERO;
        BigDecimal discount = BigDecimal.ZERO;
        List<SaleSheetPO> saleSheetPOList = saleSheetDao.findAllSheetByTime(beginDateStr, endDateStr);
        for (SaleSheetPO saleSheetPO : saleSheetPOList) {
            saleIncome = saleIncome.add(saleSheetPO.getFinalAmount());
            discount = discount.add(saleSheetPO.getRawTotalAmount().multiply(saleSheetPO.getDiscount()));
            List<SaleReturnsSheetPO> saleReturnsSheetPOList = saleReturnsSheetDao.findBySaleSheetId(saleSheetPO.getId());
            for (SaleReturnsSheetPO saleReturnsSheetPO : saleReturnsSheetPOList) {
                saleIncome = saleIncome.subtract(saleReturnsSheetPO.getFinalAmount());
                discount = discount.subtract(saleReturnsSheetPO.getRawTotalAmount().multiply(saleReturnsSheetPO.getDiscount()));
            }
        }
        BigDecimal commodityIncome = BigDecimal.ZERO;
        BigDecimal finalIncome = saleIncome.add(commodityIncome);

        BigDecimal saleOutcome = BigDecimal.ZERO;
        BigDecimal commodityOutcome = BigDecimal.ZERO;
        BigDecimal humanResourceOutcome = BigDecimal.ZERO;
        List<PurchaseSheetPO> purchaseSheetPOList = purchaseSheetDao.findByCreateTime(beginDateStr, endDateStr);
        for (PurchaseSheetPO purchaseSheetPO : purchaseSheetPOList) {
            saleOutcome = saleOutcome.add(purchaseSheetPO.getTotalAmount());
            List<PurchaseReturnsSheetPO> purchaseReturnsSheetPOList = purchaseReturnsSheetDao.findByPurchaseSheetId(purchaseSheetPO.getId());
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
