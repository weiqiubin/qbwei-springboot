package cn.weiqiubin.Entity;



import lombok.Data;
import net.bytebuddy.agent.builder.AgentBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Email;
import java.util.List;
import java.util.Map;

@Data //lombok 注解
@Validated //数据验证
@Component
@ConfigurationProperties(prefix = "usera") //注解默认配置 优先级高
//@PropertySource(value = {"classpath:waibu.properties"}) //引入外部配置文件
public class Useryml {

    @Email //配合@ConfigurationProperties 注解才可以起作用 [@Value此验证泽无效]
    //@Value("nnn") //相当于<bean value=""></bean>
    private String namea;
    private Integer age;
    private Map<String,Object> map;
    private List<Object> list;

}
