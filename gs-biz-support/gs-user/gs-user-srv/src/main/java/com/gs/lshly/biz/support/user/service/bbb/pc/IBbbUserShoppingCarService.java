package com.gs.lshly.biz.support.user.service.bbb.pc;
import com.gs.lshly.common.response.ResponseData;
import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.common.struct.bbb.pc.commodity.vo.PCBbbGoodsInfoVO;
import com.gs.lshly.common.struct.bbb.pc.user.dto.BbbUserShoppingCarDTO;
import com.gs.lshly.common.struct.bbb.pc.user.qto.BbbUserShoppingCarQTO;
import com.gs.lshly.common.struct.bbb.pc.user.vo.BbbUserShoppingCarVO;

import java.util.List;

public interface IBbbUserShoppingCarService {

    List<BbbUserShoppingCarVO.ListVO> list(BbbUserShoppingCarQTO.QTO qto);

    BbbUserShoppingCarVO.CountVO countShoppingCarGoods(BaseDTO dto);

    void addUserShoppingCar(BbbUserShoppingCarDTO.ETO eto);

    void addUserShoppingCar2(BbbUserShoppingCarDTO.AddShopingETO eto);

    void deleteBatchUserShoppingCar(BbbUserShoppingCarDTO.IdListDTO dto);

    BbbUserShoppingCarVO.ChangeQuantityVO changeQuantity(BbbUserShoppingCarDTO.QuantityDTO eto);

    PCBbbGoodsInfoVO.GetGoodsStepPriceVO getGoodsStepPrice(BbbUserShoppingCarDTO.QueryGradePriceDTO dto);

    void selectState(BbbUserShoppingCarDTO.SelectDTO eto);

    void selectStateAll(BbbUserShoppingCarDTO.SelectAllDTO eto);

    ResponseData<List<BbbUserShoppingCarVO.InnerListVO>> innerShoppingCarlist(BbbUserShoppingCarDTO.InnerIdListDTO dto);


    ResponseData<BbbUserShoppingCarVO.InnerSimpleVO> innerSimpleShoppingCarlist(BbbUserShoppingCarDTO.InnerIdListDTO dto);

    boolean innerClearShopCarList(List<String> shoppingCarList);
}