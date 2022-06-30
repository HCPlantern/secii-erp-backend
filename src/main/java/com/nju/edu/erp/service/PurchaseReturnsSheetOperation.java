package com.nju.edu.erp.service;

import com.nju.edu.erp.enums.sheetState.PurchaseReturnsSheetState;
import com.nju.edu.erp.model.vo.UserVO;
import com.nju.edu.erp.model.vo.purchaseReturns.PurchaseReturnsSheetVO;

import java.util.List;

public interface PurchaseReturnsSheetOperation extends CommonSheetOperation {
    void makePurchaseReturnsSheet(UserVO userVO, PurchaseReturnsSheetVO purchaseReturnsSheetVO);

    List<PurchaseReturnsSheetVO> getPurchaseReturnsSheetByState(PurchaseReturnsSheetState state);
}
