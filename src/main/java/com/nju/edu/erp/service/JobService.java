package com.nju.edu.erp.service;

import com.nju.edu.erp.model.vo.humanResource.JobVO;

import java.util.List;

public interface JobService {

    void createDepartmentSalaryRule(JobVO jobVO);

    List<JobVO> queryAllSalaryRules();

    void updateJobById(JobVO jobVO);

}
