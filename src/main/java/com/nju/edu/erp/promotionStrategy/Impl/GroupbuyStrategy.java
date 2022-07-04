package com.nju.edu.erp.promotionStrategy.Impl;

import com.nju.edu.erp.promotionStrategy.PromotionStrategy;

/**
 * @author hua
 */
public class GroupbuyStrategy implements PromotionStrategy {
  @Override
  public void doPromotion() {
    System.out.println("拼团，慢20人成团，全团享受团购价");
    test();   // Alt+enter  可快速创建test2方法
  }

  private void test() {
  }
}
