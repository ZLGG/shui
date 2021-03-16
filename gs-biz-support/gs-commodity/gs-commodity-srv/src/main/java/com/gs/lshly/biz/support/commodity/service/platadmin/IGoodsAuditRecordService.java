package com.gs.lshly.biz.support.commodity.service.platadmin;
import com.gs.lshly.common.struct.platadmin.commodity.dto.GoodsAuditRecordDTO;
import com.gs.lshly.common.struct.platadmin.commodity.qto.GoodsAuditRecordQTO;
import com.gs.lshly.common.struct.platadmin.commodity.vo.GoodsAuditRecordVO;

import java.util.List;

public interface IGoodsAuditRecordService {

    /**
     * 根据商品id查询某个商品的审核记录
     * @param qto
     * @return
     */
    List<GoodsAuditRecordVO.ListVO> listAuditRecordByGoodsId(GoodsAuditRecordQTO.QTO qto);

    /**
     * 添加商品审核记录
     * @param eto
     */
    void addGoodsAuditRecord(GoodsAuditRecordDTO.ETO eto);

}