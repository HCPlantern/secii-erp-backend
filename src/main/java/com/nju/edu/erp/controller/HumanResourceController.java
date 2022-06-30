package com.nju.edu.erp.controller;

import com.nju.edu.erp.service.HumanResourceService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(path = "/humanResource")
@Api(tags = "HumanResourceController")
public class HumanResourceController {
    private final HumanResourceService humanResourceService;

    @Autowired
    public HumanResourceController(HumanResourceService humanResourceService) {
        this.humanResourceService = humanResourceService;
    }

}
