package com.nju.edu.erp.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nju.edu.erp.dao.CompanyAccountDao;
import com.nju.edu.erp.model.vo.CompanyAccountVO;
import com.nju.edu.erp.service.CompanyAccountService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.HandlerResultMatchers;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class MockCompanyAccountControllerTest {
    @Resource
    private MockMvc mockMvc;

    // 使用jackson
    @Resource
    private ObjectMapper objectMapper;

    @MockBean
    private CompanyAccountService companyAccountService;

    @Test
    @Transactional
    @Rollback
    public void testCreateCompanyAccount() throws Exception {
        // mock
        CompanyAccountVO mockCompanyAmountVO= CompanyAccountVO.builder()
                .id(12)
                .name("dsugvj")
                .amount(BigDecimal.valueOf(34324))
                .build();
        String mockCompanyAmountVOJSON=objectMapper.writeValueAsString(mockCompanyAmountVO);
        mockMvc.perform(MockMvcRequestBuilders.post("/accountManage/createCompanyAccount").contentType(MediaType.APPLICATION_JSON).content(mockCompanyAmountVOJSON).accept(MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    @Transactional
    @Rollback
    public void testFindCompanyAccountByName() throws Exception {
        String accountName="wash";
        // 先查询所有 再查询name
        mockMvc.perform(MockMvcRequestBuilders.get("/accountManage/findCompanyAccountByName")).andDo(MockMvcResultHandlers.print());
        mockMvc.perform(MockMvcRequestBuilders.get("/accountManage/findCompanyAccountByName").param("name",accountName))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    @Transactional
    @Rollback
    public void testDeleteCompanyAccountById() throws Exception {
        // /accountManage/deleteCompanyAccountById
        // /accountManage/updateCompanyAccount
        Integer id=1;
        mockMvc.perform(MockMvcRequestBuilders.get("/accountManage/deleteCompanyAccountById").param("id",String.valueOf(id)))
                .andDo(MockMvcResultHandlers.print());
    }
    @Test
    @Transactional
    @Rollback
    public void testUpdateCompanyAccount() throws Exception {
        // /accountManage/deleteCompanyAccountById
        // /accountManage/updateCompanyAccount

        // mock
        CompanyAccountVO mockCompanyAmountVO= CompanyAccountVO.builder()
                .id(1)
                .name("dslkvjf")
                .amount(BigDecimal.valueOf(342324))
                .build();
        String mockCompanyAmountVOJSON=objectMapper.writeValueAsString(mockCompanyAmountVO);
        // 更新
        mockMvc.perform(MockMvcRequestBuilders.post("/accountManage/updateCompanyAccount").contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).content(mockCompanyAmountVOJSON))
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
                .andDo(MockMvcResultHandlers.print());
    }
}
