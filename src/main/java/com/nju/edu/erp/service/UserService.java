package com.nju.edu.erp.service;

import com.nju.edu.erp.model.vo.UserVO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface UserService {

    /**
     * 用户登录
     *
     * @param userVO 用户信息
     * @return token
     */
    Map<String, String> login(UserVO userVO);

    /**
     * 用户注册
     *
     * @param userVO 用户信息
     */
    void register(UserVO userVO);

    /**
     * 用户认证
     *
     * @param token token
     */
    UserVO auth(String token);

    List<String> findAllSalesMan();

    List<String> findAllUsers();
}
