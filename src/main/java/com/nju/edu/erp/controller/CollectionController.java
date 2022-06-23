package com.nju.edu.erp.controller;

import com.nju.edu.erp.common.Response;
import com.nju.edu.erp.enums.sheetState.CollectionSheetState;
import com.nju.edu.erp.model.po.User;
import com.nju.edu.erp.model.vo.CollectionSheetVO;
import com.nju.edu.erp.model.vo.UserVO;
import com.nju.edu.erp.service.CollectionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.jws.soap.SOAPBinding;

// 这个是置顶收款单
@RestController
@RequestMapping("/collection")
@Api(tags = "CollectionController")
public class CollectionController {
    private final CollectionService collectionService;

    @Autowired
    public CollectionController(CollectionService collectionService) {
        this.collectionService = collectionService;
    }


    @PostMapping("/collection-sheet-make")
    @ApiOperation("制定收款单")
    public Response makeCollectionSheet(UserVO userVO,@RequestBody CollectionSheetVO collectionSheetVO){
        collectionService.makeCollectionSheet(userVO,collectionSheetVO);
        return Response.buildSuccess();
    }

    @GetMapping("/approve-collection-sheet")
    @ApiOperation("审批收款单")
    public Response approveCollectionSheet(@RequestParam String collectionSheetId, @RequestParam CollectionSheetState state){
        if(state.equals(CollectionSheetState.SUCCESS) || state.equals(CollectionSheetState.FAILURE)){
            collectionService.approval(collectionSheetId,state);
            return Response.buildSuccess();
        }else {
            return Response.buildFailed("000000","操作失败");
        }
    }

    @GetMapping("/sheet-show")
    public Response findAllCollectionSheetByState(@RequestParam(required = false) CollectionSheetState state){
        return Response.buildSuccess(collectionService.findAllCollectionSheetByState(state));
    }
}
