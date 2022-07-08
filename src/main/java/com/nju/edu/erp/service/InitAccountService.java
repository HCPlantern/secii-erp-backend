package com.nju.edu.erp.service;

import com.nju.edu.erp.model.vo.*;

import java.util.List;

public interface InitAccountService {


    int createCustomer(InitCustomerVO initCustomerVO);

    public void createAccount(InitCompanyAccountVO initCompanyAccountVO);

    void createProduct(InitProductVO initProductVO);

    List<InitCustomerVO> getAllInitCustomer();

    List<InitProductVO> getAllInitProduct();


    List<InitCompanyAccountVO> getAllInitCompanyAccount();


}
