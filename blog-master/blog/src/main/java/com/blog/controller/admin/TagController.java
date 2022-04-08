package com.blog.controller.admin;


import com.blog.entity.Tag;
import com.blog.service.TagService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/admin/tags")
public class TagController {

    @Autowired
    private TagService tagService;


    @GetMapping()
    public String tags(@RequestParam(required = false,defaultValue = "1",value = "pagenum")int pagenum,
                       Model model){
        PageHelper.startPage(pagenum,5);
        List<Tag> tags  =tagService.getAllTag();
        PageInfo<Tag> pageInfo = new PageInfo<>(tags);
        model.addAttribute("pageInfo",pageInfo);
        return "admin/tags";
    }

    @GetMapping("/input")
    public String toAddTag(Model model){
        model.addAttribute("tag",new Tag());
        return "admin/tags-input";
    }

    @GetMapping("/{id}/input")
    public String toEdit(@PathVariable("id")Long id,Model model){
        model.addAttribute("tag",tagService.getTag(id));
        return "admin/tags-input";
    }

    @PostMapping()
    public String addTag(Tag tag, RedirectAttributes attributes){
        Tag t = tagService.getTagByName(tag.getName());
        if (t!=null){
            attributes.addFlashAttribute("msg","不能添加重复的标签");
            return "redirect:/admin/tags/input";
        }
        else {
            attributes.addFlashAttribute("msg","添加成功!");
        }
        tagService.saveTag(tag);
        return "redirect:/admin/tags";
    }

    @GetMapping("/{id}/delete")
    public String deleteTag(@PathVariable("id")Long id,RedirectAttributes attributes){
        tagService.deleteTag(id);
        attributes.addFlashAttribute("msg","删除成功!");
        return "redirect:/admin/tags";
    }
    @PostMapping("/tags/{id}")
    public String editTag(@PathVariable Long id,Tag tag,RedirectAttributes attributes){
          Tag t = tagService.getTagByName(tag.getName());
          if(t!=null){
              attributes.addFlashAttribute("msg","不能添加重复的标签");
              return "redirect:/admin/tags/input";
          }else {
              attributes.addFlashAttribute("msg","修改成功!");
          }
          tagService.updateTag(tag);
          return "redirect:/admin/tags";
    }

}
