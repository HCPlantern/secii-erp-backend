package com.nju.edu.erp.controller;

import com.nju.edu.erp.enums.sheetState.SalarySheetState;
import com.nju.edu.erp.model.vo.humanResource.JobVO;
import com.nju.edu.erp.service.SalaryService;
import com.nju.edu.erp.auth.Authorized;
import com.nju.edu.erp.common.Response;
import com.nju.edu.erp.enums.Role;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/salary")
@Api(tags = "SalaryController")
public class SalaryController {
    private final SalaryService salaryService;

    @Autowired
    SalaryController(SalaryService salaryService) {
        this.salaryService = salaryService;
    }

    @GetMapping(path = "/queryAllSalaryRules")
    @Authorized(roles = {Role.ADMIN, Role.HR})
    @ApiOperation(value = "查询所有岗位的薪资规则")
    public Response queryAllSalaryRules() {
        return Response.buildSuccess(salaryService.queryAllSalaryRules());
    }

    @PostMapping(path = "/updateDepartmentSalaryRule")
    @Authorized(roles = {Role.ADMIN, Role.HR})
    @ApiOperation(value = "更新岗位的薪资规则")
    public Response updateDepartmentSalaryRule(@RequestBody JobVO salaryRuleEditForm) {
        salaryService.updateSalaryRuleById(salaryRuleEditForm);
        return Response.buildSuccess();
    }

    @GetMapping(path = "/getSalarySheetByTime")
    @Authorized(roles = {Role.ADMIN, Role.GM, Role.FINANCIAL_STAFF, Role.HR})
    @ApiOperation(value = "根据时间查询工资单")
    public Response getSalarySheetByTime(@Param("beginTime") String beginTime, @Param("endTime") String endTime) {
        return Response.buildSuccess(salaryService.getSalarySheetByTime(beginTime, endTime));
    }

    @GetMapping(path = "/getSalarySheetById")
    @Authorized(roles = {Role.ADMIN, Role.GM, Role.FINANCIAL_STAFF, Role.HR})
    @ApiOperation(value = "根据id查询工资单")
    public Response getSalarySheetById(@Param("id") String id) {
        return Response.buildSuccess(salaryService.getSalarySheetById(id));
    }

    @GetMapping(path = "/getSalarySheetByState")
    @Authorized(roles = {Role.ADMIN, Role.GM, Role.FINANCIAL_STAFF, Role.HR})
    @ApiOperation(value = "根据状态查询工资单")
    public Response getSalarySheetByState(@RequestParam(value = "state", required = false) SalarySheetState state) {
        return Response.buildSuccess(salaryService.getSalarySheetByState(state));
    }

    @GetMapping(path = "/generateSalarySheet")
    @Authorized(roles = {Role.ADMIN, Role.HR})
    @ApiOperation(value = "生成一个工作周期内的工资单")
    public Response generateSalarySheet(@RequestParam(value = "beginTime") String beginTimeStr, @RequestParam(value = "endTime") String endTimeStr) {
        salaryService.generateSalarySheet(beginTimeStr, endTimeStr);
        return Response.buildSuccess();
    }


    @GetMapping("/salary-first-approval")
    public Response fistApproval(@RequestParam("salarySheetId") String salarySheetId,@RequestParam("state") SalarySheetState state){
        if(state.equals(SalarySheetState.FAILURE)||state.equals(SalarySheetState.PENDING_LEVEL_2)){
            salaryService.approval(salarySheetId,state);
            return Response.buildSuccess();
        }else {
            return Response.buildFailed("000000", "操作失败");
        }
    }

    @GetMapping("/salary-second-approval")
    public Response secondApproval(@RequestParam("salarySheetId") String salarySheetId,@RequestParam("state") SalarySheetState state){
        if(state.equals(SalarySheetState.FAILURE)||state.equals(SalarySheetState.SUCCESS)){
            salaryService.approval(salarySheetId,state);
            return Response.buildSuccess();
        }else {
            return Response.buildFailed("000000", "操作失败");
        }
    }

}
