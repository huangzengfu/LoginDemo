package com.huangfu.logindemo.controller;

import com.huangfu.logindemo.constant.Constant;
import com.huangfu.logindemo.pojo.ValidateCode;
import com.huangfu.logindemo.utils.KaptchaUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author HuangFu
 * @create 2022/11/9 20:49
 **/
@Controller
@RequestMapping("/sessionController")
public class SessionController {

    @Autowired
    public KaptchaUtil kaptchaUtil;

    public void loginValidateCode(@RequestBody @Validated(ValidateCode.LoginValidateCode.class)ValidateCode validateCode, HttpServletRequest request, HttpServletResponse response)throws Exception{
        kaptchaUtil.creatValidateCode(request, response, Constant.LOGIN_VALIDATE_CODE + validateCode.getRandomCode());
    }
}
