package com.nju.edu.erp.controller;


import com.nju.edu.erp.auth.Authorized;
import com.nju.edu.erp.common.Response;
import com.nju.edu.erp.enums.Role;
import com.nju.edu.erp.model.vo.*;
import com.nju.edu.erp.service.InitAccountService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping(path = "/initAccount")
@Api(tags = "InitAccountController")
public class InitAccountController {
    private final InitAccountService initAccountService;
    @Autowired
    public InitAccountController(InitAccountService initAccountService) {
        this.initAccountService = initAccountService;
    }

    @ApiOperation("期初新增客户")
    @PostMapping("/createCustomer")
    public Response createCustomer(@RequestBody InitCustomerVO initCustomerVO) {
        return Response.buildSuccess(initAccountService.createCustomer(initCustomerVO));
    }

    @ApiOperation("期初创建商品")
    @PostMapping("/createProduct")
    public Response createProduct(@RequestBody InitProductVO initProductVO) {
        initAccountService.createProduct(initProductVO);
        return Response.buildSuccess();
    }

    @PostMapping("/createCompanyAccount")
    @ApiOperation("期初新建公司银行账户")
    public Response createAccount(@RequestBody InitCompanyAccountVO initCompanyAccountVO){
        initAccountService.createAccount(initCompanyAccountVO);
        return Response.buildSuccess();
    }
    @GetMapping("/getAllInitCustomer")
    @ApiOperation("获取期初建账的客户的全部信息")
    public Response getAllInitCustomer(){
        return Response.buildSuccess(initAccountService.getAllInitCustomer());
    }

    @GetMapping("/getAllInitProduct")
    @ApiOperation("获取期初建账的商品的全部信息")
    public Response getAllInitProduct(){
        return Response.buildSuccess(initAccountService.getAllInitProduct());
    }

    @GetMapping("/getAllInitCompanyAccount")
    @ApiOperation("获取期初建账的银行账户的全部信息")
    public Response getAllInitCompanyAccount(){
        return Response.buildSuccess(initAccountService.getAllInitCompanyAccount());
    }

}
