package com.nju.edu.erp.service;

import com.nju.edu.erp.enums.BaseEnum;
import com.nju.edu.erp.enums.sheetState.PaymentSheetState;
import com.nju.edu.erp.model.po.PaymentSheetPO;
import com.nju.edu.erp.model.vo.PaymentSheetVO;
import com.nju.edu.erp.model.vo.UserVO;

import java.util.List;

public interface PaymentService extends PaymentSheetOperation{
    public void makePaymentSheet(UserVO userVO, PaymentSheetVO paymentSheetVO);
    public void approval(String paymentSheetId, BaseEnum state);

    public List<PaymentSheetVO> findAllPaymentSheetByState(PaymentSheetState paymentSheetState);
}
