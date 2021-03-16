package com.gs.lshly.biz.support.user.service.common;

import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.common.struct.bbb.pc.user.qto.PCBbbUserUser2bApplyLogQTO;
import com.gs.lshly.common.struct.bbb.pc.user.vo.BbbUserVO;
import com.gs.lshly.common.struct.bbb.pc.user.vo.PCBbbUserUser2bApplyLogVO;
import com.gs.lshly.common.struct.common.LegalDictVO;
import com.gs.lshly.common.struct.common.dto.CommonUserUser2bApplyDTO;
import com.gs.lshly.common.struct.common.vo.CommonUserUser2bApplyVO;

import java.util.List;

public interface ICommonUserUser2bApplyService {

    void editUserUser2bApply(CommonUserUser2bApplyDTO.ETO eto);


    /**
     * 企业会员通过邀请码加入私域店铺
     * @param dto
     */
    void joinPrivateShop(CommonUserUser2bApplyDTO.ShareCodeDTO dto);

    /**
     * 获取登录用户类型
     * @param dto
     * @return
     */
    BbbUserVO.UserTypeVO getUserType(BaseDTO dto);

    /**
     * 通过统一社会获取企业认证信息
     * @return
     */
    CommonUserUser2bApplyVO.DetailVO getAppleInfoVOBy(CommonUserUser2bApplyDTO.CreditCodeDTO dto);

    CommonUserUser2bApplyVO.DetailVO detailUserUser2bApply(BaseDTO dto);

    CommonUserUser2bApplyVO.ApplyRecordVO applyRecord(BaseDTO dto);

    LegalDictVO.DetailVO corpInfo(BaseDTO dto);

    /**
     * 获取企业认证记录
     * @param qto
     * @return
     */
    PageData<PCBbbUserUser2bApplyLogVO.ListVO> pageApplyLogData(PCBbbUserUser2bApplyLogQTO.QTO qto);
}