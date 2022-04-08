package com.blog.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.blog.entity.Tag;
import com.blog.mapper.TagMapper;
import com.blog.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagServiceImpl  implements TagService  {

    @Autowired
    private  TagMapper tagMapper;

    @Override
    public List<Tag> getBlogTag() {
        return tagMapper.getBlogTag();
    }
}
