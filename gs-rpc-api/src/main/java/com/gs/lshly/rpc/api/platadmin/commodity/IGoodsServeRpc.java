package com.gs.lshly.rpc.api.platadmin.commodity;

import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.merchadmin.pc.commodity.dto.PCMerchGoodsInfoDTO;
import com.gs.lshly.common.struct.platadmin.commodity.dto.GoodsInfoDTO;
import com.gs.lshly.common.struct.platadmin.commodity.dto.GoodsServeDTO;
import com.gs.lshly.common.struct.platadmin.commodity.qto.GoodsServeQTO;
import com.gs.lshly.common.struct.platadmin.commodity.vo.GoodsInfoVO;
import com.gs.lshly.common.struct.platadmin.commodity.vo.GoodsServeVO;
import org.apache.dubbo.config.annotation.DubboService;

import java.util.List;

/**
 * @author hanly
 * @since 2021-05-25
 */
@DubboService
public interface IGoodsServeRpc {
    /**
     * 分页查询商品服务列表
     *
     * @param qto
     * @return
     */
    PageData<GoodsServeVO.ListVO> pageGoodsServeData(GoodsServeQTO.QTO qto);

    /**
     * 查询商品服务详情
     *
     * @param dto
     * @return
     */
    GoodsServeVO.ListVO getGoodsServeDetail(GoodsServeDTO.IdDTO dto);

    /**
     * 新增服务
     *
     * @param eto
     */
    void addGoodsServe(GoodsServeDTO.ETO eto);

    /**
     * 修改服务信息
     *
     * @param eto
     */
    void editGoodsServe(GoodsServeDTO.EditDTO eto);

    /**
     * 删除服务
     *
     * @param dto
     */
    void deleteGoodsServe(GoodsServeDTO.IdListDTO dto);
}
