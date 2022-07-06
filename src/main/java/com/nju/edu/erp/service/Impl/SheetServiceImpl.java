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
    Map<Dao, SheetType> typeMap = new HashMap<>();

    @Autowired
    public SheetServiceImpl(SaleSheetDao saleSheetDao, SaleReturnsSheetDao saleReturnsSheetDao,
                            PurchaseSheetDao purchaseSheetDao, PurchaseReturnsSheetDao purchaseReturnsSheetDao,
                            PaymentSheetDao paymentSheetDao, CollectionDao collectionDao, SalarySheetDao salarySheetDao,
                            WarehouseInputSheetDao warehouseInputSheetDao, WarehouseOutputSheetDao warehouseOutputSheetDao) {
        this.typeMap.put(saleSheetDao, SheetType.SALE_SHEET);
        this.typeMap.put(saleReturnsSheetDao, SheetType.SALE_RETURN_SHEET);
        this.typeMap.put(purchaseSheetDao, SheetType.PURCHASE_SHEET);
        this.typeMap.put(purchaseReturnsSheetDao, SheetType.PURCHASE_RETURN_SHEET);
        this.typeMap.put(paymentSheetDao, SheetType.PAYMENT_SHEET);
        this.typeMap.put(collectionDao, SheetType.COLLECTION_SHEET);
        this.typeMap.put(salarySheetDao, SheetType.SALARY_SHEET);
        this.typeMap.put(warehouseInputSheetDao, SheetType.WAREHOUSE_SHEET);
        this.typeMap.put(warehouseOutputSheetDao, SheetType.WAREHOUSE_RETURN_SHEET);
    }


    @Override
    public List<SheetVO> findAllSheet(String beginDateStr, String endDateStr) {
        List<SheetVO> res = new ArrayList<>();
        // 遍历map
        for (Map.Entry<Dao, SheetType> entry : this.typeMap.entrySet()) {
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
