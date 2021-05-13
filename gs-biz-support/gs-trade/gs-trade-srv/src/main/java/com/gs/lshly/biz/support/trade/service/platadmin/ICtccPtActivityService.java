package com.gs.lshly.biz.support.trade.service.platadmin;

import com.gs.lshly.common.struct.platadmin.trade.dto.CtccPtActivityDTO;
import com.gs.lshly.common.struct.platadmin.trade.vo.CtccPtActivityVO;

/**
 * @Author yangxi
 * @create 2021/5/8 9:54
 */
public interface ICtccPtActivityService {
    /**
     * 添加活动
     * @param addDTO
     */
    void addActivity(CtccPtActivityDTO.AddDTO addDTO);

    /**
     * 编辑活动
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
