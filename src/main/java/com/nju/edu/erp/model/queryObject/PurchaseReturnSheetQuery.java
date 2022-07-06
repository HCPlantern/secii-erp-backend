package com.nju.edu.erp.model.queryObject;

import com.nju.edu.erp.enums.sheetState.PurchaseReturnsSheetState;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PurchaseReturnSheetQuery {
    /**
     * 进货退货单单据编号（格式为：JHTHD-yyyyMMdd-xxxxx
     */
    private String id;
    /**
     * 关联的进货单id
     */
    private String purchaseSheetId;
    /**
     * 操作员
     */
    private String operator;
    /**
     * 单据状态
     */
    private PurchaseReturnsSheetState state;

    private String beginTime;
    private String endTime;
}
