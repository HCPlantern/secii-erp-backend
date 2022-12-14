package com.nju.edu.erp.dao;

import com.nju.edu.erp.model.po.ProductPO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface ProductDao extends Dao {

    int createProduct(ProductPO productPO);

    int updateById(ProductPO productPO);

    ProductPO findById(String id);

    List<ProductPO> findAll();

    int deleteById(String id);

    int deleteAll();

    int insertFromInit();

}
