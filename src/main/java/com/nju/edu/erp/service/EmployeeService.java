package com.nju.edu.erp.service;

import com.nju.edu.erp.model.po.EmployeePO;
import com.nju.edu.erp.model.vo.UserVO;
import com.nju.edu.erp.model.vo.humanResource.EmployeeVO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface EmployeeService {
    /**
     * 添加一个员工
     * @param employeeVO
     * @return 自动创建的user账号
     */
    UserVO addEmployee(EmployeeVO employeeVO);

    List<EmployeePO> queryAllEmployees();

    /**
     * 更新员工信息，数据库触发器级联更新user.role
     * @param employeeVO
     */
    void updateEmployeeInfo(EmployeeVO employeeVO);

    /**
     * 根据员工id删除员工，数据库触发器级联删除user
     * @param id
     */
    void deleteEmployeeById(int id);
}
