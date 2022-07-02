package com.nju.edu.erp.service.Impl;

import com.nju.edu.erp.dao.JobDao;
import com.nju.edu.erp.dao.SalarySheetDao;
import com.nju.edu.erp.model.po.SalarySheetPO;
import com.nju.edu.erp.model.po.SheetPO;
import com.nju.edu.erp.model.vo.humanResource.JobVO;
import com.nju.edu.erp.service.SalaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SalaryServiceImpl implements SalaryService {

    private final JobDao jobDao;

    private final SalarySheetDao salarySheetDao;

    @Autowired
    public SalaryServiceImpl(JobDao jobDao, SalarySheetDao salarySheetDao) {
        this.jobDao = jobDao;
        this.salarySheetDao = salarySheetDao;
    }

    /**
     * 查询所有岗位信息
     *
     * @return 岗位信息列表
     */
    public List<JobVO> queryAllJobs() {
        return null;
    }

    /**
     * 根据岗位id更新岗位信息
     *
     * @param jobVO 岗位信息
     */
    public void updateJobById(JobVO jobVO) {

    }

    /**
     * 生成工资单
     */
    public void generateSalarySheet() {

    }

    /**
     * 根据时间查询工资单
     *
     * @param beginTime 开始时间
     * @param endTime   结束时间
     * @return 工资单
     */
    @Override
    public List<SalarySheetPO> getSalarySheetByTime(String beginTime, String endTime) {
        return salarySheetDao.getSalarySheetByTime(beginTime, endTime);
    }

    /**
     * 根据id查询工资单
     * @param id 工资单id
     * @return 工资单
     */
    public SalarySheetPO getSalarySheetById(Integer id) {
        return salarySheetDao.getSalarySheetById(id);
    }
}
