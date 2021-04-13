package com.gs.lshly.biz.support.commodity.service.platadmin;
import com.gs.lshly.common.struct.platadmin.commodity.dto.GoodsLabelDTO;
import com.gs.lshly.common.struct.platadmin.commodity.dto.GoodsRelationLabelDTO;
import com.gs.lshly.common.struct.platadmin.commodity.vo.GoodsLabelVO;
import com.gs.lshly.common.struct.platadmin.commodity.vo.GoodsRelationLabelVO;

import java.util.List;

/**
 * @author Starry
 */
public interface IGoodsRelationLabelService {

    /**
     * 批量建立商品与商品标签的关联
     * @param etoList
     */
    void addGoodsRelationLabel(GoodsRelationLabelDTO.ETO etoList);

    /**
     * 根据商品id查询商品标签信息
     * @param dto
     * @return
     */
    List<GoodsLabelVO.ListVO> getGoodsLabel(GoodsRelationLabelDTO.GoodsIdDTO dto);

    /**
     * 根据标签id删除标签关联
     * @param dto
     */
    void deleteGoodsRelationLabel(GoodsRelationLabelDTO.LabelIdDTO dto);

    /**
     * 根据标签id获取关联的商品列表
     * @param dto
     * @return
     */
    List<GoodsRelationLabelVO.ListVO> getRelationLabel (GoodsLabelDTO.IdDTO dto);

    /**
     * 跟据商品获取标签
     * @param goodsId
     * @return
     */
    List<String> listGoodsLabelByGoodsId(String goodsId);

}