package com.nju.edu.erp.promotionStrategy.Impl;

import com.nju.edu.erp.dao.PacketPromotionDao;
import com.nju.edu.erp.dao.PricePromotionDao;
import com.nju.edu.erp.dao.PromotionDao;
import com.nju.edu.erp.model.po.PacketPromotionPO;
import com.nju.edu.erp.model.po.PricePromotionPO;
import com.nju.edu.erp.model.po.PromotionPO;
import com.nju.edu.erp.model.vo.PromotionVO;
import com.nju.edu.erp.promotionStrategy.PromotionStrategy;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author hua
 */
public class PriceStrategy implements PromotionStrategy {
  PricePromotionDao pricePromotionDao;
  @Autowired
  public void setDao(PricePromotionDao promotionDao ) {
    this.pricePromotionDao = promotionDao;
  }
  @Override
  public void doPromotion(PromotionVO promotionVO){
    PricePromotionPO pricePromotionPO = new PricePromotionPO();
    pricePromotionPO.setBeginDate(promotionVO.getBeginDate());
    pricePromotionPO.setCoupon(promotionVO.getCoupon());
    pricePromotionPO.setGift(promotionVO.getGift());
    pricePromotionPO.setPrice(promotionVO.getPrice());
    pricePromotionPO.setEndDate(promotionVO.getEndDate());
    pricePromotionDao.createPromotion(pricePromotionPO);
  }
}
