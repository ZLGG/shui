package com.gs.lshly.rpc.api.common;

import com.gs.lshly.common.struct.common.dto.CommonSiteActiveDTO;
import com.gs.lshly.common.struct.common.vo.CommonSiteActiveVO;

/**
 * @Author Starry
 * @Date 16:25 2021/3/30
 */
public interface ICommonSiteActiveRpc {

    /**
     * 获取活动图片配置
     * @param dto
     * @return
     */
    CommonSiteActiveVO.ListVO getCommonSiteActiveVO(CommonSiteActiveDTO.QueryDTO dto);

}
