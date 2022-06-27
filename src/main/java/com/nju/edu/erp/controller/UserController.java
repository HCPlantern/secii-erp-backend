package com.nju.edu.erp.controller;

import com.nju.edu.erp.config.JwtConfig;
import com.nju.edu.erp.dao.UserDao;
import com.nju.edu.erp.model.vo.UserVO;
import com.nju.edu.erp.service.UserService;
import com.nju.edu.erp.common.Response;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(path = "/user")
@Api(tags = "UserController")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    @ApiOperation("用户登录")
    public Response userLogin(@RequestBody UserVO userVO) {
        return Response.buildSuccess(userService.login(userVO));
    }

    @PostMapping("/register")
    @ApiOperation("用户注册")
    public Response userRegister(@RequestBody UserVO userVO) {
        userService.register(userVO);
        return Response.buildSuccess();
    }

    @GetMapping("/auth")
    @ApiOperation("用户验证")
    public Response userAuth(@RequestParam(name = "token") String token) {
        return Response.buildSuccess(userService.auth(token));
    }

    @GetMapping("/findAllSalesMan")
    @ApiOperation("查询用户")
    public Response findAllSalesMan() {
        return Response.buildSuccess(userService.findAllSalesMan());
    }

    @GetMapping("/find-all-users")
    @ApiOperation("查询所有用户")
    public Response findAllUsers() {
        return Response.buildSuccess(userService.findAllUsers());
    }


}
