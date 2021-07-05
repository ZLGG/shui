package com.gs.lshly.biz.support.user.service.common.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.gs.lshly.biz.support.user.entity.User;
import com.gs.lshly.biz.support.user.entity.UserCtcc;
import com.gs.lshly.biz.support.user.repository.IUserCtccRepository;
import com.gs.lshly.biz.support.user.repository.IUserRepository;
import com.gs.lshly.biz.support.user.service.common.ICommonUserService;
import com.gs.lshly.common.struct.common.CommonUserVO;
import com.gs.lshly.common.struct.common.CommonUserVO.UserCtccDetailVO;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.vo.PCMerchMerchantAccountVO;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.vo.PCMerchShopVO;
import com.gs.lshly.common.utils.BeanCopyUtils;
import com.gs.lshly.common.utils.ListUtil;
import com.gs.lshly.middleware.mybatisplus.MybatisPlusUtil;
import com.gs.lshly.rpc.api.common.ILegalDictRpc;
import com.gs.lshly.rpc.api.merchadmin.pc.merchant.IPCMerchMerchantAccountRpc;
import com.gs.lshly.rpc.api.merchadmin.pc.merchant.IPCMerchShopRpc;

/**
* <p>
*  服务实现类
* </p>
* @author xxfc
* @since 2020-10-05
*/
@Component
public class CommonUserServiceImpl implements ICommonUserService {

    @Autowired
    private IUserRepository repository;
    @Autowired
    private IUserCtccRepository userCtccRepository;
    
    @DubboReference
    private ILegalDictRpc iLegalDictRpc;
    @DubboReference
    private IPCMerchMerchantAccountRpc ipcMerchMerchantAccountRpc;
    @DubboReference
    private IPCMerchShopRpc ipcMerchShopRpc;
    


    @Override
    public CommonUserVO.DetailVO details(String userId) {
        if(StringUtils.isBlank(userId)){
            return null;
        }
        User user =  repository.getById(userId);
        if(null == user){
           return null;
        }
        CommonUserVO.DetailVO detailVO = new CommonUserVO.DetailVO();
        BeanUtils.copyProperties(user,detailVO);
        return detailVO;
    }

    @Override
    public CommonUserVO.DetailVO detailsByUserName(String userName) {

        if(StringUtils.isBlank(userName)){
            return null;
        }
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_name",userName);
        User user =  repository.getOne(queryWrapper);
        if(null == user){
            return null;
        }
        CommonUserVO.DetailVO detailVO = new CommonUserVO.DetailVO();
        BeanUtils.copyProperties(user,detailVO);
        return detailVO;
    }


    @Override
    public List<CommonUserVO.DetailVO> moreDetail(List<String> userIdList) {
        if(ObjectUtils.isEmpty(userIdList)){
            return new ArrayList<>();
        }
        List<User> userList = repository.listByIds(userIdList);
        return ListUtil.listCover(CommonUserVO.DetailVO.class,userList);
    }

    @Override
    public CommonUserVO.selectUserIdByShopIdVO selectUserIdByShopId(String userId) {
        User user = repository.getById(userId);
        if (ObjectUtils.isEmpty(user)){
           return null;
        }
        String phone = user.getUserName();
        if (StringUtils.isBlank(phone)){
           return null;
        }
        PCMerchMerchantAccountVO.ListVO listVO = ipcMerchMerchantAccountRpc.innnerDetailMerchantAccount(phone);
        System.out.println("list______-------~~~~"+listVO);
        if (ObjectUtils.isEmpty(listVO)){
            return  null;
        }
        PCMerchShopVO.ShopSimpleVO shopSimpleVO = ipcMerchShopRpc.innerShopSimple(listVO.getShopId());
        if (ObjectUtils.isEmpty(shopSimpleVO)){
            return null;
        }
        return new CommonUserVO.selectUserIdByShopIdVO(shopSimpleVO.getPosShopId());
    }

	@Override
	public UserCtccDetailVO userCtccDetails(String userId) {
		if(StringUtils.isBlank(userId)){
            return null;
        }
        User user =  repository.getById(userId);
        if(null == user){
           return null;
        }
        CommonUserVO.UserCtccDetailVO userCtccDetailVO = new CommonUserVO.UserCtccDetailVO();
        BeanUtils.copyProperties(user,userCtccDetailVO);
        
        QueryWrapper<UserCtcc> userCtccWrapper = MybatisPlusUtil.query();
        userCtccWrapper.eq("user_id",userId);
        UserCtcc userCtcc = userCtccRepository.getOne(userCtccWrapper);
        
        BeanCopyUtils.copyProperties(userCtcc, userCtccDetailVO);
		return userCtccDetailVO;
	}

}
