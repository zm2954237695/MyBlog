package com.blog.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Comment {


    private Long id;
    private String nickname;
    private String email;
    private String content;
    private boolean adminComment;  //是否为管理员评论

    //头像
    private String avatar;
    private Date createTime;

    private Long blogId;
    private Long parentCommentId;  //父评论id
    private String parentNickname;


    //父评论
    private Comment parentComment;

    private Blog blog;


}
