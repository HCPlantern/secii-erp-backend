package com.nju.edu.erp.model.po;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.nju.edu.erp.enums.sheetState.CollectionSheetState;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 收货单
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CollectionSheetPO extends ISheetPO{
    /**
     * 收款单id
     * 格式为SKD-yyyyMMdd-xxxxx
     */
    private String id;

    /**
     * 客户id
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
    private CollectionSheetState state;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;
}
