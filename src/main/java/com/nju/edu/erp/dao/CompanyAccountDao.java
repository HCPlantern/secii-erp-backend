package com.nju.edu.erp.dao;

import com.nju.edu.erp.model.po.CompanyAccountPO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
@Mapper
public interface CompanyAccountDao extends Dao {

    void createAccount(CompanyAccountPO companyAccountPO);

    List<CompanyAccountPO> findCompanyAccountByName(String name);

    List<CompanyAccountPO> findAllCompanyAccounts();

    void deleteCompanyAccountById(Integer id);

    void updateCompanyAccount(CompanyAccountPO companyAccountPO);

    /**
     * 注意是增量update
     *
     * @param id
     * @param amount
     */
    void collectionUpdateCompanyAccountAmountById(Integer id, BigDecimal amount);


    void paymentUpdateCompanyAccountAmountById(Integer id, BigDecimal amount);

}
