package com.nju.edu.erp.service;


import com.nju.edu.erp.enums.sheetState.SalarySheetState;
import com.nju.edu.erp.model.po.JobPO;
import com.nju.edu.erp.model.po.SalarySheetPO;
import com.nju.edu.erp.model.vo.humanResource.JobVO;

import java.util.List;

public interface SalaryService {
    /**
     * 查询所有岗位信息
     *
     * @return 岗位信息列表
     */
    List<JobPO> queryAllSalaryRules();

    /**
     * 根据岗位id更新岗位信息
     *
     * @param jobVO 岗位信息
     */
    void updateSalaryRuleById(JobVO jobVO);

    /**
     * 生成工资单，时间间隔为一个月
     * @param beginDateStr 起始时间
     * @param endDateStr 截止时间
     */
    void generateSalarySheet(String beginDateStr, String endDateStr);

    List<SalarySheetPO> getSalarySheetByTime(String beginTime, String endTime);

    /**
     * 根据id查询工资单
     *
     * @param id 工资单id
     * @return 工资单
     */
    SalarySheetPO getSalarySheetById(String id);

    List<SalarySheetPO> getSalarySheetByState(SalarySheetState state);
}
