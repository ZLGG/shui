package com.gs.lshly.rpc.api.bbb.pc.user;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.bbb.pc.user.dto.PCBbbUserPrivateUserDTO;
import com.gs.lshly.common.struct.bbb.pc.user.dto.PCBbbUserPrivateUserLogDTO;
import com.gs.lshly.common.struct.bbb.pc.user.qto.PCBbbUserPrivateUserQTO;
import com.gs.lshly.common.struct.bbb.pc.user.vo.PCBbbUserPrivateUserLogVO;
import com.gs.lshly.common.struct.bbb.pc.user.vo.PCBbbUserPrivateUserVO;
import java.util.List;

/**
*
* @author xxfc
* @since 2020-12-24
*/
public interface IPCBbbUserPrivateUserRpc {

    PageData<PCBbbUserPrivateUserVO.ListVO> pageData(PCBbbUserPrivateUserQTO.QTO qto);


    /**
     * 批量删除
     * @param dto
     */
    void deleteBatch (PCBbbUserPrivateUserDTO.IdListDTO dto);

    /**
     * 加入私域店铺记录
     * @param qto
     * @return
     */
    PageData<PCBbbUserPrivateUserLogVO.ListVO> pageLogData(PCBbbUserPrivateUserQTO.QTO qto);

    /**
     * 添加加入私域店铺记录
     * @param eto
     */
    void addPrivateUserLog(PCBbbUserPrivateUserLogDTO.ETO eto);

    /**
     * 获取2b会员所有的私域店铺ID
     * @param userId
     * @return
     */
    List<String> list(String userId);

}