package com.zhangongjx;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author: wanghaojie
 * @description：
 * @date: 2018/11/3 16:58
 */
//@Configuration
public class WebMvcConfiguration implements WebMvcConfigurer {
//    private Logger logger = LoggerFactory.getLogger(getClass());

//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        logger.info("add interceptors");
//        //这里可以添加自定义的拦截器
//        registry.addInterceptor(new LoginRequiredInterceptor());
//    }
//    @Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        logger.info("add interceptors");
//        registry.addResourceHandler("/resources/static/**").addResourceLocations("classpath:/resources/static/");
//    }

}
