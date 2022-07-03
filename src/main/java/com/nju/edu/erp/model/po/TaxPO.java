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
public class TaxPO {
    /**
     * 税前所得
     */
    private BigDecimal base;

    /**
     * 税率
     */
    private BigDecimal rate;
}
