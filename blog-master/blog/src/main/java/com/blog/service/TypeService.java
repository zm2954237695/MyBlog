package com.blog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.blog.entity.Type;

import java.util.List;

public interface TypeService  {
    List<Type> getBlogType();

    List<Type> getAllType();

    Type getType(Long id);
}
