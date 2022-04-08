package com.blog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.blog.entity.Blog;
import com.blog.entity.BlogAndTag;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.stereotype.Service;

import java.util.List;

@Mapper
public interface BlogMapper extends BaseMapper<Blog> {




    List<Blog> getSearchBlog(String query);

    List<Blog> getIndexBlog();

    Blog getDetailedBlog(Long id);


    List<Blog> getAllBlog();


    /**
     *   通过年份查询博客
     * @param year
     * @return
     */
    List<Blog> findByYear(String year);

    List<String> findAllYear();

    List<Blog> getByTagId(Long id);

    List<Blog> getTypeById(Long id);

    Blog getBlogById(Long id);

    List<Blog> searchBlog(Blog blog);

    @Delete("delete from t_blog where id=#{id}")
    int deleteBlog(Long id);
    int saveBlog(Blog blog);

    int  saveBlogAndTag(BlogAndTag blogAndTag);

    int updateBlog(Blog blog);
}
