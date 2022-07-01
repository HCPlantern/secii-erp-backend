package com.nju.edu.erp.service;

import com.nju.edu.erp.enums.CustomerType;
import com.nju.edu.erp.model.po.CustomerPO;
import com.nju.edu.erp.model.vo.CustomerVO;

import java.util.List;

public interface CustomerService {
    /**
     * 根据id更新客户信息
     * @param customerVO 客户信息
     */
    int updateCustomer(CustomerVO customerVO);

    /**
     * 根据type查找对应类型的客户
     * @param type 客户类型
     * @return 客户列表
     */
    List<CustomerPO> getCustomersByType(CustomerType type);


    CustomerPO findCustomerById(Integer supplier);

  /**
   * 增加客户
   * @param customerVO 客户类型
   */
    int createCustomer(CustomerVO customerVO);

  /**
   * 删除客户
   * @param
   */
    int deleteCustomer(Integer id);
}
