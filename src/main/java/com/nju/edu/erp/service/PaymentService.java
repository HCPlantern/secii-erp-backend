package com.nju.edu.erp.service;

import com.nju.edu.erp.enums.sheetState.PaymentSheetState;
import com.nju.edu.erp.model.vo.PaymentSheetVO;
import com.nju.edu.erp.model.vo.UserVO;

public interface PaymentService {
    public void makePaymentSheet(UserVO userVO, PaymentSheetVO paymentSheetVO);
    public void approval(String paymentSheetId, PaymentSheetState state);





}
