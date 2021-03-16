package com.gs.lshly.biz.support.merchant.service.merchadmin.pc;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.dto.PCMerchShopBannerDTO;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.qto.PCMerchShopBannerQTO;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.vo.PCMerchShopBannerVO;

import java.util.List;

public interface IPCMerchShopBannerService {

    PageData<PCMerchShopBannerVO.ListVO> pageData(PCMerchShopBannerQTO.QTO qto);

    List<PCMerchShopBannerVO.H5ListVO> h5List(PCMerchShopBannerQTO.H5QTO qto);

    void h5Editor(PCMerchShopBannerDTO.H5ETO eto);

    List<PCMerchShopBannerVO.PCListVO> pcList(PCMerchShopBannerQTO.PCQTO qto);

    void pcEditor(PCMerchShopBannerDTO.PCETO eto);

}