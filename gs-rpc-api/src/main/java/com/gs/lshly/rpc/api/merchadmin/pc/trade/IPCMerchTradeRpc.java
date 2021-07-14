package com.gs.lshly.rpc.api.merchadmin.pc.trade;

import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.common.struct.BaseQTO;
import com.gs.lshly.common.struct.ExportDataDTO;
import com.gs.lshly.common.struct.merchadmin.pc.trade.dto.PCMerchTradeDTO;
import com.gs.lshly.common.struct.merchadmin.pc.trade.qto.PCMerchTradeQTO;
import com.gs.lshly.common.struct.merchadmin.pc.trade.vo.PCMerchTradeListVO;
import com.gs.lshly.common.struct.merchadmin.pc.trade.vo.PCMerchTradeVO;
import com.gs.lshly.common.struct.platadmin.trade.dto.TradeDTO;
import com.gs.lshly.common.struct.platadmin.trade.qto.TradeQTO;
import com.gs.lshly.common.struct.platadmin.trade.vo.TradeVO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 *
 * @author oy
 * @since 2020-11-16
 */
public interface IPCMerchTradeRpc {

    PageData<PCMerchTradeListVO.tradeVO> tradeListPageData(PCMerchTradeQTO.TradeList qto);


    PCMerchTradeListVO.tradeVO detail(PCMerchTradeDTO.IdDTO dto);

    List<PCMerchTradeListVO.innerGoodsIdAndName> innergoodsIdsCheck(PCMerchTradeDTO.GoodsIdsDTO dto);

    PCMerchTradeListVO.innerGoodsIdAndName innergoodsIdCheck(PCMerchTradeDTO.GoodsIdDTO dto);


    void editOrderAmount(PCMerchTradeDTO.orderAmountOrFreight dto);

    TradeVO.PayDatelistVO payDateList(TradeDTO.PayDateList dto);

    TradeVO.OperationlistVO operationList(TradeDTO.OperationList dto);

    ExportDataDTO export(PCMerchTradeQTO.IdListQTO qo) throws Exception ;

    PCMerchTradeVO.ExcelReturnVO updateDeliveryInfoBatch(byte[] file);

    ExportDataDTO downExcelModel(BaseDTO dto)throws Exception;

}