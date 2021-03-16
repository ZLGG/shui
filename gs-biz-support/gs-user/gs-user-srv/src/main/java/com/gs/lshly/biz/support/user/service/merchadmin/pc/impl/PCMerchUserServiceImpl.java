package com.gs.lshly.biz.support.user.service.merchadmin.pc.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.gs.lshly.biz.support.user.entity.User;
import com.gs.lshly.biz.support.user.entity.UserPrivateUser;
import com.gs.lshly.biz.support.user.entity.UserPrivateUserLog;
import com.gs.lshly.biz.support.user.enums.PrivateUserApplyStateEnum;
import com.gs.lshly.biz.support.user.enums.PrivateUserBindStateEnum;
import com.gs.lshly.biz.support.user.enums.UserQueryTypeEnum;
import com.gs.lshly.biz.support.user.mapper.UserPrivateUserMapper;
import com.gs.lshly.biz.support.user.mapper.view.UserPrivateUserView;
import com.gs.lshly.biz.support.user.repository.IUserPrivateUserLogRepository;
import com.gs.lshly.biz.support.user.repository.IUserPrivateUserRepository;
import com.gs.lshly.biz.support.user.repository.IUserRepository;
import com.gs.lshly.biz.support.user.service.merchadmin.pc.IPCMerchUserService;
import com.gs.lshly.common.enums.ApplyStateEnum;
import com.gs.lshly.common.exception.BusinessException;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.common.struct.common.CommonShopVO;
import com.gs.lshly.common.struct.common.LegalDictDTO;
import com.gs.lshly.common.struct.common.LegalDictVO;
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
public class PCMerchUserServiceImpl implements IPCMerchUserService {

    @Autowired
    private IUserPrivateUserRepository userPrivateUserRepository;

    @Autowired
    private IUserPrivateUserLogRepository userPrivateUserLogRepository;

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
    public PageData<PCMerchUserVO.ListVO> pageData(PCMerchUserQTO.QTO qto) {
        QueryWrapper<UserPrivateUserView> queryWrapper = MybatisPlusUtil.query();
        queryWrapper.eq("pu.state",PrivateUserApplyStateEnum.通过.getCode());
        queryWrapper.eq("pu.shop_id",qto.getJwtShopId());
        if(StringUtils.isNotBlank(qto.getConditionValue())){
            if(UserQueryTypeEnum.手机号.getCode().equals(qto.getConditionType())){
                queryWrapper.like("u.phone",qto.getConditionValue());
            }
            else if(UserQueryTypeEnum.用户名.getCode().equals(qto.getConditionType())){
                queryWrapper.like("u.user_name",qto.getConditionValue());
            }
            else if(UserQueryTypeEnum.真实姓名.getCode().equals(qto.getConditionType())){
                queryWrapper.like("u.real_name",qto.getConditionValue());
            }
            else if(UserQueryTypeEnum.邮箱.getCode().equals(qto.getConditionType())){
                queryWrapper.like("u.to",qto.getConditionValue());
            }
        }
        IPage<UserPrivateUserView> page = MybatisPlusUtil.pager(qto);
        IPage<UserPrivateUserView> userViewIPage = userPrivateUserMapper.selectUserPrivateUser(page,queryWrapper);
        List<String> legalIdist = new ArrayList<>();
        List<PCMerchUserVO.ListVO> voList = new ArrayList<>();
        if(ObjectUtils.isEmpty(userViewIPage) || ObjectUtils.isEmpty(userViewIPage.getRecords())){
            return new PageData<>();
        }
        List<String> userTypeIdList = new ArrayList<>();
        for(UserPrivateUserView userPrivateUserView:userViewIPage.getRecords()){
            PCMerchUserVO.ListVO listVO = new PCMerchUserVO.ListVO();
            BeanUtils.copyProperties(userPrivateUserView,listVO);
            CommonShopVO.SimpleVO simpleVO = commonShopRpc.shopDetails(qto.getJwtShopId());
            if (ObjectUtils.isNotEmpty(simpleVO)){
                listVO.setShopName(StringUtils.isBlank(simpleVO.getShopName())?"":simpleVO.getShopName());
            }
            voList.add(listVO);
            if(StringUtils.isNotBlank(userPrivateUserView.getUserTypeId())){
                userTypeIdList.add(userPrivateUserView.getUserTypeId());
            }
            if(StringUtils.isNotBlank(userPrivateUserView.getLegalId())){
                legalIdist.add(userPrivateUserView.getLegalId());
            }
        }
        if(ObjectUtils.isNotEmpty(legalIdist)){
            List<LegalDictVO.DetailVO> legalVoList = legalDictRpc.innerListLegalDict(legalIdist);
            if(ObjectUtils.isNotEmpty(legalIdist)){
                for(PCMerchUserVO.ListVO listVO:voList){
                    for(LegalDictVO.DetailVO detailVO:legalVoList){
                        if(listVO.getLegalId().equals(detailVO.getId())){
                            listVO.setCompanyVO(detailVO.getCompanyVO());
                            break;
                        }
                    }
                }
            }
        }
        if(ObjectUtils.isNotEmpty(userTypeIdList)){
            List<PCMerchMerchantUserTypeVO.ListVO> userTypeList =   merchMerchantUserTypeRpc.list(new BaseDTO().setJwtShopId(qto.getJwtShopId()));
            if(ObjectUtils.isNotEmpty(userTypeList)){
                for(PCMerchUserVO.ListVO userVO:voList){
                    for(PCMerchMerchantUserTypeVO.ListVO userTypeVo:userTypeList){
                        if(StringUtils.isNotBlank(userVO.getUserTypeId()) && StringUtils.isNotBlank(userTypeVo.getId())){
                            if(userVO.getUserTypeId().equals(userTypeVo.getId())){
                                userVO.setUserTypeName(userTypeVo.getUserTypeName());
                                break;
                            }
                        }
                    }
                }
            }
        }
        return MybatisPlusUtil.toPageData(voList,qto.getPageNum(),qto.getPageSize(), page.getTotal());
    }

    @Override
    public PCMerchUserVO.PrivateUserDetailVO detailUser(PCMerchUserDTO.IdDTO dto) {
        if(ObjectUtils.isEmpty(dto.getId())){
            throw new BusinessException("ID不能为空");
        }
        QueryWrapper<UserPrivateUserView> queryWrapper = MybatisPlusUtil.query();
        queryWrapper.eq("pu.user_id",dto.getId());
        queryWrapper.eq("pu.shop_id",dto.getJwtShopId());
        UserPrivateUserView userPrivateUserView =  userPrivateUserMapper.detailUserPrivateUser(queryWrapper);
        if(null == userPrivateUserView){
            return null;
        }
        PCMerchUserVO.PrivateUserDetailVO detailVo = new PCMerchUserVO.PrivateUserDetailVO();
        BeanCopyUtils.copyProperties(userPrivateUserView, detailVo);
        if(StringUtils.isNotBlank(userPrivateUserView.getLegalId())){
            LegalDictVO.DetailVO  legalDictVO = legalDictRpc.detailLegalDict(new LegalDictDTO.IdDTO(userPrivateUserView.getLegalId()));
            if(null !=legalDictVO){
                if (ObjectUtils.isNotEmpty(legalDictVO.getCompanyVO())){
                    detailVo.setCompanyVO(legalDictVO.getCompanyVO());
                }
                if (ObjectUtils.isNotEmpty(legalDictVO.getBankVO())){
                    detailVo.setBankVO(legalDictVO.getBankVO());
                }
                if (ObjectUtils.isNotEmpty(legalDictVO.getCertListVO())){
                    detailVo.setCertList(legalDictVO.getCertListVO());
                }
            }
        }
        if(StringUtils.isNotBlank(userPrivateUserView.getUserTypeId())){
            PCMerchMerchantUserTypeVO.DetailVO userTypeVo =   merchMerchantUserTypeRpc.detailMerchantUserType(new PCMerchMerchantUserTypeDTO.IdDTO(userPrivateUserView.getUserTypeId()));
            if(null != userTypeVo){
                detailVo.setUserTypeId(userTypeVo.getId());
                detailVo.setUserTypeName(userTypeVo.getUserTypeName());
                detailVo.setRatio(userTypeVo.getRatio());
            }
        }
        return detailVo;
    }

    @Override
    public void stopBatchUser(PCMerchUserDTO.IdListDTO dto) {
        if(ObjectUtils.isEmpty(dto) || ObjectUtils.isEmpty(dto.getIdList())){
            throw new BusinessException("请选择要取消关联的会员！！");
        }
        UpdateWrapper<UserPrivateUser> updateWrapper = MybatisPlusUtil.update();
        updateWrapper.set("bind_state", PrivateUserBindStateEnum.解除.getCode());
        updateWrapper.in("user_id",dto.getIdList());
        updateWrapper.eq("shop_id",dto.getJwtShopId());
        userPrivateUserRepository.update(updateWrapper);

    }

    @Override
    public void beginBatchUser(PCMerchUserDTO.IdListDTO dto) {
        if(ObjectUtils.isEmpty(dto) || ObjectUtils.isEmpty(dto.getIdList())){
            throw new BusinessException("请选择要关联的会员！！");
        }
        UpdateWrapper<UserPrivateUser> updateWrapper = MybatisPlusUtil.update();
        updateWrapper.set("bind_state", PrivateUserBindStateEnum.关联.getCode());
        updateWrapper.in("user_id",dto.getIdList());
        updateWrapper.eq("shop_id",dto.getJwtShopId());
        userPrivateUserRepository.update(updateWrapper);
    }

    @Override
    public void setUserType(PCMerchUserDTO.UserTypeDTO dto) {
        if(StringUtils.isBlank(dto.getId())){
            throw new BusinessException("商家会员关系ID不能为空");
        }
        if(StringUtils.isBlank(dto.getUserTypeId())){
            throw new BusinessException("会员类型ID不能为空");
        }
        QueryWrapper<UserPrivateUser> queryWrapper = MybatisPlusUtil.query();
        queryWrapper.eq("id",dto.getId());
        queryWrapper.eq("shop_id",dto.getJwtShopId());
        UserPrivateUser userPrivateUser = userPrivateUserRepository.getOne(queryWrapper);
        if(null == userPrivateUser){
            throw new BusinessException("商家会员关系不存在");
        }
        PCMerchMerchantUserTypeDTO.IdDTO idDTO = new PCMerchMerchantUserTypeDTO.IdDTO();
        idDTO.setId(dto.getUserTypeId());
        idDTO.setJwtShopId(dto.getJwtShopId());
        PCMerchMerchantUserTypeVO.DetailVO userTypeVo = merchMerchantUserTypeRpc.detailMerchantUserType(idDTO);
        if(null == userTypeVo){
            throw new BusinessException("会员类型不存在");
        }
        userPrivateUser.setUserTypeId(dto.getUserTypeId());
        userPrivateUserRepository.updateById(userPrivateUser);

    }


    @Override
    public List<PCMerchUserVO.ExportVO> exportUser(PCMerchUserDTO.IdListDTO dto) {
        if (null == dto || ObjectUtils.isEmpty(dto.getIdList())){
            throw new BusinessException("请选择要导出的会员信息！！");
        }
        QueryWrapper<UserPrivateUserView> queryWrapper = MybatisPlusUtil.query();
        queryWrapper.in("id",dto.getIdList());
        List<UserPrivateUserView> userViewList = userPrivateUserMapper.listUserPrivateUser(queryWrapper);
        if(ObjectUtils.isNotEmpty(userViewList)){
            List<PCMerchUserVO.ExportVO> voList = userViewList.parallelStream().map(e ->{
                PCMerchUserVO.ExportVO exportVO = new PCMerchUserVO.ExportVO();
                BeanCopyUtils.copyProperties(e,exportVO);
                //填充店铺名称
                CommonShopVO.SimpleVO commonShopVO = commonShopRpc.shopDetails(dto.getJwtShopId());
                if (null != commonShopVO){
                    exportVO.setShopName(StringUtils.isBlank(commonShopVO.getShopName())?"":commonShopVO.getShopName());
                }
                //填充企业信息
                if(StringUtils.isNotBlank(e.getLegalId())){
                    LegalDictVO.DetailVO  legalDictVO = legalDictRpc.detailLegalDict(new LegalDictDTO.IdDTO(e.getLegalId()));
                    if(null !=legalDictVO){
                        LegalDictVO.CompanyVO companyVO = legalDictVO.getCompanyVO();
                       if (null != commonShopVO){
                           exportVO.setCorpName(StringUtils.isBlank(companyVO.getCorpName())?"":companyVO.getCorpName());
                           exportVO.setCorpPersal(StringUtils.isBlank(companyVO.getCorpPersal())?"":companyVO.getCorpPersal());
                           exportVO.setCorpPhone(StringUtils.isBlank(companyVO.getCorpPhone())?"":companyVO.getCorpPhone());
                           exportVO.setPersonName(StringUtils.isBlank(companyVO.getPersonName())?"":companyVO.getPersonName());
                           exportVO.setCorpTypeName(StringUtils.isBlank(companyVO.getCorpTypeName())?"":companyVO.getCorpTypeName());
                       }
                    }
                }
                if(StringUtils.isNotBlank(e.getUserTypeId())){
                    PCMerchMerchantUserTypeVO.DetailVO userTypeVo =   merchMerchantUserTypeRpc.detailMerchantUserType(new PCMerchMerchantUserTypeDTO.IdDTO(e.getUserTypeId()));
                    if(null != userTypeVo){
                        exportVO.setUserTypeName(StringUtils.isBlank(userTypeVo.getUserTypeName())?"":userTypeVo.getUserTypeName());
                    }
                }
                return exportVO;
            }).collect(Collectors.toList());
            return voList;
        }
        return new ArrayList<>();
    }


    @Override
    public PageData<PCMerchUserVO.ApplyListVO> applyPageList(PCMerchUserQTO.ApplyListQTO qto) {
        QueryWrapper<UserPrivateUserView> queryWrapper = MybatisPlusUtil.query();
        queryWrapper.eq("pu.shop_id",qto.getJwtShopId());
        if(ObjectUtils.isNotNull(qto.getApplyState()) && !PrivateUserApplyStateEnum.全部.getCode().equals(qto.getApplyState())){
            queryWrapper.eq("pu.state",qto.getApplyState());
        }
        IPage<UserPrivateUserView> page = MybatisPlusUtil.pager(qto);
        IPage<UserPrivateUserView> userViewIPage = userPrivateUserMapper.selectUserPrivateUser(page,queryWrapper);
        List<String> legalIdist = new ArrayList<>();
        List<PCMerchUserVO.ApplyListVO> voList = new ArrayList<>();
        if(ObjectUtils.isEmpty(userViewIPage) || ObjectUtils.isNotEmpty(userViewIPage.getRecords())){
            for(UserPrivateUserView userPrivateUserView:userViewIPage.getRecords()){
                PCMerchUserVO.ApplyListVO applyListVO = new PCMerchUserVO.ApplyListVO();
                BeanUtils.copyProperties(userPrivateUserView,applyListVO);
                voList.add(applyListVO);
                if (ObjectUtils.isNotEmpty(userPrivateUserView.getLegalId())){
                    legalIdist.add(userPrivateUserView.getLegalId());
                }
            }
        }
        if(ObjectUtils.isNotEmpty(legalIdist)){
            List<LegalDictVO.DetailVO> legalVoList = legalDictRpc.innerListLegalDict(legalIdist);
            if(ObjectUtils.isNotEmpty(legalVoList)){
                for(PCMerchUserVO.ApplyListVO applyListVO:voList){
                    for(LegalDictVO.DetailVO detailVO:legalVoList){
                        if(applyListVO.getLegalId().equals(detailVO.getId())){
                            applyListVO.setCompanyVO(detailVO.getCompanyVO());
                            break;
                        }
                    }
                }
            }
        }
        return MybatisPlusUtil.toPageData(voList,qto.getPageNum(),qto.getPageSize(), page.getTotal());
    }

    @Override
    public PCMerchUserVO.ApplyPrivateUserDetailVO applyDetailUser(PCMerchUserDTO.IdDTO dto) {
        if(ObjectUtils.isEmpty(dto.getId())){
            throw new BusinessException("ID不能为空");
        }
        QueryWrapper<UserPrivateUserView> queryWrapper = MybatisPlusUtil.query();
        queryWrapper.eq("pu.id",dto.getId());
        queryWrapper.eq("pu.shop_id",dto.getJwtShopId());
        UserPrivateUserView userPrivateUserView = userPrivateUserMapper.detailUserPrivateUser(queryWrapper);
        if(null == userPrivateUserView){
            return null;
        }
        PCMerchUserVO.ApplyPrivateUserDetailVO applyPrivateUserDetailVO = new PCMerchUserVO.ApplyPrivateUserDetailVO();
        BeanUtils.copyProperties(userPrivateUserView,applyPrivateUserDetailVO);
        if(StringUtils.isNotBlank(userPrivateUserView.getLegalId())){
            LegalDictVO.InnerDetailVO  legalDictVO = legalDictRpc.innerdetailLegalDict(userPrivateUserView.getLegalId());
            if(null != legalDictVO){
                if (ObjectUtils.isNotEmpty(legalDictVO.getCompanyVO())){
                    applyPrivateUserDetailVO.setCompanyVO(legalDictVO.getCompanyVO());
                }
                if (ObjectUtils.isNotEmpty(legalDictVO.getBankVO())){
                    applyPrivateUserDetailVO.setBankVO(legalDictVO.getBankVO());
                }
                if (ObjectUtils.isNotEmpty(legalDictVO.getCertListVO())){
                    applyPrivateUserDetailVO.setCertList(legalDictVO.getCertListVO());
                }
            }
        }
        if(StringUtils.isNotBlank(userPrivateUserView.getUserTypeId())){
            PCMerchMerchantUserTypeVO.DetailVO userTypeVo =  merchMerchantUserTypeRpc.detailMerchantUserType(new PCMerchMerchantUserTypeDTO.IdDTO(userPrivateUserView.getUserTypeId()));
            if(null != userTypeVo){
                applyPrivateUserDetailVO.setUserTypeId(userTypeVo.getId());
                applyPrivateUserDetailVO.setUserTypeName(userTypeVo.getUserTypeName());
            }
        }

        return applyPrivateUserDetailVO;
    }

    @Override
    public void apply(PCMerchUserDTO.ApplyDTO dto) {
        if (ObjectUtils.isEmpty(dto)){
            throw new BusinessException("参数为空异常！！");
        }
        if(StringUtils.isBlank(dto.getId())){
            throw new BusinessException("ID不能为空");
        }
        if(!EnumUtil.checkEnumCodeWithExcludes(dto.getState(),ApplyStateEnum.class,ApplyStateEnum.待审.getCode())){
            throw new BusinessException("审核枚举错误");
        }
        if (dto.getState().equals(ApplyStateEnum.拒审.getCode()) && StringUtils.isBlank(dto.getRejectWhy())){
            throw new BusinessException("请填写拒绝原因！！");
        }
        UserPrivateUser userPrivateUser = userPrivateUserRepository.getById(dto.getId());
        if(null == userPrivateUser){
            throw new BusinessException("私域会员申请不存在");
        }
        userPrivateUser.setState(dto.getState());
        if(ApplyStateEnum.通过.getCode().equals(dto.getState())){
            userPrivateUser.setUserTypeId(dto.getUserTypeId());
            userPrivateUser.setBindState(PrivateUserBindStateEnum.关联.getCode());
        }else if(ApplyStateEnum.拒审.getCode().equals(dto.getState())){
            userPrivateUser.setBindState(PrivateUserBindStateEnum.解除.getCode());
            userPrivateUser.setRejectWhy(dto.getRejectWhy());
        }
        boolean bool = userPrivateUserRepository.updateById(userPrivateUser);
        if(!bool){
            throw new BusinessException("会员审核失败");
        }

        //添加加入私欲会员记录
        CommonShopVO.SimpleVO simpleVO = commonShopRpc.shopDetails(userPrivateUser.getShopId());
        UserPrivateUserLog privateUserLog = new UserPrivateUserLog();
        privateUserLog.setPrivateUserId(userPrivateUser.getId());
        privateUserLog.setShopId(userPrivateUser.getShopId());
        privateUserLog.setUserId(userPrivateUser.getUserId());
        privateUserLog.setState(dto.getState());
        if (ObjectUtils.isNotEmpty(simpleVO)){
            privateUserLog.setShopName(StringUtils.isBlank(simpleVO.getShopName())?"":simpleVO.getShopName());
        }
        User user1 = userRepository.getById(userPrivateUser.getUserId());
        if (ObjectUtils.isNotEmpty(user1)){
            privateUserLog.setUserName(StringUtils.isBlank(user1.getUserName())?"":user1.getUserName());
        }
        privateUserLog.setRemark(StringUtils.isBlank(dto.getRejectWhy())?"":dto.getRejectWhy());
        userPrivateUserLogRepository.save(privateUserLog);
    }

    @Override
    public PCMerchUserVO.UserSimpleVO innerUserSimple(String userId) {
        if(StringUtils.isBlank(userId)){
            return null;
        }
        User user =  userRepository.getById(userId);
        if(null == user){
            return null;
        }
        PCMerchUserVO.UserSimpleVO userSimpleVO = new PCMerchUserVO.UserSimpleVO();
        userSimpleVO.setUserId(user.getId());
        userSimpleVO.setUserName(user.getUserName());
        return userSimpleVO;
    }

}
