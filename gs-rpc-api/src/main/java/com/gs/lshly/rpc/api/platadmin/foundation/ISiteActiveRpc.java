package com.gs.lshly.rpc.api.platadmin.foundation;
import com.gs.lshly.common.struct.platadmin.foundation.dto.SiteActiveDTO;
import com.gs.lshly.common.struct.platadmin.foundation.vo.SiteActiveVO;

/**
*
* @author Starry
* @since 2021-03-30
*/
public interface ISiteActiveRpc {

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