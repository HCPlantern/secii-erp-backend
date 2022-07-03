package com.nju.edu.erp.dao;

import com.nju.edu.erp.model.po.TaxPO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface TaxDao extends Dao{

    /**
     * 查询税率单，按升序排序
     * @return
     */
    List<TaxPO> findTax();
}
