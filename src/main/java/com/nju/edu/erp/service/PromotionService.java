package com.nju.edu.erp.service;

import com.nju.edu.erp.model.vo.PromotionVO;
import org.springframework.stereotype.Service;

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
}
