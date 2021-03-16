package com.gs.lshly.rpc.api.bbb.h5.user;

import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.bbb.h5.user.qto.BbbH5UserPrivateUserQTO;
import com.gs.lshly.common.struct.bbb.h5.user.vo.BbbH5UserPrivateUserVO;
import com.gs.lshly.common.struct.bbb.h5.user.vo.BbbH5UserUser2bApplyLogVO;
import com.gs.lshly.common.struct.bbb.pc.user.qto.PCBbbUserPrivateUserQTO;

import java.util.List;

/**
*
* @author xxfc
* @since 2020-12-24
*/
public interface IBbbH5UserPrivateUserRpc {

    PageData<BbbH5UserPrivateUserVO.ListVO> pageData(BbbH5UserPrivateUserQTO.QTO qto);

    /**
     * 获取2b会员所有的私域店铺ID
     * @param userId
     * @return
     */
    List<String> list(String userId);

    /**
     * 批量删除
     * @param dto
     */
    void deleteBatch (BbbH5UserPrivateUserVO.IdListDTO dto);

    /**
     * 加入私域店铺记录
     * @param qto
     * @return
     */
    PageData<BbbH5UserUser2bApplyLogVO.ListVO> pageLogData(PCBbbUserPrivateUserQTO.QTO qto);


}