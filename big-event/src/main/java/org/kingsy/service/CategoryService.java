package org.kingsy.service;

import org.kingsy.pojo.Category;

import java.util.List;

public interface CategoryService {
//    新增分类
    void add(Category category);
//；列表查询
    List<Category> list();
//根据id查信息
    Category findById(Integer id);
//更新
    void update(Category category);
//删除
    void deleteCategory(Category category);

//
}
