package org.kingsy.service;

import org.kingsy.pojo.Article;
import org.kingsy.pojo.Category;
import org.kingsy.utils.PageBean;

public interface ArticleService {
    Article findById(Integer id);

    void add(Article article);
//条件分页列表查询
    PageBean<Article> list(Integer pageNum, Integer pageSize, Integer categoryId, String state);
//更新
    //更新
    void update(Article article);

    // 删除
 void deletearticle(Article article);
}
