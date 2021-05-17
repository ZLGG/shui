package com.gs.lshly.biz.support.user.rpc.platadmin;

import java.util.List;

import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import com.gs.lshly.biz.support.user.service.platadmin.IUserService;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.ExportDataDTO;
import com.gs.lshly.common.struct.platadmin.user.dto.UserDTO;
import com.gs.lshly.common.struct.platadmin.user.qto.UserQTO;
import com.gs.lshly.common.struct.platadmin.user.vo.UserVO;
import com.gs.lshly.common.utils.ExcelUtil;
import com.gs.lshly.rpc.api.platadmin.user.IUserRpc;

/**
 * @author xxfc
 * @since 2020-10-05
 */
@DubboService
public class UserRpc implements IUserRpc {

    @Autowired
    private IUserService userService;

    @Override
    public PageData<UserVO.ListVO> fullSearchList(UserQTO.FullSearchQTO qto) {
        return userService.fullSearchList(qto);
    }

    @Override
    public PageData<UserVO.ListVO> pageData(UserQTO.QTO qto) {
        return userService.pageData(qto);
    }

    @Override
    public UserVO.DetailVO details(UserDTO.IdDTO dto) {
        return userService.details(dto);
    }

    @Override
    public void setLabels(List<UserDTO.SetLabelDTO> dto) {
        userService.setLabels(dto);
    }

    @Override
    public void addUser(UserDTO.AddETO eto) {
        userService.addUser(eto);
    }

    @Override
    public void deleteUser(UserDTO.IdDTO dto) {
        userService.deleteUser(dto);
    }

    @Override
    public void deleteBatchUser(UserDTO.IdListDTO dto) {
        userService.deleteBatchUser(dto);
    }

    @Override
    public void editorUserInfo(UserDTO.ETO eto) {
        userService.editorUserInfo(eto);
    }

    @Override
    public void editorPassworld(UserDTO.PassworldETO eto) {
        userService.editorPassworld(eto);
    }

    @Override
    public void editorIntegral(UserDTO.IntegralETO eto) {
        userService.editorIntegral(eto);
    }

    @Override
    public void editorLeve(UserDTO.LeveETO eto) {
        userService.editorLeve(eto);
    }

    @Override
    public UserVO.DetailVO detailUser(UserDTO.IdDTO dto) {
        return userService.detailUser(dto);
    }

    @Override
    public void enableUser(UserDTO.IdListDTO dto) {
        userService.enableUser(dto);
    }

    @Override
    public void disableUser(UserDTO.IdListDTO dto) {
        userService.disableUser(dto);
    }


    @Override
    public void setUserLeve(UserDTO.SetUserLeveDTO dto) {
        userService.setUserLeve(dto);
    }

    @Override
    public ExportDataDTO export(UserDTO.ExportDTO dto) throws Exception {
        return ExcelUtil.treatmentBean(userService.exportData(dto), UserVO.ListVO.class);
    }

    @Override
    public UserVO.MiniVO mini(UserDTO.IdDTO dto) {
        return userService.mini(dto);
    }

    @Override
    public UserVO.ListVO innerByPhone(String phone) {
        return userService.innerByPhone(phone);
    }

    @Override
    public UserVO.ListVO innerSave2BUser(UserDTO.InnerETO eto) {
        return userService.innerSave2BUser(eto);
    }

}