package com.example.bookstore;

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
    void genToken(){
        Map<String,Object> claims=new HashMap<>();
        claims.put("id","1");
        claims.put("username","张三");

        String token= JWT.create()
                .withClaim("user",claims)
                .withExpiresAt(new Date(System.currentTimeMillis()+1000*60*60*3))
                .sign(Algorithm.HMAC256("itheima"));
        System.out.println(token);

    }

    @Test
    public void testParse(){
        //定义字符串，模拟用户传递过来的token
        String token="eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJleHAiOjE3MDA4MjMyNDQsInVzZXIiOnsiaWQiOiIxIiwidXNlcm5hbWUiOiLlvKDkuIkifX0.whq6ApFcYXBOv1Jf7Vt8AARfUU_NAcls2N5CIg-PHlg";
        JWTVerifier jwtVerifier=JWT.require(Algorithm.HMAC256("itheima")).build();
        DecodedJWT decodedJWT=jwtVerifier.verify(token);//验证token,生成一个解析后的JWT对象
        Map<String, Claim> claims=decodedJWT.getClaims();
        System.out.println(claims.get("user"));
    }
}
