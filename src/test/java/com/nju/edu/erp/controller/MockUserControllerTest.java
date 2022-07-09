package com.nju.edu.erp.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nju.edu.erp.enums.Role;
import com.nju.edu.erp.model.vo.UserVO;
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

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class MockUserControllerTest {
    private static final String LOGIN_API="/user/login";
    private static final String REGISTER_API="/user/register";
    private static final String AUTH_API="/user/auth";
    private static final String FIND_ALL_API="/user/findAllSalesMan";

    @Resource
    private MockMvc mockMvc;

    // 使用jackson
    @Resource
    private ObjectMapper objectMapper;

    @Test
    @Transactional
    @Rollback
    public void testLogin() throws Exception {
        UserVO mockUserVO= UserVO.builder()
                .name("hcx")
                .role(Role.FINANCIAL_STAFF)
                .password("123456")
                .build();
        String mockUserVOJSON=objectMapper.writeValueAsString(mockUserVO);
        mockMvc.perform(MockMvcRequestBuilders.post(LOGIN_API).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).content(mockUserVOJSON)).andExpect(MockMvcResultMatchers.status().is2xxSuccessful()).andDo(MockMvcResultHandlers.print());

    }

    @Test
    @Transactional
    @Rollback
    public void testRegister() throws Exception {
        UserVO mockUserVO= UserVO.builder()
                .name("sdjfhg")
                .role(Role.INVENTORY_MANAGER)
                .password("djhfgdsbvks")
                .build();
        String mockUserVOJSON=objectMapper.writeValueAsString(mockUserVO);
        mockMvc.perform(MockMvcRequestBuilders.post(REGISTER_API).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).content(mockUserVOJSON)).andExpect(MockMvcResultMatchers.status().is2xxSuccessful()).andDo(MockMvcResultHandlers.print());
    }

    @Test
    @Transactional
    @Rollback
    public void testFindAllSalesMan() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(FIND_ALL_API)).andDo(MockMvcResultHandlers.print());
    }

}
