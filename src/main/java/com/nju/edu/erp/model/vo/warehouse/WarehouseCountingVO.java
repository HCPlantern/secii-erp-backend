package com.nju.edu.erp.model.vo.warehouse;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelEntity;
import com.nju.edu.erp.model.vo.ProductInfoVO;
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
public class WarehouseCountingVO {
    /**
     * 库存id
     */
    @Excel(name = "库存id", width = 10)
    private Integer id;

    /**
     * 商品编号
     */
    @ExcelEntity(name = "商品id")
    private ProductInfoVO product;

    /**
     * 数量
     */
    @Excel(name = "数量", width = 10)
    private Integer quantity;

    /**
     * 进价
     */
    @Excel(name = "进价", width = 15)
    private BigDecimal purchasePrice;

    /**
     * 批次
     */
    @Excel(name = "批次", width = 10)
    private Integer batchId;

    /**
     * 出厂日期
     */
    @Excel(name = "出厂日期", width = 20, format = "yyyy-MM-dd HH:mm:ss")
    private Date productionDate;
}
