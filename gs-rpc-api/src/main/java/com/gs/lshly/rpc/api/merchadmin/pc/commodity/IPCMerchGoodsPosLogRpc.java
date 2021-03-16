package com.gs.lshly.rpc.api.merchadmin.pc.commodity;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.ExportDataDTO;
import com.gs.lshly.common.struct.merchadmin.pc.commodity.dto.PCMerchGoodsPosLogDTO;
import com.gs.lshly.common.struct.merchadmin.pc.commodity.qto.PCMerchGoodsPosLogQTO;
import com.gs.lshly.common.struct.merchadmin.pc.commodity.vo.PCMerchGoodsPosLogVO;

import java.util.List;

/**
*
* @author Starry
* @since 2020-12-15
*/
public interface IPCMerchGoodsPosLogRpc {

    /**
     * 分页查询POS导入商品记录
     * @param qto
     * @return
     */
    PageData<PCMerchGoodsPosLogVO.DetailListVO> pageData(PCMerchGoodsPosLogQTO.QTO qto);

    /**
     * 批量新增商品pos记录
     * @param etos
     */
    void addGoodsPosLog(List<PCMerchGoodsPosLogDTO.ETO> etos);

    /**
     * 删除商品pos记录
     * @param dto
     */
    void deleteGoodsPosLog(PCMerchGoodsPosLogDTO.NumIidDTO dto);


    /**
     * 批量删除商品pos记录
     * @param dto
     */
    void deleteBatchGoodsPosLog(PCMerchGoodsPosLogDTO.NumIidListDTO dto);


    /**
     * 导出数据
     * @return
     * @throws Exception
     */
    ExportDataDTO export(PCMerchGoodsPosLogDTO.IdListDTO dto) throws Exception;

    PCMerchGoodsPosLogVO.DetailVO detailGoodsPosLog(PCMerchGoodsPosLogDTO.IdDTO dto);

}