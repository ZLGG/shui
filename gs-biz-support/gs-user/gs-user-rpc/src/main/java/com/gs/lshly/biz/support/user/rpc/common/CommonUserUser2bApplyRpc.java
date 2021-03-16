package com.gs.lshly.biz.support.user.rpc.common;

import com.gs.lshly.biz.support.user.service.common.ICommonUserUser2bApplyService;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.common.struct.bbb.pc.user.qto.PCBbbUserUser2bApplyLogQTO;
import com.gs.lshly.common.struct.bbb.pc.user.vo.BbbUserVO;
import com.gs.lshly.common.struct.bbb.pc.user.vo.PCBbbUserUser2bApplyLogVO;
import com.gs.lshly.common.struct.common.LegalDictVO;
import com.gs.lshly.common.struct.common.dto.CommonUserUser2bApplyDTO;
import com.gs.lshly.common.struct.common.vo.CommonUserUser2bApplyVO;
import com.gs.lshly.rpc.api.common.ICommonUserUser2bApplyRpc;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author lxus
 */
@DubboService
public class CommonUserUser2bApplyRpc implements ICommonUserUser2bApplyRpc {

    @Autowired
    private ICommonUserUser2bApplyService commonUserUser2bApplyService;

    @Override
    public void editUserUser2bApply(CommonUserUser2bApplyDTO.ETO eto) {
        commonUserUser2bApplyService.editUserUser2bApply(eto);
    }

    @Override
    public void joinPrivateShop(CommonUserUser2bApplyDTO.ShareCodeDTO dto) {
        commonUserUser2bApplyService.joinPrivateShop(dto);
    }

    @Override
    public BbbUserVO.UserTypeVO getUserType(BaseDTO dto) {
        return commonUserUser2bApplyService.getUserType(dto);
    }

    @Override
    public CommonUserUser2bApplyVO.DetailVO getAppleInfoVOBy(CommonUserUser2bApplyDTO.CreditCodeDTO dto) {
        return commonUserUser2bApplyService.getAppleInfoVOBy(dto);
    }

    @Override
    public CommonUserUser2bApplyVO.DetailVO detailUserUser2bApply(BaseDTO dto) {
        return commonUserUser2bApplyService.detailUserUser2bApply(dto);
    }

    @Override
    public CommonUserUser2bApplyVO.ApplyRecordVO applyRecord(BaseDTO dto) {
        return commonUserUser2bApplyService.applyRecord(dto);
    }

    @Override
    public LegalDictVO.DetailVO corpInfo(BaseDTO dto) {
        return commonUserUser2bApplyService.corpInfo(dto);
    }

    @Override
    public PageData<PCBbbUserUser2bApplyLogVO.ListVO> pageApplyLogData(PCBbbUserUser2bApplyLogQTO.QTO qto) {
        return commonUserUser2bApplyService.pageApplyLogData(qto);
    }
}
