package com.gs.lshly.rpc.api.merchadmin.pc.merchant;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.dto.PCMerchShopMultiProductDisplayDTO;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.qto.PCMerchShopMultiProductDisplayQTO;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.vo.PCMerchShopMultiProductDisplayVO;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

/**
*
* @author 陈奇
* @since 2020-10-23
*/
public interface IPCMerchShopMultiProductDisplayRpc {

    PageData<PCMerchShopMultiProductDisplayVO.ListVO> pageData(PCMerchShopMultiProductDisplayQTO.QTO qto);

    void addShopMultiProductDisplay(PCMerchShopMultiProductDisplayDTO.ADTO adto);

    void deleteShopMultiProductDisplay(PCMerchShopMultiProductDisplayDTO.IdDTO dto);

    void editShopMultiProductDisplay( PCMerchShopMultiProductDisplayDTO.UDTO udto);

    PCMerchShopMultiProductDisplayVO.DetailVO detailShopMultiProductDisplay(PCMerchShopMultiProductDisplayDTO.IdDTO dto);

    void changeShopMultiProductDisplay(PCMerchShopMultiProductDisplayDTO.CDTO cdto);
}