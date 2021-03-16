package com.gs.lshly.biz.support.merchant.rpc.pos;

import com.gs.lshly.biz.support.merchant.service.pos.IPosShopService;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.response.ResponseData;
import com.gs.lshly.common.struct.platadmin.merchant.dto.ShopDTO;
import com.gs.lshly.common.struct.platadmin.merchant.qto.ShopQTO;
import com.gs.lshly.common.struct.platadmin.merchant.vo.ShopVO;
import com.gs.lshly.common.struct.pos.dto.PosShopDTO;
import com.gs.lshly.common.struct.pos.dto.PosShopRequestDTO;
import com.gs.lshly.common.struct.pos.qto.PosShopQTO;
import com.gs.lshly.rpc.api.pos.IPosShopRpc;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

/**
*
* @author xxfc
* @since 2020-10-13
*/
@DubboService
public class PosShopRpc implements IPosShopRpc {

    @Autowired
    private IPosShopService posShopService;


    @Override
    public ResponseData<Void> pullListShop(PosShopQTO.QTO qto) {
        return posShopService.pullListShop(qto);
    }

    @Override
    public ResponseData<Void> pullShopDetails(PosShopDTO.IdDTO dto) {
        return posShopService.pullShopDetails(dto);
    }

    @Override
    public void synchroPosShop(PosShopRequestDTO.DTO dto) {
        posShopService.synchroPosShop(dto);
    }
}