package com.gs.lshly.rpc.api.platadmin.merchant;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.platadmin.merchant.dto.ShopTypeDictDTO;
import com.gs.lshly.common.struct.platadmin.merchant.qto.ShopTypeDictQTO;
import com.gs.lshly.common.struct.platadmin.merchant.vo.ShopTypeDictVO;

/**
*
* @author xxfc
* @since 2020-10-14
*/
public interface IShopTypeDictRpc {

    PageData<ShopTypeDictVO.ListVO> pageData(ShopTypeDictQTO.QTO qto);

    void editShopTypeDict(ShopTypeDictDTO.ETO eto);


    ShopTypeDictVO.DetailVO detailsShopTypeDict(ShopTypeDictDTO.ShopTypeDTO dto);

}