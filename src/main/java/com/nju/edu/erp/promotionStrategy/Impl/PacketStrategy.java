package com.nju.edu.erp.promotionStrategy.Impl;

import com.nju.edu.erp.dao.PacketPromotionDao;
import com.nju.edu.erp.dao.PromotionDao;
import com.nju.edu.erp.model.po.PacketPromotionPO;
import com.nju.edu.erp.model.vo.PromotionVO;
import com.nju.edu.erp.promotionStrategy.PromotionStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author hua
 */
@Component
public class PacketStrategy implements PromotionStrategy {
  PacketPromotionDao packetPromotionDao;
  @Autowired
  public void setDao(PacketPromotionDao promotionDao ) {
    this.packetPromotionDao = promotionDao;
  }
  @Override
  public void doPromotion(PromotionVO promotionVO) {
    PacketPromotionPO packetPromotionPO = new PacketPromotionPO();
    //packetPromotionPO.setBeginDate(promotionVO.getBeginDate());
    //packetPromotionPO.setCommodity(promotionVO.getCommodity());
    packetPromotionPO.setPrice(promotionVO.getPrice());
   // packetPromotionPO.setEndDate(promotionVO.getEndDate());
    packetPromotionDao.createPromotion(packetPromotionPO);
  }
}
