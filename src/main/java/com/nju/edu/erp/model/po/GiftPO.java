package com.nju.edu.erp.model.po;

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
public class GiftPO {
  /**
   *商品ID
   */
  private String pid;
  /**
   * 数量
   */
  private Integer quantity;
  /**
   * 备注
   */
  private String remark;
  /**
   * 促销关联ID
   */
  private String promotionSheetId;
}
