package com.nju.edu.erp.service.Impl;


import com.nju.edu.erp.enums.Role;
import com.nju.edu.erp.model.vo.humanResource.JobVO;

import java.util.List;

public interface SalaryService {
    /**
     * 查询所有岗位信息
     *
     * @return
     */
    List<JobVO> queryAllJobs();

    /**
     * 根据岗位id更新岗位信息
     *
     * @param jobVO
     */
    void updateJobById(JobVO jobVO);

    /***
     * 生成工资单
     */
    void generateSalarySheet();
}
