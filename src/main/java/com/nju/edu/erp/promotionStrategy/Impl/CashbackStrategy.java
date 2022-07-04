package com.nju.edu.erp.promotionStrategy.Impl;

import com.nju.edu.erp.promotionStrategy.PromotionStrategy;

/**
 * @author hua
 */
public class CashbackStrategy implements PromotionStrategy {
  @Override
  public void doPromotion() {
    System.out.println("返现促销，返回的金额转到支付宝账号");
  }
}
