package com.gs.lshly.biz.support.commodity.rpc.merchadmin.pc;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.ExportDataDTO;
import com.gs.lshly.common.struct.merchadmin.pc.commodity.dto.PCMerchGoodsPosLogDTO;
import com.gs.lshly.common.struct.merchadmin.pc.commodity.qto.PCMerchGoodsPosLogQTO;
import com.gs.lshly.common.struct.merchadmin.pc.commodity.vo.PCMerchGoodsPosLogVO;
import com.gs.lshly.common.struct.platadmin.commodity.vo.GoodsCategoryVO;
import com.gs.lshly.common.utils.ExcelUtil;
import com.gs.lshly.rpc.api.merchadmin.pc.commodity.IPCMerchGoodsPosLogRpc;
import com.gs.lshly.biz.support.commodity.service.merchadmin.pc.IPCMerchGoodsPosLogService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
*
* @author Starry
* @since 2020-12-15
*/
@DubboService
public class PCMerchGoodsPosLogRpc implements IPCMerchGoodsPosLogRpc{
    @Autowired
    private IPCMerchGoodsPosLogService  pCMerchGoodsPosLogService;


    @Override
    public PageData<PCMerchGoodsPosLogVO.DetailListVO> pageData(PCMerchGoodsPosLogQTO.QTO qto) {
        return pCMerchGoodsPosLogService.pageData(qto);
    }

    @Override
    public void addGoodsPosLog(List<PCMerchGoodsPosLogDTO.ETO> etos) {
        pCMerchGoodsPosLogService.addGoodsPosLog(etos);
    }

    @Override
    public void deleteGoodsPosLog(PCMerchGoodsPosLogDTO.NumIidDTO dto) {
        pCMerchGoodsPosLogService.deleteGoodsPosLog(dto);
    }

    @Override
    public void deleteBatchGoodsPosLog(PCMerchGoodsPosLogDTO.NumIidListDTO dto) {
        pCMerchGoodsPosLogService.deleteBatchGoodsPosLog(dto);
    }

    @Override
    public ExportDataDTO export(PCMerchGoodsPosLogDTO.IdListDTO dto) throws Exception {
        return ExcelUtil.treatmentBean(pCMerchGoodsPosLogService.getExportData(dto), PCMerchGoodsPosLogVO.DetailListVO.class);
    }


    @Override
    public PCMerchGoodsPosLogVO.DetailVO detailGoodsPosLog(PCMerchGoodsPosLogDTO.IdDTO dto){
        return  pCMerchGoodsPosLogService.detailGoodsPosLog(dto);
    }

}