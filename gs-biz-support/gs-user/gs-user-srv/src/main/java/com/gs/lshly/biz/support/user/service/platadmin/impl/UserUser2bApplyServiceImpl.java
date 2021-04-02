package com.gs.lshly.biz.support.user.service.platadmin.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.gs.lshly.biz.support.user.entity.*;
import com.gs.lshly.biz.support.user.enums.ApplyQueryTypeEnum;
import com.gs.lshly.biz.support.user.enums.PrivateUserApplyStateEnum;
import com.gs.lshly.biz.support.user.enums.PrivateUserBindStateEnum;
import com.gs.lshly.biz.support.user.repository.*;
import com.gs.lshly.biz.support.user.service.platadmin.IUserUser2bApplyService;
import com.gs.lshly.common.enums.ApplyStateEnum;
import com.gs.lshly.common.enums.UserTypeEnum;
import com.gs.lshly.common.exception.BusinessException;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.common.CommonShopVO;
import com.gs.lshly.common.struct.common.LegalDictDTO;
import com.gs.lshly.common.struct.platadmin.user.dto.UserUser2bApplyDTO;
import com.gs.lshly.common.struct.platadmin.user.qto.UserUser2bApplyQTO;
import com.gs.lshly.common.struct.platadmin.user.vo.UserUser2bApplyVO;
import com.gs.lshly.common.utils.BeanCopyUtils;
import com.gs.lshly.common.utils.EnumUtil;
import com.gs.lshly.middleware.mybatisplus.MybatisPlusUtil;
import com.gs.lshly.rpc.api.common.ICommonShopRpc;
import com.gs.lshly.rpc.api.common.ILegalDictRpc;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
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
public class UserUser2bApplyServiceImpl implements IUserUser2bApplyService {

    @Autowired
    private IUserUser2bApplyRepository repository;
    @Autowired
    private IUserRepository userRepository;
    @Autowired
    private IUserPrivateUserRepository userPrivateUserRepository;
    @Autowired
    private IUserPrivateUserLogRepository userPrivateUserLogRepository;
    @Autowired
    private IUserUser2bApplyCertRepository userUser2bApplyCertRepository;
    @Autowired
    private IUserUser2bApplyLogRepository userUser2bApplyLogRepository;
    @DubboReference
    private ILegalDictRpc legalDictRpc;
    @DubboReference
    private ICommonShopRpc commonShopRpc;

    @Override
    public PageData<UserUser2bApplyVO.ListVO> pageData(UserUser2bApplyQTO.QTO qto) {
        QueryWrapper<UserUser2bApply> wrapper = MybatisPlusUtil.query();
        if(StringUtils.isNotBlank(qto.getQueryValue())){
            if(ApplyQueryTypeEnum.公司名称.getCode().equals(qto.getQueryType())){
                wrapper.like("corp_name",qto.getQueryValue());
            }
            else if(ApplyQueryTypeEnum.法人代表.getCode().equals(qto.getQueryType())){
                wrapper.like("person_name",qto.getQueryValue());
            }
            else if(ApplyQueryTypeEnum.联系人手机号.getCode().equals(qto.getQueryType())){
                wrapper.like("corp_phone",qto.getQueryValue());
            }
        }
        wrapper.orderByDesc("cdate");
        IPage<UserUser2bApply> page = MybatisPlusUtil.pager(qto);
        IPage<UserUser2bApply> user2bApplyIPage = repository.page(page, wrapper);
        List<UserUser2bApplyVO.ListVO> voList = new ArrayList<>();
        if(ObjectUtils.isEmpty(user2bApplyIPage) || ObjectUtils.isEmpty(user2bApplyIPage.getRecords())){
            return new PageData<>();
        }
        List<String> applyIdList = new ArrayList<>();
        for(UserUser2bApply userUser2bApply:user2bApplyIPage.getRecords()){
            UserUser2bApplyVO.ListVO listVO = new UserUser2bApplyVO.ListVO();
            BeanUtils.copyProperties(userUser2bApply,listVO);
            voList.add(listVO);
            applyIdList.add(userUser2bApply.getId());
        }
        QueryWrapper<UserUser2bApplyCert> applyCertQueryWrapper = MybatisPlusUtil.query();
        applyCertQueryWrapper.in("apply_id",applyIdList);
        List<UserUser2bApplyCert> applyCertList = userUser2bApplyCertRepository.list(applyCertQueryWrapper);
        if(ObjectUtils.isNotEmpty(applyCertList)){
            for(UserUser2bApplyCert applyCert:applyCertList){
                for(UserUser2bApplyVO.ListVO listVO:voList){
                    if(listVO.getId().equals(applyCert.getApplyId())){
                        UserUser2bApplyVO.CertVO certVO = new UserUser2bApplyVO.CertVO();
                        BeanUtils.copyProperties(applyCert,certVO);
                        certVO.setCertUrl(StringUtils.isBlank(applyCert.getCertFileUrl())?"":applyCert.getCertFileUrl());
                        listVO.getCertList().add(certVO);
                        break;
                    }
                }
            }
        }
        return MybatisPlusUtil.toPageData(voList,qto.getPageNum(),qto.getPageSize(),page.getTotal());
    }

    @Override
    public void addUserUser2bApply(UserUser2bApplyDTO.ETO eto) {
        UserUser2bApply userUser2bApply = new UserUser2bApply();
        BeanCopyUtils.copyProperties(eto, userUser2bApply);
        repository.save(userUser2bApply);
    }


    @Override
    public void deleteUserUser2bApply(UserUser2bApplyDTO.IdDTO dto) {
        repository.removeById(dto.getId());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteBatchUserUser2bApply(UserUser2bApplyDTO.IdListDTO dto) {
        if(ObjectUtils.isEmpty(dto) || ObjectUtils.isEmpty(dto.getIdList())){
            throw new BusinessException("删除的数据不能为空");
        }
        //删除申请
        repository.removeByIds(dto.getIdList());
        //删除申请下面的证照数据
        QueryWrapper<UserUser2bApplyCert> queryWrapper = MybatisPlusUtil.query();
        queryWrapper.in("apply_id",dto.getIdList());
        userUser2bApplyCertRepository.remove(queryWrapper);
    }

    @Override
    public void editUserUser2bApply(UserUser2bApplyDTO.ETO eto) {
        UserUser2bApply userUser2bApply = new UserUser2bApply();
        BeanCopyUtils.copyProperties(eto, userUser2bApply);
        repository.updateById(userUser2bApply);
    }

    @Override
    public UserUser2bApplyVO.DetailVO detailUserUser2bApply(UserUser2bApplyDTO.IdDTO dto) {
        UserUser2bApply userUser2bApply = repository.getById(dto.getId());
        UserUser2bApplyVO.DetailVO detailVo = new UserUser2bApplyVO.DetailVO();
        if(ObjectUtils.isEmpty(userUser2bApply)){
            throw new BusinessException("无效的ID数据");
        }
        BeanCopyUtils.copyProperties(userUser2bApply, detailVo);
        return detailVo;
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public void applyUserUser2bApply(UserUser2bApplyDTO.ApplyDTO dto) {
        if(StringUtils.isBlank(dto.getId())){
            throw new BusinessException("审核的申请ID不能为空");
        }
        if(!EnumUtil.checkEnumCodeWithExcludes(dto.getState(),ApplyStateEnum.class,ApplyStateEnum.待审.getCode())){
            throw new BusinessException("审核结果类型错误");
        }
        if (dto.getState().equals(ApplyStateEnum.拒审.getCode()) && StringUtils.isBlank(dto.getRevokeWhy())){
            throw new BusinessException("请填写拒绝的原因！！");
        }
        UserUser2bApply userUser2bApply = repository.getById(dto.getId());
        if(null == userUser2bApply){
            throw new BusinessException("会员入驻申请不存在");
        }
        if(ApplyStateEnum.拒审.getCode().equals(dto.getState())){
            userUser2bApply.setState(dto.getState());
            userUser2bApply.setRevokeWhy(dto.getRevokeWhy());
            userUser2bApply.setReovkeTime(LocalDateTime.now());
            repository.updateById(userUser2bApply);

            //记录
            addApplyLog(userUser2bApply);
            return;
        }
        if(dto.getState().equals(ApplyStateEnum.通过.getCode())){
            //保存法人单位
            LegalDictDTO.InnerETO innerETO = new LegalDictDTO.InnerETO();
            BeanUtils.copyProperties(userUser2bApply,innerETO);
            ///保存法人单位 -> 提取上传的证照数据
            QueryWrapper<UserUser2bApplyCert> applyCertQueryWrapper = MybatisPlusUtil.query();
            applyCertQueryWrapper.eq("apply_id",dto.getId());
            List<UserUser2bApplyCert> applyCertList =  userUser2bApplyCertRepository.list(applyCertQueryWrapper);
            if(ObjectUtils.isNotEmpty(applyCertList)){
                for(UserUser2bApplyCert applyCert:applyCertList){
                    LegalDictDTO.CertDTO certDTO   = new LegalDictDTO.CertDTO();
                    certDTO.setId(applyCert.getCertId());
                    certDTO.setCertName(applyCert.getCertName());
                    certDTO.setCertUrl(StringUtils.isBlank(applyCert.getCertFileUrl())?"":applyCert.getCertFileUrl());
                    innerETO.getCertList().add(certDTO);
                }
            }
            String legalId = legalDictRpc.innerQueryAddLegalDict(innerETO);
            if(StringUtils.isBlank(legalId)){
                throw new BusinessException("创建企业字典失败");
            }
            //设置用户和企业字典编号关联
            User user  = new User();
            user.setId(userUser2bApply.getUserId());
            user.setType(UserTypeEnum._2B用户.getCode());
            user.setLegalId(legalId);
            userRepository.updateById(user);
            //更新入驻申请状态和信息
            userUser2bApply.setState(dto.getState());
            userUser2bApply.setOkpassTime(LocalDateTime.now());
            repository.updateById(userUser2bApply);

            //添加记录
            addApplyLog(userUser2bApply);

            //生成私域会员审核,如果有店铺ID生成私域会员审核
            if(StringUtils.isNotBlank(userUser2bApply.getFromShopId())){
                UserPrivateUser userPrivateUser = new UserPrivateUser();

                //判断该用户是否以前加入过私域店铺
                QueryWrapper<UserPrivateUser> wrapper = MybatisPlusUtil.query();
                wrapper.eq("user_id",userUser2bApply.getUserId());
                wrapper.eq("shop_id",userUser2bApply.getFromShopId());
                UserPrivateUser privateUser = userPrivateUserRepository.getOne(wrapper);
                if (null != privateUser){
                    userPrivateUser.setId(privateUser.getId());
                }
                userPrivateUser.setUserId(userUser2bApply.getUserId());
                userPrivateUser.setShopId(userUser2bApply.getFromShopId());
                userPrivateUser.setMerchantId(userUser2bApply.getFromMerchantId());
                userPrivateUser.setState(PrivateUserApplyStateEnum.待审.getCode());
                userPrivateUser.setBindState(PrivateUserBindStateEnum.解除.getCode());
                userPrivateUserRepository.saveOrUpdate(userPrivateUser);


                CommonShopVO.SimpleVO simpleVO = commonShopRpc.shopDetails(userUser2bApply.getFromShopId());
                UserPrivateUserLog privateUserLog = new UserPrivateUserLog();
                privateUserLog.setPrivateUserId(userPrivateUser.getId());
                privateUserLog.setShopId(userUser2bApply.getFromShopId());
                privateUserLog.setUserId(userUser2bApply.getUserId());
                privateUserLog.setState(PrivateUserApplyStateEnum.待审.getCode());
                if (ObjectUtils.isNotEmpty(simpleVO)){
                    privateUserLog.setShopName(StringUtils.isBlank(simpleVO.getShopName())?"":simpleVO.getShopName());
                }
                User user1 = userRepository.getById(userUser2bApply.getUserId());
                if (ObjectUtils.isNotEmpty(user1)){
                    privateUserLog.setUserName(StringUtils.isBlank(user1.getUserName())?"":user1.getUserName());
                }
                userPrivateUserLogRepository.save(privateUserLog);
            }
        }
    }

    private void addApplyLog(UserUser2bApply userUser2bApply){
        UserUser2bApplyLog user2bApplyLog = new UserUser2bApplyLog();
        BeanUtils.copyProperties(userUser2bApply,user2bApplyLog);
        User user = userRepository.getById(userUser2bApply.getUserId());
        if (ObjectUtils.isNotEmpty(user)){
            user2bApplyLog.setUserName(org.apache.commons.lang3.StringUtils.isBlank(user.getUserName())?"":user.getUserName());
        }
        user2bApplyLog.setId("");
        user2bApplyLog.setUser2bApplyId(userUser2bApply.getId());
        userUser2bApplyLogRepository.save(user2bApplyLog);
    }

}
