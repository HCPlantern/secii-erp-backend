package com.nju.edu.erp.model.po;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author hua
 */
public class PacketPromotionPO {
  /**
   * 商品
   */
  @JsonProperty("commodity")
  private String commodity;

  public void setCommodity(String commodity) {
    this.commodity = commodity;
  }

  /**
   * 价格
   */
  @JsonProperty("price")
  private Integer price;

  public void setPrice(Integer price) {
    this.price = price;
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
