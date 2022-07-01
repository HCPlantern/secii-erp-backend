package com.nju.edu.erp.service;

import com.fasterxml.jackson.databind.BeanProperty;
import com.nju.edu.erp.dao.CustomerDao;
import com.nju.edu.erp.enums.CustomerType;
import com.nju.edu.erp.model.po.CustomerPO;
import com.nju.edu.erp.model.vo.CustomerVO;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class CustomerServiceTest {
    @Autowired
    CustomerService customerService;
    @Autowired
    CustomerDao customerDao;

    @Test
    @Transactional
    @Rollback
    public void testUpdateCustomer(){
        CustomerVO mockCustomerVO= CustomerVO.builder()
                .id(10)
                .type(CustomerType.SUPPLIER)
                .level(101)
                .name("hcxdkf")
                .phone("318342847")
                .address("vsfuvb3fbekdj")
                .zipcode("djfd")
                .lineOfCredit(BigDecimal.valueOf(193478))
                .receivable(BigDecimal.valueOf(3847))
                .payable(BigDecimal.valueOf(37908))
                .operator("dfjdfv")
                .build();
        Assert.assertEquals(1,customerService.updateCustomer(mockCustomerVO));
    }

    @Test
    @Transactional
    @Rollback
    public void testGetCustomersByType(){
        CustomerType type=CustomerType.SELLER;
        List<CustomerPO> res=customerService.getCustomersByType(type);
        Assert.assertEquals(4,res.size());
    }

    @Test
    @Transactional
    @Rollback
    public void testFindCustomerById(){
        Integer id=5;
        CustomerPO res=customerService.findCustomerById(id);
        Assert.assertNotNull(res);
    }

    @Test
    @Transactional
    @Rollback
    public void testCreateCustomer(){
        CustomerVO mockCustomerVO= CustomerVO.builder()
                .id(30)
                .type(CustomerType.SUPPLIER)
                .level(101)
                .name("dsvksfjv")
                .phone("318342847")
                .address("vsfuvb3fbekdjdsv")
                .zipcode("djf")
                .lineOfCredit(BigDecimal.valueOf(193438))
                .receivable(BigDecimal.valueOf(3547))
                .payable(BigDecimal.valueOf(3790842))
                .operator("dfjdfv")
                .build();
        Assert.assertEquals(1,customerService.createCustomer(mockCustomerVO));
    }

    @Test
    @Transactional
    @Rollback
    public void testDeleteCustomer(){
        Integer id=10;
        Assert.assertEquals(1,customerService.deleteCustomer(id));
    }

}
