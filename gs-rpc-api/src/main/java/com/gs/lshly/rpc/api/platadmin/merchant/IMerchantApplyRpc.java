package com.gs.lshly.rpc.api.platadmin.merchant;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.platadmin.merchant.dto.MerchantApplyDTO;
import com.gs.lshly.common.struct.platadmin.merchant.qto.MerchantApplyQTO;
import com.gs.lshly.common.struct.platadmin.merchant.vo.MerchantApplyVO;

/**
*
* @author xxfc
* @since 2020-10-14
*/
public interface IMerchantApplyRpc {

    PageData<MerchantApplyVO.ListVO> pageData(MerchantApplyQTO.QTO qto);

    PageData<MerchantApplyVO.ListVO> pageSearch(MerchantApplyQTO.SearchQTO qto);

    void deleteBatchMerchantApply(MerchantApplyDTO.IdListDTO dto);

    void apply(MerchantApplyDTO.ApplyDTO dto);

    void openShop(MerchantApplyDTO.IdDTO eto);

    MerchantApplyVO.BrandVO handBrandQuery(MerchantApplyDTO.IdDTO dto);

    void handBrandSubmit(MerchantApplyDTO.HandBrandDTO dto);

    MerchantApplyVO.DetailVO detailMerchantApply(MerchantApplyDTO.IdDTO dto);


}