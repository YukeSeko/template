package com.example.demo.utils;

import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import cn.hutool.jwt.JWT;
import cn.hutool.jwt.JWTValidator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * token 相关操作工具类
 */
@Component
public class TokenUtils {

    @Value("${jwt.key}")
    private String key;

    /**
     * 生成token，30分钟有效
     * @return
     */
    public String generateToken(){
        return JWT.create().setKey(key.getBytes()).setExpiresAt(DateUtil.offsetMinute(DateUtil.date(),30)).sign();
    }

    /**
     * 验证token是否有效
     * @param token
     * @return
     */
    public boolean verifyTokenIsValid(String token){
        return JWT.of(token).setKey(key.getBytes()).verify();
    }

    /**
     * 检验token是否过期
     * @param token
     * @return
     */
    public boolean verifyTokenIsExpire(String token){
        return JWT.of(token).setKey(key.getBytes()).validate(0);
    }
}
