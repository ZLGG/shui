package com.gs.lshly.biz.support.commodity.rpc.platadmin;

import com.gs.lshly.biz.support.commodity.service.platadmin.IGoodsQaService;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.platadmin.commodity.dto.GoodsQaDTO;
import com.gs.lshly.common.struct.platadmin.commodity.qto.GoodsQaQTO;
import com.gs.lshly.common.struct.platadmin.commodity.vo.GoodsQaVO;
import com.gs.lshly.rpc.api.platadmin.commodity.IGoodsQaRpc;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Author Starry
 * @Date 16:31 2020/10/17
 */
@DubboService
public class GoodsQaRpc implements IGoodsQaRpc {

    @Autowired
    private IGoodsQaService goodsQaService;

    @Override
    public PageData<GoodsQaVO.GoodsQaListVO> pageGoodsQa(GoodsQaQTO.QTO qto) {
        return goodsQaService.pageGoodsQa(qto);
    }

    @Override
    public GoodsQaVO.GoodsQaDetailVO getGoodsQaDetailVO(GoodsQaDTO.IdDTO dto) {
        return goodsQaService.getGoodsQaDetailVO(dto);
    }

    @Override
    public void deleteGoodsQa(GoodsQaDTO.IdDTO dto) {
        goodsQaService.deleteGoodsQa(dto);
    }

    @Override
    public void deleteGoodsQaReply(GoodsQaDTO.IdDTO dto) {
        goodsQaService.deleteGoodsQaReply(dto);
    }

    @Override
    public void IsShowGoodsQaContent(GoodsQaDTO.ShowContentETO eto) {
        goodsQaService.IsShowGoodsQaContent(eto);
    }
}
