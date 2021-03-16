package com.gs.lshly.biz.support.user.service.bbb.h5;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.bbb.h5.user.qto.BbbH5UserPrivateUserQTO;
import com.gs.lshly.common.struct.bbb.h5.user.vo.BbbH5UserPrivateUserVO;
import com.gs.lshly.common.struct.bbb.h5.user.vo.BbbH5UserUser2bApplyLogVO;
import com.gs.lshly.common.struct.bbb.pc.user.qto.PCBbbUserPrivateUserQTO;

import java.util.List;

public interface IBbbH5UserPrivateUserService {

    PageData<BbbH5UserPrivateUserVO.ListVO> pageData(BbbH5UserPrivateUserQTO.QTO qto);

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