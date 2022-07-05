package com.nju.edu.erp.service;

import com.nju.edu.erp.model.vo.*;

import java.util.List;

public interface InitAccountService {

//    添加商品信息
//    客户信息
//    银行账户信息
    int createCustomer(InitCustomerVO initCustomerVO);

    public void createAccount(InitCompanyAccountVO initCompanyAccountVO);

    void createProduct(InitProductVO initProductVO);

    List<InitCustomerVO> getAllInitCustomer();

    List<InitProductVO> getAllInitProduct();


    List<InitCompanyAccountVO> getAllInitCompanyAccount();


}
