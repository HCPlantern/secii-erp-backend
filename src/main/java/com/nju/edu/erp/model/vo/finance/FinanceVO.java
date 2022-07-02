package com.nju.edu.erp.model.vo.finance;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FinanceVO {
    /**
     * 销售收入 （已减去折扣和代金券）
     */
    private BigDecimal saleIncome;
    /**
     * 折让金额
     */
    private BigDecimal discount;
    /**
     * 商品类收入
     */
    private BigDecimal commodityIncome;
    /**
     * 总收入
     */
    private BigDecimal finalIncome;
    /**
     * 销售成本
     */
    private BigDecimal saleOutcome;
    /**
     * 商品类成本
     */
    private BigDecimal commodityOutcome;
    /**
     * 人力成本
     */
    private BigDecimal humanResourceOutcome;
    /**
     * 总成本
     */
    private BigDecimal finalOutcome;
    /**
     * 总利润
     */
    private BigDecimal profit;
}
