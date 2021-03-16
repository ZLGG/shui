package com.gs.lshly.rpc.api.merchadmin.pc.merchant;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.dto.PCMerchMerchantUserTypeDTO;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.qto.PCMerchMerchantUserTypeQTO;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.vo.PCMerchMerchantUserTypeVO;

import java.util.List;

/**
*
* @author xxfc
* @since 2020-12-25
*/
public interface IPCMerchMerchantUserTypeRpc {

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
     * 设置会员类型折扣率
     * @param eto
     */
    void saveMerchantUserType(PCMerchMerchantUserTypeDTO.ETO eto);


    /**
     * 批量删除
     * @param dto
     */
    void deleteBatch(PCMerchMerchantUserTypeDTO.IdListDTO dto);


    /**
     * 获取详情
     * @param dto
     * @return
     */
    PCMerchMerchantUserTypeVO.DetailVO detailMerchantUserType(PCMerchMerchantUserTypeDTO.IdDTO dto);

    //---------------------内部服务----------------

    List<PCMerchMerchantUserTypeVO.ListVO> list(BaseDTO dto);
}