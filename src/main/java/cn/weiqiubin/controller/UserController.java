package cn.weiqiubin.controller;

import cn.weiqiubin.domain.User;
import cn.weiqiubin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserService userService;
    @RequestMapping("getuser/{id}")
    public User getuser(@PathVariable Integer id){
        User user = userService.selById(id);
        return user;
    }
}
