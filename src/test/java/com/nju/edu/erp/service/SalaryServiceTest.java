package com.nju.edu.erp.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Component;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Component
class SalaryServiceTest {
    private SalaryService salaryService;

    @Autowired
    void setSalaryService(SalaryService salaryService) {
        this.salaryService = salaryService;
    }

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
//    @Transactional
    void generateSalarySheet() {
        salaryService.generateSalarySheet("2022-12-01 00:00:00",
                "2022-12-31 23:59:59");
    }
}