package com.nju.edu.erp.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nju.edu.erp.enums.sheetState.CollectionSheetState;
import com.nju.edu.erp.model.vo.CollectionSheetVO;
import com.nju.edu.erp.model.vo.TransferListSheetVO;
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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class MockCollectionControllerTest {
    @Resource
    private MockMvc mockMvc;

    //使用jackson
    @Resource
    private ObjectMapper objectMapper;

    /**
     * 测试创建收款单
     * @throws Exception 异常
     */
    @Test
    @Transactional
    @Rollback
    public void testCreateCollectionSheet() throws Exception {
        // mock
        List<TransferListSheetVO> mockCollectionContentList=new ArrayList<>();
        TransferListSheetVO mockCollectionContent1= TransferListSheetVO.builder()
                .id(20)
                .companyAccountId(1)
                .transferAmount(BigDecimal.valueOf(2388))
                .collectionSheetId("SKD-20220627-00010")
                .remark("tets1")
                .build();
        mockCollectionContentList.add(mockCollectionContent1);
        CollectionSheetVO mockCollectionSheetVO= CollectionSheetVO.builder().id("SKD-20220627-00010")
                .customer(1)
                .operator("djhf")
                .content(mockCollectionContentList)
                .state(CollectionSheetState.PENDING)
                .createTime(new Date())
                .build();
        // 转成json
        String mockCollectionSheetJSON= objectMapper.writeValueAsString(mockCollectionSheetVO);
        System.out.println(mockCollectionSheetJSON);
        mockMvc.perform(MockMvcRequestBuilders.post("/collection/collection-sheet-make")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mockCollectionSheetJSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
                .andDo(MockMvcResultHandlers.print());
    }

    /**
     * 测试审批收款单
     */
    @Test
    @Transactional
    @Rollback
    public void testApproveCollectionSheet() throws Exception {
        String collectionSheetId="SKD-20220627-00001";
        CollectionSheetState state=CollectionSheetState.SUCCESS;
        mockMvc.perform(MockMvcRequestBuilders.get("/collection/approve-collection-sheet")
                .param("collectionSheetId",collectionSheetId)
                .param("state", String.valueOf(state)))
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
                .andExpect(MockMvcResultMatchers.jsonPath("$.result").value("操作成功"))
                .andDo(MockMvcResultHandlers.print());
    }

    /**
     * 测试查询所有的收款单
     */
    @Test
    @Transactional
    @Rollback
    public void testFindAllCollectionSheet() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/collection/sheet-show"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.result.length()").value(15))
                .andDo(MockMvcResultHandlers.print());
    }

    /**
     * 测试根据状态查询收款单
     * @throws Exception 异常
     */
    @Test
    @Transactional
    @Rollback
    public void testFindAllCollectionSheetByState() throws Exception {
        CollectionSheetState state=CollectionSheetState.PENDING;
        mockMvc.perform(MockMvcRequestBuilders.get("/collection/sheet-show")
                .param("state",String.valueOf(state)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.result.length()").value(3))
                .andDo(MockMvcResultHandlers.print());
    }
}
