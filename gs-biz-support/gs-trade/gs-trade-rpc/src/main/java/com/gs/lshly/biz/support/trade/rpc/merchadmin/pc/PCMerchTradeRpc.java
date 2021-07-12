package com.gs.lshly.biz.support.trade.rpc.merchadmin.pc;

import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.gs.lshly.biz.support.trade.service.merchadmin.pc.IPCMerchTradeService;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.common.struct.ExportDataDTO;
import com.gs.lshly.common.struct.merchadmin.pc.trade.dto.PCMerchTradeDTO;
import com.gs.lshly.common.struct.merchadmin.pc.trade.qto.PCMerchTradeQTO;
import com.gs.lshly.common.struct.merchadmin.pc.trade.vo.PCMerchTradeListVO;
import com.gs.lshly.common.struct.merchadmin.pc.trade.vo.PCMerchTradeVO;
import com.gs.lshly.common.struct.platadmin.trade.dto.TradeDTO;
import com.gs.lshly.common.struct.platadmin.trade.qto.TradeQTO;
import com.gs.lshly.common.struct.platadmin.trade.vo.TradeVO;
import com.gs.lshly.common.utils.ExcelUtil;
import com.gs.lshly.rpc.api.merchadmin.pc.trade.IPCMerchTradeRpc;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author oy
 * @since 2020-11-16
 */
@DubboService
public class PCMerchTradeRpc implements IPCMerchTradeRpc {
    @Autowired
    private IPCMerchTradeService pCMerchTradeService;


    @Override
    public PageData<PCMerchTradeListVO.tradeVO> tradeListPageData(PCMerchTradeQTO.TradeList qto) {
        return pCMerchTradeService.tradeListPageData(qto);
    }

    @Override
    public PCMerchTradeListVO.tradeVO detail(PCMerchTradeDTO.IdDTO dto) {
        return pCMerchTradeService.detail(dto);
    }

    @Override
    public List<PCMerchTradeListVO.innerGoodsIdAndName> innergoodsIdsCheck(PCMerchTradeDTO.GoodsIdsDTO dto) {
        return pCMerchTradeService.innergoodsIdsCheck(dto);
    }

    @Override
    public PCMerchTradeListVO.innerGoodsIdAndName innergoodsIdCheck(PCMerchTradeDTO.GoodsIdDTO dto) {
        return pCMerchTradeService.innergoodsIdCheck(dto);
    }

    @Override
    public void editOrderAmount(PCMerchTradeDTO.orderAmountOrFreight dto) {

        pCMerchTradeService.editOrderAmount(dto);
    }

    @Override
    public TradeVO.PayDatelistVO payDateList(TradeDTO.PayDateList dto) {
        return pCMerchTradeService.payDateList(dto);
    }

    @Override
    public TradeVO.OperationlistVO operationList(TradeDTO.OperationList dto) {
        return pCMerchTradeService.operationList(dto);
    }

    @Override
    public ExportDataDTO export(PCMerchTradeQTO.IdListQTO qo) throws Exception {
        if (ObjectUtils.isNotEmpty(qo.getType()) && qo.getType().intValue() == 1) {
            return ExcelUtil.treatmentBean(pCMerchTradeService.export(qo), PCMerchTradeListVO.waitSendTradeExport.class);
        }
        return ExcelUtil.treatmentBean(pCMerchTradeService.hasSentExport(qo), PCMerchTradeListVO.hasSentTradeExport.class);
    }

    @Override
    public PCMerchTradeVO.ExcelReturnVO updateDeliveryInfoBatch(MultipartFile file, BaseDTO dto) {
       return pCMerchTradeService.updateDeliveryInfoBatch(file,dto);
    }

}