package com.gs.lshly.biz.support.foundation.rpc.bbc;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.common.struct.bbc.foundation.dto.BbcSiteAdvertDTO;
import com.gs.lshly.common.struct.bbc.foundation.qto.BbcSiteAdvertQTO;
import com.gs.lshly.common.struct.bbc.foundation.qto.BbcSiteAdvertQTO.SubjectQTO;
import com.gs.lshly.common.struct.bbc.foundation.vo.BbcSiteAdvertVO;
import com.gs.lshly.common.struct.bbc.foundation.vo.BbcSiteAdvertVO.AdvertDetailVO;
import com.gs.lshly.common.struct.platadmin.foundation.qto.SiteAdvertQTO;
import com.gs.lshly.rpc.api.bbc.foundation.IBbcSiteAdvertRpc;
import com.gs.lshly.biz.support.foundation.service.bbc.IBbcSiteAdvertService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
*
* @author hyy
* @since 2020-11-03
*/
@DubboService
public class BbcSiteAdvertRpc implements IBbcSiteAdvertRpc{

    @Autowired
    private IBbcSiteAdvertService  bbcSiteAdvertService;

    @Override
    public List<BbcSiteAdvertVO.CategoryListVO> categoryAdvertList(BbcSiteAdvertQTO.CategoryQTO qto){
        return bbcSiteAdvertService.categoryAdvertList(qto);
    }

    @Override
    public List<BbcSiteAdvertVO.SubjectListVO> subjectAdvertList(BbcSiteAdvertQTO.SubjectQTO qto) {
        return bbcSiteAdvertService.subjectAdvertList(qto);
    }

    @Override
    public PageData<BbcSiteAdvertVO.SubjectListVO> subjectAdvertPageList(BbcSiteAdvertQTO.SubjectPageQTO qto) {
        return bbcSiteAdvertService.subjectAdvertPageList(qto);
    }

    @Override
    public List<BbcSiteAdvertVO.InnerCategoryAdvertListVO> innerCategoryAdvertList(BaseDTO dto) {
        return bbcSiteAdvertService.innerCategoryAdvertList(dto);
    }

	@Override
	public List<AdvertDetailVO> listBySubject(SubjectQTO qto) {
		return bbcSiteAdvertService.listBySubject(qto);
	}


}