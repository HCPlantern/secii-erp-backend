package com.nju.edu.erp.model.queryObject;

import com.nju.edu.erp.enums.sheetState.PurchaseSheetState;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PurchaseSheetQuery {
    /**
     * 进货单单据编号（格式为：JHD-yyyyMMdd-xxxxx
     */
    private String id;
    /**
     * 供应商id
     */
    private Integer supplier;
    /**
     * 操作员
     */
    private String operator;
    /**
     * 单据状态
     */
    private PurchaseSheetState state;

    private String beginTime;
    private String endTime;
}
