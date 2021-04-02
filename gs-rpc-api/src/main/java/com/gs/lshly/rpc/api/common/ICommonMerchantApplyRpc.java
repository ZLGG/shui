package com.gs.lshly.rpc.api.common;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.common.LegalDictDTO;
import com.gs.lshly.common.struct.common.LegalDictVO;
import com.gs.lshly.common.struct.common.dto.CommonMerchantApplyDTO;
import com.gs.lshly.common.struct.common.qto.CommonMerchantApplyQTO;
import com.gs.lshly.common.struct.merchadmin.pc.commodity.vo.PCMerchGoodsCategoryVO;
import com.gs.lshly.common.struct.common.vo.CommonMerchantApplyVO;

import java.util.List;

/**
*
* @author xxfc
* @since 2020-10-14
*/
public interface ICommonMerchantApplyRpc {

    String editMerchantApply(CommonMerchantApplyDTO.ETO eto);

    CommonMerchantApplyVO.DetailVO detailMerchantApply(CommonMerchantApplyDTO.QueryDTO dto);

    void editApplyCategory(CommonMerchantApplyDTO.ApplyCategoryDTO dto);

    List<PCMerchGoodsCategoryVO.ListVO> categoryList(CommonMerchantApplyQTO.CategoryQTO dto);

    PageData<CommonMerchantApplyVO.ApplyCategoryRecordVO> applyCategoryRecord(CommonMerchantApplyQTO.ApplyCategoryQTO qto);

    void deleteCategoryRecord(CommonMerchantApplyDTO.IdDTO dto);

    /**
     * 根据商家id获取企业字典id
     * @param merchantId
     * @return
     */
    String innerGetLegalId(String merchantId);

    /**
     * 保存入驻后资料信息修改
     * @param eto
     * @return
     */
    String innerSaveSettledApply(LegalDictDTO.SettledInfoETO eto);

    /**
     * 获取修改后的入驻信息
     * @param dto
     * @return
     */
    LegalDictVO.SettledInfoVO getAfterUpdateSettleInfo(LegalDictDTO.MerchantApplyIdDTO dto);

}