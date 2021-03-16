package com.gs.lshly.biz.support.foundation.service.platadmin.rbac;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.gs.lshly.biz.support.foundation.entity.SysRole;
import com.gs.lshly.biz.support.foundation.entity.SysRoleFunc;
import com.gs.lshly.biz.support.foundation.repository.ISysFuncRepository;
import com.gs.lshly.biz.support.foundation.repository.ISysRoleFuncRepository;
import com.gs.lshly.biz.support.foundation.repository.ISysRoleRepository;
import com.gs.lshly.common.exception.BusinessException;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.common.PermitNodeVO;
import com.gs.lshly.common.struct.platadmin.foundation.dto.rbac.SysRoleDTO;
import com.gs.lshly.common.struct.platadmin.foundation.qto.rbac.SysRoleQTO;
import com.gs.lshly.common.struct.platadmin.foundation.vo.rbac.SysRoleVO;
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
 * @since 2020-12-12
 */
@Component
public class SysRoleServiceImpl implements ISysRoleService {

    @Autowired
    private ISysRoleRepository repository;

    @Autowired
    private ISysFuncRepository funcRepository;

    @Autowired
    private ISysRoleFuncRepository roleFuncRepository;


    @Override
    public PageData<SysRoleVO.ListVO> pageData(SysRoleQTO.QTO qoDTO) {
        QueryWrapper<SysRole> wq =  MybatisPlusUtil.query();
        MybatisPlusUtil.like(qoDTO, wq, "name");
        IPage<SysRole> page = MybatisPlusUtil.pager(qoDTO);
        repository.page(page, wq);
        return MybatisPlusUtil.toPageData(qoDTO, SysRoleVO.ListVO.class, page);
    }

    @Override
    public void add(SysRoleDTO.ETO dto) {
        this.uniqueCheck(dto);
        SysRole user = new SysRole();
        BeanUtils.copyProperties(dto, user);
        repository.save(user);
    }

    @Override
    public void update(SysRoleDTO.ETO dto) {
        this.uniqueCheck(dto);
        SysRole user = new SysRole();
        BeanUtils.copyProperties(dto, user);
        repository.updateById(user);
    }

    @Override
    public void delete(SysRoleDTO.IdDTO dto) {
        repository.removeById(dto.getId());
    }

    private void uniqueCheck(SysRoleDTO.ETO dto) {
        QueryWrapper qw = new QueryWrapper<SysRole>().eq("name", dto.getName());
        if (dto.getId() != null) {
            qw.ne("id", dto.getId());
        }
        if (repository.count(qw) > 0) {
            throw new BusinessException("已有[" + dto.getName() + "]名称的角色");
        }
    }

    /**
     * 获取角色权限集
     * @param dto
     * @return
     */
    @Override
    public List<String> permitFuncs(SysRoleDTO.IdDTO dto) {
        return funcRepository.baseMapper().selectRoleFuncs(dto.getId());
    }

    /**
     * 设置角色权限集
     * @param eto
     */
    @Override
    public void setRoleFuncPermit(SysRoleDTO.RoleFuncETO eto) {
        //1，删除之前的权限
        roleFuncRepository.remove(new QueryWrapper<SysRoleFunc>().eq("role_id", eto.getRoleId()));
        //2，设置最新的权限
        List<SysRoleFunc> list = new ArrayList<>();
        for (String funcId : eto.getFuncIds()){
            SysRoleFunc func = new SysRoleFunc();
            func.setFuncId(funcId);
            func.setRoleId(eto.getRoleId());
            list.add(func);
        }
        roleFuncRepository.saveBatch(list);
    }
}
