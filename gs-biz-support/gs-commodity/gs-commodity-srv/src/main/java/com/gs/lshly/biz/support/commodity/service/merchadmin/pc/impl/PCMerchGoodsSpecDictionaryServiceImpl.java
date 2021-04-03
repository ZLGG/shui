package com.gs.lshly.biz.support.commodity.service.merchadmin.pc.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.gs.lshly.biz.support.commodity.entity.GoodsCategorySpec;
import com.gs.lshly.biz.support.commodity.entity.GoodsSpecDictionaryItem;
import com.gs.lshly.biz.support.commodity.entity.GoodsSpecInfo;
import com.gs.lshly.biz.support.commodity.entity.GoodsSpecsDictionary;
import com.gs.lshly.biz.support.commodity.repository.IGoodsCategorySpecRepository;
import com.gs.lshly.biz.support.commodity.repository.IGoodsSpecDictionaryItemRepository;
import com.gs.lshly.biz.support.commodity.repository.IGoodsSpecInfoRepository;
import com.gs.lshly.biz.support.commodity.repository.IGoodsSpecsDictionaryRepository;
import com.gs.lshly.biz.support.commodity.service.merchadmin.pc.IPCMerchGoodsSpecDictionaryService;
import com.gs.lshly.common.exception.BusinessException;
import com.gs.lshly.common.struct.merchadmin.pc.commodity.dto.PCMerchGoodsCategoryDTO;
import com.gs.lshly.common.struct.merchadmin.pc.commodity.dto.PCMerchGoodsSpecInfoDTO;
import com.gs.lshly.common.struct.merchadmin.pc.commodity.vo.PCMerchGoodsSpecDictionaryItemVO;
import com.gs.lshly.common.struct.merchadmin.pc.commodity.vo.PCMerchGoodsSpecDictionaryVO;
import com.gs.lshly.middleware.mybatisplus.MybatisPlusUtil;

/**
 * @Author Starry
 * @Date 20:28 2020/10/19
 */
@Component
public class PCMerchGoodsSpecDictionaryServiceImpl implements IPCMerchGoodsSpecDictionaryService {
    @Autowired
    private IGoodsSpecsDictionaryRepository repository;
    @Autowired
    private IGoodsSpecDictionaryItemRepository dictionaryItemRepository;
    @Autowired
    private IGoodsCategorySpecRepository categorySpecRepository;
    @Autowired
    private IGoodsSpecInfoRepository specInfoRepository;

    @Override
    public List<PCMerchGoodsSpecDictionaryVO.DetailVO> listSpecDetail(PCMerchGoodsCategoryDTO.IdDTO dto) {
        if (dto == null) {
            throw new BusinessException("参数不能为空！");
        }
        QueryWrapper<GoodsCategorySpec> wrapper = MybatisPlusUtil.query();
        wrapper.eq("category_id",dto.getId());

        List<GoodsCategorySpec> categorySpecs = categorySpecRepository.list(wrapper);
        if (ObjectUtils.isEmpty(categorySpecs)){
            return null;
        }
        //查询与类目关联的规格列表
        List<GoodsSpecsDictionary> specsDictionaries = new ArrayList<>();
        for (GoodsCategorySpec categorySpec : categorySpecs){
            QueryWrapper<GoodsSpecsDictionary> wrapperBoost = MybatisPlusUtil.query();
            wrapperBoost.eq("id",categorySpec.getSpecId());
            specsDictionaries.add(repository.getOne(wrapperBoost));
        }
        List<PCMerchGoodsSpecDictionaryVO.DetailVO> detailVOS = specsDictionaries
                .stream()
                .map(e -> ConverToDetailVO(e)).collect(Collectors.toList());
        for (int i = 0; i < detailVOS.size(); i++) {
            //查询规格下的规格值
            for (PCMerchGoodsSpecDictionaryVO.DetailVO spec : detailVOS) {

                QueryWrapper<GoodsSpecDictionaryItem> queryWrapper = new QueryWrapper<>();
                queryWrapper.eq("spec_id", spec.getId());

                if (dictionaryItemRepository.list(queryWrapper) != null && dictionaryItemRepository.list(queryWrapper).size() > 0) {
                    List<PCMerchGoodsSpecDictionaryItemVO.ListVO> listVOS = dictionaryItemRepository.list(queryWrapper).stream()
                            .map(e -> ConverToItemlVO(e))
                            .collect(Collectors.toList());
                    detailVOS.get(i++).setList(listVOS);
                }
            }
        }
        return detailVOS;
    }

    @Override
    public void updateSpecInfo(List<PCMerchGoodsSpecInfoDTO.ETO> etoList,String goodsId) {

        if (ObjectUtils.isNotEmpty(etoList)){
            //更新前的规格拓展信息
            QueryWrapper<GoodsSpecInfo> wrapper = MybatisPlusUtil.query();
            wrapper.eq("good_id",goodsId);
            List<GoodsSpecInfo> specInfos = specInfoRepository.list(wrapper);
            for (GoodsSpecInfo specInfo : specInfos){
                for (PCMerchGoodsSpecInfoDTO.ETO eto:etoList){
                    if (specInfo.getSpecName().equals(eto.getSpecName())){
                        String afterValue = StringUtils.join(specInfo.getSpecValue(),",",eto.getSpecValue());
                        specInfo.setSpecValue(afterValue);
                        break;
                    }
                }
            }
            specInfoRepository.saveOrUpdateBatch(specInfos);
        }
    }

    private PCMerchGoodsSpecDictionaryItemVO.ListVO ConverToItemlVO(GoodsSpecDictionaryItem e) {
        PCMerchGoodsSpecDictionaryItemVO.ListVO listVO = new PCMerchGoodsSpecDictionaryItemVO.ListVO();
        BeanUtils.copyProperties(e,listVO);
        return listVO;
    }

    private PCMerchGoodsSpecDictionaryVO.DetailVO ConverToDetailVO(GoodsSpecsDictionary e) {
        PCMerchGoodsSpecDictionaryVO.DetailVO detailVO = new PCMerchGoodsSpecDictionaryVO.DetailVO();
        BeanUtils.copyProperties(e,detailVO);
        return detailVO;
    }
}
