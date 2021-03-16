package com.gs.lshly.biz.support.commodity.service.merchadmin.pc.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.gs.lshly.biz.support.commodity.entity.GoodsShopNavigation;
import com.gs.lshly.biz.support.commodity.entity.GoodsTempalte;
import com.gs.lshly.biz.support.commodity.repository.IGoodsShopNavigationRepository;
import com.gs.lshly.biz.support.commodity.repository.IGoodsTempalteRepository;
import com.gs.lshly.biz.support.commodity.service.merchadmin.pc.IPCMerchGoodsShopNavigationService;
import com.gs.lshly.common.exception.BusinessException;
import com.gs.lshly.common.struct.merchadmin.pc.commodity.dto.PCMerchGoodsShopNavigationDTO;
import com.gs.lshly.common.struct.merchadmin.pc.commodity.qto.PCMerchGoodsShopNavigationQTO;
import com.gs.lshly.common.struct.merchadmin.pc.commodity.vo.PCMerchGoodsShopNavigationVO;
import com.gs.lshly.middleware.mybatisplus.MybatisPlusUtil;
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
* @author Starry
* @since 2020-11-09
*/
@Component
public class PCMerchGoodsShopNavigationServiceImpl implements IPCMerchGoodsShopNavigationService {

    @Autowired
    private IGoodsShopNavigationRepository repository;
    @Autowired
    private IGoodsTempalteRepository tempalteRepository;


    @Override
    public void bindGoods(PCMerchGoodsShopNavigationDTO.BindGoodsDTO dto) {
        //校验数据
        checkData(dto);

        //清除关联关系
        QueryWrapper<GoodsShopNavigation> wrapper = MybatisPlusUtil.query();
        wrapper.in("goods_id",dto.getGoodsIdList());
        repository.remove(wrapper);

        //重新建立关联
        for (String goodsId : dto.getGoodsIdList()){
            GoodsShopNavigation shopNavigation = new GoodsShopNavigation();
            shopNavigation.setShopNavigation(dto.getId());
            shopNavigation.setGoodsId(goodsId);
            shopNavigation.setShopId(dto.getJwtShopId());
            shopNavigation.setMerchantId(dto.getJwtMerchantId());
            repository.save(shopNavigation);
        }
    }

    @Override
    public List<PCMerchGoodsShopNavigationVO.ListVO> ListInnerService(PCMerchGoodsShopNavigationQTO.QTO qto) {
        if (ObjectUtils.isEmpty(qto)){
            return new ArrayList<>();
        }
        List<GoodsShopNavigation> navigations = new ArrayList<>();
        if (ObjectUtils.isNotEmpty(qto.getShopNavigation())){
            QueryWrapper<GoodsShopNavigation> boost = MybatisPlusUtil.query();
            boost.in("shop_navigation",qto.getShopNavigation());
            navigations = repository.list(boost);

        }
        if (ObjectUtils.isEmpty(navigations)){
            return new ArrayList<>();
        }
        List<PCMerchGoodsShopNavigationVO.ListVO> listVOS = navigations.stream().map(e ->{
            PCMerchGoodsShopNavigationVO.ListVO listVO = new PCMerchGoodsShopNavigationVO.ListVO();
            BeanUtils.copyProperties(e,listVO);
            return listVO;
        }).collect(Collectors.toList());
        return listVOS;
    }

    @Override
    public void deleteInnerService(PCMerchGoodsShopNavigationDTO.IdListDTO dto) {
        if (ObjectUtils.isNotEmpty(dto.getIdList())){
            QueryWrapper<GoodsShopNavigation> boost = MybatisPlusUtil.query();
            boost.in("shop_navigation",dto.getIdList());
            repository.remove(boost);
        }
    }

    @Override
    public void saveInnerService(GoodsShopNavigation shopNavigation) {
        repository.save(shopNavigation);
    }

    @Override
    public void saveTempalteInnerService(GoodsTempalte tempalte) {
        tempalteRepository.save(tempalte);
    }

    private void checkData(PCMerchGoodsShopNavigationDTO.BindGoodsDTO dto){
        if (ObjectUtils.isEmpty(dto)){
            throw new BusinessException("参数为空，异常！！");
        }
        if (ObjectUtils.isEmpty(dto.getGoodsIdList())){
            throw new BusinessException("请选择要关联的商品");
        }
        if (ObjectUtils.isEmpty(dto.getId())){
            throw new BusinessException("请选择要关联的店铺自定义类目");
        }
    }
}
