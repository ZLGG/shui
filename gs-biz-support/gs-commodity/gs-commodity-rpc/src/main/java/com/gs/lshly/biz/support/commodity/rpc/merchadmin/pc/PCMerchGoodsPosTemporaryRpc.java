package com.gs.lshly.biz.support.commodity.rpc.merchadmin.pc;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.ExportDataDTO;
import com.gs.lshly.common.struct.merchadmin.pc.commodity.dto.PCMerchGoodsPosTemporaryDTO;
import com.gs.lshly.common.struct.merchadmin.pc.commodity.qto.PCMerchGoodsPosTemporaryQTO;
import com.gs.lshly.common.struct.merchadmin.pc.commodity.vo.PCMerchGoodsInfoVO;
import com.gs.lshly.common.struct.merchadmin.pc.commodity.vo.PCMerchGoodsPosLogVO;
import com.gs.lshly.common.struct.merchadmin.pc.commodity.vo.PCMerchGoodsPosTemporaryVO;
import com.gs.lshly.common.utils.ExcelUtil;
import com.gs.lshly.rpc.api.merchadmin.pc.commodity.IPCMerchGoodsPosTemporaryRpc;
import com.gs.lshly.biz.support.commodity.service.merchadmin.pc.IPCMerchGoodsPosTemporaryService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

/**
*
* @author Starry
* @since 2021-02-23
*/
@DubboService
public class PCMerchGoodsPosTemporaryRpc implements IPCMerchGoodsPosTemporaryRpc{
    @Autowired
    private IPCMerchGoodsPosTemporaryService  pCMerchGoodsPosTemporaryService;

    @Override
    public PageData<PCMerchGoodsPosTemporaryVO.ListVO> pageData(PCMerchGoodsPosTemporaryQTO.QTO qto){
        return pCMerchGoodsPosTemporaryService.pageData(qto);
    }

    @Override
    public void addGoodsPosTemporary(PCMerchGoodsPosTemporaryDTO.ETO eto){
        pCMerchGoodsPosTemporaryService.addGoodsPosTemporary(eto);
    }

    @Override
    public void deleteGoodsPosTemporary(PCMerchGoodsPosTemporaryDTO.IdListDTO dto){
        pCMerchGoodsPosTemporaryService.deleteGoodsPosTemporary(dto);
    }

    @Override
    public PCMerchGoodsInfoVO.EditDetailVO getEditDetailEto(PCMerchGoodsPosTemporaryDTO.PosSpuIdDTO dto) {
        return pCMerchGoodsPosTemporaryService.getEditDetailEto(dto);
    }


    @Override
    public ExportDataDTO export(PCMerchGoodsPosTemporaryDTO.IdListDTO dto) throws Exception {
        return ExcelUtil.treatmentBean(pCMerchGoodsPosTemporaryService.getExportData(dto), PCMerchGoodsPosTemporaryVO.ListVO.class);
    }

    @Override
    public void modifyReleaseState(PCMerchGoodsPosTemporaryDTO.IdDTO dto) {
        pCMerchGoodsPosTemporaryService.modifyReleaseState(dto);
    }

}