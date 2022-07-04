package com.nju.edu.erp.promotionStrategy.Impl;

import com.nju.edu.erp.promotionStrategy.PromotionStrategy;

/**
 * @author hua
 */
public class EmptyStrategy implements PromotionStrategy {
  @Override
  public void doPromotion() {
    System.out.println("无促销活动！");
  }
}
