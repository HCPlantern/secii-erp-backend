package com.nju.edu.erp.model.po;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.nju.edu.erp.enums.sheetState.SalarySheetState;
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
public class SalarySheetPO {
    /**
     * 员工id（与系统用户id相同）
     */
    private Integer id;

    /**
     * 姓名
     */
    private Integer employeeId;

    /**
     *
     * 单据创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date createTime;

    /**
     * 基本工资
     */
    private BigDecimal baseWage;

    /**
     * 岗位工资
     */
    private BigDecimal postWage;
    /**
     * 未税总工资
     */
    private BigDecimal totalSalary;

    /**
     * 税后工资
     */
    private BigDecimal taxedSalary;

    /**
     * 审批状态
     */
    private SalarySheetState state;
}

