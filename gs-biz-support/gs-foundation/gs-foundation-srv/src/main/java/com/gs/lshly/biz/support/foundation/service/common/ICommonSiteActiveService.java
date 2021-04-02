package com.gs.lshly.biz.support.foundation.service.common;

import com.gs.lshly.common.struct.common.dto.CommonSiteActiveDTO;
import com.gs.lshly.common.struct.common.vo.CommonSiteActiveVO;

/**
 * @Author Starry
 * @Date 16:12 2021/3/30
 */
public interface ICommonSiteActiveService {

    /**
     * 获取活动图片配置
     * @param dto
     * @return
     */
    CommonSiteActiveVO.ListVO getCommonSiteActiveVO(CommonSiteActiveDTO.QueryDTO dto);
}
