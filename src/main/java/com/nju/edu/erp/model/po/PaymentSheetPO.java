package com.nju.edu.erp.model.po;

import com.nju.edu.erp.enums.sheetState.PaymentSheetState;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PaymentSheetPO {
    /**
     * 付款单id
     */
    private String id;

    /**
     * 对应的客户id
     */

    private Integer customer;

    /**
     * 操作员姓名
     */
    private String operator;

    /**
     * 总额合计
     */
    private BigDecimal totalAmount;

    /**
     * 状态
     */
    private PaymentSheetState state;

    private Date createTime;
}
