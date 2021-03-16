package com.gs.lshly.biz.support.foundation.service.platadmin.rbac;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gs.lshly.biz.support.foundation.entity.SysFunc;
import com.gs.lshly.biz.support.foundation.entity.SysRoleFunc;
import com.gs.lshly.biz.support.foundation.repository.ISysFuncRepository;
import com.gs.lshly.biz.support.foundation.repository.ISysRoleFuncRepository;
import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.common.struct.PermitNode;
import com.gs.lshly.common.struct.platadmin.foundation.dto.rbac.SysFuncDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@Slf4j
public class SysFuncServiceImpl implements ISysFuncService {

    @Autowired
    private ISysFuncRepository sysFuncRepository;

    @Autowired
    private ISysRoleFuncRepository sysRoleFuncRepository;

    @Override
    public Map<String, String> frontRouterMap(BaseDTO dto) {
        List<Map<String, String>> listMap = sysFuncRepository.baseMapper().frontRouterMap();
        Map<String, String> resultMap = new HashMap<String, String>();
        for (Map<String, String> map : listMap) {
            String id = "";
            String frontRouter = "";
            for (Map.Entry<String, String> entry : map.entrySet()) {
                if ("id".equalsIgnoreCase(entry.getKey())) {
                    id = entry.getValue();
                }
                if ("front_router".equalsIgnoreCase(entry.getKey())) {
                    frontRouter = entry.getValue();
                }
            }
            resultMap.put(id, frontRouter);
        }
        return resultMap;
    }

    @Override
    public void setFrontRouter(SysFuncDTO.ETO dto) {
        SysFunc func = sysFuncRepository.getById(dto.getId());
        if (func != null) {
            func.setFrontRouter(dto.getFrontRouter());
            sysFuncRepository.updateById(func);
        } else {
            func = new SysFunc().setId(dto.getId()).setFrontRouter(dto.getFrontRouter());
            sysFuncRepository.save(func);
        }
    }

    private void addToList(List<SysFunc> funcs, PermitNode node, List<SysFunc> funcsInDB) {
        SysFunc func = new SysFunc().setId(node.getId()).setName(node.getName()).setIcon(node.getIcon()).setIdx(node.getIndex())
                .setParent(node.getParent()).setType(node.getType())
                .setLocations(CollUtil.join(node.getLocations(), ","))
                .setPaths(CollUtil.join(node.getPaths(), ","));
        //不更新已经手动配置过的节点
        for(SysFunc hasFunc : funcsInDB){
            if (hasFunc.equals(func)) {
                log.info("节点[{}]：不更新名称、图标、顺序字段", func.getId());
                func.setName(hasFunc.getName()).setIcon(hasFunc.getIcon())
                        .setIdx(hasFunc.getIdx()).setFrontRouter(hasFunc.getFrontRouter());
            }
        }
        funcs.add(func);

        if (CollUtil.isNotEmpty(node.getChildren())) {
            for (PermitNode child : node.getChildren()) {
                addToList(funcs, child, funcsInDB);
            }
        }
    }

    @Override
    public PermitNode initFuncTree2DB(PermitNode allPermitNode) {

        List<SysFunc> funcs = CollUtil.newArrayList();
        //查询已有的全部数据
        List<SysFunc> funcsInDB = sysFuncRepository.list();
        addToList(funcs, allPermitNode, funcsInDB);

        List<SysFunc> addFuncs = CollUtil.newArrayList();
        List<SysFunc> editFuncs = CollUtil.newArrayList();
        for (SysFunc func : funcs) {
            if (funcsInDB.contains(func)) {
                editFuncs.add(func);
                funcsInDB.remove(func);
            } else {
                addFuncs.add(func);
            }
        }
        //持久化功能权限
        if(addFuncs.size()>0) {
            sysFuncRepository.saveBatch(addFuncs);
        }
        if(editFuncs.size()>0) {
            sysFuncRepository.updateBatchById(editFuncs);
        }
        if (funcsInDB.size() > 0) {
            List<String> deletedIds = CollUtil.getFieldValues(funcsInDB, "id", String.class);
            //删除已不存在的功能权限
            sysFuncRepository.removeByIds(deletedIds);
            //删除已不存在的功能权限配置(role_func)
            sysRoleFuncRepository.remove(new QueryWrapper<SysRoleFunc>().in("func_id", deletedIds));
        }
        return allPermitNode;
    }
}
