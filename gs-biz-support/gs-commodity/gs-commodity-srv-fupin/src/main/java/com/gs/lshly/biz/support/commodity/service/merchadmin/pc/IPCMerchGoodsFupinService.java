package com.gs.lshly.biz.support.commodity.service.merchadmin.pc;

import com.gs.lshly.common.struct.BaseQTO;
import com.gs.lshly.common.struct.merchadmin.pc.commodityfupin.dto.PCMerchGoodsFupinDTO;
import com.gs.lshly.common.struct.merchadmin.pc.commodityfupin.qto.PCMerchGoodsFupinQTO;
import com.gs.lshly.common.struct.merchadmin.pc.commodityfupin.vo.PCMerchGoodsFupinVO;

import java.util.List;

public interface IPCMerchGoodsFupinService {

    /**
     * 为扶贫商品添加或修改上传凭证
     * @param eto
     */
    void saveGoodsFupin(PCMerchGoodsFupinDTO.ETO eto);

    /**
     * 将扶贫商品恢复成普通的商品
     * @param dto
     */
    void deleteGoodsFupin(PCMerchGoodsFupinDTO.IdDTO dto);

    /**
     * 扶贫商品图片凭证
     * @param qto
     * @return
     */
    PCMerchGoodsFupinVO.DetailVO getDetailVO(PCMerchGoodsFupinQTO.QTO qto);


    /**
     * 扶贫商品id列表
     * @param qto
     * @return
     */
    List<String> listFuPinGoodsId(BaseQTO qto);

}