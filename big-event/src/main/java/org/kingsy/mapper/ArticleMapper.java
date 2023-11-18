package org.kingsy.mapper;

import org.apache.ibatis.annotations.*;
import org.kingsy.pojo.Article;

import java.util.List;

@Mapper
public interface ArticleMapper {

//    新增
    @Insert("insert into article(title,content,cover_img,state,category_id,create_user,create_time,update_time)"+
    "values(#{title},#{content},#{coverImg},#{state},#{categoryId},#{createUser},#{createTime},#{updateTime})")
    void add(Article article);
//查询

    List<Article> list(Integer userId, Integer categoryId, String state);
    @Select("select * from article where id=#{id}")
    Article findById(Integer id);
//更新
@Update("update article set title=#{title},content=#{content},cover_img=#{coverImg},state=#{state},category_id=#{categoryId},update_time=#{updateTime} where id=#{id}")
    void update(Article article);

//    删除
@Delete("delete from article where id = #{id}")//使用#{key}方式获取方法中的参数值
    void delete(Article article);
}
