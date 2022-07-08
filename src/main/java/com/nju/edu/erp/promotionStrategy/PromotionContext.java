package com.nju.edu.erp.promotionStrategy;

/**
 * @author hua
 */
public class PromotionContext {
  private PromotionStrategy promotionStrategy;

  public PromotionContext(String strategy) {
    this.promotionStrategy = PromotionStrategyFactory.getPromotionStrtety(strategy);
  }

  public void execute(){
    promotionStrategy.doPromotion();
  }
}
