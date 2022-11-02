package com.huangfu.logindemo.dao;

import com.huangfu.logindemo.domain.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @author HuangFu
 * @create 2022/10/16 16:41
 **/

public interface UserDao {
    User select(User user);
}
