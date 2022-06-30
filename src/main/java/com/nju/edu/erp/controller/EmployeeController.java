package com.nju.edu.erp.controller;

import com.nju.edu.erp.common.Response;
import com.nju.edu.erp.model.vo.humanResource.EmployeeVO;
import com.nju.edu.erp.service.EmployeeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping(path = "/employee")
@Api(tags = "EmployeeController")
public class EmployeeController {
    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @ApiOperation("添加员工，返回用户信息")
    @PostMapping("/addEmployee")
    public Response addEmployee(@RequestBody EmployeeVO employeeVO) {
        return Response.buildSuccess(employeeService.addEmployee(employeeVO));
    }

    @ApiOperation("查询所有员工")
    @GetMapping("/queryAllEmployees")
    public Response queryAllEmployees() {
        return Response.buildSuccess(employeeService.queryAllEmployees());
    }

    @ApiOperation("更新员工信息")
    @PostMapping("/updateEmployeeInfo")
    public Response updateEmployeeInfo(@RequestBody EmployeeVO employeeVO) {
        employeeService.updateEmployeeInfo(employeeVO);
        return Response.buildSuccess();
    }

    @ApiOperation("根据员工id删除员工")
    @GetMapping("/deleteEmployeeById")
    public Response deleteEmployeeById(@RequestParam int id) {
        employeeService.deleteEmployeeById(id);
        return Response.buildSuccess();
    }
}
