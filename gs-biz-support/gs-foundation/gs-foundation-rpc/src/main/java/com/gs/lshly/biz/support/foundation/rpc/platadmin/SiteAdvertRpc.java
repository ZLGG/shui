package com.gs.lshly.biz.support.foundation.rpc.platadmin;
import com.gs.lshly.biz.support.foundation.service.platadmin.ISiteAdvertService;
import com.gs.lshly.common.struct.platadmin.foundation.dto.SiteAdvertDTO;
import com.gs.lshly.common.struct.platadmin.foundation.qto.SiteAdvertQTO;
import com.gs.lshly.common.struct.platadmin.foundation.vo.SiteAdvertVO;
import com.gs.lshly.rpc.api.platadmin.foundation.ISiteAdvertRpc;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
*
* @author hyy
* @since 2020-11-03
*/
@DubboService
public class SiteAdvertRpc implements ISiteAdvertRpc{

    @Autowired
    private ISiteAdvertService  siteAdvertService;


    @Override
    public List<SiteAdvertVO.H5CategoryListVO> h5CategoryList(SiteAdvertQTO.H5CategoryQTO qto) {
        return siteAdvertService.h5CategoryList(qto);
    }

    @Override
    public void h5CategoryEditor(SiteAdvertDTO.H5CategoryETO eto) {
        siteAdvertService.h5CategoryEditor(eto);
    }

    @Override
    public List<SiteAdvertVO.H5SubjectListVO> h5SubjectList(SiteAdvertQTO.H5SubjectQTO qto) {
        return siteAdvertService.h5SubjectList(qto);
    }

    @Override
    public void h5SubjectEditor(SiteAdvertDTO.H5SubjectETO eto) {
        siteAdvertService.h5SubjectEditor(eto);
    }

    @Override
    public List<SiteAdvertVO.PCGroupListVO> pcAdvertGroupList(SiteAdvertQTO.PCSubjectQTO qto) {
        return siteAdvertService.pcAdvertGroupList(qto);
    }

    @Override
    public void pcAdvertGroupEditor(SiteAdvertDTO.PCGroupETO eto) {

        siteAdvertService.pcAdvertGroupEditor(eto);
    }

    @Override
    public SiteAdvertVO.PCBillBoardListVO pcBillBoardList(SiteAdvertQTO.PCBillBoardQTO qto) {
        return siteAdvertService.pcBillBoardList(qto);
    }

    @Override
    public void pcBillBoardEditor(SiteAdvertDTO.PCBillBoardETO eto) {
        siteAdvertService.pcBillBoardEditor(eto);
    }

    @Override
    public List<SiteAdvertVO.PCBillBoardListVO> pcBillBoardMoreList(SiteAdvertQTO.PCBillBoardQTO qto) {
        return siteAdvertService.pcBillBoardMoreList(qto);
    }

    @Override
    public void pcBillBoardMoreEditor(SiteAdvertDTO.PCBillBoardMoreETO eto) {
        siteAdvertService.pcBillBoardMoreEditor(eto);
    }
}