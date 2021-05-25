package com.gs.lshly.biz.support.commodity.rpc.bbc;
import java.util.ArrayList;
import java.util.List;

import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.gs.lshly.biz.support.commodity.service.bbc.IBbcGoodsInfoService;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.bbc.commodity.dto.BbcGoodsInfoDTO;
import com.gs.lshly.common.struct.bbc.commodity.dto.BbcGoodsInfoDTO.CategoryIdCountDTO;
import com.gs.lshly.common.struct.bbc.commodity.dto.BbcGoodsInfoDTO.IdDTO;
import com.gs.lshly.common.struct.bbc.commodity.qto.BbcGoodsInfoQTO;
import com.gs.lshly.common.struct.bbc.commodity.qto.BbcGoodsInfoQTO.InMemberGoodsQTO;
import com.gs.lshly.common.struct.bbc.commodity.qto.BbcGoodsInfoQTO.SkuIdListQTO;
import com.gs.lshly.common.struct.bbc.commodity.vo.BbcGoodsInfoVO;
import com.gs.lshly.common.struct.bbc.commodity.vo.BbcGoodsInfoVO.InMemberHomeVO;
import com.gs.lshly.common.struct.bbc.commodity.vo.BbcGoodsInfoVO.InnerServiceVO;
import com.gs.lshly.common.struct.bbc.commodity.vo.BbcGoodsInfoVO.ListVO;
import com.gs.lshly.common.struct.bbc.commodity.vo.BbcGoodsInfoVO.SimpleListVO;
import com.gs.lshly.common.struct.bbc.commodity.vo.BbcSkuGoodInfoVO;
import com.gs.lshly.rpc.api.bbc.commodity.IBbcGoodsInfoRpc;

/**
*
* @author Starry
* @since 2020-10-23
*/
@DubboService
public class BbcGoodsInfoRpc implements IBbcGoodsInfoRpc{
    
	
	@Autowired
    private IBbcGoodsInfoService  bbcGoodsInfoService;




    @Override
    public PageData<BbcGoodsInfoVO.GoodsListVO> pageGoodsListVO(BbcGoodsInfoQTO.GoodsListByCategoryQTO qto) {
        return bbcGoodsInfoService.pageGoodsListVO(qto);
    }

    @Override
    public PageData<BbcGoodsInfoVO.GoodsListVO> pageMerchantGoods(BbcGoodsInfoQTO.MerchantGoodsQTO qto) {
        return bbcGoodsInfoService.pageMerchantGoods(qto);
    }

    @Override
    public BbcGoodsInfoVO.DetailVO detailGoodsInfo(BbcGoodsInfoDTO.IdDTO dto){
        return  bbcGoodsInfoService.detailGoodsInfo(dto);
    }

    @Override
    public PageData<BbcGoodsInfoVO.GoodsListVO> pageGoodsData(BbcGoodsInfoQTO.GoodsSearchListQTO qto) {
        return bbcGoodsInfoService.pageGoodsData(qto);
    }

    @Override
    public BbcSkuGoodInfoVO.SkuVO getSkuVO(BbcGoodsInfoQTO.GoodsSkuQTO qto) {
        return bbcGoodsInfoService.getSkuVO(qto);
    }

    @Override
    public PageData<BbcGoodsInfoVO.GoodsListVO> getRecommendGoodsList(BbcGoodsInfoQTO.OrderGoodsListQTO qto) {
        return bbcGoodsInfoService.getRecommendGoodsList(qto);
    }

    @Override
    public BbcGoodsInfoVO.GoodsSharingVO getGoodsSharingVO(BbcGoodsInfoQTO.GoodsSharingQTO qto) {
        return bbcGoodsInfoService.getGoodsSharingVO(qto);
    }


    //-------------------------内部服务---------------------------------

    @Override
    public List<BbcGoodsInfoVO.InnerServiceVO> innerServicePageShopGoods(BbcGoodsInfoQTO.SkuIdListQTO qto) {
        return bbcGoodsInfoService.innerServicePageShopGoods(qto);
    }

    @Override
    public BbcGoodsInfoVO.InnerServiceVO innerServiceGoodsVO(String skuID) {

        BbcGoodsInfoQTO.SkuIdListQTO skuIdListQTO = new BbcGoodsInfoQTO.SkuIdListQTO();
        skuIdListQTO.setSkuIdList(new ArrayList<>());
        skuIdListQTO.getSkuIdList().add(skuID);
        List<BbcGoodsInfoVO.InnerServiceVO> innerServiceDataList = bbcGoodsInfoService.innerServicePageShopGoods(skuIdListQTO);

        return ObjectUtils.isNotEmpty(innerServiceDataList) ? innerServiceDataList.get(0) : null;
    }

    @Override
    public BbcGoodsInfoVO.InnerServiceVO innerSimpleServiceGoodsVO(String skuID) {
        return bbcGoodsInfoService.innerSimpleServiceGoodsVO(skuID);
    }


    @Override
    public List<BbcGoodsInfoVO.HomeAndShopInnerServiceVO> getInnerServiceVO(BbcGoodsInfoQTO.GoodsIdListQTO qto) {
        return bbcGoodsInfoService.getGoodsInnerServiceVO(qto);
    }

    @Override
    public List<BbcGoodsInfoVO.HomeAndShopInnerServiceVO> getInnerSimpleServiceVO(List<String> goodsIds) {
        return bbcGoodsInfoService.getInnerSimpleServiceVO(goodsIds);
    }

	@Override
	public BbcGoodsInfoVO.InMemberGoodsVO inMemberGoodsHome(InMemberGoodsQTO qto) {
		return bbcGoodsInfoService.inMemberGoodsHome(qto);
	}

    @Override
    public List<BbcGoodsInfoVO.SearchHistory> getSearchHistory(BbcGoodsInfoQTO.SearchHistoryQTO qto) {
        return bbcGoodsInfoService.getSearchHistory(qto);
    }

    @Override
    public void emptySearchHistory(BbcGoodsInfoQTO.SearchHistoryQTO qto) {
        bbcGoodsInfoService.emptySearchHistory(qto);
    }

    @Override
    public List<BbcGoodsInfoVO.MyIntegrationExchangeVO> myIntegrationExchange() {
        return bbcGoodsInfoService.myIntegrationExchange();
    }

    @Override
    public PageData<BbcGoodsInfoVO.IntegralGoodsInfo> queryIntegralGoodsInfo(BbcGoodsInfoQTO.IntegralGoodsQTO qto) {
        return bbcGoodsInfoService.queryIntegralGoodsInfo(qto);
    }

    @Override
    public PageData<BbcGoodsInfoVO.InVIPSpecialAreaVO> queryInVIPSpecialAreaList(BbcGoodsInfoQTO.InSpecialAreaGoodsQTO qto) {
        return bbcGoodsInfoService.queryInVIPSpecialAreaList(qto);
    }

	@Override
	public PageData<BbcGoodsInfoVO.InVipListVO> pageInMemberGoodsInfo(InMemberGoodsQTO qto) {
		return bbcGoodsInfoService.pageInMemberGoodsInfo(qto);
	}

	@Override
	public List<SimpleListVO> listGoodsInfoByCategory(CategoryIdCountDTO dto) {
		return bbcGoodsInfoService.listGoodsInfoByCategory(dto);
	}

	@Override
	public SimpleListVO simpleListVO(IdDTO dto) {
		return bbcGoodsInfoService.simpleListVO(dto);
	}

    @Override
    public List<BbcGoodsInfoVO.GoodsListVO> getGeneralGoodsInfo() {
        return bbcGoodsInfoService.getGeneralGoodsInfo();
    }

    @Override
    public List<String> getCategoryIdsByName(String goodsName) {
        return bbcGoodsInfoService.getCategoryIdsByName(goodsName);
    }

    @Override
    public List<BbcGoodsInfoVO.GoodsListVO> getShopRecommendGoods(BbcGoodsInfoQTO.ShopGoodsIdQTO qto) {
        return bbcGoodsInfoService.getShopRecommendGoods(qto);
    }

    @Override
	public InMemberHomeVO inMemberHome() {
		return bbcGoodsInfoService.inMemberHome();
	}

	@Override
	public List<InnerServiceVO> innerServiceShopGoods(SkuIdListQTO qto) {
		return bbcGoodsInfoService.innerServiceShopGoods(qto);
	}

}