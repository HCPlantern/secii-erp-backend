package com.nju.edu.erp.model.vo.humanResource;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.nju.edu.erp.enums.Role;
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
public class SalarySheetVO {
    /**
     * 单据编号
     */
    private String id;

    /**
     * 员工id（与系统用户id相同）
     */
    private Integer employeeId;

    /**
     * 员工姓名
     */
    private String employeeName;

    /**
     * 单据创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date createTime;

    /**
     * 岗位
     */
    private Role job;

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
     * 税款
     */
    private BigDecimal tax;

    /**
     * 失业保险
     */
    private BigDecimal insurance;

    /**
     * 住房公积金
     */
    private BigDecimal housingFund;

    /**
     * 税后工资
     */
    private BigDecimal taxedSalary;

    /**
     * 工资卡账户
     */
    private String salaryAccount;

    /**
     * 审批状态
     */
    private SalarySheetState state;
}
