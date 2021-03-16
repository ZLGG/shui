package com.gs.lshly.biz.support.merchant.service.merchadmin.pc.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.gs.lshly.biz.support.merchant.entity.MerchantUserType;
import com.gs.lshly.biz.support.merchant.entity.MerchantUserTypeRatio;
import com.gs.lshly.biz.support.merchant.repository.IMerchantUserTypeRatioRepository;
import com.gs.lshly.biz.support.merchant.repository.IMerchantUserTypeRepository;
import com.gs.lshly.biz.support.merchant.service.merchadmin.pc.IPCMerchMerchantUserTypeService;
import com.gs.lshly.common.exception.BusinessException;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.dto.PCMerchMerchantUserTypeDTO;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.dto.PCMerchMerchantUserTypeRatioDTO;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.qto.PCMerchMerchantUserTypeQTO;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.vo.PCMerchMerchantUserTypeVO;
import com.gs.lshly.common.struct.platadmin.commodity.dto.GoodsBrandDTO;
import com.gs.lshly.common.utils.BeanCopyUtils;
import com.gs.lshly.common.utils.ListUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.gs.lshly.middleware.mybatisplus.MybatisPlusUtil;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


/**
* <p>
*  服务实现类
* </p>
* @author xxfc
* @since 2020-12-25
*/
@Component
public class PCMerchMerchantUserTypeServiceImpl implements IPCMerchMerchantUserTypeService {

    @Autowired
    private IMerchantUserTypeRepository repository;
    @Autowired
    private IMerchantUserTypeRatioRepository userTypeRatioRepository;

    @Override
    public PageData<PCMerchMerchantUserTypeVO.ListVO> pageData(PCMerchMerchantUserTypeQTO.QTO qto) {
        QueryWrapper<MerchantUserType> wrapper = MybatisPlusUtil.query();
        wrapper.eq("shop_id",qto.getJwtShopId());
        IPage<MerchantUserType> page = MybatisPlusUtil.pager(qto);
        repository.page(page, wrapper);
        return MybatisPlusUtil.toPageData(qto, PCMerchMerchantUserTypeVO.ListVO.class, page);
    }

    @Override
    public List<PCMerchMerchantUserTypeVO.ListVO> listData(BaseDTO dto) {
        QueryWrapper<MerchantUserType> wrapper = MybatisPlusUtil.query();
        wrapper.eq("shop_id",dto.getJwtShopId());
        List<MerchantUserType> merchantUserTypeList = repository.list(wrapper);
        if (ObjectUtils.isEmpty(merchantUserTypeList)){
            return new ArrayList<>();
        }
        List<PCMerchMerchantUserTypeVO.ListVO> listVOS = ListUtil.listCover(PCMerchMerchantUserTypeVO.ListVO.class,merchantUserTypeList);
        return listVOS;
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveMerchantUserType(PCMerchMerchantUserTypeDTO.ETO eto) {
        //数据校验
        checkBrandData(eto);

        MerchantUserType merchantUserType = new MerchantUserType();
        BeanCopyUtils.copyProperties(eto, merchantUserType);
        repository.saveOrUpdate(merchantUserType);

        //若是编辑则先删除折扣对应的商品列表
        if (StringUtils.isNotBlank(eto.getId())){
            QueryWrapper<MerchantUserTypeRatio> wrapper = MybatisPlusUtil.query();
            wrapper.eq("user_type_id",eto.getId());
            wrapper.eq("shop_id",eto.getJwtShopId());
            userTypeRatioRepository.remove(wrapper);
        }

        List<MerchantUserTypeRatio> list = new ArrayList<>();
        for (PCMerchMerchantUserTypeRatioDTO.ETO ratio : eto.getUserTypeRatioEto()){
            MerchantUserTypeRatio userTypeRatio = new MerchantUserTypeRatio();
            BeanCopyUtils.copyProperties(ratio,userTypeRatio);
            userTypeRatio.setShopId(merchantUserType.getShopId());
            userTypeRatio.setUserTypeId(merchantUserType.getId());
            list.add(userTypeRatio);
        }
        userTypeRatioRepository.saveBatch(list);
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteBatch(PCMerchMerchantUserTypeDTO.IdListDTO dto) {
        if(ObjectUtils.isEmpty(dto) || ObjectUtils.isEmpty(dto.getIdList())){
            throw new BusinessException("请选择要删除的会员类型");
        }

        QueryWrapper<MerchantUserTypeRatio> wrapper = MybatisPlusUtil.query();
        wrapper.in("user_type_id",dto.getIdList());
        wrapper.eq("shop_id",dto.getJwtShopId());
        userTypeRatioRepository.remove(wrapper);

        QueryWrapper<MerchantUserType> removeWrapper = MybatisPlusUtil.query();
        removeWrapper.in("id",dto.getIdList());
        removeWrapper.eq("shop_id",dto.getJwtShopId());
        repository.remove(removeWrapper);
    }


    @Override
    public PCMerchMerchantUserTypeVO.DetailVO detailMerchantUserType(PCMerchMerchantUserTypeDTO.IdDTO dto) {
        MerchantUserType merchantUserType = repository.getById(dto.getId());
        PCMerchMerchantUserTypeVO.DetailVO detailVo = new PCMerchMerchantUserTypeVO.DetailVO();
        if(ObjectUtils.isEmpty(merchantUserType)){
            throw new BusinessException("数据查询异常！！");
        }
        BeanUtils.copyProperties(merchantUserType, detailVo);

        QueryWrapper<MerchantUserTypeRatio> wrapper = MybatisPlusUtil.query();
        wrapper.eq("shop_id",dto.getJwtShopId());
        wrapper.eq("user_type_id",dto.getId());
        List<MerchantUserTypeRatio> ratioList = userTypeRatioRepository.list(wrapper);
        if (ObjectUtils.isNotEmpty(ratioList)){
            List<PCMerchMerchantUserTypeVO.GoodsVo> goodsVos = new ArrayList<>();
            for (MerchantUserTypeRatio typeRatio : ratioList){
                PCMerchMerchantUserTypeVO.GoodsVo goodsVo = new PCMerchMerchantUserTypeVO.GoodsVo();
                goodsVo.setGoodsId(typeRatio.getGoodsId());
                goodsVo.setSkuId(typeRatio.getSkuId());
                goodsVos.add(goodsVo);
            }
            detailVo.setUserTypeRatioEto(goodsVos);
        }
        return detailVo;
    }

    @Override
    public List<PCMerchMerchantUserTypeVO.ListVO> list(BaseDTO dto) {
        QueryWrapper<MerchantUserType> wrapper = MybatisPlusUtil.query();
        wrapper.eq("shop_id",dto.getJwtShopId());
        List<MerchantUserType> merchantUserTypeList = repository.list(wrapper);
        return ListUtil.listCover(PCMerchMerchantUserTypeVO.ListVO.class,merchantUserTypeList);
    }

    private void checkBrandData(PCMerchMerchantUserTypeDTO.ETO eto){
        if (ObjectUtils.isEmpty(eto)){
            throw new BusinessException("参数异常");
        }
        if (StringUtils.isBlank(eto.getUserTypeName())){
            throw new BusinessException("会员类型不能为空");
        }
        if (ObjectUtils.isEmpty(eto.getRatio())){
            throw new BusinessException("请填写会员对应的折扣率！");
        }
        if (eto.getRatio().compareTo(BigDecimal.ZERO) == -1 && eto.getRatio().compareTo(new BigDecimal(1)) == 1){
            throw new BusinessException("折扣率值范围为0到1！");
        }
        if (filterSameName(eto) > 0){
            throw new BusinessException(eto.getUserTypeName()+"类型已存在！");
        }
        if (ObjectUtils.isEmpty(eto.getUserTypeRatioEto())){
            throw new BusinessException("请选择该类型折扣的商品！！");
        }
    }

    private int filterSameName(PCMerchMerchantUserTypeDTO.ETO eto){
        QueryWrapper<MerchantUserType> wrapper = MybatisPlusUtil.query();
        wrapper.eq("shop_id",eto.getJwtShopId());
        wrapper.eq("user_type_name",eto.getUserTypeName());
        int count = repository.count(wrapper);

        if (StringUtils.isNotEmpty(eto.getId())){
            QueryWrapper<MerchantUserType> boost = MybatisPlusUtil.query();
            boost.eq("id",eto.getId());
            MerchantUserType userType = repository.getOne(boost);
            if (userType.getUserTypeName().equals(eto.getUserTypeName())){
                return 0;
            }else {
                return count;
            }
        }
        return count;
    }


}
