package com.gs.lshly.biz.support.user.service.merchadmin.pc;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.merchadmin.pc.user.dto.PCMerchUserDTO;
import com.gs.lshly.common.struct.merchadmin.pc.user.qto.PCMerchUserQTO;
import com.gs.lshly.common.struct.merchadmin.pc.user.vo.PCMerchUserVO;
import com.gs.lshly.common.struct.platadmin.user.dto.UserDTO;
import com.gs.lshly.common.struct.platadmin.user.qto.UserQTO;
import com.gs.lshly.common.struct.platadmin.user.vo.UserVO;

import java.util.List;

public interface IPCMerchUserService {
    /**
     * 【分页列表-高级搜索 , 批量停用，批量启用  导出 】
     */

    PageData<PCMerchUserVO.ListVO> pageData(PCMerchUserQTO.QTO qto);

    PCMerchUserVO.PrivateUserDetailVO detailUser(PCMerchUserDTO.IdDTO dto);

    void stopBatchUser(PCMerchUserDTO.IdListDTO dto);

    void beginBatchUser(PCMerchUserDTO.IdListDTO dto);

    void setUserType(PCMerchUserDTO.UserTypeDTO dto);

    /**
     * 数据导出列表
     * @param dto
     * @return
     */
    List<PCMerchUserVO.ExportVO>  exportUser(PCMerchUserDTO.IdListDTO dto);


    /**
     * 会员审核列表
     * @param qto
     * @return
     */
    PageData<PCMerchUserVO.ApplyListVO> applyPageList(PCMerchUserQTO.ApplyListQTO qto);

    PCMerchUserVO.ApplyPrivateUserDetailVO applyDetailUser(PCMerchUserDTO.IdDTO dto);

    /**
     * 审核
     * @param dto
     */
    void apply(PCMerchUserDTO.ApplyDTO dto);

    PCMerchUserVO.UserSimpleVO innerUserSimple(String userId);

}