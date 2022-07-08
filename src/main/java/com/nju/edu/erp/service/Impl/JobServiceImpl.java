package com.nju.edu.erp.service.Impl;

import com.nju.edu.erp.dao.JobDao;
import com.nju.edu.erp.model.po.JobPO;
import com.nju.edu.erp.model.vo.humanResource.JobVO;
import com.nju.edu.erp.service.JobService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class JobServiceImpl implements JobService {
    private final JobDao jobDao;

    @Autowired
    public JobServiceImpl(JobDao jobDao) {
        this.jobDao = jobDao;
    }

    @Override
    public void createDepartmentSalaryRule(JobVO jobVO) {
        JobPO jobPO=new JobPO();
        BeanUtils.copyProperties(jobVO,jobPO);
        jobDao.createDepartmentSalaryRule(jobPO);
    }

    @Override
    public List<JobVO> queryAllSalaryRules() {
        List<JobPO> jobPOS=jobDao.queryAllJobs();
        List<JobVO> jobVOS=new ArrayList<>();
        for(JobPO jobPO:jobPOS){
            JobVO jobVO=new JobVO();
            BeanUtils.copyProperties(jobPO,jobVO);
            jobVOS.add(jobVO);
        }
        return jobVOS;
    }

    @Override
    public void updateJobById(JobVO jobVO) {
        JobPO jobPO=new JobPO();
        BeanUtils.copyProperties(jobVO,jobPO);
        jobDao.updateJobById(jobPO);
    }
}
