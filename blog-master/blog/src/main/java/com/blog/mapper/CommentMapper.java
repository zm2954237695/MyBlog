package com.blog.mapper;


import com.blog.entity.Comment;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CommentMapper {

    List<Comment> getCommentByBlogId(Long blogId, long parseLong);
}
