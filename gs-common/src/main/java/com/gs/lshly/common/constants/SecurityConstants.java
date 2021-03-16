package com.gs.lshly.common.constants;

/**
 * @author lxus
 */
public class SecurityConstants {

    /**
     * 登录的地址
     */
    public static final String AUTH_LOGIN_URL = "/auth/login";
    /**
     * 登出的地址
     */
    public static final String AUTH_LOGOUT_URL = "/auth/logout";

    /**
     * 角色的key
     **/
    public static final String ROLE_CLAIMS = "rol";

    /**
     * 5分钟后 过期
     */
    public static final long NEAR_EXPIRATION = 5 * 60L;

    /**
     * rememberMe 为 false 的时候过期时间是1个小时 (临时加到10个小时)
     */
    public static final long EXPIRATION = 60 * 60 * 10L;
    /**
     * rememberMe 为 true 的时候过期时间是7天
     */
    public static final long EXPIRATION_REMEMBER = EXPIRATION * 24 * 7L;

    /**
     * JWT签名密钥硬编码到应用程序代码中，应该存放在环境变量或.properties文件中。
     */
    public static final String JWT_SECRET_KEY = "UV/SMN=h8H*O$Qq~f6rEEt\"KwI!;uBgA/o$90S;9o215DSBY&+w^pz?,xzz0ZMz,";

    // JWT token defaults
    public static final String TOKEN_HEADER = "Authorization";
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String TOKEN_TYPE = "JWT";

    private SecurityConstants() {
    }
}
