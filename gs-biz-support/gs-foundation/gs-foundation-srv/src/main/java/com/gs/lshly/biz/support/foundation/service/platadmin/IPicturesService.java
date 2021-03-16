package com.gs.lshly.biz.support.foundation.service.platadmin;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.qto.PCMerchPicturesQTO;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.vo.PCMerchPicturesVO;
import com.gs.lshly.common.struct.platadmin.foundation.dto.PicturesDTO;
import com.gs.lshly.common.struct.platadmin.foundation.qto.PictureGroupQTO;
import com.gs.lshly.common.struct.platadmin.foundation.qto.PicturesQTO;
import com.gs.lshly.common.struct.platadmin.foundation.vo.PicturesVO;

import java.util.List;

public interface IPicturesService {

    /**
     * 查询图片列表
     * @param qto
     * @return
     */
    PageData<PicturesVO.ListVO> pageData(PicturesQTO.QTO qto);

    /**
     * 将图片添加到数据库中
     * @param eto
     */
    void addPictures(PicturesDTO.ETO eto);

    /**
     * 批量将图片移入回收站
     * @param dto
     */
    void moveInRecyclePictures(PicturesDTO.IdListDTO dto);

    /**
     * 批量将图片移回原处
     * @param dto
     */
    void moveToOldPlacePictures(PicturesDTO.IdListDTO dto);

    /**
     * 将图片移动分组
     * @param dto
     */
    void moveGroup(List<PicturesDTO.MoveGroupDTO> dto);

    /**
     * 删除数据库中的图片
     * @param dto
     */
    void deletePictures(PicturesDTO.IdListDTO dto);

    /**
     * 查询平台下不同分组显示的图片
     * @param qto
     * @return
     */
    PageData<PicturesVO.ListVO> pagePicturesBy(PictureGroupQTO.GroupPictureQTO qto);


    /**
     * 根据分组查询分组下面的图片
     * @param qto
     * @return
     */
    List<PicturesVO.ListVO> getPicturesByGroup(PictureGroupQTO.GroupPictureQTO qto);

}