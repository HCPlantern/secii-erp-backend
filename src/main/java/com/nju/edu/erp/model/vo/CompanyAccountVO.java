package com.nju.edu.erp.model.vo;

import io.swagger.models.auth.In;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CompanyAccountVO {
    private Integer id;
    private String name;
    private BigDecimal amount;
}
