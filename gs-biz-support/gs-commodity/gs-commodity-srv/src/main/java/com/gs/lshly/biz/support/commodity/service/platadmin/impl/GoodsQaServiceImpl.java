package com.gs.lshly.biz.support.commodity.service.platadmin.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.gs.lshly.biz.support.commodity.entity.GoodsQa;
import com.gs.lshly.biz.support.commodity.mapper.GoodsQaMapper;
import com.gs.lshly.biz.support.commodity.mapper.view.GoodsQaView;
import com.gs.lshly.biz.support.commodity.repository.IGoodsQaRepository;
import com.gs.lshly.biz.support.commodity.service.platadmin.IGoodsInfoService;
import com.gs.lshly.biz.support.commodity.service.platadmin.IGoodsQaService;
import com.gs.lshly.common.enums.GoodsQaReplyStateEnum;
import com.gs.lshly.common.exception.BusinessException;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.common.CommonShopVO;
import com.gs.lshly.common.struct.platadmin.commodity.dto.GoodsInfoDTO;
import com.gs.lshly.common.struct.platadmin.commodity.dto.GoodsQaDTO;
import com.gs.lshly.common.struct.platadmin.commodity.qto.GoodsQaQTO;
import com.gs.lshly.common.struct.platadmin.commodity.vo.GoodsInfoVO;
import com.gs.lshly.common.struct.platadmin.commodity.vo.GoodsQaVO;
import com.gs.lshly.common.utils.ListUtil;
import com.gs.lshly.middleware.mybatisplus.MybatisPlusUtil;
import com.gs.lshly.rpc.api.common.ICommonShopRpc;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author Starry
 * @Date 16:33 2020/10/17
 */
@Component
public class GoodsQaServiceImpl implements IGoodsQaService {
    @Autowired
    private IGoodsQaRepository repository;
    @Autowired
    private GoodsQaMapper goodsQaMapper;
    @Autowired
    private IGoodsInfoService goodsInfoService;
    @DubboReference
    private ICommonShopRpc commonShopRpc;

    @Override
    public PageData<GoodsQaVO.GoodsQaListVO> pageGoodsQa(GoodsQaQTO.QTO qto) {
        QueryWrapper<GoodsQaView> wrapperBoost = MybatisPlusUtil.query();
        if (qto.getIsReply() != null && qto.getIsReply().intValue() != 0){
            wrapperBoost.eq("gqa.is_reply",qto.getIsReply());
        }
        if (StringUtils.isNotEmpty(qto.getQuizContent())){
            wrapperBoost.like("gqa.quiz_content",qto.getQuizContent());
        }
        if (qto.getQuizType() != null && qto.getQuizType().intValue() !=0 && qto.getQuizType().intValue() !=-1){
            wrapperBoost.eq("gqa.quiz_type",qto.getQuizType());
        }
        if (StringUtils.isNotBlank(qto.getOperator())){
            wrapperBoost.like("gqa.operator",qto.getOperator());
        }
        if (StringUtils.isNotBlank(qto.getShopName())){
            List<String> shopIdList = getSearchShopIdList(qto.getShopName());
            if(ObjectUtils.isEmpty(shopIdList)){
                return new PageData<>();
            }
            wrapperBoost.in("gqa.shop_id",shopIdList);
        }
        wrapperBoost.orderByDesc("gqa.cdate","gqa.id");
        IPage<GoodsQaView> page = MybatisPlusUtil.pager(qto);
        IPage<GoodsQaView> pageGoodsQas = goodsQaMapper.getGoodsQaListVO(page,wrapperBoost);
        if (ObjectUtils.isEmpty(pageGoodsQas) || ObjectUtils.isEmpty(pageGoodsQas.getRecords())){
            return new PageData<>();
        }
        List<GoodsQaVO.GoodsQaListVO> goodsQaListVOS = pageGoodsQas.getRecords()
                .stream().map(e ->{
                    GoodsQaVO.GoodsQaListVO goodsQaListVO = new GoodsQaVO.GoodsQaListVO();
                    BeanUtils.copyProperties(e,goodsQaListVO);
                    CommonShopVO.SimpleVO simpleVO = getSimpleVO(e.getShopId());
                    goodsQaListVO.setShopName(simpleVO.getShopName());
                    goodsQaListVO.setShopType(simpleVO.getShopType());
                    return goodsQaListVO;
                }).collect(Collectors.toList());
        return new PageData<>(goodsQaListVOS,qto.getPageNum(),qto.getPageSize(),pageGoodsQas.getTotal());
    }

    @Override
    public GoodsQaVO.GoodsQaDetailVO getGoodsQaDetailVO(GoodsQaDTO.IdDTO dto) {
        if (dto == null){
            throw new BusinessException("参数不能为空！");
        }
        QueryWrapper<GoodsQa> qaQueryWrapper = MybatisPlusUtil.query();
        qaQueryWrapper.eq("id",dto.getId());
        GoodsQa goodsQa = repository.getOne(qaQueryWrapper);
        if (goodsQa == null){
            throw new BusinessException("查询异常！");
        }
        GoodsQaVO.GoodsQaDetailVO detailVO = new GoodsQaVO.GoodsQaDetailVO();
        BeanUtils.copyProperties(goodsQa,detailVO);
        CommonShopVO.SimpleVO simpleVO = getSimpleVO(goodsQa.getShopId());
        //  店铺名称
        detailVO.setShopName(simpleVO.getShopName());
        //  店铺类型
        detailVO.setShopType(simpleVO.getShopType());
        //获取商家名称
        CommonShopVO.MerchantVO merchantVO = commonShopRpc.merchantByShopId(goodsQa.getShopId());
        detailVO.setMerchantName(StringUtils.isEmpty(merchantVO.getMerchantName())?"":merchantVO.getMerchantName());

        GoodsInfoVO.GoodsNameVO goodsNameVO = goodsInfoService.getGoodsName(new GoodsInfoDTO.IdDTO(goodsQa.getGoodId()));
        detailVO.setGoodsName(StringUtils.isEmpty(goodsNameVO.goodsName)?"":goodsNameVO.getGoodsName());
        detailVO.setGoodsImg(getImage(goodsNameVO.getGoodsImage()));
        return detailVO;
    }

    @Override
    public void deleteGoodsQa(GoodsQaDTO.IdDTO dto) {
        if (dto == null){
            throw new BusinessException("参数异常");
        }
        repository.removeById(dto.getId());
    }

    @Override
    public void deleteGoodsQaReply(GoodsQaDTO.IdDTO dto) {
        if (dto == null){
            throw new BusinessException("参数异常");
        }
        UpdateWrapper<GoodsQa> goodsQaUpdateWrapper = new UpdateWrapper<>();
        goodsQaUpdateWrapper.eq("id",dto.getId());

        GoodsQa goodsQa = new GoodsQa();
        goodsQa.setContent("");
        goodsQa.setIsReply(GoodsQaReplyStateEnum.未回复.getCode());

        repository.update(goodsQa,goodsQaUpdateWrapper);
    }

    @Override
    public void IsShowGoodsQaContent(GoodsQaDTO.ShowContentETO eto) {
        if (eto == null){
            throw new BusinessException("参数不能为空！");
        }
        GoodsQa qa = new GoodsQa();
        BeanUtils.copyProperties(eto,qa);
        repository.updateById(qa);
    }

    private List<String> getSearchShopIdList(String shopName){
        List<CommonShopVO.SimpleVO> simpleVOS = commonShopRpc.searchDetailShop(shopName);
        if (ObjectUtils.isEmpty(simpleVOS)){
            return new ArrayList<>();
        }
        List<String> shopIdList = ListUtil.getIdList(CommonShopVO.SimpleVO.class,simpleVOS);
        return shopIdList;
    }

    private CommonShopVO.SimpleVO getSimpleVO(String shopId){
        CommonShopVO.SimpleVO simpleVO = commonShopRpc.shopDetails(shopId);
        if (ObjectUtils.isEmpty(simpleVO)){
            throw new BusinessException("店铺信息异常！");
        }
        return simpleVO;
    }

    private  String getImage(String images){
        if (images !=null){
            JSONArray arr = JSONArray.parseArray(images);
            if (ObjectUtils.isEmpty(arr)){
                return null;
            }
            JSONObject obj = arr.getJSONObject(0);
            String imgUrl = obj.getString("imgSrc");
            return imgUrl;
        }
        return null;
    }

}
