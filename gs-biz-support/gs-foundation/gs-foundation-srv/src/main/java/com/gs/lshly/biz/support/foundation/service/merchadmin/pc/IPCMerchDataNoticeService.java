package com.gs.lshly.biz.support.foundation.service.merchadmin.pc;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.merchadmin.pc.foundation.dto.PCMerchDataNoticeDTO;
import com.gs.lshly.common.struct.merchadmin.pc.foundation.qto.PCMerchDataNoticeQTO;
import com.gs.lshly.common.struct.merchadmin.pc.foundation.vo.PCMerchDataNoticeVO;

import java.util.List;

public interface IPCMerchDataNoticeService {

    List<PCMerchDataNoticeVO.NoticeTypeListVO> noticeTypeList(PCMerchDataNoticeQTO.NoticeTypeQTO qto);

    PageData<PCMerchDataNoticeVO.ListVO> pageData(PCMerchDataNoticeQTO.QTO qto);

    PCMerchDataNoticeVO.DetailVO detailDataNotice(PCMerchDataNoticeDTO.IdDTO dto);

}