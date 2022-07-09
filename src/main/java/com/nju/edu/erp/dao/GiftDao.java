package com.nju.edu.erp.dao;

import com.nju.edu.erp.model.po.GiftPO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author hua
 */

@Repository
@Mapper
public interface GiftDao {
  int addGift(GiftPO giftPO);
  List<GiftPO> getGiftById(String promotionSheetId);
}
