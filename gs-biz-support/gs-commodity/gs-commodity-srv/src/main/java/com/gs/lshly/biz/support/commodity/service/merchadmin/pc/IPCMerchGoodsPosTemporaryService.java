package com.gs.lshly.biz.support.commodity.service.merchadmin.pc;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.merchadmin.pc.commodity.dto.PCMerchGoodsPosTemporaryDTO;
import com.gs.lshly.common.struct.merchadmin.pc.commodity.qto.PCMerchGoodsPosTemporaryQTO;
import com.gs.lshly.common.struct.merchadmin.pc.commodity.vo.PCMerchGoodsInfoVO;
import com.gs.lshly.common.struct.merchadmin.pc.commodity.vo.PCMerchGoodsPosTemporaryVO;

import java.util.List;

public interface IPCMerchGoodsPosTemporaryService {

    /**
     * 商品临时库列表
     * @param qto
     * @return
     */
    PageData<PCMerchGoodsPosTemporaryVO.ListVO> pageData(PCMerchGoodsPosTemporaryQTO.QTO qto);

    /**
     * 同步数据到临时库
     * @param eto
     */
    void addGoodsPosTemporary(PCMerchGoodsPosTemporaryDTO.ETO eto);


    /**
     * 批量删除
     * @param dto
     */
    void deleteGoodsPosTemporary(PCMerchGoodsPosTemporaryDTO.IdListDTO dto);


    /**
     * 获取pos商品详情
     * @param dto
     * @return
     */
    PCMerchGoodsInfoVO.EditDetailVO getEditDetailEto(PCMerchGoodsPosTemporaryDTO.PosSpuIdDTO dto);

    /**
     * 获取要导出的数据
     * @param dto
     * @return
     */
    List<PCMerchGoodsPosTemporaryVO.ListVO> getExportData(PCMerchGoodsPosTemporaryDTO.IdListDTO dto);


    /**
     * 修改发布状态
     * @param dto
     */
    void modifyReleaseState(PCMerchGoodsPosTemporaryDTO.PosSpuIdDTO dto);
}