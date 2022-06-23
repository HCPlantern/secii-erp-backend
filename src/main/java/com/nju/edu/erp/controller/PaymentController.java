package com.nju.edu.erp.controller;

import com.nju.edu.erp.common.Response;
import com.nju.edu.erp.enums.sheetState.CollectionSheetState;
import com.nju.edu.erp.enums.sheetState.PaymentSheetState;
import com.nju.edu.erp.model.vo.PaymentSheetVO;
import com.nju.edu.erp.model.vo.UserVO;
import com.nju.edu.erp.service.PaymentService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payment")
public class PaymentController {
    private final PaymentService paymentService;

    @Autowired
    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping("/payment-sheet-make")
    @ApiOperation("制定付款单")
    public Response makePaymentSheet(UserVO userVO, @RequestBody PaymentSheetVO paymentSheetVO){
        paymentService.makePaymentSheet(userVO,paymentSheetVO);
        return Response.buildSuccess();
    }

    @GetMapping("/approve-payment-sheet")
    @ApiOperation("审批付款单")
    public Response approvePaymentSheet(@RequestParam String paymentSheetId, @RequestParam PaymentSheetState state){
        if(state.equals(PaymentSheetState.FAILURE) || state.equals(PaymentSheetState.SUCCESS)){
            paymentService.approval(paymentSheetId,state);
            return Response.buildSuccess();
        }else{
            return Response.buildFailed("000000","操作失败");
        }
    }

    @GetMapping("/sheet-show")
    public Response findAllPaymentSheetByState(@RequestParam(required = false) PaymentSheetState state){
        return Response.buildSuccess(paymentService.findAllPaymentSheetByState(state));
    }

}
