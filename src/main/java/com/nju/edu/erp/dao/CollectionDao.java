package com.nju.edu.erp.dao;

import com.nju.edu.erp.enums.sheetState.CollectionSheetState;
import com.nju.edu.erp.model.po.CollectionSheetPO;
import com.nju.edu.erp.model.po.TransferListSheetPO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface CollectionDao {
    /**
     * 保存收货单
     * @param collectionSheetPO 收款单PO
     * @return
     */
    public int saveCollectionSheetSheet(CollectionSheetPO collectionSheetPO);

    /**
     * 保存收款单内容列表
     * @param transferListSheetPOS 收款单内容列表(就是转账列表)
     * @return 受影响的行数
     */
    public int saveTransferList(List<TransferListSheetPO> transferListSheetPOS);

    /**
     * 按照id查询收款单
     * @param id 根据收款单id查询收款单
     * @return 受影响的行数
     */
    public CollectionSheetPO findCollectionSheetById(String id);


    /**
     * 查询所有的收款单
     * @return 收款单集合
     */
    public List<CollectionSheetPO> findAllCollectionSheet();

    /**
     * 查询所有收款单的内容
     * @Param collectionSheetId 所关联的收款单Id
     * @return 当前收款单内容的集合
     */
    public List<TransferListSheetPO> findAllCollectionSheetContent(String collectionSheetId);


    /**
     * 更新id收款单的state
     * @param id
     * @param state
     * @return
     */
    public int updateState(String id, CollectionSheetState state);

}
