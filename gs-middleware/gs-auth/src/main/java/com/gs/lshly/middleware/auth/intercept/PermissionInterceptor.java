package com.gs.lshly.middleware.auth.intercept;

import com.gs.lshly.common.exception.BusinessException;
import com.gs.lshly.common.struct.JwtUser;
import com.gs.lshly.common.struct.PermitNode;
import com.gs.lshly.common.struct.common.PermitNodeVO;
import com.gs.lshly.common.utils.JsonUtils;
import com.gs.lshly.common.utils.JwtUtil;
import com.gs.lshly.middleware.auth.rbac.Func;
import com.gs.lshly.middleware.auth.rbac.PermitNodeUtil;
import com.gs.lshly.middleware.auth.rbac.RbacContants;
import com.gs.lshly.middleware.redis.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * @author lxus
 */
@Slf4j
@Component
public class PermissionInterceptor implements HandlerInterceptor {

    @Autowired
    private RedisUtil redisUtil;

    private PermitNode allPermitNode;

    @Value("${auth.terminal}")
    private String terminal;

    @Value("${auth.prefixs}")
    private String[] authUriPrefix;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        //无需过滤的路径
        String uri = request.getRequestURI();
        if(noAuthUri(uri)){
            log.info("不过滤路径：" + uri);
            return true;
        }
        JwtUser jwtUser = JwtUtil.session();
        //未登陆
        if (jwtUser == null) {
            throw new BusinessException("请先登陆");
        }
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method targetMethod = handlerMethod.getMethod();
        Func func = AnnotationUtils.findAnnotation(targetMethod, Func.class);
        if (func == null) {
            log.warn("接口[{}]未配置权限标识", targetMethod.getDeclaringClass().getName()+":"+targetMethod.getName());
            return true;
        }
        //超管
        if (JwtUtil.isAdmin(jwtUser)) {
            log.info("不过滤超管：" + jwtUser.getUsername());
            return true;
        }
        try {
            //获取系统权限树
            initAllPermitNode();
            //获取用户权限
            PermitNodeVO.PermitNodeTreeVO userDbPermitNode = (PermitNodeVO.PermitNodeTreeVO)redisUtil.get(RbacContants.SESSION_PERMIT_PREFIX + terminal + jwtUser.getId());
            if (userDbPermitNode == null) {
                throw new BusinessException("用户未配置权限");
            }
            log.info("userPermit：\n" + JsonUtils.toJson(userDbPermitNode));
            //请求路径对应的权限节点
//            log.info("allPermitNode：\n" + JsonUtils.toJson(allPermitNode));
            PermitNode methodPermitNode = PermitNodeUtil.toFunc(allPermitNode, func, targetMethod.getDeclaringClass(), targetMethod);
            log.info("funcNode：\n" + JsonUtils.toJson(methodPermitNode));

            //校验权限
            if (PermitNodeUtil.hasPermit(userDbPermitNode, methodPermitNode)) {
                return true;
            }
            throw new BusinessException("用户没有该功能权限");
        } catch (Throwable e) {
            log.error(e.getMessage(), e);
            throw new RuntimeException(e.getMessage(), e);
        }

    }

    public PermitNode getAllPermitNode() {
        initAllPermitNode();
        return allPermitNode;
    }

    private boolean noAuthUri(String requestURI) {
        for(String authUri : authUriPrefix){
            if (requestURI.indexOf(authUri.replace("**","")) == 0) {
                return false;
            }
        }
        return true;
    }

    private void initAllPermitNode() {
        if (allPermitNode == null) {
            allPermitNode = (PermitNode)redisUtil.get(RbacContants.SESSION_PERMIT_PREFIX + terminal + RbacContants.ALL_PERMIT);
        }
    }

    public void updateAllPermitNode(PermitNode allPermitNode) {
        redisUtil.set(RbacContants.SESSION_PERMIT_PREFIX + terminal + RbacContants.ALL_PERMIT, allPermitNode);
        this.allPermitNode = allPermitNode;
    }
}
