package com.gs.lshly.rpc.api.bbc.commodity;

import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.bbc.commodity.qto.BbcGoodsServeQTO;
import com.gs.lshly.common.struct.bbc.commodity.vo.BbcGoodsServeVO;
import com.gs.lshly.common.struct.platadmin.commodity.dto.GoodsServeDTO;
import com.gs.lshly.common.struct.platadmin.commodity.qto.GoodsServeQTO;
import com.gs.lshly.common.struct.platadmin.commodity.vo.GoodsServeVO;
import org.apache.dubbo.config.annotation.DubboService;

import java.util.List;

/**
 * @author hanly
 * @since 2021-05-25
 */
@DubboService
public interface IBbcGoodsServeRpc {

    /**
     * 根据商品id查询商品服务详情
     *
     * @param dto
     * @return
     */
    List<BbcGoodsServeVO.ListVO> getGoodsServeDetailByGoodsId(BbcGoodsServeQTO.GoodsInfoQTO qto);
}
