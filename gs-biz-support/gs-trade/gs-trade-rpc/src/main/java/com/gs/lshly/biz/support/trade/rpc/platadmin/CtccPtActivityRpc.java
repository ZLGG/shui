package com.gs.lshly.biz.support.trade.rpc.platadmin;

import com.gs.lshly.biz.support.trade.service.platadmin.ICtccPtActivityService;
import com.gs.lshly.common.struct.platadmin.trade.dto.CtccPtActivityDTO;
import com.gs.lshly.common.struct.platadmin.trade.vo.CtccPtActivityVO;
import com.gs.lshly.rpc.api.platadmin.trade.ICtccPtActivityRpc;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @Author yangxi
 * @create 2021/5/8 9:53
 */
@DubboService
public class CtccPtActivityRpc implements ICtccPtActivityRpc {
    @Autowired
    private ICtccPtActivityService ctccPtActivityService;

    @Override
    public void addActivity(CtccPtActivityDTO.AddDTO addDTO) {
        ctccPtActivityService.addActivity(addDTO);
    }

    @Override
    public CtccPtActivityVO.CategoryListVO getCategoryList(CtccPtActivityDTO.CateGoryListDTO listDTO) {
        return ctccPtActivityService.getCategoryList(listDTO);
    }

    @Override
    public void addActivityGoods(List<CtccPtActivityDTO.AddActivityGoodsDTO> activityGoodsDTOList) {
        ctccPtActivityService.addActivityGoods(activityGoodsDTOList);
    }

    @Override
    public void modifyActivity(CtccPtActivityDTO.ModifyDTO modifyDTO) {
        ctccPtActivityService.modifyActivity(modifyDTO);
    }
}
