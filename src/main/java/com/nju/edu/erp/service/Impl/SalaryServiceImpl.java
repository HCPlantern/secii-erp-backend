package com.nju.edu.erp.service.Impl;

import com.nju.edu.erp.dao.JobDao;
import com.nju.edu.erp.model.vo.humanResource.JobVO;
import com.nju.edu.erp.service.SalaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SalaryServiceImpl implements SalaryService {

    private final JobDao jobDao;

    @Autowired
    public SalaryServiceImpl(JobDao jobDao) {
        this.jobDao = jobDao;
    }

    /**
     * 查询所有岗位信息
     *
     * @return
     */
    public List<JobVO> queryAllJobs() {
        return null;
    }

    /**
     * 根据岗位id更新岗位信息
     *
     * @param jobVO
     */
    public void updateJobById(JobVO jobVO) {

    }

    /***
     * 生成工资单
     */
    public void generateSalarySheet() {

    }
}
