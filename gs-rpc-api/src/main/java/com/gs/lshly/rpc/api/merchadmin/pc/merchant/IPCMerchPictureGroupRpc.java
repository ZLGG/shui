package com.gs.lshly.rpc.api.merchadmin.pc.merchant;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.dto.PCMerchPictureGroupDTO;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.qto.PCMerchPictureGroupQTO;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.vo.PCMerchPictureGroupVO;

import java.util.List;

/**
*
* @author Starry
* @since 2020-10-22
*/
public interface IPCMerchPictureGroupRpc {
    /**
     * 获取某个店铺下商品图片的分组
     * @param qto
     * @return
     */
    List<PCMerchPictureGroupVO.ListVO> listGroup(PCMerchPictureGroupQTO.BelongIdQTO qto);

    void addPictureGroup(PCMerchPictureGroupDTO.ETO eto);

    void deletePictureGroup(PCMerchPictureGroupDTO.IdDTO dto);

    /**
     * 编辑商家商品图片分组
     * @param eto
     */
    void editPictureGroup(PCMerchPictureGroupDTO.ETO eto);

}