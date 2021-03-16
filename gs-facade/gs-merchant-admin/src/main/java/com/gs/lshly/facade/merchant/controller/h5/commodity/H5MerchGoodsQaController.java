package com.gs.lshly.facade.merchant.controller.h5.commodity;
import com.gs.lshly.common.constants.MsgConst;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.response.ResponseData;
import com.gs.lshly.common.struct.merchadmin.h5.commodity.dto.H5MerchGoodsQaDTO;
import com.gs.lshly.common.struct.merchadmin.h5.commodity.qto.H5MerchGoodsQaQTO;
import com.gs.lshly.common.struct.merchadmin.h5.commodity.vo.H5MerchGoodsQaVO;
import com.gs.lshly.rpc.api.merchadmin.h5.commodity.IH5MerchGoodsQaRpc;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.*;

/**
* <p>
*  前端控制器
* </p>
*
* @author zst
* @since 2021-01-22
*/
@RestController
@RequestMapping("/merchadmin/goodsQa")
@Api(tags = "H5商品咨询管理")
public class H5MerchGoodsQaController {

    @DubboReference
    private IH5MerchGoodsQaRpc h5MerchGoodsQaRpc;

    @ApiOperation("商品咨询管理列表")
    @GetMapping("")
    public ResponseData<PageData<H5MerchGoodsQaVO.ReplyListVO>> pageMerchantH5GoodsQa(H5MerchGoodsQaQTO.GoodsQaQTO qto) {
        return ResponseData.data(h5MerchGoodsQaRpc.pageMerchantH5GoodsQa(qto));
    }

    @ApiOperation("商家回复该商品咨询问题")
    @PutMapping(value = "reply/{id}")
    public ResponseData<Void> reply(@PathVariable String id, @RequestBody H5MerchGoodsQaDTO.MerchantReplyETO eto) {
        eto.setId(id);
        h5MerchGoodsQaRpc.replyGoodsQa(eto);
        return ResponseData.success(MsgConst.SAVE_SUCCESS);
    }

    @ApiOperation("是否将商品咨询显示到商品详情页")
    @PutMapping(value = "showOrHidden/{id}")
    public ResponseData<Void> showOrHidden(@PathVariable String id, @RequestBody H5MerchGoodsQaDTO.ShowContentETO eto) {
        eto.setId(id);
        h5MerchGoodsQaRpc.IsShowGoodsQaContent(eto);
        return ResponseData.success(MsgConst.SAVE_SUCCESS);
    }

    @ApiOperation("删除商品咨询回复")
    @PutMapping(value = "deleteReply/{id}")
    public ResponseData<Void> deleteReply(@PathVariable String id) {
        H5MerchGoodsQaDTO.IdDTO dto = new H5MerchGoodsQaDTO.IdDTO(id);
        h5MerchGoodsQaRpc.deleteGoodsQaReply(dto);
        return ResponseData.success(MsgConst.SAVE_SUCCESS);
    }
}
