package com.huangfu.logindemo.constant;


import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author zhaolm
 * @data 2019/1/28
 */
public class Constant {

    public static final String CONTEXT_PATH = "/userSrv";

    /**
     * 否、无效、异常、禁用、关闭
     */
    public static final int IS_NO = 0;

    /**
     * 是、有效、正常、启用
     */
    public static final int IS_YES = 1;

    /**
     * type == 2
     */
    public static final int TYPE_GROUP = 2;

    /**
     * type == 3
     */
    public static final int TYPE_PERMISSION = 3;

    /**
     * Jwt盐值
     */
    public static final String SALT = "qianxinJowto";

    public static final String UNIQUE_LOGIN_SALT = "!@#qIANxiNjoWto321";

    /**
     * 超级管理员账号
     */
    public static final String SUPERADMIN_USERNAME = "superadmin";

    /**
     * 私钥存放时间
     */
    public static final int PRIVATE_EXPIRE = 30;

    public static final String SUB_ACCOUNT_PERMISSION_CODE = "58";

    public static final String LOGIN_VALIDATE_CODE = "login_validate_code_";

    //云中心地址
    public static final String CLOUD_CENTER = "cloud_center";
    //芹菜地址
    public static final String SCAN_CENTER = "scan_center";
    //代理地址
    public static final String PROXY_SERVER = "proxy_server";

    public static final String QINCAI_SERVER_URL = "http://qincai-server";
    public static final String QINCAI_FILE_UPLOAD_URL = "http://qincai-server/file/upload?dir=";
    public static final String INSTALL_PACKAGE_URL = "installPkg";

    public static final String INSTALL_PACKAGE_CATALOG = "/file/dl/";

    public static final String LOGIN_KEY = "login_key";


    public static final String UPGRADE_PACKAGE_URL = "/app/nginx/html/webapps/agentupdate";

    //会话超时时间 分钟
    public static int SESSION_TIMEOUT = 0;

    /**
     * 业务用户类型
     */
    public static final String USER_TYPE_BUSINESS = "business";

    /**
     * 后台用户类型
     */
    public static final String USER_TYPE_BACKGROUND = "background";

    /**
     * 登陆页面
     */
    //public static final String LOGIN_PAGE = "loginPage";
    /**
     * 手机令牌页面
     */
    //public static final String MOBILE_PHONE_TOKEN_PAGE = "MobilePhoneTokenPage";
    /**
     * 从哪个页面进行的双因子绑定
     */
    //public static List<String> SOURCE_LIST = List.of(LOGIN_PAGE, MOBILE_PHONE_TOKEN_PAGE);


    public static List<String> CONTAINER_SYSTEM_MENU = Arrays.asList("10d7172471674ca49ed4492ea288d3e0","2703758ddcd247eda0c61ad09d8e1a88","a7258d23985a495aa6f9277f451f03e5","ae15fc41a0e44970ab8194c4ff0e7ac2");

    public static List<String> CONTAINER_SECURE_MENU = Arrays.asList("0abeea418894401784a70445ee3124ea","0fc7769c723c4fd1ba7c6c4157d877c2","1aa31b1f2e0c43758cd37a13ba66dfd0","22083109ed9241f2a5506e97b46314ab","efcabc0dae424f71b6a7d5cae375225f","5a5f00e4bc9e4d438d9f03aabcc57865");

    /**
     * 给前端返回base64编码时，要拼接上前缀串，前端才能解析成图片
     */
    public static final String BASE64_IMAGE_PREFIX = "data:image/png;base64,";
}
