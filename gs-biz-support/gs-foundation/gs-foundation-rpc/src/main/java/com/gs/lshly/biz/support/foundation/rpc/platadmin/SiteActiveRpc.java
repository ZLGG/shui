package com.gs.lshly.biz.support.foundation.rpc.platadmin;
import com.gs.lshly.common.struct.platadmin.foundation.dto.SiteActiveDTO;
import com.gs.lshly.common.struct.platadmin.foundation.vo.SiteActiveVO;
import com.gs.lshly.rpc.api.platadmin.foundation.ISiteActiveRpc;
import com.gs.lshly.biz.support.foundation.service.platadmin.ISiteActiveService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

/**
*
* @author Starry
* @since 2021-03-30
*/
@DubboService
public class SiteActiveRpc implements ISiteActiveRpc{
    @Autowired
    private ISiteActiveService  SiteActiveService;


    @Override
    public SiteActiveVO.ListVO getListVO(SiteActiveDTO.QueryDTO dto) {
        return SiteActiveService.getListVO(dto);
    }

    @Override
    public void saveSiteActive(SiteActiveDTO.ETO eto) {
        SiteActiveService.saveSiteActive(eto);
    }
}