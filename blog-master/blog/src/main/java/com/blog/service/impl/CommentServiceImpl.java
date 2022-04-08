package com.blog.service.impl;


import com.blog.entity.Comment;
import com.blog.mapper.CommentMapper;
import com.blog.service.CommentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl  implements CommentService {

    @Autowired
    private CommentMapper commentMapper;


    @Override
    public List<Comment> getCommentByBlogId(Long blogId) {
        return commentMapper.getCommentByBlogId(blogId,Long.parseLong("-1"));
    }
}
