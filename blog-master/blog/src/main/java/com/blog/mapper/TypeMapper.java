package com.blog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.blog.entity.Type;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import javax.annotation.ManagedBean;
import java.util.List;

@Mapper
public interface TypeMapper extends BaseMapper<Type> {
    List<Type> getBlogType();

    @Select("select * from t_type")
    List<Type> getAllType();

    @Select("select id,name from t_type where id=#{id}")
    Type getType(Long id);

    @Select("select * from t_type where name=#{name}")
    Type getTypeByName(String name);

    int saveType(Type type);

    int updateType(Type type);
}
