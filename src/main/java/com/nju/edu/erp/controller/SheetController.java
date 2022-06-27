package com.nju.edu.erp.controller;

import com.nju.edu.erp.auth.Authorized;
import com.nju.edu.erp.common.Response;
import com.nju.edu.erp.enums.Role;
import com.nju.edu.erp.service.SheetService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/sheet")
@Api(tags = "SheetController")
public class SheetController {

    private final SheetService sheetService;

    @Autowired
    public SheetController(SheetService sheetService) {
        this.sheetService = sheetService;
    }

    @Authorized(roles = {Role.ADMIN, Role.GM, Role.FINANCIAL_STAFF})
    @GetMapping(value = "/findAllSheet")
    @ApiOperation("查询所有单据")
    public Response findAllSheet(@RequestParam String beginDateStr, @RequestParam String endDateStr) {
        return Response.buildSuccess(sheetService.findAllSheet(beginDateStr, endDateStr));
    }
}
