package com.example.errand.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author : 陈宇凡
 * @date : 2021/11/27
 **/
@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Bean
    public WebMvcConfigurer corsConfigurer(){
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**").
                        allowedOriginPatterns("*").//允许跨域的域名可以用*表示允许任何域名使用
                        allowedMethods("*").//允许任何方法（post or get ）
                        allowedHeaders("*").//允许任何请求头
                        allowCredentials(true).//带上cookie信息
                        exposedHeaders(HttpHeaders.SET_COOKIE).maxAge(3600L);//3600秒内
                //WebMvcConfigurer.super.addCorsMappings(registry);
            }
        };
    }
}
