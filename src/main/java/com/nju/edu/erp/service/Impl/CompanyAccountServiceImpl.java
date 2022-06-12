package com.nju.edu.erp.service.Impl;

import com.nju.edu.erp.dao.CompanyAccountDao;
import com.nju.edu.erp.model.po.CompanyAccountPO;
import com.nju.edu.erp.model.vo.CompanyAccountVO;
import com.nju.edu.erp.service.CompanyAccountService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CompanyAccountServiceImpl implements CompanyAccountService {

    private final CompanyAccountDao companyAccountDao;

    @Autowired
    public CompanyAccountServiceImpl(CompanyAccountDao companyAccountDao) {
        this.companyAccountDao = companyAccountDao;
    }

    @Override
    public void createAccount(CompanyAccountVO companyAccountVO) {
        // 一开始创建银行账户可以手动输入amount(如果没有会有一个默认值)
        // 后续amount的更改只会根据收款单和付款单修改，不可手动修改
        CompanyAccountPO companyAccountPO=new CompanyAccountPO();
        BeanUtils.copyProperties(companyAccountVO,companyAccountPO);
        companyAccountDao.createAccount(companyAccountPO);
    }

    @Override
    public List<CompanyAccountVO> findCompanyAccountByName(String name) {
        List<CompanyAccountPO> companyAccountPOS;
        if(name==null){
            companyAccountPOS=companyAccountDao.findAllCompanyAccounts();
        }else {
            companyAccountPOS=companyAccountDao.findCompanyAccountByName(name);
        }
        List<CompanyAccountVO> companyAccountVOS=new ArrayList<>();
        for(CompanyAccountPO companyAccountPO:companyAccountPOS){
            CompanyAccountVO companyAccountVO=new CompanyAccountVO();
            BeanUtils.copyProperties(companyAccountPO,companyAccountVO);
            companyAccountVOS.add(companyAccountVO);
        }
        return companyAccountVOS;
    }

    @Override
    public void deleteCompanyAccountById(Integer id) {
        companyAccountDao.deleteCompanyAccountById(id);
    }

    @Override
    public void updateCompanyAccount(CompanyAccountVO companyAccountVO) {
        CompanyAccountPO companyAccountPO=new CompanyAccountPO();
        BeanUtils.copyProperties(companyAccountVO,companyAccountPO);
        companyAccountDao.updateCompanyAccount(companyAccountPO);
    }
}
