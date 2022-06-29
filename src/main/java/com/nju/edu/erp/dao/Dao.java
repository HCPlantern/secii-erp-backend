package com.nju.edu.erp.dao;

import com.nju.edu.erp.model.po.SheetPO;

import java.util.List;

public interface Dao {
    List<SheetPO> findAllBasicSheetInfo(String beginTime, String endTime);
}
