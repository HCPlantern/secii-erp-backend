package com.nju.edu.erp.service;

import com.nju.edu.erp.enums.BaseEnum;
import com.nju.edu.erp.enums.sheetState.PurchaseSheetState;
import com.nju.edu.erp.model.vo.UserVO;
import com.nju.edu.erp.model.vo.purchase.PurchaseSheetVO;

import java.util.List;

public interface PurchaseSheetOperation extends CommonSheetOperation {

    void makePurchaseSheet(UserVO userVO, PurchaseSheetVO purchaseSheetVO);

    List<PurchaseSheetVO> getPurchaseSheetByState(PurchaseSheetState state);

    PurchaseSheetVO getPurchaseSheetById(String purchaseSheetId);

}
