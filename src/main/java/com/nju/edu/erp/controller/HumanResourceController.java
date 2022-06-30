package com.nju.edu.erp.controller;

import com.nju.edu.erp.common.Response;
import com.nju.edu.erp.model.vo.employee.EmployeeVO;
import com.nju.edu.erp.service.HumanResourceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping(path = "/humanResource")
@Api(tags = "HumanResourceController")
public class HumanResourceController {
    private final HumanResourceService humanResourceService;

    @Autowired
    public HumanResourceController(HumanResourceService humanResourceService) {
        this.humanResourceService = humanResourceService;
    }

    @ApiOperation("添加员工，返回用户信息")
    @PostMapping("/addEmployee")
    public Response addEmployee(@RequestBody EmployeeVO employeeVO) {
        return Response.buildSuccess(humanResourceService.addEmployee(employeeVO));
    }

    @ApiOperation("查询所有员工")
    @GetMapping("/queryAllEmployees")
    public Response queryAllEmployees() {
        return Response.buildSuccess(humanResourceService.queryAllEmployees());
    }

    @ApiOperation("更新员工信息")
    @PostMapping("/updateEmployeeInfo")
    public Response updateEmployeeInfo(@RequestBody EmployeeVO employeeVO) {
        humanResourceService.updateEmployeeInfo(employeeVO);
        return Response.buildSuccess();
    }

    @ApiOperation("根据员工id删除员工")
    @GetMapping("/deleteEmployeeById")
    public Response deleteEmployeeById(@RequestParam int id) {
        humanResourceService.deleteEmployeeById(id);
        return Response.buildSuccess();
    }
}
