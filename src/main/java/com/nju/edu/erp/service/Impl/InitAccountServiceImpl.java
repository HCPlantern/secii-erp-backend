package com.nju.edu.erp.service.Impl;

import com.nju.edu.erp.dao.CategoryDao;
import com.nju.edu.erp.dao.InitAccountDao;
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

@Service
// 期初建账业务逻辑
public class InitAccountServiceImpl implements InitAccountService {

    private final InitAccountDao initAccountDao;

    private final CategoryDao categoryDao;

    @Autowired
    public InitAccountServiceImpl(InitAccountDao initAccountDao, CategoryDao categoryDao) {
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
        return initAccountDao.createCustomer(initCustomerPO);
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
        category.setItemCount(category.getItemCount() + 1);
        category.setItemIndex(category.getItemIndex() + 1);
        categoryDao.updateById(category);
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
