package com.nju.edu.erp.dao;

import com.nju.edu.erp.enums.BaseEnum;
import com.nju.edu.erp.enums.sheetState.PurchaseSheetState;
import com.nju.edu.erp.model.po.PurchaseSheetContentPO;
import com.nju.edu.erp.model.po.PurchaseSheetPO;
import com.nju.edu.erp.model.po.SheetPO;
import com.nju.edu.erp.model.queryObject.PurchaseSheetQuery;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface PurchaseSheetDao extends Dao {
    /**
     * 获取最近一条进货单
     *
     * @return 最近一条进货单
     */
    PurchaseSheetPO getLatest();

    /**
     * 存入一条进货单记录
     *
     * @param toSave 一条进货单记录
     * @return 影响的行数
     */
    int save(PurchaseSheetPO toSave);

    /**
     * 把进货单上的具体内容存入数据库
     *
     * @param purchaseSheetContent 进货单上的具体内容
     */
    void saveBatch(List<PurchaseSheetContentPO> purchaseSheetContent);

    /**
     * 返回所有进货单
     *
     * @return 进货单列表
     * @Param query 查询条件
     */
    List<PurchaseSheetPO> findAll(PurchaseSheetQuery query);

    /**
     * 根据state返回进货单
     *
     * @param state 进货单状态
     * @return 进货单列表
     */
    List<PurchaseSheetPO> findAllByState(PurchaseSheetState state);

    int updateState(String purchaseSheetId, BaseEnum state);

    int updateStateV2(String purchaseSheetId, BaseEnum prevState, BaseEnum state);

    PurchaseSheetPO findOneById(String purchaseSheetId);

    List<PurchaseSheetContentPO> findContentByPurchaseSheetId(String purchaseSheetId);

    List<SheetPO> findAllBasicSheetInfo(String beginTime, String endTime);

    List<PurchaseSheetPO> findByCreateTime(String beginTime, String endTime);
}
