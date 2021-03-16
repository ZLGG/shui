package com.gs.lshly.biz.support.foundation.service.platadmin.rbac;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.gs.lshly.biz.support.foundation.entity.SysUser;
import com.gs.lshly.biz.support.foundation.entity.SysUserRole;
import com.gs.lshly.biz.support.foundation.repository.ISysFuncRepository;
import com.gs.lshly.biz.support.foundation.repository.ISysRoleRepository;
import com.gs.lshly.biz.support.foundation.repository.ISysUserRepository;
import com.gs.lshly.biz.support.foundation.repository.ISysUserRoleRepository;
import com.gs.lshly.common.enums.SysUserTypeEnum;
import com.gs.lshly.common.enums.UserStateEnum;
import com.gs.lshly.common.enums.UserTypeEnum;
import com.gs.lshly.common.exception.BusinessException;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.AuthDTO;
import com.gs.lshly.common.struct.platadmin.foundation.dto.rbac.SysUserDTO;
import com.gs.lshly.common.struct.platadmin.foundation.qto.rbac.SysUserQTO;
import com.gs.lshly.common.struct.platadmin.foundation.vo.rbac.SysFuncVO;
import com.gs.lshly.common.struct.platadmin.foundation.vo.rbac.SysUserVO;
import com.gs.lshly.common.utils.PwdUtil;
import com.gs.lshly.middleware.mybatisplus.MybatisPlusUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 * @author lxus
 * @since 2020-09-14
 */
@Component
public class SysUserServiceImpl implements ISysUserService {

    @Autowired
    private ISysUserRepository repository;

    @Autowired
    private ISysUserRoleRepository userRoleRepository;

    @Autowired
    private ISysRoleRepository roleRepository;

    @Autowired
    private ISysFuncRepository funcRepository;

    @Override
    public PageData<SysUserVO.ListVO> pageData(SysUserQTO.QTO qoDTO) {
        QueryWrapper<SysUser> wq =  MybatisPlusUtil.query();
        MybatisPlusUtil.like(qoDTO, wq, "name");
        IPage<SysUser> page = MybatisPlusUtil.pager(qoDTO);
        repository.page(page, wq);
        return MybatisPlusUtil.toPageData(qoDTO, SysUserVO.ListVO.class, page);
    }

    @Override
    public void saveUser(SysUserDTO.ETO dto) {
        this.uniqueCheck(dto);
        SysUser user = new SysUser();
        BeanUtils.copyProperties(dto, user);
        user.setPwd(PwdUtil.encode(dto.getPwd()));
        repository.save(user);
    }

    @Override
    public void editUser(SysUserDTO.ETO dto) {
        this.uniqueCheck(dto);
        SysUser user = new SysUser();
        BeanUtils.copyProperties(dto, user);
        if (StrUtil.isNotBlank(dto.getPwd())) {
            user.setPwd(PwdUtil.encode(dto.getPwd()));
        }
        repository.updateById(user);
    }

    @Override
    public void delete(SysUserDTO.IdDTO dto) {
        repository.removeById(dto.getId());
    }

    @Override
    public void enable(SysUserDTO.IdDTO dto) {
        repository.updateById(new SysUser().setId(dto.getId()).setState(UserStateEnum.启用.getCode()));
    }

    @Override
    public void disable(SysUserDTO.IdDTO dto) {
        repository.updateById(new SysUser().setId(dto.getId()).setState(UserStateEnum.停用.getCode()));
    }

    @Override
    public SysUserVO.DetailVO detail(SysUserDTO.IdDTO dto) {
        SysUser user = repository.getById(dto.getId());
        SysUserVO.DetailVO detail = new SysUserVO.DetailVO();
        BeanUtils.copyProperties(user, detail);
        return detail;
    }

    @Override
    public void changePassword(SysUserDTO.EditPasswordDTO dto) {
        SysUser user = repository.getById(dto.getId());
        if (user == null) {
            throw new BusinessException("用户未找到");
        }
        if (user.getPwd().equals(dto.getOldPwd())) {
            repository.updateById(new SysUser().setId(user.getId()).setPwd(dto.getNewPwd()));
        } else {
            throw new BusinessException("旧密码错误");
        }
    }

    @Override
    public void uniqueCheck(SysUserDTO.ETO dto) {
        QueryWrapper qw = new QueryWrapper<SysUser>().eq("name", dto.getName());
        if (dto.getId() != null) {
            qw.ne("id", dto.getId());
        }
        if (repository.count(qw) > 0) {
            throw new BusinessException("已有[" + dto.getName() + "]登陆名的管理账号");
        }
    }

    @Override
    public AuthDTO findByUserName(String username) {
        SysUser user = repository.getOne(new QueryWrapper<SysUser>().eq("name", username));
        if (user == null) {
            return null;
        }
        AuthDTO authDTO = new AuthDTO().setId(user.getId())
                .setType(user.getType() != null && user.getType().equals(SysUserTypeEnum.运营管理账号.getCode())
                        ? UserTypeEnum.平台运营账号.getCode()
                        : UserTypeEnum.平台超管账号.getCode())
                .setPassword(user.getPwd())
                .setUsername(user.getName())
                .setState(user.getState());
        //1，查询用户角色
        List<SysFuncVO.List> funcs = funcRepository.baseMapper().selectUserFuncs(user.getId());
        return authDTO.setPermitFuncs(funcs);
    }

    /**
     * 账号角色列表
     * @param dto
     * @return
     */
    @Override
    public List<SysUserVO.UserRoleVO> roles(SysUserDTO.IdDTO dto) {
        return roleRepository.baseMapper().userRoles(dto.getId());
    }

    /**
     * 添加账号角色
     * @param eto
     */
    @Override
    public void addUserRolePermit(SysUserDTO.UserRoleETO eto) {
        List<SysUserRole> userRoles = new ArrayList<>();
        for (String roleId : eto.getRoleIds()) {
            userRoles.add(new SysUserRole().setRoleId(roleId).setUserId(eto.getUserId()));
        }
        userRoleRepository.saveBatch(userRoles);
    }

    /**
     * 删除账号角色
     * @param eto
     */
    @Override
    public void deleteUserRolePermit(SysUserDTO.UserRoleETO eto) {
        userRoleRepository.remove(new QueryWrapper<SysUserRole>().eq("user_id", eto.getUserId()).in("role_id", eto.getRoleIds()));
    }
}
