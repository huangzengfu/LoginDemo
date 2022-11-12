package com.huangfu.logindemo.pojo;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class ValidateCode {

    @NotEmpty
    @Size(max = 32, groups = {LoginValidateCode.class, UserAlidateCode.class})
    private String randomCode;

    @NotEmpty
    @Size(max = 6, groups = {UserAlidateCode.class})
    private String validateCode;

    public String getRandomCode() {
        return randomCode;
    }

    public void setRandomCode(String randomCode) {
        this.randomCode = randomCode;
    }

    public String getValidateCode() {
        return validateCode;
    }

    public void setValidateCode(String validateCode) {
        this.validateCode = validateCode;
    }

    public interface LoginValidateCode {
    }

    public interface UserAlidateCode {

    }
}
