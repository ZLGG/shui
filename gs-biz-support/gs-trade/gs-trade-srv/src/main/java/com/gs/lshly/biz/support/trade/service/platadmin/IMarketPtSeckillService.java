package com.gs.lshly.biz.support.trade.service.platadmin;
import java.util.List;

import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.platadmin.trade.dto.MarketPtSeckillDTO;
import com.gs.lshly.common.struct.platadmin.trade.qto.MarketPtSeckillQTO;
import com.gs.lshly.common.struct.platadmin.trade.vo.MarketPtSeckillVO;

/**
 * 
 *
 * 
 * @author yingjun
 * @date 2021年5月7日 上午10:54:42
 */
public interface IMarketPtSeckillService {

    PageData<MarketPtSeckillVO.ActivityListVO> pageData(MarketPtSeckillQTO.QTO qto);

    void addMarketPtSeckill(MarketPtSeckillDTO.ETO eto);

    MarketPtSeckillVO.ActivityVO detailMarketPtSeckill(MarketPtSeckillQTO.IdQTO qto);

    PageData<MarketPtSeckillVO.KillGoodsVO> seckillGoods(MarketPtSeckillQTO.GoodsQTO qto);

    void saveKillGoods(MarketPtSeckillDTO.SeckillGoodsDTO qto);

    List<MarketPtSeckillVO.KillSkuGoods> seckillSkuGoods(MarketPtSeckillQTO.SkuGoodsQTO qto);

    PageData<MarketPtSeckillVO.QuantumSessionVO> getKillQuanTum(MarketPtSeckillQTO.QuantumQTO dto);
/*
    void deleteMarketPtSeckill(MarketPtSeckillDTO.IdListDTO dto);


    void editMarketPtSeckill(MarketPtSeckillDTO.ETO eto);



    List<MarketPtSeckillVO.ListVO> list();*/

}