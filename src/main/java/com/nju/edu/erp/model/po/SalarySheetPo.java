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
public class SalarySheetPo {
    /**
     * 员工id（与系统用户id相同）
     */
    private Integer id;

    /**
     * 姓名
     */
    private String name;

    /**
     * 工作岗位
     */
    private String job;

    /**
     * 基本工资
     */
    private BigDecimal baseWage;

    /**
     * 岗位工资
     */
    private BigDecimal postWage;

    /**
     * 岗位级别
     */
    private Integer jobGrade;

    /**
     * 薪资计算方式
     */
    private String salaryCalculationMethod;

    /**
     * 薪资发放方式
     */
    private String salaryPaymentMethod;

    /**
     * 未税总工资
     */
    private BigDecimal totalSalary;

    /**
     * 税后工资
     */
    private BigDecimal taxedSalary;

}

