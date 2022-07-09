package com.nju.edu.erp.controller;

import com.nju.edu.erp.auth.Authorized;
import com.nju.edu.erp.common.Response;
import com.nju.edu.erp.enums.Role;
import com.nju.edu.erp.model.vo.PromotionVO;
import com.nju.edu.erp.service.PromotionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author hua
 */
@RestController
@RequestMapping(path = "/promotion")
@Api(tags = "PromotionController")
public class PromotionController {
  private final PromotionService promotionService;

  @Autowired
  PromotionController(PromotionService promotionService){this.promotionService = promotionService;}

  @PostMapping(path = "/createPromotionStrategy")
  @Authorized(roles = {Role.GM})
  @ApiOperation(value = "创建促销规则")
  public Response createPromotionStrategy(@RequestBody PromotionVO promotionStrategyForm) {
    promotionService.addPromotionStrategy(promotionStrategyForm);
    return Response.buildSuccess();
  }
  @GetMapping(path = "/findPromotionStrategyByTime")
  @Authorized(roles = {Role.GM})
  @ApiOperation(value = "根据时间查询促销规则")
  public Response findPromotionStrategyByTime(@Param("beginTime") String beginTime, @Param("endTime") String endTime) {

    return Response.buildSuccess(promotionService.getPromotionStrategyByTime(beginTime, endTime));
  }

  @GetMapping(path = "/getAllPromotionStrategy")
  public Response getAllPromotionStrategy(){

    return Response.buildSuccess();
  }

}
