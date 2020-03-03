package cn.weiqiubin.mapper;


import cn.weiqiubin.domain.User;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author:wjup
 * @Date: 2018/9/26 0026
 * @Time: 15:20
 */
@Repository
public interface UserMapper {

    User Sel(int id);

    void updateNameOne(User user);

    User selectSexName(int id);

    List<User> selectHashMap(Map map);

    List<HashMap<String,Object>> selectresultType(int id);

    List<User> dongtaisql(User user);

    List<User> dongtaisqlforeach(User user);

    List<User> dongtaisqlObject(User[] users);
}
