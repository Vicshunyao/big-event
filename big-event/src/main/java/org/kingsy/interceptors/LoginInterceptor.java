package org.kingsy.interceptors;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.kingsy.pojo.Result;
import org.kingsy.utils.JwtUtil;
import org.kingsy.utils.ThreadLocalUtil;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Map;

@Component

public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        令牌验证，调用request对象
      String token= request.getHeader("Authorization");
        try {
            Map<String ,Object> claims = JwtUtil.parseToken(token);
//            把业务村村到tl中
            ThreadLocalUtil.set(claims);
//            放行
            return true;
        }catch (Exception e){
            response.setStatus(401);
//            不放行
            return false;
        }


    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        //清除tl中的数据
        ThreadLocalUtil.remove();
    }
}
