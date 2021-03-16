package com.gs.lshly.biz.support.foundation.rpc.platadmin;
import com.gs.lshly.biz.support.foundation.service.platadmin.IPictureGroupService;
import com.gs.lshly.common.struct.platadmin.foundation.dto.PictureGroupDTO;
import com.gs.lshly.common.struct.platadmin.foundation.qto.PictureGroupQTO;
import com.gs.lshly.common.struct.platadmin.foundation.vo.PictureGroupVO;
import com.gs.lshly.rpc.api.platadmin.commodity.IPictureGroupRpc;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
*
* @author Starry
* @since 2020-10-06
*/
@DubboService
public class PictureGroupRpc implements IPictureGroupRpc{

    @Autowired
    private IPictureGroupService pictureGroupService;

    @Override
    public List<PictureGroupVO.ListVO> pageData(PictureGroupQTO.QTO qto){
        return pictureGroupService.pageData(qto);
    }

    @Override
    public void addPictureGroup(PictureGroupDTO.ETO eto){
        pictureGroupService.addPictureGroup(eto);
    }

    @Override
    public void deletePictureGroup(PictureGroupDTO.IdDTO dto){
        pictureGroupService.deletePictureGroup(dto);
    }

    @Override
    public void editPictureGroup(PictureGroupDTO.ETO eto){
        pictureGroupService.editPictureGroup(eto);
    }

    @Override
    public PictureGroupVO.DetailVO detailPictureGroup(PictureGroupDTO.IdDTO dto){
        return pictureGroupService.detailPictureGroup(dto);
    }

}