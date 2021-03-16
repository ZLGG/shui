package com.gs.lshly.biz.support.commodity.service.platadmin;

import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.platadmin.commodity.dto.GoodsAttributeDictionaryDTO;
import com.gs.lshly.common.struct.platadmin.commodity.dto.GoodsBrandDTO;
import com.gs.lshly.common.struct.platadmin.commodity.dto.GoodsCategoryDTO;
import com.gs.lshly.common.struct.platadmin.commodity.dto.GoodsSpecDictionaryDTO;
import com.gs.lshly.common.struct.platadmin.commodity.qto.GoodsCategoryQTO;
import com.gs.lshly.common.struct.platadmin.commodity.vo.GoodsCategoryVO;

import java.util.List;

/**
 * @Author Starry
 * @Date 15:51 2020/9/27
 */
public interface IGoodsCategoryService {

    /**
     * 添加分类
     * @param eto
     */
    void addCategory(GoodsCategoryDTO.ETO eto);

    /**
     *查询该类目下的父分类
     * @param dto
     * @return
     */
    GoodsCategoryVO.ParentCategoryVO findParentCategoryVO(GoodsCategoryDTO.IdDTO dto);

    /**
     * 获取一级分类详情信息
     * @param dto
     * @return
     */
    GoodsCategoryVO.Level1VO getLevel1Detail(GoodsCategoryDTO.IdDTO dto);

    /**
     * 获取二级分类详情信息
     * @param dto
     * @return
     */
    GoodsCategoryVO.Level2VO getLevel2Detail(GoodsCategoryDTO.IdDTO dto);

    /**
     * 获取三级分类详情信息
     * @param dto
     * @return
     */
    GoodsCategoryVO.Level3VO getLevel3Detail(GoodsCategoryDTO.IdDTO dto);

    /**
     * 查询分类列表
     * @return
     */
    List<GoodsCategoryVO.CategoryTreeVO> listCategory();

    /**
     * 修改分类数据
     * @param eto
     */
    void updateCategory(GoodsCategoryDTO.ETO eto);

    /**
     * 删除分类数据
     * @param dto
     */
    void deleteCategory(GoodsCategoryDTO.IdDTO dto);

    /**
     * 查询当前分类下子分类数量
     * @param dto
     * @return
     */
    int findChildrenCategory(GoodsCategoryDTO.IdDTO dto);

    /**
     * 编辑排序
     * @param idxETO
     *
     */
    void updateIdx(List<GoodsCategoryDTO.IdxETO> idxETO);

    /**
     * 设置使用范围
     * @param eto
     */
    void updateUseFile(GoodsCategoryDTO.UseFiledETO eto);

    /**
     * 查询三级分类信息
     * @param qto
     * @return
     */
    PageData<GoodsCategoryVO.Level3VO> findLevel3(GoodsCategoryQTO.QTO qto);

    /**
     * 关联规格
     * @param dto
     * @param idListDTO
     */
    void bindSpec(GoodsSpecDictionaryDTO.IdListDTO idListDTO, GoodsCategoryDTO.IdDTO dto);

    /**
     * 关联属性
     * @param dto
     * @param idListDTO
     */
    void bindAttribute(GoodsAttributeDictionaryDTO.IdListDTO idListDTO, GoodsCategoryDTO.IdDTO dto);


    /**
     * 关联品牌
     * @param dto
     * @param idListDTO
     */
    void bindBrand(GoodsBrandDTO.IdListDTO idListDTO, GoodsCategoryDTO.IdDTO dto);

    /**
     * 查询分类信息
     * @param dto
     * @return
     */
    GoodsCategoryVO.ListVO getGoodsCategory(GoodsCategoryDTO.IdDTO dto);

    /**
     * 查询该类目下是否关联了品牌
     * @param dto
     * @return
     */
    int countBindBrand(GoodsCategoryDTO.IdDTO dto);

    /**
     * 查询该类目下是否关联了参数
     * @param dto
     * @return
     */
    int countBindParams(GoodsCategoryDTO.IdDTO dto);

    /**
     * 查询该类目是否关联了属性
     * @param dto
     * @return
     */
    int countBindAttribute(GoodsCategoryDTO.IdDTO dto);

    /**
     * 查询该类目是否关联了规格
     * @param dto
     * @return
     */
    int countBindSpec(GoodsCategoryDTO.IdDTO dto);

    /**
     * 获取类目导出数据
     * @return
     */
    List<GoodsCategoryVO.CategoryExcelVO> listExcelVO();

    /**
     * 查询该店铺下的所有一级分类
     * @param dto
     * @return
     */
    List<GoodsCategoryVO.CategoryJoinSearchVO> listCategoryLevel1(GoodsCategoryDTO.IdListDTO dto);

    /**
     * 查询该店铺所有的二级分类或者三级分类
     * @param dto
     * @return
     */
    List<GoodsCategoryVO.CategoryJoinSearchVO> listCategoryLevels(GoodsCategoryDTO.IdDTO dto);


    /**
     * 根据商品类目id查询商品数据
     * @param dto
     * @return
     */
    GoodsCategoryVO.ListVO getListVOById(GoodsCategoryDTO.IdDTO dto);


    /**
     * 查询商品一级分类
     * @return
     */
    List<GoodsCategoryVO.ListVO> level1Categories();


    /**
     * 根据一级类目查询子分类信息
     * @param dto
     * @return
     */
    GoodsCategoryVO.CategoryTreeVO categoryTree(GoodsCategoryDTO.IdDTO dto);




    //---------------内部服务--------------


    List<GoodsCategoryVO.CategoryTreeVO> listCategoryTree(List<String> idList);

    void innerRightsCategorySettings(GoodsCategoryDTO.RightsSetting dto);

    /**
     * 根据类目id查询id+name
     * @param categoryIds
     * @return
     */
    List<GoodsCategoryVO.CategoryJoinSearchVO> innerGetIdAndName(List<String> categoryIds);
}
