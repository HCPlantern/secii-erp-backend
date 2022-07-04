package com.nju.edu.erp.service.Impl;

import com.nju.edu.erp.dao.*;
import com.nju.edu.erp.exception.MyServiceException;
import com.nju.edu.erp.model.po.CategoryPO;
import com.nju.edu.erp.model.po.InitCompanyAccountPO;
import com.nju.edu.erp.model.po.InitCustomerPO;
import com.nju.edu.erp.model.po.InitProductPO;
import com.nju.edu.erp.model.vo.*;
import com.nju.edu.erp.service.InitAccountService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
// 期初建账业务逻辑
public class InitAccountServiceImpl implements InitAccountService {

    private final CustomerDao customerDao;

    private final CompanyAccountDao companyAccountDao;

    private final ProductDao productDao;

    private final InitAccountDao initAccountDao;

    private final CategoryDao categoryDao;

    @Autowired
    public InitAccountServiceImpl(CustomerDao customerDao, CompanyAccountDao companyAccountDao, ProductDao productDao, InitAccountDao initAccountDao, CategoryDao categoryDao) {
        this.customerDao = customerDao;
        this.companyAccountDao = companyAccountDao;
        this.productDao = productDao;
        this.initAccountDao = initAccountDao;
        this.categoryDao = categoryDao;
    }

    /**
     * 期初创建客户
     * @param initCustomerVO
     * @return
     */
    @Override
    @Transactional
    public int createCustomer(InitCustomerVO initCustomerVO) {
        InitCustomerPO initCustomerPO=new InitCustomerPO();
        BeanUtils.copyProperties(initCustomerVO,initCustomerPO);
        int res = initAccountDao.createCustomer(initCustomerPO);
//        customerDao.deleteAll();
        customerDao.insertFromInit();
        return res;
    }

    /**
     * 期初创建公司账户
     * @param initCompanyAccountVO
     */
    @Override
    @Transactional
    public void createAccount(InitCompanyAccountVO initCompanyAccountVO) {
        InitCompanyAccountPO initCompanyAccountPO=new InitCompanyAccountPO();
        BeanUtils.copyProperties(initCompanyAccountVO,initCompanyAccountPO);
        initAccountDao.createAccount(initCompanyAccountPO);
//        companyAccountDao.deleteAll();
        companyAccountDao.insertFromInit();
    }

    /**
     * 期初建立商品
     * @param initProductVO
     * @return
     */
    @Override
    @Transactional
    public void createProduct(InitProductVO initProductVO) {
        CategoryPO category = categoryDao.findByCategoryId(initProductVO.getCategoryId());
        // 无法增加商品的情况
        if (category == null) {
            throw new MyServiceException("B0001", "当前分类不存在");
        }
        if (!category.isLeaf()) {
            throw new MyServiceException("B0002", "当前分类无法增加商品");
        }
        String productId = generateProductId(category);
        InitProductPO savePO=new InitProductPO();
        BeanUtils.copyProperties(initProductVO,savePO);
        savePO.setId(productId);
        initAccountDao.createProduct(savePO);
        // 修改对应的商品分类
        category.setItemCount(category.getItemCount() + 1);
        category.setItemIndex(category.getItemIndex() + 1);
        categoryDao.updateById(category);
//        productDao.deleteAll();
        productDao.insertFromInit();
    }

    @Override
    public List<InitCustomerVO> getAllInitCustomer() {
        List<InitCustomerPO> initCustomerPOS=initAccountDao.getAllInitCustomer();
        List<InitCustomerVO> res=new ArrayList<>();
        for(InitCustomerPO initCustomerPO:initCustomerPOS){
            InitCustomerVO initCustomerVO=new InitCustomerVO();
            BeanUtils.copyProperties(initCustomerPO,initCustomerVO);
            res.add(initCustomerVO);
        }
        return res;
    }

    @Override
    public List<InitProductVO> getAllInitProduct() {
        List<InitProductPO> initProductPOS=initAccountDao.getAllInitProduct();
        List<InitProductVO> res=new ArrayList<>();
        for(InitProductPO initProductPO:initProductPOS){
            InitProductVO initProductVO=new InitProductVO();
            BeanUtils.copyProperties(initProductVO,initProductPO);
            res.add(initProductVO);
        }
        return res;
    }

    @Override
    public List<InitCompanyAccountVO> getAllInitCompanyAccount() {
        List<InitCompanyAccountPO> initCompanyAccountPOS=initAccountDao.getAllInitCompanyAccount();
        List<InitCompanyAccountVO> res=new ArrayList<>();
        for(InitCompanyAccountPO initCompanyAccountPO:initCompanyAccountPOS){
            InitCompanyAccountVO initCompanyAccountVO=new InitCompanyAccountVO();
            BeanUtils.copyProperties(initCompanyAccountPO,initCompanyAccountVO);
            res.add(initCompanyAccountVO);
        }
        return res;
    }

    private String generateProductId(CategoryPO categoryPO) {
        StringBuilder ans = new StringBuilder();
        String categoryStr = categoryPO.getId().toString();
        String indexStr = categoryPO.getItemIndex().toString();
        if (indexStr.length() > 5) {
            throw new MyServiceException("B0006", "当前分类下商品编号已用完！");
        }
        for (int i = 0; i < 11 - categoryStr.length(); i++)
            ans.append('0');
        ans.append(categoryStr);
        for (int i = 0; i < 5 - indexStr.length(); i++)
            ans.append('0');
        ans.append(indexStr);
        return ans.toString();
    }
}
