package com.gs.lshly.rpc.api.merchadmin.pc.commodity;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.ExportDataDTO;
import com.gs.lshly.common.struct.merchadmin.pc.commodity.dto.PCMerchGoodsPosTemporaryDTO;
import com.gs.lshly.common.struct.merchadmin.pc.commodity.qto.PCMerchGoodsPosTemporaryQTO;
import com.gs.lshly.common.struct.merchadmin.pc.commodity.vo.PCMerchGoodsInfoVO;
import com.gs.lshly.common.struct.merchadmin.pc.commodity.vo.PCMerchGoodsPosTemporaryVO;

/**
*
* @author Starry
* @since 2021-02-23
*/
public interface IPCMerchGoodsPosTemporaryRpc {

    PageData<PCMerchGoodsPosTemporaryVO.ListVO> pageData(PCMerchGoodsPosTemporaryQTO.QTO qto);

    /**
     * 同步商品
     * @param eto
     */
    void addGoodsPosTemporary(PCMerchGoodsPosTemporaryDTO.ETO eto);

    void deleteGoodsPosTemporary(PCMerchGoodsPosTemporaryDTO.IdListDTO dto);

    /**
     * 获取pos商品详情
     * @param dto
     * @return
     */
    PCMerchGoodsInfoVO.EditDetailVO getEditDetailEto(PCMerchGoodsPosTemporaryDTO.PosSpuIdDTO dto);

    /**
     * 导出数据
     * @return
     * @throws Exception
     */
    ExportDataDTO export(PCMerchGoodsPosTemporaryDTO.IdListDTO dto) throws Exception;


    /**
     * 修改发布状态
     * @param dto
     */
    void modifyReleaseState(PCMerchGoodsPosTemporaryDTO.PosSpuIdDTO dto);
}