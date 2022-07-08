package com.nju.edu.erp.promotionStrategy.Impl;

import com.nju.edu.erp.dao.JobDao;
import com.nju.edu.erp.dao.PromotionDao;
import com.nju.edu.erp.model.po.PromotionPO;
import com.nju.edu.erp.model.po.SheetPO;
import com.nju.edu.erp.model.vo.PromotionVO;
import com.nju.edu.erp.promotionStrategy.PromotionStrategy;
import com.nju.edu.erp.utils.PromotionVOtoPO;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author hua
 */
public class CustomerStrategy implements PromotionStrategy {
  private static PromotionDao promotionDao;

  @Autowired
  public void setDao(PromotionDao promotionDao ) {
    this.promotionDao = promotionDao;
  }

  @Override
  public void doPromotion(PromotionVO promotionVO) {
    PromotionPO promotionPO = PromotionVOtoPO.convert(promotionVO);
    promotionDao.createPromotion(promotionPO);
  }
}
