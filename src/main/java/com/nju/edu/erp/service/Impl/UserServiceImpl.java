package com.nju.edu.erp.service.Impl;

import com.auth0.jwt.interfaces.Claim;
import com.nju.edu.erp.config.JwtConfig;
import com.nju.edu.erp.dao.UserDao;
import com.nju.edu.erp.enums.Role;
import com.nju.edu.erp.exception.MyServiceException;
import com.nju.edu.erp.model.po.User;
import com.nju.edu.erp.model.vo.UserVO;
import com.nju.edu.erp.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;


@Service
public class UserServiceImpl implements UserService {

    private final UserDao userDao;

    private final JwtConfig jwtConfig;

    @Autowired
    public UserServiceImpl(UserDao userDao, JwtConfig jwtConfig) {
        this.userDao = userDao;
        this.jwtConfig = jwtConfig;
    }


    @Override
    public Map<String, String> login(UserVO userVO) {
        User user = userDao.findByUsernameAndPassword(userVO.getName(), userVO.getPassword());
        if (null == user) {
            throw new MyServiceException("A0000", "用户名或密码错误");
        }
        Map<String, String> authToken = new HashMap<>();
        String token = jwtConfig.createJWT(user);
        authToken.put("token", token);
        return authToken;
    }

    @Override
    public void register(UserVO userVO) {
        User user = userDao.findByUsername(userVO.getName());
        if (user != null) {
            throw new MyServiceException("A0001", "用户名已存在");
        }
        User userSave = new User();
        BeanUtils.copyProperties(userVO, userSave);
        userDao.createUser(userSave);
    }

    @Override
    public UserVO auth(String token) {
        Map<String, Claim> claims = jwtConfig.parseJwt(token);
        return UserVO.builder()
                .name(claims.get("name").as(String.class))
                .role(Role.valueOf(claims.get("role").as(String.class)))
                .build();
    }

    @Override
    public List<String> findAllSalesMan() {
        HashSet<String> set = new HashSet<>();
        List<String> users;
        List<String> roles = new ArrayList<>();
        roles.add(Role.SALE_MANAGER.toString());
        roles.add(Role.SALE_STAFF.toString());
        for (String role : roles) {
            users = userDao.findUserNameByRole(role);
            set.addAll(users);
        }
        return new ArrayList<>(set);
    }

    @Override
    public List<String> findAllUsers() {
        HashSet<String> set = new HashSet<>();
        List<String> users;
        List<String> roles = new ArrayList<>();
        roles.add(Role.INVENTORY_MANAGER.toString());
        roles.add(Role.SALE_STAFF.toString());
        roles.add(Role.FINANCIAL_STAFF.toString());
        roles.add(Role.SALE_MANAGER.toString());
        roles.add(Role.HR.toString());
        roles.add(Role.GM.toString());
        for (String role : roles) {
            users = userDao.findUserNameByRole(role);
            set.addAll(users);
        }
        return new ArrayList<>(set);
    }

    @Override
    public int signIn(String token) {
        Map<String, Claim> claims = jwtConfig.parseJwt(token);
        String name = claims.get("name").as(String.class);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        User user = userDao.findByUsername(name);
        if (sdf.format(user.getLastSignInTime()).equals(sdf.format(new Date())))
            return 0;
        else userDao.signInByUserName(name);
        return 1;
    }
}
