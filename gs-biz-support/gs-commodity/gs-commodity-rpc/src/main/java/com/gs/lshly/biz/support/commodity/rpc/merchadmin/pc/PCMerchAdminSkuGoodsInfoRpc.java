package com.gs.lshly.biz.support.commodity.rpc.merchadmin.pc;

import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.gs.lshly.biz.support.commodity.service.merchadmin.pc.IPCMerchGoodsFupinService;
import com.gs.lshly.biz.support.commodity.service.merchadmin.pc.IPCMerchGoodsInfoService;
import com.gs.lshly.biz.support.commodity.service.merchadmin.pc.IPCMerchSkuGoodInfoService;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.common.struct.BaseQTO;
import com.gs.lshly.common.struct.ExportDataDTO;
import com.gs.lshly.common.struct.merchadmin.pc.commodity.dto.PCMerchGoodsInfoDTO;
import com.gs.lshly.common.struct.merchadmin.pc.commodity.qto.PCMerchGoodsInfoQTO;
import com.gs.lshly.common.struct.merchadmin.pc.commodity.vo.PCMerchGoodsInfoVO;
import com.gs.lshly.common.struct.merchadmin.pc.commodity.vo.PCMerchSkuGoodInfoVO;
import com.gs.lshly.common.struct.merchadmin.pc.commodityfupin.dto.PCMerchGoodsFupinDTO;
import com.gs.lshly.common.struct.merchadmin.pc.commodityfupin.qto.PCMerchGoodsFupinQTO;
import com.gs.lshly.common.struct.merchadmin.pc.commodityfupin.vo.PCMerchGoodsFupinVO;
import com.gs.lshly.common.utils.BeanCopyUtils;
import com.gs.lshly.common.utils.ExcelUtil;
import com.gs.lshly.rpc.api.merchadmin.pc.commodity.IPCMerchAdminGoodsInfoRpc;
import com.gs.lshly.rpc.api.merchadmin.pc.commodity.IPCMerchAdminSkuGoodsInfoRpc;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Starry
 * @since 2020-10-08
 */
@DubboService
public class PCMerchAdminSkuGoodsInfoRpc implements IPCMerchAdminSkuGoodsInfoRpc {
    @Autowired
    private IPCMerchSkuGoodInfoService ipcMerchSkuGoodInfoService;

    @Override
    public String selectSkuImg(String skuId) {
        return ipcMerchSkuGoodInfoService.selectSkuImg(skuId);
    }
}