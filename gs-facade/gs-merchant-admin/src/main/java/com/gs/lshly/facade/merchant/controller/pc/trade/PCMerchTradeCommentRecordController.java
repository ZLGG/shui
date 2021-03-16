package com.gs.lshly.facade.merchant.controller.pc.trade;
import com.gs.lshly.common.constants.MsgConst;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.response.ResponseData;
import com.gs.lshly.common.struct.merchadmin.pc.trade.dto.PCMerchTradeCommentRecordDTO;
import com.gs.lshly.common.struct.merchadmin.pc.trade.qto.PCMerchTradeCommentRecordQTO;
import com.gs.lshly.common.struct.merchadmin.pc.trade.vo.PCMerchTradeCommentRecordVO;
import com.gs.lshly.rpc.api.merchadmin.pc.trade.IPCMerchTradeCommentRecordRpc;
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
@RequestMapping("/merchadmin/tradeCommentRecord")
@Api(tags = "商家订单交易评论记录管理")
public class PCMerchTradeCommentRecordController {

    @DubboReference
    private IPCMerchTradeCommentRecordRpc pcMerchTradeCommentRecordRpc;

    @ApiOperation("商家订单交易评论记录列表")
    @GetMapping("")
    public ResponseData<PageData<PCMerchTradeCommentRecordVO.ListVO>> list(PCMerchTradeCommentRecordQTO.QTO qto) {
        return ResponseData.data(pcMerchTradeCommentRecordRpc.pageData(qto));
    }

    @ApiOperation("商家订单交易评论记录详情")
    @GetMapping(value = "/{id}")
    public ResponseData<PCMerchTradeCommentRecordVO.DetailVO> get(@PathVariable String id) {
        return ResponseData.data(pcMerchTradeCommentRecordRpc.detailTradeCommentRecord(new PCMerchTradeCommentRecordDTO.IdDTO(id)));
    }

    @ApiOperation("新增商家订单交易评论记录")
    @PostMapping("")
    public ResponseData<Void> add(@Valid @RequestBody PCMerchTradeCommentRecordDTO.ETO dto) {
            pcMerchTradeCommentRecordRpc.addTradeCommentRecord(dto);
        return ResponseData.success(MsgConst.ADD_SUCCESS);
    }

    @ApiOperation("删除商家订单交易评论记录")
    @DeleteMapping(value = "/{id}")
    public ResponseData<Void> delete(@PathVariable String id) {
        PCMerchTradeCommentRecordDTO.IdDTO dto = new PCMerchTradeCommentRecordDTO.IdDTO(id);
        pcMerchTradeCommentRecordRpc.deleteTradeCommentRecord(dto);
        return ResponseData.success(MsgConst.DELETE_SUCCESS);
    }


    @ApiOperation("修改商家订单交易评论记录")
    @PutMapping(value = "/{id}")
    public ResponseData<Void> update(@PathVariable String id, @Valid @RequestBody PCMerchTradeCommentRecordDTO.ETO eto) {
        eto.setId(id);
        pcMerchTradeCommentRecordRpc.editTradeCommentRecord(eto);
        return ResponseData.success(MsgConst.UPDATE_SUCCESS);
    }

}
