package com.gs.lshly.rpc.api.platadmin.foundation;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.platadmin.foundation.dto.SiteFloorNewDTO;
import com.gs.lshly.common.struct.platadmin.foundation.qto.SiteFloorNewQTO;
import com.gs.lshly.common.struct.platadmin.foundation.vo.SiteFloorNewVO;

import java.util.List;

/**
*
* @author Starry
* @since 2021-03-10
*/
public interface ISiteFloorNewRpc {

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