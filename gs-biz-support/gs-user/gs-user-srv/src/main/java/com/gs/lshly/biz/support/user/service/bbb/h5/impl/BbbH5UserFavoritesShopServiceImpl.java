package com.gs.lshly.biz.support.user.service.bbb.h5.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.gs.lshly.biz.support.user.entity.UserFavoritesShop;
import com.gs.lshly.biz.support.user.mapper.UserFavoritesShopMapper;
import com.gs.lshly.biz.support.user.repository.IUserFavoritesShopRepository;
import com.gs.lshly.biz.support.user.service.bbb.h5.IBbbH5UserFavoritesShopService;
import com.gs.lshly.common.enums.TrueFalseEnum;
import com.gs.lshly.common.exception.BusinessException;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.common.struct.bbb.h5.merchant.qto.BbbH5ShopQTO;
import com.gs.lshly.common.struct.bbb.h5.merchant.vo.BbbH5ShopVO;
import com.gs.lshly.common.struct.bbb.h5.user.dto.BbbH5UserFavoritesShopDTO;
import com.gs.lshly.common.struct.bbb.h5.user.qto.BbbH5UserFavoritesShopQTO;
import com.gs.lshly.common.struct.bbb.h5.user.vo.BbbH5UserFavoritesShopVO;
import com.gs.lshly.common.utils.BeanCopyUtils;
import com.gs.lshly.middleware.mybatisplus.MybatisPlusUtil;
import com.gs.lshly.rpc.api.bbb.h5.merchant.IBbbH5ShopRpc;
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
* @author 陈奇
* @since 2020-10-24
*/
@Component
public class BbbH5UserFavoritesShopServiceImpl implements IBbbH5UserFavoritesShopService {

    @Autowired
    private IUserFavoritesShopRepository repository;

    @Autowired
    private UserFavoritesShopMapper userFavoritesShopMapper;

    @DubboReference
    private IBbbH5ShopRpc bbbH5ShopRpc;


    @Override
    public PageData<BbbH5UserFavoritesShopVO.ListVO> pageData(BbbH5UserFavoritesShopQTO.QTO qto) {
        if(null == qto.getJwtUserId()){
            throw new BusinessException("没有登录");
        }
        QueryWrapper<UserFavoritesShop> queryWrapper = MybatisPlusUtil.query();
        IPage<UserFavoritesShop> pager = MybatisPlusUtil.pager(qto);
        queryWrapper.eq("user_id", qto.getJwtUserId());
        queryWrapper.orderByDesc("cdate");
        repository.page(pager,queryWrapper);
        if(ObjectUtils.isEmpty(pager.getRecords())){
            return new PageData<>(new ArrayList<>(),qto.getPageNum(),qto.getPageSize(),pager.getTotal());
        }
        List<String> shopIdList = new ArrayList<>();
        Map<String,BbbH5UserFavoritesShopVO.ListVO> voMap = new HashMap<>();
        for(UserFavoritesShop item:pager.getRecords()){
            shopIdList.add(item.getShopId());
            BbbH5UserFavoritesShopVO.ListVO listVO =  new BbbH5UserFavoritesShopVO.ListVO();
            listVO.setId(item.getId());
            voMap.put(item.getShopId(),listVO);
        }
        BbbH5ShopQTO.InnerListShopQTO rpcQto = new BbbH5ShopQTO.InnerListShopQTO(shopIdList);
        List<BbbH5ShopVO.InnerDetailVO> innerShopList = bbbH5ShopRpc.innerListDetailShop(rpcQto);
        for(BbbH5ShopVO.InnerDetailVO shopItem:innerShopList){
            BbbH5UserFavoritesShopVO.ListVO listVO =  voMap.get(shopItem.getId());
            if(null != listVO){
                listVO.setShopId(shopItem.getId());
                listVO.setShopLogo(shopItem.getShopLogo());
                listVO.setShopName(shopItem.getShopName());
                listVO.setShopDesc(shopItem.getShopDesc());
                listVO.setShopScore(shopItem.getShopScore());
            }
        }
        return new PageData<>(new ArrayList<>(voMap.values()),qto.getPageNum(),qto.getPageSize(),pager.getTotal());
    }

    @Override
    public void addUserFavoritesShop(BbbH5UserFavoritesShopDTO.ETO eto) {
        if(null == eto.getJwtUserId()){
            throw new BusinessException("没有登录");
        }
        QueryWrapper<UserFavoritesShop> countWrapper = MybatisPlusUtil.query();
        countWrapper.eq("shop_id",eto.getShopId());
        countWrapper.eq("user_id",eto.getJwtUserId());
        int count =  repository.count(countWrapper);
        if(count > 0){
            throw new BusinessException("店铺已收藏");
        }
        UserFavoritesShop userFavoritesShop = new UserFavoritesShop();
        BeanCopyUtils.copyProperties(eto,userFavoritesShop);
        userFavoritesShop.setCdate(LocalDateTime.now());
        userFavoritesShop.setUserId(eto.getJwtUserId());
        repository.save(userFavoritesShop);
    }

    @Override
    public void deleteBatchUserFavoritesShop(BbbH5UserFavoritesShopDTO.IdListDTO dto) {
        if(null == dto.getJwtUserId()){
            throw new BusinessException("没有登录");
        }
        if(ObjectUtils.isNotEmpty(dto.getIdList())){
            QueryWrapper<UserFavoritesShop> deleteWrapper = MybatisPlusUtil.query();
            deleteWrapper.in("shop_id", dto.getIdList());
            deleteWrapper.eq("user_id", dto.getJwtUserId());
            userFavoritesShopMapper.delete(deleteWrapper);
        }
    }

    @Override
    public Integer innerFavoritesState(BaseDTO dto, String shopId,String userId) {
        if(StringUtils.isBlank(userId)){
            return TrueFalseEnum.否.getCode();
        }
        QueryWrapper<UserFavoritesShop> queryWrapper = MybatisPlusUtil.query();
        queryWrapper.eq("shop_id",shopId);
        queryWrapper.eq("user_id",userId);
        List<UserFavoritesShop> userFavoritesShopList = repository.list(queryWrapper);
        if(ObjectUtils.isNotEmpty(userFavoritesShopList)){
            return TrueFalseEnum.是.getCode();
        }
        return TrueFalseEnum.否.getCode();
    }
}
