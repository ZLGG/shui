package com.gs.lshly.biz.support.user.service.bbb.pc.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.gs.lshly.biz.support.user.entity.UserFavoritesGoods;
import com.gs.lshly.biz.support.user.mapper.UserFavoritesGoodsMapper;
import com.gs.lshly.biz.support.user.repository.IUserFavoritesGoodsRepository;
import com.gs.lshly.biz.support.user.service.bbb.pc.IBbbUserFavoritesGoodsService;
import com.gs.lshly.common.enums.TrueFalseEnum;
import com.gs.lshly.common.exception.BusinessException;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.common.struct.bbb.pc.commodity.vo.PCBbbGoodsInfoVO;
import com.gs.lshly.common.struct.bbb.pc.user.dto.BbbUserFavoritesGoodsDTO;
import com.gs.lshly.common.struct.bbb.pc.user.qto.BbbUserFavoritesGoodsQTO;
import com.gs.lshly.common.struct.bbb.pc.user.vo.BbbUserFavoritesGoodsVO;
import com.gs.lshly.common.utils.BeanCopyUtils;
import com.gs.lshly.common.utils.ListUtil;
import com.gs.lshly.middleware.mybatisplus.MybatisPlusUtil;
import com.gs.lshly.rpc.api.bbb.pc.commodity.IPCBbbGoodsInfoRpc;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
* <p>
*  服务实现类
* </p>
* @author xxfc
* @since 2020-10-28
*/
@Component
public class BbbUserFavoritesGoodsServiceImpl implements IBbbUserFavoritesGoodsService {

    @Autowired
    private IUserFavoritesGoodsRepository repository;

    @Autowired
    private UserFavoritesGoodsMapper userFavoritesGoodsMapper;

    @DubboReference
    private IPCBbbGoodsInfoRpc bbbGoodsInfoRpc;



    @Override
    public PageData<BbbUserFavoritesGoodsVO.ListVO> pageData(BbbUserFavoritesGoodsQTO.QTO qto) {
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
        List<BbbUserFavoritesGoodsVO.ListVO> voList = new ArrayList<>();
        for(UserFavoritesGoods item:pager.getRecords()){
            if(!goodsIdList.contains(item.getGoodsId())){
                goodsIdList.add(item.getGoodsId());
            }
            BbbUserFavoritesGoodsVO.ListVO listVO = new BbbUserFavoritesGoodsVO.ListVO();
            listVO.setGoodsId(item.getGoodsId());
            voList.add(listVO);
        }
        List<PCBbbGoodsInfoVO.HomeInnerServiceVO> innerGoodsList =  bbbGoodsInfoRpc.getHomeGoodsInnerServiceVO(goodsIdList,
                new BaseDTO().setJwtUserId(qto.getJwtUserId()).setJwtUserType(qto.getJwtUserType()));
        if(ObjectUtils.isEmpty(innerGoodsList) && ObjectUtils.isEmpty(voList)){
            return new PageData<>(new ArrayList<>(),qto.getPageNum(),qto.getPageSize(),pager.getTotal());
        }
        for (int i = 0; i < innerGoodsList.size(); i++) {
            for (int i1 = 0; i1 < voList.size(); i1++) {
                if (ObjectUtils.isNotEmpty(innerGoodsList.get(i).getId()) && ObjectUtils.isNotEmpty(voList.get(i1).getGoodsId())){
                    if (innerGoodsList.get(i).getId().equals(voList.get(i1).getGoodsId())){
                        BeanUtils.copyProperties(innerGoodsList.get(i),voList.get(i1));
                    }

                }
            }
        }
        return new PageData<>(voList,qto.getPageNum(),qto.getPageSize(),pager.getTotal());
    }


    @Override
    public void addUserFavoritesGoods(BbbUserFavoritesGoodsDTO.ETO eto) {
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
    public void deleteBatchUserFavoritesGoods(BbbUserFavoritesGoodsDTO.IdListDTO dto) {
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

    @Override
    public List<String> innerFavoritesListState(List<String> goodsIdList, String userId) {
        if (StringUtils.isBlank(userId) || ObjectUtils.isEmpty(goodsIdList)) {
            return new ArrayList<>();
        }
        QueryWrapper<UserFavoritesGoods> queryWrapper = MybatisPlusUtil.query();
        queryWrapper.in("goods_id",goodsIdList);
        queryWrapper.eq("user_id",userId);
        List<UserFavoritesGoods> userFavoritesGoodsList = repository.list(queryWrapper);
        return ListUtil.getIdList(UserFavoritesGoods.class,userFavoritesGoodsList,"goodsId");
    }

}
