package com.nju.edu.erp.controller;

import com.nju.edu.erp.service.Impl.SalaryService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
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

}
