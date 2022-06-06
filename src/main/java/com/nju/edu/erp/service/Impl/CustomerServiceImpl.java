package com.nju.edu.erp.service.Impl;

import ch.qos.logback.core.joran.util.beans.BeanUtil;
import com.nju.edu.erp.dao.CustomerDao;
import com.nju.edu.erp.enums.CustomerType;
import com.nju.edu.erp.model.po.CustomerPO;
import com.nju.edu.erp.model.vo.CustomerVO;
import com.nju.edu.erp.service.CustomerService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerDao customerDao;

    @Autowired
    public CustomerServiceImpl(CustomerDao customerDao) {
        this.customerDao = customerDao;
    }

    /**
     * 根据id更新客户信息
     *
     * @param customerPO 客户信息
     */
    @Override
    @Transactional
    public void updateCustomer(CustomerPO customerPO) {
        customerDao.updateOne(customerPO);
    }

    /**
     * 根据type查找对应类型的客户
     *
     * @param type 客户类型
     * @return 客户列表
     */
    @Override
    @Transactional
    public List<CustomerPO> getCustomersByType(CustomerType type) {

        return customerDao.findAllByType(type);
    }

    @Override
    @Transactional
    public CustomerPO findCustomerById(Integer supplier) {
        return customerDao.findOneById(supplier);
    }

  /**
   * 增加客户
   * @param customerVO 客户类型
   */
   @Override
   public void createCustomer(CustomerVO customerVO){
     CustomerPO customerPO = new CustomerPO();
     BeanUtils.copyProperties(customerVO, customerPO);
     customerDao.createCustomer(customerPO);
  }
  /**
   * 删除客户
   * @param
   */
  @Transactional
  public void deleteCustomer(Integer supplier){
    customerDao.deleteById(supplier);
  }
}
