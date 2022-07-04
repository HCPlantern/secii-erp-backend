package com.nju.edu.erp.promotionStrategy;

import com.nju.edu.erp.promotionStrategy.Impl.CashbackStrategy;
import com.nju.edu.erp.promotionStrategy.Impl.CouponStrategy;

/**
 * @author hua
 */
public class Test1 {
  public static void main( String[] args ) {
    PromotionContext activity618 = new PromotionContext(new CouponStrategy());
    PromotionContext activity1111 = new PromotionContext(new CashbackStrategy());
    activity618.execute();
    activity1111.execute();
  }
}
