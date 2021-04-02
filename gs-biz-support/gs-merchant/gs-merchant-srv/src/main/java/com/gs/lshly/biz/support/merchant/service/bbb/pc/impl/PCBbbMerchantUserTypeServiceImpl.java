package com.gs.lshly.biz.support.merchant.service.bbb.pc.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.gs.lshly.biz.support.merchant.entity.MerchantUserType;
import com.gs.lshly.biz.support.merchant.entity.MerchantUserTypeRatio;
import com.gs.lshly.biz.support.merchant.repository.IMerchantUserTypeRatioRepository;
import com.gs.lshly.biz.support.merchant.repository.IMerchantUserTypeRepository;
import com.gs.lshly.biz.support.merchant.service.bbb.pc.IPCBbbMerchantUserTypeService;
import com.gs.lshly.common.struct.bbb.pc.merchant.vo.PCBbbMerchantUserTypeVO;
import com.gs.lshly.common.utils.ListUtil;
import com.gs.lshly.middleware.mybatisplus.MybatisPlusUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;


/**
* <p>
*  服务实现类
* </p>
* @author xxfc
* @since 2020-12-25
*/
@Component
public class PCBbbMerchantUserTypeServiceImpl implements IPCBbbMerchantUserTypeService {

    @Autowired
    private IMerchantUserTypeRepository merchantUserTypeRepository;
    @Autowired
    private IMerchantUserTypeRatioRepository userTypeRatioRepository;


    @Override
    public PCBbbMerchantUserTypeVO.DetailsVO details(String id) {
        MerchantUserType merchantUserType = merchantUserTypeRepository.getById(id);
        PCBbbMerchantUserTypeVO.DetailsVO detailVo = new PCBbbMerchantUserTypeVO.DetailsVO();
        if(null == merchantUserType){
           return detailVo.setRatio(new BigDecimal(1));
        }
        BeanUtils.copyProperties(merchantUserType, detailVo);
        detailVo.setUserTypeId(merchantUserType.getId());

        QueryWrapper<MerchantUserTypeRatio> wrapper = MybatisPlusUtil.query();
        wrapper.eq("user_type_id",id);
        wrapper.orderByDesc("cdate");
        List<MerchantUserTypeRatio> ratioList = userTypeRatioRepository.list(wrapper);
        if (ObjectUtils.isNotEmpty(ratioList)){
            List<PCBbbMerchantUserTypeVO.RatioGoodsVO> ratioGoodsVOS = ListUtil.listCover(PCBbbMerchantUserTypeVO.RatioGoodsVO.class,ratioList);
            detailVo.setRatioGoodsVOS(ratioGoodsVOS);
        }
        return detailVo;
    }
}
