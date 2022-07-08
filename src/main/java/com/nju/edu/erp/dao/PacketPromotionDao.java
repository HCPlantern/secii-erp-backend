package com.nju.edu.erp.dao;

import com.nju.edu.erp.model.po.PacketPromotionPO;
import com.nju.edu.erp.model.po.PromotionPO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author hua
 */
@Repository
@Mapper
public interface  PacketPromotionDao extends Dao{
  int createPromotion(PacketPromotionPO promotionPO);
}
