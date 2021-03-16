package com.gs.lshly.biz.support.foundation.service.platadmin;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.platadmin.foundation.dto.PicturesSettingDTO;
import com.gs.lshly.common.struct.platadmin.foundation.qto.PicturesSettingQTO;
import com.gs.lshly.common.struct.platadmin.foundation.vo.PicturesSettingVO;

import java.util.List;

public interface IPicturesSettingService {


    /**
     * 查询默认图片信息
     * @return
     */
    List<PicturesSettingVO.ListVO> listPicturesSetting();

    /**
     * 新增默认图片设置
     * @param eto
     */
    void addPicturesSetting(List<PicturesSettingDTO.ETO> eto);

}