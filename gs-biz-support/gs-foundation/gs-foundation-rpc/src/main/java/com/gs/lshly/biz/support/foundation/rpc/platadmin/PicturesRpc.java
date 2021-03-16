package com.gs.lshly.biz.support.foundation.rpc.platadmin;
import com.gs.lshly.biz.support.foundation.service.platadmin.IPicturesService;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.platadmin.foundation.dto.PicturesDTO;
import com.gs.lshly.common.struct.platadmin.foundation.qto.PictureGroupQTO;
import com.gs.lshly.common.struct.platadmin.foundation.qto.PicturesQTO;
import com.gs.lshly.common.struct.platadmin.foundation.vo.PicturesVO;
import com.gs.lshly.rpc.api.platadmin.commodity.IPicturesRpc;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
*
* @author Starry
* @since 2020-10-06
*/
@DubboService
public class PicturesRpc implements IPicturesRpc{

    @Autowired
    private IPicturesService picturesService;

    @Override
    public PageData<PicturesVO.ListVO> pageData(PicturesQTO.QTO qto){
        return picturesService.pageData(qto);
    }

    @Override
    public void addPictures(PicturesDTO.ETO eto){
        picturesService.addPictures(eto);
    }

    @Override
    public void moveInRecyclePictures(PicturesDTO.IdListDTO dto) {
        picturesService.moveInRecyclePictures(dto);
    }

    @Override
    public void moveToOldPlacePictures(PicturesDTO.IdListDTO dto) {
        picturesService.moveToOldPlacePictures(dto);
    }

    @Override
    public void moveGroup(List<PicturesDTO.MoveGroupDTO> dto) {
        picturesService.moveGroup(dto);
    }

    @Override
    public void deletePictures(PicturesDTO.IdListDTO dto) {
        picturesService.deletePictures(dto);
    }

    @Override
    public PageData<PicturesVO.ListVO> pagePicturesBy(PictureGroupQTO.GroupPictureQTO qto) {
        return picturesService.pagePicturesBy(qto);
    }


}