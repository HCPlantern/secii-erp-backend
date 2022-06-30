package com.nju.edu.erp.service;

import com.nju.edu.erp.model.vo.UserVO;
import com.nju.edu.erp.model.vo.employee.EmployeeVO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface HumanResourceService {

    UserVO addEmployee(EmployeeVO employeeVO);

    List<EmployeeVO> queryAllEmployees();

    void updateEmployeeInfo(EmployeeVO employeeVO);

    void deleteEmployeeById(int id);

    void generateSalarySheet();

}
