package com.nju.edu.erp.controller;

import com.nju.edu.erp.service.SalaryService;
import com.nju.edu.erp.auth.Authorized;
import com.nju.edu.erp.common.Response;
import com.nju.edu.erp.enums.Role;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/salary")
@Api(tags = "SalaryController")
public class SalaryController {
    private final SalaryService salaryService;

    @Autowired
    SalaryController(SalaryService salaryService) {
        this.salaryService = salaryService;
    }

    @GetMapping(path = "/getSalarySheetByTime")
    @Authorized(roles = {Role.ADMIN, Role.GM, Role.FINANCIAL_STAFF})
    @ApiOperation(value = "根据时间查询工资单")
    public Response getSalarySheetByTime(@Param("beginTime") String beginTime, @Param("endTime") String endTime) {
        return Response.buildSuccess(salaryService.getSalarySheetByTime(beginTime, endTime));
    }

    @GetMapping(path = "/getSalarySheetById")
    @Authorized(roles = {Role.ADMIN, Role.GM, Role.FINANCIAL_STAFF})
    @ApiOperation(value = "根据id查询工资单")
    public Response getSalarySheetById(@Param("id") Integer id) {
        return Response.buildSuccess(salaryService.getSalarySheetById(id));
    }

}
