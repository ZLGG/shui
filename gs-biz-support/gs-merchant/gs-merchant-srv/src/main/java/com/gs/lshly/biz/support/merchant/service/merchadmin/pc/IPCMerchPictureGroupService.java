package com.gs.lshly.biz.support.merchant.service.merchadmin.pc;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.dto.PCMerchPictureGroupDTO;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.qto.PCMerchPictureGroupQTO;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.vo.PCMerchPictureGroupVO;

import java.util.List;

public interface IPCMerchPictureGroupService {

    /**
     * 获取某个店铺下商品图片的分组
     * @param qto
     * @return
     */
    List<PCMerchPictureGroupVO.ListVO> listGroup(PCMerchPictureGroupQTO.BelongIdQTO qto);

    /**
     * 添加商家商品图片分组
     * @param eto
     */
    void addPictureGroup(PCMerchPictureGroupDTO.ETO eto);

    /**
     * 删除商家商品图片分组
     * @param dto
     */
    void deletePictureGroup(PCMerchPictureGroupDTO.IdDTO dto);

    /**
     * 编辑商家商品图片分组
     * @param eto
     */
    void editPictureGroup(PCMerchPictureGroupDTO.ETO eto);


}