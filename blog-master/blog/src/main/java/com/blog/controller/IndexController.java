package com.blog.controller;


import com.blog.entity.Blog;
import com.blog.entity.Tag;
import com.blog.entity.Type;
import com.blog.service.BlogService;
import com.blog.service.TagService;
import com.blog.service.TypeService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class IndexController {

     /**
     * 显示博客
     */
     
     @Autowired
     private BlogService blogService;
     @Autowired
     private TagService tagService;
     @Autowired
     private TypeService typeService;


     @GetMapping("/")
     public String index(@RequestParam(required = false,defaultValue = "1",value = "pagenum")int pagenum, Model model){

         PageHelper.startPage(pagenum,8);//分页插件
         List<Blog> AllBlogs = blogService.getIndexBlog(); //获取所有博客
         List<Tag> tags = tagService.getBlogTag();
         List<Type> types = typeService.getBlogType();

         PageInfo pageInfo = new PageInfo(AllBlogs);
         model.addAttribute("pageInfo",pageInfo);
         model.addAttribute("tags",tags);
         model.addAttribute("typeds",types);
         return "index";
     }

    /**
     * 搜索框
     * @param pagenum
     * @param query
     * @param model
     * @return
     */
     @PostMapping("/search")
     public String search(@RequestParam(required = false,defaultValue = "1",value = "pagenum")int pagenum,
                          @RequestParam String query, Model model){

         PageHelper.startPage(pagenum,5);
         List<Blog> searchBlog = blogService.getSearchBlog(query);
         PageInfo pageInfo  = new PageInfo(searchBlog);
         model.addAttribute("pageInfo",pageInfo);
         model.addAttribute("query",query);
         return "search";
     }


     //根据blog的ID查询详细的blog信息
     @GetMapping("/blog/{id}")
     public String toLogin(@PathVariable("id") Long id,Model model){
         Blog blog  = blogService.getDetailedBlog(id);
         model.addAttribute("blog",blog);
         return "blog";

     }


}
