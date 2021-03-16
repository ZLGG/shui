package com.gs.lshly.biz.support.merchant.service.merchadmin.pc;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.dto.PCMerchPicturesDTO;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.qto.PCMerchPicturesQTO;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.vo.PCMerchPicturesVO;

import java.util.List;

public interface IPCMerchPicturesService {

    PageData<PCMerchPicturesVO.ListVO> pageData(PCMerchPicturesQTO.QTO qto);

    void addPictures(PCMerchPicturesDTO.ETO eto);

    /**
     * 批量删除图片
     * @param dto
     */
    void deletePictures(PCMerchPicturesDTO.IdListDTO dto);

    PCMerchPicturesVO.DetailVO detailPictures(PCMerchPicturesDTO.IdDTO dto);

    /**
     * 批量将图片移入其他的分组中
     * @param etos
     */
    void moveInGroup(List<PCMerchPicturesDTO.MoveGroupETO> etos);

    /**
     * 根据分组查询分组下面的图片
     * @param qto
     * @return
     */
    List<PCMerchPicturesVO.ListVO> getPicturesByGroup(PCMerchPicturesQTO.GroupIdQTO qto);

}