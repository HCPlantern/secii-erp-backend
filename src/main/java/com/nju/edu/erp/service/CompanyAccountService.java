package com.nju.edu.erp.service;

import com.nju.edu.erp.model.vo.CompanyAccountVO;

import java.util.List;

public interface CompanyAccountService {

    public void createAccount(CompanyAccountVO companyAccountVO);

    public List<CompanyAccountVO> findCompanyAccountByName(String name);


    public void deleteCompanyAccountById(Integer id);

    public void updateCompanyAccount(CompanyAccountVO companyAccountVO);

}
