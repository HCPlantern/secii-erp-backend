package com.nju.edu.erp.service;

import com.nju.edu.erp.enums.BaseEnum;
import com.nju.edu.erp.enums.sheetState.SaleSheetState;
import com.nju.edu.erp.model.vo.UserVO;
import com.nju.edu.erp.model.vo.sale.SaleSheetVO;

import java.util.List;

public interface SaleSheetOperation extends CommonSheetOperation{

    public void makeSaleSheet(UserVO userVO, SaleSheetVO saleSheetVO);

    public List<SaleSheetVO> getSaleSheetByState(SaleSheetState state);

}
