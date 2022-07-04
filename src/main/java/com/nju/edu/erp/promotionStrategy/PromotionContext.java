package com.nju.edu.erp.promotionStrategy;

/**
 * @author hua
 */
public class PromotionContext {
  private PromotionStrategy promotionStrategy;

  public PromotionContext( PromotionStrategy promotionStrategy ) {
    this.promotionStrategy = promotionStrategy;
  }

  public void execute(){
    promotionStrategy.doPromotion();
  }
}
