package cn.weiqiubin.config;

import cn.weiqiubin.Entity.Student;
import cn.weiqiubin.LocaleResolver.MyLocale;
import cn.weiqiubin.Servlet.Myservlet;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.impl.WxMpServiceImpl;
import me.chanjar.weixin.mp.config.WxMpConfigStorage;
import me.chanjar.weixin.mp.config.impl.WxMpDefaultConfigImpl;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.web.servlet.LocaleResolver;

import javax.servlet.Servlet;
import java.lang.reflect.Method;
import java.net.UnknownHostException;
import java.time.Duration;
import java.util.Arrays;

@Configuration
public class Myconfig extends CachingConfigurerSupport {

    @Bean
    public Student student(){
        Student student = new Student();
        return student;
    }

    @Bean
    public LocaleResolver localeResolver(){
        MyLocale myLocale = new MyLocale();
        return myLocale;
    }

    @Bean
    public ServletRegistrationBean servletRegistrationBean(){
        ServletRegistrationBean<Servlet> servletServletRegistrationBean = new ServletRegistrationBean<>();
        servletServletRegistrationBean.addUrlMappings("/myservlet");
        servletServletRegistrationBean.setServlet(new Myservlet());
        return servletServletRegistrationBean;
    }

    /**
     * 使用RedisTemplate操作缓存
     * @param redisConnectionFactory
     * @return
     * @throws UnknownHostException
     */
    @Bean
    public RedisTemplate<Object, Object> userredisTemplate(
            RedisConnectionFactory redisConnectionFactory)
            throws UnknownHostException {
        RedisTemplate<Object, Object> template = new RedisTemplate<Object, Object>();
        template.setConnectionFactory(redisConnectionFactory);
        //修改默认的序列化为json
        Jackson2JsonRedisSerializer<Object> serializer = new Jackson2JsonRedisSerializer<>(Object.class);
        //反序列化
        ObjectMapper om = new ObjectMapper();
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        serializer.setObjectMapper(om);

        template.setDefaultSerializer(serializer);
        template.setEnableDefaultSerializer(true);
        return template;
    }

    /**
     * 配置@Cacheable注解的写入方式为json
     * @return
     */
    @Bean
    public RedisCacheConfiguration redisCacheConfiguration() {
        Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<>(Object.class);
        ObjectMapper om = new ObjectMapper();
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        jackson2JsonRedisSerializer.setObjectMapper(om);

        RedisCacheConfiguration redisCacheConfiguration = RedisCacheConfiguration.defaultCacheConfig();
        redisCacheConfiguration = redisCacheConfiguration.serializeValuesWith(
                RedisSerializationContext
                        .SerializationPair
                        .fromSerializer(jackson2JsonRedisSerializer)
        ).entryTtl(Duration.ofMinutes(30));

        return redisCacheConfiguration;
    }


    /**
     * 生成缓存key
     * @return
     */
    @Bean
    @Override
    public KeyGenerator keyGenerator() {
        return new KeyGenerator() {
            public Object generate(Object target, Method method, Object... objects) {
                StringBuilder sb = new StringBuilder();
                //user::cn.weiqiubin.service.UserService.selById[3]
                sb.append(target.getClass().getName()).append(".").append(method.getName()).append(Arrays.toString(objects));
                return sb.toString();
            }
        };
    }

    /*微信授权方面*/
    @Bean
    public WxMpService wxMpService(){
        WxMpServiceImpl wxMpService = new WxMpServiceImpl();
        wxMpService.setWxMpConfigStorage(wxMpConfigStorage());
        return wxMpService;

    }
    /*微信授权方面*/
    @Bean
    public WxMpConfigStorage wxMpConfigStorage(){
        WxMpDefaultConfigImpl wxMpConfigStorage = new WxMpDefaultConfigImpl();
        wxMpConfigStorage.setAppId("wxa44dc0ce28b5d078");
        wxMpConfigStorage.setSecret("4ddeda9b9cda16e70cf47f6660200f36");
        return wxMpConfigStorage;
    }


}
