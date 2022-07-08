package com.nju.edu.erp.dao;

import com.nju.edu.erp.enums.Role;
import com.nju.edu.erp.model.po.JobPO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface JobDao extends Dao {

    JobPO findJobByName(Role name);

    void updateJobById(JobPO jobPO);

    List<JobPO> queryAllJobs();

    void createDepartmentSalaryRule(JobPO jobPO);

}
