package com.gs.lshly.facade.bbc.controller.pc.user;
import com.gs.lshly.common.constants.MsgConst;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.response.ResponseData;
import com.gs.lshly.common.struct.bbb.pc.user.dto.PCBbbUserFrequentGoodsDTO;
import com.gs.lshly.common.struct.bbb.pc.user.qto.PCBbbUserFrequentGoodsQTO;
import com.gs.lshly.common.struct.bbb.pc.user.vo.PCBbbUserFrequentGoodsVO;
import com.gs.lshly.rpc.api.bbb.pc.user.IPCBbbUserFrequentRpc;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.*;

/**
* <p>
*  前端控制器
* </p>
*
* @author xxfc
* @since 2020-12-10
*/
@RestController
@RequestMapping("/bbc/pc/userCenter/userFrequentGoods")
@Api(tags = "2C PC常购商品管理")
public class BbcPcUserFrequentController {

    @DubboReference
    private IPCBbbUserFrequentRpc pcBbbUserFrequentGoodsRpc;

    @ApiOperation("常购商品列表")
    @GetMapping("")
    public ResponseData<PageData<PCBbbUserFrequentGoodsVO.ListVO>> pageData(PCBbbUserFrequentGoodsQTO.QTO qto) {
        return ResponseData.data(pcBbbUserFrequentGoodsRpc.pageData(qto));
    }


    @ApiOperation("常购商品添加")
    @PostMapping(value = "")
    public ResponseData<Void> add(PCBbbUserFrequentGoodsDTO.OneETO dto) {
        pcBbbUserFrequentGoodsRpc.addOne(dto);
        return ResponseData.success(MsgConst.SAVE_SUCCESS);
    }


    @ApiOperation("常购商品删除")
    @DeleteMapping(value = "/{id}")
    public ResponseData<Void> delete(@PathVariable String id) {
        PCBbbUserFrequentGoodsDTO.IdDTO dto = new PCBbbUserFrequentGoodsDTO.IdDTO(id);
        pcBbbUserFrequentGoodsRpc.deleteUserFrequentGoods(dto);
        return ResponseData.success(MsgConst.DELETE_SUCCESS);
    }


}
