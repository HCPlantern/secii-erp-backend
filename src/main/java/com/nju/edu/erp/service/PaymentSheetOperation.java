package com.nju.edu.erp.service;

import com.nju.edu.erp.enums.sheetState.PaymentSheetState;
import com.nju.edu.erp.model.vo.payment.PaymentSheetVO;
import com.nju.edu.erp.model.vo.UserVO;

import java.util.List;

public interface PaymentSheetOperation extends CommonSheetOperation{
    public void makePaymentSheet(UserVO userVO, PaymentSheetVO paymentSheetVO);

    public List<PaymentSheetVO> findAllPaymentSheetByState(PaymentSheetState paymentSheetState);

}
