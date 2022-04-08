package com.blog.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.util.CollectionUtils;

import java.util.Date;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Data
@AllArgsConstructor
@NoArgsConstructor
//博客实体类
public class Blog {


    private Long id;
    private String title;
    private String content;
    private String firstPicture;
    private String flag;
    private Integer views;
    private Boolean appreciation;
    private Boolean shareStatement;
    private Boolean commentabled;
    private Boolean  published;
    private Boolean recommend;
    private Date createTime;
    private Date updateTime;


    private Long typeId; //多表连接
    private Long userId;
    private String tagIds; //将tags集合转成字符串 传给前端
    private String description;
    private  Type type;
    private  User user;
    private List<Tag> tags;
    private List<Comment> comments;
    public  void init(){
         this.tagIds = tagsToIds(this.getTags());
    }

    private String tagsToIds(List<Tag> tags) {
        if(CollectionUtils.isEmpty(tags)) {
            return tagIds;
        }else {
            StringBuffer sb = new StringBuffer();
            boolean flag=false;
            for(Tag tag:tags){
                if (flag)sb.append(",");
                else {
                    flag=true;
                }
                sb.append(tag.getId());
            }
            return sb.toString();
        }
    }
}
