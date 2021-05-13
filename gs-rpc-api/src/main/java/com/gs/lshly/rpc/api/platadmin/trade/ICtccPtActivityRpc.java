package com.gs.lshly.rpc.api.platadmin.trade;

import com.gs.lshly.common.struct.platadmin.trade.dto.CtccPtActivityDTO;
import com.gs.lshly.common.struct.platadmin.trade.vo.CtccPtActivityVO;

/**
 * @Author yangxi
 * @create 2021/5/8 9:35
 */

public interface ICtccPtActivityRpc {
    /**
     * 新增活动
     * @param addDTO
     */
    void addActivity(CtccPtActivityDTO.AddDTO addDTO);

    /**
     * 修改活动
     * @param modifyDTO
     */
    void modifyActivity(CtccPtActivityDTO.ModifyDTO modifyDTO);

    /**
     * 商品类目列表展示
     * @param listDTO
     * @return
     */
    CtccPtActivityVO.CategoryListVO getCategoryList(CtccPtActivityDTO.CateGoryListDTO listDTO);
}
