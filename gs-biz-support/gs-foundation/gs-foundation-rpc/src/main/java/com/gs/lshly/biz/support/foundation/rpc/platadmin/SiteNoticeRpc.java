package com.gs.lshly.biz.support.foundation.rpc.platadmin;
import com.gs.lshly.common.struct.bbc.user.qto.BbcMessageQTO;
import com.gs.lshly.common.struct.bbc.user.vo.BbcSiteNoticeVO;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import com.gs.lshly.biz.support.foundation.service.platadmin.ISiteNoticeService;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.platadmin.foundation.dto.SiteNoticeDTO.ETO;
import com.gs.lshly.common.struct.platadmin.foundation.dto.SiteNoticeDTO.IdDTO;
import com.gs.lshly.common.struct.platadmin.foundation.qto.SiteNoticeQTO.QTO;
import com.gs.lshly.common.struct.platadmin.foundation.vo.SiteNoticeVO.PCDetailVO;
import com.gs.lshly.common.struct.platadmin.foundation.vo.SiteNoticeVO.PCListVO;
import com.gs.lshly.rpc.api.platadmin.foundation.ISiteNoticeRpc;

/**
 * 
 *
 * 
 * @author yingjun
 * @date 2021年3月10日 上午3:01:50
 */
@DubboService
public class SiteNoticeRpc implements ISiteNoticeRpc{

    @Autowired
    private ISiteNoticeService  siteNoticeServic;

	@Override
	public PageData<PCListVO> pageData(QTO qto) {
		return siteNoticeServic.pageData(qto);
	}

	@Override
	public void editor(ETO dto) {
		siteNoticeServic.editor(dto);
	}

	@Override
	public PCDetailVO get(IdDTO dto) {
		return siteNoticeServic.get(dto);
	}

	@Override
	public void delete(IdDTO dto) {
		siteNoticeServic.delete(dto);
	}

	@Override
	public PageData<BbcSiteNoticeVO.NoticeListVO> getNoticeList(BbcMessageQTO.NoticeListQTO qto) {
		return siteNoticeServic.getNoticeList(qto);
	}


}