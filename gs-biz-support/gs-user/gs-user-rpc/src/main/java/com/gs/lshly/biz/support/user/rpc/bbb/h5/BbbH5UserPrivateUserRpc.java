package com.gs.lshly.biz.support.user.rpc.bbb.h5;

import com.gs.lshly.biz.support.user.service.bbb.h5.IBbbH5UserPrivateUserService;
import com.gs.lshly.biz.support.user.service.bbb.pc.IPCBbbUserPrivateUserService;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.bbb.h5.user.qto.BbbH5UserPrivateUserQTO;
import com.gs.lshly.common.struct.bbb.h5.user.vo.BbbH5UserPrivateUserVO;
import com.gs.lshly.common.struct.bbb.h5.user.vo.BbbH5UserUser2bApplyLogVO;
import com.gs.lshly.common.struct.bbb.pc.user.qto.PCBbbUserPrivateUserQTO;
import com.gs.lshly.common.struct.bbb.pc.user.vo.PCBbbUserPrivateUserVO;
import com.gs.lshly.rpc.api.bbb.h5.user.IBbbH5UserPrivateUserRpc;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
*
* @author xxfc
* @since 2020-12-24
*/
@DubboService
public class BbbH5UserPrivateUserRpc implements IBbbH5UserPrivateUserRpc {

    @Autowired
    private IBbbH5UserPrivateUserService bbbH5UserPrivateUserService;

    @Override
    public PageData<BbbH5UserPrivateUserVO.ListVO> pageData(BbbH5UserPrivateUserQTO.QTO qto){
        return bbbH5UserPrivateUserService.pageData(qto);
    }

    @Override
    public List<String> list(String userId) {
        return bbbH5UserPrivateUserService.list(userId);
    }

    @Override
    public void deleteBatch(BbbH5UserPrivateUserVO.IdListDTO dto) {
        bbbH5UserPrivateUserService.deleteBatch(dto);
    }

    @Override
    public PageData<BbbH5UserUser2bApplyLogVO.ListVO> pageLogData(PCBbbUserPrivateUserQTO.QTO qto) {
        return bbbH5UserPrivateUserService.pageLogData(qto);
    }


}