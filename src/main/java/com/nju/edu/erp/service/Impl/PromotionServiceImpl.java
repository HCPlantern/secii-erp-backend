package com.nju.edu.erp.service.Impl;

import com.nju.edu.erp.model.vo.PromotionVO;
import com.nju.edu.erp.promotionStrategy.PromotionContext;
import com.nju.edu.erp.service.PromotionService;
import org.springframework.stereotype.Service;

/**
 * @author hua
 */
@Service
public class PromotionServiceImpl implements PromotionService {
  @Override
  public void addPromotionStrategy(PromotionVO promotionVO){
    PromotionContext promotionContext = new PromotionContext(promotionVO.getPromotionStrategy());
  }
}
