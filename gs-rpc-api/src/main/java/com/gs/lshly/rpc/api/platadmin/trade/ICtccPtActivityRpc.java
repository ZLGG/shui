package com.gs.lshly.rpc.api.platadmin.trade;

import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.bbc.commodity.vo.BbcGoodsInfoVO;
import com.gs.lshly.common.struct.platadmin.trade.dto.CtccPtActivityDTO;
import com.gs.lshly.common.struct.platadmin.trade.vo.CtccPtActivityVO;

import java.util.List;

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

    /**
     * 添加活动商品
     * @param activityGoodsDTOList
     */
    void addActivityGoods(List<CtccPtActivityDTO.AddGoodsDTO> activityGoodsDTOList);

    /**
     * 添加电信国际类目
     * @param dto
     */
    void addCategory(CtccPtActivityDTO.AddCategoryDTO dto);

    /**
     * 添加电信国际类目下商品
     * @param list
     */
    void addCategoryGoods(List<CtccPtActivityDTO.AddCategoryGoodsDTO> list);

    /**
     * 查看商品详情
     * @param id
     * @return
     */
    BbcGoodsInfoVO.CtccGoodsDetailVO getActivityDetail(String id);

    /**
     * 活动列表展示
     * @param dto
     * @return
     */
    PageData<BbcGoodsInfoVO.CtccGoodsDetailVO> queryActivityList(CtccPtActivityDTO.ActivityListDTO dto);

    /**
     * 更新商品上下架状态
     * @param list
     */
    void updateGoodsState(List<CtccPtActivityDTO.RemoveGoodsDTO> list);

    /**
     * 批量删除商品
     * @param list
     */
    void deleteGoods(CtccPtActivityDTO.DeleteGoodsDTO list);

    /**
     * 商品排序
     * @param dto
     */
    void sortedGoods(CtccPtActivityDTO.SortedGoodsDTO dto);
}
