package com.gs.lshly.facade.bbb.controller.pc.user;

import com.gs.lshly.common.constants.MsgConst;
import com.gs.lshly.common.enums.BusinessTypeEnum;
import com.gs.lshly.common.exception.BusinessException;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.response.ResponseData;
import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.common.struct.bbb.pc.user.qto.PCBbbUserUser2bApplyLogQTO;
import com.gs.lshly.common.struct.bbb.pc.user.vo.PCBbbUserUser2bApplyLogVO;
import com.gs.lshly.common.struct.common.dto.CommonUserUser2bApplyDTO;
import com.gs.lshly.common.struct.common.vo.CommonUserUser2bApplyVO;
import com.gs.lshly.common.struct.common.CorpTypeDictDTO;
import com.gs.lshly.common.struct.common.CorpTypeDictVO;
import com.gs.lshly.common.struct.common.LegalDictVO;
import com.gs.lshly.common.struct.platadmin.foundation.qto.CorpTypeDictQTO;
import com.gs.lshly.rpc.api.bbb.pc.user.IBbbUserRpc;
import com.gs.lshly.rpc.api.common.ICommonUserUser2bApplyRpc;
import com.gs.lshly.rpc.api.common.ICorpTypeDictRpc;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
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
* @since 2020-10-27
*/
@RestController
@RequestMapping("/bbb/userCenter/apply")
@Api(tags = "企业员会入驻",description = " ")
public class BbbUserApplyController {

    @DubboReference
    private ICorpTypeDictRpc corpTypeDictRpc;

    @DubboReference
    private ICommonUserUser2bApplyRpc commonUserUser2bApplyRpc;

    @ApiOperation("会员入驻信息提交（主动入驻提交）")
    @PostMapping("/apply")
    public ResponseData<Void> apply(@Valid @RequestBody CommonUserUser2bApplyDTO.ETO dto) {
        commonUserUser2bApplyRpc.editUserUser2bApply(dto);
        return ResponseData.data(MsgConst.SUBMIT_SUCCESS);
    }

    @ApiOperation("企业会员通过邀请码加人私域店铺")
    @GetMapping("/jionPrivateShop")
    public ResponseData<Void> jionPrivateShop(CommonUserUser2bApplyDTO.ShareCodeDTO dto) {
        commonUserUser2bApplyRpc.joinPrivateShop(dto);
        return ResponseData.data(MsgConst.SUBMIT_SUCCESS);
    }

    @ApiOperation("获取登录用户类型")
    @GetMapping("/getUserType")
    public ResponseData<Void> getUserType(BaseDTO dto) {
        return ResponseData.data(commonUserUser2bApplyRpc.getUserType(dto));
    }

    @ApiOperation("通过统一社会信用码获取企业信息")
    @GetMapping("/getApplyByCreditCode")
    public ResponseData<Void> getApplyByCreditCode(CommonUserUser2bApplyDTO.CreditCodeDTO dto) {
        return ResponseData.data(commonUserUser2bApplyRpc.getAppleInfoVOBy(dto));
    }

    @ApiOperation("会员入驻信息提交（商家分享邀请）")
    @PostMapping("/fromApply/{shopId}")
    public ResponseData<Void> fromShopApply(@PathVariable String shopId, @Valid @RequestBody  CommonUserUser2bApplyDTO.ETO dto) {
        if(StringUtils.isBlank(shopId)){
            throw new BusinessException("邀请店铺ID不能为空");
        }
        dto.setFromShopId(shopId);
        commonUserUser2bApplyRpc.editUserUser2bApply(dto);
        return ResponseData.data(MsgConst.SUBMIT_SUCCESS);
    }

    @ApiOperation("会员入驻申请信息查询")
    @GetMapping("/detail")
    public ResponseData<CommonUserUser2bApplyVO.DetailVO> detail() {
        return ResponseData.data(commonUserUser2bApplyRpc.detailUserUser2bApply(new BaseDTO()));
    }

    @ApiOperation("企业类型选择列表(2B会员入驻)")
    @GetMapping(value = "/corpTypeList")
    public ResponseData<List<CorpTypeDictVO.SimpleList>> corpTypeList() {
        CorpTypeDictQTO.SimpleQTO simpleQTO = new CorpTypeDictQTO.SimpleQTO();
        simpleQTO.setBusinessType(BusinessTypeEnum.买家.getCode());
        return ResponseData.data(corpTypeDictRpc.simpleList(simpleQTO));
    }

    @ApiOperation("企业类型证照列表(2B会员入驻)（企业类型ID）")
    @GetMapping(value = "/corpTypeCertList/{id}")
    public ResponseData<CorpTypeDictVO.DetailVO> corpTypeCertList(@PathVariable String id) {
        return ResponseData.data(corpTypeDictRpc.detailCorpTypeDict(new CorpTypeDictDTO.IdDTO(id)));
    }


    @ApiOperation("我的企业认证信息（审核已经通过）")
    @GetMapping("/corpInfo")
    public  ResponseData<LegalDictVO.DetailVO> corpInfo() {
        return ResponseData.data(commonUserUser2bApplyRpc.corpInfo(new BaseDTO()));
    }

    @ApiOperation("我的认证记录")
    @GetMapping("/applyRecord")
    public  ResponseData<CommonUserUser2bApplyVO.ApplyRecordVO> applyRecord() {
        return ResponseData.data(commonUserUser2bApplyRpc.applyRecord(new BaseDTO()));
    }

    @ApiOperation("我的企业认证记录")
    @GetMapping("/applyLog")
    public  ResponseData<PageData<PCBbbUserUser2bApplyLogVO.ListVO>> applyLog(PCBbbUserUser2bApplyLogQTO.QTO qto) {
        return ResponseData.data(commonUserUser2bApplyRpc.pageApplyLogData(qto));
    }

}
