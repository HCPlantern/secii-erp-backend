package com.nju.edu.erp.dao;

import com.nju.edu.erp.enums.BaseEnum;
import com.nju.edu.erp.model.po.PaymentSheetContentPO;
import com.nju.edu.erp.model.po.PaymentSheetPO;
import com.nju.edu.erp.model.po.SheetPO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface PaymentSheetDao extends Dao {

    int savePaymentSheet(PaymentSheetPO paymentSheetPO);

    int savePaymentSheetContent(List<PaymentSheetContentPO> paymentSheetContentPOS);

    PaymentSheetPO findPaymentSheetById(String id);

    List<PaymentSheetPO> findAllPaymentSheet();

    int updateStateById(String id, BaseEnum state);

    List<PaymentSheetContentPO> findAllPaymentSheetContentById(String id);

    PaymentSheetPO findLatest();

    List<PaymentSheetPO> findAllPaymentSheetByState(BaseEnum state);

    List<SheetPO> findAllBasicSheetInfo(@Param("beginTime") String beginTime, @Param("endTime") String endTime);
}
