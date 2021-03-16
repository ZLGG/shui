package com.gs.lshly.biz.support.merchant.service.platadmin;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.platadmin.merchant.dto.ShopTypeDictDTO;
import com.gs.lshly.common.struct.platadmin.merchant.qto.ShopTypeDictQTO;
import com.gs.lshly.common.struct.platadmin.merchant.vo.ShopTypeDictVO;

public interface IShopTypeDictService {

    PageData<ShopTypeDictVO.ListVO> pageData(ShopTypeDictQTO.QTO qto);

    void editShopTypeDict(ShopTypeDictDTO.ETO eto);

    ShopTypeDictVO.DetailVO detailsShopTypeDict(ShopTypeDictDTO.ShopTypeDTO dto);

}