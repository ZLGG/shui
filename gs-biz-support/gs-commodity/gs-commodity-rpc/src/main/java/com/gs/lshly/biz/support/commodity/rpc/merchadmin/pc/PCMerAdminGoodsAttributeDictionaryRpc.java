package com.gs.lshly.biz.support.commodity.rpc.merchadmin.pc;

import com.gs.lshly.biz.support.commodity.service.merchadmin.pc.IPCMerchGoodsAttributeDictionaryService;
import com.gs.lshly.common.struct.merchadmin.pc.commodity.dto.PCMerchGoodsCategoryDTO;
import com.gs.lshly.common.struct.merchadmin.pc.commodity.vo.PCMerchGoodsAttributeDictionaryVO;
import com.gs.lshly.rpc.api.merchadmin.pc.commodity.IPCMerchAdminGoodsAttributeDictionaryRpc;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @Author Starry
 * @Date 14:30 2020/10/20
 */
@DubboService
public class PCMerAdminGoodsAttributeDictionaryRpc implements IPCMerchAdminGoodsAttributeDictionaryRpc {
   @Autowired
   private IPCMerchGoodsAttributeDictionaryService attributeDictionaryService;

    @Override
    public List<PCMerchGoodsAttributeDictionaryVO.DetailVO> listAttributeDetail(PCMerchGoodsCategoryDTO.IdDTO dto) {
        return attributeDictionaryService.listAttributeDetail(dto);
    }
}
