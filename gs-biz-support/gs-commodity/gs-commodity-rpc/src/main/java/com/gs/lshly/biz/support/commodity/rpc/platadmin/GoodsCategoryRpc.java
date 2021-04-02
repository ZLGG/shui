package com.gs.lshly.biz.support.commodity.rpc.platadmin;

import com.gs.lshly.biz.support.commodity.service.platadmin.IGoodsCategoryService;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.common.struct.ExportDataDTO;
import com.gs.lshly.common.struct.platadmin.commodity.dto.GoodsAttributeDictionaryDTO;
import com.gs.lshly.common.struct.platadmin.commodity.dto.GoodsBrandDTO;
import com.gs.lshly.common.struct.platadmin.commodity.dto.GoodsCategoryDTO;
import com.gs.lshly.common.struct.platadmin.commodity.dto.GoodsSpecDictionaryDTO;
import com.gs.lshly.common.struct.platadmin.commodity.qto.GoodsCategoryQTO;
import com.gs.lshly.common.struct.platadmin.commodity.vo.GoodsCategoryVO;
import com.gs.lshly.common.utils.ExcelUtil;
import com.gs.lshly.rpc.api.platadmin.commodity.IGoodsCategoryRpc;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @Author Starry
 * @Date 15:21 2020/9/27
 */
@DubboService
public class GoodsCategoryRpc implements IGoodsCategoryRpc {

    @Autowired
    private IGoodsCategoryService categoryService;

    @Override
    public void addCategory(GoodsCategoryDTO.ETO eto) {
        categoryService.addCategory(eto);
    }

    @Override
    public GoodsCategoryVO.ParentCategoryVO getParentCategory(GoodsCategoryDTO.IdDTO dto) {
        return categoryService.findParentCategoryVO(dto);
    }

    @Override
    public GoodsCategoryVO.Level1VO selectLevel1VO(GoodsCategoryDTO.IdDTO dto) {
        return categoryService.getLevel1Detail(dto);
    }

    @Override
    public GoodsCategoryVO.Level2VO selectLevel2VO(GoodsCategoryDTO.IdDTO dto) {
        return categoryService.getLevel2Detail(dto);
    }

    @Override
    public GoodsCategoryVO.Level3VO selectLevel3VO(GoodsCategoryDTO.IdDTO dto) {
        return categoryService.getLevel3Detail(dto);
    }

    @Override
    public List<GoodsCategoryVO.CategoryTreeVO> selectCategoryTree() {
        return categoryService.listCategory();
    }

    @Override
    public void updateCategory(GoodsCategoryDTO.ETO eto) {
        categoryService.updateCategory(eto);
    }

    @Override
    public void deleteCategory(GoodsCategoryDTO.IdDTO dto) {
        categoryService.deleteCategory(dto);
    }

    @Override
    public void updateIdx(List<GoodsCategoryDTO.IdxETO> idxETO) {
        categoryService.updateIdx(idxETO);
    }

    @Override
    public void updateUseFile(GoodsCategoryDTO.UseFiledETO eto) {
        categoryService.updateUseFile(eto);
    }

    @Override
    public PageData<GoodsCategoryVO.Level3VO> selectLevel3(GoodsCategoryQTO.QTO qto) {
        return categoryService.findLevel3(qto);
    }

    @Override
    public void bindSpec(GoodsSpecDictionaryDTO.IdListDTO idListDTO, GoodsCategoryDTO.IdDTO dto) {
        categoryService.bindSpec(idListDTO, dto);
    }

    @Override
    public void bindAttribute(GoodsAttributeDictionaryDTO.IdListDTO idListDTO, GoodsCategoryDTO.IdDTO dto) {
        categoryService.bindAttribute(idListDTO, dto);
    }


    @Override
    public void bindBrand(GoodsBrandDTO.IdListDTO idListDTO, GoodsCategoryDTO.IdDTO dto) {
        categoryService.bindBrand(idListDTO, dto);
    }

    @Override
    public ExportDataDTO export() throws Exception {
        return ExcelUtil.treatmentBean(categoryService.listExcelVO(), GoodsCategoryVO.CategoryExcelVO.class);
    }

    @Override
    public List<GoodsCategoryVO.CategoryJoinSearchVO> listCategoryLevels(GoodsCategoryDTO.IdDTO dto) {
        return categoryService.listCategoryLevels(dto);
    }

    @Override
    public List<GoodsCategoryVO.CategoryJoinSearchVO> listCategoryLevel1(GoodsCategoryDTO.IdListDTO dto) {
        return categoryService.listCategoryLevel1(dto);
    }

    @Override
    public List<GoodsCategoryVO.ListVO> level1Categories() {
        return categoryService.level1Categories();
    }

    @Override
    public List<GoodsCategoryVO.ListVO> level2Categories(GoodsCategoryDTO.ApplyIdDTO dto) {
        return categoryService.level2Categories(dto);
    }

    @Override
    public GoodsCategoryVO.CategoryTreeVO categoryTree(GoodsCategoryDTO.IdDTO dto) {
        return categoryService.categoryTree(dto);
    }

    @Override
    public List<GoodsCategoryVO.CategoryTreeVO> listCategoryTree(List<String> idList) {
        return categoryService.listCategoryTree(idList);
    }

    @Override
    public void innerRightsCategorySettings(GoodsCategoryDTO.RightsSetting dto) {
        categoryService.innerRightsCategorySettings(dto);
    }

    @Override
    public List<GoodsCategoryVO.CategoryJoinSearchVO> innerGetIdAndName(List<String> categoryIds) {
        return categoryService.innerGetIdAndName(categoryIds);
    }

    @Override
    public List<GoodsCategoryVO.InnerListVO> innerCategoryList(BaseDTO dto) {
        return categoryService.innerCategoryList(dto);
    }
}
