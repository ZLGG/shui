package com.gs.lshly.biz.support.merchant.rpc.merchadmin.pc;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.dto.PCMerchPictureGroupDTO;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.qto.PCMerchPictureGroupQTO;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.vo.PCMerchPictureGroupVO;
import com.gs.lshly.rpc.api.merchadmin.pc.merchant.IPCMerchPictureGroupRpc;
import com.gs.lshly.biz.support.merchant.service.merchadmin.pc.IPCMerchPictureGroupService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
*
* @author Starry
* @since 2020-10-22
*/
@DubboService
public class PCMerchPictureGroupRpc implements IPCMerchPictureGroupRpc{
    @Autowired
    private IPCMerchPictureGroupService  pCMerchPictureGroupService;


    @Override
    public List<PCMerchPictureGroupVO.ListVO> listGroup(PCMerchPictureGroupDTO.ParentIdDTO dto) {
        return pCMerchPictureGroupService.listGroup(dto);
    }

    @Override
    public void addPictureGroup(PCMerchPictureGroupDTO.SaveETO eto,BaseDTO baseDTO){
        pCMerchPictureGroupService.addPictureGroup(eto,baseDTO);
    }

    @Override
    public void deletePictureGroup(PCMerchPictureGroupDTO.IdListDTO dto){
        pCMerchPictureGroupService.deletePictureGroup(dto);
    }

    @Override
    public void editPictureGroup(PCMerchPictureGroupDTO.ETO eto) {
        pCMerchPictureGroupService.editPictureGroup(eto);
    }


}