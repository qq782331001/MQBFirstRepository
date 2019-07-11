package com.mqb.springbootspringsecurity.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("user")
public class UserController {

    @RequestMapping("findAll")
    public String findAll(){return "查询所有";};

    @RequestMapping("findById")
    public String findById(){return "根据id查询";};

}
