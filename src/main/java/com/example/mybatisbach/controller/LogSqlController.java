package com.example.mybatisbach.controller;

import com.example.mybatisbach.dao.UserMapper;
import com.example.mybatisbach.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author kangshaofei
 * @description 打印sql
 * @date 2018/4/27
 **/
@RestController
public class LogSqlController {

    @Autowired
    UserMapper userMapper;


    @GetMapping("/pring")
    public String print(@RequestParam String username){
        userMapper.selectByPrimaryKey(1);
        User user = new User();
        user.setUserId(109);
        user.setUserName("lisi");
        user.setPassword("111");
        user.setPhone("110");
        userMapper.insert(user);
        return "asdfasdf";
//        userMapper.insert(new User());
    }


}
