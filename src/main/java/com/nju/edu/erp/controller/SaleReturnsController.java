package com.nju.edu.erp.controller;

import com.nju.edu.erp.auth.Authorized;
import com.nju.edu.erp.common.Response;
import com.nju.edu.erp.enums.Role;
import com.nju.edu.erp.enums.sheetState.SaleReturnsSheetState;
import com.nju.edu.erp.model.vo.UserVO;
import com.nju.edu.erp.model.vo.saleReturns.SaleReturnsSheetVO;
import com.nju.edu.erp.service.SaleReturnsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/sale-returns")
@Api(tags = "SaleReturnsController")
public class SaleReturnsController {

    private final SaleReturnsService saleReturnsService;

    @Autowired
    public SaleReturnsController(SaleReturnsService saleReturnsService) {
        this.saleReturnsService = saleReturnsService;
    }

    /**
     * 销售人员制定销售退货单
     *
     * @param userVO             操作人员
     * @param saleReturnsSheetVO 退货单
     * @return
     */
    @Authorized(roles = {Role.SALE_STAFF, Role.SALE_MANAGER, Role.GM, Role.ADMIN})
    @PostMapping(value = "sheet-make")
    @ApiOperation("制定销售退货单")
    public Response makeSaleReturnsOrder(
            UserVO userVO,
            @RequestBody SaleReturnsSheetVO saleReturnsSheetVO) {
        saleReturnsService.makeSaleReturnSheet(userVO, saleReturnsSheetVO);
        return Response.buildSuccess();
    }

    /**
     * 销售经理审批
     *
     * @param saleReturnsSheetId 销售退货单ID
     * @param state              修改后的状态(审批失败/待二级审批)
     * @return
     */
    @Authorized(roles = {Role.SALE_MANAGER, Role.GM, Role.ADMIN})
    @GetMapping(value = "/first-approval")
    @ApiOperation("销售经理一级审批")
    public Response firstApproval(
            @RequestParam("saleReturnsSheetId") String saleReturnsSheetId,
            @RequestParam("state") SaleReturnsSheetState state) {
        if (state.equals(SaleReturnsSheetState.FAILURE) || state.equals(SaleReturnsSheetState.PENDING_LEVEL_2)) {
            saleReturnsService.approval(saleReturnsSheetId, state);
            return Response.buildSuccess();
        } else {
            return Response.buildFailed("000000", "操作失败");
        }
    }

    /**
     * 总经理审批
     *
     * @param saleReturnsSheetId 销售退货单ID
     * @param state              修改后的状态(审批失败/审批通过)
     * @return
     */
    @Authorized(roles = {Role.GM, Role.ADMIN})
    @GetMapping(value = "/second-approval")
    @ApiOperation("总经理二级审批")
    public Response secondApproval(
            @RequestParam("saleReturnsSheetId") String saleReturnsSheetId,
            @RequestParam("state") SaleReturnsSheetState state) {
        if (state.equals(SaleReturnsSheetState.FAILURE) || state.equals(SaleReturnsSheetState.SUCCESS)) {
            saleReturnsService.approval(saleReturnsSheetId, state);
            return Response.buildSuccess();
        } else {
            return Response.buildFailed("000000", "操作失败");
        }
    }

    @GetMapping(value = "/sheet-show")
    @ApiOperation("根据状态查询销售退货单")
    public Response showSheetByOrder(@RequestParam(value = "state", required = false) SaleReturnsSheetState state) {
        return Response.buildSuccess(saleReturnsService.getSaleReturnsSheetByState(state));
    }

}
