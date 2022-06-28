package com.nju.edu.erp.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nju.edu.erp.enums.sheetState.SaleSheetState;
import com.nju.edu.erp.model.vo.sale.SaleSheetContentVO;
import com.nju.edu.erp.model.vo.sale.SaleSheetVO;
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
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class MockSaleControllerTest {
    private static final String MAKE_SHEET_API="/sale/sheet-make";
    private static final String FIRST_APPROVAL_API="/sale/first-approval";
    private static final String SECOND_APPROVAL_API="/sale/second-approval";
    private static final String FIND_BY_STATE_API="/sale/sheet-show";
    private static final String FIND_BY_ID_API="/sale/find-sheet";
    private static final String FIND_MaxAmountCustomerOfSalesmanByTime="/sale/maxAmountCustomer";

    @Resource
    private MockMvc mockMvc;

    // 使用jackson
    @Resource
    private ObjectMapper objectMapper;

    @Test
    @Transactional
    @Rollback
    public void testMakeSheet() throws Exception {
        List<SaleSheetContentVO> mockSaleSheetContentVOList=new ArrayList<>();
        SaleSheetContentVO mockSaleSheetContentVO= SaleSheetContentVO.builder()
                .id(10)
                .saleSheetId("dkjkhfsdf")
                .pid("dkjfgdskf")
                .quantity(131)
                .unitPrice(BigDecimal.valueOf(324))
                .remark("kdfvhsf")
                .build();
        mockSaleSheetContentVOList.add(mockSaleSheetContentVO);
        SaleSheetVO mockSaleSheetVO= SaleSheetVO.builder()
                .id("dkjfgsf")
                .supplier(2)
                .salesman("dkfdfnd")
                .operator("kdvcjbdv")
                .remark("djbfdfbdv")
                .voucherAmount(BigDecimal.valueOf(200))
                .saleSheetContent(mockSaleSheetContentVOList)
                .build();
        String mockSaleSheetVOJSON= objectMapper.writeValueAsString(mockSaleSheetVO);
        mockMvc.perform(MockMvcRequestBuilders.post(MAKE_SHEET_API).contentType(MediaType.APPLICATION_JSON).content(mockSaleSheetVOJSON).accept(MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().is2xxSuccessful()).andDo(MockMvcResultHandlers.print());
    }

    @Test
    @Transactional
    @Rollback
    public void testFirstApproval() throws Exception {
        String saleSheetId="djvfmvbf";
        SaleSheetState state=SaleSheetState.FAILURE;
        mockMvc.perform(MockMvcRequestBuilders.get(FIRST_APPROVAL_API).param("saleSheetId",saleSheetId).param("state",String.valueOf(state))).andDo(MockMvcResultHandlers.print());
    }

    @Test
    @Transactional
    @Rollback
    public void testSecondApproval() throws Exception {
        String saleSheetId="djvfmvbf";
        SaleSheetState state=SaleSheetState.SUCCESS;
        mockMvc.perform(MockMvcRequestBuilders.get(SECOND_APPROVAL_API).param("saleSheetId",saleSheetId).param("state",String.valueOf(state))).andDo(MockMvcResultHandlers.print());
    }

    @Test
    @Transactional
    @Rollback
    public void testFindByState() throws Exception {
        SaleSheetState state=SaleSheetState.PENDING_LEVEL_1;
        mockMvc.perform(MockMvcRequestBuilders.get(FIND_BY_STATE_API).param("state",String.valueOf(state))).andDo(MockMvcResultHandlers.print());
    }

    @Test
    @Transactional
    @Rollback
    public void testFindById() throws Exception {
        String id="dkhfbdv";
        mockMvc.perform(MockMvcRequestBuilders.get(FIND_BY_ID_API).param("id",id)).andDo(MockMvcResultHandlers.print());
    }

    @Test
    @Transactional
    @Rollback
    public void testFindMaxAmountCustomerOfSalesmanByTime() throws Exception {
        String salesMan="djfbjdf";
        String beginDate="dkfbdf";
        String endDate="dkfhdf";
        mockMvc.perform(MockMvcRequestBuilders.get(FIND_MaxAmountCustomerOfSalesmanByTime).param("salesman",salesMan).param("beginDateStr",beginDate).param("endDateStr",endDate)).andDo(MockMvcResultHandlers.print());
    }
}
