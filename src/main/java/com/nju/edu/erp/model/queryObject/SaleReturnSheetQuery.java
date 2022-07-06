package com.nju.edu.erp.model.queryObject;

import com.nju.edu.erp.enums.sheetState.SaleReturnsSheetState;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SaleReturnSheetQuery {
    private String id;
    private String saleSheetId;
    private Integer supplier;
    private String operator;
    private SaleReturnsSheetState state;
    private String beginTime;
    private String endTime;
    private String salesman;
}
