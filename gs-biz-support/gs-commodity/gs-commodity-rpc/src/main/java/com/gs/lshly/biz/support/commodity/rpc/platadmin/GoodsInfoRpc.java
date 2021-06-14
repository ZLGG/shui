package com.gs.lshly.biz.support.commodity.rpc.platadmin;

import java.util.List;

import com.gs.lshly.biz.support.commodity.service.platadmin.IGoodsInfoTempService;
import com.gs.lshly.common.struct.merchadmin.pc.commodity.dto.PCMerchGoodsInfoDTO;
import com.gs.lshly.common.struct.merchadmin.pc.commodity.vo.PCMerchGoodsInfoVO;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.qto.PCMerchMarketPtSeckillQTO;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.vo.PCMerchMarketPtSeckillVO;
import com.gs.lshly.biz.support.commodity.service.merchadmin.pc.IPCMerchGoodsInfoService;
import com.gs.lshly.biz.support.commodity.service.merchadmin.pc.IPCMerchGoodsInfoTempService;
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
import org.springframework.transaction.annotation.Transactional;

/**
 * @Author Starry
 * @Date 17:12 2020/10/14
 */
@DubboService
public class GoodsInfoRpc implements IGoodsInfoRpc {
    @Autowired
    private IGoodsInfoService goodsInfoService;
    @Autowired
    private IGoodsInfoTempService goodsInfoTempService;
    @Autowired
    private IGoodsFupinService fupinService;
    @Autowired
    private IPCMerchGoodsInfoService ipcMerchGoodsInfoService;
    @Autowired
    private IPCMerchGoodsInfoTempService ipcMerchGoodsInfoTempService;

    @Override
    public PageData<GoodsInfoVO.SpuListVO> pageGoodsData(GoodsInfoQTO.QTO qto) {
        return goodsInfoService.pageGoodsInfoData(qto);
    }

    @Override
    public GoodsInfoVO.DetailVO getGoodsDetail(GoodsInfoDTO.IdDTO dto) {

        //
        GoodsInfoVO.DetailVO detailVO;
        if(goodsInfoTempService.isUpdateGoodInfo(dto.getId())){
            GoodsInfoDTO.IdDTO  idDTO = new GoodsInfoDTO.IdDTO(dto.getId());
            idDTO.setId(dto.getId());
            detailVO = goodsInfoTempService.getGoodsDetail(idDTO);
        }else {
            detailVO = goodsInfoService.getGoodsDetail(dto);
        }
        //商品扶贫信息
        /*GoodsFupinQTO.QTO qto = new GoodsFupinQTO.QTO();
        qto.setGoodsId(dto.getId());
        GoodsFupinVO.DetailVO fupin = fupinService.detailGoodsFupin(qto);
        if (ObjectUtils.isNotEmpty(fupin)) {
            detailVO.setFupinInfo(fupin);
        }*/
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
    @Transactional(rollbackFor = Exception.class)
    public void checkGoods(GoodsInfoDTO.CheckGoodsDTO dto) {
        //区分新增与更新
        if(ipcMerchGoodsInfoTempService.isUpdateGoodInfo(dto.getId())){
            ipcMerchGoodsInfoService.changeTempToGoodsInfo(dto.getId());
            dto.setType(2);
            goodsInfoService.checkGoods(dto);
        }else{
            ipcMerchGoodsInfoService.addTempToGoodsInfo(dto.getId());
            dto.setType(1);
            goodsInfoService.checkGoods(dto);
        }
    }

    @Override
    public void checkGoodsBatches(GoodsInfoDTO.CheckGoodsBatchesDTO dto) {
        //todo 后面优化
        for (String goodsId : dto.getIdList()) {
            if(ipcMerchGoodsInfoTempService.isUpdateGoodInfo(goodsId)){
                ipcMerchGoodsInfoService.changeTempToGoodsInfo(goodsId);
            }
        }
        goodsInfoService.checkGoodsBatches(dto);
    }

    @Override
    public PageData<GoodsInfoVO.ShopFloorCommodityVO> getShopFloorCommodityVO(GoodsInfoQTO.ShopFloorQTO qto) {
        return goodsInfoService.getShopFloorCommodityVO(qto);
    }

    @Override
    public PageData<GoodsInfoVO.FupinFloorCommodityVO> getFupinFloorCommodityVO(GoodsInfoQTO.FupinFloorQTO qto) {
        List<String> goodsIdList = fupinService.listFuPinGoodsId(qto);
        if (ObjectUtils.isEmpty(goodsIdList)) {
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
