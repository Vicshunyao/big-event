package org.kingsy.controller;

import jakarta.validation.constraints.Pattern;
import org.kingsy.pojo.Result;
import org.kingsy.pojo.User;
import org.kingsy.service.UserService;
import org.kingsy.utils.Md5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@Validated
public class UserController {

    @Autowired
    private UserService userService;
    @PostMapping("/register")
    public Result register(@Pattern(regexp = "^\\S{5,16}$") String username,@Pattern(regexp = "^\\S{5,16}$") String password){
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
    @PostMapping("/login")

    public Result<String> login(@Pattern(regexp = "^\\S{5,16}$") String username,@Pattern(regexp = "^\\S{5,16}$") String password){
//        根据用户名查询用户
      User loginUser=  userService.findByUserName(username);


//        判断该用户是否存在
        if(loginUser==null){
            return Result.error("用户名错误");


        }
//        判断秘密是否正确
        if(Md5Util.getMD5String(password).equals(loginUser.getPassword())){
//            登录成功
            return Result.success("jwt token");
        }

        return Result.error("密码错误");

    }
}
