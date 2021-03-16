package com.gs.lshly.rpc.api.platadmin.commodity;


import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.platadmin.commodity.dto.GoodsAttributeDictionaryDTO;
import com.gs.lshly.common.struct.platadmin.commodity.dto.GoodsCategoryDTO;
import com.gs.lshly.common.struct.platadmin.commodity.qto.GoodsAttributeDictionaryQTO;
import com.gs.lshly.common.struct.platadmin.commodity.vo.GoodsAttributeDictionaryVO;

import java.util.List;

/**
 * @Author Starry
 * @Date 14:44 2020/9/23
 */
public interface IGoodsAttributeDictionaryRpc {
    /**
     * 添加商品公共属性
     * @param dto
     *
     */
    void addGoodsAttributeDictionary(GoodsAttributeDictionaryDTO.ETO dto);

    /**
     * 查询该属性的信息
     * @param dto
     * @return
     */
    GoodsAttributeDictionaryVO.GetCategoryVO getGetCategoryVO(GoodsAttributeDictionaryDTO.IdDTO dto);

    /**
     * 删除商品公共属性
     * @param dto
     */
    void deleteAttribute(GoodsAttributeDictionaryDTO.IdDTO dto);

    /**
     * 批量删除属性
     * @param dto
     * @return
     */
    void deleteAttributeBatch(GoodsAttributeDictionaryDTO.IdListDTO dto);

    /**
     * 获取属性详情信息
     * @param dto
     * @return
     */
    GoodsAttributeDictionaryVO.DetailVO getAttributeDetails(GoodsAttributeDictionaryDTO.IdDTO dto);

    /**
     * 查询属性列表
     * @param qto
     * @return
     */
    PageData<GoodsAttributeDictionaryVO.ListVO> listAttribute(GoodsAttributeDictionaryQTO.QTO qto);

    /**
     * 修改属性信息
     * @param dto
     */
    void updateAttribute(GoodsAttributeDictionaryDTO.ETO dto);

    /**
     * 获取与类目关联的属性信息
     * @param dto
     * @return
     */
    List<GoodsAttributeDictionaryVO.ListVO> listAttributes(GoodsCategoryDTO.IdDTO dto);
    /**
     * 获取与类目关联的属性以及属性详情信息
     * @param dto
     * @return
     */
    List<GoodsAttributeDictionaryVO.DetailVO> listAttributeDetail(GoodsCategoryDTO.IdDTO dto);

    /**
     * 查询所有的属性以及属性值列表
     * @return
     */
    List<GoodsAttributeDictionaryVO.DetailVO> listAttributes();
}
