package com.nju.edu.erp.service;


import com.nju.edu.erp.model.po.SalarySheetPO;
import com.nju.edu.erp.model.vo.humanResource.JobVO;

import java.util.List;

public interface SalaryService {
    /**
     * 查询所有岗位信息
     *
     * @return 岗位信息列表
     */
    List<JobVO> queryAllJobs();

    /**
     * 根据岗位id更新岗位信息
     *
     * @param jobVO 岗位信息
     */
    void updateJobById(JobVO jobVO);

    /***
     * 生成工资单
     */
    void generateSalarySheet();

    List<SalarySheetPO> getSalarySheetByTime(String beginTime, String endTime);


    /**
     * 根据id查询工资单
     *
     * @param id 工资单id
     * @return 工资单
     */
    SalarySheetPO getSalarySheetById(Integer id);
}
