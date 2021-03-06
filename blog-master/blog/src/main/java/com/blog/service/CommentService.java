package com.blog.service;

import com.blog.entity.Comment;

import java.util.List;

public interface CommentService {
    List<Comment> getCommentByBlogId(Long blogId);
    int saveComment(Comment comment);
}
