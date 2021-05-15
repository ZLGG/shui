package com.gs.lshly.rpc.api.platadmin.user;
import java.util.List;

import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.ExportDataDTO;
import com.gs.lshly.common.struct.platadmin.user.dto.UserDTO;
import com.gs.lshly.common.struct.platadmin.user.qto.UserQTO;
import com.gs.lshly.common.struct.platadmin.user.vo.UserVO;

/**
*
* @author xxfc
* @since 2020-10-05
*/
public interface IUserRpc {

    /**
     * 高级搜索
     * @param qto
     * @return
     */
    PageData<UserVO.ListVO> fullSearchList(UserQTO.FullSearchQTO qto);


    /**
     * 普通搜索
     * @param qto
     * @return
     */
    PageData<UserVO.ListVO> pageData(UserQTO.QTO qto);

    UserVO.DetailVO details(UserDTO.IdDTO dto);

    void setLabels(List<UserDTO.SetLabelDTO> dto);


    /**
     * 添加企业会员
     * @param eto
     */
    void addUser(UserDTO.AddETO eto);

    void deleteUser(UserDTO.IdDTO dto);

    void deleteBatchUser(UserDTO.IdListDTO dto);


    void editorUserInfo(UserDTO.ETO eto);

    void editorPassworld(UserDTO.PassworldETO eto);

    void editorIntegral(UserDTO.IntegralETO eto);

    void editorLeve(UserDTO.LeveETO eto);


    UserVO.DetailVO detailUser(UserDTO.IdDTO dto);

    /**
     * 启用
     * @param dto
     */
    void enableUser(UserDTO.IdListDTO dto);

    /**
     * 停用
     * @param dto
     */
    void disableUser(UserDTO.IdListDTO dto);

    void setUserLeve(UserDTO.SetUserLeveDTO dto);

    /**
     * 数据导出
     * @param dto
     * @return
     * @throws Exception
     */
    ExportDataDTO export(UserDTO.ExportDTO dto) throws Exception;

    UserVO.MiniVO mini(UserDTO.IdDTO dto);

    UserVO.ListVO innerByPhone(String phone);

    UserVO.ListVO innerSave2BUser(UserDTO.InnerETO eto);

}