package com.nju.edu.erp.model.vo.humanResource;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.nju.edu.erp.enums.Role;
import com.nju.edu.erp.enums.SalaryCalculationMethod;
import com.nju.edu.erp.enums.SalaryPaymentMethod;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class JobVO {
    /**
     * 岗位id
     */
    @JsonProperty("id")
    private Integer id;

    /**
     * 岗位名称（唯一）
     */
    @JsonProperty("name")
    private Role name;

    /**
     * 基本工资
     */
    @JsonProperty("baseWage")
    private BigDecimal baseWage;

    /**
     * 薪资计算方法
     */
    @JsonProperty("salaryCalculationMethod")
    private SalaryCalculationMethod salaryCalculationMethod;

    /**
     * 薪资发放方法
     */
    @JsonProperty("salaryPaymentMethod")
    private SalaryPaymentMethod salaryPaymentMethod;

    /**
     * 岗位工资
     */
    @JsonProperty("postWage")
    private BigDecimal postWage;

    /**
     * 年终奖
     */
    @JsonProperty("annualBonus")
    private BigDecimal annualBonus;

    /**
     * 提成率
     */
    @JsonProperty("deductRate")
    private BigDecimal deductRate;

    /**
     * 岗位级别加薪率
     */
    @JsonProperty("gradeRate")
    private BigDecimal gradeRate;

    /**
     * 失业保险金
     */
    @JsonProperty("insurance")
    private BigDecimal insurance;

    /**
     * 住房公积金
     */
    @JsonProperty("housingFund")
    private BigDecimal housingFund;
}
