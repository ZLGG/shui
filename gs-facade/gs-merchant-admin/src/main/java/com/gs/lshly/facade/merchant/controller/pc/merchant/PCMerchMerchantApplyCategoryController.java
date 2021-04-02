package com.gs.lshly.facade.merchant.controller.pc.merchant;

import com.gs.lshly.common.constants.MsgConst;
import com.gs.lshly.common.enums.BusinessTypeEnum;
import com.gs.lshly.common.enums.LegalTypeEnum;
import com.gs.lshly.common.enums.TerminalEnum;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.response.ResponseData;
import com.gs.lshly.common.struct.common.CorpTypeDictDTO;
import com.gs.lshly.common.struct.common.CorpTypeDictVO;
import com.gs.lshly.common.struct.common.LegalDictDTO;
import com.gs.lshly.common.struct.common.LegalDictVO;
import com.gs.lshly.common.struct.common.dto.CommonMerchantApplyDTO;
import com.gs.lshly.common.struct.common.qto.CommonMerchantApplyQTO;
import com.gs.lshly.common.struct.common.vo.CommonMerchantApplyVO;
import com.gs.lshly.common.struct.merchadmin.pc.commodity.vo.PCMerchGoodsCategoryVO;
import com.gs.lshly.common.struct.platadmin.foundation.qto.CorpTypeDictQTO;
import com.gs.lshly.middleware.auth.rbac.Module;
import com.gs.lshly.rpc.api.common.ICommonMerchantApplyRpc;
import com.gs.lshly.rpc.api.common.ICorpTypeDictRpc;
import com.gs.lshly.rpc.api.common.ILegalDictRpc;
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
* @since 2020-10-19
*/
@RestController
@RequestMapping("/merchadmin/merchantApplyCategory")
@Api(tags = "申请商品类目申请管理",description = " ")
public class PCMerchMerchantApplyCategoryController {


    @DubboReference
    private ICommonMerchantApplyRpc commonMerchantApplyRpc;


    @ApiOperation("商品类目申请可申请列表")
    @GetMapping(value = "/categoryList")
    public ResponseData<List<PCMerchGoodsCategoryVO.ListVO>> categoryList() {
        CommonMerchantApplyQTO.CategoryQTO categoryQTO = new CommonMerchantApplyQTO.CategoryQTO();
        categoryQTO.setTerminal(TerminalEnum.BBB.getCode());
        return ResponseData.data(commonMerchantApplyRpc.categoryList(categoryQTO));
    }

    @ApiOperation("商品类目申请提交")
    @PostMapping(value = "/applyCategory")
    public ResponseData<String> applyCategory(@Valid @RequestBody CommonMerchantApplyDTO.ApplyCategoryDTO dto) {
        dto.setTerminal(TerminalEnum.BBB.getCode());
        commonMerchantApplyRpc.editApplyCategory(dto);
        return ResponseData.success(MsgConst.SUBMIT_SUCCESS);
    }

    @ApiOperation("商品类目申请记录列表")
    @GetMapping(value = "/applyCategoryRecord")
    public ResponseData<PageData<CommonMerchantApplyVO.ApplyCategoryRecordVO>> applyCategoryRecord(CommonMerchantApplyQTO.ApplyCategoryQTO qto) {
       qto.setTerminal(TerminalEnum.BBB.getCode());
        return ResponseData.data(commonMerchantApplyRpc.applyCategoryRecord(qto));
    }

    @ApiOperation("商品类目申请记录删除(待审状态可删)")
    @DeleteMapping(value = "/deleteCategoryRecord/{id}")
    public ResponseData<Void> deleteCategoryRecord(@PathVariable String id) {
        commonMerchantApplyRpc.deleteCategoryRecord(new CommonMerchantApplyDTO.IdDTO(id));
        return ResponseData.success(MsgConst.DELETE_SUCCESS);
    }
}
