package com.gs.lshly.biz.support.foundation.rpc.common;

import com.gs.lshly.biz.support.foundation.service.common.ICommonSiteActiveService;
import com.gs.lshly.common.struct.common.dto.CommonSiteActiveDTO;
import com.gs.lshly.common.struct.common.vo.CommonSiteActiveVO;
import com.gs.lshly.rpc.api.common.ICommonSiteActiveRpc;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Author Starry
 * @Date 16:26 2021/3/30
 */
@DubboService
public class CommonSiteActiveRpc implements ICommonSiteActiveRpc {

    @Autowired
    private ICommonSiteActiveService commonSiteActiveService;

    @Override
    public CommonSiteActiveVO.ListVO getCommonSiteActiveVO(CommonSiteActiveDTO.QueryDTO dto) {
        return commonSiteActiveService.getCommonSiteActiveVO(dto);
    }
}
