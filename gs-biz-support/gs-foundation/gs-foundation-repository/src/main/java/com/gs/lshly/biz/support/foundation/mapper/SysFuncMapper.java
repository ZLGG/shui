package com.gs.lshly.biz.support.foundation.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gs.lshly.biz.support.foundation.entity.SysFunc;
import com.gs.lshly.common.struct.platadmin.foundation.vo.SysUserFuncVO;
import com.gs.lshly.common.struct.platadmin.foundation.vo.rbac.SysFuncVO;

import io.swagger.annotations.ApiModelProperty;

/**
 * <p>
 * 平台功能树 Mapper 接口
 * </p>
 *
 * @author lxus
 * @since 2020-12-12
 */
public interface SysFuncMapper extends BaseMapper<SysFunc> {

    @Select("select id, front_router from gs_sys_func where front_router is not null and flag=0")
    List<Map<String, String>> frontRouterMap();

    @Select("SELECT distinct f.* FROM gs_sys_user_role ur" +
            " INNER JOIN gs_sys_role r ON ur.role_id = r.id" +
            " INNER JOIN gs_sys_role_func rf ON r.id = rf.role_id" +
            " INNER JOIN gs_sys_func f ON f.id = rf.func_id" +
            " where f.flag=0 and r.flag=0 and ur.flag=0 and ur.user_id=#{userId}")
    List<SysFuncVO.List> selectUserFuncs(@Param("userId") String userId);

    @Select("SELECT f.id FROM gs_sys_role_func rf" +
            " INNER JOIN gs_sys_func f ON f.id = rf.func_id" +
            " where f.flag=0 and rf.role_id=#{roleId}")
    List<String> selectRoleFuncs(@Param("roleId") String roleId);
    
    
    @Select("select t.id,t.name,t.icon,t.idx,t.paths as frontRouter from (SELECT distinct f.* FROM gs_sys_user_role ur" +
            " INNER JOIN gs_sys_role r ON ur.role_id = r.id" +
            " INNER JOIN gs_sys_role_func rf ON r.id = rf.role_id" +
            " INNER JOIN gs_sys_func f ON f.id = rf.func_id" +
            " where f.flag=0 and r.flag=0 and ur.flag=0 and ur.user_id=#{userId} and f.parent=#{parent} order by f.idx) t")
    List<SysUserFuncVO.ListVO> selectUserFuncsByParent(@Param("userId") String userId,@Param("parent") String parent);

    /**
     * @ApiModelProperty(value = "菜单功能id")
        private String id;

        @ApiModelProperty(value = "名称")
        private String name;

        @ApiModelProperty(value = "图标")
        private String icon;

        @ApiModelProperty(value = "顺序")
        private String index;

        @ApiModelProperty("路由")
        private String frontRouter;
     * @param userId
     * @return
     */
    @Select("select t.id,t.name,t.icon,t.idx,t.paths as frontRouter from (SELECT distinct f.* FROM gs_sys_user_role ur" +
            " INNER JOIN gs_sys_role r ON ur.role_id = r.id" +
            " INNER JOIN gs_sys_role_func rf ON r.id = rf.role_id" +
            " INNER JOIN gs_sys_func f ON f.id = rf.func_id" +
            " where f.flag=0 and r.flag=0 and ur.flag=0 and ur.user_id=#{userId} and f.parent is null order by f.idx) t")
    List<SysUserFuncVO.ListVO> selectUserFuncsParent(@Param("userId") String userId);

}
