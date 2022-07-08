package com.nju.edu.erp.dao;

import com.nju.edu.erp.model.po.CompanyAccountPO;
import com.nju.edu.erp.model.po.ProductPO;
import com.nju.edu.erp.model.po.PromotionPO;
import com.nju.edu.erp.model.po.SheetPO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author hua
 */
@Repository
@Mapper
public interface  PromotionDao extends Dao{
  int createPromotion(PromotionPO promotionPO);
}
