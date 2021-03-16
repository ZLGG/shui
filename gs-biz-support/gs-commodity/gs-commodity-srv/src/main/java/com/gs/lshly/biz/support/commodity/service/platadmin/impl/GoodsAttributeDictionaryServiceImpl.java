package com.gs.lshly.biz.support.commodity.service.platadmin.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.gs.lshly.biz.support.commodity.entity.GoodsAttributeDictionary;
import com.gs.lshly.biz.support.commodity.entity.GoodsAttributeDictionaryItem;
import com.gs.lshly.biz.support.commodity.entity.GoodsCategoryAttribute;
import com.gs.lshly.biz.support.commodity.repository.IGoodsAttributeDictionaryItemRepository;
import com.gs.lshly.biz.support.commodity.repository.IGoodsAttributeDictionaryRepository;
import com.gs.lshly.biz.support.commodity.repository.IGoodsCategoryAttributeRepository;
import com.gs.lshly.biz.support.commodity.service.platadmin.IGoodsAttributeDictionaryService;
import com.gs.lshly.common.exception.BusinessException;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.platadmin.commodity.dto.GoodsAttributeDictionaryDTO;
import com.gs.lshly.common.struct.platadmin.commodity.dto.GoodsAttributeDictionaryItemDTO;
import com.gs.lshly.common.struct.platadmin.commodity.dto.GoodsCategoryDTO;
import com.gs.lshly.common.struct.platadmin.commodity.qto.GoodsAttributeDictionaryQTO;
import com.gs.lshly.common.struct.platadmin.commodity.vo.GoodsAttributeDictionaryItemVO;
import com.gs.lshly.common.struct.platadmin.commodity.vo.GoodsAttributeDictionaryVO;
import com.gs.lshly.common.utils.ListUtil;
import com.gs.lshly.middleware.mybatisplus.MybatisPlusUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 
 * @since 2020-09-23
 */
@Service
public class GoodsAttributeDictionaryServiceImpl implements IGoodsAttributeDictionaryService {

    @Autowired
    private IGoodsCategoryAttributeRepository categoryAttributeRepository;
    @Autowired
    private IGoodsAttributeDictionaryRepository attributeDictionaryRepository;

    @Autowired
   private IGoodsAttributeDictionaryItemRepository attributeDictionaryItemRepository;

    @Override
    public void addAttribute(GoodsAttributeDictionaryDTO.ETO dto) {
        //校验数据
        checkData(dto);
        GoodsAttributeDictionary dictionary = new GoodsAttributeDictionary();
        BeanUtils.copyProperties(dto,dictionary);
        attributeDictionaryRepository.save(dictionary);
        List<GoodsAttributeDictionaryItemDTO.ETO> item = dto.getList();
        for (GoodsAttributeDictionaryItemDTO.ETO attributeValue : item) {
            GoodsAttributeDictionaryItem goodsAttributeDictionaryItem = new GoodsAttributeDictionaryItem();
            BeanUtils.copyProperties(attributeValue,goodsAttributeDictionaryItem);
            goodsAttributeDictionaryItem.setAttributeId(dictionary.getId());
            attributeDictionaryItemRepository.save(goodsAttributeDictionaryItem);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteAttribute(GoodsAttributeDictionaryDTO.IdDTO dto) {
        //判断该属性是否与类目进行了关联
        QueryWrapper<GoodsCategoryAttribute> boost = MybatisPlusUtil.query();
        boost.eq("attribute_id",dto.getId());
        List<GoodsCategoryAttribute> categoryAttributes = categoryAttributeRepository.list(boost);
        if (ObjectUtils.isNotEmpty(categoryAttributes)){
            throw new BusinessException("属性已经与类目关联不可以删除");
        }
        //删除属性下的属性值列表
        QueryWrapper<GoodsAttributeDictionaryItem> itemQueryWrapper = new QueryWrapper<>();
        itemQueryWrapper.eq("attribute_id",dto.getId());
        attributeDictionaryItemRepository.remove(itemQueryWrapper);
        //删除属性
        QueryWrapper<GoodsAttributeDictionary> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id",dto.getId());
        attributeDictionaryRepository.remove(queryWrapper);
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public void deleteAttributeBatch(GoodsAttributeDictionaryDTO.IdListDTO dto) {
        if (dto == null){
            throw new BusinessException("参数不能为空");
        }
        for (String id : dto.getIdList()){
            deleteAttribute(new GoodsAttributeDictionaryDTO.IdDTO(id));
        }
    }

    @Override
    public GoodsAttributeDictionaryVO.GetCategoryVO getGoodsAttributeDictionary(GoodsAttributeDictionaryDTO.IdDTO dto) {
        //根据属性id查询属性信息
        GoodsAttributeDictionaryVO.GetCategoryVO attribute = new GoodsAttributeDictionaryVO.GetCategoryVO();
        BeanUtils.copyProperties(attributeDictionaryRepository.getById(dto.getId()),attribute);
        //根据属性id查询与他相关联的类目id
        QueryWrapper<GoodsCategoryAttribute> wrapperBoost = MybatisPlusUtil.query();
        wrapperBoost.eq("attribute_id",attribute.getId());
        GoodsCategoryAttribute categoryAttribute = categoryAttributeRepository.getOne(wrapperBoost);
        attribute.setCategoryId(StringUtils.isEmpty(categoryAttribute.getCategoryId())?"":categoryAttribute.getCategoryId());
        return attribute;
    }

    @Override
    public GoodsAttributeDictionaryVO.DetailVO getAttributeDetails(GoodsAttributeDictionaryDTO.IdDTO dto) {
       //获取属性详情信息
        GoodsAttributeDictionary goodsAttributeDictionary = attributeDictionaryRepository.getById(dto.getId());
        GoodsAttributeDictionaryVO.DetailVO detailVO = new GoodsAttributeDictionaryVO.DetailVO();
        BeanUtils.copyProperties(goodsAttributeDictionary,detailVO);
        return detailVO;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateAttribute(GoodsAttributeDictionaryDTO.ETO dto) {
        //校验数据
        checkData(dto);
        GoodsAttributeDictionary dictionary = new GoodsAttributeDictionary();
        BeanUtils.copyProperties(dto,dictionary);
        attributeDictionaryRepository.updateById(dictionary);
        /**
         * 先进行清除该属性下所有的属性值，在进行重新添加
         */
        QueryWrapper<GoodsAttributeDictionaryItem> itemQueryWrapper = new QueryWrapper<>();
        itemQueryWrapper.eq("attribute_id",dto.getId());
        attributeDictionaryItemRepository.remove(itemQueryWrapper);

        for (GoodsAttributeDictionaryItemDTO.ETO attributeValue : dto.getList()) {
            GoodsAttributeDictionaryItem goodsAttributeDictionaryItem = new GoodsAttributeDictionaryItem();
            BeanUtils.copyProperties(attributeValue,goodsAttributeDictionaryItem);
            goodsAttributeDictionaryItem.setAttributeId(dictionary.getId());
            attributeDictionaryItemRepository.save(goodsAttributeDictionaryItem);
        }
    }

    @Override
    public PageData<GoodsAttributeDictionaryVO.ListVO> list(GoodsAttributeDictionaryQTO.QTO qto) {
        QueryWrapper<GoodsAttributeDictionary> boost = MybatisPlusUtil.query();
        boost.orderByAsc("idx","id");
        IPage<GoodsAttributeDictionary> page = MybatisPlusUtil.pager(qto);
        attributeDictionaryRepository.page(page,boost);
        return MybatisPlusUtil.toPageData(qto, GoodsAttributeDictionaryVO.ListVO.class, page);
    }

    @Override
    public List<GoodsAttributeDictionaryVO.ListVO> listAttributes(GoodsCategoryDTO.IdDTO dto) {
        QueryWrapper<GoodsAttributeDictionary> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("category_id",dto.getId());

        List<GoodsAttributeDictionary> goodsAttributeDictionaries = attributeDictionaryRepository.list(queryWrapper);
        if (ObjectUtils.isEmpty(goodsAttributeDictionaries)){
            return new ArrayList<>();
        }
        List<GoodsAttributeDictionaryVO.ListVO> listVOS = goodsAttributeDictionaries
                .stream()
                .map(e ->coverToVO(e)).collect(Collectors.toList());
        return listVOS;
    }

    @Override
    public List<GoodsAttributeDictionaryVO.DetailVO> listAttributeDetail(GoodsCategoryDTO.IdDTO dto) {
        if (dto == null) {
            throw new BusinessException("参数不能为空！");
        }
        QueryWrapper<GoodsCategoryAttribute> wrapper = MybatisPlusUtil.query();
        wrapper.eq("category_id",dto.getId());

        List<GoodsCategoryAttribute> categoryAttributes = categoryAttributeRepository.list(wrapper);
        if (ObjectUtils.isEmpty(categoryAttributes)){
            return new ArrayList<>();
        }
        //根据类目属性关系数据查询属性列表
        List<GoodsAttributeDictionary> goodsAttributeDictionaries = new ArrayList<>();
        for (GoodsCategoryAttribute categoryAttribute : categoryAttributes){
            QueryWrapper<GoodsAttributeDictionary> queryWrapperBoost = MybatisPlusUtil.query();
            queryWrapperBoost.eq("id",categoryAttribute.getAttributeId());
            goodsAttributeDictionaries.add(attributeDictionaryRepository.getOne(queryWrapperBoost));
        }

        List<GoodsAttributeDictionaryVO.DetailVO> detailVOS = goodsAttributeDictionaries
                .stream()
                .map(e -> ConverToDetailVO(e)).collect(Collectors.toList());
         for(int i =0 ;i<detailVOS.size();i++){
             //查询属性下的属性值
             for (GoodsAttributeDictionaryVO.DetailVO dictionary : detailVOS){

                 QueryWrapper<GoodsAttributeDictionaryItem> queryWrapper = new QueryWrapper<>();
                 queryWrapper.eq("attribute_id",dictionary.getId());
                 List<GoodsAttributeDictionaryItem> itemList = attributeDictionaryItemRepository.list(queryWrapper);
                 if (ObjectUtils.isNotEmpty(itemList)) {
                     List<GoodsAttributeDictionaryItemVO.ListVO> listVOS = attributeDictionaryItemRepository.list(queryWrapper).stream()
                             .map(e -> ConverToItemlVO(e))
                             .collect(Collectors.toList());
                     detailVOS.get(i).setList(listVOS);
                 }
             }
         }
         return detailVOS;
    }

    @Override
    public List<GoodsAttributeDictionaryVO.DetailVO> listAttributes() {
        List<GoodsAttributeDictionary> goodsAttributeDictionaries = attributeDictionaryRepository.list();
        if (ObjectUtils.isEmpty(goodsAttributeDictionaries)){
            return new ArrayList<>();
        }
        List<GoodsAttributeDictionaryVO.DetailVO> detailVOS = goodsAttributeDictionaries.stream()
                .map(e -> {
                    GoodsAttributeDictionaryVO.DetailVO detailVO = new GoodsAttributeDictionaryVO.DetailVO();
                    BeanUtils.copyProperties(e,detailVO);

                    QueryWrapper<GoodsAttributeDictionaryItem> queryWrapper = new QueryWrapper<>();
                    queryWrapper.eq("attribute_id",e.getId());
                    List<GoodsAttributeDictionaryItem> itemList =attributeDictionaryItemRepository.list(queryWrapper);
                    if (ObjectUtils.isEmpty(itemList)){
                        throw new BusinessException("属性值数据异常!!");
                    }
                    List<GoodsAttributeDictionaryItemVO.ListVO> listVOS = ListUtil.listCover(GoodsAttributeDictionaryItemVO.ListVO.class,itemList);

                    detailVO.setList(listVOS);
                    return detailVO;
                }).collect(Collectors.toList());
        return detailVOS;
    }

    private GoodsAttributeDictionaryItemVO.ListVO ConverToItemlVO(GoodsAttributeDictionaryItem e) {
        GoodsAttributeDictionaryItemVO.ListVO listVO = new GoodsAttributeDictionaryItemVO.ListVO();
        BeanUtils.copyProperties(e,listVO);
        return listVO;
    }

    private GoodsAttributeDictionaryVO.DetailVO ConverToDetailVO(GoodsAttributeDictionary e) {
        GoodsAttributeDictionaryVO.DetailVO detailVO = new GoodsAttributeDictionaryVO.DetailVO();
        BeanUtils.copyProperties(e,detailVO);
        return detailVO;
    }

    private GoodsAttributeDictionaryVO.ListVO coverToVO(GoodsAttributeDictionary e) {
        GoodsAttributeDictionaryVO.ListVO listVO = new GoodsAttributeDictionaryVO.ListVO();
        BeanUtils.copyProperties(e,listVO);
        return listVO;
    }

    private void checkData(GoodsAttributeDictionaryDTO.ETO dto ){
        if (ObjectUtils.isEmpty(dto)){
            throw new BusinessException("参数为空，异常！！！");
        }
        if (StringUtils.isBlank(dto.getName())){
            throw new BusinessException("属性名称不能为空！");
        }
        if (ObjectUtils.isEmpty(dto.getList())){
            throw new BusinessException("至少添加一组属性值");
        }
        if (ObjectUtils.isNotEmpty(dto.getList())){
            for (GoodsAttributeDictionaryItemDTO.ETO eto : dto.getList()){
                if (StringUtils.isBlank(eto.getAttributeValue())){
                    throw new BusinessException("属性值不能为空！！");
                }
            }
        }
    }
}
