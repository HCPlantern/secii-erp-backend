package com.nju.edu.erp.model.po;

import com.fasterxml.jackson.annotation.JsonFormat;
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
public class WarehouseInputSheetContentPO extends ISheetPO{
    /**
     * 入库商品列表id
     */
    private Integer id;
    /**
     * 入库单编号
     */
    private String warehouseInputSheetId;
    /**
     * 商品id
     */
    private String pid;
    /**
     * 商品数量
     */
    private Integer quantity;
    /**
     * 单价
     */
    private BigDecimal purchasePrice;
    /**
     * 出厂日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date productionDate;
    /**
     * 备注
     */
    private String remark;
}
