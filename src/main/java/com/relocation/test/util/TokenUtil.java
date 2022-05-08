package com.relocation.test.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.relocation.test.repository.EmployeeRepository;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class TokenUtil {
    //token过期时长30分钟
    private static final long EXPIRE_TIME = 30 * 60 * 1000;
    //token私钥
    private static final String TOKEN_SECRET = "abcdefg";

    public static String sign(String userName, String password) {

        String signData = "";
        //过期时间
        Date date = new Date(System.currentTimeMillis() + EXPIRE_TIME);
        //私钥及加密算法
        Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
        //设置头信息
        Map<String, Object> header = new HashMap();
        header.put("typ", "JWT");
        header.put("alg", "HS256");

        signData = JWT.create()
                .withHeader(header)
                .withClaim("userName", userName)
                .withClaim("password", password)
                .withExpiresAt(date)
                .sign(algorithm);

        return signData;
    }

    public static boolean verify(String token, EmployeeRepository repository) {

        try {
            Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
            JWTVerifier jwtVerifier = JWT.require(algorithm).build();
            DecodedJWT decodedJWT = jwtVerifier.verify(token);
            String userName = decodedJWT.getClaim("userName").asString();
            String password = decodedJWT.getClaim("password").asString();
            if(!repository.existsEmployeeByNameAndPwd(userName, password)) {
                return false;
            }
            if(new Date().getTime() > decodedJWT.getExpiresAt().getTime()){
                return false;
            }

        } catch (Exception e) {
            return false;
        }

        return true;
    }
}
