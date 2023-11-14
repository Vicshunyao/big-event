package org.kingsy.controller;

import org.kingsy.pojo.Result;
import org.kingsy.pojo.User;
import org.kingsy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;
    @PostMapping("/register")
    public Result register(String username,String password){
//        查询用户
     User u =userService.findByUserName(username);
     if(u==null){
         userService.register(username,password);
         return Result.success();

     }else {
         return Result.error("用户名已经被占用");

     }
//        注册用户

    }
}
