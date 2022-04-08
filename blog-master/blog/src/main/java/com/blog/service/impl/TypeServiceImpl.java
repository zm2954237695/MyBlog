package com.blog.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.blog.entity.Type;
import com.blog.mapper.TypeMapper;
import com.blog.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.lang.model.type.TypeMirror;
import java.util.List;

@Service
public class TypeServiceImpl  implements TypeService {



    @Autowired
    private  TypeMapper typeMapper ;
    @Override
    public List<Type> getBlogType() {
        return typeMapper.getBlogType();
    }

    @Override
    public List<Type> getAllType() {
        return typeMapper.getAllType();
    }

    @Override
    public Type getType(Long id) {
        return typeMapper.getType(id);
    }
}
