package com.gs.lshly.facade.merchant.security;

import com.gs.lshly.common.constants.CommonConst;
import com.gs.lshly.common.struct.AuthDTO;
import com.gs.lshly.common.struct.JwtUser;
import com.gs.lshly.middleware.auth.rbac.IMenuSet;
import com.gs.lshly.middleware.auth.rbac.MenuMessage;
import com.gs.lshly.middleware.log.Log;
import com.gs.lshly.middleware.log.aop.LogAspect;
import com.gs.lshly.middleware.redis.RedisUtil;
import com.gs.lshly.rpc.api.merchadmin.pc.merchant.IPCMerchMerchantAccountAuthRpc;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

/**
 * @author lxus
 */
@Slf4j
@Component
public class JwtMerchantService implements UserDetailsService, IMenuSet {

    @DubboReference
    private IPCMerchMerchantAccountAuthRpc pcMerchMerchantAccountAuthRpc;

    @Autowired
    private RedisUtil redisUtil;

    @Value("${auth.terminal}")
    private String terminal;


    @Override
    @Log(module = "登陆", func = "商家登陆")
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AuthDTO dto = pcMerchMerchantAccountAuthRpc.loadUserByUsername(username);
        if (dto != null) {
            JwtUser jwtUser = new JwtUser(dto);
            LogAspect.set(LogAspect.toDTO(jwtUser));
            return jwtUser;
        } else {
            throw new UsernameNotFoundException(CommonConst.USERNAME_OR_PASSWORD_INCORRECT);
        }
    }

    @Override
    public MenuMessage[] getMenus() {
        return new MenuMessage[0];
    }

    /**
     * 判断用户是否被设置过期
     * @param username
     * @return
     */
    @Override
    public Boolean isUserExpire(String username) {
        return redisUtil.get(terminal + "_user_expire_" + username) != null;
    }

    /**
     * 设置用户过期
     * @param username
     */
    @Override
    public void setUserExpire(String username) {
        redisUtil.set(terminal + "_user_expire_" + username, true, 60);
    }
}
