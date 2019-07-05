package com.example.demo.controller;

import com.example.demo.dao.UserDao;
import com.example.demo.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("test")
public class TestController {

    @Autowired
    private UserDao userDao;
    @RequestMapping("tt")
    public List<User> test(){
        List<User> users= userDao.select();
        System.out.println(users);
        return users;
    }
}
