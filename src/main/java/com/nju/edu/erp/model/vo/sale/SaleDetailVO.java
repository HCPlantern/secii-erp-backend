package com.nju.edu.erp.model.vo.sale;

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
public class SaleDetailVO {
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date date;
    private String salesman;
    private String supplier;
    private Integer id;
    private String name;
    private String type;
    private Integer quantity;
    private BigDecimal unitPrice;
    private BigDecimal totalPrice;
}
