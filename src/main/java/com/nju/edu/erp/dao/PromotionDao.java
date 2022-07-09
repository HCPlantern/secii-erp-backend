package com.nju.edu.erp.dao;

import com.nju.edu.erp.model.po.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author hua
 */
@Repository
@Mapper
public interface  PromotionDao extends Dao{

  PromotionPO getLatestSheet();

  int createPromotion(PromotionPO promotionPO);

  List<PromotionPO> getPromotionStrategyByTime(@Param("beginTime") String beginTime, @Param("endTime") String endTime);
}
