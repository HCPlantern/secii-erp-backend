package com.nju.edu.erp.model.queryObject;

import com.nju.edu.erp.enums.Role;
import com.nju.edu.erp.enums.sheetState.SalarySheetState;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SalarySheetQuery {
    String id;
    Integer employeeId;
    /**
     * 开始时间，注意目前按照创建时间来查询
     */
    String beginTime;
    /**
     * 结束时间，注意目前按照创建时间来查询
     */
    String endTime;
    /**
     * 职位
     */
    Role job;
    String salaryAccount;
    SalarySheetState state;
}
