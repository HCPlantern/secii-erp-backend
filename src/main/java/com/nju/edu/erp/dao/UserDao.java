package com.nju.edu.erp.dao;


import com.nju.edu.erp.model.po.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface UserDao extends Dao {

    User findByUsernameAndPassword(String username, String password);

    int createUser(User user);

    User findByUsername(String username);

    List<String> findUserNameByRole(String role);

    void signInByUserName(String name);
}
