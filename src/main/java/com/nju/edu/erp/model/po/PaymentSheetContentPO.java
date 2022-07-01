package com.nju.edu.erp.model.po;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PaymentSheetContentPO extends ISheetPO{
    private Integer id;
    private Integer companyAccountId;
    private BigDecimal transferAmount;
    private String paymentSheetId;
    private String remark;
}
