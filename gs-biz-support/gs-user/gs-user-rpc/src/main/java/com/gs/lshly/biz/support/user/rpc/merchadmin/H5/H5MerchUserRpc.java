package com.gs.lshly.biz.support.user.rpc.merchadmin.H5;

import com.gs.lshly.biz.support.user.service.merchadmin.h5.IH5MerchUserService;
import com.gs.lshly.biz.support.user.service.merchadmin.pc.IPCMerchUserService;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.ExportDataDTO;
import com.gs.lshly.common.struct.merchadmin.h5.trade.vo.H5MerchUserVO;
import com.gs.lshly.common.struct.merchadmin.pc.user.dto.PCMerchUserDTO;
import com.gs.lshly.common.struct.merchadmin.pc.user.qto.PCMerchUserQTO;
import com.gs.lshly.common.struct.merchadmin.pc.user.vo.PCMerchUserVO;
import com.gs.lshly.common.utils.ExcelUtil;
import com.gs.lshly.rpc.api.merchadmin.h5.user.IH5MerchUserRpc;
import com.gs.lshly.rpc.api.merchadmin.pc.user.IPCMerchUserRpc;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
*
* @author xxfc
* @since 2020-10-20
*/
@DubboService
public class H5MerchUserRpc implements IH5MerchUserRpc {

    @Autowired
    private IH5MerchUserService h5MerchUserService;




    @Override
    public H5MerchUserVO.UserSimpleVO innerUserSimple(String userId) {
        return h5MerchUserService.innerUserSimple(userId);
    }




}