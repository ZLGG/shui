package com.gs.lshly.biz.support.commodity.service.platadmin;

import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.platadmin.commodity.dto.GoodsAttributeDictionaryDTO;
import com.gs.lshly.common.struct.platadmin.commodity.dto.GoodsCategoryDTO;
import com.gs.lshly.common.struct.platadmin.commodity.qto.GoodsAttributeDictionaryQTO;
import com.gs.lshly.common.struct.platadmin.commodity.vo.GoodsAttributeDictionaryVO;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 
 * @since 2020-09-23
 */
public interface IGoodsAttributeDictionaryService {

    void addAttribute(GoodsAttributeDictionaryDTO.ETO dto);

    /**
     * 删除属性
     * @param dto
     */
    void deleteAttribute(GoodsAttributeDictionaryDTO.IdDTO dto);

    /**
     * 批量删除属性
     * @param dto
     * @return
     */
    void deleteAttributeBatch(GoodsAttributeDictionaryDTO.IdListDTO dto);

    GoodsAttributeDictionaryVO.GetCategoryVO getGoodsAttributeDictionary(GoodsAttributeDictionaryDTO.IdDTO dto);

    GoodsAttributeDictionaryVO.DetailVO getAttributeDetails(GoodsAttributeDictionaryDTO.IdDTO dto);

    void updateAttribute(GoodsAttributeDictionaryDTO.ETO dto);

    PageData<GoodsAttributeDictionaryVO.ListVO> list(GoodsAttributeDictionaryQTO.QTO qto);
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
