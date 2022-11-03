package com.huangfu.logindemo.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author HuangFu
 * @create 2022/10/16 16:37
 **/

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserLogin {
    private String username;
    private String password;

    public String getUsername(){
        return username;
    }

}
