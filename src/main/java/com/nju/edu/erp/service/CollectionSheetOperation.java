package com.nju.edu.erp.service;

import com.nju.edu.erp.enums.sheetState.CollectionSheetState;
import com.nju.edu.erp.model.vo.CollectionSheetVO;

import java.util.List;

public interface CollectionSheetOperation extends CommonSheetOperation{
    public void makeCollectionSheet(CollectionSheetVO collectionSheetVO);
    public List<CollectionSheetVO> findAllCollectionSheetByState(CollectionSheetState state);
}
