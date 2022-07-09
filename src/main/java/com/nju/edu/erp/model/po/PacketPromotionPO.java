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
public class PacketPromotionPO {
  /**
   * 商品
   */
  private String commodity;

  public void setCommodity(String commodity) {
    this.commodity = commodity;
  }

  private String gift;

  /**
   * 价格
   */
  private Integer price;

  public void setPrice(Integer price) {
    this.price = price;
  }

  /**
   * 开始日期
   */
  private String beginDate;

  /**
   * 结束日期
   */
  private String endDate;

  public void setBeginDate(String beginDate) {
    this.beginDate = beginDate;
  }

  public void setEndDate(String endDate) {
    this.endDate = endDate;
  }
}
