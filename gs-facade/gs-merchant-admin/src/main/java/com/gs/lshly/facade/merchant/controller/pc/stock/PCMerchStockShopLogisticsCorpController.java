package com.gs.lshly.facade.merchant.controller.pc.stock;
import com.gs.lshly.common.constants.MsgConst;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.response.ResponseData;
import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.common.struct.merchadmin.pc.stock.dto.PCMerchStockShopLogisticsCorpDTO;
import com.gs.lshly.common.struct.merchadmin.pc.stock.qto.PCMerchStockShopLogisticsCorpQTO;
import com.gs.lshly.common.struct.merchadmin.pc.stock.vo.PCMerchStockShopLogisticsCorpVO;
import com.gs.lshly.rpc.api.merchadmin.pc.stock.IPCMerchStockShopLogisticsCorpRpc;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
* <p>
*  前端控制器
* </p>
*
* @author R
* @since 2020-10-24
*/
@RestController
@RequestMapping("/merchadmin/stockShopLogisticsCorp")
@Api(tags = "店铺支持物流管理")
public class PCMerchStockShopLogisticsCorpController {

    @DubboReference
    private IPCMerchStockShopLogisticsCorpRpc pcMerchStockShopLogisticsCorpRpc;

    @ApiOperation("店铺物流公司配置列表")
    @GetMapping("/list")
    public ResponseData<List<PCMerchStockShopLogisticsCorpVO.ListVO>> list(PCMerchStockShopLogisticsCorpQTO.QTO qto) {
        return ResponseData.data(pcMerchStockShopLogisticsCorpRpc.pageData(qto));
    }

    @ApiOperation("店铺物流公司启用列表")
    @GetMapping("/enableList")
    public ResponseData<List<PCMerchStockShopLogisticsCorpVO.ListVO>> enableList() {
        return ResponseData.data(pcMerchStockShopLogisticsCorpRpc.enableList(new BaseDTO()));
    }

    @ApiOperation("启用")
    @PutMapping(value = "/enable/{id}")
    public ResponseData<Void> enable(@PathVariable String id) {
        PCMerchStockShopLogisticsCorpDTO.IdDTO idDTO = new PCMerchStockShopLogisticsCorpDTO.IdDTO(id);
        pcMerchStockShopLogisticsCorpRpc.enable(idDTO);
        return ResponseData.success(MsgConst.ENABLE_SUCCESS);
    }

    @ApiOperation("禁用")
    @PutMapping(value = "/disable/{id}")
    public ResponseData<Void> disable(@PathVariable String id) {
        PCMerchStockShopLogisticsCorpDTO.IdDTO idDTO = new PCMerchStockShopLogisticsCorpDTO.IdDTO(id);
        pcMerchStockShopLogisticsCorpRpc.disable(idDTO);
        return ResponseData.success(MsgConst.DISABLE_SUCCESS);
    }

}










