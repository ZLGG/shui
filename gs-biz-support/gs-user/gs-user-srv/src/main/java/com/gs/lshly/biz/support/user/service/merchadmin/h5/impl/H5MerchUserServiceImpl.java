package com.gs.lshly.biz.support.user.service.merchadmin.h5.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.gs.lshly.biz.support.user.entity.User;
import com.gs.lshly.biz.support.user.entity.UserPrivateUser;
import com.gs.lshly.biz.support.user.enums.PrivateUserApplyStateEnum;
import com.gs.lshly.biz.support.user.enums.PrivateUserBindStateEnum;
import com.gs.lshly.biz.support.user.enums.UserQueryTypeEnum;
import com.gs.lshly.biz.support.user.mapper.UserPrivateUserMapper;
import com.gs.lshly.biz.support.user.mapper.view.UserPrivateUserView;
import com.gs.lshly.biz.support.user.repository.IUserPrivateUserRepository;
import com.gs.lshly.biz.support.user.repository.IUserRepository;
import com.gs.lshly.biz.support.user.service.merchadmin.h5.IH5MerchUserService;
import com.gs.lshly.biz.support.user.service.merchadmin.pc.IPCMerchUserService;
import com.gs.lshly.common.enums.ApplyStateEnum;
import com.gs.lshly.common.exception.BusinessException;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.common.struct.common.CommonShopVO;
import com.gs.lshly.common.struct.common.LegalDictDTO;
import com.gs.lshly.common.struct.common.LegalDictVO;
import com.gs.lshly.common.struct.merchadmin.h5.trade.vo.H5MerchUserVO;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.dto.PCMerchMerchantUserTypeDTO;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.vo.PCMerchMerchantUserTypeVO;
import com.gs.lshly.common.struct.merchadmin.pc.user.dto.PCMerchUserDTO;
import com.gs.lshly.common.struct.merchadmin.pc.user.qto.PCMerchUserQTO;
import com.gs.lshly.common.struct.merchadmin.pc.user.vo.PCMerchUserVO;
import com.gs.lshly.common.utils.BeanCopyUtils;
import com.gs.lshly.common.utils.EnumUtil;
import com.gs.lshly.middleware.mybatisplus.MybatisPlusUtil;
import com.gs.lshly.rpc.api.common.ICommonShopRpc;
import com.gs.lshly.rpc.api.common.ILegalDictRpc;
import com.gs.lshly.rpc.api.merchadmin.pc.merchant.IPCMerchMerchantUserTypeRpc;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
* <p>
*  服务实现类
* </p>
* @author xxfc
* @since 2020-10-20
*/
@Component
public class H5MerchUserServiceImpl implements IH5MerchUserService {

    @Autowired
    private IUserPrivateUserRepository userPrivateUserRepository;

    @Autowired
    private UserPrivateUserMapper userPrivateUserMapper;

    @Autowired
    private IUserRepository userRepository;

    @DubboReference
    private ILegalDictRpc legalDictRpc;
    @DubboReference
    private IPCMerchMerchantUserTypeRpc merchMerchantUserTypeRpc;
    @DubboReference
    private ICommonShopRpc commonShopRpc;



    @Override
    public H5MerchUserVO.UserSimpleVO innerUserSimple(String userId) {
        if(StringUtils.isBlank(userId)){
            return null;
        }
        User user =  userRepository.getById(userId);
        if(null == user){
            return null;
        }
        H5MerchUserVO.UserSimpleVO userSimpleVO = new H5MerchUserVO.UserSimpleVO();
        userSimpleVO.setUserId(user.getId());
        userSimpleVO.setUserName(user.getUserName());
        return userSimpleVO;
    }

}
