package com.nju.edu.erp.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nju.edu.erp.enums.Role;
import com.nju.edu.erp.enums.SalaryCalculationMethod;
import com.nju.edu.erp.enums.SalaryPaymentMethod;
import com.nju.edu.erp.enums.sheetState.SalarySheetState;
import com.nju.edu.erp.model.vo.humanResource.JobVO;
import com.nju.edu.erp.model.vo.humanResource.SalarySheetVO;
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
import java.math.BigDecimal;
import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class MockSalaryControllerTest {

    private static final String FIND_API="/salary/queryAllSalaryRules";
    private static final String GENERATE_API="/salary/generateSalarySheet";

    private static final String UPDATE_API="/salary/updateDepartmentSalaryRule";


    @Resource
    private MockMvc mockMvc;

    // 使用jackson
    @Resource
    private ObjectMapper objectMapper;


    /**
     * 测试查询薪资规则
     */
    @Test
    @Transactional
    @Rollback
    public void testQueryAllSalaryRules() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(FIND_API))
                .andDo(MockMvcResultHandlers.print());
    }

    /**
     * 测试更新薪资规则
     */
    @Test
    @Transactional
    @Rollback
    public void testUpdateDepartmentSalaryRule() throws Exception {
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
        String mockJobVOJSON=objectMapper.writeValueAsString(jobVO);
        mockMvc.perform(MockMvcRequestBuilders.post(UPDATE_API).contentType(MediaType.APPLICATION_JSON).content(mockJobVOJSON).accept(MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().is2xxSuccessful()).andDo(MockMvcResultHandlers.print());
    }















}
