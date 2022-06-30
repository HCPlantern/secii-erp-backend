package com.nju.edu.erp.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nju.edu.erp.enums.CustomerType;
import com.nju.edu.erp.model.vo.CustomerVO;
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

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class MockCustomerControllerTest {
    private static final String FIND_API="/customer/findByType";
    private static final String CREATE_API="/customer/createCustomer";
    private static final String UPDATE_API="/customer/updateCustomer";
    private static final String DELETE_API="/customer/deleteCustomer";
    @Resource
    private MockMvc mockMvc;

    // 使用jackson
    @Resource
    private ObjectMapper objectMapper;

    @Test
    @Transactional
    @Rollback
    public void testFindCustomerByType() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(FIND_API)).andDo(MockMvcResultHandlers.print());
        mockMvc.perform(MockMvcRequestBuilders.get(FIND_API).param("type","供应商")).andDo(MockMvcResultHandlers.print());
        mockMvc.perform(MockMvcRequestBuilders.get(FIND_API).param("type","销售商")).andDo(MockMvcResultHandlers.print());
    }

    @Test
    @Transactional
    @Rollback
    public void testCreateCustomer() throws Exception {
        CustomerVO customerVO= CustomerVO.builder()
                .id(100)
                .type(CustomerType.SELLER)
                .level(10)
                .name("djhfdks")
                .phone("123123123")
                .address("dkfjbdjfb")
                .zipcode("djsfgjdhfbsv")
                .email("shdgvms")
                .lineOfCredit(BigDecimal.valueOf(13423))
                .receivable(BigDecimal.valueOf(823749))
                .payable(BigDecimal.valueOf(3746))
                .operator("djfgshdf")
                .build();
        String mockCustomerVOJSON= objectMapper.writeValueAsString(customerVO);
        mockMvc.perform(MockMvcRequestBuilders.post(CREATE_API).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).content(mockCustomerVOJSON)).andExpect(MockMvcResultMatchers.status().is2xxSuccessful()).andDo(MockMvcResultHandlers.print());
    }

    @Test
    @Transactional
    @Rollback
    public void testUpdateCustomer() throws Exception {
        CustomerVO customerVO= CustomerVO.builder()
                .id(100)
                .type(CustomerType.SELLER)
                .level(10)
                .name("djdskf")
                .phone("12312131314245123")
                .address("dskdnvslkdnv")
                .zipcode("flgkfkmgf")
                .email("sfskvlbm")
                .lineOfCredit(BigDecimal.valueOf(134540))
                .receivable(BigDecimal.valueOf(8423749))
                .payable(BigDecimal.valueOf(374295))
                .operator("djfgshdf")
                .build();
        String mockCustomerVOJSON= objectMapper.writeValueAsString(customerVO);
        mockMvc.perform(MockMvcRequestBuilders.post(UPDATE_API).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).content(mockCustomerVOJSON)).andExpect(MockMvcResultMatchers.status().is2xxSuccessful()).andDo(MockMvcResultHandlers.print());
    }

    @Test
    @Transactional
    @Rollback
    public void testDeleteCustomer() throws Exception {
        Integer id=10;
        mockMvc.perform(MockMvcRequestBuilders.get(DELETE_API).param("id",String.valueOf(id))).andDo(MockMvcResultHandlers.print());
    }
}
