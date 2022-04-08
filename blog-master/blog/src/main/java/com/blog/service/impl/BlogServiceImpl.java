package com.blog.service.impl;

import com.blog.entity.Blog;
import com.blog.entity.BlogAndTag;
import com.blog.entity.Tag;
import com.blog.mapper.BlogMapper;
import com.blog.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BlogServiceImpl implements BlogService {


     @Autowired
     private BlogMapper blogMapper;


    @Override
    public List<Blog> getAllBlog() {
        return blogMapper.getAllBlog();
    }

    @Override
    public List<Blog> getSearchBlog(String query) {
        return blogMapper.getSearchBlog(query);
    }

    //首页博客显示
    @Override
    public List<Blog> getIndexBlog() {
        return blogMapper.getIndexBlog();
    }

    @Override
    public Blog getDetailedBlog(Long id) {
        return blogMapper.getDetailedBlog(id);
    }

    @Override
    public Map<String, List<Blog>> archiveBlog() {
        List<String> years = blogMapper.findAllYear();
        Map<String,List<Blog>> map = new HashMap<>();
        years.forEach(year->{
            map.put(year,blogMapper.findByYear(year));
        });
        return map;
    }

    @Override
    public int countBlog() {
        return blogMapper.getAllBlog().size();
    }

    @Override
    public List<Blog> getByTagId(Long id) {
        return blogMapper.getByTagId(id);
    }

    @Override
    public List<Blog> getTypeById(Long id) {
        return blogMapper.getTypeById(id);
    }

    @Override
    public Blog getBlogById(Long id) {
        return blogMapper.getBlogById(id);
    }

    @Override
    public List<Blog> searchBlogs(Blog blog) {
        return blogMapper.searchBlog(blog);
    }

    @Override
    public int deleteBlog(Long id) {
        return blogMapper.deleteBlog(id);
    }

    @Override
    public int saveBlog(Blog blog) {
         blog.setCreateTime(new Date());
         blog.setUpdateTime(new Date());
         blog.setViews(0);
         blogMapper.saveBlog(blog);
         Long id = blog.getId();
         List<Tag> tags = blog.getTags();
        BlogAndTag blogAndTag = null;
        for(Tag tag:tags){
            blogAndTag  = new BlogAndTag(tag.getId(),id);
            blogMapper.saveBlogAndTag(blogAndTag);
        }
         return 1;
    }

    @Override
    public int updateBlog(Blog blog) {
        blog.setUpdateTime(new Date());
        List<Tag> tags = blog.getTags();
        BlogAndTag blogAndTag = null;
        for(Tag tag:tags){
            blogAndTag  = new BlogAndTag(tag.getId(),blog.getId());
            blogMapper.saveBlogAndTag(blogAndTag);
        }
        return blogMapper.updateBlog(blog);
    }
}
