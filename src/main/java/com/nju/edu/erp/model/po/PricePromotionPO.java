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
public class PricePromotionPO {
  /**
   * 总价
   */
  @JsonProperty("price")
  private int price;

  public void setPrice(int price) {
    this.price = price;
  }

  /**
   * 赠品
   */
  @JsonProperty("gift")
  private String gift;

  public void setGift(String gift) {
    this.gift = gift;
  }


  public void setCoupon(int coupon) {
    this.coupon = coupon;
  }

  /**
   * 代金券
   */
  @JsonProperty("coupon")
  private int coupon;


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
