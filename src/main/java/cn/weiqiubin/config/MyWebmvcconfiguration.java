package cn.weiqiubin.config;

import cn.weiqiubin.interceptor.HanlderTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;


@Configuration
public class MyWebmvcconfiguration implements WebMvcConfigurer {
    @Autowired
    private HanlderTest hanlderTest;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(hanlderTest).addPathPatterns("/**");
    }

    /**
     * 静态资源路径映射
     * http://localhost:8080/my/log/logback-spring.xml
     * @param registry
     */
//    @Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        registry.addResourceHandler("/my/**").addResourceLocations("classpath:/");
//    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/login").setViewName("login");
        registry.addViewController("/main.html").setViewName("dashboard");
    }


}
