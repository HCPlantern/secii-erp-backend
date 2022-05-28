package com.nju.edu.erp.controller;

import com.nju.edu.erp.auth.Authorized;
import com.nju.edu.erp.enums.Role;
import com.nju.edu.erp.service.CategoryService;
import com.nju.edu.erp.common.Response;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path = "/category")
@Api(tags = "CategoryController")
public class CategoryController {

    private CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }


    @ApiOperation("创建商品分类")
    @GetMapping("/create")
    @Authorized(roles = {Role.ADMIN, Role.GM, Role.INVENTORY_MANAGER})
    @ResponseBody
    public Response createCategory(@RequestParam(value = "parentId") int parentId,
                               @RequestParam(value = "name") String name) {
        return Response.buildSuccess(categoryService.createCategory(parentId, name));
    }

    @ApiOperation("查询商品分类")
    @GetMapping("/queryAll")
    @Authorized(roles = {Role.ADMIN, Role.GM, Role.INVENTORY_MANAGER})
    @ResponseBody
    public Response queryAllCategory() {
        return Response.buildSuccess(categoryService.queryAllCategory());
    }

    @GetMapping("/update")
    @Authorized(roles = {Role.ADMIN, Role.GM, Role.INVENTORY_MANAGER})
    public Response updateCategory(@RequestParam(value = "id") int id,
                                   @RequestParam(value = "name") String name) {
        return Response.buildSuccess(categoryService.updateCategory(id, name));
    }

    @GetMapping("/delete")
    @Authorized(roles = {Role.ADMIN, Role.GM, Role.INVENTORY_MANAGER})
    public Response deleteCategory(@RequestParam(value = "id") int id) {
        categoryService.deleteCategory(id);
        return Response.buildSuccess();
    }
}
