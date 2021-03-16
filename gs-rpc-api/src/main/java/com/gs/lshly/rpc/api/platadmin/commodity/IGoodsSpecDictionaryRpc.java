package com.gs.lshly.rpc.api.platadmin.commodity;

import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.platadmin.commodity.dto.GoodsCategoryDTO;
import com.gs.lshly.common.struct.platadmin.commodity.dto.GoodsSpecDictionaryDTO;
import com.gs.lshly.common.struct.platadmin.commodity.qto.GoodsSpecDictionaryQTO;
import com.gs.lshly.common.struct.platadmin.commodity.vo.GoodsAttributeDictionaryVO;
import com.gs.lshly.common.struct.platadmin.commodity.vo.GoodsSpecDictionaryVO;

import java.util.List;

/**
 * @Author Starry
 * @Date 12:02 2020/9/25
 */
public interface IGoodsSpecDictionaryRpc {
    /**
     * 添加商品规格
     * @param addSpecInfoETO
     */
    void addSpec(GoodsSpecDictionaryDTO.ETO addSpecInfoETO);

    /**
     * 删除商品规格
     * @param idDTO
     */
    void  deleteSpec(GoodsSpecDictionaryDTO.IdDTO idDTO);

    /**
     * 批量删除规格
     * @param dto
     */
    void deleteSpecBatches(GoodsSpecDictionaryDTO.IdListDTO dto);

    /**
     * 查询商品规格详情信息
     * @param idDTO
     * @return
     */
    GoodsSpecDictionaryVO.DetailVO getSpecDetails(GoodsSpecDictionaryDTO.IdDTO idDTO);

    /**
     * 修改商品规格信息
     * @param dto
     */
    void updateSpec(GoodsSpecDictionaryDTO.ETO dto);

    /**
     * 查询规格信息
     * @param idDTO
     * @return
     */
    GoodsSpecDictionaryVO.GetCategoryVO getSpecInfo(GoodsSpecDictionaryDTO.IdDTO idDTO);

    /**
     * 查询商品规格列表
     * @param qto
     * @return
     */
    PageData<GoodsSpecDictionaryVO.ListVO> listSpec(GoodsSpecDictionaryQTO.QTO qto);

    /**
     * 获取与类目关联的规格信息
     * @param dto
     * @return
     */
    List<GoodsSpecDictionaryVO.ListVO> listSpecInfo(GoodsCategoryDTO.IdDTO dto);
    /**
     * 获取与类目关联的属性以及属性详情信息
     * @param dto
     * @return
     */
    List<GoodsSpecDictionaryVO.DetailVO> listSpecDetail(GoodsCategoryDTO.IdDTO dto);


    /**
     * 获取规格与规格详情列表
     * @return
     */
    List<GoodsSpecDictionaryVO.DetailVO> listSpecInfos();
}
