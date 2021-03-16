package com.gs.lshly.middleware.auth.config;

import cn.hutool.core.util.ClassUtil;
import com.gs.lshly.common.struct.PermitNode;
import com.gs.lshly.common.utils.JsonUtils;
import com.gs.lshly.middleware.auth.intercept.PermissionInterceptor;
import com.gs.lshly.middleware.auth.rbac.*;
import com.gs.lshly.middleware.redis.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.PostConstruct;
import java.lang.reflect.Method;
import java.util.*;

/**
 * 权限配置
 * @author lxus
 * @since 2020-12-08
 */
@Configuration
@Slf4j
@Order()
public class RbacConfig implements ApplicationContextAware, WebMvcConfigurer {

    private PermitNode rootPermitNode = new PermitNode().setId(rootId)
            .setCode(rootId).setName("所有功能")
            .setType(PermitNodeTypeEnum.根节点.getCode());

    private ApplicationContext applicationContext;

    private static final String rootId = "root";

    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private PermissionInterceptor permissionInterceptor;

    @Value("${auth.terminal}")
    private String terminal;

    @Value("${auth.bean}")
    private String authBeanName;

    @Value("${auth.prefixs}")
    private String[] authUriPrefix;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        log.info("rbac拦截路径：" + Arrays.toString(authUriPrefix));
        registry.addInterceptor(permissionInterceptor).addPathPatterns(authUriPrefix);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @PostConstruct
    public void run() {
        IMenuSet menuSet = (IMenuSet) applicationContext.getBean(authBeanName);
        if (menuSet == null){
            log.info("auth.bean未配置");
            return;
        }
        if(menuSet.getMenus() == null || menuSet.getMenus().length == 0) {
            log.info("菜单未配置");
            return;
        }
        //初始化顶部菜单
        for (MenuMessage topMenu : menuSet.getMenus()) {
            PermitNodeUtil.addModule(rootPermitNode, PermitNodeUtil.toTopMenu(topMenu, rootPermitNode.getCode()));
        }

        log.info("===================开始加载权限========================");
        Map<String, Object> controllers = applicationContext.getBeansWithAnnotation(RestController.class);
        List<Method> errorMethods = new ArrayList<>();
        addModule(controllers.values(), errorMethods);
        redisUtil.set(RbacContants.SESSION_PERMIT_PREFIX + terminal + RbacContants.ALL_PERMIT, rootPermitNode);
        log.info(JsonUtils.toJson(rootPermitNode) + "\n===================权限树加载完成========================");
    }

    private void addModule(Collection<Object> controllers, List<Method> errorMethods) {
        List<Object> laveControllers = new ArrayList<>();
        for(Object controller : controllers) {
            Class clazz = controller.getClass();
            Module module = AnnotationUtils.findAnnotation(clazz, Module.class);
            if (module == null) {
                continue;
            }
            boolean moduleAdded = PermitNodeUtil.addModule(rootPermitNode, PermitNodeUtil.toModule(module, clazz));
            if (moduleAdded) {
                //处理功能点
                Method[] methods = ClassUtil.getDeclaredMethods(clazz);
                for(Method method : methods) {
                    Func func = AnnotationUtils.findAnnotation(method, Func.class);
                    if (func == null) {
                        continue;
                    }
                    boolean funcAdded = PermitNodeUtil.addModule(rootPermitNode, PermitNodeUtil.toFunc(func, clazz, method));
                    if (!funcAdded) {
                        errorMethods.add(method);
                    }
                }
            } else {
                laveControllers.add(controller);
            }
        }
        if (laveControllers.size() == controllers.size()) {
            return;
        }
        addModule(laveControllers, errorMethods);
    }
}
