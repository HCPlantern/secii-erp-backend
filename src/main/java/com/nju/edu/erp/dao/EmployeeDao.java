package com.nju.edu.erp.dao;

import com.nju.edu.erp.model.po.EmployeePO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface EmployeeDao extends Dao {

    int addEmployee(EmployeePO employeePO);

    List<EmployeePO> findAllEmployees();

    void updateEmployeeInfo(EmployeePO employeePO);

    void deleteEmployeeById(int id);

    int getGradeById(int id);
}
