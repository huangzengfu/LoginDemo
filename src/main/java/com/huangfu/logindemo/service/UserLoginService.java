package com.huangfu.logindemo.service;

import com.huangfu.logindemo.mapper.UserLoginMapper;
import com.huangfu.logindemo.pojo.UserLogin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author HuangFu
 * @create 2022/10/16 16:37
 **/

public interface UserLoginService {

    List<UserLogin> queryAll();

    int add(UserLogin userLogin);

    UserLogin queryByName(String username);


}
