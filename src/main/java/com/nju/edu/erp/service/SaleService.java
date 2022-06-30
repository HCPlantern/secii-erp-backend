package com.nju.edu.erp.service;

import com.nju.edu.erp.enums.BaseEnum;
import com.nju.edu.erp.enums.sheetState.SaleSheetState;
import com.nju.edu.erp.model.vo.CustomerPurchaseAmountVO;
import com.nju.edu.erp.model.vo.sale.SaleDetailVO;
import com.nju.edu.erp.model.vo.sale.SaleSheetVO;
import com.nju.edu.erp.model.vo.UserVO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface SaleService extends SaleSheetOperation{

    /**
     * 指定销售单
     *
     * @param userVO 用户信息
     * @param saleSheetVO 销售单信息
     */
    void makeSaleSheet(UserVO userVO, SaleSheetVO saleSheetVO);

    /**
     * 根据单据状态获取销售单
     *
     * @param state 单据状态
     * @return 销售单列表
     */
    List<SaleSheetVO> getSaleSheetByState(SaleSheetState state);

    /**
     * 审批单据
     *
     * @param saleSheetId 单据id
     * @param state 审批结果
     */
    void approval(String saleSheetId, BaseEnum state);

    /**
     * 获取某个销售人员某段时间内消费总金额最大的客户(不考虑退货情况,销售单不需要审批通过,如果这样的客户有多个，仅保留一个)
     *
     * @param salesman     销售人员的名字
     * @param beginDateStr 开始时间字符串
     * @param endDateStr   结束时间字符串
     * @return 客户消费总金额最大的客户
     */
    CustomerPurchaseAmountVO getMaxAmountCustomerOfSalesmanByTime(String salesman, String beginDateStr, String endDateStr);

    /**
     * 根据销售单Id搜索销售单信息
     *
     * @param saleSheetId 销售单Id
     * @return 销售单全部信息
     */
    SaleSheetVO getSaleSheetById(String saleSheetId);


    /**
     * 根据时间段搜索销售详细信息
     * @param beginDateStr 开始时间字符串
     * @param endDateStr 结束时间字符串
     * @return 销售详细信息
     */
    List<SaleDetailVO> findAllSaleDetailByTime(String beginDateStr, String endDateStr);
}
