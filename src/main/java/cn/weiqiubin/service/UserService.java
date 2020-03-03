package cn.weiqiubin.service;

import cn.weiqiubin.domain.User;
import cn.weiqiubin.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class UserService  {

    @Autowired
    private UserMapper userMapper;

    @Cacheable(value = "db0",keyGenerator = "keyGenerator")
    public User selById(Integer id){
        return userMapper.Sel(id);
    }

    @Async
    public String sayhello(){
        for (int i = 0; i < 100; i++) {
            try {
                Thread.sleep(100);
                System.out.println("hello"+"----"+i);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
            }
        }
        return "hello";
    }

    @Async
    public String sayworld(){
        for (int i = 0; i < 100; i++) {
            try {
                Thread.sleep(100);
                System.out.println("world"+"----"+i);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
            }
        }
        return "world";
    }

//    @Scheduled(cron = "0,5,10,20,30,40,50,55 * * * * *")
    public void Scheduling(){
        System.out.println("hello");
    }
}
