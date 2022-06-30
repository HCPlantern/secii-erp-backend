package com.nju.edu.erp.service.Impl;

import com.nju.edu.erp.dao.EmployeeDAO;
import com.nju.edu.erp.model.po.EmployeePO;
import com.nju.edu.erp.model.vo.employee.EmployeeVO;
import com.nju.edu.erp.service.HumanResourceService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HumanResourceServiceImpl implements HumanResourceService {

    private final EmployeeDAO employeeDAO;

    @Autowired
    public HumanResourceServiceImpl(EmployeeDAO employeeDAO) {
        this.employeeDAO = employeeDAO;
    }

    public void addEmployee(EmployeeVO employeeVO) {
        EmployeePO employeePO = new EmployeePO();
        BeanUtils.copyProperties(employeeVO, employeePO);
        employeeDAO.addEmployee(employeePO);
    }

    public List<EmployeeVO> queryAllEmployees() {
        List<EmployeeVO> employeeVOList = employeeDAO.findAllEmployees();
        return employeeVOList;
    }

    public void updateEmployeeInfo(EmployeeVO employeeVO) {
        EmployeePO employeePO = new EmployeePO();
        BeanUtils.copyProperties(employeeVO, employeePO);
        employeeDAO.updateEmployeeInfo(employeePO);
    }

    public void deleteEmployeeById(int id) {
        employeeDAO.deleteEmployeeById(id);
    }

    public void generateSalarySheet() {
        //todo
    }

}
