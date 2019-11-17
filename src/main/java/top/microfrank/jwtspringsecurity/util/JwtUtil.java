package top.microfrank.jwtspringsecurity.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * @author Frank
 * @date 2019/11/17 11:51
 */
@Component
public class JwtUtil {

    // jwt中的secret设置
    private String SECRET_KEY = "secret";

    /**
     * 生成token
     * @param subject jwt的payload部分
     * @return token字符串
     */
    public String generateToken(String subject) {
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, subject);
    }

    /**
     * 验证token合法性
     * @param token 浏览器传过来的token
     * @param subject 检查是否和token中一致
     * @return
     */
    public Boolean validateToken(String token, String subject) {
        final String username = extractSubject(token);
        return (username.equals(subject) && !isTokenExpired(token));
    }


    // 拿出subject（存的是username）
    public String extractSubject(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    // 拿出过期时间
    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }


    private  <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }
    private Claims extractAllClaims(String token) {
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
    }

    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }



    private String createToken(Map<String, Object> claims, String subject) {
        return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY).compact();
    }


}
