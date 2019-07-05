package com.example.demo.dao;

import com.example.demo.pojo.User;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface UserDao {


    @Select("select * from user")
    List<User> select();
}
