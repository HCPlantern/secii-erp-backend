package com.nju.edu.erp.model.po;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author hua
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PromotionPO {
  /**
   * 用户级别
   */
  @JsonProperty("customer_level")
  private int customer_level;

  public void setCustomer_level(int customer_level) {
    this.customer_level = customer_level;
  }

  /**
   * 赠品
   */
  @JsonProperty("gift")
  private String gift;

  public void setGift(String gift) {
    this.gift = gift;
  }

  /**
   * 折扣
   */
  @JsonProperty("discount")
  private float discount;

  public void setCoupon(int coupon) {
    this.coupon = coupon;
  }

  /**
   * 代金券
   */
  @JsonProperty("coupon")
  private int coupon;

  public void setDiscount(float discount) {
    this.discount = discount;
  }

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

  public void setBeginDate(String beginDate) {
    this.beginDate = beginDate;
  }

  public void setEndDate(String endDate) {
    this.endDate = endDate;
  }
}
