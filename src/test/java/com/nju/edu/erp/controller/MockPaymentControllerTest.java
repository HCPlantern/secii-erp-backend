package com.nju.edu.erp.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nju.edu.erp.enums.sheetState.PaymentSheetState;
import com.nju.edu.erp.model.vo.payment.PaymentSheetContentVO;
import com.nju.edu.erp.model.vo.payment.PaymentSheetVO;
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
public class MockPaymentControllerTest {
    private static final String APPROVE_API="/payment/approve-payment-sheet";
    private static final String CREATE_API="/payment/payment-sheet-make";
    private static final String FIND_API="/payment/sheet-show";
    @Resource
    private MockMvc mockMvc;

    // 使用jackson
    @Resource
    private ObjectMapper objectMapper;

    @Test
    @Transactional
    @Rollback
    public void testMakePaymentSheet() throws Exception {
        List<PaymentSheetContentVO> mockPaymentSheetContentList=new ArrayList<>();
        PaymentSheetContentVO mockPaymentSheetContent= PaymentSheetContentVO.builder()
                .id(29)
                .companyAccountId(1)
                .transferAmount(BigDecimal.valueOf(31423))
                .paymentSheetId("XJFYD-20220627-00001")
                .remark("dkjfhsnkbnv")
                .build();
        mockPaymentSheetContentList.add(mockPaymentSheetContent);
        PaymentSheetVO mockPaymentSheetVO= PaymentSheetVO.builder()
                .id("XJFYD-20220627-00001")
                .customer(3)
                .operator("sfjhg")
                .paymentSheetContentVOS(mockPaymentSheetContentList)
                .state(PaymentSheetState.PENDING)
                .createTime(new Date())
                .build();
        String mockPaymentSheetVOJSON= objectMapper.writeValueAsString(mockPaymentSheetVO);
        mockMvc.perform(MockMvcRequestBuilders.post(CREATE_API).contentType(MediaType.APPLICATION_JSON).content(mockPaymentSheetVOJSON).accept(MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().is2xxSuccessful()).andDo(MockMvcResultHandlers.print());
    }


    @Test
    @Transactional
    @Rollback
    public void testApprovePaymentSheet() throws Exception {
        String paymentSheetId="XJFYD-20220612-00001";
        PaymentSheetState state=PaymentSheetState.SUCCESS;
        mockMvc.perform(MockMvcRequestBuilders.get(APPROVE_API).param("paymentSheetId",paymentSheetId).param("state",String.valueOf(state))).andDo(MockMvcResultHandlers.print());
    }

    @Test
    @Transactional
    @Rollback
    public void testFindPaymentSheet() throws Exception {
        PaymentSheetState state=PaymentSheetState.PENDING;
        mockMvc.perform(MockMvcRequestBuilders.get(FIND_API)).andDo(MockMvcResultHandlers.print());
        mockMvc.perform(MockMvcRequestBuilders.get(FIND_API).param("state",String.valueOf(state))).andDo(MockMvcResultHandlers.print());
    }
}
