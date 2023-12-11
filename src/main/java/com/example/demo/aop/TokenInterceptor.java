package com.example.demo.aop;

import com.example.demo.common.BaseResponse;
import com.example.demo.utils.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Token拦截器
 */
@Component
public class TokenInterceptor implements HandlerInterceptor {

    @Autowired
    private TokenUtils tokenUtils;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("Authorization");
        if (token == null){
            //token 不存在
            BaseResponse baseResponse = new BaseResponse(5000,null,"Token does not exist");
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().println(baseResponse);
            return false;
        }
        if (!tokenUtils.verifyTokenIsValid(token)){
            //token 非法
            BaseResponse baseResponse = new BaseResponse(50008,null,"Illegal token");
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().println(baseResponse);
            return false;
        }
        if (!tokenUtils.verifyTokenIsExpire(token)){
            //token 过期
            BaseResponse baseResponse = new BaseResponse(50014,null,"Token expired");
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().println(baseResponse);
            return false;
        }
        return true;
    }
}
