package com.gs.lshly.rpc.api.bbb.h5.user;
import com.gs.lshly.common.response.ResponseData;
import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.common.struct.bbb.h5.user.dto.BbbH5UserShoppingCarDTO;
import com.gs.lshly.common.struct.bbb.h5.user.qto.BbbH5UserShoppingCarQTO;
import com.gs.lshly.common.struct.bbb.h5.user.vo.BbbH5UserShoppingCarVO;
import java.util.List;

/**
*
* @author xxfc
* @since 2020-10-28
*/
public interface IBbbH5UserShoppingCarRpc {

    List<BbbH5UserShoppingCarVO.ListVO> list(BbbH5UserShoppingCarQTO.QTO qto);

    BbbH5UserShoppingCarVO.CountVO countShoppingCarGoods(BaseDTO dto);


    void addUserShoppingCar(BbbH5UserShoppingCarDTO.ETO eto);

    void deleteBatchUserShoppingCar(BbbH5UserShoppingCarDTO.IdListDTO dto);

    void changeQuantity(BbbH5UserShoppingCarDTO.QuantityDTO eto);

    void selectState(BbbH5UserShoppingCarDTO.SelectDTO eto);

    void selectStateAll(BbbH5UserShoppingCarDTO.SelectAllDTO eto);

    /**
     * 获取购物车信息数组(按店铺分组)
     * @param dto
     * @return
     */
    ResponseData<List<BbbH5UserShoppingCarVO.InnerListVO>> innerShoppingCarlist(BbbH5UserShoppingCarDTO.InnerIdListDTO dto);

    /**
     * 获取购物车数据(店铺)
     * @param dto
     * @return
     */
    ResponseData<BbbH5UserShoppingCarVO.InnerSimpleVO> innerSimpleShoppingCarlist(BbbH5UserShoppingCarDTO.InnerIdListDTO dto);

    /**
     * 清理购物车
     * @param
     * @return
     */
    boolean innerClearShopCarList(List<String> shoppingCarList);

}