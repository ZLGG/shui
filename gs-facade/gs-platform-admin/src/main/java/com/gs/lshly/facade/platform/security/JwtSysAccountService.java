package com.gs.lshly.facade.platform.security;

import cn.hutool.core.collection.CollUtil;
import com.gs.lshly.common.constants.CommonConst;
import com.gs.lshly.common.struct.AuthDTO;
import com.gs.lshly.common.struct.JwtUser;
import com.gs.lshly.common.struct.PermitNode;
import com.gs.lshly.common.struct.common.PermitNodeVO;
import com.gs.lshly.common.struct.platadmin.foundation.vo.rbac.SysFuncVO;
import com.gs.lshly.common.utils.JwtUtil;
import com.gs.lshly.middleware.auth.rbac.IMenuSet;
import com.gs.lshly.middleware.auth.rbac.MenuMessage;
import com.gs.lshly.middleware.auth.rbac.RbacContants;
import com.gs.lshly.middleware.log.Log;
import com.gs.lshly.middleware.log.aop.LogAspect;
import com.gs.lshly.middleware.redis.RedisUtil;
import com.gs.lshly.rpc.api.platadmin.foundation.rbac.ISysAccountAuthRpc;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author lxus
 */
@Slf4j
@Component("jwtAccountService")
public class JwtSysAccountService implements UserDetailsService, IMenuSet {

    @DubboReference
    private ISysAccountAuthRpc sysAccountAuthRpc;

    @Autowired
    private RedisUtil redisUtil;

    @Value("${auth.terminal}")
    private String terminal;

    @Override
    @Log(module = "登陆", func = "平台登陆")
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AuthDTO dto = sysAccountAuthRpc.loadUserByUsername(username);
        if (dto != null) {
            PermitNode allPermitNode = (PermitNode)redisUtil.get(RbacContants.SESSION_PERMIT_PREFIX + terminal + RbacContants.ALL_PERMIT);
            JwtUser jwtUser = new JwtUser(dto);
            boolean isAdmin = JwtUtil.isAdmin(jwtUser);
            PermitNodeVO.PermitNodeTreeVO treeVO = convert(allPermitNode, dto.getPermitFuncs(), isAdmin);
            redisUtil.set(RbacContants.SESSION_PERMIT_PREFIX + terminal + jwtUser.getId(), treeVO);
            jwtUser.setPermitNode(treeVO);
            LogAspect.set(LogAspect.toDTO(jwtUser));
            return jwtUser;
        } else {
            throw new UsernameNotFoundException(CommonConst.USERNAME_OR_PASSWORD_INCORRECT);
        }
    }

    private PermitNodeVO.PermitNodeTreeVO convert(PermitNode allPermitNode, List<SysFuncVO.List> permitFuncs, boolean isAdmin) {
        check(allPermitNode, permitFuncs, isAdmin);
        //过滤check不为true的节点
        return filter(allPermitNode);
    }

    private void check(PermitNode node, List<SysFuncVO.List> permitFuncs, boolean isAdmin) {
        if (isAdmin) {
            node.setChecked(true).setFrontRouter(node.getFrontRouter());
        }
        for (SysFuncVO.List vo : permitFuncs) {
            if (vo.getId().indexOf(node.getId()) == 0) {
                node.setChecked(true).setFrontRouter(vo.getFrontRouter());
            }
        }
        if(CollUtil.isNotEmpty(node.getChildren())) {
            for (PermitNode child : node.getChildren()) {
                check(child, permitFuncs, isAdmin);
            }
        }
    }

    private PermitNodeVO.PermitNodeTreeVO filter(PermitNode node) {
        PermitNodeVO.PermitNodeTreeVO treeVO = null;
        if(node.getChecked()!=null && node.getChecked()) {
            treeVO = new PermitNodeVO.PermitNodeTreeVO().setId(node.getId()).setLabel(node.getName()).setFrontRouter(node.getFrontRouter()).setChildren(CollUtil.newArrayList());
        }
        if (node.getChildren() != null && node.getChildren().size() > 0) {
            for (PermitNode child : node.getChildren()) {
                PermitNodeVO.PermitNodeTreeVO childLeaf = filter(child);
                if (childLeaf != null) {
                    treeVO.getChildren().add(childLeaf);
                }
            }
        }
        return treeVO;
    }

    @Override
    public MenuMessage[] getMenus() {
        return MenuEnum.values();
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
