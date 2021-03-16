package com.gs.lshly.biz.support.user.service.merchadmin.h5;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.common.struct.merchadmin.h5.user.dto.H5MerchUserPrivateUserDTO;
import com.gs.lshly.common.struct.merchadmin.h5.user.qto.H5MerchUserPrivateUserQTO;
import com.gs.lshly.common.struct.merchadmin.h5.user.vo.H5MerchUserPrivateUserVO;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.vo.PCMerchMerchantUserTypeVO;
import com.gs.lshly.common.struct.merchadmin.pc.user.dto.PCMerchUserDTO;
import com.gs.lshly.common.struct.merchadmin.pc.user.qto.PCMerchUserQTO;
import com.gs.lshly.common.struct.merchadmin.pc.user.vo.PCMerchUserVO;

import java.util.List;

public interface IH5MerchUserPrivateUserService {

    PageData<H5MerchUserPrivateUserVO.ListVO> pageData(H5MerchUserPrivateUserQTO.QTO qto);

    void addUserPrivateUser(H5MerchUserPrivateUserDTO.ETO eto);

    void deleteUserPrivateUser(H5MerchUserPrivateUserDTO.IdDTO dto);


    void editUserPrivateUser(H5MerchUserPrivateUserDTO.ETO eto);

    H5MerchUserPrivateUserVO.PrivateUserDetailVO detailUserPrivateUser(H5MerchUserPrivateUserDTO.IdDTO dto);

    /**
     * 会员审核列表
     * @param qto
     * @return
     */
    PageData<H5MerchUserPrivateUserVO.ApplyListVO> applyPageList(H5MerchUserPrivateUserQTO.ApplyListQTO qto);


    H5MerchUserPrivateUserVO.ApplyPrivateUserDetailVO applyDetailUser(PCMerchUserDTO.IdDTO dto);

    /**
     * 审核
     * @param dto
     */
    void apply(H5MerchUserPrivateUserDTO.ApplyDTO dto);

}