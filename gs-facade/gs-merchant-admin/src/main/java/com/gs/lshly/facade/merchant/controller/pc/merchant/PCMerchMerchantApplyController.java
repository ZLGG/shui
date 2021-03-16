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
import com.gs.lshly.common.struct.common.vo.CommonMerchantApplyVO;
import com.gs.lshly.common.struct.merchadmin.pc.commodity.vo.PCMerchGoodsCategoryVO;
import com.gs.lshly.common.struct.common.qto.CommonMerchantApplyQTO;
import com.gs.lshly.common.struct.platadmin.foundation.qto.CorpTypeDictQTO;
import com.gs.lshly.rpc.api.common.ICorpTypeDictRpc;
import com.gs.lshly.rpc.api.common.ILegalDictRpc;
import com.gs.lshly.rpc.api.common.ICommonMerchantApplyRpc;
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
@RequestMapping("/merchadmin/merchantApply")
@Api(tags = "商家入驻申请管理",description = " ")
public class PCMerchMerchantApplyController {


    @DubboReference
    private ICommonMerchantApplyRpc commonMerchantApplyRpc;

    @DubboReference
    private ILegalDictRpc legalDictRpc;

    @DubboReference
    private ICorpTypeDictRpc corpTypeDictRpc;

    @ApiOperation("商家入驻（企业类型选择列表）")
    @GetMapping(value = "/corpTypeList")
    public ResponseData<List<CorpTypeDictVO.SimpleList>> corpTypeList() {
        CorpTypeDictQTO.SimpleQTO simpleQTO = new CorpTypeDictQTO.SimpleQTO();
        simpleQTO.setBusinessType(BusinessTypeEnum.卖家.getCode());
        return ResponseData.data(corpTypeDictRpc.simpleList(simpleQTO));
    }

    @ApiOperation("商家入驻企业类型证照列表（企业类型ID）")
    @GetMapping(value = "/corpTypeCertList/{id}")
    public ResponseData<CorpTypeDictVO.DetailVO> corpTypeCertList(@PathVariable String id) {
        return ResponseData.data(corpTypeDictRpc.detailCorpTypeDict(new CorpTypeDictDTO.IdDTO(id)));
    }

    @ApiOperation("商家入驻企业字典查询(企业注册号)")
    @GetMapping(value = "/legalQuery/{corpLicenseNum}")
    public ResponseData<LegalDictVO.DetailVO> applyMerchantConfirmQuery(@PathVariable String corpLicenseNum) {
        LegalDictDTO.LicenseNumDTO dto = new LegalDictDTO.LicenseNumDTO();
        dto.setCorpLicenseNum(corpLicenseNum);
        return  ResponseData.data(legalDictRpc.detailLegalDict(dto));
    }

    @ApiOperation("商家入驻流程查询(分段),至少会返回一个分段位置")
    @GetMapping(value = "/applyFlowQuery")
    public ResponseData<CommonMerchantApplyVO.DetailVO> applyFlowQuery(CommonMerchantApplyDTO.QueryDTO queryDTO) {
        CommonMerchantApplyVO.DetailVO detailVO = commonMerchantApplyRpc.detailMerchantApply(queryDTO);
        return ResponseData.data(detailVO);
    }

    @ApiOperation("商家入驻流程提交(分段)")
    @PostMapping(value = "/applyFlow")
    public ResponseData<String> applyFlow(@Valid @RequestBody CommonMerchantApplyDTO.ETO eto) {
        eto.setLegalType(LegalTypeEnum.企业.getCode());
        String applyId =  commonMerchantApplyRpc.editMerchantApply(eto);
        return ResponseData.data(applyId);
    }

}
