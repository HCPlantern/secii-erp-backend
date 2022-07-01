package com.nju.edu.erp.dao;

import com.nju.edu.erp.enums.sheetState.WarehouseOutputSheetState;
import com.nju.edu.erp.model.po.SheetPO;
import com.nju.edu.erp.model.po.WarehouseOutputSheetContentPO;
import com.nju.edu.erp.model.po.WarehouseOutputSheetPO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
@Mapper
public interface WarehouseOutputSheetDao extends Dao {
    /**
     * 获取最近一条出库单
     *
     * @return 最近一条出库单
     */
    WarehouseOutputSheetPO getLatest();

    /**
     * 存入一条出库单记录
     *
     * @param toSave 一条出库单记录
     */
    void save(WarehouseOutputSheetPO toSave);

    /**
     * 把出库单上的具体内容存入数据库
     *
     * @param warehouseOutputListPOSheetContent 出库单上的具体内容
     */
    void saveBatch(List<WarehouseOutputSheetContentPO> warehouseOutputListPOSheetContent);

    /**
     * 获取所有出库单记录
     *
     * @return 所有出库单记录
     */
    List<WarehouseOutputSheetPO> getAllSheets();

    /**
     * 获取指定状态的出库单记录
     *
     * @param state 出库单状态
     * @return 所有指定状态的出库单
     */
    List<WarehouseOutputSheetPO> getDraftSheets(WarehouseOutputSheetState state);

    /**
     * 根据id获取单据
     *
     * @param sheetId 出库单Id
     * @return 对应的出库单
     */
    WarehouseOutputSheetPO findById(String sheetId);

    /**
     * 更新PO
     *
     * @param warehouseOutputSheetPO 出库单
     */
    void updateById(WarehouseOutputSheetPO warehouseOutputSheetPO);

    /**
     * 获取出库单具体内容
     *
     * @param sheetId 出库单Id
     * @return 出库单具体内容
     */
    List<WarehouseOutputSheetContentPO> getAllContentById(String sheetId);


    /**
     * 删除无批次的初始内容
     *
     * @param sheetId 出库单Id
     */
    void deleteContent(String sheetId);

    Integer getWarehouseOutputProductQuantityByTime(Date beginTime, Date endTime);

    /**
     * 通过销售单ID和商品ID找到所有的出库单内容
     *
     * @param saleSheetId 销售单ID
     * @param pid         商品ID
     * @return 出库单内容List
     */
    List<WarehouseOutputSheetContentPO> findBatchBySaleSheetIdAndPId(String saleSheetId, String pid);

    List<SheetPO> findAllBasicSheetInfo(@Param("beginTime") Date beginTime, @Param("endTime") Date endTime);
}
