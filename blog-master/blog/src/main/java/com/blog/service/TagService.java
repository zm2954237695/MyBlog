package com.blog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.blog.entity.Tag;

import java.util.List;

public interface TagService  {
    List<Tag> getBlogTag();
}
