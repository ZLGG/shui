package com.gs.lshly.facade.merchant.controller.pc.merchant;
import com.gs.lshly.common.constants.MsgConst;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.response.ResponseData;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.dto.PCMerchMerchantArticleCategoryDTO;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.qto.PCMerchMerchantArticleCategoryQTO;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.vo.PCMerchMerchantArticleCategoryVO;
import com.gs.lshly.rpc.api.merchadmin.pc.merchant.IPCMerchMerchantArticleCategoryRpc;
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
@RequestMapping("/merchadmin/merchantArticleCategory")
@Api(tags = "商家文章栏目管理")
public class PCMerchMerchantArticleCategoryController {

    @DubboReference
    private IPCMerchMerchantArticleCategoryRpc pcMerchMerchantArticleCategoryRpc;

    @ApiOperation("商家文章栏目列表")
    @GetMapping("")
    public ResponseData<PageData<PCMerchMerchantArticleCategoryVO.ListVO>> list(PCMerchMerchantArticleCategoryQTO.QTO qto) {
        return ResponseData.data(pcMerchMerchantArticleCategoryRpc.pageData(qto));
    }

    @ApiOperation("新增商家文章栏目")
    @PostMapping("")
    public ResponseData<Void> add(@Valid @RequestBody PCMerchMerchantArticleCategoryDTO.ETO dto) {
            pcMerchMerchantArticleCategoryRpc.addMerchantArticleCategory(dto);
        return ResponseData.success(MsgConst.ADD_SUCCESS);
    }

    @ApiOperation("删除商家文章栏目")
    @DeleteMapping(value = "/{id}")
    public ResponseData<Void> delete(@PathVariable String id) {
        PCMerchMerchantArticleCategoryDTO.IdDTO dto = new PCMerchMerchantArticleCategoryDTO.IdDTO(id);
        pcMerchMerchantArticleCategoryRpc.deleteMerchantArticleCategory(dto);
        return ResponseData.success(MsgConst.DELETE_SUCCESS);
    }


    @ApiOperation("修改商家文章栏目")
    @PutMapping(value = "/{id}")
    public ResponseData<Void> update(@PathVariable String id, @Valid @RequestBody PCMerchMerchantArticleCategoryDTO.ETO eto) {
        eto.setId(id);
        pcMerchMerchantArticleCategoryRpc.editMerchantArticleCategory(eto);
        return ResponseData.success(MsgConst.UPDATE_SUCCESS);
    }

}