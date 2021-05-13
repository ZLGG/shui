package com.gs.lshly.facade.merchant.controller.pc.trade;
import javax.validation.Valid;

import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gs.lshly.common.constants.MsgConst;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.response.ResponseData;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.dto.PCMerchMarketPtSeckillMerchantDTO;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.qto.PCMerchMarketPtSeckillMerchantQTO;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.vo.PCMerchMarketPtSeckillMerchantVO;
import com.gs.lshly.rpc.api.merchadmin.pc.trade.IPCMerchMarketPtSeckillMerchantRpc;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 秒杀秒杀参与记录管理
 *
 * 
 * @author yingjun
 * @date 2021年5月8日 上午10:11:04
 */
@RestController
@RequestMapping("/merchadmin/marketPtSeckillMerchant")
@Api(tags = "秒杀商家参与记录管理-v1.1.0")
@SuppressWarnings("unchecked")
public class PCMerchMarketPtSeckillMerchantController {

    @DubboReference
    private IPCMerchMarketPtSeckillMerchantRpc pcMerchMarketPtSeckillMerchantRpc;

	@ApiOperation("秒杀商家参与记录列表-v1.1.0")
    @GetMapping("")
    public ResponseData<PageData<PCMerchMarketPtSeckillMerchantVO.ListVO>> list(PCMerchMarketPtSeckillMerchantQTO.QTO qto) {
        return ResponseData.data(pcMerchMarketPtSeckillMerchantRpc.pageData(qto));
    }

    @ApiOperation("秒杀商家参与记录详情-v1.1.0")
    @GetMapping(value = "/{id}")
    public ResponseData<PCMerchMarketPtSeckillMerchantVO.DetailVO> get(@PathVariable String id) {
        return ResponseData.data(pcMerchMarketPtSeckillMerchantRpc.detailMarketPtSeckillMerchant(new PCMerchMarketPtSeckillMerchantDTO.IdDTO(id)));
    }

    @ApiOperation("新增秒杀商家参与记录-v1.1.0")
    @PostMapping("")
    public ResponseData<Void> add(@Valid @RequestBody PCMerchMarketPtSeckillMerchantDTO.ETO dto) {
    	pcMerchMarketPtSeckillMerchantRpc.addMarketPtSeckillMerchant(dto);
        return ResponseData.success(MsgConst.ADD_SUCCESS);
    }

    @ApiOperation("删除秒杀商家参与记录-v1.1.0")
    @DeleteMapping(value = "/{id}")
    public ResponseData<Void> delete(@PathVariable String id) {
        PCMerchMarketPtSeckillMerchantDTO.IdDTO dto = new PCMerchMarketPtSeckillMerchantDTO.IdDTO(id);
        pcMerchMarketPtSeckillMerchantRpc.deleteMarketPtSeckillMerchant(dto);
        return ResponseData.success(MsgConst.DELETE_SUCCESS);
    }


    @ApiOperation("修改秒杀商家参与记录-v1.1.0")
    @PutMapping(value = "/{id}")
    public ResponseData<Void> update(@PathVariable String id, @Valid @RequestBody PCMerchMarketPtSeckillMerchantDTO.ETO eto) {
        eto.setId(id);
        pcMerchMarketPtSeckillMerchantRpc.editMarketPtSeckillMerchant(eto);
        return ResponseData.success(MsgConst.UPDATE_SUCCESS);
    }

}
