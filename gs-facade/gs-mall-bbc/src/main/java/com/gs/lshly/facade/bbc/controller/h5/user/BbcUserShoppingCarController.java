package com.gs.lshly.facade.bbc.controller.h5.user;
import com.gs.lshly.common.constants.MsgConst;
import com.gs.lshly.common.enums.SubjectEnum;
import com.gs.lshly.common.enums.TerminalEnum;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.response.ResponseData;
import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.common.struct.bbc.commodity.qto.BbcGoodsInfoQTO;
import com.gs.lshly.common.struct.bbc.commodity.vo.BbcGoodsInfoVO;
import com.gs.lshly.common.struct.bbc.foundation.qto.BbcSiteTopicQTO;
import com.gs.lshly.common.struct.bbc.user.dto.BbcUserShoppingCarDTO;
import com.gs.lshly.common.struct.bbc.user.qto.BbcUserShoppingCarQTO;
import com.gs.lshly.common.struct.bbc.user.vo.BbcUserShoppingCarVO;
import com.gs.lshly.common.struct.platadmin.commodity.vo.GoodsInfoVO;
import com.gs.lshly.common.utils.BeanCopyUtils;
import com.gs.lshly.rpc.api.bbc.commodity.IBbcGoodsInfoRpc;
import com.gs.lshly.rpc.api.bbc.user.IBbcUserShoppingCarRpc;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

/**
* <p>
*  前端控制器
* </p>
*
* @author xxfc
* @since 2020-10-28
*/
@RestController
@RequestMapping("/bbc/userCenter/userShoppingCar")
@Api(tags = "会员购物车管理-v1.1.0")
@SuppressWarnings("unchecked")
public class BbcUserShoppingCarController {

    @DubboReference
    private IBbcUserShoppingCarRpc bbcUserShoppingCarRpc;
    
    @DubboReference
    private IBbcGoodsInfoRpc bbcGoodsInfoRpc; 


	@ApiOperation("购物车商品列表-v1.1.0")
    @GetMapping("")
    public ResponseData<BbcUserShoppingCarVO.HomeVO> list(BbcUserShoppingCarQTO.QTO qto) {
        return ResponseData.data(bbcUserShoppingCarRpc.list(qto));
    }

    @ApiOperation("购物车项数量统计(角标数字)")
    @GetMapping("/count")
    public ResponseData<BbcUserShoppingCarVO.CountVO> countShoppingCarGoods() {
        return ResponseData.data(bbcUserShoppingCarRpc.countShoppingCarGoods(new BaseDTO()));
    }

    @ApiOperation("新增购物车商品")
    @PostMapping("")
    public ResponseData<Void> add(@Valid @RequestBody BbcUserShoppingCarDTO.ETO dto) {
        bbcUserShoppingCarRpc.addUserShoppingCar(dto);
        return ResponseData.success(MsgConst.ADD_SUCCESS);
    }
    
    @ApiOperation("修改购物车SKUID")
    @PostMapping("/modifySku")
    public ResponseData<Void> modifySku(@Valid @RequestBody BbcUserShoppingCarDTO.ModifySkuDTO dto) {
        bbcUserShoppingCarRpc.modifySku(dto);
        return ResponseData.success(MsgConst.ADD_SUCCESS);
    }

    @ApiOperation("删除/清空失效购物车商品-v1.1.0")
    @DeleteMapping(value = "/deleteBatch")
    public ResponseData<Void> deleteBatch(@Valid @RequestBody BbcUserShoppingCarDTO.IdListDTO dto) {
        bbcUserShoppingCarRpc.deleteBatchUserShoppingCar(dto);
        return ResponseData.success(MsgConst.DELETE_SUCCESS);
    }
    
    @ApiOperation("计算购物车商品合计金额-v1.1.0")
    @PostMapping(value = "/summation")
    public ResponseData<BbcUserShoppingCarVO.SummationVO> summation(@Valid @RequestBody BbcUserShoppingCarDTO.IdListDTO dto) {
        return ResponseData.data(bbcUserShoppingCarRpc.summationUserShoppingCar(dto));
    }


    @ApiOperation("改变购物车商品选中状态")
    @PutMapping(value = "/selectState/{id}")
    public ResponseData<Void> selectState(@PathVariable String id) {
        BbcUserShoppingCarDTO.SelectDTO eto = new BbcUserShoppingCarDTO.SelectDTO(id);
        bbcUserShoppingCarRpc.selectState(eto);
        return ResponseData.success(MsgConst.UPDATE_SUCCESS);
    }

    @ApiOperation("改变购物车商品选中状态（全选/返选）")
    @PutMapping(value = "/selectStateAll")
    public ResponseData<Void> selectStateAll(@Valid @RequestBody BbcUserShoppingCarDTO.SelectAllDTO dto) {
        bbcUserShoppingCarRpc.selectStateAll(dto);
        return ResponseData.success(MsgConst.UPDATE_SUCCESS);
    }

    @ApiOperation("改变购物车商品数量")
    @PutMapping(value = "/{id}")
    public ResponseData<Void> updateQuantity(@PathVariable String id, @Valid @RequestBody BbcUserShoppingCarDTO.QuantityDTO eto) {
        eto.setId(id);
        bbcUserShoppingCarRpc.changeQuantity(eto);
        return ResponseData.success(MsgConst.UPDATE_SUCCESS);
    }
    
    @ApiOperation("猜你喜欢-v1.1.0")
    @GetMapping("/enjoyList")
    public ResponseData<PageData<BbcGoodsInfoVO.GoodsListVO>> listEnjoy(BbcGoodsInfoQTO.EnjoyQTO qto) {
    	BbcGoodsInfoQTO.GoodsSearchListQTO goodsListQTO = new BbcGoodsInfoQTO.GoodsSearchListQTO();
    	BeanCopyUtils.copyProperties(qto, goodsListQTO);
    	return ResponseData.data(bbcGoodsInfoRpc.pageGoodsData(goodsListQTO));
    }
    

}
