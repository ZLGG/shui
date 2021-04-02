package com.gs.lshly.common.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gs.lshly.common.constants.SecurityConstants;
import com.gs.lshly.common.enums.UserTypeEnum;
import com.gs.lshly.common.struct.JwtUser;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.crypto.SecretKey;
import javax.xml.bind.DatatypeConverter;
import java.util.Date;

/**
 * @author lxus
 */
@Slf4j
public class JwtUtil {

    /**
     * 生成足够的安全随机密钥，以适合符合规范的签名
     */
    private static final byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(SecurityConstants.JWT_SECRET_KEY);
    private static final SecretKey key = Keys.hmacShaKeyFor(apiKeySecretBytes);

    /**
     * 生成token
     * @param jwtUser
     * @return
     */
    public static String createToken(JwtUser jwtUser) {

        ObjectMapper mapper = new ObjectMapper();

        try {
            String jwtStr = mapper.writeValueAsString(jwtUser);
            long expiration = jwtUser.getRememberMe()!=null && jwtUser.getRememberMe()
                    ? SecurityConstants.EXPIRATION_REMEMBER : SecurityConstants.EXPIRATION;
            Date expire = new Date(System.currentTimeMillis() + expiration * 1000);

            String token = Jwts
                    .builder()
                    .setHeaderParam("type", SecurityConstants.TOKEN_TYPE)
                    .setSubject(jwtUser.getUsername())
                    .claim(SecurityConstants.ROLE_CLAIMS, jwtStr)
                    .setIssuedAt(new Date())
                    .setExpiration(expire)
                    .signWith(key, SignatureAlgorithm.HS256).compact();
            return SecurityConstants.TOKEN_PREFIX + token;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return "Jwt 生成失败";
        }
    }

    public static Claims checkJWT(String token) {
        try {
            Jws<Claims> jws = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
//            log.info("jwt str:"+jws.toString());
            return jws.getBody();
        } catch (Exception e) {
            throw e;
        }
    }

    /**
     * 获取用户角色
     * @param token
     * @return
     */
    public static JwtUser getJwtUser(String token){
        try {
            Claims claims = checkJWT(token.replace(SecurityConstants.TOKEN_PREFIX, ""));
            String jwtStr = claims.get(SecurityConstants.ROLE_CLAIMS).toString();
            ObjectMapper mapper = new ObjectMapper();
            JwtUser jwtUser = mapper.readValue(jwtStr, JwtUser.class);
            jwtUser.setExpire(claims.getExpiration());
            return jwtUser;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 是否过期
     * @return
     */
    public static boolean isExpiration(JwtUser jwtUser){
        return jwtUser.getExpire().after(new Date());
    }

    /**
     * 是否即将过期
     * @return
     */
    public static boolean nearExpire(JwtUser jwtUser){
        return (new Date(jwtUser.getExpire().getTime() - SecurityConstants.NEAR_EXPIRATION * 1000).before(new Date()));
    }

    public static final String PlatForm_Account = "";

    /**
     * 商家merchant账号
     */
    public static final String Merchant_Account = "";
    /**
     * 2c会员，有效期1年
     */
    public static final String BBC_User = "";

    /**
    /**
     * 2b会员，有效期1年
     */
    public static final String BBB_User = "";

    public static JwtUser session() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(!(authentication instanceof UsernamePasswordAuthenticationToken)){
            return null;
        }
        UsernamePasswordAuthenticationToken authenticationToken = (UsernamePasswordAuthenticationToken) authentication;
        return (JwtUser) authenticationToken.getDetails();
    }

    public static boolean isAdmin(JwtUser jwtUser) {
        return UserTypeEnum._2B商家主账号.getCode().equals(jwtUser.getType())
                || UserTypeEnum._2C商家主账号.getCode().equals(jwtUser.getType())
                || UserTypeEnum.平台超管账号.getCode().equals(jwtUser.getType());
    }

    /*public static void main(String[] args) {
        JwtUser user = new JwtUser();
        user.setId("0001");
        user.setUsername("merchant");
        user.setPassword("$2a$10$ccZj8lZoa.UHTLwaCxoQ0em//hlOurIQ9EaxuTDYjXmd.BMC4yPpm");
        user.setState(UserStateEnum.启用.getCode());
        String token = createToken(user);
        System.out.println(token);
    }

    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String pwd = encoder.encode("123456");
        System.out.println(pwd);
        System.out.println(encoder.matches("123456", pwd));
    }
    */
}
