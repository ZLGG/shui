package com.gs.lshly.biz.support.trade.rpc.platadmin;

import java.util.List;

import com.gs.lshly.biz.support.trade.service.platadmin.IMarketPtSeckillTimeQuantumService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import com.gs.lshly.biz.support.trade.service.platadmin.IMarketPtSeckillService;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.platadmin.trade.dto.MarketPtSeckillDTO;
import com.gs.lshly.common.struct.platadmin.trade.qto.MarketPtSeckillQTO;
import com.gs.lshly.common.struct.platadmin.trade.vo.MarketPtSeckillVO;
import com.gs.lshly.rpc.api.platadmin.trade.IMarketPtSeckillRpc;

/**
 * 秒杀活动
 *
 * @author yingjun
 * @date 2021年5月7日 上午10:50:13
 */
@DubboService
public class MarketPtSeckillRpc implements IMarketPtSeckillRpc {

    @Autowired
    private IMarketPtSeckillService marketPtSeckillService;
    @Autowired
    private IMarketPtSeckillTimeQuantumService marketPtSeckillTimeQantumService;

    @Override
    public PageData<MarketPtSeckillVO.activityListVO> pageData(MarketPtSeckillQTO.QTO qto) {
        return marketPtSeckillService.pageData(qto);
    }


    @Override
    public void addMarketPtSeckill(MarketPtSeckillDTO.ETO eto) {
        marketPtSeckillService.addMarketPtSeckill(eto);
    }

    @Override
    public void deleteMarketPtSeckill(MarketPtSeckillDTO.IdListDTO dto) {
        marketPtSeckillService.deleteMarketPtSeckill(dto);
    }


    @Override
    public void editMarketPtSeckill(MarketPtSeckillDTO.ETO eto) {
        marketPtSeckillService.editMarketPtSeckill(eto);
    }

    @Override
    public MarketPtSeckillVO.activityListVO detailMarketPtSeckill(MarketPtSeckillDTO.IdDTO dto) {
        return marketPtSeckillService.detailMarketPtSeckill(dto);
    }

    @Override
    public List<MarketPtSeckillVO.ListVO> list() {
        return marketPtSeckillService.list();
    }
}