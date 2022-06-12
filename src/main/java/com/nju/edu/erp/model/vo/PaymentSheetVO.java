package com.nju.edu.erp.model.vo;

import com.nju.edu.erp.enums.sheetState.PaymentSheetState;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PaymentSheetVO {
    private String id;
    private Integer customer;
    private String operator;
    private List<PaymentSheetContentVO> paymentSheetContentVOS;
    private BigDecimal totalAmount;
    private PaymentSheetState state;
}
