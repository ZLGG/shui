package com.gs.lshly.biz.support.foundation.service.platadmin;
import com.gs.lshly.common.struct.platadmin.foundation.dto.SiteFloorNewDTO;
import com.gs.lshly.common.struct.platadmin.foundation.vo.SiteFloorNewVO;

import java.util.List;

public interface ISiteFloorNewService {

    /**
     * 楼层配置列表
     * @param dto
     * @return
     */
    List<SiteFloorNewVO.ListVO> listSiteFloorNewVO(SiteFloorNewDTO.ShowDTO dto);


    /**
     * 保存楼层配置
     * @param etoList
     */
    void saveSiteFloorNew(List<SiteFloorNewDTO.ETO> etoList);

}