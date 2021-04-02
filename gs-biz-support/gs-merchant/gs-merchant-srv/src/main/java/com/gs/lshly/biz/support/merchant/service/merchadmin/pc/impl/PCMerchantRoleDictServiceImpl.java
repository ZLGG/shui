package com.gs.lshly.biz.support.merchant.service.merchadmin.pc.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.gs.lshly.biz.support.merchant.entity.MerchantAccountRole;
import com.gs.lshly.biz.support.merchant.entity.MerchantPermissionDict;
import com.gs.lshly.biz.support.merchant.entity.MerchantRoleDict;
import com.gs.lshly.biz.support.merchant.entity.MerchantRolePermission;
import com.gs.lshly.biz.support.merchant.mapper.MerchantRoleDictMapper;
import com.gs.lshly.biz.support.merchant.mapper.views.RolePermissionView;
import com.gs.lshly.biz.support.merchant.repository.IMerchantAccountRoleRepository;
import com.gs.lshly.biz.support.merchant.repository.IMerchantPermissionDictRepository;
import com.gs.lshly.biz.support.merchant.repository.IMerchantRoleDictRepository;
import com.gs.lshly.biz.support.merchant.repository.IMerchantRolePermissionRepository;
import com.gs.lshly.biz.support.merchant.service.merchadmin.pc.IPCMerchantRoleDictService;
import com.gs.lshly.common.exception.BusinessException;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.platadmin.merchant.dto.MerchantRoleDictDTO;
import com.gs.lshly.common.struct.platadmin.merchant.qto.MerchantRoleDictQTO;
import com.gs.lshly.common.struct.platadmin.merchant.vo.MerchantRoleDictVO;
import com.gs.lshly.common.utils.BeanCopyUtils;
import com.gs.lshly.common.utils.ListUtil;
import com.gs.lshly.middleware.mybatisplus.MybatisPlusUtil;
import com.gs.lshly.middleware.mybatisplus.MybatisPlusUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
* <p>
*  服务实现类
* </p>
* @author xxfc
* @since 2020-10-08
*/
@Component
public class PCMerchantRoleDictServiceImpl implements IPCMerchantRoleDictService {

    @Autowired
    private IMerchantRoleDictRepository repository;

    @Autowired
    private IMerchantRolePermissionRepository merchantRolePermissionRepository;

    @Autowired
    private IMerchantAccountRoleRepository merchantAccountRoleRepository;

    @Autowired
    private IMerchantPermissionDictRepository merchantPermissionDictRepository;

    @Autowired
    private MerchantRoleDictMapper merchantRoleDictMapper;


    @Override
    public PageData<MerchantRoleDictVO.ListVO> pageData(MerchantRoleDictQTO.QTO qto) {
        QueryWrapper<MerchantRoleDict> queryWrapper = MybatisPlusUtil.queryContainShopId(qto);
        queryWrapper.orderByDesc("cdate");
        IPage<MerchantRoleDict> page = MybatisPlusUtil.pager(qto);
        repository.page(page, queryWrapper);
        return MybatisPlusUtil.toPageData(qto, MerchantRoleDictVO.ListVO.class, page);
    }

    @Override
    public MerchantRoleDictVO.DetailVO detail(MerchantRoleDictDTO.IdDTO dto) {

        QueryWrapper<MerchantRoleDict> queryWrapperBoost = MybatisPlusUtil.query();
        queryWrapperBoost.eq("id",dto.getId());
        queryWrapperBoost.eq("shop_id",dto.getJwtShopId());
        MerchantRoleDict merchantRoleDict = repository.getOne(queryWrapperBoost);
        MerchantRoleDictVO.DetailVO detailVO = new MerchantRoleDictVO.DetailVO();
        BeanCopyUtils.copyProperties(merchantRoleDict,detailVO);
        QueryWrapper<RolePermissionView> rolePermissionQueryWrapper = MybatisPlusUtil.query();
        rolePermissionQueryWrapper.eq("rpm.role_id",dto.getId());
        List<RolePermissionView> rolePermissionViewList = merchantRoleDictMapper.selectRolePermission(rolePermissionQueryWrapper);
        detailVO.setPermissionList(new ArrayList<>());
        for(RolePermissionView viewItem:rolePermissionViewList){
            MerchantRoleDictVO.PermissionItemVO permissionItem = new MerchantRoleDictVO.PermissionItemVO();
            permissionItem.setId(viewItem.getId());
            permissionItem.setGroupName(viewItem.getGroupName());
            permissionItem.setPermissionName(viewItem.getPermissionName());
            detailVO.getPermissionList().add(permissionItem);
        }
        detailVO.setPermissionAllList(merchantPermissionDictRepository.selectPermissionDictForGroup());
        return detailVO;
    }

    @Override
    public void addMerchantRoleDict(MerchantRoleDictDTO.ETO eto) {
        if(ObjectUtils.isEmpty(eto.getPermissionIdList())){
            throw new BusinessException("创建角色必须指分配权限");
        }
        List<MerchantPermissionDict> permissionDictList = merchantPermissionDictRepository.list();
        boolean boolCheck = false;
        for(String permissionId:eto.getPermissionIdList()){
            for(MerchantPermissionDict permissionDict:permissionDictList){
                if(permissionId.equals(permissionDict.getId())){
                    boolCheck = true;
                    break;
                }
            }
        }
        if(!boolCheck){
            throw new BusinessException("非法的限权数据");
        }
        QueryWrapper<MerchantRoleDict> countWrapper = MybatisPlusUtil.query();
        countWrapper.eq("role_name",eto.getRoleName());
        int count =  repository.count(countWrapper);
        if(count > 0){
            throw new BusinessException("该角色名已存在");
        }
        MerchantRoleDict merchantRoleDict = new MerchantRoleDict();
        BeanCopyUtils.copyProperties(eto, merchantRoleDict);
        repository.save(merchantRoleDict);
        List<MerchantRolePermission> merchantPermissionDictBatchList = new ArrayList<>();
        for(String permissionId:eto.getPermissionIdList()){
            MerchantRolePermission merchantRolePermission = new MerchantRolePermission();
            merchantRolePermission.setRoleId(merchantRoleDict.getId());
            merchantRolePermission.setPermissionId(permissionId);
            merchantPermissionDictBatchList.add(merchantRolePermission);
        }
        merchantRolePermissionRepository.saveBatch(merchantPermissionDictBatchList);
    }


    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public void deleteMerchantRoleDict(MerchantRoleDictDTO.IdDTO dto) {
        //查角色是不是被帐号关联
        QueryWrapper<MerchantAccountRole> countWrapper  = MybatisPlusUtil.query();
        countWrapper.eq("role_id",dto.getId());
        int count =  merchantAccountRoleRepository.count(countWrapper);
        if(count > 0 ){
            throw new BusinessException("不能删除角色[有帐号在使用该角色]");
        }
        //删角色
        UpdateWrapper<MerchantRoleDict> removeWrapper = MybatisPlusUtil.update();
        removeWrapper.eq("id",dto.getId());
        boolean bool = repository.remove(removeWrapper);
        //删角色和权限关系
        if(bool){
            QueryWrapper<MerchantRolePermission> removePermissionWrapper = MybatisPlusUtil.query();
            removePermissionWrapper.eq("role_id",dto.getId());
            merchantRolePermissionRepository.remove(removePermissionWrapper);
        }
    }

    @Override
    public void editMerchantRoleDict(MerchantRoleDictDTO.ETO eto) {
        if(ObjectUtils.isEmpty(eto.getPermissionIdList())){
            throw new BusinessException("创建角色必须指分配权限");
        }
        List<MerchantPermissionDict> permissionDictList = merchantPermissionDictRepository.list();
        boolean boolCheck = false;
        for(String permissionId:eto.getPermissionIdList()){
            for(MerchantPermissionDict permissionDict:permissionDictList){
                if(permissionId.equals(permissionDict.getId())){
                    boolCheck = true;
                    break;
                }
            }
        }
        if(!boolCheck){
            throw new BusinessException("非法的限权数据");
        }
        QueryWrapper<MerchantRoleDict> countWrapper = MybatisPlusUtil.query();
        countWrapper.eq("role_name",eto.getRoleName());
        countWrapper.ne("id",eto.getId());
        int count =  repository.count(countWrapper);
        if(count > 0){
            throw new BusinessException("该角色名已存在");
        }
        MerchantRoleDict merchantRoleDict = new MerchantRoleDict();
        BeanCopyUtils.copyProperties(eto, merchantRoleDict);
        boolean bool = repository.save(merchantRoleDict);
        //删除角色和权限关系
        if(bool){
            QueryWrapper<MerchantRolePermission> removePermissionWrapper = MybatisPlusUtil.query();
            removePermissionWrapper.eq("role_id",eto.getId());
            merchantRolePermissionRepository.remove(removePermissionWrapper);
        }
        //保存角色和权限关系
        List<MerchantRolePermission> merchantPermissionDictBatchList = new ArrayList<>();
        for(String permissionId:eto.getPermissionIdList()){
            MerchantRolePermission merchantRolePermission = new MerchantRolePermission();
            merchantRolePermission.setRoleId(merchantRoleDict.getId());
            merchantRolePermission.setPermissionId(permissionId);
            merchantPermissionDictBatchList.add(merchantRolePermission);
        }
        merchantRolePermissionRepository.saveBatch(merchantPermissionDictBatchList);
    }



}
