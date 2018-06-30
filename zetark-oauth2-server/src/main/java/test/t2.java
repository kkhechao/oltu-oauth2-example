package test;

import cn.zetark.oauth2.jwt.JwtHelper;
import io.jsonwebtoken.Claims;

import java.text.SimpleDateFormat;

/**
 * Copyright (c) 2017, 北京卡拉卡尔科技股份有限公司 All rights reserved.
 * e
 *
 * @author chao.he@karakal.com.cn
 * @version 1.0
 * @since 2018年05月26日    23:36
 */
public class t2 {

    public static void main(String [] args){
      String token= JwtHelper.createJWT("chaohe","管理员","全部");
        System.out.println(token);

        Claims claims   =JwtHelper.parseJWT(token) ;
        if(claims==null){
            System.out.println("eeeeeeeee");
        }
        System.out.println(claims.toString());
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:MM:SS");
        System.out.println( simpleDateFormat.format(claims.getExpiration()));
        System.out.println(simpleDateFormat.format(claims.getNotBefore()));
        System.out.println(claims.getSubject());
        System.out.println(claims.toString());

    }
}
