package com.nju.edu.erp.service;

import com.nju.edu.erp.enums.BaseEnum;
import com.nju.edu.erp.enums.sheetState.SaleReturnsSheetState;
import com.nju.edu.erp.model.vo.UserVO;
import com.nju.edu.erp.model.vo.saleReturns.SaleReturnsSheetVO;

import java.util.List;

public interface SaleReturnsService extends SaleReturnsSheetOperation{

    /**
     * 制定销售退货单
     *
     * @param userVO             操作人员
     * @param saleReturnsSheetVO 销售退货单
     */
    void makeSaleReturnSheet(UserVO userVO, SaleReturnsSheetVO saleReturnsSheetVO);

    /**
     * 根据销售退货单状态进行审批
     * (state == "待二级审批"/"审批完成"/"审批失败") 在controller层进行权限控制
     *
     * @param saleReturnsSheetId 销售退货单ID
     * @param state              销售退货单状态
     */
    void approval(String saleReturnsSheetId, BaseEnum state);

    /**
     * 根据状态获取销售退货单
     * 若 state == null 则获取所有销售退货单
     * @param state 需要查询的销售退货单状态
     * @return 满足条件的销售退货单
     */
    List<SaleReturnsSheetVO> getSaleReturnsSheetByState(SaleReturnsSheetState state);



    /**
     * 根据销售退货单Id搜索销售单信息
     *
     * @param id 销售单Id
     * @return 销售退货单全部信息
     */
    SaleReturnsSheetVO getSaleReturnsSheetById(String id);
}
