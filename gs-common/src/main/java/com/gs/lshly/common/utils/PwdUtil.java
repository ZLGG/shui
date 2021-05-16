package com.gs.lshly.common.utils;

import cn.hutool.crypto.SecureUtil;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * 密码工具类
 */
public class PwdUtil {

    public static String encode(String password) {
        return getEncoder().encode(SecureUtil.md5(password));
    }

    public static PasswordEncoder getEncoder() {
        return new BCryptPasswordEncoder();
    }

    public static boolean matches(String rawPassword, String encodedPassword){
        return getEncoder().matches(SecureUtil.md5(rawPassword), encodedPassword);
    }

    public static void main(String[] args) {
        String pwd1 = encode("123456");
        System.out.println(pwd1);
        String pwd2 = encode("123456");
        System.out.println(pwd2);
        System.out.println(matches("123456", pwd1));

        System.out.println(matches("123456", pwd2));
        
        System.out.println(matches("123456qq", "$2a$10$8kruZbdH2iUprpUps3CAHusIL8oQCld62OJhE0h1abU3cNNID9BXq"));
    }

}