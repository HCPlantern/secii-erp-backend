package com.nju.edu.erp.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nju.edu.erp.enums.sheetState.WarehouseInputSheetState;
import com.nju.edu.erp.enums.sheetState.WarehouseOutputSheetState;
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
public class MockWareHouseControllerTest {

    private static final String APPROVE_INPUT_API="/warehouse/inputSheet/approve";
    private static final String FIND_INPUT_STATE_API="/warehouse/inputSheet/state";
    private static final String FIND_OUTPUT_STATE_API="/warehouse/outputSheet/state";
    private static final String APPROVE_OUTPUT_API="/warehouse/outputSheet/approve";
    @Resource
    private MockMvc mockMvc;

    // 使用jackson
    @Resource
    private ObjectMapper objectMapper;

    /**
     * 测试审批入库单
     */
    @Test
    @Transactional
    @Rollback
    public void testWarehouseInputSheetApprove() throws Exception {
        String sheetId="RKD-20220706-00000";
        WarehouseInputSheetState warehouseInputSheetState=WarehouseInputSheetState.SUCCESS;
        mockMvc.perform(MockMvcRequestBuilders.get(APPROVE_INPUT_API).param("sheetId",sheetId).param("state",String.valueOf(warehouseInputSheetState))).andDo(MockMvcResultHandlers.print());
    }

    @Test
    @Transactional
    @Rollback
    public void testFindWarehouseInputSheetByState() throws Exception {
        WarehouseInputSheetState warehouseInputSheetState=WarehouseInputSheetState.PENDING;
        mockMvc.perform(MockMvcRequestBuilders.get(FIND_INPUT_STATE_API).param("sheetId",String.valueOf(warehouseInputSheetState))).andDo(MockMvcResultHandlers.print());
    }

    @Test
    @Transactional
    @Rollback
    public void testFindWarehouseOutputSheetByState() throws Exception {
        WarehouseOutputSheetState warehouseOutputSheetState = WarehouseOutputSheetState.PENDING;
        mockMvc.perform(MockMvcRequestBuilders.get(FIND_OUTPUT_STATE_API).param("sheetId", String.valueOf(warehouseOutputSheetState))).andDo(MockMvcResultHandlers.print());
    }
    @Test
    @Transactional
    @Rollback
    public void testWarehouseOutputSheetApprove() throws Exception {
        String sheetId="djkhshvsmfd";
        WarehouseOutputSheetState warehouseOutputSheetState = WarehouseOutputSheetState.SUCCESS;
        mockMvc.perform(MockMvcRequestBuilders.get(APPROVE_OUTPUT_API).param("sheetId", String.valueOf(warehouseOutputSheetState))).andDo(MockMvcResultHandlers.print());
    }

}
