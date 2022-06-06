package com.nju.edu.erp.controller;

import com.nju.edu.erp.enums.CustomerType;
import com.nju.edu.erp.model.po.CustomerPO;
import com.nju.edu.erp.model.vo.CustomerVO;
import com.nju.edu.erp.service.CustomerService;
import com.nju.edu.erp.common.Response;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(path = "/customer")
@Api(tags = "CustomerController")
public class CustomerController {
    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @ApiOperation("查询顾客")
    @GetMapping("/findByType")
    public Response findByType(@RequestParam CustomerType type) {
        return Response.buildSuccess(customerService.getCustomersByType(type));
    }

   @ApiOperation("新增客户")
   @PostMapping("/createCustomer")
   public Response createCustomer(@RequestParam CustomerVO customerVO) {
      customerService.createCustomer(customerVO);
      return Response.buildSuccess();
   }

  @ApiOperation("修改客户的信息")
  @PostMapping("/updateCustomer")
  public Response updateCustomer(@RequestParam CustomerPO customerPO) {
    customerService.updateCustomer(customerPO);
    return Response.buildSuccess();
  }
}
