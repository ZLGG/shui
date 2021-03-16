package com.gs.lshly.biz.support.user.service.bbc.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.gs.lshly.biz.support.user.entity.UserFavoritesGoods;
import com.gs.lshly.biz.support.user.entity.UserFavoritesShop;
import com.gs.lshly.biz.support.user.enums.UserFavoritesShopStateEnum;
import com.gs.lshly.biz.support.user.mapper.UserFavoritesShopMapper;
import com.gs.lshly.biz.support.user.repository.IUserFavoritesShopRepository;
import com.gs.lshly.biz.support.user.service.bbc.IBbcUserFavoritesShopService;
import com.gs.lshly.common.enums.TrueFalseEnum;
import com.gs.lshly.common.exception.BusinessException;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.common.struct.bbc.merchant.qto.BbcShopQTO;
import com.gs.lshly.common.struct.bbc.merchant.vo.BbcShopVO;
import com.gs.lshly.common.struct.bbc.user.dto.BbcUserFavoritesShopDTO;
import com.gs.lshly.common.struct.bbc.user.qto.BbcUserFavoritesShopQTO;
import com.gs.lshly.common.struct.bbc.user.vo.BbcUserFavoritesGoodsVO;
import com.gs.lshly.common.struct.bbc.user.vo.BbcUserFavoritesShopVO;
import com.gs.lshly.common.utils.BeanCopyUtils;
import com.gs.lshly.common.utils.ListUtil;
import com.gs.lshly.middleware.mybatisplus.MybatisPlusUtil;
import com.gs.lshly.rpc.api.bbc.merchant.IBbcShopRpc;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;

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
public class BbcUserFavoritesShopServiceImpl implements IBbcUserFavoritesShopService {

    @Autowired
    private IUserFavoritesShopRepository repository;

    @Autowired
    private UserFavoritesShopMapper userFavoritesShopMapper;

    @DubboReference
    private IBbcShopRpc bbcShopRpc;


    @Override
    public PageData<BbcUserFavoritesShopVO.ListVO> pageData(BbcUserFavoritesShopQTO.QTO qto) {
        if(null == qto.getJwtUserId()){
            throw new BusinessException("没有登录");
        }
        QueryWrapper<UserFavoritesShop> queryWrapper = MybatisPlusUtil.query();
        IPage<UserFavoritesShop> pager = MybatisPlusUtil.pager(qto);
        queryWrapper.eq("user_id", qto.getJwtUserId());
        repository.page(pager,queryWrapper);
        if(ObjectUtils.isEmpty(pager.getRecords())){
            return new PageData<>(new ArrayList<>(),qto.getPageNum(),qto.getPageSize(),pager.getTotal());
        }
        List<String> shopIdList = new ArrayList<>();
        Map<String,BbcUserFavoritesShopVO.ListVO> voMap = new HashMap<>();
        for(UserFavoritesShop item:pager.getRecords()){
            shopIdList.add(item.getShopId());
            BbcUserFavoritesShopVO.ListVO listVO =  new BbcUserFavoritesShopVO.ListVO();
            listVO.setId(item.getId());
            voMap.put(item.getShopId(),listVO);
        }
        BbcShopQTO.InnerListShopQTO rpcQto = new BbcShopQTO.InnerListShopQTO(shopIdList);
        List<BbcShopVO.InnerDetailVO> innerShopList = bbcShopRpc.innerListDetailShop(rpcQto);
        for(BbcShopVO.InnerDetailVO shopItem:innerShopList){
            BbcUserFavoritesShopVO.ListVO listVO =  voMap.get(shopItem.getId());
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
    public void addUserFavoritesShop(BbcUserFavoritesShopDTO.ETO eto) {
        if(null == eto.getJwtUserId()){
            throw new BusinessException("没有登录");
        }
        QueryWrapper<UserFavoritesShop> countWrapper = MybatisPlusUtil.query();
        countWrapper.eq("shop_id",eto.getId());
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
    public void deleteBatchUserFavoritesShop(BbcUserFavoritesShopDTO.IdListDTO dto) {
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
