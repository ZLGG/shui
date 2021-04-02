package com.gs.lshly.biz.support.foundation.service.platadmin;
import com.gs.lshly.common.struct.platadmin.foundation.dto.SiteActiveDTO;
import com.gs.lshly.common.struct.platadmin.foundation.vo.SiteActiveVO;

public interface ISiteActiveService {

    /**
     * 获取活动配置信息
     * @param dto
     * @return
     */
    SiteActiveVO.ListVO getListVO(SiteActiveDTO.QueryDTO dto);

    /**
     * 保存配置信息
     * @param eto
     */
    void saveSiteActive(SiteActiveDTO.ETO eto);


}