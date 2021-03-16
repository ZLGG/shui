package com.gs.lshly.rpc.api.platadmin.commodity;

import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.ExportDataDTO;
import com.gs.lshly.common.struct.merchadmin.pc.commodity.vo.PCMerchGoodsCategoryVO;
import com.gs.lshly.common.struct.platadmin.commodity.dto.GoodsAttributeDictionaryDTO;
import com.gs.lshly.common.struct.platadmin.commodity.dto.GoodsBrandDTO;
import com.gs.lshly.common.struct.platadmin.commodity.dto.GoodsCategoryDTO;
import com.gs.lshly.common.struct.platadmin.commodity.dto.GoodsSpecDictionaryDTO;
import com.gs.lshly.common.struct.platadmin.commodity.qto.GoodsCategoryQTO;
import com.gs.lshly.common.struct.platadmin.commodity.vo.GoodsCategoryVO;

import java.util.List;

/**
 * @Author Starry
 * @Date 15:22 2020/9/27
 */
public interface IGoodsCategoryRpc {

    /**
     * 添加分类
     * @param eto
     */
    void addCategory(GoodsCategoryDTO.ETO eto);

    /**
     * 查询该分类下的父分类的信息
     * @param dto
     * @return
     */
    GoodsCategoryVO.ParentCategoryVO getParentCategory(GoodsCategoryDTO.IdDTO dto);

    /**
     * 查询一级分类详情
     * @param dto
     * @return
     */
    GoodsCategoryVO.Level1VO selectLevel1VO(GoodsCategoryDTO.IdDTO dto);

    /**
     * 查询二级分类详情
     * @param dto
     * @return
     */
    GoodsCategoryVO.Level2VO selectLevel2VO(GoodsCategoryDTO.IdDTO dto);

    /**
     * 查询三级分类详情
     * @param dto
     * @return
     */
    GoodsCategoryVO.Level3VO selectLevel3VO(GoodsCategoryDTO.IdDTO dto);

    /**
     * 获取树形列表
     * @return
     */
    List<GoodsCategoryVO.CategoryTreeVO> selectCategoryTree();

    /**
     *修该分类信息
     * @param eto
     */
    void updateCategory(GoodsCategoryDTO.ETO eto);

    /**
     * 删除分类数据
     * @param dto
     */
    void deleteCategory(GoodsCategoryDTO.IdDTO dto);

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
    PageData<GoodsCategoryVO.Level3VO> selectLevel3(GoodsCategoryQTO.QTO qto);

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
     * @param idListDTO
     * @param dto
     */
    void bindBrand(GoodsBrandDTO.IdListDTO idListDTO, GoodsCategoryDTO.IdDTO dto);

    /**
     * 导出数据
     * @return
     * @throws Exception
     */
    ExportDataDTO export() throws Exception;

    /**
     * 查询该店铺所有的二级分类或者三级分类
     * @param dto
     * @return
     */
    List<GoodsCategoryVO.CategoryJoinSearchVO> listCategoryLevels(GoodsCategoryDTO.IdDTO dto);

    /**
     * 查询该店铺下的所有一级分类
     * @param dto
     * @return
     */
    List<GoodsCategoryVO.CategoryJoinSearchVO> listCategoryLevel1(GoodsCategoryDTO.IdListDTO dto);

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

    /**
     * 查询1级分类下的2-3级全量信息树型结构
     * @param idList
     * @return
     */
    List<GoodsCategoryVO.CategoryTreeVO> listCategoryTree(List<String> idList);

    void innerRightsCategorySettings(GoodsCategoryDTO.RightsSetting dto);

    /**
     * 根据类目id查询id+name
     * @param categoryIds
     * @return
     */
    List<GoodsCategoryVO.CategoryJoinSearchVO> innerGetIdAndName(List<String> categoryIds);

}
