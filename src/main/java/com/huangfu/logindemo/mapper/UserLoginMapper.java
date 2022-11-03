package com.huangfu.logindemo.mapper;

import com.huangfu.logindemo.pojo.UserLogin;
import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author HuangFu
 * @create 2022/10/16 16:41
 **/

@Mapper
@Repository
public interface UserLoginMapper {

    List<UserLogin> queryAll();

    int add(UserLogin userLogin);

    UserLogin queryByName(String username);
}
