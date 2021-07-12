package com.gs.lshly.biz.support.commodity.service.platadmin.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.gs.lshly.biz.support.commodity.entity.GoodsCategorySpec;
import com.gs.lshly.biz.support.commodity.entity.GoodsSpecDictionaryItem;
import com.gs.lshly.biz.support.commodity.entity.GoodsSpecsDictionary;
import com.gs.lshly.biz.support.commodity.repository.IGoodsCategorySpecRepository;
import com.gs.lshly.biz.support.commodity.repository.IGoodsSpecDictionaryItemRepository;
import com.gs.lshly.biz.support.commodity.repository.IGoodsSpecsDictionaryRepository;
import com.gs.lshly.biz.support.commodity.service.platadmin.IGoodsSpecsDictionaryService;
import com.gs.lshly.common.exception.BusinessException;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.platadmin.commodity.dto.GoodsCategoryDTO;
import com.gs.lshly.common.struct.platadmin.commodity.dto.GoodsSpecDictionaryDTO;
import com.gs.lshly.common.struct.platadmin.commodity.dto.GoodsSpecDictionaryItemDTO;
import com.gs.lshly.common.struct.platadmin.commodity.qto.GoodsSpecDictionaryQTO;
import com.gs.lshly.common.struct.platadmin.commodity.vo.GoodsSpecDictionaryItemVO;
import com.gs.lshly.common.struct.platadmin.commodity.vo.GoodsSpecDictionaryVO;
import com.gs.lshly.middleware.mybatisplus.MybatisPlusUtil;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author
 * @since 2020-09-25
 */
@Service
public class GoodsSpecsDictionaryServiceImpl implements IGoodsSpecsDictionaryService {

    @Autowired
    private IGoodsSpecsDictionaryRepository repository;

    @Autowired
    private IGoodsSpecDictionaryItemRepository dictionaryItemRepository;

    @Autowired
    private IGoodsCategorySpecRepository categorySpecRepository;

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public void addSpecInfo(GoodsSpecDictionaryDTO.ETO addSpecInfoETO) {
        //校验数据
        checkData(addSpecInfoETO);

        GoodsSpecsDictionary goodsSpecsDictionary = new GoodsSpecsDictionary();
        BeanUtils.copyProperties(addSpecInfoETO, goodsSpecsDictionary);
        repository.save(goodsSpecsDictionary);

        //添加相应的规格值
        List<GoodsSpecDictionaryItemDTO.ETO> etoList = addSpecInfoETO.getList();
        if (etoList.size() > 0 && etoList != null) {
            for (GoodsSpecDictionaryItemDTO.ETO eto : etoList) {
                GoodsSpecDictionaryItem goodsSpecDictionaryItem = new GoodsSpecDictionaryItem();
                BeanUtils.copyProperties(eto, goodsSpecDictionaryItem);
                goodsSpecDictionaryItem.setSpecId(goodsSpecsDictionary.getId());
                dictionaryItemRepository.save(goodsSpecDictionaryItem);
            }
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteSpecInfo(GoodsSpecDictionaryDTO.IdDTO dto) {
        GoodsSpecsDictionary specsDictionary = repository.getById(dto.getId());
        QueryWrapper<GoodsCategorySpec> boost = MybatisPlusUtil.query();
        boost.eq("spec_id", specsDictionary.getId());
        List<GoodsCategorySpec> categorySpecs = categorySpecRepository.list(boost);
        if (ObjectUtils.isNotEmpty(categorySpecs)) {
            throw new BusinessException("该规格已经关联了类目不可以直接删除！");
        }
        //删除该规格下的规格值
        QueryWrapper<GoodsSpecDictionaryItem> itemQueryWrapper = new QueryWrapper<>();
        itemQueryWrapper.eq("spec_id", dto.getId());
        dictionaryItemRepository.remove(itemQueryWrapper);

        //删除规格
        QueryWrapper<GoodsSpecsDictionary> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", dto.getId());
        repository.remove(queryWrapper);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteSpecBatches(GoodsSpecDictionaryDTO.IdListDTO dto) {
        if (dto == null) {
            throw new BusinessException("参数不能为空！");
        }
        for (String id : dto.getIdList()) {
            deleteSpecInfo(new GoodsSpecDictionaryDTO.IdDTO(id));
        }
    }

    @Override
    public GoodsSpecDictionaryVO.DetailVO getSpecDetails(GoodsSpecDictionaryDTO.IdDTO idDTO) {
        //查询规格
        GoodsSpecsDictionary goodsSpecsDictionary = repository.getById(idDTO.getId());
        GoodsSpecDictionaryVO.DetailVO detailVO = new GoodsSpecDictionaryVO.DetailVO();
        if (goodsSpecsDictionary != null) {
            BeanUtils.copyProperties(goodsSpecsDictionary, detailVO);
        }
        return detailVO;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateSpec(GoodsSpecDictionaryDTO.ETO eto) {
        //校验数据
        checkData(eto);

        GoodsSpecsDictionary dictionary = new GoodsSpecsDictionary();
        BeanUtils.copyProperties(eto, dictionary);
        repository.updateById(dictionary);
        /**
         * 先进行清除该规格下所有的规格值，在进行重新添加
         */
        QueryWrapper<GoodsSpecDictionaryItem> itemQueryWrapper = new QueryWrapper<>();
        itemQueryWrapper.eq("spec_id", eto.getId());
        dictionaryItemRepository.remove(itemQueryWrapper);

        for (GoodsSpecDictionaryItemDTO.ETO specItem : eto.getList()) {
            GoodsSpecDictionaryItem dictionaryItem = new GoodsSpecDictionaryItem();
            BeanUtils.copyProperties(specItem, dictionaryItem);
            dictionaryItem.setSpecId(dictionary.getId());
            dictionaryItemRepository.save(dictionaryItem);
        }
    }

    @Override
    public GoodsSpecDictionaryVO.GetCategoryVO getSpecInfo(GoodsSpecDictionaryDTO.IdDTO dto) {
        QueryWrapper<GoodsSpecsDictionary> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", dto.getId());
        GoodsSpecsDictionary dictionary = repository.getOne(queryWrapper);
        GoodsSpecDictionaryVO.GetCategoryVO getCategoryVO = new GoodsSpecDictionaryVO.GetCategoryVO();
        BeanUtils.copyProperties(dictionary, getCategoryVO);

        //根据规格id查询与他相关联的类目id
        QueryWrapper<GoodsCategorySpec> wrapperBoost = MybatisPlusUtil.query();
        wrapperBoost.eq("spec_id", dictionary.getId());
        GoodsCategorySpec categorySpec = categorySpecRepository.getOne(wrapperBoost);
        getCategoryVO.setCategoryId(StringUtils.isEmpty(categorySpec.getCategoryId()) ? "" : categorySpec.getCategoryId());
        return getCategoryVO;
    }

    @Override
    public PageData<GoodsSpecDictionaryVO.ListVO> list(GoodsSpecDictionaryQTO.QTO qto) {
        QueryWrapper<GoodsSpecsDictionary> boost = MybatisPlusUtil.query();
        boost.orderByAsc("idx", "id");
        IPage<GoodsSpecsDictionary> page = MybatisPlusUtil.pager(qto);
        repository.page(page, boost);
        return MybatisPlusUtil.toPageData(qto, GoodsSpecDictionaryVO.ListVO.class, page);
    }

    @Override
    public List<GoodsSpecDictionaryVO.ListVO> listSpec(GoodsCategoryDTO.IdDTO dto) {
        QueryWrapper<GoodsSpecsDictionary> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("category_id", dto.getId());

        List<GoodsSpecsDictionary> specsDictionaries = repository.list(queryWrapper);
        if (ObjectUtils.isEmpty(specsDictionaries)) {
            return new ArrayList<>();
        }
        List<GoodsSpecDictionaryVO.ListVO> listVOS = specsDictionaries
                .stream()
                .map(e -> coverToVO(e)).collect(Collectors.toList());
        return listVOS;
    }

    @Override
    public List<GoodsSpecDictionaryVO.DetailVO> listSpecDetail(GoodsCategoryDTO.IdDTO dto) {
        if (dto == null) {
            throw new BusinessException("参数不能为空！");
        }
        QueryWrapper<GoodsCategorySpec> wrapper = MybatisPlusUtil.query();
        wrapper.eq("category_id", dto.getId());

        List<GoodsCategorySpec> categorySpecs = categorySpecRepository.list(wrapper);
        if (ObjectUtils.isEmpty(categorySpecs)) {
            return new ArrayList<>();
        }
        //根据类目属性关系数据查询属性列表
        List<GoodsSpecsDictionary> specsDictionaries = new ArrayList<>();
        for (GoodsCategorySpec categorySpec : categorySpecs) {
            QueryWrapper<GoodsSpecsDictionary> queryWrapperBoost = MybatisPlusUtil.query();
            queryWrapperBoost.eq("id", categorySpec.getSpecId());
            specsDictionaries.add(repository.getOne(queryWrapperBoost));
        }
        List<GoodsSpecDictionaryVO.DetailVO> detailVOS = specsDictionaries
                .stream()
                .map(e -> ConverToDetailVO(e)).collect(Collectors.toList());

        //查询规格下的规格值
        for (GoodsSpecDictionaryVO.DetailVO spec : detailVOS) {

            QueryWrapper<GoodsSpecDictionaryItem> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("spec_id", spec.getId());

            if (dictionaryItemRepository.list(queryWrapper) != null && dictionaryItemRepository.list(queryWrapper).size() > 0) {
                List<GoodsSpecDictionaryItemVO.ListVO> listVOS = dictionaryItemRepository.list(queryWrapper).stream()
                        .map(e -> ConverToItemlVO(e))
                        .collect(Collectors.toList());
                spec.setList(listVOS);
            }
        }

        return detailVOS;
    }

    @Override
    public List<GoodsSpecDictionaryVO.DetailVO> listSpecInfos() {
        List<GoodsSpecsDictionary> specsDictionaries = repository.list();
        if (ObjectUtils.isEmpty(specsDictionaries)) {
            return new ArrayList<>();
        }
        List<GoodsSpecDictionaryVO.DetailVO> detailVOS = specsDictionaries
                .stream()
                .map(e -> ConverToDetailVO(e)).collect(Collectors.toList());
        for (int i = 0; i < detailVOS.size(); i++) {
            //查询规格下的规格值
            for (GoodsSpecDictionaryVO.DetailVO spec : detailVOS) {

                QueryWrapper<GoodsSpecDictionaryItem> queryWrapper = new QueryWrapper<>();
                queryWrapper.eq("spec_id", spec.getId());

                if (dictionaryItemRepository.list(queryWrapper) != null && dictionaryItemRepository.list(queryWrapper).size() > 0) {
                    List<GoodsSpecDictionaryItemVO.ListVO> listVOS = dictionaryItemRepository.list(queryWrapper).stream()
                            .map(e -> ConverToItemlVO(e))
                            .collect(Collectors.toList());
                    detailVOS.get(i).setList(listVOS);
                }
            }
        }
        return detailVOS;
    }

    private GoodsSpecDictionaryItemVO.ListVO ConverToItemlVO(GoodsSpecDictionaryItem e) {
        GoodsSpecDictionaryItemVO.ListVO listVO = new GoodsSpecDictionaryItemVO.ListVO();
        BeanUtils.copyProperties(e, listVO);
        return listVO;
    }

    private GoodsSpecDictionaryVO.DetailVO ConverToDetailVO(GoodsSpecsDictionary e) {
        GoodsSpecDictionaryVO.DetailVO detailVO = new GoodsSpecDictionaryVO.DetailVO();
        BeanUtils.copyProperties(e, detailVO);
        return detailVO;
    }

    private GoodsSpecDictionaryVO.ListVO coverToVO(GoodsSpecsDictionary e) {
        GoodsSpecDictionaryVO.ListVO listVO = new GoodsSpecDictionaryVO.ListVO();
        BeanUtils.copyProperties(e, listVO);
        return listVO;
    }

    private void checkData(GoodsSpecDictionaryDTO.ETO addSpecInfoETO) {
        if (ObjectUtils.isEmpty(addSpecInfoETO)) {
            throw new BusinessException("参数不能为空，异常！！！");
        }
        if (StringUtils.isBlank(addSpecInfoETO.getName())) {
            throw new BusinessException("请填写规格名称！！");
        }
        if (ObjectUtils.isEmpty(addSpecInfoETO.getList())) {
            throw new BusinessException("每组规格至少添加一组规格值！！");
        }
        if (ObjectUtils.isNotEmpty(addSpecInfoETO.getList())) {
            for (GoodsSpecDictionaryItemDTO.ETO eto : addSpecInfoETO.getList()) {
                if (StringUtils.isBlank(eto.getSpecValue())) {
                    throw new BusinessException("请填写规格值！！");
                }
            }
        }
        if (filterSameName(addSpecInfoETO) > 0) {
            throw new BusinessException(addSpecInfoETO.getName() + "规格名称已存在！！");
        }
    }

    private int filterSameName(GoodsSpecDictionaryDTO.ETO eto) {
        QueryWrapper<GoodsSpecsDictionary> wrapper = MybatisPlusUtil.query();
        wrapper.eq("name", eto.getName());
        int count = repository.count(wrapper);

        if (StringUtils.isNotEmpty(eto.getId())) {
            QueryWrapper<GoodsSpecsDictionary> boost = MybatisPlusUtil.query();
            boost.eq("id", eto.getId());
            GoodsSpecsDictionary specsDictionary = repository.getOne(boost);
            if (specsDictionary.getName().equals(eto.getName())) {
                return 0;
            } else {
                return count;
            }
        }
        return count;
    }
}
