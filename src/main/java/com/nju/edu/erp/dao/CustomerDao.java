package com.nju.edu.erp.dao;

import com.nju.edu.erp.enums.CustomerType;
import com.nju.edu.erp.model.po.CustomerPO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
@Mapper
public interface CustomerDao extends Dao {
    int updateOne(CustomerPO customerPO);

    CustomerPO findOneById(Integer id);

    List<CustomerPO> findAllByType(CustomerType type);

    List<CustomerPO> findAll();

    int createCustomer(CustomerPO customerPO);

    int deleteById(Integer id);

    /**
     * 更新客户的应收数据
     *
     * @param id     客户id
     * @param amount 数额
     * @return 受影响的行数
     */
    int updateReceivableById(Integer id, BigDecimal amount);

    /**
     * 更新客户的应付数据
     *
     * @param id     客户id
     * @param amount 数额
     * @return
     */
    int updatePayableById(Integer id, BigDecimal amount);

    /**
     * 删除表的数据
     * @return
     */
    int deleteAll();

    /**
     * 从初始化的表中导入
     * @return
     */
    int insertFromInit();
}
