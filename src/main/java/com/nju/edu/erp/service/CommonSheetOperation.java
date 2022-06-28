package com.nju.edu.erp.service;

import com.nju.edu.erp.enums.BaseEnum;
import com.nju.edu.erp.model.po.ISheetPO;
import com.nju.edu.erp.model.vo.ISheetVO;

import java.util.List;

public interface CommonSheetOperation {
    // 审批的公共接口
    void approval(String sheetId, BaseEnum state);

}
