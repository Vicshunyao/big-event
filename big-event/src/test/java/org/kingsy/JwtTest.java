package org.kingsy;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtTest {
    @Test
    public void testGen(){
        Map<String,Object> claims =new HashMap<>();
        claims.put("id",1);
        claims.put("username","张三");
//        生成jwt代码
      String token=  JWT.create()
                .withClaim("user",claims)//添加载荷
                .withExpiresAt(new Date(System.currentTimeMillis()))//添加过期时间
                .sign(Algorithm.HMAC256("kingsy"));//指定算法，配置秘钥
        System.out.println(token);

    }
    @Test
    public void testParse(){
        //定义字符串，模拟用户传过来的token
        String token="eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9."+
                "eyJleHAiOjE3MDAwODA0MzgsInVzZXIiOnsiaWQiOjEsInVzZXJuYW1lIjoi5byg5LiJIn19"+
                ".9RLEhROY46ehLUMfZFTCE4U8KLEJBsqvJs2I9YJWhqw";
        JWTVerifier jwtVerifier =JWT.require(Algorithm.HMAC256("kingsy")).build();
        DecodedJWT decodedJWT =jwtVerifier.verify(token);
        Map<String, Claim> claims =decodedJWT.getClaims();
        System.out.println(claims.get("user"));
//        如果修改头部和载荷部分，验证失败，
//        秘钥不对也验证失败，过期也验证失败

    }
}
