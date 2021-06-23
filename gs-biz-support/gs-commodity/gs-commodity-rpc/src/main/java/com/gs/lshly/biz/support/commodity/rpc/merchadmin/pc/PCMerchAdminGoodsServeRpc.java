package com.gs.lshly.biz.support.commodity.rpc.merchadmin.pc;

import com.gs.lshly.biz.support.commodity.service.merchadmin.pc.IPCMerchGoodsServeService;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.merchadmin.pc.commodity.dto.PCMerchGoodsServeDTO;
import com.gs.lshly.common.struct.merchadmin.pc.commodity.dto.PCMerchGoodsServeDTO.IdDTO;
import com.gs.lshly.common.struct.merchadmin.pc.commodity.qto.PCMerchGoodsServeQTO;
import com.gs.lshly.common.struct.merchadmin.pc.commodity.vo.PCMerchGoodsServeVO;
import com.gs.lshly.common.struct.platadmin.commodity.dto.GoodsServeDTO;
import com.gs.lshly.common.struct.platadmin.commodity.qto.GoodsServeQTO;
import com.gs.lshly.common.struct.platadmin.commodity.vo.GoodsServeVO;
import com.gs.lshly.rpc.api.merchadmin.pc.commodity.IPCMerchGoodsServeRpc;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@DubboService
public class PCMerchAdminGoodsServeRpc implements IPCMerchGoodsServeRpc {

    @Autowired
    private IPCMerchGoodsServeService goodsServeService;

    @Override
    public PageData<PCMerchGoodsServeVO.ListVO> pageGoodsServeData(PCMerchGoodsServeQTO.QTO qto) {
        return goodsServeService.pageGoodsServeData(qto);
    }

    @Override
    public List<PCMerchGoodsServeVO.ListVO> getGoodsServeDetailByGoodsId(PCMerchGoodsServeDTO.IdDTO dto) {
        return goodsServeService.getGoodsServeDetail(dto);
    }

    @Override
    public List<PCMerchGoodsServeVO.ListVO> getGoodsServeDetailTempByGoodsId(PCMerchGoodsServeDTO.IdDTO dto) {
        return goodsServeService.getGoodsServeDetailTemp(dto);
    }

	@Override
	public List<String> getServeTempIdByGoodsId(IdDTO dto) {
		return goodsServeService.getServeTempIdByGoodsId(dto);
	}
}
