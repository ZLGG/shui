package com.gs.lshly.biz.support.foundation.rpc.platadmin;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.platadmin.foundation.dto.PicturesSettingDTO;
import com.gs.lshly.common.struct.platadmin.foundation.qto.PicturesSettingQTO;
import com.gs.lshly.common.struct.platadmin.foundation.vo.PicturesSettingVO;
import com.gs.lshly.rpc.api.platadmin.foundation.IPicturesSettingRpc;
import com.gs.lshly.biz.support.foundation.service.platadmin.IPicturesSettingService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
*
* @author Starry
* @since 2020-10-21
*/
@DubboService
public class PicturesSettingRpc implements IPicturesSettingRpc{
    @Autowired
    private IPicturesSettingService  PicturesSettingService;


    @Override
    public List<PicturesSettingVO.ListVO> listPicturesSetting() {
        return PicturesSettingService.listPicturesSetting();
    }

    @Override
    public void addPicturesSetting(List<PicturesSettingDTO.ETO> eto){
        PicturesSettingService.addPicturesSetting(eto);
    }


}