package com.nju.edu.erp.service;

import com.nju.edu.erp.model.po.EmployeePO;
import com.nju.edu.erp.model.vo.EmployeeVO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface HumanResourceService {

    void addEmployee(EmployeePO employeePO);

    List<EmployeeVO> queryAllEmployees();

    void updateEmployeeInfo(EmployeePO employeePO);

    void generateSalarySheet();

}
