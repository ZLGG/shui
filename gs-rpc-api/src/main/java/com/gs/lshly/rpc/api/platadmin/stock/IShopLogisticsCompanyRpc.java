package com.gs.lshly.rpc.api.platadmin.stock;

import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.platadmin.merchant.vo.ShopLogisticsCompanyVO;
import com.gs.lshly.common.struct.platadmin.stock.dto.LogisticsCompanyDTO;
import com.gs.lshly.common.struct.platadmin.stock.qto.LogisticsCompanyQTO;

public interface IShopLogisticsCompanyRpc {

    /**
     * 开启使用物流公司
     * @param idDTO
     * @param shopIdDTO
     */
    void openLogisticsCompany(LogisticsCompanyDTO.IdDTO idDTO, LogisticsCompanyDTO.ShopIdDTO shopIdDTO);

    /**
     * 关闭使用物流公司
     * @param idDTO
     * @param shopIdDTO
     */
    void closeLogisticsCompany(LogisticsCompanyDTO.IdDTO idDTO, LogisticsCompanyDTO.ShopIdDTO shopIdDTO);

    /**
     * 查询物流公司列表（分页）
     */
    PageData<ShopLogisticsCompanyVO.ListVO> findPageOfShop(LogisticsCompanyQTO.QueryParam param, LogisticsCompanyDTO.ShopIdDTO shopIdDTO);

}
