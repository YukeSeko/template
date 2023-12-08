package com.example.demo.config;

import com.example.demo.aop.TokenInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Arrays;
import java.util.List;

/**
 * 注册拦截器
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {


    /**
     * 放行接口
     */
    private List<String> pathPatterns = Arrays.asList("/user/register","/user/login","/user/logout","/user/getLoginUser");

    /**
     * 放行静态资源
     */
    private List<String> staticPath = Arrays.asList("/charging/**","/swagger-ui.html", "/swagger-ui/*", "/swagger-resources/**", "/v2/api-docs", "/v3/api-docs", "/webjars/**","/doc.html");

    @Autowired
    private TokenInterceptor tokenInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(tokenInterceptor)
                .excludePathPatterns(pathPatterns)
                .excludePathPatterns(staticPath);
    }
}
