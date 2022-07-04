package com.nju.edu.erp.promotionStrategy;

/**
 * @author hua
 */
public interface PromotionStrategy {
  //促销策略：赠品、价格折让、赠送代金券
  //制定促销策略
  //（1. 总经理可以针对不同级别的用户制定促销策略（赠品、价格折让、 赠送代金劵）。
    //2. 总经理可以制定特价包（组合商品降价）。
    //3. 总经理可以制定 针对不同总价的促销策略（赠品、赠送代金卷）。
  //所有促销策略都有其实时间和间 隔时间。所有赠品条件促发后，系统会自动建立库存赠送单，由总经理审批通过后， 发送消息给库存管理员发放赠品。）
    void doPromotion();
}
