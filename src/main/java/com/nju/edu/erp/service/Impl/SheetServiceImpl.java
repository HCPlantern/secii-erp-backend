package com.nju.edu.erp.service.Impl;

import com.nju.edu.erp.dao.*;
import com.nju.edu.erp.enums.SheetType;
import com.nju.edu.erp.model.po.SaleSheetPO;
import com.nju.edu.erp.model.po.SheetPO;
import com.nju.edu.erp.model.vo.sheet.SheetVO;
import com.nju.edu.erp.service.SheetService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SheetServiceImpl implements SheetService {
    private final SaleSheetDao saleSheetDao;

    private final SaleReturnsSheetDao saleReturnsSheetDao;

    private final PurchaseSheetDao purchaseSheetDao;

    private final PurchaseReturnsSheetDao purchaseReturnsSheetDao;

    private final PaymentSheetDao paymentSheetDao;

    // TODO: CollectionSheetDao and SalarySheetDao

    private final WarehouseInputSheetDao warehouseInputSheetDao;

    private final WarehouseOutputSheetDao warehouseOutputSheetDao;

    @Autowired
    public SheetServiceImpl(SaleSheetDao saleSheetDao, SaleReturnsSheetDao saleReturnsSheetDao, PurchaseSheetDao purchaseSheetDao, PurchaseReturnsSheetDao purchaseReturnsSheetDao, PaymentSheetDao paymentSheetDao, WarehouseInputSheetDao warehouseInputSheetDao, WarehouseOutputSheetDao warehouseOutputSheetDao) {
        this.saleSheetDao = saleSheetDao;
        this.saleReturnsSheetDao = saleReturnsSheetDao;
        this.purchaseSheetDao = purchaseSheetDao;
        this.purchaseReturnsSheetDao = purchaseReturnsSheetDao;
        this.paymentSheetDao = paymentSheetDao;
        this.warehouseInputSheetDao = warehouseInputSheetDao;
        this.warehouseOutputSheetDao = warehouseOutputSheetDao;
    }


    @Override
    public List<SheetVO> findAllSheet(String beginDateStr, String endDateStr) {
        List<SheetVO> res = new ArrayList<>();
        List<SheetPO> sheetPOs = new ArrayList<>();
        // 销售单
        sheetPOs.addAll(saleSheetDao.findAllBasicSheetInfo(beginDateStr, endDateStr));
        // TODO: 其他单据: 销售退货，进货单， 进货退货单， 付款单， 收款单， 工资单， 入库单， 出库单

        for (SheetPO sheetPO: sheetPOs) {
            SheetVO sheetVO = new SheetVO();
            BeanUtils.copyProperties(sheetPO, sheetVO);
            sheetVO.setType(SheetType.SALE_SHEET.getValue());
            res.add(sheetVO);
        }
        return res;
    }
}
