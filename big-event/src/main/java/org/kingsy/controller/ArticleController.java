package org.kingsy.controller;

import jakarta.servlet.http.HttpServletResponse;
import org.kingsy.pojo.Article;
import org.kingsy.pojo.Category;
import org.kingsy.pojo.Result;
import org.kingsy.service.ArticleService;
import org.kingsy.service.CategoryService;
import org.kingsy.utils.JwtUtil;
import org.kingsy.utils.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/article")
public class ArticleController {
    @Autowired
    private ArticleService articleService ;
//    @Autowired
//    private ArticleService articleService;

    @PostMapping("/add")
    public Result add(@RequestBody  @Validated(Article.Add.class)  Article article){
        articleService.add(article);
        return Result.success();


    }

@GetMapping("/list")
    public Result<PageBean<Article>> list(
            Integer pageNum,
            Integer pageSize,
           @RequestParam(required = false) Integer categoryId,
           @RequestParam(required = false) String state

    ){
     PageBean<Article> atr=articleService.list(pageNum,pageSize,categoryId,state);
        return Result.success(atr);

    }
    @GetMapping("/detail")

    public Result<Article>detail(Integer id){
        Article aa= articleService.findById(id);

        return Result.success(aa);

    }
    @PutMapping
    public Result update(@RequestBody @Validated(Article.Update.class) Article article){
        articleService.update(article);
        return Result.success();
    }
    @PostMapping ("/delete")
    public Result delete(@RequestBody  Article article) {

        articleService.deletearticle(article);
        return Result.success();
    }

//    @GetMapping("/list")
//
//    public Result<String> list(/*@RequestHeader(name="Authorization") String token, HttpServletResponse response*/){
//
//        //验证token
////        try {
////            Map<String ,Object> claims = JwtUtil.parseToken(token);
////            return Result.success("所有的文章数据");
////        }catch (Exception e){
////            response.setStatus(401);
////            return Result.error("未登录");
////        }
//
//        return Result.success("所有的文章数据");
//
//    }
}
