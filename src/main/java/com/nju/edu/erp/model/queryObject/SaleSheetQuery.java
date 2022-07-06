package com.nju.edu.erp.model.queryObject;

import com.nju.edu.erp.enums.sheetState.SaleSheetState;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SaleSheetQuery {
    private String id;
    private Integer supplier;
    private String operator;
    private SaleSheetState state;
    private String beginTime;
    private String endTime;
    private String salesman;
}
