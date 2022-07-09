package com.nju.edu.erp.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class MockFinanceControllerTest {

    @Resource
    private MockMvc mockMvc;

    // 使用jackson
    @Resource
    private ObjectMapper objectMapper;

    private static final String GET_API="/finance/getFinancialReport";



    @Test
    @Transactional
    @Rollback
    public void testGetFinancialReport() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.get(GET_API).param("beginDateStr","2022-06-09 21:28:32").param("endDataStr","2022-07-09 21:28:32")).andDo(MockMvcResultHandlers.print());
    }
}
