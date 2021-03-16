package com.gs.lshly.rpc.api.merchadmin.pc.user;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.ExportDataDTO;
import com.gs.lshly.common.struct.merchadmin.pc.user.dto.PCMerchUserDTO;
import com.gs.lshly.common.struct.merchadmin.pc.user.qto.PCMerchUserQTO;
import com.gs.lshly.common.struct.merchadmin.pc.user.vo.PCMerchUserVO;

import java.util.List;

/**
*
* @author xxfc
* @since 2020-10-20
*/
public interface IPCMerchUserRpc {

    /**
     * 私域会员分页列表
     * @param qto
     * @return
     */
    PageData<PCMerchUserVO.ListVO> pageData(PCMerchUserQTO.QTO qto);

    PCMerchUserVO.PrivateUserDetailVO detailUser(PCMerchUserDTO.IdDTO dto);

    void stopBatchUser(PCMerchUserDTO.IdListDTO dto);

    void beginBatchUser(PCMerchUserDTO.IdListDTO dto);

    void setUserType(PCMerchUserDTO.UserTypeDTO dto);

    /**
     * 导出
     * @param dto
     * @return
     * @throws Exception
     */
    ExportDataDTO exportUser(PCMerchUserDTO.IdListDTO dto) throws Exception;

    /**
     * 会员审核分页列表
     * @param qto
     * @return
     */
    PageData<PCMerchUserVO.ApplyListVO> applyPageList(PCMerchUserQTO.ApplyListQTO qto);

    /**
     * 详情
     * @param dto
     * @return
     */
    PCMerchUserVO.ApplyPrivateUserDetailVO applyDetailUser(PCMerchUserDTO.IdDTO dto);

    /**
     * 审核
     * @param dto
     */
    void apply(PCMerchUserDTO.ApplyDTO dto);

    PCMerchUserVO.UserSimpleVO innerUserSimple(String userId);
}