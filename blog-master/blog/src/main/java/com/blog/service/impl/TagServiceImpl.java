package com.blog.service.impl;

import com.blog.entity.Tag;
import com.blog.mapper.TagMapper;
import com.blog.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class TagServiceImpl  implements TagService  {

    @Autowired
    private  TagMapper tagMapper;

    @Override
    public List<Tag> getBlogTag() {
        return tagMapper.getBlogTag();
    }

    @Override
    public List<Tag> getAllTag() {
        return tagMapper.getAllTag();
    }

    @Override
    public List<Tag> getTagByString(String tagIds) {
        List<Tag> tags = new ArrayList<>();
        List<Long> longs = convertToLongs(tagIds);
        for(Long longId:longs){
            tags.add(tagMapper.getTag(longId));
        }
        return tags;
    }

    @Override
    public Tag getTag(Long id) {
        return tagMapper.getTag(id);
    }

    @Override
    public Tag getTagByName(String name) {
        return tagMapper.getTagByName(name);
    }

    @Override
    public int saveTag(Tag tag) {
        return tagMapper.saveTag(tag);
    }

    @Override
    public int deleteTag(Long id) {
        return tagMapper.deleteTag(id);
    }

    @Override
    public int updateTag(Tag tag) {
        return tagMapper.updateTag(tag);
    }

    private List<Long> convertToLongs(String tagIds) {
        List<Long> longs = new ArrayList<>();
        if(!StringUtils.isEmpty(tagIds)){
            String[] array = tagIds.split(",");
            for(String s:array){
                longs.add(new Long(s));
            }
        }
        return longs;
    }
}
