package org.kingsy.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.kingsy.mapper.ArticleMapper;
import org.kingsy.pojo.Article;
import org.kingsy.service.ArticleService;
import org.kingsy.utils.PageBean;
import org.kingsy.utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
@Service
public class ArticelServiceImpl implements ArticleService {
    @Autowired
    private ArticleMapper articelMapper;

    @Override
    public Article findById(Integer id) {
        Article aa=articelMapper.findById(id);


        return aa;
    }

    @Override
    public void add(Article article) {
//        补充属性
        article.setUpdateTime(LocalDateTime.now());
        article.setCreateTime(LocalDateTime.now());
     Map<String,Object> map= ThreadLocalUtil.get();
        Integer userId= (Integer) map.get("id");
        article.setCreateUser(userId);
        articelMapper.add(article);

    }

    @Override
    public PageBean<Article> list(Integer pageNum, Integer pageSize, Integer categoryId, String state) {
//        1.创建对象，开启查询，
        PageBean<Article> pb =new PageBean<>();
        PageHelper.startPage(pageNum,pageSize);
//        调用mapper
        Map<String,Object> map= ThreadLocalUtil.get();
        Integer userId= (Integer) map.get("id");

       List<Article> as= articelMapper.list(userId,categoryId,state);
//       Page中提出了方法，可以获取PageHelper分页查询后，得到的总记录条数和当前页数据
        Page<Article> p =(Page<Article>) as;
//把数据填充到PageBean对象中
        pb.setTotal(p.getTotal());
        pb.setItems(p.getResult());
        return pb;
    }

    @Override
    public void update(Article article) {
        article.setUpdateTime(LocalDateTime.now());
        articelMapper.update(article);
    }

    //更新
//    @Override
//    public void update1(Article article) {
//        article.setUpdateTime(LocalDateTime.now());
//        articelMapper.update(article);
//    }


//删除
 @Override
    public void deletearticle(Article article) {
        articelMapper.delete(article);

    }
}
