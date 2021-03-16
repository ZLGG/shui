package com.gs.lshly.rpc.api.platadmin.foundation;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.platadmin.foundation.dto.PicturesSettingDTO;
import com.gs.lshly.common.struct.platadmin.foundation.qto.PicturesSettingQTO;
import com.gs.lshly.common.struct.platadmin.foundation.vo.PicturesSettingVO;

import java.util.List;

/**
*
* @author Starry
* @since 2020-10-21
*/
public interface IPicturesSettingRpc {


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