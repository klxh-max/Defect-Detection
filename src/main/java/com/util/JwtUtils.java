package com.util;

import com.entity.User;
import com.mapper.UserMapper;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtUtils {

    @Autowired
    private UserMapper userMapper;
    /**
     * 过期时间7天
     */
    private static final long EXPIRE_TIME = 7 * 24 * 60 * 60 * 1000;
    /**
     * 加密密钥
     */
    private static final String KEY = "liuhongfei";

    /**
     * 生成token
     *
     * @param id       用户id
     * @param userName 用户名
     * @return
     */
    public String createToken(Integer id, String userName) {
        Map<String, Object> header = new HashMap();
        header.put("typ", "JWT");
        header.put("alg", "HS256");
        //setID:用户ID
        //setExpiration:token过期时间  当前时间+有效时间
        //setSubject:用户名
        //setIssuedAt:token创建时间
        //signWith:加密方式
        JwtBuilder builder = Jwts.builder().setHeader(header)
                .setId("" + id)
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRE_TIME))
                .setSubject(userName)
                .setIssuedAt(new Date())
                .signWith(SignatureAlgorithm.HS256, KEY);
        return builder.compact();
    }

    /**
     * 验证token是否有效
     *
     * @param token 请求头中携带的token
     * @return token验证结果  2-token过期；1-token认证通过；0-token认证失败
     */
    public int verify(String token) {
        Claims claims = null;
        try {
            //token过期后，会抛出ExpiredJwtException 异常，通过这个来判定token过期，
            claims = Jwts.parser().setSigningKey(KEY).parseClaimsJws(token).getBody();
        } catch (ExpiredJwtException e) {
            System.out.println("claims err");
            return 2;
        }
        //从token中获取用户id，查询该Id的用户是否存在，存在则token验证通过
        String id = claims.getId();
        User user = userMapper.selectById(id);
        if (user != null) {
            return 1;
        } else {
            return 0;
        }
    }

    public int getUserId(String token) {
        Claims claims = null;
        try {
            //token过期后，会抛出ExpiredJwtException 异常，通过这个来判定token过期，
            claims = Jwts.parser().setSigningKey(KEY).parseClaimsJws(token).getBody();
        } catch (ExpiredJwtException e) {
            System.out.println("claims err");
            return 2;
        }
        //从token中获取用户id，查询该Id的用户是否存在，存在则token验证通过
        String id = claims.getId();
        return Integer.parseInt(id);
    }

}

