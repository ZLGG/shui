package com.gs.lshly.common.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * 密码工具类
 */
public class PwdUtil {

    public static String encode(String password) {
        PasswordEncoder encoder = encoder();
        return encoder.encode(password);
    }

    public static PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    public static void main(String[] args) {
        String pwd1 = encode("123456");
        System.out.println(pwd1);
        String pwd2 = encode("123456");
        System.out.println(pwd2);
        System.out.println(encoder().matches("123456", pwd1));

        System.out.println(encoder().matches("123456", pwd2));
    }

}
