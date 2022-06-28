package com.nju.edu.erp.service;

import com.nju.edu.erp.enums.BaseEnum;
import com.nju.edu.erp.enums.sheetState.CollectionSheetState;
import com.nju.edu.erp.model.vo.CollectionSheetVO;
import com.nju.edu.erp.model.vo.UserVO;

import java.util.List;

public interface CollectionService extends CollectionSheetOperation{

    public void makeCollectionSheet(CollectionSheetVO collectionSheetVO);

    public void approval(String collectionSheetId, BaseEnum state);

    public List<CollectionSheetVO> findAllCollectionSheetByState(CollectionSheetState state);

}
