package com.gs.lshly.biz.support.user.rpc.merchadmin.H5;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.common.struct.merchadmin.h5.user.dto.H5MerchUserPrivateUserDTO;
import com.gs.lshly.common.struct.merchadmin.h5.user.qto.H5MerchUserPrivateUserQTO;
import com.gs.lshly.common.struct.merchadmin.h5.user.vo.H5MerchUserPrivateUserVO;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.vo.PCMerchMerchantUserTypeVO;
import com.gs.lshly.common.struct.merchadmin.pc.user.dto.PCMerchUserDTO;
import com.gs.lshly.common.struct.merchadmin.pc.user.qto.PCMerchUserQTO;
import com.gs.lshly.common.struct.merchadmin.pc.user.vo.PCMerchUserVO;
import com.gs.lshly.rpc.api.merchadmin.h5.user.IH5MerchUserPrivateUserRpc;
import com.gs.lshly.biz.support.user.service.merchadmin.h5.IH5MerchUserPrivateUserService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
*
* @author zst
* @since 2021-01-20
*/
@DubboService
public class H5MerchUserPrivateUserRpc implements IH5MerchUserPrivateUserRpc{
    @Autowired
    private IH5MerchUserPrivateUserService  h5MerchUserPrivateUserService;

    @Override
    public PageData<H5MerchUserPrivateUserVO.ListVO> pageData(H5MerchUserPrivateUserQTO.QTO qto){
        return h5MerchUserPrivateUserService.pageData(qto);
    }

    @Override
    public void addUserPrivateUser(H5MerchUserPrivateUserDTO.ETO eto){
        h5MerchUserPrivateUserService.addUserPrivateUser(eto);
    }

    @Override
    public void deleteUserPrivateUser(H5MerchUserPrivateUserDTO.IdDTO dto){
        h5MerchUserPrivateUserService.deleteUserPrivateUser(dto);
    }


    @Override
    public void editUserPrivateUser(H5MerchUserPrivateUserDTO.ETO eto){
        h5MerchUserPrivateUserService.editUserPrivateUser(eto);
    }

    @Override
    public H5MerchUserPrivateUserVO.PrivateUserDetailVO detailUserPrivateUser(H5MerchUserPrivateUserDTO.IdDTO dto){
        return  h5MerchUserPrivateUserService.detailUserPrivateUser(dto);
    }

    @Override
    public PageData<H5MerchUserPrivateUserVO.ApplyListVO> applyPageList(H5MerchUserPrivateUserQTO.ApplyListQTO qto) {
        return h5MerchUserPrivateUserService.applyPageList(qto);
    }

    @Override
    public H5MerchUserPrivateUserVO.ApplyPrivateUserDetailVO applyDetailUser(PCMerchUserDTO.IdDTO dto) {
        return  h5MerchUserPrivateUserService.applyDetailUser(dto);
    }

    @Override
    public void apply(H5MerchUserPrivateUserDTO.ApplyDTO dto){
        h5MerchUserPrivateUserService.apply(dto);
    }

}