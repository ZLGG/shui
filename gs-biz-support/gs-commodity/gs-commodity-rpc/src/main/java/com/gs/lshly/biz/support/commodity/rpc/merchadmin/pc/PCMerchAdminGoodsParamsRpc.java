package com.gs.lshly.biz.support.commodity.rpc.merchadmin.pc;

import com.gs.lshly.biz.support.commodity.service.merchadmin.pc.IPCMerchGoodsParamsService;
import com.gs.lshly.common.struct.merchadmin.pc.commodity.dto.PCMerchGoodsCategoryDTO;
import com.gs.lshly.common.struct.merchadmin.pc.commodity.vo.PCMerchGoodsParamsVO;
import com.gs.lshly.rpc.api.merchadmin.pc.commodity.IPCMerchAdminGoodsParamsRpc;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @Author Starry
 * @Date 10:59 2020/10/20
 */
@DubboService
public class PCMerchAdminGoodsParamsRpc implements IPCMerchAdminGoodsParamsRpc {

    @Autowired
    private IPCMerchGoodsParamsService paramsService;

    @Override
    public List<PCMerchGoodsParamsVO.ParamsListVO> listGoodsParams(PCMerchGoodsCategoryDTO.IdDTO dto) {
        return paramsService.listGoodsParams(dto);
    }

}
