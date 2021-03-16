package com.gs.lshly.biz.support.user.service.merchadmin.h5.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.gs.lshly.biz.support.user.entity.UserPrivateUser;
import com.gs.lshly.biz.support.user.enums.PrivateUserApplyStateEnum;
import com.gs.lshly.biz.support.user.enums.PrivateUserBindStateEnum;
import com.gs.lshly.biz.support.user.enums.UserQueryTypeEnum;
import com.gs.lshly.biz.support.user.mapper.UserPrivateUserMapper;
import com.gs.lshly.biz.support.user.mapper.view.UserPrivateUserView;
import com.gs.lshly.biz.support.user.repository.IUserPrivateUserRepository;
import com.gs.lshly.biz.support.user.service.merchadmin.h5.IH5MerchUserPrivateUserService;
import com.gs.lshly.common.enums.ApplyStateEnum;
import com.gs.lshly.common.exception.BusinessException;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.common.struct.common.CommonShopVO;
import com.gs.lshly.common.struct.common.LegalDictDTO;
import com.gs.lshly.common.struct.common.LegalDictVO;
import com.gs.lshly.common.struct.merchadmin.h5.user.dto.H5MerchUserPrivateUserDTO;
import com.gs.lshly.common.struct.merchadmin.h5.user.qto.H5MerchUserPrivateUserQTO;
import com.gs.lshly.common.struct.merchadmin.h5.user.vo.H5MerchUserPrivateUserVO;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.dto.PCMerchMerchantUserTypeDTO;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.vo.PCMerchMerchantUserTypeVO;
import com.gs.lshly.common.struct.merchadmin.pc.user.dto.PCMerchUserDTO;
import com.gs.lshly.common.struct.merchadmin.pc.user.qto.PCMerchUserQTO;
import com.gs.lshly.common.struct.merchadmin.pc.user.vo.PCMerchUserVO;
import com.gs.lshly.common.utils.BeanCopyUtils;
import com.gs.lshly.common.utils.EnumUtil;
import com.gs.lshly.common.utils.ListUtil;
import com.gs.lshly.rpc.api.common.ICommonShopRpc;
import com.gs.lshly.rpc.api.common.ILegalDictRpc;
import com.gs.lshly.rpc.api.merchadmin.h5.user.IH5MerchUserPrivateUserRpc;
import com.gs.lshly.rpc.api.merchadmin.pc.merchant.IPCMerchMerchantUserTypeRpc;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.gs.lshly.middleware.mybatisplus.MybatisPlusUtil;

import java.util.ArrayList;
import java.util.List;


/**
* <p>
*  服务实现类
* </p>
* @author zst
* @since 2021-01-20
*/
@Component
public class H5MerchUserPrivateUserServiceImpl implements IH5MerchUserPrivateUserService {

    @Autowired
    private IUserPrivateUserRepository userPrivateUserRepository;
    @Autowired
    private IUserPrivateUserRepository repository;
    @Autowired
    private UserPrivateUserMapper userPrivateUserMapper;
    @DubboReference
    private IPCMerchMerchantUserTypeRpc merchMerchantUserTypeRpc;

    @DubboReference
    private ICommonShopRpc commonShopRpc;
    @DubboReference
    private ILegalDictRpc legalDictRpc;

    @Override
    public PageData<H5MerchUserPrivateUserVO.ListVO> pageData(H5MerchUserPrivateUserQTO.QTO qto) {
        QueryWrapper<UserPrivateUserView> queryWrapper = MybatisPlusUtil.query();
        queryWrapper.eq("pu.state", PrivateUserApplyStateEnum.通过.getCode());
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
        List<H5MerchUserPrivateUserVO.ListVO> voList = new ArrayList<>();
        if(ObjectUtils.isEmpty(userViewIPage) || ObjectUtils.isEmpty(userViewIPage.getRecords())){
            return new PageData<>();
        }
        List<String> userTypeIdList = new ArrayList<>();
        for(UserPrivateUserView userPrivateUserView:userViewIPage.getRecords()){
            H5MerchUserPrivateUserVO.ListVO listVO = new H5MerchUserPrivateUserVO.ListVO();
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
                for(H5MerchUserPrivateUserVO.ListVO listVO:voList){
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
                for(H5MerchUserPrivateUserVO.ListVO userVO:voList){
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
    public PageData<H5MerchUserPrivateUserVO.ApplyListVO> applyPageList(H5MerchUserPrivateUserQTO.ApplyListQTO qto) {
        QueryWrapper<UserPrivateUserView> queryWrapper = MybatisPlusUtil.query();
        queryWrapper.eq("pu.shop_id",qto.getJwtShopId());
        if(ObjectUtils.isNotNull(qto.getApplyState()) && !PrivateUserApplyStateEnum.全部.getCode().equals(qto.getApplyState())){
            queryWrapper.eq("pu.state",qto.getApplyState());
        }
        IPage<UserPrivateUserView> page = MybatisPlusUtil.pager(qto);
        IPage<UserPrivateUserView> userViewIPage = userPrivateUserMapper.selectUserPrivateUser(page,queryWrapper);
        List<String> legalIdist = new ArrayList<>();
        List<H5MerchUserPrivateUserVO.ApplyListVO> voList = new ArrayList<>();
        if(ObjectUtils.isEmpty(userViewIPage) || ObjectUtils.isNotEmpty(userViewIPage.getRecords())){
            for(UserPrivateUserView userPrivateUserView:userViewIPage.getRecords()){
                H5MerchUserPrivateUserVO.ApplyListVO applyListVO = new H5MerchUserPrivateUserVO.ApplyListVO();
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
                for(H5MerchUserPrivateUserVO.ApplyListVO applyListVO:voList){
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
    public H5MerchUserPrivateUserVO.ApplyPrivateUserDetailVO applyDetailUser(PCMerchUserDTO.IdDTO dto) {
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
        H5MerchUserPrivateUserVO.ApplyPrivateUserDetailVO applyPrivateUserDetailVO = new H5MerchUserPrivateUserVO.ApplyPrivateUserDetailVO();
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
    public void apply(H5MerchUserPrivateUserDTO.ApplyDTO dto) {
        if (ObjectUtils.isEmpty(dto)){
            throw new BusinessException("参数为空异常！！");
        }
        if(StringUtils.isBlank(dto.getId())){
            throw new BusinessException("ID不能为空");
        }
        if(!EnumUtil.checkEnumCodeWithExcludes(dto.getState(), ApplyStateEnum.class,ApplyStateEnum.待审.getCode())){
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
    }



    @Override
    public void addUserPrivateUser(H5MerchUserPrivateUserDTO.ETO eto) {
        UserPrivateUser userPrivateUser = new UserPrivateUser();
        BeanUtils.copyProperties(eto, userPrivateUser);
        repository.save(userPrivateUser);
    }


    @Override
    public void deleteUserPrivateUser(H5MerchUserPrivateUserDTO.IdDTO dto) {
        repository.removeById(dto.getId());
    }
    @Override
    public void editUserPrivateUser(H5MerchUserPrivateUserDTO.ETO eto) {
        UserPrivateUser userPrivateUser = new UserPrivateUser();
        BeanUtils.copyProperties(eto, userPrivateUser);
        repository.updateById(userPrivateUser);
    }

    @Override
    public H5MerchUserPrivateUserVO.PrivateUserDetailVO detailUserPrivateUser(H5MerchUserPrivateUserDTO.IdDTO dto) {
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
        H5MerchUserPrivateUserVO.PrivateUserDetailVO detailVo = new H5MerchUserPrivateUserVO.PrivateUserDetailVO();
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


}
