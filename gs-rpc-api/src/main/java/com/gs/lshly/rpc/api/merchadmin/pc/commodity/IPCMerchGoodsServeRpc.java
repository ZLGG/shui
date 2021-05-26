package com.gs.lshly.rpc.api.merchadmin.pc.commodity;

import com.gs.lshly.common.response.PageData;
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
public interface IPCMerchGoodsServeRpc {
    /**
     * 分页查询商品服务列表
     *
     * @return
     */
    PageData<GoodsServeVO.ListVO> pageGoodsServeData(GoodsServeQTO.QTO qto);

    /**
     * 根据商品id查询商品服务详情
     *
     * @param dto
     * @return
     */
    List<GoodsServeVO.ListVO> getGoodsServeDetailByGoodsId(GoodsServeDTO.IdDTO dto);
}
