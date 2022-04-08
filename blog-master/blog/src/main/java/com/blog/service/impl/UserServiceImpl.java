package com.blog.service.impl;

import com.blog.entity.User;
import com.blog.mapper.UserMapper;
import com.blog.service.UserService;
import com.blog.utils.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;


    @Override
    public User login(String username, String password) {
         if(StringUtils.isEmpty(username) || StringUtils.isEmpty(password)){
             return null;
         }

       // System.out.println(password);
         return userMapper.login(username, MD5Utils.code(password));
    }
}
