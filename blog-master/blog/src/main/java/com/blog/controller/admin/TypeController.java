package com.blog.controller.admin;


import com.blog.entity.Type;
import com.blog.service.TypeService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/admin/types")
public class TypeController {

    @Autowired
    private  TypeService typeService;

    @GetMapping()
    public String types(@RequestParam(required = false,defaultValue = "1",value = "pagenum")
                        int pagenum, Model model){
        PageHelper.startPage(pagenum,5);
        List<Type> types = typeService.getAllType();
        PageInfo<Type> pageInfo = new PageInfo<>(types);
        model.addAttribute("pageInfo",pageInfo);
        return "admin/types";
    }

    @GetMapping("/input")
    public String toAddType(Model model){
        model.addAttribute("type",new Type());
        return "admin/types-input";
    }

    @GetMapping("/{id}/input")
    public String toEditType(@PathVariable("id") Long id,
                             Model model){
        model.addAttribute("type",typeService.getType(id));
        return "redirect:admin/types-input"; //不能直接跳转到types页面 ,否则不会显示type数据
    }


    @PostMapping()
    public String addType(Type type, RedirectAttributes attributes){
         Type t = typeService.getTypeByName(type.getName());
         if (null!=t){
             attributes.addFlashAttribute("msg","添加失败,不能添加重复的分类");
             return "redirect:/admin/types/input";
         } else {
             attributes.addFlashAttribute("msg","添加成功!");
         }
         typeService.saveType(type);
         return "redirect:/admin/types";
    }
     @PostMapping("/{id}")
     public String editType(@PathVariable("id") Long id,Type type,RedirectAttributes attributes){
           Type t=  typeService.getTypeByName(type.getName());
           if (null!=t){
               attributes.addFlashAttribute("msg","不能添加重复的分类");
               return "redirect:/admin/types/input";
           } else {
               attributes.addFlashAttribute("msg","修改成功");
           }
           typeService.updateType(type);
           return "redirect:/admin/types";
     }

     @GetMapping("/{id}")
     public String deleteType(@PathVariable("id")Long id, RedirectAttributes attributes){
        typeService.deleteById(id);
        attributes.addFlashAttribute("msg","删除成功");
        return "redirect:/admin/types";

     }



}
