package com.nju.edu.erp.dao;

import com.nju.edu.erp.model.po.CompanyAccountPO;
import com.nju.edu.erp.model.vo.CompanyAccountVO;
import io.swagger.models.auth.In;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
@Mapper
public interface CompanyAccountDao {

    public void createAccount(CompanyAccountPO companyAccountPO);

    public List<CompanyAccountPO> findCompanyAccountByName(String name);

    public List<CompanyAccountPO> findAllCompanyAccounts();

    public void deleteCompanyAccountById(Integer id);

    public void updateCompanyAccount(CompanyAccountPO companyAccountPO);

    /**
     * 注意是增量update
     * @param id
     * @param amount
     */
    public void collectionUpdateCompanyAccountAmountById(Integer id, BigDecimal amount);


    public void paymentUpdateCompanyAccountAmountById(Integer id, BigDecimal amount);

}
