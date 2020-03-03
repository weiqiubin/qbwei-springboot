package cn.weiqiubin.Entity;

import cn.weiqiubin.controller.Helloworld;
import cn.weiqiubin.domain.User;
import cn.weiqiubin.mapper.UserMapper;
import cn.weiqiubin.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.http.HttpProperties;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.ApplicationContext;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.test.context.junit4.SpringRunner;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Set;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class springbootTest {

    @Autowired
    private Useryml useryml;

    @Autowired
    private Student student;



    @Autowired
    private HttpProperties httpProperties;

    @Autowired
    private WebMvcProperties webMvcProperties;
    @Test
    public void test1(){
        System.out.println(useryml);
        System.out.println(student);
        HttpProperties.Encoding encoding = httpProperties.getEncoding();
        System.out.println(encoding.getCharset());
    }

    @Test
    public void testLogging(){
        Logger logger = LoggerFactory.getLogger(Helloworld.class);
        // 级别由低到高 trace<debug<info<warn<error
        logger.trace("这是一个trace日志...");
        logger.debug("这是一个debug日志...");
        // SpringBoot默认是info级别，只会输出info及以上级别的日志
        logger.info("这是一个info日志...");
        logger.warn("这是一个warn日志...");
        logger.error("这是一个error日志...");
        log.info("lomok");
    }
    @Test
    public void testSpringmvc(){
        System.out.println(webMvcProperties.getServlet().getPath());
    }

    @Autowired
    ApplicationContext applicationContext;
    @Test
    public void test01(){
        System.out.println(applicationContext.getClass().getName());
    }

    @Autowired
    private UserService userService;
    @Test
    public void test02(){
        System.out.println(userService.selById(3));
    }

    @Autowired
    @Qualifier("redisTemplate")
    private RedisTemplate redisTemplate;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    @Qualifier("userredisTemplate")
    private  RedisTemplate userredisTemplate;
    @Test
    public void testredis(){
//        System.out.println(stringRedisTemplate.opsForValue().get("db0::cn.weiqiubin.service.UserService.selById[3]"));
//        System.out.println(redisTemplate.hasKey("db0::cn.weiqiubin.service.UserService.selById[3]"));
        User user = userService.selById(3);
        userredisTemplate.opsForValue().set("my0",user);
//        Set keys = userredisTemplate.keys("db0:*");
//        Iterator iterator = keys.iterator();
//        while (iterator.hasNext()) {
//            System.out.println(iterator.next());
//        }
        String my0 = userredisTemplate.opsForValue().get("my0").getClass().getName();

        Set keys = userredisTemplate.keys("*");
        Iterator iterator = keys.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
        System.out.println(my0);


    }

    @Autowired
    private JavaMailSenderImpl javaMailSender;
    @Test
    public void testmail(){
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setSubject("通知");
        simpleMailMessage.setText("内容");
        simpleMailMessage.setTo("15815317370@163.com");
        simpleMailMessage.setFrom("1782919642@qq.com");
        javaMailSender.send(simpleMailMessage);
    }

    @Test
    public void testmail2() throws MessagingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage,true);


    }


}


