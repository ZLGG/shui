package com.gs.lshly.biz.support.user.rpc.bbc;
import java.util.List;

import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import com.gs.lshly.biz.support.user.service.bbc.IBbcUserShoppingCarService;
import com.gs.lshly.common.response.ResponseData;
import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.common.struct.bbc.user.dto.BbcUserShoppingCarDTO;
import com.gs.lshly.common.struct.bbc.user.dto.BbcUserShoppingCarDTO.IdListDTO;
import com.gs.lshly.common.struct.bbc.user.dto.BbcUserShoppingCarDTO.InnerIdListDTO;
import com.gs.lshly.common.struct.bbc.user.dto.BbcUserShoppingCarDTO.ModifySkuDTO;
import com.gs.lshly.common.struct.bbc.user.qto.BbcUserShoppingCarQTO;
import com.gs.lshly.common.struct.bbc.user.vo.BbcUserShoppingCarVO;
import com.gs.lshly.common.struct.bbc.user.vo.BbcUserShoppingCarVO.ShopSkuVO;
import com.gs.lshly.common.struct.bbc.user.vo.BbcUserShoppingCarVO.SummationVO;
import com.gs.lshly.rpc.api.bbc.user.IBbcUserShoppingCarRpc;

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
    public BbcUserShoppingCarVO.HomeVO list(BbcUserShoppingCarQTO.QTO qto){
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

	@Override
	public SummationVO summationUserShoppingCar(IdListDTO dto) {
		return bbcUserShoppingCarService.summationUserShoppingCar(dto);
	}

	@Override
	public List<ShopSkuVO> groupShopByCarId(InnerIdListDTO dto) {
		return bbcUserShoppingCarService.groupShopByCarId(dto);
	}

	@Override
	public void modifySku(ModifySkuDTO dto) {
		bbcUserShoppingCarService.modifySku(dto);
		
	}

}