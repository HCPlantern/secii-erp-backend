package com.nju.edu.erp.model.po;

import com.nju.edu.erp.enums.Role;
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
    private Integer id;

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
    private Role job;

    /**
     * 岗位级别
     */
    private Integer jobGrade;

    /**
     * 工资卡账户
     */
    private String salaryAccount;
}
