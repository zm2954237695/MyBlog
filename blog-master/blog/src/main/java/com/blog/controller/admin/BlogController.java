package com.blog.controller.admin;


import com.blog.entity.Blog;
import com.blog.entity.User;
import com.blog.service.BlogService;
import com.blog.service.TagService;
import com.blog.service.TypeService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/admin/blogs")
public class BlogController {

    @Autowired
    private BlogService blogService;
    @Autowired
    private TagService tagService;
    @Autowired
    private TypeService typeService;

    @GetMapping()
    public String getAllBlog(@RequestParam(required = false,defaultValue = "1"
                                ,value = "pagenum")int pagenum, Model model){

        PageHelper.startPage(pagenum,5);
        List<Blog> blogs = blogService.getAllBlog();
        PageInfo pageInfo = new PageInfo(blogs);
        model.addAttribute("pageInfo",pageInfo);
        //model.addAttribute("msg",查询成功)
        setTypeAndTag(model);
        return "admin/blogs";
    }
    private void setTypeAndTag(Model model){
        model.addAttribute("tags",tagService.getAllTag());
        model.addAttribute("types",typeService.getAllType());
    }

    /**
     * 去到新增博客页面
     * @param model
     * @return
     */
    @GetMapping("/input")
    public String toAddBlog(Model model){
        model.addAttribute("blog",new Blog());
        setTypeAndTag(model);
        return "admin/blogs-input";
    }

    /**
     * 去到编辑博客页面
     * @param id
     * @param model
     * @return
     */

    @GetMapping("/{id}/input")
    public String toEdit(@PathVariable("id")Long id,Model model){
         Blog blog = blogService.getBlogById(id);
         blog.init();
         model.addAttribute("blog",blog);
         setTypeAndTag(model);
         return "admin/blogs-input";
    }

    @PostMapping("/search")
    public String searchBlogs(Blog blog,@RequestParam(required = false,defaultValue = "1",
    value = "pagenum")int pagenum,Model model){
        PageHelper.startPage(pagenum,5);
        List<Blog> blogs = blogService.searchBlogs(blog);
        System.out.println(blogs);
        PageInfo pageInfo = new PageInfo(blogs);
        model.addAttribute("pageInfo",pageInfo);
        model.addAttribute("message","查询成功!");
        setTypeAndTag(model);
        return "admin/blogs";
    }

    @GetMapping("/{id}/delete")
    public String deleteBlog(@PathVariable("id")Long id, RedirectAttributes attributes){
         blogService.deleteBlog(id);
         attributes.addFlashAttribute("msg","删除成功");
         return "redirect:/admin/blogs";
    }

    @PostMapping()
    public String addBlog(Blog blog , HttpSession session,RedirectAttributes attributes){
        blog.setUser((User)session.getAttribute("user"));
        blog.setUserId(blog.getUser().getId());
        blog.setType(typeService.getType(blog.getType().getId()));
        blog.setTypeId(blog.getType().getId());
        blog.setTags(tagService.getTagByString(blog.getTagIds()));
        if (null == blog.getId()){
            blogService.saveBlog(blog);
        } else {
            blogService.updateBlog(blog);
        }
        attributes.addFlashAttribute("msg","新增成功");
        return "redirect:/admin/blogs";

    }

}
