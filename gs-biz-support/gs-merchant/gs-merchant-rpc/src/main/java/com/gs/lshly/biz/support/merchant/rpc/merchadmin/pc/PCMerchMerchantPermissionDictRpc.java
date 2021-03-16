package com.gs.lshly.biz.support.merchant.rpc.merchadmin.pc;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.dto.PCMerchMerchantPermissionDictDTO;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.qto.PCMerchMerchantPermissionDictQTO;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.vo.PCMerchMerchantPermissionDictVO;
import com.gs.lshly.rpc.api.merchadmin.pc.merchant.IPCMerchMerchantPermissionDictRpc;
import com.gs.lshly.biz.support.merchant.service.merchadmin.pc.IPCMerchMerchantPermissionDictService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
*
* @author xxfc
* @since 2020-10-26
*/
@DubboService
public class PCMerchMerchantPermissionDictRpc implements IPCMerchMerchantPermissionDictRpc{

    @Autowired
    private IPCMerchMerchantPermissionDictService  pCMerchMerchantPermissionDictService;

    @Override
    public List<PCMerchMerchantPermissionDictVO.ListVO> list(PCMerchMerchantPermissionDictQTO.QTO qto){
        return pCMerchMerchantPermissionDictService.list(qto);
    }

    @Override
    public void addMerchantPermissionDict(PCMerchMerchantPermissionDictDTO.ETO eto){
        pCMerchMerchantPermissionDictService.addMerchantPermissionDict(eto);
    }

    @Override
    public void deleteMerchantPermissionDict(PCMerchMerchantPermissionDictDTO.IdDTO dto){
        pCMerchMerchantPermissionDictService.deleteMerchantPermissionDict(dto);
    }


    @Override
    public void editMerchantPermissionDict(PCMerchMerchantPermissionDictDTO.ETO eto){
        pCMerchMerchantPermissionDictService.editMerchantPermissionDict(eto);
    }

}