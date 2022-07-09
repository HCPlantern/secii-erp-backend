package com.nju.edu.erp.service;

import com.nju.edu.erp.model.po.PromotionPO;
import com.nju.edu.erp.model.vo.PromotionVO;
import com.nju.edu.erp.model.vo.sale.SaleDetailVO;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author hua
 */
@Service
public interface PromotionService {
  /**
   * 添加促销策略
   *
   * @param  promotionVO 策略信息
   */
  void addPromotionStrategy(PromotionVO promotionVO);

  /**
   * 根据时间段搜索促销详细信息
   * @param beginDateStr 开始时间字符串
   * @param endDateStr 结束时间字符串
   * @return 促销详细信息
   */
  List<PromotionPO> getPromotionStrategyByTime(String beginDateStr, String endDateStr);
}
