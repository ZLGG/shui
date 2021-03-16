package com.gs.lshly.facade.platform.controller.foundation.rbac;


import cn.hutool.core.collection.CollUtil;
import com.gs.lshly.common.response.ResponseData;
import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.common.struct.PermitNode;
import com.gs.lshly.common.struct.common.PermitNodeVO;
import com.gs.lshly.middleware.auth.rbac.RbacContants;
import com.gs.lshly.middleware.redis.RedisUtil;
import com.gs.lshly.rpc.api.platadmin.foundation.rbac.ISysFuncRpc;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * <p>
 * 平台功能树 前端控制器
 * </p>
 *
 * @author lxus
 * @since 2020-12-12
 */
@RestController
@RequestMapping("/platform/sys-func")
@Api(tags = "RBAC功能管理")
public class SysFuncController {

    @Autowired
    private RedisUtil redisUtil;

    @Value("${auth.terminal}")
    private String terminal;

    @DubboReference
    private ISysFuncRpc sysFuncRpc;

    private PermitNodeVO.PermitNodeTreeVO initAllPermitNodeTree(Map<String, String> frontRouterMap) {
        PermitNode allPermitNode = (PermitNode)redisUtil.get(RbacContants.SESSION_PERMIT_PREFIX + terminal + RbacContants.ALL_PERMIT);
        return convert(allPermitNode, frontRouterMap);
    }

    private PermitNodeVO.PermitNodeTreeVO convert(PermitNode node, Map<String, String> frontRouterMap) {
        PermitNodeVO.PermitNodeTreeVO tree = new PermitNodeVO.PermitNodeTreeVO();
        tree.setId(node.getId());
        tree.setLabel(node.getName());
        tree.setFrontRouter(frontRouterMap.get(node.getId()));
        if(CollUtil.isNotEmpty(node.getChildren())) {
            tree.setChildren(CollUtil.newArrayList());
            for (PermitNode subNode : node.getChildren()) {
                tree.getChildren().add(convert(subNode, frontRouterMap));
            }
        }
        return tree;
    }

    @ApiOperation("功能树")
    @GetMapping("/all")
    public ResponseData<PermitNodeVO.PermitNodeTreeVO> all() {
        Map<String, String> frontRouterMap = sysFuncRpc.frontRouterMap(new BaseDTO());
        PermitNodeVO.PermitNodeTreeVO allPermitNodeTree = initAllPermitNodeTree(frontRouterMap);
        return ResponseData.data(allPermitNodeTree);
    }
//
//    @ApiOperation("设置前端路由")
//    @PutMapping("/frontRouter")
//    public ResponseData<Void> frontRouter(SysFuncDTO.ETO dto) {
//        sysFuncRpc.setFrontRouter(dto);
//        return ResponseData.success(MsgConst.SAVE_SUCCESS);
//    }

}
