package com.blog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.blog.entity.Tag;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface TagMapper extends BaseMapper<Tag> {
    List<Tag> getBlogTag();

    @Select("select * from t_tag")
    List<Tag> getAllTag();

    @Select("select id,name from t_tag where id=#{longId}")
    Tag getTag(Long longId);

    @Select("select * from t_tag where name= #{name}")
    Tag getTagByName(String name);

    int saveTag(Tag tag);

    @Delete("delete from t_tag where id=#{id}")
    int deleteTag(Long id);

    int updateTag(Tag tag);
}
