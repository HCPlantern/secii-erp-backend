package com.nju.edu.erp.model.vo;

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
public class PaymentSheetContentVO {
    /**
     * 自增id
     */
    private Integer id;
    /**
     * 所关联的公司银行账户id
     */
    private Integer companyAccountId;
    /**
     * 转账金额
     */
    private BigDecimal transferAmount;
    /**
     * 关联的付款单id
     */
    private String paymentSheetId;
    /**
     * 备注
     */
    private String remark;
}
