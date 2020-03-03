package cn.weiqiubin.controller;

import cn.weiqiubin.Entity.Student;
import cn.weiqiubin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class Helloworld {

    @Autowired
    private UserService userService;

    @ResponseBody
    @RequestMapping("/hello")
    public String hello(){
        try {
            String sayhello = userService.sayhello();
            String sayworld = userService.sayworld();
            return sayhello+sayworld;
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            return "finally";
        }
    }

    /**
     * 使用实现接口WebMvcConfigurer中的 addViewControllers 方法
     * 举例
     *       @Override
     *       public void addViewControllers(ViewControllerRegistry registry) {
     *             registry.addViewController("/tologin").setViewName("login");
     *       }
     * @param name
     * @return
     */
//    @RequestMapping(value = "/login",method = RequestMethod.GET)
//    public String login(){
//        return "/login";
//    }
    @ResponseBody//告诉SpringMVC，此时的返回 不是一个 View页面，而是一个 ajax调用的返回值（Json数组）
    @RequestMapping(value="testJson",method = RequestMethod.POST)
    public List<Student> testJson(@RequestBody List<Object> name) {

        for (Object o : name) {
            System.out.println(o);
        }

        Student stu1 = new Student("zs","25",23);
        Student stu2 = new Student("ls","25",24);
        Student stu3 = new Student("ww","25",25);
        List<Student> students = new ArrayList<>();
        students.add(stu1) ;
        students.add(stu2) ;
        students.add(stu3) ;
        return students;
    }

    @PostMapping(value = "/user/login")
    public String login(@RequestParam("username") String username,
                        @RequestParam("password") String password,
                        Map<String,Object> map, HttpSession session){
        if(!StringUtils.isEmpty(username) && "123456".equals(password)){
            //登陆成功，防止表单重复提交，可以重定向到主页
            session.setAttribute("loginUser",username);
            return "redirect:/main.html";
        }else{
            //登陆失败

            map.put("msg","用户名密码错误");
            return  "login";
        }

    }

}
