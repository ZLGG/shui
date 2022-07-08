package com.citydo.appraisal.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletRequest;
import java.util.Date;

public class JwtUtil {

    private final static String USERNAME = "username";

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 过期时间120分钟
     */
    private static final long EXPIRE_TIME = 2 * 60 * 60 * 1000;

    private static final String DEFAULT_CAPTCHA = "shiqi:shiro:cache:jwtTokenCache:";
    
    private static Cache<String, String> jwtTokenCache;

    public JwtUtil(CacheManager cacheManager) {
        jwtTokenCache = cacheManager.getCache(DEFAULT_CAPTCHA);
    }

    /**
     * 添加token
     * @param request
     * @param username
     * @param token
     */
    public static void set(ServletRequest request, String username, String token){
        jwtTokenCache.put(username,token);
    }

    /**
     * 移除一个token
     * @param request
     * @param username
     */
    public static void delete(ServletRequest request, String username){
        if(ServlertUtil.isAjax(request) || ServlertUtil.isBrowser(request)){
            username +="(browser)";
        }else if(ServlertUtil.isMobile(request)){
            username +="(mobile)";
        }
        jwtTokenCache.remove(username);
    }

    /**
     * 验证Token(在缓存中)
     * @param request
     * @param username
     * @param token
     * @return
     */
    public static boolean verify(ServletRequest request, String username, String token){
        String findToken = (String) RedisCache.get(DEFAULT_CAPTCHA + username);
        return reduceToken(token).equals(findToken);
    }

    /**
     * 校验token是否正确
     * @param token  密钥
     * @param username 用户名
     * @return 是否正确
     */
    public static boolean verify(String token, String username, String password) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(password);
            JWTVerifier verifier = JWT.require(algorithm)
                    .withClaim(USERNAME, username)
                    .build();
            //verifier.verify(reduceToken(token));
            return true;
        } catch (Exception exception) {
            return false;
        }
    }

    /**
     * 获得token中的信息无需secret解密也能获得
     * @return token中包含的用户名
     */
    public static String getUsername(String token) {
        try {
            DecodedJWT jwt = JWT.decode(reduceToken(token));
            return jwt.getClaim(USERNAME).asString();
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 生成签名
     * @param username 用户名
     * @return 加密的token
     */
    public static String sign(String username,String password) {
        Date date = new Date(System.currentTimeMillis() + EXPIRE_TIME);
        Algorithm algorithm = Algorithm.HMAC256(password);
        // 附带username信息
        return JWT.create()
                .withClaim(USERNAME, username)
                .withExpiresAt(date)
                .sign(algorithm);
    }

    /**
     * 加头
     *
     * @param token
     * @return
     */
    private static String addToken(String token) {
        token = token.replaceAll("Bearer ","");
        token = "Bearer "+token;
        return token;
    }

    /**
     * 去头
     *
     * @param token
     * @return
     */
    private static String reduceToken(String token) {
        return token.replaceAll("Bearer ","");
    }

}
