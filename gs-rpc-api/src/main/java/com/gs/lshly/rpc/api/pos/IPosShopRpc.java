package com.gs.lshly.rpc.api.pos;
import com.gs.lshly.common.response.ResponseData;
import com.gs.lshly.common.struct.pos.dto.PosShopDTO;
import com.gs.lshly.common.struct.pos.dto.PosShopRequestDTO;
import com.gs.lshly.common.struct.pos.qto.PosShopQTO;

/**
*
* @author xxfc
* @since 2020-10-13
*/
public interface IPosShopRpc {

    ResponseData<Void> pullListShop(PosShopQTO.QTO qto);

    ResponseData<Void> pullShopDetails(PosShopDTO.IdDTO dto);

    /**
     * 同步pos店铺信息
     * @param dto
     */
    void synchroPosShop(PosShopRequestDTO.DTO dto);

}