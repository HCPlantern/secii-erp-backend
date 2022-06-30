package com.nju.edu.erp.service;

import com.nju.edu.erp.dao.CollectionDao;
import com.nju.edu.erp.dao.PaymentSheetDao;
import com.nju.edu.erp.enums.sheetState.PaymentSheetState;
import com.nju.edu.erp.model.po.PaymentSheetContentPO;
import com.nju.edu.erp.model.po.PaymentSheetPO;
import com.nju.edu.erp.model.vo.PaymentSheetContentVO;
import com.nju.edu.erp.model.vo.PaymentSheetVO;
import com.nju.edu.erp.service.Impl.CollectionServiceImpl;
import com.nju.edu.erp.service.Impl.PaymentServiceImpl;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class MockPaymentServiceTest {
    @InjectMocks
    private PaymentServiceImpl paymentService;
    // 这个表示mock的方法
    @Mock
    private PaymentSheetDao paymentSheetDao;


    @Test
    @Transactional
    @Rollback
    public void testFindAllPaymentSheetByState(){
        PaymentSheetPO paymentSheetPO= PaymentSheetPO.builder()
                .id("XJFYD-20220612-00001")
                .customer(2)
                .operator("dkjfs")
                .state(PaymentSheetState.PENDING)
                .createTime(new Date())
                .build();
        List<PaymentSheetPO> paymentSheetPOS=new ArrayList<>();
        List<PaymentSheetContentPO> paymentSheetContentPOS=new ArrayList<>();
        PaymentSheetContentPO paymentSheetContentPO= PaymentSheetContentPO.builder()
                .id(3)
                .companyAccountId(2)
                .transferAmount(BigDecimal.valueOf(298))
                .paymentSheetId("XJFYD-20220612-00001")
                .remark("dsjfjdfgb")
                .build();
        paymentSheetContentPOS.add(paymentSheetContentPO);
        paymentSheetPOS.add(paymentSheetPO);
        // 对于paymentSheetDao.findAllPaymentSheetByState方法进行打桩
        Mockito.when(paymentSheetDao.findAllPaymentSheetByState(PaymentSheetState.PENDING)).thenReturn(paymentSheetPOS);
        // 对于paymentSheetDao.findAllPaymentSheetContentById方法进行打桩
        Mockito.when(paymentSheetDao.findAllPaymentSheetContentById("XJFYD-20220612-00001")).thenReturn(paymentSheetContentPOS);
        List<PaymentSheetContentVO> paymentSheetContentVOS=new ArrayList<>();
        PaymentSheetContentVO paymentSheetContentVO1= PaymentSheetContentVO.builder()
                .id(3)
                .companyAccountId(2)
                .transferAmount(BigDecimal.valueOf(298))
                .paymentSheetId("XJFYD-20220612-00001")
                .remark("dsjfjdfgb")
                .build();
        paymentSheetContentVOS.add(paymentSheetContentVO1);
        PaymentSheetVO paymentSheetVO= PaymentSheetVO.builder()
                .id("XJFYD-20220612-00001")
                .customer(2)
                .operator("dkjfs")
                .state(PaymentSheetState.PENDING)
                .paymentSheetContentVOS(paymentSheetContentVOS)
                .createTime(new Date())
                .build();
        List<PaymentSheetVO> paymentSheetVOS=new ArrayList<>();
        paymentSheetVOS.add(paymentSheetVO);
        Assert.assertEquals(paymentSheetVOS.toString(),paymentService.findAllPaymentSheetByState(PaymentSheetState.PENDING).toString());
    }


}
