package com.huangfu.logindemo.service;

import com.huangfu.logindemo.mapper.UserLoginMapper;
import com.huangfu.logindemo.pojo.UserLogin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author HuangFu
 * @create 2022/11/3 20:36
 **/
@Service
public class UserLoginServiceImpl implements UserLoginService{

    @Autowired
    public UserLoginMapper userLoginMapper;

    @Override
    public List<UserLogin> queryAll() {
        return userLoginMapper.queryAll();
    }

    @Override
    public int add(UserLogin userLogin) {
        return this.userLoginMapper.add(userLogin);
    }

    @Override
    public UserLogin queryByName(String username) {
        return this.userLoginMapper.queryByName(username);
    }
}
