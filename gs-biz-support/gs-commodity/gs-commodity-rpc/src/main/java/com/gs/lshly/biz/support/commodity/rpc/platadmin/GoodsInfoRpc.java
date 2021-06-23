package com.gs.lshly.biz.support.commodity.rpc.platadmin;

import java.util.List;

import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.gs.lshly.biz.support.commodity.entity.GoodsInfo;
import com.gs.lshly.biz.support.commodity.service.merchadmin.pc.IPCMerchGoodsInfoService;
import com.gs.lshly.biz.support.commodity.service.merchadmin.pc.IPCMerchGoodsInfoTempService;
import com.gs.lshly.biz.support.commodity.service.platadmin.IGoodsFupinService;
import com.gs.lshly.biz.support.commodity.service.platadmin.IGoodsInfoService;
import com.gs.lshly.biz.support.commodity.service.platadmin.IGoodsInfoTempService;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.bb.commodity.qto.BbGoodsInfoQTO;
import com.gs.lshly.common.struct.platadmin.commodity.dto.GoodsInfoDTO;
import com.gs.lshly.common.struct.platadmin.commodity.qto.GoodsInfoQTO;
import com.gs.lshly.common.struct.platadmin.commodity.qto.GoodsInfoQTO.ListQTO;
import com.gs.lshly.common.struct.platadmin.commodity.vo.GoodsInfoVO;
import com.gs.lshly.common.struct.platadmin.commodity.vo.GoodsInfoVO.ListVO;
import com.gs.lshly.common.struct.platadmin.commodity.vo.GoodsInfoVO.SpuListVO;
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
        GoodsInfoDTO.IdDTO idDTO = new GoodsInfoDTO.IdDTO(dto.getId());
        idDTO.setId(dto.getId());
        GoodsInfoVO.DetailVO detailVO = goodsInfoTempService.getGoodsDetail(idDTO);
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

        GoodsInfo goodsInfo = ipcMerchGoodsInfoService.getGoodsInfo(dto.getId());

        //审核通过
        if (ObjectUtils.isNotEmpty(dto) && dto.getState().intValue() == 20) {
            //更新商品信息
            if (ObjectUtils.isNotEmpty(goodsInfo)) {
                ipcMerchGoodsInfoService.changeTempToGoodsInfo(dto.getId());
                goodsInfoService.checkGoods(dto,2,true);
                ipcMerchGoodsInfoTempService.updateGoodsInfoStateTemp(dto.getId(),40);
            } else {
                //新增商品信息
                ipcMerchGoodsInfoService.addTempToGoodsInfo(dto.getId());
                goodsInfoService.checkGoods(dto,1,true);
                ipcMerchGoodsInfoTempService.updateGoodsInfoStateTemp(dto.getId(),40);
            }

        }
        //审核不通过
        if (ObjectUtils.isNotEmpty(dto) && dto.getState().intValue() == 40) {
            goodsInfoService.checkGoods(dto,0,false);
            ipcMerchGoodsInfoTempService.updateGoodsInfoStateTemp(dto.getId(),40);
        }


        /*if(ipcMerchGoodsInfoTempService.isUpdateGoodInfo(dto.getId())){
            ipcMerchGoodsInfoService.changeTempToGoodsInfo(dto.getId());
            dto.setType(2);
            goodsInfoService.checkGoods(dto);
        }else{
            ipcMerchGoodsInfoService.addTempToGoodsInfo(dto.getId());
            dto.setType(1);
            goodsInfoService.checkGoods(dto);
        }*/
    }

    @Override
    public void checkGoodsBatches(GoodsInfoDTO.CheckGoodsBatchesDTO dto) {
        //todo 后面优化
        if(ObjectUtils.isNotEmpty(dto)&&ObjectUtils.isNotEmpty(dto.getIdList())){
            GoodsInfoDTO.CheckGoodsDTO checkGoodsDTO;
            for (String goodId : dto.getIdList()) {
                checkGoodsDTO = new GoodsInfoDTO.CheckGoodsDTO();
                checkGoodsDTO.setId(goodId);
                checkGoodsDTO.setRefuseRemark(dto.getRefuseRemark());
                checkGoodsDTO.setState(dto.getState());
                checkGoods(checkGoodsDTO);
            }
        }
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

	@Override
	public List<SpuListVO> listGoodsData(ListQTO qto) {
		return goodsInfoService.listGoodsData(qto);
	}

}
