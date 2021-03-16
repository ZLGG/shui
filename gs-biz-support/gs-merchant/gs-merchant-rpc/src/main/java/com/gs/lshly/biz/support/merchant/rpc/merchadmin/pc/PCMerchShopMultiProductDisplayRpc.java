package com.gs.lshly.biz.support.merchant.rpc.merchadmin.pc;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.dto.PCMerchShopMultiProductDisplayDTO;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.qto.PCMerchShopMultiProductDisplayQTO;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.vo.PCMerchShopMultiProductDisplayVO;
import com.gs.lshly.rpc.api.merchadmin.pc.merchant.IPCMerchShopMultiProductDisplayRpc;
import com.gs.lshly.biz.support.merchant.service.merchadmin.pc.IPCMerchShopMultiProductDisplayService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

/**
*
* @author 陈奇
* @since 2020-10-23
*/
@DubboService
public class PCMerchShopMultiProductDisplayRpc implements IPCMerchShopMultiProductDisplayRpc{
    @Autowired
    private IPCMerchShopMultiProductDisplayService  pCMerchShopMultiProductDisplayService;

    @Override
    public PageData<PCMerchShopMultiProductDisplayVO.ListVO> pageData(PCMerchShopMultiProductDisplayQTO.QTO qto){
        return pCMerchShopMultiProductDisplayService.pageData(qto);
    }

    @Override
    public void addShopMultiProductDisplay(PCMerchShopMultiProductDisplayDTO.ADTO adto){
        pCMerchShopMultiProductDisplayService.addShopMultiProductDisplay(adto);
    }

    @Override
    public void deleteShopMultiProductDisplay(PCMerchShopMultiProductDisplayDTO.IdDTO dto){
        pCMerchShopMultiProductDisplayService.deleteShopMultiProductDisplay(dto);
    }


    @Override
    public void editShopMultiProductDisplay( PCMerchShopMultiProductDisplayDTO.UDTO udto){
        pCMerchShopMultiProductDisplayService.editShopMultiProductDisplay(udto);
    }

    @Override
    public PCMerchShopMultiProductDisplayVO.DetailVO detailShopMultiProductDisplay(PCMerchShopMultiProductDisplayDTO.IdDTO dto){
        return  pCMerchShopMultiProductDisplayService.detailShopMultiProductDisplay(dto);
    }

    @Override
    public void changeShopMultiProductDisplay(PCMerchShopMultiProductDisplayDTO.CDTO cdto) {
        pCMerchShopMultiProductDisplayService.changeShopMultiProductDisplay(cdto);
    }

}