package com.gs.lshly.facade.bbc.controller.h5.foundation;

import com.gs.lshly.common.enums.PcH5Enum;
import com.gs.lshly.common.enums.TerminalEnum;
import com.gs.lshly.common.response.ResponseData;
import com.gs.lshly.common.struct.common.dto.CommonSiteActiveDTO;
import com.gs.lshly.common.struct.common.vo.CommonSiteActiveVO;
import com.gs.lshly.rpc.api.common.ICommonSiteActiveRpc;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author Starry
 * @Date 16:10 2021/3/30
 */
@RestController
@RequestMapping("/bbc/steActive")
@Api(tags = "活动图片配置")
public class BbcSiteActiveController {

    @DubboReference
    private ICommonSiteActiveRpc siteActiveRpc;


    @ApiOperation("活动图片配置")
    @GetMapping("/getSiteActiveVO")
    public ResponseData<CommonSiteActiveVO.ListVO> getSiteActiveVO(CommonSiteActiveDTO.QueryDTO dto) {
        dto.setPcShow(PcH5Enum.NO.getCode());
        dto.setTerminal(TerminalEnum.BBC.getCode());
        return ResponseData.data(siteActiveRpc.getCommonSiteActiveVO(dto));
    }


}
