package com.gs.lshly.biz.support.user.service.platadmin;
import java.util.List;

import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.platadmin.user.dto.UserDTO;
import com.gs.lshly.common.struct.platadmin.user.qto.UserQTO;
import com.gs.lshly.common.struct.platadmin.user.vo.UserVO;

/**
 * @author xxfc
 */
public interface IUserService {

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

    /**
     * 会员详情
     * @param dto
     * @return
     */
    UserVO.DetailVO details(UserDTO.IdDTO dto);

    void setLabels(List<UserDTO.SetLabelDTO> dto);

    /**
     * 添加企业会员
     * @param eto
     */
    void addUser(UserDTO.AddETO eto);

    void deleteUser(UserDTO.IdDTO dto);

    /**
     * 批量删除
     * @param dto
     */
    void deleteBatchUser(UserDTO.IdListDTO dto);

    /**
     * 编辑会员信息
     * @param eto
     */
    void editorUserInfo(UserDTO.ETO eto);

    /**
     * 修改密码
     * @param eto
     */
    void editorPassworld(UserDTO.PassworldETO eto);

    /**
     * 修改会员积分
     * @param eto
     */
    void editorIntegral(UserDTO.IntegralETO eto);

    /**
     * 修改会员等级
     * @param eto
     */
    void editorLeve(UserDTO.LeveETO eto);

    /**
     * 会员详情
     * @param dto
     * @return
     */
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

    /**
     * 导出数据
     * @param dto
     * @return
     * @throws Exception
     */
    List<UserVO.ListVO> exportData(UserDTO.ExportDTO dto);

    /**
     * 设置会员等级
     * @param dto
     */
    void setUserLeve(UserDTO.SetUserLeveDTO dto);

    UserVO.MiniVO mini(UserDTO.IdDTO dto);

    void addGongHuiUser(UserDTO.AddETO eto);

    //---------------内部服务-------------


    UserVO.ListVO innerByPhone(String phone);

    UserVO.ListVO innerSave2BUser(UserDTO.InnerETO eto);
    
}