package com.nju.edu.erp.dao;

import com.nju.edu.erp.model.po.*;
import com.nju.edu.erp.model.vo.InitCompanyAccountVO;
import com.nju.edu.erp.model.vo.InitCustomerVO;
import com.nju.edu.erp.model.vo.InitProductVO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface InitAccountDao {

    int createProduct(InitProductPO initProductPO);

    void createAccount(InitCompanyAccountPO initCompanyAccountPO);

    int createCustomer(InitCustomerPO initCustomerPO);

    List<InitCustomerPO> getAllInitCustomer();

    List<InitProductPO> getAllInitProduct();

    List<InitCompanyAccountPO> getAllInitCompanyAccount();

}
