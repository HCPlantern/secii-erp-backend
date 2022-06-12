package com.nju.edu.erp.service.Impl;

import com.nju.edu.erp.dao.CompanyAccountDao;
import com.nju.edu.erp.dao.CustomerDao;
import com.nju.edu.erp.dao.PaymentSheetDao;
import com.nju.edu.erp.enums.sheetState.PaymentSheetState;
import com.nju.edu.erp.model.po.PaymentSheetContentPO;
import com.nju.edu.erp.model.po.PaymentSheetPO;
import com.nju.edu.erp.model.vo.PaymentSheetContentVO;
import com.nju.edu.erp.model.vo.PaymentSheetVO;
import com.nju.edu.erp.model.vo.UserVO;
import com.nju.edu.erp.service.PaymentService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class PaymentServiceImpl implements PaymentService {

    private final PaymentSheetDao paymentSheetDao;

    private final CompanyAccountDao companyAccountDao;

    private final CustomerDao customerDao;

    @Autowired
    public PaymentServiceImpl(PaymentSheetDao paymentSheetDao, CompanyAccountDao companyAccountDao, CustomerDao customerDao) {
        this.paymentSheetDao = paymentSheetDao;
        this.companyAccountDao = companyAccountDao;
        this.customerDao = customerDao;
    }

    /**
     * 制定付款单
     * @param userVO
     * @param paymentSheetVO
     */
    @Override
    public void makePaymentSheet(UserVO userVO, PaymentSheetVO paymentSheetVO) {
        PaymentSheetPO paymentSheetPO=new PaymentSheetPO();
        BeanUtils.copyProperties(paymentSheetVO,paymentSheetPO);
        paymentSheetPO.setState(PaymentSheetState.PENDING);
        List<PaymentSheetContentPO> paymentSheetContentPOS=new ArrayList<>();
        // 收款单内容
        List<PaymentSheetContentVO> paymentSheetContentVOS=paymentSheetVO.getPaymentSheetContentVOS();
        BigDecimal totalAmount= BigDecimal.valueOf(0);
        for (PaymentSheetContentVO paymentSheetContentVO:paymentSheetContentVOS){
            PaymentSheetContentPO paymentSheetContentPO=new PaymentSheetContentPO();
            BeanUtils.copyProperties(paymentSheetContentVO,paymentSheetContentPO);
            totalAmount=totalAmount.add(paymentSheetContentVO.getTransferAmount());
            paymentSheetContentPOS.add(paymentSheetContentPO);
        }
        paymentSheetPO.setTotalAmount(totalAmount);
        paymentSheetDao.savePaymentSheetContent(paymentSheetContentPOS);
        paymentSheetDao.savePaymentSheet(paymentSheetPO);
    }

    @Override
    public void approval(String paymentSheetId, PaymentSheetState state) {
        PaymentSheetPO paymentSheetPO=paymentSheetDao.findPaymentSheetById(paymentSheetId);
        Integer customerId=paymentSheetPO.getCustomer();
        if(state.equals(PaymentSheetState.FAILURE)){
            if(paymentSheetPO.getState().equals(PaymentSheetState.SUCCESS)){
                throw new RuntimeException("状态更新失败");
            }
        }
        int effectedLines=paymentSheetDao.updateStateById(paymentSheetId,state);
        if (effectedLines == 0) {
            throw new RuntimeException("状态更新失败");
        }
        // 状态更新成功
        if(state.equals(PaymentSheetState.SUCCESS)){
            List<PaymentSheetContentPO> paymentSheetContentPOS=paymentSheetDao.findAllPaymentSheetContentById(paymentSheetId);
            for(PaymentSheetContentPO paymentSheetContentPO:paymentSheetContentPOS){
                companyAccountDao.paymentUpdateCompanyAccountAmountById(paymentSheetContentPO.getCompanyAccountId(),paymentSheetContentPO.getTransferAmount());
                customerDao.updatePayableById(customerId,paymentSheetContentPO.getTransferAmount());
            }
        }
    }
}
