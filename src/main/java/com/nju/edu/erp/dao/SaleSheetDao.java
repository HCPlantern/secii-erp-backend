package com.nju.edu.erp.dao;


import com.nju.edu.erp.enums.BaseEnum;
import com.nju.edu.erp.enums.sheetState.SaleSheetState;
import com.nju.edu.erp.model.po.*;
import com.nju.edu.erp.model.queryObject.SaleSheetQuery;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Repository
@Mapper
public interface SaleSheetDao extends Dao {

    /**
     * 获取最近一条销售单
     *
     * @return 销售单
     */
    SaleSheetPO getLatestSheet();

    /**
     * 存入一条销售单记录
     *
     * @param toSave 一条销售单记录
     * @return 影响的行数
     */
    int saveSheet(SaleSheetPO toSave);

    /**
     * 把销售单上的具体内容存入数据库
     *
     * @param saleSheetContent 入销售单上的具体内容
     */
    int saveBatchSheetContent(List<SaleSheetContentPO> saleSheetContent);

    /**
     * 查找所有销售单
     *
     * @return 满足查询条件的销售单列表
     * @Param query 查询条件
     */
    List<SaleSheetPO> findAllSheet(SaleSheetQuery query);

    /**
     * 根据时间段查找销售单
     *
     * @param beginTime 开始时间
     * @param endTime   结束时间
     * @return 销售单列表
     */
    List<SaleSheetPO> findAllSheetByTime(@Param("beginTime") String beginTime, @Param("endTime") String endTime);

    List<SaleSheetPO> findAllByState(@Param("state") SaleSheetState state);

    /**
     * 查找指定id的销售单
     *
     * @param id 销售单id
     * @return 销售单
     */
    SaleSheetPO findSheetById(String id);

    /**
     * 查找指定销售单下具体的商品内容
     *
     * @param sheetId 销售单id
     */
    List<SaleSheetContentPO> findContentBySheetId(String sheetId);

    /**
     * 更新指定销售单的状态
     *
     * @param sheetId 销售单id
     * @param state   销售单状态
     * @return 影响的行数
     */
    int updateSheetState(String sheetId, BaseEnum state);


    /**
     * 根据当前状态更新销售单状态
     *
     * @param sheetId 销售单id
     * @param prev    当前状态
     * @param state   更新后的状态
     * @return 影响的行数
     */
    int updateSheetStateOnPrev(String sheetId, BaseEnum prev, BaseEnum state);


    /**
     * 获取某个销售人员某段时间内消费总金额最大的客户(不考虑退货情况,销售单不需要审批通过,如果这样的客户有多个，仅保留一个)
     *
     * @param salesman  销售人员的名字
     * @param beginTime 开始时间
     * @param endTime   结束时间
     * @return 客户的信息
     */
    CustomerPurchaseAmountPO getMaxAmountCustomerOfSalesmanByTime(String salesman, Date beginTime, Date endTime);

    /**
     * 获取时间段内的销售详细信息
     *
     * @param beginTime 开始时间
     * @param endTime   结束时间
     * @return 销售详细信息
     */
    List<SaleDetailPO> findAllSaleDetailByTime(@Param("beginTime") String beginTime, @Param("endTime") String endTime);

    /**
     * 获取所有销售单的基本信息
     *
     * @return 所有销售单基本信息
     */
    List<SheetPO> findAllBasicSheetInfo(@Param("beginTime") String beginTime, @Param("endTime") String endTime);

    /**
     * 获取某个销售员一段时间内审批完成的销售总额，用于计算提成
     *
     * @param employeeId 员工id
     * @return 折扣前销售额
     */
    BigDecimal calTotalAmountOfSalesman(int employeeId, @Param("beginTime") String beginTime, @Param("endTime") String endTime);
}
