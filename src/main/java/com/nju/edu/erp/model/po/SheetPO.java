package com.nju.edu.erp.model.po;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SheetPO extends ISheetPO{
    /**
     * 单据编号
     */
    String id;
    /**
     * 时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    Date date;
    /**
     * 客户
     */
    String client;
    /**
     * 业务员
     */
    String operator;
}
