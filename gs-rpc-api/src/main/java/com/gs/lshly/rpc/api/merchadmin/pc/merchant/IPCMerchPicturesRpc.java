package com.gs.lshly.rpc.api.merchadmin.pc.merchant;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.dto.PCMerchPicturesDTO;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.qto.PCMerchPicturesQTO;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.vo.PCMerchPicturesVO;

import java.util.List;

/**
*
* @author Starry
* @since 2020-10-22
*/
public interface IPCMerchPicturesRpc {

    PageData<PCMerchPicturesVO.ListVO> pageData(PCMerchPicturesQTO.QTO qto);

    void addPictures(PCMerchPicturesDTO.ETO eto);

    void deletePictures(PCMerchPicturesDTO.IdListDTO dto);


    PCMerchPicturesVO.DetailVO detailPictures(PCMerchPicturesDTO.IdDTO dto);

    /**
     * 批量将图片移入其他的分组中
     * @param etos
     */
    void moveInGroup(List<PCMerchPicturesDTO.MoveGroupETO> etos);

}