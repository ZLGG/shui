package com.gs.lshly.biz.support.user.rpc.bbb.h5;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.common.struct.BaseQTO;
import com.gs.lshly.common.struct.bbb.h5.user.dto.BbbH5UserCardDTO;
import com.gs.lshly.common.struct.bbb.h5.user.qto.BbbH5UserCardQTO;
import com.gs.lshly.common.struct.bbb.h5.user.vo.BbbH5UserCardVO;
import com.gs.lshly.common.struct.bbb.h5.user.vo.BbbH5UserVO;
import com.gs.lshly.rpc.api.bbb.h5.user.IBbbH5UserCardRpc;
import com.gs.lshly.biz.support.user.service.bbb.h5.IBbbH5UserCardService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
*
* @author xxfc
* @since 2021-01-05
*/
@DubboService
public class BbbH5UserCardRpc implements IBbbH5UserCardRpc {

    @Autowired
    private IBbbH5UserCardService h5BbbUserCardService;

    @Override
    public PageData<BbbH5UserCardVO.ListVO> pageData(BbbH5UserCardQTO.QTO qto){
        return h5BbbUserCardService.pageData(qto);
    }

    @Override
    public void addUserCard(BbbH5UserCardDTO.ETO eto){
        h5BbbUserCardService.addUserCard(eto);
    }

    @Override
    public void deleteUserCard(BbbH5UserCardDTO.IdDTO dto){
        h5BbbUserCardService.deleteUserCard(dto);
    }

    @Override
    public BbbH5UserCardVO.DetailVO detailUserCard(BbbH5UserCardDTO.IdDTO dto){
        return  h5BbbUserCardService.detailUserCard(dto);
    }

    @Override
    public BbbH5UserVO.UserIntegralVO integral(BaseDTO dto) {
        return h5BbbUserCardService.integral(dto);
    }

    @Override
    public PageData<BbbH5UserVO.UserIntegralRecordVO> integralLog(BaseQTO qto) {
        return h5BbbUserCardService.integralLog(qto);
    }

    @Override
    public void signInIntegralLog(BaseDTO dto) {
        h5BbbUserCardService.signInIntegralLog(dto);
    }

    @Override
    public BbbH5UserVO.UserIntegralStatusVO signInIntegralLogState(BaseDTO dto) {
        return h5BbbUserCardService.signInIntegralLogState(dto);
    }

}