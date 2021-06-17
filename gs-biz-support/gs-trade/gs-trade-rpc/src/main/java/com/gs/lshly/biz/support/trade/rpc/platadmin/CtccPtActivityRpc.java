package com.gs.lshly.biz.support.trade.rpc.platadmin;

import com.gs.lshly.biz.support.trade.service.platadmin.ICtccPtActivityService;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.bbc.commodity.vo.BbcGoodsInfoVO;
import com.gs.lshly.common.struct.platadmin.trade.dto.CtccPtActivityDTO;
import com.gs.lshly.common.struct.platadmin.trade.dto.CtccPtActivityDTO.IdDTO;
import com.gs.lshly.common.struct.platadmin.trade.dto.CtccPtActivityDTO.ModifyCategoryDTO;
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
    public void updateGoodsState(List<CtccPtActivityDTO.RemoveGoodsDTO> list) {
        ctccPtActivityService.updateGoodsState(list);
    }

    @Override
    public void deleteGoods(CtccPtActivityDTO.DeleteGoodsDTO list) {
        ctccPtActivityService.deleteGoods(list);
    }

    @Override
    public void sortedGoods(CtccPtActivityDTO.SortedGoodsDTO dto) {
        ctccPtActivityService.sortedGoods(dto);
    }

    @Override
    public PageData<BbcGoodsInfoVO.CtccGoodsDetailVO> queryActivityList(CtccPtActivityDTO.ActivityListDTO dto) {
        return ctccPtActivityService.queryActivityList(dto);
    }

    @Override
    public BbcGoodsInfoVO.CtccGoodsDetailVO getActivityDetail(String id) {
        return ctccPtActivityService.getActivityDetail(id);
    }

    @Override
    public void addCategoryGoods(List<CtccPtActivityDTO.AddCategoryGoodsDTO> list) {
        ctccPtActivityService.addCategoryGoods(list);
    }

    @Override
    public void addCategory(CtccPtActivityDTO.AddCategoryDTO dto) {
        ctccPtActivityService.addCategory(dto);
    }

    @Override
    public void addActivityGoods(List<CtccPtActivityDTO.AddGoodsDTO> activityGoodsDTOList) {
        ctccPtActivityService.addActivityGoods(activityGoodsDTOList);
    }

    @Override
    public void modifyActivity(CtccPtActivityDTO.ModifyDTO modifyDTO) {
        ctccPtActivityService.modifyActivity(modifyDTO);
    }

	@Override
	public void modifyCategory(ModifyCategoryDTO dto) {
		ctccPtActivityService.modifyCategory(dto);
		
	}

	@Override
	public void deleteCategory(IdDTO dto) {
		ctccPtActivityService.deleteCategory(dto);
		
	}
}
