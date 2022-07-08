package com.nju.edu.erp.controller;

import com.nju.edu.erp.common.Response;
import com.nju.edu.erp.model.vo.humanResource.JobVO;
import com.nju.edu.erp.service.JobService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Api("制定岗位薪资规则")
@RequestMapping(path = "/job")
public class JobController {

    private final JobService jobService;

    @Autowired
    public JobController(JobService jobService) {
        this.jobService = jobService;
    }

    @PostMapping("/createDepartmentSalaryRule")
    @ApiOperation("制定部门薪资规则")
    public Response createDepartmentSalaryRule(@RequestBody JobVO jobVO){
        jobService.createDepartmentSalaryRule(jobVO);
        return Response.buildSuccess();
    }

    @GetMapping("/queryAllSalaryRules")
    @ApiOperation("查询所有薪资岗位规则")
    public Response queryAllSalaryRules(){
        return Response.buildSuccess(jobService.queryAllSalaryRules());
    }

    @PostMapping("/updateDepartmentSalaryRule")
    @ApiOperation("更新对应的部门薪资规则")
    public Response updateDepartmentSalaryRule(@RequestBody JobVO jobVO){
        jobService.updateJobById(jobVO);
        return Response.buildSuccess();
    }

}
