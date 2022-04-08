package com.blog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.blog.entity.Type;

import java.util.List;

public interface TypeMapper extends BaseMapper<Type> {
    List<Type> getBlogType();
}
