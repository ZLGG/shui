package com.gs.lshly.biz.support.stock.service.common.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.gs.lshly.biz.support.stock.entity.StockLogisticsCorp;
import com.gs.lshly.biz.support.stock.mapper.StockShopLogisticsCorpMapper;
import com.gs.lshly.biz.support.stock.mapper.view.StockShopLogisticsCorpView;
import com.gs.lshly.biz.support.stock.repository.IStockLogisticsCorpRepository;
import com.gs.lshly.biz.support.stock.service.common.ICommonLogisticsCompanyService;
import com.gs.lshly.common.enums.TrueFalseEnum;
import com.gs.lshly.common.struct.common.CommonLogisticsCompanyVO;
import com.gs.lshly.common.utils.ListUtil;
import com.gs.lshly.middleware.mybatisplus.MybatisPlusUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
* <p>
*  服务实现类
* </p>
* @author xxfc
* @since 2020-10-30
*/
@Component
public class CommonLogisticsCompanyServiceImpl implements ICommonLogisticsCompanyService {

    @Autowired
    private StockShopLogisticsCorpMapper stockShopLogisticsCorpMapper;

    @Autowired
    private IStockLogisticsCorpRepository stockLogisticsCorpRepository;

    @Override
    public List<CommonLogisticsCompanyVO.DetailVO> listShopLogisticsCompany(String shopId) {
        if(StringUtils.isBlank(shopId)){
            return new ArrayList<>();
        }
        QueryWrapper<StockShopLogisticsCorpView> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("sc.shop_id",shopId);
        queryWrapper.eq("sc.state", TrueFalseEnum.是.getCode());
        queryWrapper.orderByDesc("sc.cdate");
        List<StockShopLogisticsCorpView> viewList = stockShopLogisticsCorpMapper.mapperListShopLogisticsCorp(queryWrapper);
        return ListUtil.listCover(CommonLogisticsCompanyVO.DetailVO.class,viewList);
    }

    @Override
    public CommonLogisticsCompanyVO.DetailVO getLogisticsCompany(String id) {
        if(StringUtils.isBlank(id)){
           return null;
        }
        StockLogisticsCorp stockLogisticsCorp =  stockLogisticsCorpRepository.getById(id);
        if(null == stockLogisticsCorp){
            return null;
        }
        CommonLogisticsCompanyVO.DetailVO detailVO = new CommonLogisticsCompanyVO.DetailVO();
        BeanUtils.copyProperties(stockLogisticsCorp,detailVO);
        return detailVO;
    }

    @Override
    public CommonLogisticsCompanyVO.DetailVO getLogisticsName(String logisticsName) {
        if(StringUtils.isBlank(logisticsName)){
            return null;
        }
        QueryWrapper<StockLogisticsCorp> query = MybatisPlusUtil.query();
        query.like("name",logisticsName);
        StockLogisticsCorp one = stockLogisticsCorpRepository.getOne(query);
        CommonLogisticsCompanyVO.DetailVO detailVO = new CommonLogisticsCompanyVO.DetailVO();
        if (ObjectUtils.isNotEmpty(one)) {
            BeanUtils.copyProperties(one, detailVO);
        }
        return detailVO;
    }
}
