package com.gs.lshly.facade.merchant.controller.pc.merchant;
import com.gs.lshly.common.constants.MsgConst;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.response.ResponseData;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.dto.PCMerchMerchantArticleDTO;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.qto.PCMerchMerchantArticleQTO;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.vo.PCMerchMerchantArticleVO;
import com.gs.lshly.middleware.auth.rbac.Func;
import com.gs.lshly.middleware.auth.rbac.Module;
import com.gs.lshly.rpc.api.merchadmin.pc.merchant.IPCMerchMerchantArticleRpc;
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
* @author hyy
* @since 2020-11-07
*/
@RestController
@RequestMapping("/merchadmin/merchantArticle")
@Api(tags = "商家文章管理",description = " ")
public class PCMerchMerchantArticleController {

    @DubboReference
    private IPCMerchMerchantArticleRpc pcMerchMerchantArticleRpc;

    @ApiOperation("商家文章列表")
    @GetMapping("")
    public ResponseData<PageData<PCMerchMerchantArticleVO.ListVO>> list(PCMerchMerchantArticleQTO.QTO qto) {
        return ResponseData.data(pcMerchMerchantArticleRpc.pageData(qto));
    }

    @ApiOperation("商家文章详情")
    @GetMapping(value = "/{id}")
    public ResponseData<PCMerchMerchantArticleVO.DetailVO> get(@PathVariable String id) {
        return ResponseData.data(pcMerchMerchantArticleRpc.detailMerchantArticle(new PCMerchMerchantArticleDTO.IdDTO(id)));
    }

    @ApiOperation("新增商家文章")
    @PostMapping("")
    public ResponseData<Void> add(@Valid @RequestBody PCMerchMerchantArticleDTO.ETO dto) {
        pcMerchMerchantArticleRpc.addMerchantArticle(dto);
        return ResponseData.success(MsgConst.ADD_SUCCESS);
    }

    @ApiOperation("删除商家文章")
    @DeleteMapping(value = "/{id}")
    public ResponseData<Void> delete(@PathVariable String id) {
        pcMerchMerchantArticleRpc.deleteMerchantArticle(new PCMerchMerchantArticleDTO.IdDTO(id));
        return ResponseData.success(MsgConst.DELETE_SUCCESS);
    }


    @ApiOperation("修改商家文章")
    @PutMapping(value = "/{id}")
    public ResponseData<Void> update(@PathVariable String id, @Valid @RequestBody PCMerchMerchantArticleDTO.ETO eto) {
        eto.setId(id);
        pcMerchMerchantArticleRpc.editMerchantArticle(eto);
        return ResponseData.success(MsgConst.UPDATE_SUCCESS);
    }

    @ApiOperation("商家文章链接地址")
    @GetMapping(value = "/linkUrl/{id}")
    public ResponseData<PCMerchMerchantArticleVO.LinkListVO> listLinkUrl(@PathVariable String id) {
        PCMerchMerchantArticleVO.LinkListVO linkListVO =  pcMerchMerchantArticleRpc.listLinkUrl(new PCMerchMerchantArticleDTO.IdDTO(id));
        linkListVO.setLinkPc("/merchadmin/merchantArticle/"+  id);
        linkListVO.setLinkH5("/merchadmin/merchantArticle/"+  id);
        return  ResponseData.data(linkListVO);
    }

}
