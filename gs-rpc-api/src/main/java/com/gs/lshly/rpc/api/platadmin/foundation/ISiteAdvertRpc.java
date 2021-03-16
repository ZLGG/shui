package com.gs.lshly.rpc.api.platadmin.foundation;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.platadmin.foundation.dto.SiteAdvertDTO;
import com.gs.lshly.common.struct.platadmin.foundation.qto.SiteAdvertQTO;
import com.gs.lshly.common.struct.platadmin.foundation.vo.SiteAdvertVO;

import java.util.List;

/**
*
* @author hyy
* @since 2020-11-03
*/
public interface ISiteAdvertRpc {

    List<SiteAdvertVO.H5CategoryListVO> h5CategoryList(SiteAdvertQTO.H5CategoryQTO qto);

    void h5CategoryEditor(SiteAdvertDTO.H5CategoryETO eto);

    List<SiteAdvertVO.H5SubjectListVO> h5SubjectList(SiteAdvertQTO.H5SubjectQTO qto);

    void h5SubjectEditor(SiteAdvertDTO.H5SubjectETO eto);

    List<SiteAdvertVO.PCGroupListVO> pcAdvertGroupList(SiteAdvertQTO.PCSubjectQTO qto);

    void pcAdvertGroupEditor(SiteAdvertDTO.PCGroupETO eto);

    SiteAdvertVO.PCBillBoardListVO pcBillBoardList(SiteAdvertQTO.PCBillBoardQTO qto);

    void pcBillBoardEditor(SiteAdvertDTO.PCBillBoardETO eto);


    List<SiteAdvertVO.PCBillBoardListVO> pcBillBoardMoreList(SiteAdvertQTO.PCBillBoardQTO qto);

    void pcBillBoardMoreEditor(SiteAdvertDTO.PCBillBoardMoreETO eto);
}