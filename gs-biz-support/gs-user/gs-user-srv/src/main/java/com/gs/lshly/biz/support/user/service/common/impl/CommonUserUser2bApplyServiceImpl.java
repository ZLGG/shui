package com.gs.lshly.biz.support.user.service.common.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.gs.lshly.biz.support.user.entity.*;
import com.gs.lshly.biz.support.user.enums.PrivateUserApplyStateEnum;
import com.gs.lshly.biz.support.user.enums.PrivateUserBindStateEnum;
import com.gs.lshly.biz.support.user.enums.UserApplyStateEnum;
import com.gs.lshly.biz.support.user.repository.*;
import com.gs.lshly.biz.support.user.service.bbb.pc.IPCBbbUserPrivateUserService;
import com.gs.lshly.biz.support.user.service.common.ICommonUserUser2bApplyService;
import com.gs.lshly.common.enums.BusinessTypeEnum;
import com.gs.lshly.common.exception.BusinessException;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.common.struct.bbb.pc.user.dto.PCBbbUserPrivateUserLogDTO;
import com.gs.lshly.common.struct.bbb.pc.user.qto.PCBbbUserUser2bApplyLogQTO;
import com.gs.lshly.common.struct.bbb.pc.user.vo.BbbUserVO;
import com.gs.lshly.common.struct.bbb.pc.user.vo.PCBbbUserUser2bApplyLogVO;
import com.gs.lshly.common.struct.common.LegalDictDTO;
import com.gs.lshly.common.struct.common.LegalDictVO;
import com.gs.lshly.common.struct.common.dto.CommonUserUser2bApplyDTO;
import com.gs.lshly.common.struct.common.vo.CommonUserUser2bApplyVO;
import com.gs.lshly.common.struct.common.CommonShopVO;
import com.gs.lshly.middleware.mybatisplus.MybatisPlusUtil;
import com.gs.lshly.rpc.api.common.ICommonShopRpc;
import com.gs.lshly.rpc.api.common.ILegalDictRpc;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;

import java.util.ArrayList;
import java.util.List;

/**
* <p>
*  服务实现类
* </p>
* @author xxfc
* @since 2020-10-06
*/
@Component
public class CommonUserUser2bApplyServiceImpl implements ICommonUserUser2bApplyService {

    @Autowired
    private IUserUser2bApplyRepository userUser2bApplyRepository;

    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private IUserPrivateUserRepository userPrivateUserRepository;

    @Autowired
    private IPCBbbUserPrivateUserService userPrivateUserService;

    @Autowired
    private IUserUser2bApplyCertRepository userUser2bApplyCertRepository;

    @Autowired
    private IUserUser2bApplyLogRepository userUser2bApplyLogRepository;

    @DubboReference
    private ICommonShopRpc commonShopRpc;

    @DubboReference
    private ILegalDictRpc legalDictRpc;

    @Override
    public void editUserUser2bApply(CommonUserUser2bApplyDTO.ETO dto) {
        if(StringUtils.isBlank(dto.getJwtUserId())){
            throw new BusinessException("没有登录");
        }
        String fromShopId = null;
        if (StringUtils.isNotBlank(dto.getCode())){
            CommonShopVO.SimpleVO simpleVO = commonShopRpc.innerServiceByShareCode(dto.getCode());
            if (null == simpleVO){
                throw new BusinessException("邀请码填写错误！");
            }
            fromShopId = simpleVO.getId();
        }
        CommonShopVO.MerchantVO merchantVO = null;
        if(StringUtils.isNotBlank(fromShopId)){
            merchantVO =  commonShopRpc.merchantByShopId(fromShopId);
            if(null == merchantVO){
                throw new BusinessException("店铺不存在");
            }
            User user  = new User();
            user.setId(dto.getJwtUserId());
            user.setFromShopId(fromShopId);
            userRepository.updateById(user);
        }
        QueryWrapper<UserUser2bApply> userUser2bApplyQueryWrapper = MybatisPlusUtil.query();
        userUser2bApplyQueryWrapper.eq("user_id",dto.getJwtUserId());
        UserUser2bApply userUser2bApply = userUser2bApplyRepository.getOne(userUser2bApplyQueryWrapper);
        if(null == userUser2bApply){
            userUser2bApply = new UserUser2bApply();
            BeanUtils.copyProperties(dto,userUser2bApply);
            this.utilConfigUserApply(userUser2bApply);
            if(null != merchantVO){
                userUser2bApply.setFromMerchantId(merchantVO.getMerchantId());
            }
            if (null != fromShopId){
                userUser2bApply.setFromShopId(fromShopId);
            }
            userUser2bApply.setUserId(dto.getJwtUserId());
            userUser2bApplyRepository.save(userUser2bApply);

            addApplyLog(userUser2bApply,userUser2bApply.getId());
        }else{
            BeanUtils.copyProperties(dto,userUser2bApply);
            this.utilConfigUserApply(userUser2bApply);
            if(null != merchantVO){
                userUser2bApply.setFromMerchantId(merchantVO.getMerchantId());
            }
            if (null != fromShopId){
                userUser2bApply.setFromShopId(fromShopId);
            }
            userUser2bApplyRepository.updateById(userUser2bApply);
            //是编辑，先删老的证照
            QueryWrapper<UserUser2bApplyCert> removeQueryWrapper = MybatisPlusUtil.query();
            removeQueryWrapper.eq("apply_id",userUser2bApply.getId());
            userUser2bApplyCertRepository.remove(removeQueryWrapper);

            addApplyLog(userUser2bApply,userUser2bApply.getId());
        }
        if(ObjectUtils.isNotEmpty(dto.getCertList())){
            List<UserUser2bApplyCert> applyCertList = new ArrayList<>();
            for(CommonUserUser2bApplyDTO.CertDTO certDto:dto.getCertList()){
                UserUser2bApplyCert userUser2bApplyCert = new UserUser2bApplyCert();
                BeanUtils.copyProperties(certDto,userUser2bApplyCert);
                userUser2bApplyCert.setApplyId(userUser2bApply.getId());
                userUser2bApplyCert.setCertName(certDto.getCertName());
                userUser2bApplyCert.setCertFileUrl(StringUtils.isBlank(certDto.getCertUrl())?"":certDto.getCertUrl());
                applyCertList.add(userUser2bApplyCert);
            }
            userUser2bApplyCertRepository.saveBatch(applyCertList);
        }
    }

    @Override
    public void joinPrivateShop(CommonUserUser2bApplyDTO.ShareCodeDTO dto) {
        if (null == dto || StringUtils.isBlank(dto.getShareCode())){
            throw new BusinessException("请填写邀请码！");
        }
        //通过邀请码查询店铺信息
        CommonShopVO.SimpleVO simpleVO = commonShopRpc.innerServiceByShareCode(dto.getShareCode());
        if (null == simpleVO){
            throw new BusinessException("该邀请码填写错误！");
        }
        UserPrivateUser userPrivateUser = new UserPrivateUser();

        //判断该用户是否以前加入过私域店铺
        QueryWrapper<UserPrivateUser> wrapper = MybatisPlusUtil.query();
        wrapper.eq("user_id",dto.getJwtUserId());
        wrapper.eq("shop_id",simpleVO.getId());
        UserPrivateUser privateUser = userPrivateUserRepository.getOne(wrapper);
        if (null != privateUser){
            userPrivateUser.setId(privateUser.getId());
        }
        userPrivateUser.setUserId(dto.getJwtUserId());
        userPrivateUser.setShopId(simpleVO.getId());
        userPrivateUser.setMerchantId(simpleVO.getMerchantId());
        userPrivateUser.setState(PrivateUserApplyStateEnum.待审.getCode());
        userPrivateUser.setBindState(PrivateUserBindStateEnum.解除.getCode());
        userPrivateUserRepository.saveOrUpdate(userPrivateUser);

        //添加加入私域店铺记录
        PCBbbUserPrivateUserLogDTO.ETO eto = new PCBbbUserPrivateUserLogDTO.ETO();
        eto.setPrivateUserId(userPrivateUser.getId());
        eto.setShopId(simpleVO.getId());
        eto.setShopName(StringUtils.isBlank(simpleVO.getShopName())?"":simpleVO.getShopName());
        eto.setUserId(dto.getJwtUserId());
        eto.setUserName(dto.getJwtUserName());
        eto.setState(PrivateUserApplyStateEnum.待审.getCode());
        userPrivateUserService.addPrivateUserLog(eto);


    }

    @Override
    public BbbUserVO.UserTypeVO getUserType(BaseDTO dto) {
        QueryWrapper<User> wrapper = MybatisPlusUtil.query();
        wrapper.eq("id",dto.getJwtUserId());
        User user = userRepository.getOne(wrapper);
        if (null != user){
            BbbUserVO.UserTypeVO userTypeVO = new BbbUserVO.UserTypeVO();
            userTypeVO.setId(user.getId());
            userTypeVO.setUserType(user.getType());
            return userTypeVO;
        }
        return null;
    }

    @Override
    public CommonUserUser2bApplyVO.DetailVO getAppleInfoVOBy(CommonUserUser2bApplyDTO.CreditCodeDTO dto) {
        if(StringUtils.isBlank(dto.getJwtUserId())){
            throw new BusinessException("没有登录");
        }
        QueryWrapper<UserUser2bApply> userUser2bApplyQueryWrapper = MybatisPlusUtil.query();
        userUser2bApplyQueryWrapper.eq("user_id",dto.getJwtUserId());
        userUser2bApplyQueryWrapper.eq("unified_social_credit_code",dto.getUnifiedSocialCreditCode());
        UserUser2bApply userUser2bApply = userUser2bApplyRepository.getOne(userUser2bApplyQueryWrapper);
        if(null == userUser2bApply){
            return null;
        }
        CommonUserUser2bApplyVO.DetailVO detailVO = new CommonUserUser2bApplyVO.DetailVO();
        BeanUtils.copyProperties(userUser2bApply,detailVO);
        QueryWrapper<UserUser2bApplyCert> certQueryWrapper = MybatisPlusUtil.query();
        certQueryWrapper.eq("apply_id",userUser2bApply.getId());
        List<UserUser2bApplyCert> applyCertList =  userUser2bApplyCertRepository.list(certQueryWrapper);
        if(ObjectUtils.isNotEmpty(applyCertList)){
            for(UserUser2bApplyCert applyCert:applyCertList){
                CommonUserUser2bApplyVO.CertVO certVO = new CommonUserUser2bApplyVO.CertVO();
                BeanUtils.copyProperties(applyCert,certVO);
                certVO.setCertUrl(applyCert.getCertFileUrl());
                detailVO.getCertList().add(certVO);
            }
        }
        return detailVO;
    }

    private void utilConfigUserApply(UserUser2bApply userUser2bApply){
        userUser2bApply.setBusinessType(BusinessTypeEnum.买家.getCode());
        userUser2bApply.setState(UserApplyStateEnum.待审.getCode());
    }

    @Override
    public CommonUserUser2bApplyVO.DetailVO detailUserUser2bApply(BaseDTO dto) {
        if(StringUtils.isBlank(dto.getJwtUserId())){
            throw new BusinessException("没有登录");
        }
        QueryWrapper<UserUser2bApply> userUser2bApplyQueryWrapper = MybatisPlusUtil.query();
        userUser2bApplyQueryWrapper.eq("user_id",dto.getJwtUserId());
        UserUser2bApply userUser2bApply = userUser2bApplyRepository.getOne(userUser2bApplyQueryWrapper);
        if(null == userUser2bApply){
            return null;
        }
        CommonUserUser2bApplyVO.DetailVO detailVO = new CommonUserUser2bApplyVO.DetailVO();
        BeanUtils.copyProperties(userUser2bApply,detailVO);
        QueryWrapper<UserUser2bApplyCert> certQueryWrapper = MybatisPlusUtil.query();
        certQueryWrapper.eq("apply_id",userUser2bApply.getId());
        List<UserUser2bApplyCert> applyCertList =  userUser2bApplyCertRepository.list(certQueryWrapper);
        if(ObjectUtils.isNotEmpty(applyCertList)){
            for(UserUser2bApplyCert applyCert:applyCertList){
                CommonUserUser2bApplyVO.CertVO certVO = new CommonUserUser2bApplyVO.CertVO();
                BeanUtils.copyProperties(applyCert,certVO);
                certVO.setCertUrl(applyCert.getCertFileUrl());
                detailVO.getCertList().add(certVO);
            }
        }
        return detailVO;
    }

    @Override
    public CommonUserUser2bApplyVO.ApplyRecordVO applyRecord(BaseDTO dto) {
        QueryWrapper<UserUser2bApply> userUser2bApplyQueryWrapper = MybatisPlusUtil.query();
        userUser2bApplyQueryWrapper.eq("user_id",dto.getJwtUserId());
        UserUser2bApply userUser2bApply = userUser2bApplyRepository.getOne(userUser2bApplyQueryWrapper);
        if(null == userUser2bApply){
            return null;
        }
        CommonUserUser2bApplyVO.ApplyRecordVO applyRecordVO = new CommonUserUser2bApplyVO.ApplyRecordVO();
        BeanUtils.copyProperties(userUser2bApply,applyRecordVO);
        return applyRecordVO;
    }

    @Override
    public LegalDictVO.DetailVO corpInfo(BaseDTO dto) {
        User user =   userRepository.getById(dto.getJwtUserId());
        if(null == user){
            return  null;
        }
        if(com.baomidou.mybatisplus.core.toolkit.StringUtils.isBlank(user.getLegalId())){
            return null;
        }
        return legalDictRpc.detailLegalDict(new LegalDictDTO.IdDTO(user.getLegalId()));
    }

    @Override
    public PageData<PCBbbUserUser2bApplyLogVO.ListVO> pageApplyLogData(PCBbbUserUser2bApplyLogQTO.QTO qto) {
        if (ObjectUtils.isEmpty(qto.getJwtUserId())){
            throw new BusinessException("未登录！！");
        }
        QueryWrapper<UserUser2bApplyLog> wrapper = MybatisPlusUtil.query();
        wrapper.eq("user_id",qto.getJwtUserId());
        IPage<UserUser2bApplyLog> page = MybatisPlusUtil.pager(qto);
        IPage<UserUser2bApplyLog> user2bApplyLogIPage = userUser2bApplyLogRepository.page(page,wrapper);
        return MybatisPlusUtil.toPageData(qto,PCBbbUserUser2bApplyLogVO.ListVO.class,user2bApplyLogIPage);
    }

    private void addApplyLog(UserUser2bApply userUser2bApply,String applyId){
        UserUser2bApplyLog user2bApplyLog = new UserUser2bApplyLog();
        BeanUtils.copyProperties(userUser2bApply,user2bApplyLog);
        User user = userRepository.getById(userUser2bApply.getUserId());
        if (ObjectUtils.isNotEmpty(user)){
            user2bApplyLog.setUserName(StringUtils.isBlank(user.getUserName())?"":user.getUserName());
        }
        user2bApplyLog.setId("");
        user2bApplyLog.setUser2bApplyId(applyId);
        userUser2bApplyLogRepository.save(user2bApplyLog);
    }

}
