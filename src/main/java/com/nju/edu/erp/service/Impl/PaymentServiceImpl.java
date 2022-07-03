package com.nju.edu.erp.service.Impl;

import com.nju.edu.erp.dao.CompanyAccountDao;
import com.nju.edu.erp.dao.CustomerDao;
import com.nju.edu.erp.dao.PaymentSheetDao;
import com.nju.edu.erp.enums.BaseEnum;
import com.nju.edu.erp.enums.sheetState.PaymentSheetState;
import com.nju.edu.erp.model.po.CustomerPO;
import com.nju.edu.erp.model.po.PaymentSheetContentPO;
import com.nju.edu.erp.model.po.PaymentSheetPO;
import com.nju.edu.erp.model.vo.payment.PaymentSheetContentVO;
import com.nju.edu.erp.model.vo.payment.PaymentSheetVO;
import com.nju.edu.erp.model.vo.UserVO;
import com.nju.edu.erp.service.PaymentService;
import com.nju.edu.erp.utils.IdGenerator;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
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
        PaymentSheetPO latest=paymentSheetDao.findLatest();
        String id= IdGenerator.generateSheetId(latest == null ? null : latest.getId(),"XJFKD");
        paymentSheetPO.setId(id);
        paymentSheetPO.setCreateTime(new Date());
        paymentSheetPO.setState(PaymentSheetState.PENDING);
        List<PaymentSheetContentPO> paymentSheetContentPOS=new ArrayList<>();
        // 收款单内容
        List<PaymentSheetContentVO> paymentSheetContentVOS=paymentSheetVO.getPaymentSheetContentVOS();
        BigDecimal totalAmount= BigDecimal.valueOf(0);
        for (PaymentSheetContentVO paymentSheetContentVO:paymentSheetContentVOS){
            PaymentSheetContentPO paymentSheetContentPO=new PaymentSheetContentPO();
            BeanUtils.copyProperties(paymentSheetContentVO,paymentSheetContentPO);
            paymentSheetContentPO.setPaymentSheetId(id);
            // 防御式编程 转账金额不能够小于0
            assert paymentSheetContentVO.getTransferAmount().compareTo(BigDecimal.ZERO)>=0:"错误! 转账金额不能够小于0";

            totalAmount=totalAmount.add(paymentSheetContentVO.getTransferAmount());
            paymentSheetContentPOS.add(paymentSheetContentPO);
        }
        // 防御式编程 付款单金额不能够大于客户应收金额
        Integer customerId=paymentSheetVO.getCustomer();
        CustomerPO relevantCustomer=customerDao.findOneById(customerId);
        BigDecimal receivableAmount=relevantCustomer.getReceivable();
        assert totalAmount.compareTo(receivableAmount)<=0:"付款单金额不能够大于客户应收金额!";

        paymentSheetPO.setTotalAmount(totalAmount);
        paymentSheetDao.savePaymentSheetContent(paymentSheetContentPOS);
        paymentSheetDao.savePaymentSheet(paymentSheetPO);
    }

    @Override
    public void approval(String paymentSheetId, BaseEnum state) {
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
                customerDao.updateReceivableById(customerId,paymentSheetContentPO.getTransferAmount());
            }
        }
    }

    @Override
    public List<PaymentSheetVO> findAllPaymentSheetByState(PaymentSheetState paymentSheetState) {
        List<PaymentSheetPO> paymentSheetPOS;
        List<PaymentSheetVO> paymentSheetVOS=new ArrayList<>();
        if(paymentSheetState==null){
            paymentSheetPOS=paymentSheetDao.findAllPaymentSheet();
        }else {
            paymentSheetPOS=paymentSheetDao.findAllPaymentSheetByState(paymentSheetState);
        }
        for(PaymentSheetPO paymentSheetPO:paymentSheetPOS){
            paymentSheetVOS.add(getVOFromPO(paymentSheetPO));
        }
        return paymentSheetVOS;
    }

    public PaymentSheetVO findPaymentSheetById(String id) {
        PaymentSheetPO paymentSheetPO = paymentSheetDao.findPaymentSheetById(id);
        return getVOFromPO(paymentSheetPO);
    }

    private PaymentSheetVO getVOFromPO(PaymentSheetPO po) {
        PaymentSheetVO vo = new PaymentSheetVO();
        BeanUtils.copyProperties(po, vo);
        List<PaymentSheetContentPO> pscPOList = paymentSheetDao.findAllPaymentSheetContentById(po.getId());
        List<PaymentSheetContentVO> pscVOList = new ArrayList<>();
        for (PaymentSheetContentPO pscPO : pscPOList) {
            PaymentSheetContentVO pscVO = new PaymentSheetContentVO();
            BeanUtils.copyProperties(pscPO, pscVO);
            pscVOList.add(pscVO);
        }
        vo.setPaymentSheetContentVOS(pscVOList);
        return vo;
    }
}
