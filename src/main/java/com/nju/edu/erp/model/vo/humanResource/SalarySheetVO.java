package com.nju.edu.erp.model.vo.humanResource;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SalarySheetVO {
    /**
     * 姓名
     */
    private String name;

    /**
     * 工作岗位
     */
    private String job;

    /**
     * 基本工资
     */
    private String baseWage;

    /**
     * 岗位工资
     */
    private String postWage;

    /**
     * 岗位级别
     */
    private String jobGrade;

}
