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
    String CUSTOMER = "CUSTOMER";
    String PACKET = "PACKET";
    String PRICE = "PRICE";
  }
  private static Map<String,PromotionStrategy> PROMOTION_STRATEGY_MAP =new HashMap<String,PromotionStrategy>();

  static {
    PROMOTION_STRATEGY_MAP.put(PromotionKey.CUSTOMER,new CustomerStrategy());
    PROMOTION_STRATEGY_MAP.put(PromotionKey.PACKET,new PacketStrategy());
    PROMOTION_STRATEGY_MAP.put(PromotionKey.PRICE,new PriceStrategy());
  }

  private static final PromotionStrategy NON_PROMOTION = null;

  public static PromotionStrategy getPromotionStrategy(String promotionKey){
    PromotionStrategy promotionStrategy = PROMOTION_STRATEGY_MAP.get(promotionKey);
    return promotionStrategy == null ? NON_PROMOTION:promotionStrategy;
  }
}
