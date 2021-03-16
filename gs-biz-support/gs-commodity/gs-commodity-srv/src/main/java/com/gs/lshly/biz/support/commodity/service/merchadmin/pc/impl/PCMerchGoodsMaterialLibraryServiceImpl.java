package com.gs.lshly.biz.support.commodity.service.merchadmin.pc.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.gs.lshly.biz.support.commodity.entity.GoodsCategory;
import com.gs.lshly.biz.support.commodity.entity.GoodsMaterialLibrary;
import com.gs.lshly.biz.support.commodity.entity.GoodsMaterialLibraryImg;
import com.gs.lshly.biz.support.commodity.mapper.GoodsMaterialLibraryMapper;
import com.gs.lshly.biz.support.commodity.mapper.view.GoodsMaterialLibraryView;
import com.gs.lshly.biz.support.commodity.repository.IGoodsCategoryRepository;
import com.gs.lshly.biz.support.commodity.repository.IGoodsMaterialLibraryImgRepository;
import com.gs.lshly.biz.support.commodity.repository.IGoodsMaterialLibraryRepository;
import com.gs.lshly.biz.support.commodity.service.merchadmin.pc.IPCMerchGoodsMaterialLibraryService;
import com.gs.lshly.common.enums.GoodsCategoryLevelEnum;
import com.gs.lshly.common.enums.GoodsUsePlatformEnums;
import com.gs.lshly.common.exception.BusinessException;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.merchadmin.pc.commodity.dto.PCMerchGoodsCategoryDTO;
import com.gs.lshly.common.struct.merchadmin.pc.commodity.dto.PCMerchGoodsMaterialLibraryDTO;
import com.gs.lshly.common.struct.merchadmin.pc.commodity.qto.PCMerchGoodsMaterialLibraryQTO;
import com.gs.lshly.common.struct.merchadmin.pc.commodity.vo.PCMerchGoodsCategoryVO;
import com.gs.lshly.common.struct.merchadmin.pc.commodity.vo.PCMerchGoodsMaterialLibraryVO;
import com.gs.lshly.common.utils.ListUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.gs.lshly.middleware.mybatisplus.MybatisPlusUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


/**
* <p>
*  服务实现类
* </p>
* @author Starry
* @since 2020-12-10
*/
@Component
public class PCMerchGoodsMaterialLibraryServiceImpl implements IPCMerchGoodsMaterialLibraryService {

    @Autowired
    private GoodsMaterialLibraryMapper libraryMapper;
    @Autowired
    private IGoodsMaterialLibraryImgRepository libraryImgRepository;



    @Override
    public List<PCMerchGoodsMaterialLibraryVO.DetailVO> detailGoodsMaterialLibrary(PCMerchGoodsMaterialLibraryQTO.QTO qto) {
        QueryWrapper<GoodsMaterialLibraryView> wrapper = new QueryWrapper<>();
        wrapper.eq("gml.category_id",qto.getCategoryId());
        List<GoodsMaterialLibraryView> libraryViews = libraryMapper.listData(wrapper);
        if (ObjectUtils.isEmpty(libraryViews)){
           return new ArrayList<>();
        }
        List<PCMerchGoodsMaterialLibraryVO.DetailVO> detailVOS = libraryViews
                .parallelStream().map(e ->{
                    PCMerchGoodsMaterialLibraryVO.DetailVO detailVO = new PCMerchGoodsMaterialLibraryVO.DetailVO();
                    BeanUtils.copyProperties(e,detailVO);
                    QueryWrapper<GoodsMaterialLibraryImg> queryWrapper = MybatisPlusUtil.query();
                    queryWrapper.eq("material_library_id",e.getId());
                    List<GoodsMaterialLibraryImg> imgList = libraryImgRepository.list(queryWrapper);
                    if (ObjectUtils.isEmpty(imgList)){
                        throw new BusinessException("数据异常！！");
                    }
                    List<String> images = imgList.stream().map(GoodsMaterialLibraryImg::getImageUrl).collect(Collectors.toList());
                    detailVO.setImageList(images);
                    return detailVO;
                }).collect(Collectors.toList());
        return detailVOS;
    }

}
