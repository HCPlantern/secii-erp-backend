package com.nju.edu.erp.model.vo;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.nju.edu.erp.enums.sheetState.CollectionSheetState;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CollectionSheetVO {
    /**
     * 表示收款单id
     */
    private String id;
    /**
     * 对应的客户
     */
    private Integer customer;
    /**
     * 操作员
     */
    private String operator;
    /**
     * 表示收款单的具体内容
     */
    private List<TransferListSheetVO> content;
    /**
     * 收款总金额
     * 这个是根据收款单具体内容算出来的
     */
    private BigDecimal totalAmount;
    /**
     * 状态
     */
    private CollectionSheetState state;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createTime;
}
