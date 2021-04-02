package com.gs.lshly.biz.support.user.service.bbb.pc.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.gs.lshly.biz.support.user.entity.UserFavoritesShop;
import com.gs.lshly.biz.support.user.mapper.UserFavoritesShopMapper;
import com.gs.lshly.biz.support.user.repository.IUserFavoritesShopRepository;
import com.gs.lshly.biz.support.user.service.bbb.pc.IBbbUserFavoritesShopService;
import com.gs.lshly.common.enums.TrueFalseEnum;
import com.gs.lshly.common.exception.BusinessException;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.common.struct.bbb.pc.merchant.vo.BbbShopVO;
import com.gs.lshly.common.struct.bbb.pc.user.dto.BbbUserFavoritesShopDTO;
import com.gs.lshly.common.struct.bbb.pc.user.qto.BbbUserFavoritesShopQTO;
import com.gs.lshly.common.struct.bbb.pc.user.vo.BbbUserFavoritesShopVO;
import com.gs.lshly.common.struct.common.CommonShopVO;
import com.gs.lshly.common.utils.BeanCopyUtils;
import com.gs.lshly.common.utils.ListUtil;
import com.gs.lshly.middleware.mybatisplus.MybatisPlusUtil;
import com.gs.lshly.rpc.api.bbb.pc.merchant.IBbbShopRpc;
import com.gs.lshly.rpc.api.common.ICommonShopRpc;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.BeanUtils;
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
public class BbbUserFavoritesShopServiceImpl implements IBbbUserFavoritesShopService {

    @Autowired
    private IUserFavoritesShopRepository repository;
    @Autowired
    private UserFavoritesShopMapper userFavoritesShopMapper;
    @DubboReference
    private IBbbShopRpc bbbShopRpc;
    @DubboReference
    private ICommonShopRpc commonShopRpc;

    @Override
    public PageData<BbbUserFavoritesShopVO.ListVO> pageData(BbbUserFavoritesShopQTO.QTO qto) {
        if(null == qto.getJwtUserId()){
            throw new BusinessException("没有登录");
        }
        QueryWrapper<UserFavoritesShop> queryWrapper = MybatisPlusUtil.query();
        IPage<UserFavoritesShop> pager = MybatisPlusUtil.pager(qto);
        queryWrapper.eq("user_id", qto.getJwtUserId());
        queryWrapper.orderByDesc("cdate");
        List<BbbUserFavoritesShopVO.ListVO> voList = new ArrayList<>();
        repository.page(pager,queryWrapper);
        if(ObjectUtils.isEmpty(pager.getRecords())){
            return MybatisPlusUtil.toPageData(voList,qto.getPageNum(),qto.getPageSize(),pager.getTotal());
        }
        List<String> shopIdList = new ArrayList<>();
        for(UserFavoritesShop favoritesShop:pager.getRecords()){
            shopIdList.add(favoritesShop.getShopId());
            BbbUserFavoritesShopVO.ListVO listVO =  new BbbUserFavoritesShopVO.ListVO();
            BeanUtils.copyProperties(favoritesShop,listVO);
            voList.add(listVO);
        }
        List<CommonShopVO.SimpleDetailVO> simpleDetailList  = commonShopRpc.listSimpleByShopIdList(shopIdList);
        for(CommonShopVO.SimpleDetailVO simpleDetailVO:simpleDetailList){
            for(BbbUserFavoritesShopVO.ListVO listVO:voList){
                if(listVO.getShopId().equals(simpleDetailVO.getId())){
                    listVO.setShopId(simpleDetailVO.getId());
                    listVO.setShopLogo(simpleDetailVO.getShopLogo());
                    listVO.setShopName(simpleDetailVO.getShopName());
                    listVO.setShopDesc(simpleDetailVO.getShopDesc());
                    listVO.setShopScore(simpleDetailVO.getShopScore());
                }
            }
        }
        return MybatisPlusUtil.toPageData(voList,qto.getPageNum(),qto.getPageSize(),pager.getTotal());
    }

    @Override
    public void addUserFavoritesShop(BbbUserFavoritesShopDTO.ETO eto) {
        if(null == eto.getJwtUserId()){
            throw new BusinessException("没有登录");
        }
        QueryWrapper<UserFavoritesShop> countWrapper = MybatisPlusUtil.query();
        System.out.println("shopId:" + eto.getShopId());
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
    public void deleteBatchUserFavoritesShop(BbbUserFavoritesShopDTO.IdListDTO dto) {
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

    @Override
    public List<String> innerFavoritesListState(BaseDTO dto, List<String> shopIdList, String userId) {
        if(StringUtils.isBlank(userId) || ObjectUtils.isEmpty(shopIdList)){
            return new ArrayList<>();
        }
        QueryWrapper<UserFavoritesShop> queryWrapper = MybatisPlusUtil.query();
        queryWrapper.in("shop_id",shopIdList);
        queryWrapper.eq("user_id",userId);
        List<UserFavoritesShop> userFavoritesShopList = repository.list(queryWrapper);
        return ListUtil.getIdList(UserFavoritesShop.class,userFavoritesShopList,"shopId");
    }
}
