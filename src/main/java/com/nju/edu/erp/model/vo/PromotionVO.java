package com.nju.edu.erp.model.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.nju.edu.erp.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * @author hua
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PromotionVO {
  /**
   * 策略方案
   */
  @JsonProperty("promotionStrategy")
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
  private String gift;

  /**
   * 折扣
   */
  @JsonProperty("discount")
  private Double discount;

  /**
   * 代金券
   */
  @JsonProperty("coupon")
  private String coupon;

  /**
   * 商品
   */
  @JsonProperty("commodity")
  private String commodity;

  /**
   * 价格
   */
  @JsonProperty("price")
  private Integer price;

  /**
   * 开始日期
   */
  @JsonProperty("beginDate")
  private String beginDate;

  /**
   * 结束日期
   */
  @JsonProperty("endDate")
  private String endDate;

}
