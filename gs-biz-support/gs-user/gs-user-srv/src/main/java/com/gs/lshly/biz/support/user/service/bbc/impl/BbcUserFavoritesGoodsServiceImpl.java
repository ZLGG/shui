package com.gs.lshly.biz.support.user.service.bbc.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.gs.lshly.biz.support.user.entity.UserFavoritesGoods;
import com.gs.lshly.biz.support.user.entity.UserFavoritesShop;
import com.gs.lshly.biz.support.user.mapper.UserFavoritesGoodsMapper;
import com.gs.lshly.biz.support.user.repository.IUserFavoritesGoodsRepository;
import com.gs.lshly.biz.support.user.service.bbc.IBbcUserFavoritesGoodsService;
import com.gs.lshly.common.enums.TrueFalseEnum;
import com.gs.lshly.common.exception.BusinessException;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.common.struct.bbc.commodity.qto.BbcGoodsInfoQTO;
import com.gs.lshly.common.struct.bbc.commodity.vo.BbcGoodsInfoVO;
import com.gs.lshly.common.struct.bbc.merchant.qto.BbcShopQTO;
import com.gs.lshly.common.struct.bbc.merchant.vo.BbcShopVO;
import com.gs.lshly.common.struct.bbc.user.dto.BbcUserFavoritesGoodsDTO;
import com.gs.lshly.common.struct.bbc.user.qto.BbcUserFavoritesGoodsQTO;
import com.gs.lshly.common.struct.bbc.user.vo.BbcUserFavoritesGoodsVO;
import com.gs.lshly.common.struct.bbc.user.vo.BbcUserFavoritesShopVO;
import com.gs.lshly.common.utils.BeanCopyUtils;
import com.gs.lshly.middleware.mybatisplus.MybatisPlusUtil;
import com.gs.lshly.middleware.mybatisplus.MybatisPlusUtil;
import com.gs.lshly.rpc.api.bbc.commodity.IBbcGoodsInfoRpc;
import com.gs.lshly.rpc.api.bbc.merchant.IBbcShopRpc;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
* <p>
*  服务实现类
* </p>
* @author xxfc
* @since 2020-10-28
*/
@Component
public class BbcUserFavoritesGoodsServiceImpl implements IBbcUserFavoritesGoodsService {

    @Autowired
    private IUserFavoritesGoodsRepository repository;

    @Autowired
    private UserFavoritesGoodsMapper userFavoritesGoodsMapper;

    @DubboReference
    private IBbcGoodsInfoRpc bbcGoodsInfoRpc;



    @Override
    public PageData<BbcUserFavoritesGoodsVO.ListVO> pageData(BbcUserFavoritesGoodsQTO.QTO qto) {
        if(null == qto.getJwtUserId()){
           throw new BusinessException("没有登录");
        }
        QueryWrapper queryWrapper = MybatisPlusUtil.query();
        queryWrapper.eq("user_id",qto.getJwtUserId());
        queryWrapper.orderByDesc("cdate");
        IPage<UserFavoritesGoods> pager = MybatisPlusUtil.pager(qto);
        repository.page(pager,queryWrapper);
        if(ObjectUtils.isEmpty(pager.getRecords())){
            return new PageData<>(new ArrayList<>(),qto.getPageNum(),qto.getPageSize(),pager.getTotal());
        }
        List<String> goodsIdList = new ArrayList<>();

        Map<String,BbcUserFavoritesGoodsVO.ListVO> voMap = new HashMap<>();
        for(UserFavoritesGoods item:pager.getRecords()){
            if(!goodsIdList.contains(item.getGoodsId())){
                goodsIdList.add(item.getGoodsId());
                BbcUserFavoritesGoodsVO.ListVO listVO = new BbcUserFavoritesGoodsVO.ListVO();
                voMap.put(item.getGoodsId(),listVO);
            }
        }
        BbcGoodsInfoQTO.GoodsIdListQTO goodsIdListQTO = new BbcGoodsInfoQTO.GoodsIdListQTO();
        goodsIdListQTO.setIdList(goodsIdList);
        List<BbcGoodsInfoVO.HomeAndShopInnerServiceVO> innerGoodsList = bbcGoodsInfoRpc.getInnerServiceVO(goodsIdListQTO);
        if(ObjectUtils.isEmpty(innerGoodsList)){
            return new PageData<>(new ArrayList<>(),qto.getPageNum(),qto.getPageSize(),pager.getTotal());
        }
        List<BbcUserFavoritesGoodsVO.ListVO> voList = new ArrayList<>();
        for(BbcGoodsInfoVO.HomeAndShopInnerServiceVO innerGoodsVo:innerGoodsList){
            BbcUserFavoritesGoodsVO.ListVO listVoItem =   voMap.get(innerGoodsVo.getId());
            if(null != listVoItem){
                BeanCopyUtils.copyProperties(innerGoodsVo,listVoItem);
                listVoItem.setCollectionCounts(userFavoritesGoodsMapper.selectCount(
                        new QueryWrapper<UserFavoritesGoods>().eq("goods_id",listVoItem.getId())));
                voList.add(listVoItem);
            }
        }
        return new PageData<>(voList,qto.getPageNum(),qto.getPageSize(),pager.getTotal());
    }


    @Override
    public void addUserFavoritesGoods(BbcUserFavoritesGoodsDTO.ETO eto) {
        if(null == eto.getJwtUserId()){
            throw new BusinessException("没有登录");
        }
        QueryWrapper<UserFavoritesGoods> countWrapper = MybatisPlusUtil.query();
        countWrapper.eq("goods_id",eto.getGoodsId());
        countWrapper.eq("user_id",eto.getJwtUserId());
        int count = repository.count(countWrapper);
        if(count > 0 ){
            throw new BusinessException("该商品已收藏");
        }
        UserFavoritesGoods userFavoritesGoods = new UserFavoritesGoods();
        BeanCopyUtils.copyProperties(eto, userFavoritesGoods);
        userFavoritesGoods.setId(null);
        userFavoritesGoods.setUserId(eto.getJwtUserId());
        userFavoritesGoods.setCdate(LocalDateTime.now());
        repository.save(userFavoritesGoods);
    }

    @Override
    public void deleteBatchUserFavoritesGoods(BbcUserFavoritesGoodsDTO.IdListDTO dto) {
        if(null == dto.getJwtUserId()){
            throw new BusinessException("没有登录");
        }
        if(ObjectUtils.isNotEmpty(dto.getIdList())){
            QueryWrapper<UserFavoritesGoods> deleteWrapper = MybatisPlusUtil.query();
            deleteWrapper.in("goods_id",dto.getIdList());
            deleteWrapper.eq("user_id", dto.getJwtUserId());
            userFavoritesGoodsMapper.delete(deleteWrapper);
        }
    }

    @Override
    public Integer innerFavoritesState(String goodsId, String userId) {
        if (StringUtils.isBlank(userId)) {
            return TrueFalseEnum.否.getCode();
        }
        QueryWrapper<UserFavoritesGoods> queryWrapper = MybatisPlusUtil.query();
        queryWrapper.eq("goods_id",goodsId);
        queryWrapper.eq("user_id",userId);
        UserFavoritesGoods userFavoritesGoods = repository.getOne(queryWrapper);
        if(null != userFavoritesGoods){
            return TrueFalseEnum.是.getCode();
        }
        return TrueFalseEnum.否.getCode();
    }

}
