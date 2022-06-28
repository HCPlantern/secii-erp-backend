package com.nju.edu.erp.model.vo.sheet;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.nju.edu.erp.model.vo.ISheetVO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SheetVO extends ISheetVO {
    /**
     * 单据编号
     */
    String id;
    /**
     * 单据类型
     */
    String type;
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
