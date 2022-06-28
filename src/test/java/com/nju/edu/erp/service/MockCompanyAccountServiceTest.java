package com.nju.edu.erp.service;

import com.nju.edu.erp.dao.CompanyAccountDao;
import com.nju.edu.erp.model.po.CompanyAccountPO;
import com.nju.edu.erp.model.vo.CompanyAccountVO;
import com.nju.edu.erp.service.Impl.CompanyAccountServiceImpl;
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
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class MockCompanyAccountServiceTest {
    @InjectMocks
    private CompanyAccountServiceImpl companyAccountService;
    // 这个表示mock的方法
    @Mock
    private CompanyAccountDao companyAccountDao;

    @Test
    @Transactional
    @Rollback
    public void testCreateAccount(){
        Mockito.doNothing().when(companyAccountDao).createAccount(Mockito.any(CompanyAccountPO.class));
        CompanyAccountVO companyAccountVO= CompanyAccountVO.builder()
                .id(100)
                .name("djfdf")
                .amount(BigDecimal.valueOf(2875462))
                .build();
        companyAccountService.createAccount(companyAccountVO);
        // 测试方法是否被调用
        Mockito.verify(companyAccountDao,Mockito.times(1)).createAccount(Mockito.any(CompanyAccountPO.class));
    }

    @Test
    @Transactional
    @Rollback
    public void testFindCompanyAccountByName(){
        List<CompanyAccountPO> companyAccountPOList=new ArrayList<>();
        CompanyAccountPO companyAccountPO1= CompanyAccountPO.builder()
                .id(129)
                .name("dfg")
                .amount(BigDecimal.valueOf(38746))
                .build();
        companyAccountPOList.add(companyAccountPO1);
        Mockito.when(companyAccountDao.findCompanyAccountByName(Mockito.any(String.class))).thenReturn(companyAccountPOList);
        List<CompanyAccountVO> companyAccountVOList=new ArrayList<>();
        CompanyAccountVO companyAccountVO1= CompanyAccountVO.builder()
                .id(129)
                .name("dfg")
                .amount(BigDecimal.valueOf(38746))
                .build();
        companyAccountVOList.add(companyAccountVO1);
        Assert.assertEquals(companyAccountVOList,companyAccountService.findCompanyAccountByName("djfdf"));
    }
    @Test
    @Transactional
    @Rollback
    public void testDeleteCompanyAccountById(){
        Mockito.doNothing().when(companyAccountDao).deleteCompanyAccountById(Mockito.any(Integer.class));
        companyAccountService.deleteCompanyAccountById(2);
        Mockito.verify(companyAccountDao,Mockito.times(1)).deleteCompanyAccountById(2);
    }
    @Test
    @Transactional
    @Rollback
    public void testUpdateCompanyAccount(){
        Mockito.doNothing().when(companyAccountDao).updateCompanyAccount(Mockito.any(CompanyAccountPO.class));
        CompanyAccountVO companyAccountVO= CompanyAccountVO.builder()
                .id(2)
                .name("djfdf")
                .amount(BigDecimal.valueOf(2875462))
                .build();
        companyAccountService.updateCompanyAccount(companyAccountVO);
        Mockito.verify(companyAccountDao,Mockito.times(1)).updateCompanyAccount(Mockito.any(CompanyAccountPO.class));
    }

}
