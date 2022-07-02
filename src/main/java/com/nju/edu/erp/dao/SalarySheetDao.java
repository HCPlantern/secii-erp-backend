package com.nju.edu.erp.dao;

import com.nju.edu.erp.model.po.SalarySheetPO;
import com.nju.edu.erp.model.po.SheetPO;
import com.nju.edu.erp.model.vo.sheet.SheetVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface SalarySheetDao extends Dao {
    List<SalarySheetPO> getSalarySheetByTime(@Param("startTime") String startTime, @Param("endTime") String endTime);

    SalarySheetPO getSalarySheetById(Integer id);

    List<SheetPO> findAllBasicSheetInfo(@Param("startTime") String startTime, @Param("endTime") String endTime);
}
