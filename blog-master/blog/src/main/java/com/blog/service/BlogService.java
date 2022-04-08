package com.blog.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.blog.entity.Blog;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;


public interface BlogService {


    List<Blog> getAllBlog();

    List<Blog> getSearchBlog(String query);

    List<Blog> getIndexBlog();

    Blog getDetailedBlog(Long id);

    Map<String,List<Blog>> archiveBlog();

    int countBlog();

    List<Blog> getByTagId(Long id);

    List<Blog> getTypeById(Long id);
}
