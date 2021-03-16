package com.gs.lshly.facade.platform.controller.merchant;
import com.gs.lshly.common.constants.MsgConst;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.response.ResponseData;
import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.common.struct.common.CommonShopVO;
import com.gs.lshly.common.struct.platadmin.merchant.dto.MerchantArticleDTO;
import com.gs.lshly.common.struct.platadmin.merchant.qto.MerchantArticleQTO;
import com.gs.lshly.common.struct.platadmin.merchant.vo.MerchantArticleVO;
import com.gs.lshly.middleware.auth.rbac.Func;
import com.gs.lshly.middleware.auth.rbac.Module;
import com.gs.lshly.rpc.api.common.ICommonShopRpc;
import com.gs.lshly.rpc.api.platadmin.merchant.IMerchantArticleRpc;
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
* @since 2020-11-09
*/
@RestController
@RequestMapping("/platadmin/merchantArticle")
@Api(tags = "商家文章管理",description = " ")
@Module(code = "articleBusiness", parent = "site", name = "商家文章", index = 4)
public class MerchantArticleController {

    @DubboReference
    private IMerchantArticleRpc MerchantArticleRpc;
    @DubboReference
    private ICommonShopRpc commonShopRpc;

    @ApiOperation("商家列表")
    @GetMapping("/merchantList")
    @Func(code="view", name="查")
    public ResponseData<List<CommonShopVO.MerchantListVO>> merchantList() {
        return ResponseData.data(commonShopRpc.merchantList(new BaseDTO()));
    }

    @ApiOperation("商家文章列表")
    @GetMapping("")
    @Func(code="view", name="查")
    public ResponseData<PageData<MerchantArticleVO.ListVO>> pageList(MerchantArticleQTO.QTO qto) {
        return ResponseData.data(MerchantArticleRpc.pageData(qto));
    }


    @ApiOperation("商家文章详情")
    @GetMapping(value = "/{id}")
    @Func(code="view", name="查")
    public ResponseData<MerchantArticleVO.DetailVO> get(@PathVariable String id) {
        return ResponseData.data(MerchantArticleRpc.detailMerchantArticle(new MerchantArticleDTO.IdDTO(id)));
    }

    @ApiOperation("删除商家文章")
    @PostMapping(value = "/deleteBatch")
    @Func(code="delete", name="删")
    public ResponseData<Void> deleteBatch(@Valid @RequestBody MerchantArticleDTO.IdListDTO dto) {
        MerchantArticleRpc.deleteBatchMerchantArticle(dto);
        return ResponseData.success(MsgConst.DELETE_SUCCESS);
    }


    @ApiOperation("审核商家文章")
    @PutMapping(value = "/{id}")
    @Func(code="edit", name="改")
    public ResponseData<Void> apply(@PathVariable String id,MerchantArticleDTO.ApplyDTO dto) {
        dto.setId(id);
        MerchantArticleRpc.applyMerchantArticle(dto);
        return ResponseData.success(MsgConst.APPLY_SUCCESS);
    }

}
