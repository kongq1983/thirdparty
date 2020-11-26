package com.kq.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.HashMap;
import java.util.Map;

public class JWTMain {

    public static void main(String[] args) {
        String SECRET = "1234567";
        // header Map
        Map<String, Object> headerMap = new HashMap<>();
        headerMap.put("alg", "HS256");
        headerMap.put("typ", "JWT");

       String token =  JWT.create().withHeader(headerMap).withJWTId("123456").withClaim("userId",10L)
                .withClaim("username","admin").withSubject("king")
               .sign(Algorithm.HMAC256(SECRET));

        System.out.println("token="+token);

        //解密
        DecodedJWT decodedJWT = JWT.require(Algorithm.HMAC256(SECRET)).build().verify(token);
        String header = decodedJWT.getHeader();
        System.out.println("header="+header);

    }

}
