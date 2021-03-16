package com.gs.lshly.facade.bbc.security;

import com.gs.lshly.common.constants.CommonConst;
import com.gs.lshly.common.struct.AuthDTO;
import com.gs.lshly.common.struct.JwtUser;
import com.gs.lshly.middleware.auth.rbac.IMenuSet;
import com.gs.lshly.middleware.auth.rbac.MenuMessage;
import com.gs.lshly.middleware.redis.RedisUtil;
import com.gs.lshly.rpc.api.bbc.user.IBbcUserAuthRpc;
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
public class JwtBbcUserService implements UserDetailsService, IMenuSet {

    @DubboReference
    private IBbcUserAuthRpc bbcUserAuthRpc;

    @Autowired
    private RedisUtil redisUtil;

    @Value("${auth.terminal}")
    private String terminal;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AuthDTO dto = bbcUserAuthRpc.loadUserByUsername(username);
        if (dto != null) {
            return new JwtUser(dto);
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
