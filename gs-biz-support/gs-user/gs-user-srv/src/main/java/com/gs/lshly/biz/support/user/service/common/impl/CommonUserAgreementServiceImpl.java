package com.gs.lshly.biz.support.user.service.common.impl;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gs.lshly.biz.support.user.entity.UserAgreement;
import com.gs.lshly.biz.support.user.repository.IUserAgreementRepository;
import com.gs.lshly.biz.support.user.service.common.ICommonUserAgreementService;
import com.gs.lshly.common.struct.common.dto.CommonUserAgreementDTO;
import com.gs.lshly.common.struct.common.qto.CommonUserAgreementQTO;
import com.gs.lshly.common.struct.common.vo.CommonUserAgreementVO;
import com.gs.lshly.middleware.mybatisplus.MybatisPlusUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
* <p>
*  服务实现类
* </p>
* @author xxfc
* @since 2021-01-14
*/
@Component
public class CommonUserAgreementServiceImpl implements ICommonUserAgreementService {

    @Autowired
    private IUserAgreementRepository repository;

    @Override
    public void editUserAgreement(CommonUserAgreementDTO.ETO dto) {
        QueryWrapper<UserAgreement> userAgreementQueryWrapper = MybatisPlusUtil.query();
        userAgreementQueryWrapper.eq("user_type",dto.getUserType());
        userAgreementQueryWrapper.orderByDesc("cdate");
        UserAgreement userAgreement = repository.getOne(userAgreementQueryWrapper);
        if(null == userAgreement){
            userAgreement = new UserAgreement();
            BeanUtils.copyProperties(dto, userAgreement);
            repository.save(userAgreement);
        }else{
            BeanUtils.copyProperties(dto, userAgreement);
            repository.updateById(userAgreement);
        }
    }

    @Override
    public CommonUserAgreementVO.DetailVO detailUserAgreement(CommonUserAgreementQTO.QTO dto) {
        QueryWrapper<UserAgreement> userAgreementQueryWrapper = MybatisPlusUtil.query();
        UserAgreement userAgreement = repository.getOne(userAgreementQueryWrapper);
        if(null == userAgreement){
           return null;
        }
        CommonUserAgreementVO.DetailVO detailVo = new CommonUserAgreementVO.DetailVO();
        BeanUtils.copyProperties(userAgreement, detailVo);
        return detailVo;
    }

}
