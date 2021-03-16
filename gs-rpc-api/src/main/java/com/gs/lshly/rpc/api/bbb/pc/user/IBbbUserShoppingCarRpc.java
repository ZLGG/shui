package com.gs.lshly.rpc.api.bbb.pc.user;
import com.gs.lshly.common.response.ResponseData;
import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.common.struct.bbb.pc.commodity.vo.PCBbbGoodsInfoVO;
import com.gs.lshly.common.struct.bbb.pc.user.dto.BbbUserShoppingCarDTO;
import com.gs.lshly.common.struct.bbb.pc.user.qto.BbbUserShoppingCarQTO;
import com.gs.lshly.common.struct.bbb.pc.user.vo.BbbUserShoppingCarVO;
import java.util.List;

/**
*
* @author xxfc
* @since 2020-10-28
*/
public interface IBbbUserShoppingCarRpc {

    List<BbbUserShoppingCarVO.ListVO> list(BbbUserShoppingCarQTO.QTO qto);

    BbbUserShoppingCarVO.CountVO countShoppingCarGoods(BaseDTO dto);


    void addUserShoppingCar(BbbUserShoppingCarDTO.ETO eto);

    void addUserShoppingCar2(BbbUserShoppingCarDTO.AddShopingETO eto);

    void deleteBatchUserShoppingCar(BbbUserShoppingCarDTO.IdListDTO dto);

    BbbUserShoppingCarVO.ChangeQuantityVO changeQuantity(BbbUserShoppingCarDTO.QuantityDTO eto);

    void selectState(BbbUserShoppingCarDTO.SelectDTO eto);

    void selectStateAll(BbbUserShoppingCarDTO.SelectAllDTO eto);

    PCBbbGoodsInfoVO.GetGoodsStepPriceVO getGoodsStepPrice(BbbUserShoppingCarDTO.QueryGradePriceDTO dto);

    /**
     * 获取购物车信息数组(按店铺分组)
     * @param dto
     * @return
     */
    ResponseData<List<BbbUserShoppingCarVO.InnerListVO>> innerShoppingCarlist(BbbUserShoppingCarDTO.InnerIdListDTO dto);

    /**
     * 获取购物车数据(店铺)
     * @param dto
     * @return
     */
    ResponseData<BbbUserShoppingCarVO.InnerSimpleVO> innerSimpleShoppingCarlist(BbbUserShoppingCarDTO.InnerIdListDTO dto);

    /**
     * 清理购物车
     * @param
     * @return
     */
    boolean innerClearShopCarList(List<String> shoppingCarList);

}