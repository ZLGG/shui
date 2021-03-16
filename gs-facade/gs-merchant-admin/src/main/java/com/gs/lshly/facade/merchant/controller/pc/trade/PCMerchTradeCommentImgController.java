package com.gs.lshly.facade.merchant.controller.pc.trade;
import com.gs.lshly.common.constants.MsgConst;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.response.ResponseData;
import com.gs.lshly.common.struct.merchadmin.pc.trade.dto.PCMerchTradeCommentImgDTO;
import com.gs.lshly.common.struct.merchadmin.pc.trade.qto.PCMerchTradeCommentImgQTO;
import com.gs.lshly.common.struct.merchadmin.pc.trade.vo.PCMerchTradeCommentImgVO;
import com.gs.lshly.rpc.api.merchadmin.pc.trade.IPCMerchTradeCommentImgRpc;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

/**
* <p>
*  前端控制器
* </p>
*
* @author Starry
* @since 2020-11-16
*/
@RestController
@RequestMapping("/merchadmin/tradeCommentImg")
@Api(tags = "商家订单交易评论图片管理管理")
public class PCMerchTradeCommentImgController {

    @DubboReference
    private IPCMerchTradeCommentImgRpc pcMerchTradeCommentImgRpc;

    @ApiOperation("商家订单交易评论图片管理列表")
    @GetMapping("")
    public ResponseData<PageData<PCMerchTradeCommentImgVO.ListVO>> list(PCMerchTradeCommentImgQTO.QTO qto) {
        return ResponseData.data(pcMerchTradeCommentImgRpc.pageData(qto));
    }

    @ApiOperation("商家订单交易评论图片管理详情")
    @GetMapping(value = "/{id}")
    public ResponseData<PCMerchTradeCommentImgVO.DetailVO> get(@PathVariable String id) {
        return ResponseData.data(pcMerchTradeCommentImgRpc.detailTradeCommentImg(new PCMerchTradeCommentImgDTO.IdDTO(id)));
    }

    @ApiOperation("新增商家订单交易评论图片管理")
    @PostMapping("")
    public ResponseData<Void> add(@Valid @RequestBody PCMerchTradeCommentImgDTO.ETO dto) {
            pcMerchTradeCommentImgRpc.addTradeCommentImg(dto);
        return ResponseData.success(MsgConst.ADD_SUCCESS);
    }

    @ApiOperation("删除商家订单交易评论图片管理")
    @DeleteMapping(value = "/{id}")
    public ResponseData<Void> delete(@PathVariable String id) {
        PCMerchTradeCommentImgDTO.IdDTO dto = new PCMerchTradeCommentImgDTO.IdDTO(id);
        pcMerchTradeCommentImgRpc.deleteTradeCommentImg(dto);
        return ResponseData.success(MsgConst.DELETE_SUCCESS);
    }


    @ApiOperation("修改商家订单交易评论图片管理")
    @PutMapping(value = "/{id}")
    public ResponseData<Void> update(@PathVariable String id, @Valid @RequestBody PCMerchTradeCommentImgDTO.ETO eto) {
        eto.setId(id);
        pcMerchTradeCommentImgRpc.editTradeCommentImg(eto);
        return ResponseData.success(MsgConst.UPDATE_SUCCESS);
    }

}
