package com.nju.edu.erp.model.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

/**
 * @author hua
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PromotionVO {
  /**
   * 策略方案ID
   */
  private String id;
  /**
   * 策略方案
   */
  private String promotionStrategy;

  /**
   * 用户等级
   */
  @JsonProperty("customerLevel")
  private Integer customerLevel;

  /**
   * 赠品
   */
  @JsonProperty("gift")
  private List<GiftVO> gift;

  /**
   * 折扣
   */
  @JsonProperty("discount")
  private Float discount;

  /**
   * 代金券
   */
  @JsonProperty("coupon")
  private Integer coupon;

  /**
   * 价格
   */
  @JsonProperty("price")
  private Integer price;

  /**
   * 开始日期
   */
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
  private Date beginTime;


  /**
   * 结束日期
   */
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
  private Date endTime;

}
