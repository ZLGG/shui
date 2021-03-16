package com.gs.lshly.biz.support.commodity.rpc.platadmin;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.platadmin.commodity.dto.GoodsAuditRecordDTO;
import com.gs.lshly.common.struct.platadmin.commodity.qto.GoodsAuditRecordQTO;
import com.gs.lshly.common.struct.platadmin.commodity.vo.GoodsAuditRecordVO;
import com.gs.lshly.rpc.api.platadmin.commodity.IGoodsAuditRecordRpc;
import com.gs.lshly.biz.support.commodity.service.platadmin.IGoodsAuditRecordService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
*
* @author Starry
* @since 2020-10-29
*/
@DubboService
public class GoodsAuditRecordRpc implements IGoodsAuditRecordRpc{
    @Autowired
    private IGoodsAuditRecordService  GoodsAuditRecordService;


    @Override
    public List<GoodsAuditRecordVO.ListVO> listAuditRecordByGoodsId(GoodsAuditRecordQTO.QTO qto) {
        return GoodsAuditRecordService.listAuditRecordByGoodsId(qto);
    }

    @Override
    public void addGoodsAuditRecord(GoodsAuditRecordDTO.ETO eto){
        GoodsAuditRecordService.addGoodsAuditRecord(eto);
    }



}