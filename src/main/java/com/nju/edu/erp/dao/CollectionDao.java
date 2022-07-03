package com.nju.edu.erp.dao;

import com.nju.edu.erp.enums.BaseEnum;
import com.nju.edu.erp.enums.sheetState.CollectionSheetState;
import com.nju.edu.erp.model.po.CollectionSheetPO;
import com.nju.edu.erp.model.po.SheetPO;
import com.nju.edu.erp.model.po.TransferListSheetPO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface CollectionDao extends Dao {
    /**
     * 保存收货单
     *
     * @param collectionSheetPO 收款单PO
     * @return
     */
    int saveCollectionSheetSheet(CollectionSheetPO collectionSheetPO);

    /**
     * 保存收款单内容列表
     *
     * @param transferListSheetPOS 收款单内容列表(就是转账列表)
     * @return 受影响的行数
     */
    int saveTransferList(List<TransferListSheetPO> transferListSheetPOS);

    /**
     * 按照id查询收款单
     *
     * @param id 根据收款单id查询收款单
     * @return 收款单PO
     */
    CollectionSheetPO findCollectionSheetById(String id);


    /**
     * 查询所有的收款单
     *
     * @return 收款单集合
     */
    List<CollectionSheetPO> findAllCollectionSheet();

    List<CollectionSheetPO> findAllCollectionSheetByState(CollectionSheetState state);

    /**
     * 查询所有收款单的内容
     *
     * @return 当前收款单内容的集合
     * @Param collectionSheetId 所关联的收款单Id
     */
    List<TransferListSheetPO> findAllCollectionSheetContent(String collectionSheetId);


    /**
     * 更新id收款单的state
     *
     * @param id    收款单id
     * @param state 收款单状态
     * @return
     */
    int updateState(String id, BaseEnum state);

    CollectionSheetPO findLatest();

    List<SheetPO> findAllBasicSheetInfo(@Param("beginTime") String beginTime, @Param("endTime") String endTime);

}
