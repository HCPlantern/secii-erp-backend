package com.nju.edu.erp.model.po;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EmployeePO {
    /**
     * 员工id（与系统用户id相同）
     */
    private String id;
    /**
     * 姓名
     */
    private String name;
    /**
     * 性别
     */
    private String sex;
    /**
     * 生日
     */
    private String birthday;
    /**
     * 手机号码
     */
    private String phone;
    /**
     * 工作岗位
     */
    private String job;
    /**
     * 基本工资
     */
    private String baseWage;
    /**
     * 岗位工资
     */
    private String postWage;
    /**
     * 岗位级别
     */
    private String jobGrade;
    /**
     * 薪资计算方式
     */
    private String salaryCalculationMethod;
    /**
     * 薪资发放方式
     */
    private String salaryPaymentMethod;
    /**
     * 工资卡账户
     */
    private String salaryAccount;
}
