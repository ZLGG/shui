package com.gs.lshly.biz.support.foundation.rpc.bbb.h5;

import com.gs.lshly.biz.support.foundation.service.bbb.h5.IBbbH5SiteAdvertService;
import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.common.struct.bbb.h5.foundation.qto.BbbH5SiteAdvertQTO;
import com.gs.lshly.common.struct.bbb.h5.foundation.vo.BbbH5SiteAdvertVO;
import com.gs.lshly.rpc.api.bbb.h5.foundation.IBbbH5SiteAdvertRpc;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;


/**
*
* @author hyy
* @since 2020-11-03
*/
@DubboService
public class BbbH5SiteAdvertRpc implements IBbbH5SiteAdvertRpc {

    @Autowired
    private IBbbH5SiteAdvertService bbbSiteAdvertService;

    @Override
    public List<BbbH5SiteAdvertVO.CategoryListVO> categoryAdvertList(BbbH5SiteAdvertQTO.CategoryQTO qto){
        return bbbSiteAdvertService.categoryAdvertList(qto);
    }

    @Override
    public List<BbbH5SiteAdvertVO.SubjectListVO> subjectAdvertList(BbbH5SiteAdvertQTO.SubjectQTO qto) {
        return bbbSiteAdvertService.subjectAdvertList(qto);
    }

    @Override
    public List<BbbH5SiteAdvertVO.InnerCategoryAdvertListVO> innerCategoryAdvertList(BaseDTO dto) {
        return bbbSiteAdvertService.innerCategoryAdvertList(dto);
    }


}