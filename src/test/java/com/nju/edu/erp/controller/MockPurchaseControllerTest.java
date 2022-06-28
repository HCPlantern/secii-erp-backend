package com.nju.edu.erp.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class MockPurchaseControllerTest {
    private static final String MAKE_SHEET_API="/purchase/sheet-make";
    private static final String FIRST_APPROVAL_API="/purchase/first-approval";
    private static final String SECOND_APPROVAL_API="/purchase/second-approval";
    private static final String FIND_BY_STATE_API="/purchase/sheet-show";
    private static final String FIND_BY_ID_API="/purchase/find-sheet";

    @Resource
    private MockMvc mockMvc;

    // 使用jackson
    @Resource
    private ObjectMapper objectMapper;

    @Test
    @Transactional
    @Rollback
    public void testMakeSheet(){


    }

    @Test
    @Transactional
    @Rollback
    public void testFirstApproval(){


    }

    @Test
    @Transactional
    @Rollback
    public void testSecondApproval(){

    }
    @Test
    @Transactional
    @Rollback
    public void testFindByState(){


    }

    @Test
    @Transactional
    @Rollback
    public void testFindById(){

    }
}
