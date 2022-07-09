package com.nju.edu.erp.service.Impl;

import com.nju.edu.erp.dao.GiftDao;
import com.nju.edu.erp.dao.PromotionDao;
import com.nju.edu.erp.model.po.GiftPO;
import com.nju.edu.erp.model.po.ProductPO;
import com.nju.edu.erp.model.po.PromotionPO;
import com.nju.edu.erp.model.po.SaleSheetPO;
import com.nju.edu.erp.model.vo.GiftVO;
import com.nju.edu.erp.model.vo.PromotionVO;
import com.nju.edu.erp.promotionStrategy.PromotionContext;
import com.nju.edu.erp.service.PromotionService;
import com.nju.edu.erp.utils.IdGenerator;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author hua
 */
@Service
public class PromotionServiceImpl implements PromotionService {

  private final PromotionDao promotionDao;
  private final GiftDao giftDao;

  @Autowired
  public PromotionServiceImpl(PromotionDao promotionDao, GiftDao giftDao){

    this.promotionDao = promotionDao;
    this.giftDao = giftDao;
  }

  /**
   * 增加促销方案
   * @param  promotionVO 策略信息
   */
  @Override
  public void addPromotionStrategy(PromotionVO promotionVO){

    PromotionPO promotionPO = new PromotionPO();
    BeanUtils.copyProperties(promotionVO, promotionPO);
    // 设置Id
    PromotionPO latest = promotionDao.getLatestSheet();
    String id = IdGenerator.generateSheetId(latest == null ? null : latest.getId(), "XSD");
    promotionPO.setId(id);

    promotionDao.createPromotion(promotionPO);
    for(GiftVO giftVO : promotionVO.getGift()){
      GiftPO giftPO = new GiftPO();
      BeanUtils.copyProperties(giftVO,giftPO);
      giftPO.setPromotionSheetId(id);
      giftDao.addGift(giftPO);
    }
    //PromotionContext promotionContext = new PromotionContext(promotionVO.getPromotionStrategy());
    //promotionContext.execute(promotionVO);
  }
  /**
   * 根据时间查询促销方案
   *
   * @param beginTime 开始时间
   * @param endTime   结束时间
   * @return 促销信息
   */
  @Override
  public List<PromotionPO> getPromotionStrategyByTime(String beginTime, String endTime){
    List<PromotionPO> promotionPOS = promotionDao.getPromotionStrategyByTime(beginTime,endTime);
    for(PromotionPO promotionPO : promotionPOS){
      String id = promotionPO.getId();
      List<GiftPO> giftPOS = giftDao.getGiftById(id);
      promotionPO.setGift(giftPOS);
    }
    return promotionPOS;
  }
}
