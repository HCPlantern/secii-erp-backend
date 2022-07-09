package com.nju.edu.erp.utils;

import com.nju.edu.erp.model.po.PromotionPO;
import com.nju.edu.erp.model.vo.PromotionVO;

/**
 * @author hua
 */
public class PromotionVOtoPO {
  public static PromotionPO convert(PromotionVO promotionVO){
    PromotionPO promotionPO = new PromotionPO();
    promotionPO.setCoupon(promotionVO.getCoupon());
    promotionPO.setCustomer_level(promotionVO.getCustomerLevel());
    //promotionPO.setGift(promotionVO.getGift());
    promotionPO.setDiscount(promotionVO.getDiscount());
   // promotionPO.setBeginDate(promotionVO.getBeginDate());
   // promotionPO.setEndDate(promotionVO.getEndDate());
    return promotionPO;
  }
}
