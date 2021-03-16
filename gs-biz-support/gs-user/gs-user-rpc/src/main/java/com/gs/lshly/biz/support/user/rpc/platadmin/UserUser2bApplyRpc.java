package com.gs.lshly.biz.support.user.rpc.platadmin;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.gs.lshly.common.exception.BusinessException;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.platadmin.user.dto.UserUser2bApplyDTO;
import com.gs.lshly.common.struct.platadmin.user.qto.UserUser2bApplyQTO;
import com.gs.lshly.common.struct.platadmin.user.vo.UserUser2bApplyVO;
import com.gs.lshly.biz.support.user.service.platadmin.IUserUser2bApplyService;
import com.gs.lshly.rpc.api.platadmin.user.IUserUser2bApplyRpc;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

/**
*
* @author xxfc
* @since 2020-10-06
*/
@DubboService
public class UserUser2bApplyRpc implements IUserUser2bApplyRpc{

    @Autowired
    private IUserUser2bApplyService userUser2bApplyService;

    @Override
    public PageData<UserUser2bApplyVO.ListVO> pageData(UserUser2bApplyQTO.QTO qto){
        return userUser2bApplyService.pageData(qto);
    }

    @Override
    public void deleteUserUser2bApply(UserUser2bApplyDTO.IdDTO dto){
        userUser2bApplyService.deleteUserUser2bApply(dto);
    }

    @Override
    public void deleteBatchUserUser2bApply(UserUser2bApplyDTO.IdListDTO dto){
        userUser2bApplyService.deleteBatchUserUser2bApply(dto);
    }

    @Override
    public void editUserUser2bApply(UserUser2bApplyDTO.ETO eto){
        userUser2bApplyService.editUserUser2bApply(eto);
    }

    @Override
    public UserUser2bApplyVO.DetailVO detailUserUser2bApply(UserUser2bApplyDTO.IdDTO dto){
        return userUser2bApplyService.detailUserUser2bApply(dto);
    }

    @Override
    public void applyUserUser2bApply(UserUser2bApplyDTO.ApplyDTO dto) {
         userUser2bApplyService.applyUserUser2bApply(dto);
    }

}