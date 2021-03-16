package com.gs.lshly.biz.support.user.rpc.platadmin;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.platadmin.user.dto.UserLeveDictDTO;
import com.gs.lshly.common.struct.platadmin.user.qto.UserLeveDictQTO;
import com.gs.lshly.common.struct.platadmin.user.vo.UserLeveDictVO;
import com.gs.lshly.biz.support.user.service.platadmin.IUserLeveDictService;
import com.gs.lshly.rpc.api.platadmin.user.IUserLeveDictRpc;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
*
* @author xxfc
* @since 2020-10-05
*/
@DubboService
public class UserLeveDictRpc implements IUserLeveDictRpc{

    @Autowired
    private IUserLeveDictService userLeveDictService;

    @Override
    public PageData<UserLeveDictVO.ListVO> pageData(UserLeveDictQTO.QTO qto){
        return userLeveDictService.pageData(qto);
    }

    @Override
    public void addUserLeveDict(UserLeveDictDTO.ETO eto){
        userLeveDictService.addUserLeveDict(eto);
    }

    @Override
    public void deleteBatchUserLeveDict(UserLeveDictDTO.IdListDTO dto){
        userLeveDictService.deleteBatchUserLeveDict(dto);
    }

    @Override
    public void editUserLeveDict(UserLeveDictDTO.ETO eto){
        userLeveDictService.editUserLeveDict(eto);
    }

    @Override
    public UserLeveDictVO.DetailVO detailUserLeveDict(UserLeveDictDTO.IdDTO dto){
        return userLeveDictService.detailUserLeveDict(dto);
    }

    @Override
    public List<UserLeveDictVO.LevelListVO> lisLevelListVO(UserLeveDictDTO.UsingTypeDTO dto) {
        return userLeveDictService.lisLevelListVO(dto);
    }

}