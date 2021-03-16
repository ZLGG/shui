package com.gs.lshly.biz.support.commodity.service.merchadmin.pc;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.merchadmin.pc.commodity.dto.PCMerchGoodsPosLogDTO;
import com.gs.lshly.common.struct.merchadmin.pc.commodity.qto.PCMerchGoodsPosLogQTO;
import com.gs.lshly.common.struct.merchadmin.pc.commodity.vo.PCMerchGoodsPosLogVO;

import java.util.List;

public interface IPCMerchGoodsPosLogService {

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
     * 获取要导出的数据
     * @param dto
     * @return
     */
    List<PCMerchGoodsPosLogVO.DetailListVO> getExportData(PCMerchGoodsPosLogDTO.IdListDTO dto);


    /**
     * 查询记录详情
     * @param dto
     * @return
     */
    PCMerchGoodsPosLogVO.DetailVO detailGoodsPosLog(PCMerchGoodsPosLogDTO.IdDTO dto);

}