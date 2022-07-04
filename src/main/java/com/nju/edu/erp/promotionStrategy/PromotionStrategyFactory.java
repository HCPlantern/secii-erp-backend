package com.nju.edu.erp.promotionStrategy;

/**
 * @author hua
 */
import com.nju.edu.erp.promotionStrategy.Impl.*;

import java.util.HashMap;
import java.util.Map;

public class PromotionStrategyFactory {
  private interface PromotionKey{
    String COUPON= "COUPON";
    String CASHBACK ="CASHBACK";
    String GROUPBUY = "GROUPBUY";
    String DISCOUNT = "DISCOUNT";
    String GIFT = "GIFT";
  }
  private static Map<String,PromotionStrategy> PROMOTION_STRATEGY_MAP =new HashMap<String,PromotionStrategy>();

  static {
    PROMOTION_STRATEGY_MAP.put(PromotionKey.COUPON,new CouponStrategy());
    PROMOTION_STRATEGY_MAP.put(PromotionKey.CASHBACK,new CashbackStrategy());
    PROMOTION_STRATEGY_MAP.put(PromotionKey.GROUPBUY,new GroupbuyStrategy());
    PROMOTION_STRATEGY_MAP.put(PromotionKey.DISCOUNT,new DiscountStrategy());
    PROMOTION_STRATEGY_MAP.put(PromotionKey.GIFT,new GiftStrategy());
  }

  private static final PromotionStrategy NON_PROMOTION = new EmptyStrategy();

  public static PromotionStrategy getPromotionStrtety(String promotionKey){
    PromotionStrategy promotionStrategy = PROMOTION_STRATEGY_MAP.get(promotionKey);
    return promotionStrategy == null ? NON_PROMOTION:promotionStrategy;
  }
}
