package com.gs.lshly.biz.support.commodity.rpc.platadmin;

import java.util.List;

import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.gs.lshly.biz.support.commodity.service.platadmin.IGoodsFupinService;
import com.gs.lshly.biz.support.commodity.service.platadmin.IGoodsInfoService;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.bb.commodity.qto.BbGoodsInfoQTO;
import com.gs.lshly.common.struct.platadmin.commodity.dto.GoodsInfoDTO;
import com.gs.lshly.common.struct.platadmin.commodity.qto.GoodsInfoQTO;
import com.gs.lshly.common.struct.platadmin.commodity.vo.GoodsInfoVO;
import com.gs.lshly.common.struct.platadmin.commodity.vo.GoodsInfoVO.ListVO;
import com.gs.lshly.common.struct.platadmin.commodityfupin.qto.GoodsFupinQTO;
import com.gs.lshly.common.struct.platadmin.commodityfupin.vo.GoodsFupinVO;
import com.gs.lshly.rpc.api.platadmin.commodity.IGoodsInfoRpc;

/**
 * @Author Starry
 * @Date 17:12 2020/10/14
 */
@DubboService
public class GoodsInfoRpc implements IGoodsInfoRpc {
    @Autowired
    private IGoodsInfoService goodsInfoService;
    @Autowired
    private IGoodsFupinService fupinService;

    @Override
    public PageData<GoodsInfoVO.SpuListVO> pageGoodsData(GoodsInfoQTO.QTO qto) {
        return goodsInfoService.pageGoodsInfoData(qto);
    }

    @Override
    public GoodsInfoVO.DetailVO getGoodsDetail(GoodsInfoDTO.IdDTO dto) {
        GoodsInfoVO.DetailVO detailVO = goodsInfoService.getGoodsDetail(dto);

        //商品扶贫信息
        GoodsFupinQTO.QTO qto = new GoodsFupinQTO.QTO();
        qto.setGoodsId(dto.getId());
        GoodsFupinVO.DetailVO fupin = fupinService.detailGoodsFupin(qto);
        if (ObjectUtils.isNotEmpty(fupin)){
            detailVO.setFupinInfo(fupin);
        }
        return detailVO;
    }

    @Override
    public void upCarriageGoods(GoodsInfoDTO.IdListDTO dto) {
        goodsInfoService.upCarriageGoods(dto);
    }

    @Override
    public void underCarriageGoods(GoodsInfoDTO.IdListDTO dto) {
        goodsInfoService.underCarriageGoods(dto);
    }


    @Override
    public void deleteGoodsBatches(GoodsInfoDTO.IdListDTO dto) {
        goodsInfoService.deleteGoodsBatches(dto);
    }

    @Override
    public void checkGoods(GoodsInfoDTO.CheckGoodsDTO dto) {
        goodsInfoService.checkGoods(dto);
    }

    @Override
    public void checkGoodsBatches(GoodsInfoDTO.CheckGoodsBatchesDTO dto) {
        goodsInfoService.checkGoodsBatches(dto);
    }

    @Override
    public PageData<GoodsInfoVO.ShopFloorCommodityVO> getShopFloorCommodityVO(GoodsInfoQTO.ShopFloorQTO qto) {
        return goodsInfoService.getShopFloorCommodityVO(qto);
    }

    @Override
    public PageData<GoodsInfoVO.FupinFloorCommodityVO> getFupinFloorCommodityVO(GoodsInfoQTO.FupinFloorQTO qto) {
        List<String> goodsIdList = fupinService.listFuPinGoodsId(qto);
        if (ObjectUtils.isEmpty(goodsIdList)){
            return new PageData<>();
        }
        qto.setGoodsId(goodsIdList);
        return goodsInfoService.getFupinFloorCommodityVO(qto);
    }

    @Override
    public PageData<GoodsInfoVO.BindCategoryGoodsVO> getBindCategoryGoodsVO(GoodsInfoQTO.CategoryIdQTO qto) {
        return goodsInfoService.getBindCategoryGoodsVO(qto);
    }

    @Override
    public List<GoodsInfoVO.InnerServiceGoodsVO> innerServiceGoodsVO(GoodsInfoDTO.IdsInnerServiceDTO dto) {
        return goodsInfoService.innerServiceGoodsVO(dto);
    }

    @Override
    public List<GoodsInfoVO.ListVO> innerServiceSpuGoodsInfo(GoodsInfoDTO.IdsInnerServiceDTO dto) {
        return goodsInfoService.innerServiceSpuGoodsInfo(dto);
    }

    @Override
    public List<GoodsInfoVO.InnerServiceGoodsInfoVO> innerServiceGoodsInfo(GoodsInfoDTO.IdsInnerServiceDTO dto) {
        return goodsInfoService.innerServiceGoodsInfo(dto);
    }

    @Override
    public List<GoodsInfoVO.InnerServiceGoodsVO> innerServiceAllGoodsVO(GoodsInfoDTO.IdsInnerServiceDTO dto) {
        return goodsInfoService.innerServiceAllGoodsVO(dto);
    }

    @Override
    public List<GoodsInfoVO.ListVO> innerServiceAllSpuGoodsInfo(GoodsInfoDTO.IdsInnerServiceDTO dto) {
        return goodsInfoService.innerServiceAllSpuGoodsInfo(dto);
    }

    @Override
    public List<GoodsInfoVO.InnerServiceGoodsInfoVO> innerServiceAllGoodsInfo(GoodsInfoDTO.IdsInnerServiceDTO dto) {
        return goodsInfoService.innerServiceAllGoodsInfo(dto);
    }

    @Override
    public void innerServiceUnderShelfGoods(List<String> shopId) {
        goodsInfoService.innerServiceUnderShelfGoods(shopId);
    }

	@Override
	public List<ListVO> listGoodsData() {
		return goodsInfoService.listGoodsData();
	}

	@Override
	public PageData<ListVO> pageInGoods(BbGoodsInfoQTO.QTO qto) {
		return goodsInfoService.pageInGoods(qto);
	}

}
