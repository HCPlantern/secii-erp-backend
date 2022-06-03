package com.nju.edu.erp.controller;


import com.nju.edu.erp.auth.Authorized;
import com.nju.edu.erp.enums.Role;
import com.nju.edu.erp.model.vo.CreateProductVO;
import com.nju.edu.erp.model.vo.ProductInfoVO;
import com.nju.edu.erp.service.ProductService;
import com.nju.edu.erp.common.Response;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/product")
@Api(tags = "ProductController",description = "产品")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @ApiOperation("创建商品")
    @PostMapping("/create")
    @Authorized(roles = {Role.ADMIN, Role.GM, Role.INVENTORY_MANAGER})
    public Response createProduct(@RequestBody CreateProductVO createProductVO) {
        return Response.buildSuccess(productService.createProduct(createProductVO));
    }

    @PostMapping("/update")
    @Authorized(roles = {Role.ADMIN, Role.GM, Role.INVENTORY_MANAGER})
    public Response updateProduct(@RequestBody ProductInfoVO productInfoVO) {
        return Response.buildSuccess(productService.updateProduct(productInfoVO));
    }

    @GetMapping("/queryAll")
    @Authorized(roles = {Role.ADMIN, Role.GM, Role.INVENTORY_MANAGER, Role.SALE_STAFF, Role.SALE_MANAGER})
    public Response findAllProduct() {
        return Response.buildSuccess(productService.queryAllProduct());
    }

    @GetMapping("/delete")
    @Authorized(roles = {Role.ADMIN, Role.GM, Role.INVENTORY_MANAGER})
    public Response deleteProduct(@RequestParam String id) {
        productService.deleteById(id);
        return Response.buildSuccess();
    }

}
