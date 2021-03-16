package com.gs.lshly.rpc.api.platadmin.commodity;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.platadmin.commodity.dto.GoodsAuditRecordDTO;
import com.gs.lshly.common.struct.platadmin.commodity.qto.GoodsAuditRecordQTO;
import com.gs.lshly.common.struct.platadmin.commodity.vo.GoodsAuditRecordVO;

import java.util.List;

/**
*
* @author Starry
* @since 2020-10-29
*/
public interface IGoodsAuditRecordRpc {
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