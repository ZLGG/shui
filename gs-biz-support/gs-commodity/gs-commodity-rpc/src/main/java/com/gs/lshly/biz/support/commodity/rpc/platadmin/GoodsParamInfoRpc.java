package com.gs.lshly.biz.support.commodity.rpc.platadmin;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.platadmin.commodity.dto.GoodsParamInfoDTO;
import com.gs.lshly.common.struct.platadmin.commodity.qto.GoodsParamInfoQTO;
import com.gs.lshly.common.struct.platadmin.commodity.vo.GoodsParamInfoVO;
import com.gs.lshly.biz.support.commodity.service.platadmin.IGoodsParamInfoService;
import com.gs.lshly.rpc.api.platadmin.commodity.IGoodsParamInfoRpc;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

/**
*
* @author Starry
* @since 2020-09-29
*/
@DubboService
public class GoodsParamInfoRpc implements IGoodsParamInfoRpc{

    @Autowired
    private IGoodsParamInfoService goodsParamInfoService;

    @Override
    public PageData<GoodsParamInfoVO.ListVO> pageData(GoodsParamInfoQTO.QTO qto){
        return goodsParamInfoService.pageData(qto);
    }

    @Override
    public void addGoodsParamInfo(GoodsParamInfoDTO.ETO eto){
        goodsParamInfoService.addGoodsParamInfo(eto);
    }

    @Override
    public void deleteGoodsParamInfo(GoodsParamInfoDTO.IdDTO dto){
        goodsParamInfoService.deleteGoodsParamInfo(dto);
    }

    @Override
    public void editGoodsParamInfo(GoodsParamInfoDTO.ETO eto){
        goodsParamInfoService.editGoodsParamInfo(eto);
    }

    @Override
    public GoodsParamInfoVO.DetailVO detailGoodsParamInfo(GoodsParamInfoDTO.IdDTO dto){
        return goodsParamInfoService.detailGoodsParamInfo(dto);
    }

}