package com.example.mybatisbach.config;

import com.example.mybatisbach.entity.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author kangshaofei
 * @Description
 * @Date 2018/1/30
 **/
@Configuration
public class UserConfig {

    @Bean
    public User zhangsan(){
        User user = new User();
        user.setUserName("san");
        return user;
    }


}
