package com.gs.lshly.biz.support.foundation.rpc.platadmin;
import com.gs.lshly.biz.support.foundation.service.platadmin.ISiteBottomArticleService;
import com.gs.lshly.common.struct.platadmin.foundation.dto.SiteBottomArticleDTO;
import com.gs.lshly.common.struct.platadmin.foundation.qto.SiteBottomArticleQTO;
import com.gs.lshly.common.struct.platadmin.foundation.vo.SiteBottomArticleVO;
import com.gs.lshly.rpc.api.platadmin.foundation.ISiteBottomArticleRpc;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
*
* @author 陈奇
* @since 2020-10-08
*/
@DubboService
public class SiteBottomArticleRpc implements ISiteBottomArticleRpc{

    @Autowired
    private ISiteBottomArticleService siteBottomArticleService;


    @Override
    public List<SiteBottomArticleVO.PCListVO> list(SiteBottomArticleQTO.PCQTO qto) {
        return siteBottomArticleService.list(qto);
    }

    @Override
    public void bottomArticleEditor(SiteBottomArticleDTO.PCUDTO eto) {
        siteBottomArticleService.bottomArticleEditor(eto);
    }

}