package com.nju.edu.erp.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * 测试API
 * 集成测试
 * 可以配合打桩
 * Mockito
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class MockCategoryControllerTest {
    @Resource
    private MockMvc mockMvc;

    /**
     * 测试查询所有的商品分类信息
     */
    @Test
    @Transactional
    @Rollback
    public void testQueryAll() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/category/queryAll"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.result.length()").value(5))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andDo(MockMvcResultHandlers.print());
    }
    /**
     * 根据商品id修改商品分类
     */
    @Test
    @Transactional
    @Rollback
    public void testUpdate() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/category/update").param("id","1").param("name","dyfgs"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.result.name").value("dyfgs"))
                .andDo(MockMvcResultHandlers.print());
    }

    /**
     * 根据商品分类id删除商品
     */
    @Test
    @Transactional
    @Rollback
    public void testDelete() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/category/delete")
                        .param("id","6"))
                .andDo(MockMvcResultHandlers.print());
    }

    /**
     * 创建商品分类信息
     */
    @Test
    @Transactional
    @Rollback
    public void testCreate() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/category/create").param("parentId","1").param("name","dfyu")).andDo(MockMvcResultHandlers.print());
    }

}
