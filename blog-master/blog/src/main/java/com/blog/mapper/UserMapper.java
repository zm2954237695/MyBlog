package com.blog.mapper;


import com.blog.entity.User;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface UserMapper {
    User login(String username, String password);
}
