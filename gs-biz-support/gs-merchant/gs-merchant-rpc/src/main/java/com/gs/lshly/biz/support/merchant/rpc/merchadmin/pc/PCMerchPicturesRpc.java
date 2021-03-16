package com.gs.lshly.biz.support.merchant.rpc.merchadmin.pc;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.dto.PCMerchPicturesDTO;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.qto.PCMerchPicturesQTO;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.vo.PCMerchPicturesVO;
import com.gs.lshly.rpc.api.merchadmin.pc.merchant.IPCMerchPicturesRpc;
import com.gs.lshly.biz.support.merchant.service.merchadmin.pc.IPCMerchPicturesService;
import org.apache.dubbo.config.annotation.DubboService;
import org.apache.poi.ss.usermodel.Picture;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
*
* @author Starry
* @since 2020-10-22
*/
@DubboService
public class PCMerchPicturesRpc implements IPCMerchPicturesRpc{
    @Autowired
    private IPCMerchPicturesService  pCMerchPicturesService;

    @Override
    public PageData<PCMerchPicturesVO.ListVO> pageData(PCMerchPicturesQTO.QTO qto){
        return pCMerchPicturesService.pageData(qto);
    }

    @Override
    public void addPictures(PCMerchPicturesDTO.ETO eto){
        pCMerchPicturesService.addPictures(eto);
    }

    @Override
    public void deletePictures(PCMerchPicturesDTO.IdListDTO dto){
        pCMerchPicturesService.deletePictures(dto);
    }


    @Override
    public PCMerchPicturesVO.DetailVO detailPictures(PCMerchPicturesDTO.IdDTO dto){
        return  pCMerchPicturesService.detailPictures(dto);
    }

    @Override
    public void moveInGroup(List<PCMerchPicturesDTO.MoveGroupETO> etos) {
        pCMerchPicturesService.moveInGroup(etos);
    }

}