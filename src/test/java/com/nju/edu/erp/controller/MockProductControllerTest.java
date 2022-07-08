package com.nju.edu.erp.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nju.edu.erp.model.vo.ProductInfoVO;
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
public class MockProductControllerTest {
    private static final String FIND_API="/product/queryAll";
    private static final String CREATE_API="/product/create";
    private static final String UPDATE_API="/product/update";
    private static final String DELETE_API="/product/delete";
    @Resource
    private MockMvc mockMvc;

    // 使用jackson
    @Resource
    private ObjectMapper objectMapper;

    /**
     * 测试查询所有商品
     * @throws Exception 异常
     */
    @Test
    @Transactional
    @Rollback
    public void testFindAllProduct() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(FIND_API)).andDo(MockMvcResultHandlers.print());
    }

    /**
     * 测试创建商品
     * @throws Exception
     */
    @Test
    @Transactional
    @Rollback
    public void testCreateProduct() throws Exception {
        ProductInfoVO productVO= ProductInfoVO.builder()
                .id("32908")
                .name("sfkvjhskfv")
                .categoryId(2)
                .type("dkfhdv")
                .quantity(13)
                .purchasePrice(BigDecimal.valueOf(31946))
                .retailPrice(BigDecimal.valueOf(34878923))
                .recentPp(BigDecimal.valueOf(34234))
                .recentRp(BigDecimal.valueOf(3423))
                .build();
        String mockProductVOJSON=objectMapper.writeValueAsString(productVO);
        mockMvc.perform(MockMvcRequestBuilders.post(CREATE_API).contentType(MediaType.APPLICATION_JSON).content(mockProductVOJSON).accept(MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().is2xxSuccessful()).andDo(MockMvcResultHandlers.print());
    }

    /**
     * 测试创建商品
     * @throws Exception
     */
    @Test
    @Transactional
    @Rollback
    public void testUpdateProduct() throws Exception {
        ProductInfoVO productVO= ProductInfoVO.builder()
                .id("32908")
                .name("sfkvjhskfv")
                .categoryId(2)
                .type("dkfhdv")
                .quantity(13)
                .purchasePrice(BigDecimal.valueOf(31946))
                .retailPrice(BigDecimal.valueOf(34878923))
                .recentPp(BigDecimal.valueOf(34234))
                .recentRp(BigDecimal.valueOf(3423))
                .build();
        String mockProductVOJSON=objectMapper.writeValueAsString(productVO);
        mockMvc.perform(MockMvcRequestBuilders.post(UPDATE_API).contentType(MediaType.APPLICATION_JSON).content(mockProductVOJSON).accept(MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().is2xxSuccessful()).andDo(MockMvcResultHandlers.print());

    }

    /**
     * 测试删除商品
     * @throws Exception 异常
     */
    @Test
    @Transactional
    @Rollback
    public void testDeleteProduct() throws Exception {
        String productId="3489238";
        mockMvc.perform(MockMvcRequestBuilders.post(DELETE_API).param("id",productId)).andDo(MockMvcResultHandlers.print());
    }
}
