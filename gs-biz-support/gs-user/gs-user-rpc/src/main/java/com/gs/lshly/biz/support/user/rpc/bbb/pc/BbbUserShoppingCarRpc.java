package com.gs.lshly.biz.support.user.rpc.bbb.pc;
import com.gs.lshly.biz.support.user.service.bbb.pc.IBbbUserShoppingCarService;
import com.gs.lshly.common.response.ResponseData;
import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.common.struct.bbb.pc.commodity.vo.PCBbbGoodsInfoVO;
import com.gs.lshly.common.struct.bbb.pc.user.dto.BbbUserShoppingCarDTO;
import com.gs.lshly.common.struct.bbb.pc.user.qto.BbbUserShoppingCarQTO;
import com.gs.lshly.common.struct.bbb.pc.user.vo.BbbUserShoppingCarVO;
import com.gs.lshly.rpc.api.bbb.pc.user.IBbbUserShoppingCarRpc;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
*
* @author xxfc
* @since 2020-10-28
*/
@DubboService
public class BbbUserShoppingCarRpc implements IBbbUserShoppingCarRpc {

    @Autowired
    private IBbbUserShoppingCarService bbbUserShoppingCarService;

    @Override
    public List<BbbUserShoppingCarVO.ListVO> list(BbbUserShoppingCarQTO.QTO qto){
        return bbbUserShoppingCarService.list(qto);
    }

    @Override
    public BbbUserShoppingCarVO.CountVO countShoppingCarGoods(BaseDTO dto) {
        return bbbUserShoppingCarService.countShoppingCarGoods(dto);
    }

    @Override
    public void addUserShoppingCar(BbbUserShoppingCarDTO.ETO eto){
        bbbUserShoppingCarService.addUserShoppingCar(eto);
    }

    @Override
    public void addUserShoppingCar2(BbbUserShoppingCarDTO.AddShopingETO eto) {
        bbbUserShoppingCarService.addUserShoppingCar2(eto);
    }

    @Override
    public void deleteBatchUserShoppingCar(BbbUserShoppingCarDTO.IdListDTO dto){
        bbbUserShoppingCarService.deleteBatchUserShoppingCar(dto);
    }

    @Override
    public void selectState(BbbUserShoppingCarDTO.SelectDTO eto) {
        bbbUserShoppingCarService.selectState(eto);
    }

    @Override
    public void selectStateAll(BbbUserShoppingCarDTO.SelectAllDTO eto) {
        bbbUserShoppingCarService.selectStateAll(eto);
    }

    @Override
    public PCBbbGoodsInfoVO.GetGoodsStepPriceVO getGoodsStepPrice(BbbUserShoppingCarDTO.QueryGradePriceDTO dto) {
        return bbbUserShoppingCarService.getGoodsStepPrice(dto);
    }

    @Override
    public BbbUserShoppingCarVO.ChangeQuantityVO changeQuantity(BbbUserShoppingCarDTO.QuantityDTO eto) {
        return  bbbUserShoppingCarService.changeQuantity(eto);
    }

    @Override
    public ResponseData<List<BbbUserShoppingCarVO.InnerListVO>> innerShoppingCarlist(BbbUserShoppingCarDTO.InnerIdListDTO dto) {
        return bbbUserShoppingCarService.innerShoppingCarlist(dto);
    }

    @Override
    public ResponseData<BbbUserShoppingCarVO.InnerSimpleVO> innerSimpleShoppingCarlist(BbbUserShoppingCarDTO.InnerIdListDTO dto) {
        return bbbUserShoppingCarService.innerSimpleShoppingCarlist(dto);
    }

    @Override
    public boolean innerClearShopCarList(List<String> shoppingCarList) {
        return bbbUserShoppingCarService.innerClearShopCarList(shoppingCarList);
    }

}