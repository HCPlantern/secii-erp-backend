package com.nju.edu.erp.service;

import com.nju.edu.erp.enums.sheetState.CollectionSheetState;
import com.nju.edu.erp.model.vo.CollectionSheetVO;
import com.nju.edu.erp.model.vo.UserVO;

import java.util.List;

public interface CollectionService {

    public void makeCollectionSheet(UserVO userVO, CollectionSheetVO collectionSheetVO);

    public void approval(String collectionSheetId, CollectionSheetState state);

    public List<CollectionSheetVO> findAllCollectionSheetByState(CollectionSheetState state);

}
