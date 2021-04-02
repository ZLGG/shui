package com.gs.lshly.biz.support.commodity.service.platadmin.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.gs.lshly.biz.support.commodity.entity.GoodsLabel;
import com.gs.lshly.biz.support.commodity.entity.GoodsMaterialLibrary;
import com.gs.lshly.biz.support.commodity.entity.GoodsMaterialLibraryImg;
import com.gs.lshly.biz.support.commodity.mapper.GoodsCategoryMapper;
import com.gs.lshly.biz.support.commodity.mapper.GoodsMaterialLibraryMapper;
import com.gs.lshly.biz.support.commodity.mapper.view.GoodsMaterialLibraryView;
import com.gs.lshly.biz.support.commodity.repository.IGoodsMaterialLibraryImgRepository;
import com.gs.lshly.biz.support.commodity.repository.IGoodsMaterialLibraryRepository;
import com.gs.lshly.biz.support.commodity.service.platadmin.IGoodsMaterialLibraryService;
import com.gs.lshly.common.exception.BusinessException;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.BaseQTO;
import com.gs.lshly.common.struct.platadmin.commodity.dto.GoodsLabelDTO;
import com.gs.lshly.common.struct.platadmin.commodity.dto.GoodsMaterialLibraryDTO;
import com.gs.lshly.common.struct.platadmin.commodity.qto.GoodsMaterialLibraryQTO;
import com.gs.lshly.common.struct.platadmin.commodity.vo.GoodsCategoryVO;
import com.gs.lshly.common.struct.platadmin.commodity.vo.GoodsMaterialLibraryVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.gs.lshly.middleware.mybatisplus.MybatisPlusUtil;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.TreeSet;
import java.util.stream.Collectors;


/**
* <p>
*  服务实现类
* </p>
* @author Starry
* @since 2020-12-10
*/
@Component
public class GoodsMaterialLibraryServiceImpl implements IGoodsMaterialLibraryService {

    @Autowired
    private IGoodsMaterialLibraryRepository repository;
    @Autowired
    private GoodsMaterialLibraryMapper materialLibraryMapper;
    @Autowired
    private IGoodsMaterialLibraryImgRepository libraryImgRepository;
    @Autowired
    private GoodsCategoryMapper categoryMapper;

    @Override
    public PageData<GoodsMaterialLibraryVO.DetailListVO> pageData(GoodsMaterialLibraryQTO.QTO qto) {
        QueryWrapper<GoodsMaterialLibraryView> wrapper = MybatisPlusUtil.query();
        if (StringUtils.isNotBlank(qto.getBrandName())){
            wrapper.like("gb.brand_name",qto.getBrandName());
        }
        if (StringUtils.isNotBlank(qto.getGoodsName())){
            wrapper.like("gml.goods_name",qto.getGoodsName());
        }
        IPage<GoodsMaterialLibraryView> page = MybatisPlusUtil.pager(qto);
        IPage<GoodsMaterialLibraryView> materialLibraryViewIPage = materialLibraryMapper.pageData(page,wrapper);
        if (ObjectUtils.isEmpty(materialLibraryViewIPage) || ObjectUtils.isEmpty(materialLibraryViewIPage.getRecords())){
            return new PageData<>();
        }
        List<GoodsMaterialLibraryVO.DetailListVO> listVOS = materialLibraryViewIPage.getRecords()
                .stream().map(e ->{
                    GoodsMaterialLibraryVO.DetailListVO listVO = new GoodsMaterialLibraryVO.DetailListVO();
                    BeanUtils.copyProperties(e,listVO);
                    GoodsMaterialLibraryImg libraryImg = getInfo(e.getId());
                    listVO.setImageUrl(libraryImg.getImageUrl());
                    return listVO;
                }).collect(Collectors.toList());
        return new PageData<>(listVOS,qto.getPageNum(),qto.getPageSize(),materialLibraryViewIPage.getTotal());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addGoodsMaterialLibrary(GoodsMaterialLibraryDTO.ETO eto) {
        //校验数据
        checkData(eto);

        GoodsMaterialLibrary goodsMaterialLibrary = new GoodsMaterialLibrary();
        BeanUtils.copyProperties(eto, goodsMaterialLibrary);
        repository.save(goodsMaterialLibrary);

        saveImage(eto.getImageList(),goodsMaterialLibrary.getId());

    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteGoodsMaterialLibrary(GoodsMaterialLibraryDTO.IdListDTO dto) {
        QueryWrapper<GoodsMaterialLibraryImg> queryWrapper = MybatisPlusUtil.query();
        queryWrapper.in("material_library_id",dto.getIdList());
        libraryImgRepository.remove(queryWrapper);

        QueryWrapper<GoodsMaterialLibrary> wrapper = MybatisPlusUtil.query();
        wrapper.in("id",dto.getIdList());
        repository.remove(wrapper);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void editGoodsMaterialLibrary(GoodsMaterialLibraryDTO.ETO eto) {
        //校验数据
        checkData(eto);

        UpdateWrapper<GoodsMaterialLibrary> wrapper = MybatisPlusUtil.update();
        wrapper.eq("id",eto.getId());
        GoodsMaterialLibrary goodsMaterialLibrary = new GoodsMaterialLibrary();
        BeanUtils.copyProperties(eto, goodsMaterialLibrary);
        repository.update(goodsMaterialLibrary,wrapper);

        //删除图片
        QueryWrapper<GoodsMaterialLibraryImg> libraryImgQueryWrapper = MybatisPlusUtil.query();
        libraryImgQueryWrapper.eq("material_library_id",eto.getId());
        libraryImgRepository.remove(libraryImgQueryWrapper);

        saveImage(eto.getImageList(),eto.getId());

    }

    @Override
    public GoodsMaterialLibraryVO.DetailVO detailGoodsMaterialLibrary(GoodsMaterialLibraryDTO.IdDTO dto) {
        QueryWrapper<GoodsMaterialLibrary> wrapper = MybatisPlusUtil.query();
        wrapper.eq("id",dto.getId());
        GoodsMaterialLibrary goodsMaterialLibrary = repository.getOne(wrapper);
        GoodsMaterialLibraryVO.DetailVO detailVo = new GoodsMaterialLibraryVO.DetailVO();
        if(ObjectUtils.isEmpty(goodsMaterialLibrary)){
            throw new BusinessException("商品素材数据查询异常！！");
        }
        BeanUtils.copyProperties(goodsMaterialLibrary, detailVo);
        QueryWrapper<GoodsMaterialLibraryImg> queryWrapper = MybatisPlusUtil.query();
        queryWrapper.eq("material_library_id",dto.getId());
        List<GoodsMaterialLibraryImg> libraryImgs = libraryImgRepository.list(queryWrapper);
        if (ObjectUtils.isEmpty(libraryImgs)){
            throw new BusinessException("商品素材对应的图片数据异常");
        }
        List<String> imageList = libraryImgs.stream().map(GoodsMaterialLibraryImg::getImageUrl).collect(Collectors.toList());
        detailVo.setImageList(imageList);
        return detailVo;
    }

    @Override
    public List<GoodsMaterialLibraryVO.exportDataVO> exportData(GoodsMaterialLibraryDTO.IdListDTO dto) {
        if (null == dto || ObjectUtils.isEmpty(dto.getIdList())){
            throw new BusinessException("请选择要导出的模板！");
        }
        QueryWrapper<GoodsMaterialLibrary> wrapper = MybatisPlusUtil.query();
        wrapper.in("gml.id",dto.getIdList());
        List<GoodsMaterialLibraryVO.exportDataVO> exportDataVOS = materialLibraryMapper.getExportData(wrapper);
        if (ObjectUtils.isEmpty(exportDataVOS)){
            return new ArrayList<>();
        }
        exportDataVOS = exportDataVOS.parallelStream()
                .collect(Collectors.collectingAndThen(Collectors.toCollection(() -> new TreeSet<>(Comparator.comparing(f -> f.getId()))), ArrayList::new));
        for (GoodsMaterialLibraryVO.exportDataVO dataVO : exportDataVOS){
            GoodsCategoryVO.ParentCategoryVO parentCategoryVO = categoryMapper.selectParentCategoryVO(dataVO.getCategoryId());
            dataVO.setLevel1CategoryName(StringUtils.isNotBlank(parentCategoryVO.getLevName())?parentCategoryVO.getLevName():"");
            dataVO.setLevel2CategoryName(StringUtils.isNotBlank(parentCategoryVO.getLevName())?parentCategoryVO.getLev1Name():"");
            dataVO.setLevel3CategoryName(StringUtils.isNotBlank(parentCategoryVO.getLevName())?parentCategoryVO.getLev2Name():"");
        }
        return exportDataVOS;
    }


    private void checkData(GoodsMaterialLibraryDTO.ETO eto){
        if (StringUtils.isBlank(eto.getCategoryId())){
            throw new BusinessException("请选择类目");
        }
        if (StringUtils.isBlank(eto.getGoodsName())){
            throw new BusinessException("请填写商品名称");
        }
        if (StringUtils.isBlank(eto.getGoodsTitle())){
            throw new BusinessException("请填写商品");
        }
        if (StringUtils.isBlank(eto.getBrandId())){
            throw new BusinessException("请选择品牌");
        }
        if (ObjectUtils.isEmpty(eto.getImageList())){
            throw new BusinessException("请上传商品素材库图片！");
        }
        if (ObjectUtils.isEmpty(eto.getGoodsH5Desc())){
            throw new BusinessException("请填写商品移动端描述");
        }
        if (ObjectUtils.isEmpty(eto.getGoodsPcDesc())){
            throw new BusinessException("请填写商品电脑端描述");
        }
        if (filterSameName(eto)>0){
            throw new BusinessException("该商品名称"+eto.getGoodsName()+"的素材模板已存在");
        }
    }

    private void saveImage(List<String> imageList,String id){
        for (String img : imageList){
            GoodsMaterialLibraryImg libraryImg = new GoodsMaterialLibraryImg();
            libraryImg.setImageUrl(img);
            libraryImg.setMaterialLibraryId(id);
            libraryImgRepository.save(libraryImg);
        }
    }

    private int filterSameName(GoodsMaterialLibraryDTO.ETO eto){
        if (eto == null){
            throw new BusinessException("参数不能为空！");
        }
        QueryWrapper<GoodsMaterialLibrary> wrapper = MybatisPlusUtil.query();
        wrapper.eq("goods_name",eto.getGoodsName());
        int count = repository.count(wrapper);

        if (StringUtils.isNotBlank(eto.getId())){
            QueryWrapper<GoodsMaterialLibrary> boost = MybatisPlusUtil.query();
            boost.eq("id",eto.getId());
            GoodsMaterialLibrary library = repository.getOne(boost);
            if (library.getGoodsName().equals(eto.getGoodsName())){
                return 0;
            }else {
                return count;
            }
        }
        return count;
    }

    private GoodsMaterialLibraryImg getInfo(String id){
        QueryWrapper<GoodsMaterialLibraryImg> queryWrapper = MybatisPlusUtil.query();
        queryWrapper.eq("material_library_id",id);
        queryWrapper.last("limit 0,1");
        GoodsMaterialLibraryImg img = libraryImgRepository.getOne(queryWrapper);
        if (ObjectUtils.isEmpty(img)){
            throw new BusinessException("数据异常！！");
        }
        return img;
    }
}
