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

    @ApiOperation("根据客户类型查询客户")
    @GetMapping("/findByType")
    public Response findByType(@RequestParam(required = false) CustomerType type) {
        return Response.buildSuccess(customerService.getCustomersByType(type));
    }

   @ApiOperation("新增客户")
   @PostMapping("/createCustomer")
   public Response createCustomer(@RequestBody CustomerVO customerVO) {
       customerService.createCustomer(customerVO);
      return Response.buildSuccess();
   }

  @ApiOperation("修改客户的信息")
  @PostMapping("/updateCustomer")
  public Response updateCustomer(@RequestBody CustomerVO customerVO) {
    customerService.updateCustomer(customerVO);
    return Response.buildSuccess();
  }

  @ApiOperation("删除客户")
  @GetMapping("/deleteCustomer")
  public Response deleteCustomer(@RequestParam(value = "id") int id){
        customerService.deleteCustomer(id);
        return Response.buildSuccess();
  }
}
