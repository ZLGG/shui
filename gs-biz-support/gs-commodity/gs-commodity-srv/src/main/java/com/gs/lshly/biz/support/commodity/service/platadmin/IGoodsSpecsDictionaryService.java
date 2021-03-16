package com.gs.lshly.biz.support.commodity.service.platadmin;

import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.platadmin.commodity.dto.GoodsCategoryDTO;
import com.gs.lshly.common.struct.platadmin.commodity.dto.GoodsSpecDictionaryDTO;
import com.gs.lshly.common.struct.platadmin.commodity.qto.GoodsSpecDictionaryQTO;
import com.gs.lshly.common.struct.platadmin.commodity.vo.GoodsSpecDictionaryVO;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 
 * @since 2020-09-25
 */
public interface IGoodsSpecsDictionaryService {

   void addSpecInfo(GoodsSpecDictionaryDTO.ETO addSpecInfoETO);

   void deleteSpecInfo(GoodsSpecDictionaryDTO.IdDTO dto);

    /**
     * 批量删除规格
     * @param dto
     */
   void deleteSpecBatches(GoodsSpecDictionaryDTO.IdListDTO dto);

   GoodsSpecDictionaryVO.DetailVO getSpecDetails(GoodsSpecDictionaryDTO.IdDTO idDTO);

   void updateSpec(GoodsSpecDictionaryDTO.ETO et);

   GoodsSpecDictionaryVO.GetCategoryVO getSpecInfo(GoodsSpecDictionaryDTO.IdDTO dto);

   PageData<GoodsSpecDictionaryVO.ListVO> list(GoodsSpecDictionaryQTO.QTO qto);

   List<GoodsSpecDictionaryVO.ListVO> listSpec(GoodsCategoryDTO.IdDTO dto);
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
