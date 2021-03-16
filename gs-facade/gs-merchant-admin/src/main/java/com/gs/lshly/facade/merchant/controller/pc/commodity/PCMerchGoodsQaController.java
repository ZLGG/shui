package com.gs.lshly.facade.merchant.controller.pc.commodity;

import com.gs.lshly.common.constants.MsgConst;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.response.ResponseData;
import com.gs.lshly.common.struct.merchadmin.pc.commodity.dto.PCMerchGoodsQaDTO;
import com.gs.lshly.common.struct.merchadmin.pc.commodity.qto.PCMerchGoodsQaQTO;
import com.gs.lshly.common.struct.merchadmin.pc.commodity.vo.PCMerchGoodsQaVO;
import com.gs.lshly.rpc.api.merchadmin.pc.commodity.IPCMerchAdminGoodsQaRpc;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.*;

/**
 * @Author Starry
 * @Date 10:06 2020/10/17
 */
@RestController
@Api(tags = "商品咨询管理")
@RequestMapping("/merchant/pc/goodsQa")
public class PCMerchGoodsQaController {
    @DubboReference
    private IPCMerchAdminGoodsQaRpc merchAdminGoodsQaRpc;

    @ApiOperation("商品咨询列表")
    @GetMapping("")
    public ResponseData<PageData<PCMerchGoodsQaVO.ReplyListVO>> list(PCMerchGoodsQaQTO.GoodsQaQTO qto) {
        return ResponseData.data(merchAdminGoodsQaRpc.pageMerchantGoodsQa(qto));
    }

    @ApiOperation("商家回复该商品咨询问题")
    @PutMapping(value = "reply/{id}")
    public ResponseData<Void> reply(@PathVariable String id, @RequestBody PCMerchGoodsQaDTO.MerchantReplyETO eto) {
        eto.setId(id);
        merchAdminGoodsQaRpc.replyGoodsQa(eto);
        return ResponseData.success(MsgConst.SAVE_SUCCESS);
    }

    @ApiOperation("是否将商品咨询显示到商品详情页")
    @PutMapping(value = "showOrHidden/{id}")
    public ResponseData<Void> showOrHidden(@PathVariable String id, @RequestBody PCMerchGoodsQaDTO.ShowContentETO eto) {
        eto.setId(id);
        merchAdminGoodsQaRpc.IsShowGoodsQaContent(eto);
        return ResponseData.success(MsgConst.SAVE_SUCCESS);
    }

    @ApiOperation("删除商品咨询回复")
    @PutMapping(value = "deleteReply/{id}")
    public ResponseData<Void> deleteReply(@PathVariable String id) {
        PCMerchGoodsQaDTO.IdDTO dto = new PCMerchGoodsQaDTO.IdDTO(id);
        merchAdminGoodsQaRpc.deleteGoodsQaReply(dto);
        return ResponseData.success(MsgConst.SAVE_SUCCESS);
    }
}
