package com.blog.controller;


import com.blog.service.BlogService;
import com.blog.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class CommentController {

    @Autowired
    private CommentService commentService;
    @Autowired
    private BlogService blogService;
    @Value("${comment.avatar}")
    private String avatar;

    @GetMapping("/comments/{blogId}")
    public String comments(@PathVariable("blogId")Long blogId, Model model){
        model.addAttribute("comments",commentService.getCommentByBlogId(blogId));
        model.addAttribute("blog",blogService.getDetailedBlog(blogId));
        return "blog :: commentList";
    }

}
