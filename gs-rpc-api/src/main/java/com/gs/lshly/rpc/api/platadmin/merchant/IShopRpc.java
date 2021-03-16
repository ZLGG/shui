package com.gs.lshly.rpc.api.platadmin.merchant;
import com.gs.lshly.common.enums.ShopTypeEnum;
import com.gs.lshly.common.enums.TerminalEnum;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.common.struct.platadmin.merchant.dto.ShopDTO;
import com.gs.lshly.common.struct.platadmin.merchant.qto.ShopQTO;
import com.gs.lshly.common.struct.platadmin.merchant.vo.ShopVO;

import java.util.List;

/**
*
* @author xxfc
* @since 2020-10-13
*/
public interface IShopRpc {

    /**
     * 店铺查询分页列表
     * @param qto
     * @return
     */
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

    /**
     * 自营店列表
     * @param qto
     * @return
     */
    List<ShopVO.IdNameVO> selfShopList(ShopQTO.SelfShopQTO qto);


    /**
     * 自营店详情
     * @param dto
     * @return
     */
    ShopVO.SelfShopDetailVO selfShopDetails(ShopDTO.IdDTO dto);


    /**
     * 店铺详情
     * @param dto
     * @return
     */
    ShopVO.DetailVO shopDetails(ShopDTO.IdDTO dto);

    /**
     * 编辑店铺
     * @param dto
     */
    void editorShop(ShopDTO.ComplexETO dto);


    /**
     * 店铺
     * @param dto
     * @return
     */
    List<ShopVO.InnerSimpleVO> innerlistShopIdName(BaseDTO dto,List<String> shopIdList);


    /**
     * 店铺列表.
     * shopType :店铺类型 ShopTypeEnum,
     * terminal:2c,2b店   TerminalEnum
     * @param dto
     * @param shopType
     * @param terminal
     * @return
     */

    List<ShopVO.InnerSimpleVO> innerlistShopIdName(BaseDTO dto,Integer shopType,Integer terminal);
}