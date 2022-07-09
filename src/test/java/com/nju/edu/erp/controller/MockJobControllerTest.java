package com.nju.edu.erp.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nju.edu.erp.enums.Role;
import com.nju.edu.erp.enums.SalaryCalculationMethod;
import com.nju.edu.erp.enums.SalaryPaymentMethod;
import com.nju.edu.erp.model.vo.humanResource.JobVO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.awt.*;
import java.math.BigDecimal;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class MockJobControllerTest {

    @Resource
    private MockMvc mockMvc;

    // 使用jackson
    @Resource
    private ObjectMapper objectMapper;

    private static final String CREATE_API="/job/createDepartmentSalaryRule";


    @Test
    @Transactional
    @Rollback
    public void testCreateDepartmentSalaryRule() throws Exception {
        JobVO jobVO= JobVO.builder()
                .id(100)
                .name(Role.GM)
                .baseWage(BigDecimal.valueOf(3784))
                .salaryCalculationMethod(SalaryCalculationMethod.POST)
                .salaryPaymentMethod(SalaryPaymentMethod.ANNUALLY)
                .postWage(BigDecimal.valueOf(38746))
                .annualBonus(BigDecimal.valueOf(432))
                .deductRate(BigDecimal.valueOf(0.8))
                .gradeRate(BigDecimal.valueOf(0.9))
                .insurance(BigDecimal.valueOf(2445))
                .housingFund(BigDecimal.valueOf(34234))
                .build();
        String mockSalaryVOJSON=objectMapper.writeValueAsString(jobVO);
        mockMvc.perform(MockMvcRequestBuilders.post(CREATE_API).contentType(MediaType.APPLICATION_JSON).contentType(mockSalaryVOJSON).accept(MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().is2xxSuccessful()).andDo(MockMvcResultHandlers.print());
    }












}
