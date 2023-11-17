package org.kingsy.mapper;

import org.apache.ibatis.annotations.*;
import org.kingsy.pojo.Category;

import java.util.List;

@Mapper
public interface CategoryMapper {
//    新增

    @Insert("insert into category(category_name,category_alias,create_user,create_time,update_time)"+
    "values(#{categoryName},#{categoryAlias},#{createUser},#{createTime},#{updateTime})")
    void add(Category category);
//查询所有
    @Select("select * from category where create_user=#{userId}")
    List<Category> list(Integer userId);
//查id信息
    @Select("select * from category where id=#{id}")
    Category findById(Integer id);
//删除信息

    @Delete("delete from category where id = #{id}")//使用#{key}方式获取方法中的参数值
     void delete(Category category);

//更新
@Update("update category set category_name=#{categoryName},category_alias=#{categoryAlias},update_time=#{updateTime} where id=#{id}")
    void update(Category category);
}
