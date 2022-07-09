package com.nju.edu.erp.model.po;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.nju.edu.erp.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    /**
     * 用户ID
     */
    private Integer id;

    /**
     * 用户姓名
     */
    private String name;

    /**
     * 用户密码
     */
    private String password;

    /**
     * 用户身份
     */
    private Role role;

    /**
     * 关联的员工id
     */
    private int employeeId;
}