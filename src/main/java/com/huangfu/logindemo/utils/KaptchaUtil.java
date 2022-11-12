package com.huangfu.logindemo.utils;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.util.concurrent.TimeUnit;

@Component
public class KaptchaUtil {
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private DefaultKaptcha captchaProducer;

    public void creatValidateCode(HttpServletRequest request, HttpServletResponse response, String validateSessionKey) throws Exception {
        // Set to expire far in the past.
        response.setDateHeader("Expires", 0);
        // Set standard HTTP/1.1 no-cache headers.
        response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
        // Set IE extended HTTP/1.1 no-cache headers (use addHeader).
        response.addHeader("Cache-Control", "post-check=0, pre-check=0");
        // Set standard HTTP/1.0 no-cache header.
        response.setHeader("Pragma", "no-cache");

        // return a jpeg
        response.setContentType("image/jpeg");

        // create the text for the image
        String capText = captchaProducer.createText();

        this.redisTemplate.opsForValue().set(validateSessionKey, capText, 5, TimeUnit.MINUTES);

        // create the image with the text
        BufferedImage bi = captchaProducer.createImage(capText);

        ServletOutputStream out = response.getOutputStream();

        // write the data out
        ImageIO.write(bi, "jpg", out);
        try {
            out.flush();
        } finally {
            out.close();
        }
    }

    public boolean validateCode(String validateSessionKey, String validateCode) {
        Object code = this.redisTemplate.opsForValue().get(validateSessionKey);
        if (code == null || !validateCode.equalsIgnoreCase(code.toString())) {
            return false;
        }
        return true;
    }
}
