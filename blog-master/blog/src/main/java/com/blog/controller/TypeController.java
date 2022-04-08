package com.blog.controller;


import com.blog.entity.Blog;
import com.blog.entity.Type;
import com.blog.service.BlogService;
import com.blog.service.TypeService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class TypeController {

    @Autowired
    private BlogService blogService;
    @Autowired
    private TypeService typeService;

    @GetMapping("/types/{id}")
    public String types(@PathVariable("id")Long id, @RequestParam(required = false,
                   defaultValue = "1",value = "pagenum")int pagenum,
                        Model model){
        PageHelper.startPage(pagenum,100);
        List<Type> types =  typeService.getBlogType();
        if (-1 == id) {
            id = types.get(0).getId();
        }
        List<Blog> blogs = blogService.getTypeById(id);
        PageInfo<Blog> pageInfo = new PageInfo<>(blogs);
        model.addAttribute("types",types);
        model.addAttribute("pageInfo",pageInfo);
        model.addAttribute("activeTypeId",id);
        return "types";
    }

}
