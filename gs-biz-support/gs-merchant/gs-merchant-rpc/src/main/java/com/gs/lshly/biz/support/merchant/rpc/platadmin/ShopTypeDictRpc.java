package com.gs.lshly.biz.support.merchant.rpc.platadmin;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.platadmin.merchant.dto.ShopTypeDictDTO;
import com.gs.lshly.common.struct.platadmin.merchant.qto.ShopTypeDictQTO;
import com.gs.lshly.common.struct.platadmin.merchant.vo.ShopTypeDictVO;
import com.gs.lshly.biz.support.merchant.service.platadmin.IShopTypeDictService;
import com.gs.lshly.rpc.api.platadmin.merchant.IShopTypeDictRpc;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

/**
*
* @author xxfc
* @since 2020-10-14
*/
@DubboService
public class ShopTypeDictRpc implements IShopTypeDictRpc{

    @Autowired
    private IShopTypeDictService shopTypeDictService;

    @Override
    public PageData<ShopTypeDictVO.ListVO> pageData(ShopTypeDictQTO.QTO qto){
        return shopTypeDictService.pageData(qto);
    }

    @Override
    public void editShopTypeDict(ShopTypeDictDTO.ETO eto){
        shopTypeDictService.editShopTypeDict(eto);
    }

    @Override
    public ShopTypeDictVO.DetailVO detailsShopTypeDict(ShopTypeDictDTO.ShopTypeDTO dto) {
        return shopTypeDictService.detailsShopTypeDict(dto);
    }


}