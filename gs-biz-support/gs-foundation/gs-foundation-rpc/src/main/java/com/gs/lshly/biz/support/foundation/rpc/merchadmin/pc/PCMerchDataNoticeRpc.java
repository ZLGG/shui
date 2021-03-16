package com.gs.lshly.biz.support.foundation.rpc.merchadmin.pc;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.merchadmin.pc.foundation.dto.PCMerchDataNoticeDTO;
import com.gs.lshly.common.struct.merchadmin.pc.foundation.qto.PCMerchDataNoticeQTO;
import com.gs.lshly.common.struct.merchadmin.pc.foundation.vo.PCMerchDataNoticeVO;
import com.gs.lshly.rpc.api.merchadmin.pc.foundation.IPCMerchDataNoticeRpc;
import com.gs.lshly.biz.support.foundation.service.merchadmin.pc.IPCMerchDataNoticeService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
*
* @author xxfc
* @since 2020-11-16
*/
@DubboService
public class PCMerchDataNoticeRpc implements IPCMerchDataNoticeRpc{

    @Autowired
    private IPCMerchDataNoticeService  pCMerchDataNoticeService;

    @Override
    public List<PCMerchDataNoticeVO.NoticeTypeListVO> noticeTypeList(PCMerchDataNoticeQTO.NoticeTypeQTO qto) {
        return pCMerchDataNoticeService.noticeTypeList(qto);
    }

    @Override
    public PageData<PCMerchDataNoticeVO.ListVO> pageData(PCMerchDataNoticeQTO.QTO qto){
        return pCMerchDataNoticeService.pageData(qto);
    }

    @Override
    public PCMerchDataNoticeVO.DetailVO detailDataNotice(PCMerchDataNoticeDTO.IdDTO dto){
        return  pCMerchDataNoticeService.detailDataNotice(dto);
    }

}