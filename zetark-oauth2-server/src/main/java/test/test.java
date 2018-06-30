package test;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Date;

/**
 * Copyright (c) 2017, 北京卡拉卡尔科技股份有限公司 All rights reserved.
 * tt
 *
 * @author chao.he@karakal.com.cn
 * @version 1.0
 * @since 2017年11月07日    17:55
 */
public class test {

   public static void main(String [] args){

       io.jsonwebtoken.SignatureAlgorithm  signatureAlgorithm= SignatureAlgorithm.HS256;
       long nowMillis = System.currentTimeMillis();
       long ttMillis=nowMillis+2*60000;
       Date now = new Date(ttMillis);
     //te[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary("20171107");
       String ss=DatatypeConverter.printBase64Binary("cms".getBytes());
       byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(ss);
       System.out.println("tttttttttttt"+apiKeySecretBytes.length);
       Key signingKey = new SecretKeySpec("cms".getBytes(), signatureAlgorithm.getJcaName());
       JwtBuilder builder = Jwts.builder().setId("123")
               .setIssuedAt(now)
               .setSubject("test")
               .setIssuer("karakal")
               .signWith(signatureAlgorithm, signingKey).setExpiration(now)
               ;

       String accres_token=builder.compact();
       System.out.println(builder.compact());

       Claims claims = Jwts.parser()
               .setSigningKey(DatatypeConverter.parseBase64Binary(ss))
               .parseClaimsJws("eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxMjMiLCJpYXQiOjE1MTAwNjY4ODcsInN1YiI6InRlc3QiLCJpc3MiOiJrYXJha2FsIiwiZXhwIjoxNTEwMDY2ODg3fQ._r_elKh9ttiD9e0563-jh_Sjuc3McJCBzwwsPBUzECs").getBody();
       System.out.println("ID: " + claims.getId());
       System.out.println("Subject: " + claims.getSubject());
       System.out.println("Issuer: " + claims.getIssuer());
       System.out.println("Expiration: " + claims.getExpiration());



   }

}
