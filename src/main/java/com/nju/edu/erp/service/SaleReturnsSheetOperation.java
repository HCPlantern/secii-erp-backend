package com.nju.edu.erp.service;

import com.nju.edu.erp.enums.sheetState.SaleReturnsSheetState;
import com.nju.edu.erp.model.vo.UserVO;
import com.nju.edu.erp.model.vo.saleReturns.SaleReturnsSheetVO;

import java.util.List;

public interface SaleReturnsSheetOperation extends CommonSheetOperation{
    void makeSaleReturnSheet(UserVO userVO, SaleReturnsSheetVO saleReturnsSheetVO);
    List<SaleReturnsSheetVO> getSaleReturnsSheetByState(SaleReturnsSheetState state);
}
