package com.gs.lshly.rpc.api.merchadmin.pc.commodity;

import com.gs.lshly.common.struct.merchadmin.pc.commodity.dto.PCMerchGoodsCategoryDTO;
import com.gs.lshly.common.struct.merchadmin.pc.commodity.vo.PCMerchGoodsParamsVO;

import java.util.List;

/**
 * @Author Starry
 * @Date 10:48 2020/10/20
 */
public interface IPCMerchAdminGoodsParamsRpc {
    /**
     * 查询与类目关联的参数列表
     * @param dto
     * @return
     */
    List<PCMerchGoodsParamsVO.ParamsListVO> listGoodsParams(PCMerchGoodsCategoryDTO.IdDTO dto);

}
