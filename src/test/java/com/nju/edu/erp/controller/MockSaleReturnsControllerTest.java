package com.nju.edu.erp.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nju.edu.erp.enums.sheetState.SaleReturnsSheetState;
import com.nju.edu.erp.model.vo.sale.SaleSheetContentVO;
import com.nju.edu.erp.model.vo.saleReturns.SaleReturnsSheetContentVO;
import com.nju.edu.erp.model.vo.saleReturns.SaleReturnsSheetVO;
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
public class MockSaleReturnsControllerTest {

    private static final String MAKE_SHEET_API="/sale-returns/sheet-make";
    private static final String FIRST_APPROVAL_API="/sale-returns/first-approval";
    private static final String SECOND_APPROVAL_API="/sale-returns/second-approval";
    private static final String FIND_BY_STATE_API="/sale-returns/sheet-show";

    @Resource
    private MockMvc mockMvc;

    // 使用jackson
    @Resource
    private ObjectMapper objectMapper;

    @Test
    @Transactional
    @Rollback
    public void testMakeSheet() throws Exception {
        List<SaleReturnsSheetContentVO> mockSaleReturnsSheetContentVOList=new ArrayList<>();
        SaleReturnsSheetContentVO mockSaleReturnsSheetContentVO= SaleReturnsSheetContentVO.builder()
                .id(13)
                .saleReturnsSheetId("XSTHD-20220524-00000")
                .pid("0000000000400001")
                .quantity(12)
                .unitPrice(BigDecimal.valueOf(34))
                .remark("dkjfbdf")
                .build();
        mockSaleReturnsSheetContentVOList.add(mockSaleReturnsSheetContentVO);
        SaleReturnsSheetVO mockSaleReturnsSheetVO= SaleReturnsSheetVO.builder()
                .id("XSTHD-20220524-00000")
                .saleSheetId("XSD-20220524-00000")
                .supplier(3)
                .salesman("dsjhfvsd")
                .operator("dkfjbd")
                .remark("dkcfbd")
                .discount(BigDecimal.valueOf(0.9))
                .voucherAmount(BigDecimal.valueOf(3274))
                .createTime(new Date())
                .saleReturnsSheetContent(mockSaleReturnsSheetContentVOList)
                .build();
        String mockSaleReturnsSheetVOJSON= objectMapper.writeValueAsString(mockSaleReturnsSheetVO);
        mockMvc.perform(MockMvcRequestBuilders.post(MAKE_SHEET_API).contentType(MediaType.APPLICATION_JSON).content(mockSaleReturnsSheetVOJSON).accept(MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().is2xxSuccessful()).andDo(MockMvcResultHandlers.print());
    }

    @Test
    @Transactional
    @Rollback
    public void testFirstApproval() throws Exception {
        String saleReturnSheetId="djhfbdf";
        SaleReturnsSheetState saleReturnsSheetState=SaleReturnsSheetState.PENDING_LEVEL_2;
        mockMvc.perform(MockMvcRequestBuilders.post(FIRST_APPROVAL_API).param("saleReturnsSheetId",saleReturnSheetId).param("state",String.valueOf(saleReturnsSheetState))).andDo(MockMvcResultHandlers.print());
    }

    @Test
    @Transactional
    @Rollback
    public void testSecondApproval() throws Exception {
        String saleReturnSheetId="fskgbfsjgv";
        SaleReturnsSheetState saleReturnsSheetState=SaleReturnsSheetState.SUCCESS;
        mockMvc.perform(MockMvcRequestBuilders.post(SECOND_APPROVAL_API).param("saleReturnsSheetId",saleReturnSheetId).param("state",String.valueOf(saleReturnsSheetState))).andDo(MockMvcResultHandlers.print());
    }

    @Test
    @Transactional
    @Rollback
    public void testFindByState() throws Exception {
        SaleReturnsSheetState saleReturnsSheetState=SaleReturnsSheetState.SUCCESS;
        mockMvc.perform(MockMvcRequestBuilders.get(FIND_BY_STATE_API).param("state",String.valueOf(saleReturnsSheetState))).andDo(MockMvcResultHandlers.print());
    }
}
