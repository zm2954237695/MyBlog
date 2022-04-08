package com.blog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.blog.entity.Tag;

import java.util.List;

public interface TagService  {
    List<Tag> getBlogTag();

    List<Tag> getAllTag();

    List<Tag> getTagByString(String tagIds);

    Tag getTag(Long id);

    Tag getTagByName(String name);

    int saveTag(Tag tag);

    int deleteTag(Long id);

    int updateTag(Tag tag);
}
