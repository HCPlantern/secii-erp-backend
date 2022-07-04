package com.nju.edu.erp.dao;

import com.nju.edu.erp.enums.sheetState.SalarySheetState;
import com.nju.edu.erp.model.po.SalarySheetPO;
import com.nju.edu.erp.model.po.SheetPO;
import com.nju.edu.erp.model.vo.sheet.SheetVO;
import jdk.nashorn.internal.runtime.ListAdapter;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface SalarySheetDao extends Dao {
    void createSalarySheet(SalarySheetPO salarySheetPO);

    List<SalarySheetPO> getSalarySheetByTime(@Param("startTime") String startTime, @Param("endTime") String endTime);

    SalarySheetPO getSalarySheetById(Integer id);

    List<SheetPO> findAllBasicSheetInfo(@Param("startTime") String startTime, @Param("endTime") String endTime);

    List<SalarySheetPO> getSalarySheetByState(SalarySheetState state);

    List<SalarySheetPO> getAllSalarySheet();
}
