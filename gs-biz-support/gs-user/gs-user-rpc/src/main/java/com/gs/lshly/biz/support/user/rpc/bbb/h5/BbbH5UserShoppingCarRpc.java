package com.gs.lshly.biz.support.user.rpc.bbb.h5;
import com.gs.lshly.biz.support.user.service.bbb.h5.IBbbH5UserShoppingCarService;
import com.gs.lshly.common.response.ResponseData;
import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.common.struct.bbb.h5.user.dto.BbbH5UserShoppingCarDTO;
import com.gs.lshly.common.struct.bbb.h5.user.qto.BbbH5UserShoppingCarQTO;
import com.gs.lshly.common.struct.bbb.h5.user.vo.BbbH5UserShoppingCarVO;
import com.gs.lshly.rpc.api.bbb.h5.user.IBbbH5UserShoppingCarRpc;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
*
* @author xxfc
* @since 2020-10-28
*/
@DubboService
public class BbbH5UserShoppingCarRpc implements IBbbH5UserShoppingCarRpc {

    @Autowired
    private IBbbH5UserShoppingCarService bbbH5UserShoppingCarService;

    @Override
    public List<BbbH5UserShoppingCarVO.ListVO> list(BbbH5UserShoppingCarQTO.QTO qto){
        return bbbH5UserShoppingCarService.list(qto);
    }

    @Override
    public BbbH5UserShoppingCarVO.CountVO countShoppingCarGoods(BaseDTO dto) {
        return bbbH5UserShoppingCarService.countShoppingCarGoods(dto);
    }

    @Override
    public void addUserShoppingCar(BbbH5UserShoppingCarDTO.ETO eto){
        bbbH5UserShoppingCarService.addUserShoppingCar(eto);
    }

    @Override
    public void deleteBatchUserShoppingCar(BbbH5UserShoppingCarDTO.IdListDTO dto){
        bbbH5UserShoppingCarService.deleteBatchUserShoppingCar(dto);
    }

    @Override
    public void selectState(BbbH5UserShoppingCarDTO.SelectDTO eto) {
        bbbH5UserShoppingCarService.selectState(eto);
    }

    @Override
    public void selectStateAll(BbbH5UserShoppingCarDTO.SelectAllDTO eto) {
        bbbH5UserShoppingCarService.selectStateAll(eto);
    }

    @Override
    public void changeQuantity(BbbH5UserShoppingCarDTO.QuantityDTO eto) {
        bbbH5UserShoppingCarService.changeQuantity(eto);
    }

    @Override
    public ResponseData<List<BbbH5UserShoppingCarVO.InnerListVO>> innerShoppingCarlist(BbbH5UserShoppingCarDTO.InnerIdListDTO dto) {
        return bbbH5UserShoppingCarService.innerShoppingCarlist(dto);
    }

    @Override
    public ResponseData<BbbH5UserShoppingCarVO.InnerSimpleVO> innerSimpleShoppingCarlist(BbbH5UserShoppingCarDTO.InnerIdListDTO dto) {
        return bbbH5UserShoppingCarService.innerSimpleShoppingCarlist(dto);
    }

    @Override
    public boolean innerClearShopCarList(List<String> shoppingCarList) {
        return bbbH5UserShoppingCarService.innerClearShopCarList(shoppingCarList);
    }

}