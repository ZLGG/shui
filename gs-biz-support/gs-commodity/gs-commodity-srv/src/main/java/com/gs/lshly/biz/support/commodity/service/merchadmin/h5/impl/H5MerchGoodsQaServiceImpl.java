package com.gs.lshly.biz.support.commodity.service.merchadmin.h5.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.gs.lshly.biz.support.commodity.entity.GoodsQa;
import com.gs.lshly.biz.support.commodity.mapper.GoodsQaMapper;
import com.gs.lshly.biz.support.commodity.mapper.view.GoodsQaView;
import com.gs.lshly.biz.support.commodity.repository.IGoodsQaRepository;
import com.gs.lshly.biz.support.commodity.service.merchadmin.h5.IH5MerchGoodsQaService;
import com.gs.lshly.biz.support.commodity.service.merchadmin.pc.IPCMerchGoodsInfoService;
import com.gs.lshly.common.enums.GoodsQaReplyStateEnum;
import com.gs.lshly.common.enums.ShowQuizStateEnum;
import com.gs.lshly.common.exception.BusinessException;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.common.CommonShopVO;
import com.gs.lshly.common.struct.merchadmin.h5.commodity.dto.H5MerchGoodsQaDTO;
import com.gs.lshly.common.struct.merchadmin.h5.commodity.qto.H5MerchGoodsQaQTO;
import com.gs.lshly.common.struct.merchadmin.h5.commodity.vo.H5MerchGoodsQaVO;
import com.gs.lshly.rpc.api.common.ICommonShopRpc;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.gs.lshly.middleware.mybatisplus.MybatisPlusUtil;

import java.util.List;
import java.util.stream.Collectors;


/**
* <p>
*  服务实现类
* </p>
* @author zst
* @since 2021-01-22
*/
@Component
public class H5MerchGoodsQaServiceImpl implements IH5MerchGoodsQaService {

    @Autowired
    private IGoodsQaRepository repository;
    @Autowired
    private GoodsQaMapper goodsQaMapper;
    @Autowired
    private IPCMerchGoodsInfoService merchGoodsInfoService;

    @DubboReference
    private ICommonShopRpc commonShopRpc;

    @Override
    public PageData<H5MerchGoodsQaVO.ReplyListVO> pageMerchantH5GoodsQa(H5MerchGoodsQaQTO.GoodsQaQTO qto) {
        QueryWrapper<GoodsQaView> qaQueryWrapper = MybatisPlusUtil.query();
        if (qto.getIsReply() != null && qto.getIsReply().intValue() != 0 && qto.getIsReply().intValue()!=-1){
            qaQueryWrapper.eq("gqa.is_reply",qto.getIsReply());
        }
        if (qto.getQuizType() != null && qto.getQuizType().intValue() != 0 && qto.getQuizType().intValue()!=-1){
            qaQueryWrapper.eq("gqa.quiz_type",qto.getQuizType());
        }
        qaQueryWrapper.eq("gqa.shop_id",qto.getJwtShopId());
        IPage<GoodsQaView> page = MybatisPlusUtil.pager(qto);
        IPage<GoodsQaView> qaList = goodsQaMapper.getGoodsQaListVO(page,qaQueryWrapper);
        if (ObjectUtils.isEmpty(qaList)){
            return new PageData<>();
        }
        List<H5MerchGoodsQaVO.ReplyListVO> replyListVOS = qaList.getRecords()
                .stream().map(e ->{
                    H5MerchGoodsQaVO.ReplyListVO replyListVO = new H5MerchGoodsQaVO.ReplyListVO();
                    BeanUtils.copyProperties(e,replyListVO);
                    replyListVO.setGoodsImage(getImage(e.getGoodsImage()));
                    CommonShopVO.SimpleVO simpleVO = getSimpleVO(e.getShopId());
                    replyListVO.setShopName(simpleVO.getShopName());

                    //获取商家名称
                    CommonShopVO.MerchantVO merchantVO = commonShopRpc.merchantByShopId(qto.getJwtShopId());
                    replyListVO.setMerchantName(StringUtils.isEmpty(merchantVO.getMerchantName())?"":merchantVO.getMerchantName());
                    return replyListVO;
                }).collect(Collectors.toList());
        return new PageData<>(replyListVOS,qto.getPageNum(),qto.getPageSize(),qaList.getTotal());
    }


    @Override
    public void replyGoodsQa(H5MerchGoodsQaDTO.MerchantReplyETO eto) {
        if (eto == null){
            throw new BusinessException("参数异常！");
        }
        GoodsQa goodsQa = new GoodsQa();
        BeanUtils.copyProperties(eto,goodsQa);
        goodsQa.setIsReply(GoodsQaReplyStateEnum.已回复.getCode());
        goodsQa.setIsShowContent(ShowQuizStateEnum.不显示.getCode());
        repository.updateById(goodsQa);
    }


    @Override
    public void IsShowGoodsQaContent(H5MerchGoodsQaDTO.ShowContentETO eto) {

        if (eto == null){
            throw new BusinessException("参数不能为空！");
        }
        GoodsQa qa = new GoodsQa();
        BeanUtils.copyProperties(eto,qa);
        repository.updateById(qa);
    }


    @Override
    public void deleteGoodsQaReply(H5MerchGoodsQaDTO.IdDTO dto) {
        if (ObjectUtils.isEmpty(dto)){
            throw new BusinessException("参数异常");
        }
        UpdateWrapper<GoodsQa> goodsQaUpdateWrapper = new UpdateWrapper<>();
        goodsQaUpdateWrapper.eq("id",dto.getId());

        GoodsQa goodsQa = new GoodsQa();
        goodsQa.setContent("");
        goodsQa.setIsReply(GoodsQaReplyStateEnum.未回复.getCode());

        repository.update(goodsQa,goodsQaUpdateWrapper);
    }

    private  String getImage(String images){
        if (images !=null&&!images.equals("{}")){
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

    private CommonShopVO.SimpleVO getSimpleVO(String shopId){
        CommonShopVO.SimpleVO simpleVO = commonShopRpc.shopDetails(shopId);
        if (ObjectUtils.isEmpty(simpleVO)){
            throw new BusinessException("店铺信息异常！");
        }
        return simpleVO;
    }


}
