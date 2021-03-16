package com.gs.lshly.biz.support.merchant.service.merchadmin.pc.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.gs.lshly.biz.support.merchant.entity.MerchantAccount;
import com.gs.lshly.biz.support.merchant.entity.MerchantAccountRole;
import com.gs.lshly.biz.support.merchant.entity.MerchantRoleDict;
import com.gs.lshly.biz.support.merchant.enums.MerchantAccountStateEnum;
import com.gs.lshly.biz.support.merchant.enums.MerchantAccountTypeEnum;
import com.gs.lshly.biz.support.merchant.enums.MerchantApplyStateEnum;
import com.gs.lshly.biz.support.merchant.mapper.MerchantAccountMapper;
import com.gs.lshly.biz.support.merchant.mapper.MerchantAccountRoleMapper;
import com.gs.lshly.biz.support.merchant.mapper.views.AccountShopView;
import com.gs.lshly.biz.support.merchant.repository.IMerchantAccountRepository;
import com.gs.lshly.biz.support.merchant.repository.IMerchantAccountRoleRepository;
import com.gs.lshly.biz.support.merchant.repository.IMerchantRoleDictRepository;
import com.gs.lshly.biz.support.merchant.service.merchadmin.pc.IPCMerchMerchantAccountService;
import com.gs.lshly.common.enums.TerminalEnum;
import com.gs.lshly.common.enums.UserTypeEnum;
import com.gs.lshly.common.exception.BusinessException;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.AuthDTO;
import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.common.struct.JwtUser;
import com.gs.lshly.common.struct.bbb.pc.user.dto.BbbUserDTO;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.dto.PCMerchMerchantAccountDTO;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.qto.PCMerchMerchantAccountQTO;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.vo.PCMerchMerchantAccountVO;
import com.gs.lshly.common.utils.BeanCopyUtils;
import com.gs.lshly.common.utils.JwtUtil;
import com.gs.lshly.common.utils.ListUtil;
import com.gs.lshly.common.utils.PwdUtil;
import com.gs.lshly.middleware.mybatisplus.MybatisPlusUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
* <p>
*  服务实现类
* </p>
* @author xxfc
* @since 2020-10-23
*/
@Component
public class PCMerchMerchantAccountServiceImpl implements IPCMerchMerchantAccountService {

    @Autowired
    private IMerchantAccountRepository repository;

    @Autowired
    private MerchantAccountMapper merchantAccountMapper;

    @Autowired
    private MerchantAccountRoleMapper merchantAccountRoleMapper;

    @Autowired
    private IMerchantRoleDictRepository merchantRoleDictRepository;

    @Autowired
    private IMerchantAccountRoleRepository merchantAccountRoleRepository;

    @Override
    public boolean checkUserName(PCMerchMerchantAccountDTO.CheckUserNameDTO dto) {
        if(StringUtils.isBlank(dto.getUserName())){
            throw new BusinessException("用户名,不能为空");
        }
        QueryWrapper<MerchantAccount> userQueryWrapper = MybatisPlusUtil.query();
        userQueryWrapper.eq("user_name",dto.getUserName());
        MerchantAccount merchantAccount = repository.getOne(userQueryWrapper);
        if(null != merchantAccount){
            throw new BusinessException("用户名已存在");
        }
        return true;
    }


    @Override
    public String regMerchantAccount(PCMerchMerchantAccountDTO.RegDTO eto) {
        if(!eto.getUserPwd().equals(eto.getUserPwdCfm())){
            throw new BusinessException("确认密码输入错误");
        }
        QueryWrapper<MerchantAccount> merchantAccountQueryWrapper = MybatisPlusUtil.query();
        merchantAccountQueryWrapper.eq("user_name",eto.getUserName());
        merchantAccountQueryWrapper.or();
        merchantAccountQueryWrapper.eq("phone",eto.getPhone());
        List<MerchantAccount> merchantAccountList =  repository.list(merchantAccountQueryWrapper);
        if(ObjectUtils.isNotEmpty(merchantAccountList)){
            for(MerchantAccount merchantAccount:merchantAccountList){
                if(merchantAccount.getUserName().equals(eto.getUserName())){
                    throw new BusinessException("用户名已注册");
                }
                if(merchantAccount.getPhone().equals(eto.getPhone())){
                    throw new BusinessException("手机号已注册");
                }
            }
        }
        //注册成功生成主帐号(所有的入驻信息是关联在这个主帐号下),平台审核的时候为帐号分配商家,审核开通店铺的时候创建详细的商品分类数据,和企业字点
        MerchantAccount merchantAccount = new MerchantAccount();
        BeanUtils.copyProperties(eto,merchantAccount);
        merchantAccount.setAccountType(MerchantAccountTypeEnum.主帐号.getCode());
        merchantAccount.setTerminal(TerminalEnum.BBB.getCode());
        merchantAccount.setAccountState(MerchantAccountStateEnum.启用.getCode());
        merchantAccount.setUserPwd(PwdUtil.encode(merchantAccount.getUserPwd()));
        repository.save(merchantAccount);

        return this.createMerchantAccountJwtToken(merchantAccount);
    }
    /** 商家帐号token **/
    private String createMerchantAccountJwtToken(MerchantAccount merchantAccount){
        AuthDTO authDTO = new AuthDTO().setId(merchantAccount.getId())
                .setType(UserTypeEnum._2B商家主账号.getCode())
                .setPassword(merchantAccount.getUserPwd())
                .setUsername(merchantAccount.getUserName())
                .setHeadImg(merchantAccount.getHeadImg())
                .setState(merchantAccount.getAccountState())
                .setShopId(merchantAccount.getShopId())
                .setPhone(merchantAccount.getPhone())
                .setMerchantId(merchantAccount.getMerchantId());
       return JwtUtil.createToken(new JwtUser(authDTO));

    }


    @Override
    public PageData<PCMerchMerchantAccountVO.ListVO> pageData(PCMerchMerchantAccountQTO.QTO qto) {

        IPage<AccountShopView> page = MybatisPlusUtil.pager(qto);
        QueryWrapper<AccountShopView> queryWrapper = MybatisPlusUtil.query();
        queryWrapper.eq("act.account_type",MerchantAccountTypeEnum.子帐号.getCode());
        queryWrapper.eq("act.shop_id",qto.getJwtShopId());
        merchantAccountMapper.mapperPage(page,queryWrapper);
        return MybatisPlusUtil.toPageData(qto, PCMerchMerchantAccountVO.ListVO.class, page);
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public void addMerchantAccount(PCMerchMerchantAccountDTO.AddDTO eto) {
        if(!eto.getUserPwdCfm().equals(eto.getUserPwd())){
            throw new BusinessException("确认输入密码失败");
        }
        if(ObjectUtils.isEmpty(eto.getRoleId())){
            throw new BusinessException("帐号没有分配角色");
        }
        QueryWrapper<MerchantAccount> queryWrapper = MybatisPlusUtil.query();
        queryWrapper.eq("user_name",eto.getUserName());
        queryWrapper.eq("shop_id",eto.getJwtShopId());
        int count = repository.count(queryWrapper);
        if(count > 0 ){
            throw new BusinessException("帐号已存在");
        }
        MerchantAccount supperMerchantAccount = repository.getById(eto.getJwtUserId());
        if(null == supperMerchantAccount){
            throw new BusinessException("管理商家帐号不存在");
        }
        MerchantAccount merchantAccount = new MerchantAccount();
        BeanCopyUtils.copyProperties(eto, merchantAccount);
        merchantAccount.setAccountType(MerchantAccountTypeEnum.子帐号.getCode());
        merchantAccount.setAccountState(MerchantAccountStateEnum.启用.getCode());
        merchantAccount.setTerminal(supperMerchantAccount.getTerminal());
        merchantAccount.setUserPwd(PwdUtil.encode(merchantAccount.getUserPwd()));
        merchantAccount.setMainAccountId(supperMerchantAccount.getId());
        merchantAccount.setShopId(supperMerchantAccount.getShopId());
        merchantAccount.setMerchantId(supperMerchantAccount.getMerchantId());
        repository.save(merchantAccount);
        //存帐号角色关系
        MerchantAccountRole merchantAccountRole = new MerchantAccountRole();
        merchantAccountRole.setAccountId(merchantAccount.getId());
        merchantAccountRole.setRoleId(eto.getRoleId());
        merchantAccountRoleRepository.save(merchantAccountRole);
    }


    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public void deleteMerchantAccount(PCMerchMerchantAccountDTO.IdDTO dto) {
        QueryWrapper<MerchantAccount> removeQueryWrapper = MybatisPlusUtil.query();
        removeQueryWrapper.eq("id",dto.getId());
        removeQueryWrapper.eq("account_type",MerchantAccountTypeEnum.子帐号.getCode());
        removeQueryWrapper.eq("shop_id",dto.getJwtShopId());
        boolean bool =  repository.remove(removeQueryWrapper);
        if(bool == false){
            throw new BusinessException("删除失败");
        }
        QueryWrapper<MerchantAccountRole> deleteQueryWrapper = MybatisPlusUtil.query();
        deleteQueryWrapper.eq("account_id",dto.getId());
        merchantAccountRoleMapper.delete(deleteQueryWrapper);
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public void editMerchantAccount(PCMerchMerchantAccountDTO.ETO eto) {
        if(ObjectUtils.isEmpty(eto.getId())){
            throw new BusinessException("ID不能为空");
        }
        if(ObjectUtils.isEmpty(eto.getRoleId())){
            throw new BusinessException("角色ID不能为空");
        }
        MerchantAccount merchantAccount = new MerchantAccount();
        BeanCopyUtils.copyProperties(eto, merchantAccount);
        UpdateWrapper<MerchantAccount> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("id",eto.getId());
        updateWrapper.eq("account_type",MerchantAccountTypeEnum.子帐号.getCode());
        updateWrapper.eq("shop_id",eto.getJwtShopId());
        boolean bool = repository.update(merchantAccount,updateWrapper);
        if(bool == true){
            //删除帐号角色
            QueryWrapper<MerchantAccountRole> deleteQueryWrapper = MybatisPlusUtil.query();
            deleteQueryWrapper.eq("account_id",eto.getId());
            merchantAccountRoleMapper.delete(deleteQueryWrapper);
            //存帐号角色关系
            MerchantAccountRole merchantAccountRole = new MerchantAccountRole();
            merchantAccountRole.setAccountId(merchantAccount.getId());
            merchantAccountRole.setRoleId(eto.getRoleId());
            merchantAccountRoleRepository.save(merchantAccountRole);
        }
    }

    @Override
    public void updatePassworld(PCMerchMerchantAccountDTO.PassworldETO eto) {
        if(ObjectUtils.isEmpty(eto.getId())){
            throw new BusinessException("ID不能为空");
        }
        if(!eto.getUserPwdCfm().equals(eto.getUserPwd())){
            throw new BusinessException("确认输入密码失败");
        }
        MerchantAccount merchantAccount = new MerchantAccount();
        BeanCopyUtils.copyProperties(eto, merchantAccount);
        UpdateWrapper<MerchantAccount> updatePassworldWrapper = new UpdateWrapper<>();
        updatePassworldWrapper.eq("id",eto.getId());
        updatePassworldWrapper.eq("account_type",MerchantAccountTypeEnum.子帐号.getCode());
        updatePassworldWrapper.eq("shop_id",eto.getJwtShopId());
        boolean bool = repository.update(merchantAccount,updatePassworldWrapper);
        if(bool == false){
            throw new BusinessException("修改密码失败");
        }
    }


    @Override
    public PCMerchMerchantAccountVO.DetailVO detailMerchantAccount(PCMerchMerchantAccountDTO.IdDTO dto) {
        QueryWrapper<AccountShopView> queryWrapper = MybatisPlusUtil.query();
        queryWrapper.eq("act.id",dto.getId());
        AccountShopView accountShopView = merchantAccountMapper.mapperOne(queryWrapper);
        if(null == accountShopView){
            throw new BusinessException("帐号不存在");
        }
        PCMerchMerchantAccountVO.DetailVO detailVo = new PCMerchMerchantAccountVO.DetailVO();
        BeanCopyUtils.copyProperties(accountShopView, detailVo);
        //查询所有的角色
        QueryWrapper<MerchantRoleDict> merchantRoleDictWrapper = MybatisPlusUtil.query();
        List<MerchantRoleDict> merchantRoleDictList = merchantRoleDictRepository.list(merchantRoleDictWrapper);
        detailVo.setRoleList(ListUtil.listCover(PCMerchMerchantAccountVO.RoleItemVO.class,merchantRoleDictList));
        return detailVo;
    }


    @Override
    public PCMerchMerchantAccountVO.CheckShopVO checkShop(BaseDTO dto) {
        PCMerchMerchantAccountVO.CheckShopVO checkShopVO = new PCMerchMerchantAccountVO.CheckShopVO();
        checkShopVO.setShopId(dto.getJwtShopId());
        return checkShopVO;
    }

    @Override
    public PCMerchMerchantAccountVO.ListVO innnerDetailMerchantAccount(String phoneNum) {
        if (ObjectUtils.isEmpty(phoneNum)){
            return null;
        }
        QueryWrapper<MerchantAccount> query = MybatisPlusUtil.query();
        query.and(i->i.eq("phone",phoneNum));
        query.last("limit 0,1");
        MerchantAccount one = repository.getOne(query);
        if (ObjectUtils.isEmpty(one)){
            return null;
        }
        PCMerchMerchantAccountVO.ListVO listVO = new PCMerchMerchantAccountVO.ListVO();
        BeanUtils.copyProperties(one,listVO);
        return listVO;
    }


}
