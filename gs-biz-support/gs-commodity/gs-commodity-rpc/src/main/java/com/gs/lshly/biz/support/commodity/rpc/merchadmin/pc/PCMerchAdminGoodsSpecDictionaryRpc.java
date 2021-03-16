package com.gs.lshly.biz.support.commodity.rpc.merchadmin.pc;

import com.gs.lshly.biz.support.commodity.service.merchadmin.pc.IPCMerchGoodsSpecDictionaryService;
import com.gs.lshly.common.struct.merchadmin.pc.commodity.dto.PCMerchGoodsCategoryDTO;
import com.gs.lshly.common.struct.merchadmin.pc.commodity.vo.PCMerchGoodsSpecDictionaryVO;
import com.gs.lshly.rpc.api.merchadmin.pc.commodity.IPCMerchAdminGoodsSpecDictionaryRpc;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @Author Starry
 * @Date 20:42 2020/10/19
 */
@DubboService
public class PCMerchAdminGoodsSpecDictionaryRpc implements IPCMerchAdminGoodsSpecDictionaryRpc {
    @Autowired
    private IPCMerchGoodsSpecDictionaryService specDictionaryService;

    @Override
    public List<PCMerchGoodsSpecDictionaryVO.DetailVO> listSpecDetail(PCMerchGoodsCategoryDTO.IdDTO dto) {
        return specDictionaryService.listSpecDetail(dto);
    }
}
