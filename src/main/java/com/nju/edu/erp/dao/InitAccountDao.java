package com.nju.edu.erp.dao;

import com.nju.edu.erp.model.po.*;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface InitAccountDao {

    int createProduct(InitProductPO initProductPO);

    void createAccount(InitCompanyAccountPO initCompanyAccountPO);

    int createCustomer(InitCustomerPO initCustomerPO);
}
