package com.gs.lshly.middleware.auth.rbac;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.collection.ListUtil;
import com.gs.lshly.common.struct.PermitNode;
import com.gs.lshly.common.struct.common.PermitNodeVO;
import com.gs.lshly.common.utils.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * 菜单-模块-功能，工具类
 * @author lxus
 * @since 2020-12-09
 */
@Slf4j
public class PermitNodeUtil {

    public static boolean addModule(PermitNode root, PermitNode node) {

        if (root.getCode().equals(node.getParent())) {
            addChild(root, node.setId(root.getId()+"."+node.getCode()));
            return true;
        }

        List<PermitNode> children = root.getChildren();
        if (children != null) {
            for (PermitNode child : children) {
                boolean matched = addModule(child, node);
                if (matched) {
                    return true;
                }
            }
        }
        return false;
    }

    private static void addChild(PermitNode parent, PermitNode child) {
        List<PermitNode> children = parent.getChildren() != null ? parent.getChildren() : new ArrayList<>();
        if (!children.contains(child)) {
            children.add(child);
            CollUtil.sort(children, (o1, o2) -> o1.getIndex() != null && o2.getIndex() != null ? o1.getIndex() - o2.getIndex() : 0);
            parent.setChildren(children);
        } else {
            children.forEach(permitNode -> {
                if (permitNode.equals(child)) {
                    permitNode.getLocations().addAll(child.getLocations());
                    permitNode.getPaths().addAll(child.getPaths());
                }
            });
        }
    }

    public static PermitNode toTopMenu(MenuMessage topMenu, String parent) {
        return new PermitNode().setCode(topMenu.getCode()).setName(topMenu.getName())
                .setType(PermitNodeTypeEnum.模块.getCode()).setIndex(topMenu.getIndex())
                .setParent(parent);
    }

    public static PermitNode toModule(Module module, Class<?> clazz) {
        return new PermitNode().setCode(module.code()).setName(module.name())
                .setType(PermitNodeTypeEnum.模块.getCode()).setIndex(module.index())
                .setLocations(CollUtil.newHashSet(clazz.getSimpleName()))
                .setPaths(CollUtil.newHashSet())
                .setParent(module.parent());
    }

    public static PermitNode toFunc(Func func, Class clazz, Method method) {
        return new PermitNode().setCode(func.code()).setName(func.name())
                .setType(PermitNodeTypeEnum.功能.getCode()).setIndex(func.index())
                .setLocations(CollUtil.newHashSet(method.getDeclaringClass().getSimpleName() + ":" + method.getName()))
                .setPaths(CollUtil.newHashSet(path(method)))
                .setParent(AnnotationUtils.findAnnotation(clazz, Module.class).code());
    }

    public static PermitNode toFunc(PermitNode allPermitNode, Func func, Class<?> declaringClass, Method method) {
        PermitNode funcNode = toFunc(func, declaringClass, method);
        boolean bind = bindFuncId(allPermitNode, funcNode);
        if (!bind) {
            log.error("未能成功绑定权限节点:"+ JsonUtils.toJson(funcNode));
        }
        return funcNode;
    }

    private static boolean bindFuncId(PermitNode root, PermitNode funcNode) {
        if (root.getChildren() == null) {
            return false;
        }
        for (PermitNode child : root.getChildren()) {
            log.info("child.getPaths():"+ CollUtil.join(child.getPaths(),","));
            if (child.getPaths()!=null && child.getPaths().containsAll(funcNode.getPaths())) {
                funcNode.setId(child.getId());
                return true;
            } else {
                boolean binded = bindFuncId(child, funcNode);
                if (binded) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean hasPermit(PermitNodeVO.PermitNodeTreeVO userPermitNode, PermitNode methodPermitNode) {
        return funcIdMatched(userPermitNode, methodPermitNode);
    }

    private static boolean funcIdMatched(PermitNodeVO.PermitNodeTreeVO root, PermitNode funcNode) {
        if (root.getChildren() == null) {
            return false;
        }
        for (PermitNodeVO.PermitNodeTreeVO child : root.getChildren()) {
            if (child.getId().equals(funcNode.getId())) {
                return true;
            } else {
                boolean matched = funcIdMatched(child, funcNode);
                if (matched) {
                    return true;
                }
            }
        }
        return false;
    }

    private static String[] path(Method method) {
        String[] paths = new String[]{};
        RequestMethod requestMethod = null;
        RequestMapping requestMapping = AnnotationUtils.findAnnotation(method, RequestMapping.class);
        if (requestMapping != null) {
            paths = requestMapping.path();
            requestMethod = requestMapping.method()[0];
        }
        GetMapping getMapping = AnnotationUtils.findAnnotation(method, GetMapping.class);
        if (getMapping != null) {
            paths = getMapping.path();
            requestMethod = RequestMethod.GET;
        }
        PostMapping postMapping = AnnotationUtils.findAnnotation(method, PostMapping.class);
        if (postMapping != null) {
            paths = postMapping.path();
            requestMethod = RequestMethod.POST;
        }
        PutMapping putMapping = AnnotationUtils.findAnnotation(method, PutMapping.class);
        if (putMapping != null) {
            paths = putMapping.path();
            requestMethod = RequestMethod.PUT;
        }
        DeleteMapping deleteMapping = AnnotationUtils.findAnnotation(method, DeleteMapping.class);
        if (deleteMapping != null) {
            paths = deleteMapping.path();
            requestMethod = RequestMethod.DELETE;
        }
        //拼接类路径
        RequestMapping clazzMapping = AnnotationUtils.findAnnotation(method.getDeclaringClass(), RequestMapping.class);
        for (int i = 0; i < paths.length; i++) {
            // 待验证 .replaceAll("\\{[^}]*\\}","")
            paths[i] = (requestMethod.name() + ":" + clazzMapping.path()[0] + "/" + paths[i])
                    .replace("//", "/");
        }
        return paths;
    }

}
