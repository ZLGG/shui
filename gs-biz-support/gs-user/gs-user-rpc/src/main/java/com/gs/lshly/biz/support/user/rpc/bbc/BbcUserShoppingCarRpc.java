package com.gs.lshly.biz.support.user.rpc.bbc;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.response.ResponseData;
import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.common.struct.bbc.user.dto.BbcUserShoppingCarDTO;
import com.gs.lshly.common.struct.bbc.user.qto.BbcUserShoppingCarQTO;
import com.gs.lshly.common.struct.bbc.user.vo.BbcUserShoppingCarVO;
import com.gs.lshly.rpc.api.bbc.user.IBbcUserShoppingCarRpc;
import com.gs.lshly.biz.support.user.service.bbc.IBbcUserShoppingCarService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
*
* @author xxfc
* @since 2020-10-28
*/
@DubboService
public class BbcUserShoppingCarRpc implements IBbcUserShoppingCarRpc{

    @Autowired
    private IBbcUserShoppingCarService  bbcUserShoppingCarService;

    @Override
    public List<BbcUserShoppingCarVO.ListVO> list(BbcUserShoppingCarQTO.QTO qto){
        return bbcUserShoppingCarService.list(qto);
    }

    @Override
    public BbcUserShoppingCarVO.CountVO countShoppingCarGoods(BaseDTO dto) {
        return bbcUserShoppingCarService.countShoppingCarGoods(dto);
    }

    @Override
    public void addUserShoppingCar(BbcUserShoppingCarDTO.ETO eto){
        bbcUserShoppingCarService.addUserShoppingCar(eto);
    }

    @Override
    public void deleteBatchUserShoppingCar(BbcUserShoppingCarDTO.IdListDTO dto){
        bbcUserShoppingCarService.deleteBatchUserShoppingCar(dto);
    }

    @Override
    public void selectState(BbcUserShoppingCarDTO.SelectDTO eto) {
        bbcUserShoppingCarService.selectState(eto);
    }

    @Override
    public void selectStateAll(BbcUserShoppingCarDTO.SelectAllDTO eto) {
        bbcUserShoppingCarService.selectStateAll(eto);
    }

    @Override
    public void changeQuantity(BbcUserShoppingCarDTO.QuantityDTO eto) {
        bbcUserShoppingCarService.changeQuantity(eto);
    }

    @Override
    public ResponseData<List<BbcUserShoppingCarVO.InnerListVO>> innerShoppingCarlist(BbcUserShoppingCarDTO.InnerIdListDTO dto) {
        return bbcUserShoppingCarService.innerShoppingCarlist(dto);
    }

    @Override
    public ResponseData<BbcUserShoppingCarVO.InnerSimpleVO> innerSimpleShoppingCarlist(BbcUserShoppingCarDTO.InnerIdListDTO dto) {
        return bbcUserShoppingCarService.innerSimpleShoppingCarlist(dto);
    }

    @Override
    public boolean innerClearShopCarList(List<String> shoppingCarList) {
        return bbcUserShoppingCarService.innerClearShopCarList(shoppingCarList);
    }

}