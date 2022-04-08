package com.blog.service.impl;


import com.blog.entity.Comment;
import com.blog.mapper.CommentMapper;
import com.blog.service.CommentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CommentServiceImpl  implements CommentService {

    @Autowired
    private CommentMapper commentMapper;


    @Override
    public List<Comment> getCommentByBlogId(Long blogId) {
        return commentMapper.getCommentByBlogId(blogId,Long.parseLong("-1"));
    }

    @Override
    public int saveComment(Comment comment) {
          Long parentCommentId = comment.getParentComment().getId();
          if (parentCommentId != -1){
              comment.setParentComment(commentMapper.findByParentCommentId(parentCommentId));
          }
          else {
              comment.setParentCommentId((long)-1);
              comment.setParentComment(null);
          }
          comment.setCreateTime(new Date());
          return commentMapper.saveComment(comment);

    }
}
