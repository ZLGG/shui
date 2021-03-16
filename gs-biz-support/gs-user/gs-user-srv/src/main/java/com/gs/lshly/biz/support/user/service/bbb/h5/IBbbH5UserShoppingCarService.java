package com.gs.lshly.biz.support.user.service.bbb.h5;
import com.gs.lshly.common.response.ResponseData;
import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.common.struct.bbb.h5.user.dto.BbbH5UserShoppingCarDTO;
import com.gs.lshly.common.struct.bbb.h5.user.qto.BbbH5UserShoppingCarQTO;
import com.gs.lshly.common.struct.bbb.h5.user.vo.BbbH5UserShoppingCarVO;
import java.util.List;

public interface IBbbH5UserShoppingCarService {

    List<BbbH5UserShoppingCarVO.ListVO> list(BbbH5UserShoppingCarQTO.QTO qto);

    BbbH5UserShoppingCarVO.CountVO countShoppingCarGoods(BaseDTO dto);

    void addUserShoppingCar(BbbH5UserShoppingCarDTO.ETO eto);

    void deleteBatchUserShoppingCar(BbbH5UserShoppingCarDTO.IdListDTO dto);

    void changeQuantity(BbbH5UserShoppingCarDTO.QuantityDTO eto);

    void selectState(BbbH5UserShoppingCarDTO.SelectDTO eto);

    void selectStateAll(BbbH5UserShoppingCarDTO.SelectAllDTO eto);

    ResponseData<List<BbbH5UserShoppingCarVO.InnerListVO>> innerShoppingCarlist(BbbH5UserShoppingCarDTO.InnerIdListDTO dto);


    ResponseData<BbbH5UserShoppingCarVO.InnerSimpleVO> innerSimpleShoppingCarlist(BbbH5UserShoppingCarDTO.InnerIdListDTO dto);

    boolean innerClearShopCarList(List<String> shoppingCarList);
}