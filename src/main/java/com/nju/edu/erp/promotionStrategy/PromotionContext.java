package com.nju.edu.erp.promotionStrategy;

import com.nju.edu.erp.model.vo.PromotionVO;

/**
 * @author hua
 */
public class PromotionContext {
  private PromotionStrategy promotionStrategy;

  public PromotionContext(String strategy) {
    this.promotionStrategy = PromotionStrategyFactory.getPromotionStrategy(strategy);
  }

  public void execute(PromotionVO promotionVO){
    promotionStrategy.doPromotion(promotionVO);
  }
}
