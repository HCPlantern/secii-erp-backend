package com.nju.edu.erp.service;

import com.nju.edu.erp.dao.CollectionDao;
import com.nju.edu.erp.enums.sheetState.CollectionSheetState;
import com.nju.edu.erp.model.po.CollectionSheetPO;
import com.nju.edu.erp.model.po.TransferListSheetPO;
import com.nju.edu.erp.model.vo.CollectionSheetVO;
import com.nju.edu.erp.model.vo.TransferListSheetVO;
import com.nju.edu.erp.service.Impl.CollectionServiceImpl;
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
public class MockCollectionServiceTest {
    // 这个调用真实的方法
    @InjectMocks
    private CollectionServiceImpl collectionService;
    // 这个表示mock的方法
    @Mock
    private CollectionDao collectionDao;

    @Test
    @Transactional
    @Rollback
    public void testFindAllCollectionSheetByState(){
        CollectionSheetPO mockCollectionSheetPO= CollectionSheetPO.builder()
                .id("SKD-20220612-00004")
                .customer(1)
                .operator("djhf")
                .state(CollectionSheetState.PENDING)
                .createTime(new Date())
                .build();
        List<CollectionSheetPO> res=new ArrayList<>();
        res.add(mockCollectionSheetPO);
        TransferListSheetPO transferListSheetPO= TransferListSheetPO.builder()
                .id(2)
                .companyAccountId(2)
                .transferAmount(BigDecimal.valueOf(37154))
                .collectionSheetId("SKD-20220612-00004")
                .remark("djhfsf")
                .build();
        List<TransferListSheetPO> transferListSheetPOS=new ArrayList<>();
        transferListSheetPOS.add(transferListSheetPO);
        Mockito.when(collectionDao.findAllCollectionSheetContent("SKD-20220612-00004")).thenReturn(transferListSheetPOS);
        Mockito.when(collectionDao.findAllCollectionSheetByState(CollectionSheetState.PENDING)).thenReturn(res);
        List<TransferListSheetVO> transferListSheetVOS=new ArrayList<>();
        TransferListSheetVO transferListSheetVO=TransferListSheetVO.builder()
                .id(2)
                .companyAccountId(2)
                .transferAmount(BigDecimal.valueOf(37154))
                .collectionSheetId("SKD-20220612-00004")
                .remark("djhfsf")
                .build();
        transferListSheetVOS.add(transferListSheetVO);
        CollectionSheetVO mockCollectionSheetVO= CollectionSheetVO.builder().id("SKD-20220612-00004")
                .customer(1)
                .operator("djhf")
                .state(CollectionSheetState.PENDING)
                .content(transferListSheetVOS)
                .createTime(new Date())
                .build();
        List<CollectionSheetVO> res2=new ArrayList<>();
        res2.add(mockCollectionSheetVO);
        Assert.assertEquals(res2.toString(),collectionService.findAllCollectionSheetByState(CollectionSheetState.PENDING).toString());
    }
}
