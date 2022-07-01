package com.nju.edu.erp.service.Impl;

import com.nju.edu.erp.dao.*;
import com.nju.edu.erp.enums.SheetType;
import com.nju.edu.erp.model.po.SheetPO;
import com.nju.edu.erp.model.vo.sheet.SheetVO;
import com.nju.edu.erp.service.SheetService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SheetServiceImpl implements SheetService {
    private final SaleSheetDao saleSheetDao;

    private final SaleReturnsSheetDao saleReturnsSheetDao;

    private final PurchaseSheetDao purchaseSheetDao;

    private final PurchaseReturnsSheetDao purchaseReturnsSheetDao;

    private final PaymentSheetDao paymentSheetDao;

    private final CollectionDao collectionDao;

    // TODO: SalarySheetDao
    private final WarehouseInputSheetDao warehouseInputSheetDao;

    private final WarehouseOutputSheetDao warehouseOutputSheetDao;

    @Autowired
    public SheetServiceImpl(SaleSheetDao saleSheetDao, SaleReturnsSheetDao saleReturnsSheetDao, PurchaseSheetDao purchaseSheetDao, PurchaseReturnsSheetDao purchaseReturnsSheetDao, PaymentSheetDao paymentSheetDao, CollectionDao collectionDao, WarehouseInputSheetDao warehouseInputSheetDao, WarehouseOutputSheetDao warehouseOutputSheetDao) {
        this.saleSheetDao = saleSheetDao;
        this.saleReturnsSheetDao = saleReturnsSheetDao;
        this.purchaseSheetDao = purchaseSheetDao;
        this.purchaseReturnsSheetDao = purchaseReturnsSheetDao;
        this.paymentSheetDao = paymentSheetDao;
        this.collectionDao = collectionDao;
        this.warehouseInputSheetDao = warehouseInputSheetDao;
        this.warehouseOutputSheetDao = warehouseOutputSheetDao;
    }


    @Override
    public List<SheetVO> findAllSheet(String beginDateStr, String endDateStr) {
        List<SheetVO> res = new ArrayList<>();
        Map<Dao, SheetType> map = new HashMap<Dao, SheetType>(){{
            put(saleSheetDao, SheetType.SALE_SHEET);
            put(saleReturnsSheetDao, SheetType.SALE_RETURN_SHEET);
            put(purchaseSheetDao, SheetType.PURCHASE_SHEET);
            put(purchaseReturnsSheetDao, SheetType.PURCHASE_RETURN_SHEET);
            put(paymentSheetDao, SheetType.PAYMENT_SHEET);
            put(collectionDao, SheetType.COLLECTION_SHEET);
            // TODO: add salarySheetDao
            put(warehouseInputSheetDao, SheetType.WAREHOUSE_SHEET);
            put(warehouseOutputSheetDao, SheetType.WAREHOUSE_RETURN_SHEET);
        }};
        // 遍历map
        for (Map.Entry<Dao, SheetType> entry : map.entrySet()) {
            List<SheetPO> sheetPOS = entry.getKey().findAllBasicSheetInfo(beginDateStr, endDateStr);
            for (SheetPO sheetPO : sheetPOS) {
                SheetVO sheetVO = new SheetVO();
                BeanUtils.copyProperties(sheetPO, sheetVO);
                sheetVO.setType(entry.getValue().getValue());
                res.add(sheetVO);
            }
        }
        return res;
    }
}
