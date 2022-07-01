package com.nju.edu.erp.dao;

import com.nju.edu.erp.model.po.EmployeePO;
import com.nju.edu.erp.model.vo.humanResource.EmployeeVO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface EmployeeDao extends Dao {

    int addEmployee(EmployeePO employeePO);

    List<EmployeeVO> findAllEmployees();

    void updateEmployeeInfo(EmployeePO employeePO);

    void deleteEmployeeById(int id);

//    void generateSalarySheet();
}
