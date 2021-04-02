package com.gs.lshly.biz.support.merchant.service.platadmin;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.common.struct.platadmin.merchant.dto.ShopDTO;
import com.gs.lshly.common.struct.platadmin.merchant.qto.ShopQTO;
import com.gs.lshly.common.struct.platadmin.merchant.vo.ShopVO;

import java.util.List;

public interface IShopService {

    PageData<ShopVO.ListVO> pageListShop(ShopQTO.QTO qto);

    /**
     * 添加自营店
     * @param dto
     */
    void addSelftShop(ShopDTO.ETO dto);


    /**
     * 编辑自营店
     * @param dto
     */
    void editorSelftShop(ShopDTO.ETO dto);




    /**
     * 关闭店铺
     * @param dto
     */
    void closeShop(ShopDTO.IdDTO dto);

    /**
     * 开启店铺
     * @param dto
     */
    void openShop(ShopDTO.IdDTO dto);



    List<ShopVO.IdNameVO> selfShopList(ShopQTO.SelfShopQTO qto);


    ShopVO.SelfShopDetailVO selfShopDetails(ShopDTO.IdDTO dto);


    ShopVO.DetailVO shopDetails(ShopDTO.IdDTO dto);


    void editorShop(ShopDTO.ComplexETO dto);


    List<ShopVO.InnerSimpleVO> innerlistShopIdName(BaseDTO dto, List<String> shopIdList);


    List<ShopVO.InnerSimpleVO> innerlistShopIdName(BaseDTO dto,Integer shopType,Integer terminal);

    ShopVO.DetailVO innerByIdGetPosShopId(String shopId);
}