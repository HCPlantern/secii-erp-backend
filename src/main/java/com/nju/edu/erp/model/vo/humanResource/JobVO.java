package com.nju.edu.erp.model.vo.humanResource;

import com.nju.edu.erp.enums.Role;
import com.nju.edu.erp.enums.SalaryCalculationMethod;
import com.nju.edu.erp.enums.SalaryPaymentMethod;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JobVO {
    /**
     * 岗位id
     */
    private Integer id;

    /**
     * 岗位名称（唯一）
     */
    private Role name;

    /**
     * 基本工资
     */
    private BigDecimal baseWage;

    /**
     * 薪资计算方法
     */
    private SalaryCalculationMethod salaryCalculationMethod;

    /**
     * 薪资发放方法
     */
    private SalaryPaymentMethod salaryPaymentMethod;

    /**
     * 岗位工资
     */
    private BigDecimal postWage;
}
