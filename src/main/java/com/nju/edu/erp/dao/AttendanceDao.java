package com.nju.edu.erp.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface AttendanceDao extends Dao {

    /**
     * 根据员工id和月份查询打卡次数
     *
     * @param employeeId
     * @param dateStr    yyyyMM%
     * @return
     */
    int getAttendanceByIdAndDate(int employeeId, String dateStr);

    /**
     * 员工打卡
     *
     * @param userName
     * @param dateStr  yyyyMMdd
     */
    void signInByUserName(String userName, String dateStr);

    /**
     * @param userName
     * @param dateStr  yyyyMMdd
     */
    int getAttendanceByUsernameAndDate(String userName, String dateStr);
}
