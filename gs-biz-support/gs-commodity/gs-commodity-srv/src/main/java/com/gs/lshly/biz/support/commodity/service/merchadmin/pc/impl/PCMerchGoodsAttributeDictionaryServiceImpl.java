package com.gs.lshly.biz.support.commodity.service.merchadmin.pc.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.gs.lshly.biz.support.commodity.entity.GoodsAttributeDictionary;
import com.gs.lshly.biz.support.commodity.entity.GoodsAttributeDictionaryItem;
import com.gs.lshly.biz.support.commodity.entity.GoodsCategoryAttribute;
import com.gs.lshly.biz.support.commodity.repository.IGoodsAttributeDictionaryItemRepository;
import com.gs.lshly.biz.support.commodity.repository.IGoodsAttributeDictionaryRepository;
import com.gs.lshly.biz.support.commodity.repository.IGoodsCategoryAttributeRepository;
import com.gs.lshly.biz.support.commodity.service.merchadmin.pc.IPCMerchGoodsAttributeDictionaryService;
import com.gs.lshly.common.exception.BusinessException;
import com.gs.lshly.common.struct.merchadmin.pc.commodity.dto.PCMerchGoodsCategoryDTO;
import com.gs.lshly.common.struct.merchadmin.pc.commodity.vo.PCMerchGoodsAttributeDictionaryItemVO;
import com.gs.lshly.common.struct.merchadmin.pc.commodity.vo.PCMerchGoodsAttributeDictionaryVO;
import com.gs.lshly.common.struct.platadmin.commodity.vo.GoodsAttributeDictionaryItemVO;
import com.gs.lshly.common.struct.platadmin.commodity.vo.GoodsAttributeDictionaryVO;
import com.gs.lshly.middleware.mybatisplus.MybatisPlusUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author Starry
 * @Date 14:39 2020/10/20
 */
@Component
public class PCMerchGoodsAttributeDictionaryServiceImpl implements IPCMerchGoodsAttributeDictionaryService {
    @Autowired
    private IGoodsAttributeDictionaryRepository attributeDictionaryRepository;
    @Autowired
    private IGoodsAttributeDictionaryItemRepository attributeDictionaryItemRepository;
    @Autowired
    private IGoodsCategoryAttributeRepository categoryAttributeRepository;

    @Override
    public List<PCMerchGoodsAttributeDictionaryVO.DetailVO> listAttributeDetail(PCMerchGoodsCategoryDTO.IdDTO dto) {
        if (dto == null) {
            throw new BusinessException("参数不能为空！");
        }
        QueryWrapper<GoodsCategoryAttribute> wrapper = MybatisPlusUtil.query();
        wrapper.eq("category_id",dto.getId());

        List<GoodsCategoryAttribute> categoryAttributes = categoryAttributeRepository.list(wrapper);
        if (ObjectUtils.isEmpty(categoryAttributes)){
            return null;
        }
        //查询与类目关联的属性列
        List<GoodsAttributeDictionary> goodsAttributeDictionaries = new ArrayList<>();
        for (GoodsCategoryAttribute categoryAttribute : categoryAttributes){
            QueryWrapper<GoodsAttributeDictionary> wrapperBoost = MybatisPlusUtil.query();
            wrapperBoost.eq("id",categoryAttribute.getAttributeId());
            goodsAttributeDictionaries.add(attributeDictionaryRepository.getOne(wrapperBoost));
        }

        List<PCMerchGoodsAttributeDictionaryVO.DetailVO> detailVOS = goodsAttributeDictionaries
                .stream()
                .map(e -> ConverToDetailVO(e)).collect(Collectors.toList());
        for(int i =0 ;i<detailVOS.size();i++){
            //查询属性下的属性值
            for (PCMerchGoodsAttributeDictionaryVO.DetailVO dictionary : detailVOS){

                QueryWrapper<GoodsAttributeDictionaryItem> queryWrapper = new QueryWrapper<>();
                queryWrapper.eq("attribute_id",dictionary.getId());

                if (attributeDictionaryItemRepository.list(queryWrapper) !=null && attributeDictionaryItemRepository.list(queryWrapper).size()>0) {
                    List<PCMerchGoodsAttributeDictionaryItemVO.ListVO> listVOS = attributeDictionaryItemRepository.list(queryWrapper).stream()
                            .map(e -> ConverToItemlVO(e))
                            .collect(Collectors.toList());
                    detailVOS.get(i++).setList(listVOS);
                }
            }
        }
        return detailVOS;
    }

    private PCMerchGoodsAttributeDictionaryItemVO.ListVO ConverToItemlVO(GoodsAttributeDictionaryItem e) {
        PCMerchGoodsAttributeDictionaryItemVO.ListVO listVO = new PCMerchGoodsAttributeDictionaryItemVO.ListVO();
        BeanUtils.copyProperties(e,listVO);
        return listVO;
    }

    private PCMerchGoodsAttributeDictionaryVO.DetailVO ConverToDetailVO(GoodsAttributeDictionary e) {
        PCMerchGoodsAttributeDictionaryVO.DetailVO detailVO = new PCMerchGoodsAttributeDictionaryVO.DetailVO();
        BeanUtils.copyProperties(e,detailVO);
        return detailVO;
    }
}
