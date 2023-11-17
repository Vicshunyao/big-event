package org.kingsy.controller;

import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.URL;
import org.kingsy.pojo.Result;
import org.kingsy.pojo.User;
import org.kingsy.service.UserService;
import org.kingsy.utils.JwtUtil;
import org.kingsy.utils.Md5Util;
import org.kingsy.utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

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
            Map<String,Object> claims =new HashMap<>();
            claims.put("id",loginUser.getId());
            claims.put("username",loginUser.getUsername());
           String token= JwtUtil.genToken(claims);
//            登录成功
            return Result.success(token);
        }

        return Result.error("密码错误");

    }



    @GetMapping("/userInfo")

    public Result<User> userInfo(/*@RequestHeader(name="Authorization") String token*/){
        Map<String,Object> map= ThreadLocalUtil.get();
        String username =(String) map.get("username");
      /*  Map<String,Object> map=JwtUtil.parseToken(token);
        String username =(String) map.get("username");*/
       User user= userService.findByUserName(username);
       return Result.success(user);

    }
    @PutMapping("/update")
    public  Result update(@RequestBody @Validated User user){
        userService.update(user);
        return Result.success();


    }
    @PatchMapping("updateAvatar")
    public Result updateAvater(@RequestParam @URL String avatarUrl){
        userService.updateAvatar(avatarUrl);
        return Result.success();


    }
    @PatchMapping("/updatePwd")
    public Result updatePwd(@RequestBody Map<String,String> params){
//        校验参数
       String oldPwd= params.get("old_pwd");
       String newPwd =params.get("new_pwd");
       String rePwd =params.get("re_pwd");
       if(!StringUtils.hasLength(oldPwd)||!StringUtils.hasLength(newPwd)||!StringUtils.hasLength(rePwd)){
           return  Result.error("缺少必要参数");

       }
//       原密码是否正确，调用用户名拿到原密码
        Map<String,Object> map=ThreadLocalUtil.get();
       String username =(String) map.get("username");
       User LoginUser= userService.findByUserName(username);
      if(! LoginUser.getPassword().equals(Md5Util.getMD5String(oldPwd))){
          return  Result.error("原密码不正确");
      }
//        newpwd和repwd是否一样
        if(!rePwd.equals(newPwd)){
            return  Result.error("两次填写新密码不一样");
        }
        userService.updatePwd(newPwd);
        return  Result.success();

//        调用service完成更新
    }
}
