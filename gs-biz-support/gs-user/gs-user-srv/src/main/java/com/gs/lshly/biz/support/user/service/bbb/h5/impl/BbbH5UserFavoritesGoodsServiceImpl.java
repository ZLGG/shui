package com.gs.lshly.biz.support.user.service.bbb.h5.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.gs.lshly.biz.support.user.entity.UserFavoritesGoods;
import com.gs.lshly.biz.support.user.mapper.UserFavoritesGoodsMapper;
import com.gs.lshly.biz.support.user.repository.IUserFavoritesGoodsRepository;
import com.gs.lshly.biz.support.user.service.bbb.h5.IBbbH5UserFavoritesGoodsService;
import com.gs.lshly.common.enums.TrueFalseEnum;
import com.gs.lshly.common.exception.BusinessException;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.bbb.h5.commodity.qto.BbbH5GoodsInfoQTO;
import com.gs.lshly.common.struct.bbb.h5.commodity.vo.BbbH5GoodsInfoVO;
import com.gs.lshly.common.struct.bbb.h5.user.dto.BbbH5UserFavoritesGoodsDTO;
import com.gs.lshly.common.struct.bbb.h5.user.qto.BbbH5UserFavoritesGoodsQTO;
import com.gs.lshly.common.struct.bbb.h5.user.vo.BbbH5UserFavoritesGoodsVO;
import com.gs.lshly.common.utils.BeanCopyUtils;
import com.gs.lshly.common.utils.ListUtil;
import com.gs.lshly.middleware.mybatisplus.MybatisPlusUtil;
import com.gs.lshly.rpc.api.bbb.h5.commodity.IBbbH5GoodsInfoRpc;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
public class BbbH5UserFavoritesGoodsServiceImpl implements IBbbH5UserFavoritesGoodsService {

    @Autowired
    private IUserFavoritesGoodsRepository repository;

    @Autowired
    private UserFavoritesGoodsMapper userFavoritesGoodsMapper;

    @DubboReference
    private IBbbH5GoodsInfoRpc bbbH5GoodsInfoRpc;

    @Override
    public PageData<BbbH5GoodsInfoVO.HomeInnerServiceVO> pageData(BbbH5UserFavoritesGoodsQTO.QTO qto) {
        if(null == qto.getJwtUserId()){
           throw new BusinessException("没有登录");
        }
        QueryWrapper queryWrapper = MybatisPlusUtil.query();
        queryWrapper.eq("user_id",qto.getJwtUserId());
        IPage<UserFavoritesGoods> pager = MybatisPlusUtil.pager(qto);
        repository.page(pager,queryWrapper);
        if(ObjectUtils.isEmpty(pager.getRecords())){
            return new PageData<>(new ArrayList<>(),qto.getPageNum(),qto.getPageSize(),pager.getTotal());
        }
        List<String> goodsIdList = ListUtil.getIdList(UserFavoritesGoods.class,pager.getRecords(),"goodsId");
        List<BbbH5GoodsInfoVO.HomeInnerServiceVO> voList = bbbH5GoodsInfoRpc.getHomeGoodsInnerServiceVO(goodsIdList,qto);
        return new PageData<>(voList,qto.getPageNum(),qto.getPageSize(),pager.getTotal());
    }


    @Override
    public void addUserFavoritesGoods(BbbH5UserFavoritesGoodsDTO.ETO eto) {
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
    public void deleteBatchUserFavoritesGoods(BbbH5UserFavoritesGoodsDTO.IdListDTO dto) {
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
