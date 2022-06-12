package com.nju.edu.erp.controller;

import com.nju.edu.erp.auth.Authorized;
import com.nju.edu.erp.common.Response;
import com.nju.edu.erp.enums.Role;
import com.nju.edu.erp.model.vo.CompanyAccountVO;
import com.nju.edu.erp.service.CompanyAccountService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping(path = "/accountManage")
@Api(tags = "CompanyAccountController")
public class CompanyAccountController {
    private final CompanyAccountService companyAccountService;
    @Autowired
    public CompanyAccountController(CompanyAccountService companyAccountService) {
        this.companyAccountService = companyAccountService;
    }
    @PostMapping("/createCompanyAccount")
    @ApiOperation("新建公司银行账户(只允许admin)")
    @Authorized(roles = {Role.ADMIN})
    public Response createAccount(@RequestBody CompanyAccountVO companyAccountVO){
        companyAccountService.createAccount(companyAccountVO);
        return Response.buildSuccess();
    }

    @GetMapping("/findCompanyAccountByName")
    @ApiOperation("根据账户名称查询所有公司银行账户(只允许admin)")
    @Authorized(roles = {Role.ADMIN})
    public Response findCompanyAccountByName(@RequestParam(required = false) String name){
        return Response.buildSuccess(companyAccountService.findCompanyAccountByName(name));
    }

    @GetMapping("/deleteCompanyAccountById")
    @ApiOperation("删除指定id的账户(只允许admin)")
    @Authorized(roles = {Role.ADMIN})
    public Response deleteCompanyAccountById(@RequestParam Integer id){
        companyAccountService.deleteCompanyAccountById(id);
        return Response.buildSuccess();
    }
    @PostMapping("/updateCompanyAccount")
    @ApiOperation("修改账户(只可以修改账户名称)")
    @Authorized(roles = {Role.ADMIN})
    public Response updateCompanyAccount(@RequestBody CompanyAccountVO companyAccountVO){
        companyAccountService.updateCompanyAccount(companyAccountVO);
        return Response.buildSuccess();
    }
}
