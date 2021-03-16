package com.gs.lshly.rpc.api.platadmin.commodity;

import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.platadmin.commodity.dto.GoodsQaDTO;
import com.gs.lshly.common.struct.platadmin.commodity.qto.GoodsQaQTO;
import com.gs.lshly.common.struct.platadmin.commodity.vo.GoodsQaVO;

/**
 * @Author Starry
 * @Date 16:36 2020/10/17
 */
public interface IGoodsQaRpc {
    /**
     * 多条件分页查询所有商品咨询
     * @param qto
     * @return
     */
    PageData<GoodsQaVO.GoodsQaListVO> pageGoodsQa(GoodsQaQTO.QTO qto);

    /**
     * 查询商品问答的详情
     * @param dto
     * @return
     */
    GoodsQaVO.GoodsQaDetailVO getGoodsQaDetailVO(GoodsQaDTO.IdDTO dto);

    /**
     * 删除咨询内容
     * @param dto
     */
    void deleteGoodsQa(GoodsQaDTO.IdDTO dto);

    /**
     * 删除商家回复内容
     * @param dto
     */
    void deleteGoodsQaReply(GoodsQaDTO.IdDTO dto);

    /**
     * 是否将咨询显示在商品详情页
     * @param eto
     */
    void IsShowGoodsQaContent(GoodsQaDTO.ShowContentETO eto);
}
