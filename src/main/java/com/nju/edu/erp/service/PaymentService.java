package com.nju.edu.erp.service;

import com.nju.edu.erp.enums.BaseEnum;
import com.nju.edu.erp.enums.sheetState.PaymentSheetState;
import com.nju.edu.erp.model.vo.payment.PaymentSheetVO;
import com.nju.edu.erp.model.vo.UserVO;

import java.util.List;

public interface PaymentService extends PaymentSheetOperation{
    void makePaymentSheet(UserVO userVO, PaymentSheetVO paymentSheetVO);
    void approval(String paymentSheetId, BaseEnum state);

    List<PaymentSheetVO> findAllPaymentSheetByState(PaymentSheetState paymentSheetState);

    PaymentSheetVO findPaymentSheetById(String id);
}
