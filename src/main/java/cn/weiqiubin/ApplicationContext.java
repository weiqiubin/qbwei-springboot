package cn.weiqiubin;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;


@SpringBootApplication
@MapperScan(value = "cn.weiqiubin.mapper")
@EnableCaching
@EnableAsync
@EnableScheduling
public class ApplicationContext {
    public static void main(String[] args) {
        //注释master分支
        SpringApplication.run(ApplicationContext.class,args);

    }
}
