package com.huangfu.logindemo.service;

import com.huangfu.logindemo.dao.UserDao;
import com.huangfu.logindemo.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author HuangFu
 * @create 2022/10/16 16:37
 **/
@Service
public class UserService {
    @Autowired
    public UserDao userDao;

    public User getUser(User user){
        return this.userDao.select(user);
    }
}
