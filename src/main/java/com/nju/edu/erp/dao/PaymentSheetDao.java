package com.nju.edu.erp.dao;

import com.nju.edu.erp.enums.BaseEnum;
import com.nju.edu.erp.enums.sheetState.PaymentSheetState;
import com.nju.edu.erp.model.po.PaymentSheetContentPO;
import com.nju.edu.erp.model.po.PaymentSheetPO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface PaymentSheetDao {

    public int savePaymentSheet(PaymentSheetPO paymentSheetPO);

    public int savePaymentSheetContent(List<PaymentSheetContentPO> paymentSheetContentPOS);

    public PaymentSheetPO findPaymentSheetById(String id);

    public List<PaymentSheetPO> findAllPaymentSheet();

    public int updateStateById(String id, BaseEnum state);

    public List<PaymentSheetContentPO> findAllPaymentSheetContentById(String id);

    public PaymentSheetPO findLatest();

    public List<PaymentSheetPO> findAllPaymentSheetByState(BaseEnum state);
}
