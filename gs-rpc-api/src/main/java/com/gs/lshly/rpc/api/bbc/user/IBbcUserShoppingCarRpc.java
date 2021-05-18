package com.gs.lshly.rpc.api.bbc.user;
import java.util.List;

import com.gs.lshly.common.response.ResponseData;
import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.common.struct.bbc.user.dto.BbcUserShoppingCarDTO;
import com.gs.lshly.common.struct.bbc.user.qto.BbcUserShoppingCarQTO;
import com.gs.lshly.common.struct.bbc.user.vo.BbcUserShoppingCarVO;
import com.gs.lshly.common.struct.bbc.user.vo.BbcUserShoppingCarVO.ShopSkuVO;

/**
*
* @author xxfc
* @since 2020-10-28
*/
public interface IBbcUserShoppingCarRpc {

	BbcUserShoppingCarVO.HomeVO list(BbcUserShoppingCarQTO.QTO qto);

    BbcUserShoppingCarVO.CountVO countShoppingCarGoods(BaseDTO dto);


    void addUserShoppingCar(BbcUserShoppingCarDTO.ETO eto);

    void deleteBatchUserShoppingCar(BbcUserShoppingCarDTO.IdListDTO dto);

    void changeQuantity(BbcUserShoppingCarDTO.QuantityDTO eto);

    void selectState(BbcUserShoppingCarDTO.SelectDTO eto);

    void selectStateAll(BbcUserShoppingCarDTO.SelectAllDTO eto);

    /**
     * 获取购物车信息数组(按店铺分组)
     * @param dto
     * @return
     */
    ResponseData<List<BbcUserShoppingCarVO.InnerListVO>> innerShoppingCarlist(BbcUserShoppingCarDTO.InnerIdListDTO dto);

    /**
     * 获取购物车数据(店铺)
     * @param dto
     * @return
     */
    ResponseData<BbcUserShoppingCarVO.InnerSimpleVO> innerSimpleShoppingCarlist(BbcUserShoppingCarDTO.InnerIdListDTO dto);

    /**
     * 清理购物车
     * @param
     * @return
     */
    boolean innerClearShopCarList(List<String> shoppingCarList);
    
    /**
     * 购物车总计
     * @param dto
     * @return
     */
    BbcUserShoppingCarVO.SummationVO summationUserShoppingCar(BbcUserShoppingCarDTO.IdListDTO dto);
    
    /**
     * 跟据购物车的skuid，获取所属的店铺id列表
     * @param dto
     * @return
     */
    List<ShopSkuVO> groupShopByCarId(BbcUserShoppingCarDTO.InnerIdListDTO dto);

}