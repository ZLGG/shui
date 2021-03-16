package com.gs.lshly.biz.support.merchant.service.merchadmin.pc;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.dto.PCMerchMerchantUserTypeDTO;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.qto.PCMerchMerchantUserTypeQTO;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.vo.PCMerchMerchantUserTypeVO;

import java.util.List;

public interface IPCMerchMerchantUserTypeService {

    /**
     * 会员类型分页列表
     * @param qto
     * @return
     */
    PageData<PCMerchMerchantUserTypeVO.ListVO> pageData(PCMerchMerchantUserTypeQTO.QTO qto);

    /**
     * 会员类型列表
     * @param dto
     * @return
     */
    List<PCMerchMerchantUserTypeVO.ListVO> listData(BaseDTO dto);

    /**
     * 设置商家会员类型折扣率
     * @param eto
     */
    void saveMerchantUserType(PCMerchMerchantUserTypeDTO.ETO eto);

    /**
     * 批量删除
     * @param dto
     */
    void deleteBatch(PCMerchMerchantUserTypeDTO.IdListDTO dto);


    /**
     * 获取会员类型详情
     * @param dto
     * @return
     */
    PCMerchMerchantUserTypeVO.DetailVO detailMerchantUserType(PCMerchMerchantUserTypeDTO.IdDTO dto);

    //---------------------内部服务----------------

    List<PCMerchMerchantUserTypeVO.ListVO> list(BaseDTO dto);
}