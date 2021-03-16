package com.gs.lshly.biz.support.user.rpc.platadmin;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.platadmin.user.dto.UserLabelDictDTO;
import com.gs.lshly.common.struct.platadmin.user.qto.UserLabelDictQTO;
import com.gs.lshly.common.struct.platadmin.user.vo.UserLabelDictVO;
import com.gs.lshly.biz.support.user.service.platadmin.IUserLabelDictService;
import com.gs.lshly.rpc.api.platadmin.user.IUserLabelDictRpc;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
*
* @author xxfc
* @since 2020-10-05
*/
@DubboService
public class UserLabelDictRpc implements IUserLabelDictRpc{

    @Autowired
    private IUserLabelDictService userLabelDictService;

    @Override
    public PageData<UserLabelDictVO.ListVO> pageData(UserLabelDictQTO.QTO qto){
        return userLabelDictService.pageData(qto);
    }

    @Override
    public List<UserLabelDictVO.ListVO> list() {
        return userLabelDictService.list();
    }

    @Override
    public void addUserLabelDict(UserLabelDictDTO.ETO eto){
        userLabelDictService.addUserLabelDict(eto);
    }

    @Override
    public void deleteBatchUserLabelDict(UserLabelDictDTO.IdListDTO dto) {
        userLabelDictService.deleteBatchUserLabelDict(dto);
    }

    @Override
    public void editUserLabelDict(UserLabelDictDTO.ETO eto){
        userLabelDictService.editUserLabelDict(eto);
    }


    @Override
    public void addUserLabel(UserLabelDictDTO.AddUserLabelDTO dto) {
        userLabelDictService.addUserLabel(dto);
    }

    @Override
    public List<UserLabelDictVO.UserLabelVO> getMoreUserLabel(UserLabelDictQTO.UserIdListQTO qto) {
        return userLabelDictService.getMoreUserLabel(qto);
    }


}