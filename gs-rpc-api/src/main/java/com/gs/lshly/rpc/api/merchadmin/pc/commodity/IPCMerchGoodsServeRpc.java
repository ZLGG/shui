package com.gs.lshly.rpc.api.merchadmin.pc.commodity;

import java.util.List;

import org.apache.dubbo.config.annotation.DubboService;

import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.merchadmin.pc.commodity.dto.PCMerchGoodsServeDTO;
import com.gs.lshly.common.struct.merchadmin.pc.commodity.qto.PCMerchGoodsServeQTO;
import com.gs.lshly.common.struct.merchadmin.pc.commodity.vo.PCMerchGoodsServeVO;

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
    PageData<PCMerchGoodsServeVO.ListVO> pageGoodsServeData(PCMerchGoodsServeQTO.QTO qto);

    /**
     * 根据商品id查询商品服务详情
     *
     * @param dto
     * @return
     */
    List<PCMerchGoodsServeVO.ListVO> getGoodsServeDetailByGoodsId(PCMerchGoodsServeDTO.IdDTO dto);

    List<PCMerchGoodsServeVO.ListVO> getGoodsServeDetailTempByGoodsId(PCMerchGoodsServeDTO.IdDTO dto);
    
    List<String> getServeTempIdByGoodsId(PCMerchGoodsServeDTO.IdDTO dto);
    
    List<String> getServeIdByGoodsId(PCMerchGoodsServeDTO.IdDTO dto);
}
