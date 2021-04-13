package com.gs.lshly.biz.support.user.service.bbc;
import com.gs.lshly.common.response.ResponseData;
import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.common.struct.bbc.user.dto.BbcUserShoppingCarDTO;
import com.gs.lshly.common.struct.bbc.user.qto.BbcUserShoppingCarQTO;
import com.gs.lshly.common.struct.bbc.user.vo.BbcUserShoppingCarVO;
import java.util.List;

public interface IBbcUserShoppingCarService {

	BbcUserShoppingCarVO.HomeVO list(BbcUserShoppingCarQTO.QTO qto);

    BbcUserShoppingCarVO.CountVO countShoppingCarGoods(BaseDTO dto);

    void addUserShoppingCar(BbcUserShoppingCarDTO.ETO eto);

    void deleteBatchUserShoppingCar(BbcUserShoppingCarDTO.IdListDTO dto);

    void changeQuantity(BbcUserShoppingCarDTO.QuantityDTO eto);

    void selectState(BbcUserShoppingCarDTO.SelectDTO eto);

    void selectStateAll(BbcUserShoppingCarDTO.SelectAllDTO eto);

    ResponseData<List<BbcUserShoppingCarVO.InnerListVO>> innerShoppingCarlist(BbcUserShoppingCarDTO.InnerIdListDTO dto);


    ResponseData<BbcUserShoppingCarVO.InnerSimpleVO> innerSimpleShoppingCarlist(BbcUserShoppingCarDTO.InnerIdListDTO dto);

    boolean innerClearShopCarList(List<String> shoppingCarList);
}