package com.blog.mapper;


import com.blog.entity.Comment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CommentMapper {

    List<Comment> getCommentByBlogId(Long blogId, long blogParentId);

    Comment findByParentCommentId(@Param("parentCommentId") Long parentCommentId);

    int saveComment(Comment comment);
}
