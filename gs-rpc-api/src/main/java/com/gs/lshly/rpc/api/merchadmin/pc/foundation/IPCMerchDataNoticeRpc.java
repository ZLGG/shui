package com.gs.lshly.rpc.api.merchadmin.pc.foundation;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.merchadmin.pc.foundation.dto.PCMerchDataNoticeDTO;
import com.gs.lshly.common.struct.merchadmin.pc.foundation.qto.PCMerchDataNoticeQTO;
import com.gs.lshly.common.struct.merchadmin.pc.foundation.vo.PCMerchDataNoticeVO;

import java.util.List;

/**
*
* @author xxfc
* @since 2020-11-16
*/
public interface IPCMerchDataNoticeRpc {


    List<PCMerchDataNoticeVO.NoticeTypeListVO> noticeTypeList(PCMerchDataNoticeQTO.NoticeTypeQTO qto);

    PageData<PCMerchDataNoticeVO.ListVO> pageData(PCMerchDataNoticeQTO.QTO qto);

    PCMerchDataNoticeVO.DetailVO detailDataNotice(PCMerchDataNoticeDTO.IdDTO dto);

    List<PCMerchDataNoticeVO.ListVO> innerList(PCMerchDataNoticeDTO.innerDTO qto);

}