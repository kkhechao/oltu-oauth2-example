package cn.zetark.oauth2.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Date;

/**
 * Copyright (c) 2017, 北京卡拉卡尔科技股份有限公司 All rights reserved.
 * jwt 安全认证
 *
 * @author chao.he@karakal.com.cn
 * @version 1.0
 * @since 2018年05月26日    18:58
 */
public class JwtHelper {
    private final Logger log = LoggerFactory.getLogger(JwtHelper.class);
    /**
     * 安全密钥
     */
    private final static String base64Secret ="123456";

    private final static int expiresSecond =200;

    /**
     * 创建 token
     * @param username
     * @param roles
     * @param privileges
     * @return
     */
    public static String createJWT(String username, String roles, String privileges) {
        io.jsonwebtoken.SignatureAlgorithm signatureAlgorithm= io.jsonwebtoken.SignatureAlgorithm.HS256;
        long nowMillis = System.currentTimeMillis();
        Date now=new Date();
        //生成签名密钥
        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(base64Secret);
        System.out.println(apiKeySecretBytes);
        System.out.println(signatureAlgorithm.getJcaName()+"==="+signatureAlgorithm.getValue());
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());
        System.out.println(signingKey);

        //添加构成JWT的参数
        JwtBuilder builder = Jwts.builder().setHeaderParam("typ", "JWT")
                .claim("user_name", username)
                .claim("user_role", roles)
                .claim("user_privilege", privileges)
                .signWith(signatureAlgorithm, signingKey);
        //添加Token过期时间
        if (expiresSecond >= 0) {
            long expMillis = nowMillis + expiresSecond;
            Date exp = new Date(expMillis);
            builder.setExpiration(exp).setNotBefore(now);
        }

        //生成JWT
        return builder.compact();
    }

    /**
     * 解密
     * @param jsonWebToken
     * @return
     */
    public static Claims parseJWT(String jsonWebToken) {
        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(DatatypeConverter.parseBase64Binary(base64Secret))
                    .parseClaimsJws(jsonWebToken).getBody();
            return claims;
        } catch (Exception ex) {
            return null;
        }
    }

}
