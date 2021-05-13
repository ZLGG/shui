package com.gs.lshly.biz.support.trade.service.platadmin.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gs.lshly.biz.support.trade.entity.CtccCategory;
import com.gs.lshly.biz.support.trade.entity.CtccCategoryGoods;
import com.gs.lshly.biz.support.trade.entity.CtccPtActivity;
import com.gs.lshly.biz.support.trade.repository.ICtccCategoryGoodsRepository;
import com.gs.lshly.biz.support.trade.repository.ICtccCategoryRepository;
import com.gs.lshly.biz.support.trade.repository.ICtccPtActivityRepository;
import com.gs.lshly.biz.support.trade.service.platadmin.ICtccPtActivityService;
import com.gs.lshly.common.enums.SubjectEnum;
import com.gs.lshly.common.struct.bbc.commodity.dto.BbcGoodsInfoDTO;
import com.gs.lshly.common.struct.bbc.commodity.vo.BbcCtccCategoryGoodsVO;
import com.gs.lshly.common.struct.bbc.commodity.vo.BbcGoodsInfoVO;
import com.gs.lshly.common.struct.platadmin.trade.dto.CtccPtActivityDTO;
import com.gs.lshly.common.struct.platadmin.trade.vo.CtccPtActivityVO;
import com.gs.lshly.common.utils.BeanCopyUtils;
import com.gs.lshly.middleware.mybatisplus.MybatisPlusUtil;
import com.gs.lshly.rpc.api.bbc.commodity.IBbcGoodsInfoRpc;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Author yangxi
 * @create 2021/5/8 9:55
 */
@Component
public class CtccPtActivityServiceImpl implements ICtccPtActivityService {
    @Autowired
    private ICtccPtActivityRepository ctccPtActivityRepository;
    @Autowired
    private ICtccCategoryGoodsRepository goodsRepository;
    @Autowired
    private IBbcGoodsInfoRpc bbcGoodsInfoRpc;
    @Autowired
    private ICtccCategoryRepository repository;

    @Override
    public void modifyActivity(CtccPtActivityDTO.ModifyDTO modifyDTO) {
        CtccPtActivity ctccPtActivity = new CtccPtActivity();
        BeanUtils.copyProperties(modifyDTO, ctccPtActivity);
        ctccPtActivity.setUserId(modifyDTO.getJwtUserId()).setGmtModify(new Date());
        ctccPtActivityRepository.updateById(ctccPtActivity);
    }

    @Override
    public CtccPtActivityVO.CategoryListVO getCategoryList(CtccPtActivityDTO.CateGoryListDTO listDTO) {

        QueryWrapper<CtccCategory> wrapper = MybatisPlusUtil.query();
        wrapper.eq("subject", SubjectEnum.电信国际.getCode());
        wrapper.orderByAsc("idx");

        CtccPtActivityVO.CategoryListVO categoryListVO = new CtccPtActivityVO.CategoryListVO();

        // 查询商品类别
        List<CtccCategory> ctccCategoryList = repository.list( wrapper);
        if(CollectionUtil.isNotEmpty(ctccCategoryList)){
            List<CtccPtActivityVO.CtccCategoryVO> categorys = new ArrayList<>();
            CtccPtActivityVO.CtccCategoryVO ctccCategoryVO = null;
            for(CtccCategory ctccCategory:ctccCategoryList){
                QueryWrapper<CtccCategoryGoods> wrapperGoods = MybatisPlusUtil.query();
                ctccCategoryVO = new CtccPtActivityVO.CtccCategoryVO();
                BeanCopyUtils.copyProperties(ctccCategory, ctccCategoryVO);
                // 查询类别对应商品
                wrapperGoods.eq("category_id", ctccCategory.getId());
                List<CtccCategoryGoods> ctccCategoryGoods = goodsRepository.list(wrapperGoods);
                // 查询商品详情
                List<BbcGoodsInfoVO.CtccGoodsDetailVO> ctccGoodsDetailVOList = new ArrayList<>();
                if(CollectionUtil.isNotEmpty(ctccCategoryGoods)){
                    for(CtccCategoryGoods ctccCategoryGood:ctccCategoryGoods){
                        BbcGoodsInfoVO.CtccGoodsDetailVO ctccGoodsDetailVO = new BbcGoodsInfoVO.CtccGoodsDetailVO();
                        String goodsId = ctccCategoryGood.getGoodsId();
                        BbcGoodsInfoVO.DetailVO detailVO= bbcGoodsInfoRpc.detailGoodsInfo(new BbcGoodsInfoDTO.IdDTO(goodsId));
                        BeanUtils.copyProperties(detailVO,ctccGoodsDetailVO);
                        ctccGoodsDetailVOList.add(ctccGoodsDetailVO);
                    }
                }

                ctccCategoryVO.setGoodsList(ctccGoodsDetailVOList);
                categorys.add(ctccCategoryVO);
            }
            categoryListVO.setCategorys(categorys);
        }
        return categoryListVO;
    }

    @Override
    public void addActivity(CtccPtActivityDTO.AddDTO addDTO) {
        CtccPtActivity ctccPtActivity = new CtccPtActivity();
        BeanUtils.copyProperties(addDTO, ctccPtActivity);
        ctccPtActivity.setGmtCreate(new Date()).setGmtModify(new Date()).setFlag(false).setUserId(addDTO.getJwtUserId());
        ctccPtActivityRepository.save(ctccPtActivity);
    }
}
