package com.gs.lshly.biz.support.trade.service.platadmin.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.gs.lshly.biz.support.trade.entity.MarketPtActivity;
import com.gs.lshly.biz.support.trade.entity.MarketPtActivityGoodsCategory;
import com.gs.lshly.biz.support.trade.entity.MarketPtActivityJurisdiction;
import com.gs.lshly.biz.support.trade.repository.IMarketPtActivityGoodsCategoryRepository;
import com.gs.lshly.biz.support.trade.repository.IMarketPtActivityJurisdictionRepository;
import com.gs.lshly.biz.support.trade.repository.IMarketPtActivityRepository;
import com.gs.lshly.biz.support.trade.service.platadmin.IMarketPtActivityService;
import com.gs.lshly.common.exception.BusinessException;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.platadmin.commodity.vo.GoodsCategoryVO;
import com.gs.lshly.common.struct.platadmin.trade.dto.MarketPtActivityDTO;
import com.gs.lshly.common.struct.platadmin.trade.qto.MarketPtActivityQTO;
import com.gs.lshly.common.struct.platadmin.trade.vo.MarketPtActivityVO;
import com.gs.lshly.middleware.mybatisplus.MybatisPlusUtil;
import com.gs.lshly.rpc.api.platadmin.commodity.IGoodsCategoryRpc;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
* <p>
*  服务实现类
* </p>
* @author zdf
* @since 2020-11-28
*/
@Component
public class MarketPtActivityServiceImpl implements IMarketPtActivityService {

    @Autowired
    private IMarketPtActivityRepository repository;
    @Autowired
    private IMarketPtActivityGoodsCategoryRepository categoryRepository;
    @Autowired
    private IMarketPtActivityJurisdictionRepository iMarketPtActivityJurisdictionRepository;
    @DubboReference
    private IGoodsCategoryRpc iGoodsCategoryRpc;

    @Override
    public PageData<MarketPtActivityVO.ListVO> pageData(MarketPtActivityQTO.QTO qto) {
        QueryWrapper<MarketPtActivity> wrapper = MybatisPlusUtil.query();
        wrapper.orderByDesc("cdate");
        IPage<MarketPtActivity> page = MybatisPlusUtil.pager(qto);
        repository.page(page, wrapper);
        return MybatisPlusUtil.toPageData(qto,MarketPtActivityVO.ListVO.class, page);
    }

    @Override
    @Transactional
    public void addMarketPtActivity(MarketPtActivityDTO.ETO eto) {
        if (null == eto) {
            throw new BusinessException("参数不能微空");
        }
        checkTime(eto);
        MarketPtActivity marketPtActivity = new MarketPtActivity();
        BeanUtils.copyProperties(eto, marketPtActivity);
        if (ObjectUtils.isNotEmpty(eto.getBuyMax())){
            marketPtActivity.setBuyMax(999);
        }
        if (ObjectUtils.isNotEmpty(eto.getGoodsMax())){
            marketPtActivity.setGoodsMax(999);
        }
        repository.save(marketPtActivity);
        //获取活动id
        String activityId = marketPtActivity.getId();
        //遍历类目id
        if (eto.getCategoryIds()==null||eto.getTypeCode()==null) {
            throw new BusinessException("请选择商品类目或选择店铺类型");
        }
        for (String categoryId:eto.getCategoryIds()){
            MarketPtActivityGoodsCategory marketPtActivityGoodsCategory = new MarketPtActivityGoodsCategory();
            marketPtActivityGoodsCategory.setActivityId(activityId).setCategoryId(categoryId);
            System.out.println(marketPtActivityGoodsCategory);
            categoryRepository.save(marketPtActivityGoodsCategory);
        }

    }

    private void checkTime(MarketPtActivityDTO.ETO eto) {
        if (ObjectUtils.isEmpty(eto.getActivityEndTime())){
            throw new BusinessException("时间为空");
        }

        if (ObjectUtils.isEmpty(eto.getActivityStartTime())){
            throw new BusinessException("时间为空");
        }
        if (ObjectUtils.isEmpty(eto.getOnlineStartTime())){
            throw new BusinessException("时间为空");
        }
        if (ObjectUtils.isEmpty(eto.getSignEndTime())){
            throw new BusinessException("时间为空");
        }
        if (ObjectUtils.isEmpty(eto.getSignStartTime())){
            throw new BusinessException("时间为空");
        }
        if (LocalDateTime.now().isAfter(eto.getActivityEndTime())){
            throw new BusinessException("请检查时间");
        }
        if (LocalDateTime.now().isAfter(eto.getActivityStartTime())){
            throw new BusinessException("请检查时间");
        }
        if (LocalDateTime.now().isAfter(eto.getOnlineStartTime())){
            throw new BusinessException("请检查时间");
        }
        if (LocalDateTime.now().isAfter(eto.getSignEndTime())){
            throw new BusinessException("请检查时间");
        }
        if (LocalDateTime.now().isAfter(eto.getSignStartTime())){
            throw new BusinessException("请检查时间");
        }
    }

    @Override
    @Transactional
    public void deleteMarketPtActivity(MarketPtActivityDTO.IdListDTO dto) {
        if (dto == null || dto.getIdList() == null || dto.getIdList().size() == 0){
            throw new BusinessException("参数不能为空！");
        }
        //删除活动类目表
        for (String activitId:dto.getIdList()){
            QueryWrapper<MarketPtActivityGoodsCategory> wrapper = new QueryWrapper<>();
            wrapper.eq("activity_id",activitId);
            List<MarketPtActivityGoodsCategory> list = categoryRepository.list(wrapper);
            if (list != null && list.size()>0){
                categoryRepository.remove(wrapper);
            }
        }
        repository.removeByIds(dto.getIdList());
    }
    @Override
    public void editMarketPtActivity(MarketPtActivityDTO.ETO eto) {
        MarketPtActivity marketPtActivity = new MarketPtActivity();
        BeanUtils.copyProperties(eto, marketPtActivity);
        repository.updateById(marketPtActivity);
    }

    @Override
    public MarketPtActivityVO.DetailVO detailMarketPtActivity(MarketPtActivityDTO.IdDTO dto) {
        MarketPtActivity marketPtActivity = repository.getById(dto.getId());
        MarketPtActivityVO.DetailVO detailVo = new MarketPtActivityVO.DetailVO();
        if(ObjectUtils.isEmpty(marketPtActivity)){
            throw new BusinessException("没有数据");
        }
        BeanUtils.copyProperties(marketPtActivity, detailVo);
        QueryWrapper<MarketPtActivityGoodsCategory> query = MybatisPlusUtil.query();
        query.and(i->i.eq("activity_id",dto.getId()));
        List<MarketPtActivityGoodsCategory> list = categoryRepository.list(query);
        if (ObjectUtils.isNotEmpty(list)){
            List<String> categoryIds = new ArrayList<>();
            for (MarketPtActivityGoodsCategory marketPtActivityGoodsCategory : list) {
                categoryIds.add(marketPtActivityGoodsCategory.getCategoryId());
            }
            if (ObjectUtils.isNotEmpty(categoryIds)) {
                List<GoodsCategoryVO.CategoryJoinSearchVO> categoryJoinSearchVOS = iGoodsCategoryRpc.innerGetIdAndName(categoryIds);
                detailVo.setCategory(categoryJoinSearchVOS);
            }
        }
        return detailVo;
    }

    @Override
    public List<MarketPtActivityVO.ListVO> list() {
        List<MarketPtActivity> list = repository.list();
        List<MarketPtActivityVO.ListVO> ListVO=new ArrayList<MarketPtActivityVO.ListVO>();
        for (MarketPtActivity marketPtActivity:list){
            MarketPtActivityVO.ListVO VO = new MarketPtActivityVO.ListVO();
            BeanUtils.copyProperties(marketPtActivity,VO);
            ListVO.add(VO);
        }
        return ListVO;
    }

    @Override
    public void updateActivity(MarketPtActivityDTO.updateDTO eto) {
        MarketPtActivityJurisdiction one = iMarketPtActivityJurisdictionRepository.getOne(null);
        if (ObjectUtils.isNotEmpty(one)){
            eto.setId(one.getId());
            MarketPtActivityJurisdiction marketPtActivityJurisdiction = new MarketPtActivityJurisdiction();
            BeanUtils.copyProperties(eto,marketPtActivityJurisdiction);
            iMarketPtActivityJurisdictionRepository.updateById(marketPtActivityJurisdiction);
        }
    }

    @Override
    public MarketPtActivityVO.updateDTO getActivity() {
        MarketPtActivityJurisdiction one = iMarketPtActivityJurisdictionRepository.getOne(null);
        MarketPtActivityVO.updateDTO updateDTO = new MarketPtActivityVO.updateDTO();
        BeanUtils.copyProperties(one,updateDTO);
        return updateDTO;
    }
}
