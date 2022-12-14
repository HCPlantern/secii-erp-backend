package com.nju.edu.erp.service;


import com.nju.edu.erp.dao.ProductDao;
import com.nju.edu.erp.model.po.ProductPO;
import com.nju.edu.erp.model.vo.CreateProductVO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

@SpringBootTest
public class ProductServiceTest {

    @Autowired
    ProductDao productDao;

    @Autowired
    ProductService productService;

    @Test
    void updateTest() {
        ProductPO productPO = new ProductPO();
        productPO.setId("0000000000500000");
        productPO.setName("intel电脑");
        productDao.updateById(productPO);
    }

    @Test
    void findByIdTest() {
        System.out.println(productDao.findById("0000000000500000"));
    }

    @Test
    void createProductTest() {
        CreateProductVO createProductVO = CreateProductVO.builder()
                .name("test")
                .categoryId(5)
                .purchasePrice(new BigDecimal(1.0))
                .retailPrice(new BigDecimal(2.0))
                .type("unknown")
                .build();
        try {
            productService.createProduct(createProductVO);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
