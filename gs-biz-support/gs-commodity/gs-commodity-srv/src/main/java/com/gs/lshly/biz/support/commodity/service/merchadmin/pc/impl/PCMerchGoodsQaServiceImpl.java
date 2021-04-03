package com.gs.lshly.biz.support.commodity.service.merchadmin.pc.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
import com.gs.lshly.biz.support.commodity.service.merchadmin.pc.IPCMerchGoodsInfoService;
import com.gs.lshly.biz.support.commodity.service.merchadmin.pc.IPCMerchGoodsQaService;
import com.gs.lshly.common.enums.GoodsQaReplyStateEnum;
import com.gs.lshly.common.enums.ShowQuizStateEnum;
import com.gs.lshly.common.exception.BusinessException;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.common.CommonShopVO;
import com.gs.lshly.common.struct.merchadmin.pc.commodity.dto.PCMerchGoodsQaDTO;
import com.gs.lshly.common.struct.merchadmin.pc.commodity.qto.PCMerchGoodsQaQTO;
import com.gs.lshly.common.struct.merchadmin.pc.commodity.vo.PCMerchGoodsQaVO;
import com.gs.lshly.middleware.mybatisplus.MybatisPlusUtil;
import com.gs.lshly.rpc.api.common.ICommonShopRpc;

/**
 * @Author Starry
 * @Date 10:01 2020/10/17
 */
@Service
public class PCMerchGoodsQaServiceImpl implements IPCMerchGoodsQaService {
    @Autowired
    private IGoodsQaRepository repository;
    @Autowired
    private GoodsQaMapper goodsQaMapper;
    @Autowired
    private IPCMerchGoodsInfoService merchGoodsInfoService;

    @DubboReference
    private ICommonShopRpc commonShopRpc;


    @Override
    public PageData<PCMerchGoodsQaVO.ReplyListVO> pageMerchantGoodsQa(PCMerchGoodsQaQTO.GoodsQaQTO qto) {
        QueryWrapper<GoodsQaView> qaQueryWrapper = MybatisPlusUtil.query();
        if (qto.getIsReply() != null && qto.getIsReply().intValue() != 0 && qto.getIsReply().intValue()!=-1){
            qaQueryWrapper.eq("gqa.is_reply",qto.getIsReply());
        }
        if (qto.getQuizType() != null && qto.getQuizType().intValue() != 0 && qto.getQuizType().intValue()!=-1){
            qaQueryWrapper.eq("gqa.quiz_type",qto.getQuizType());
        }
        qaQueryWrapper.eq("gqa.shop_id",qto.getJwtShopId());
        qaQueryWrapper.orderByDesc("gqa.cdate","gqa.id");
        IPage<GoodsQaView> page = MybatisPlusUtil.pager(qto);
        IPage<GoodsQaView> qaList = goodsQaMapper.getGoodsQaListVO(page,qaQueryWrapper);
        if (ObjectUtils.isEmpty(qaList)){
            return new PageData<>();
        }
        List<PCMerchGoodsQaVO.ReplyListVO> replyListVOS = qaList.getRecords()
                .stream().map(e ->{
                    PCMerchGoodsQaVO.ReplyListVO replyListVO = new PCMerchGoodsQaVO.ReplyListVO();
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
    public void replyGoodsQa(PCMerchGoodsQaDTO.MerchantReplyETO eto) {
        if (eto == null){
            throw new BusinessException("参数异常！");
        }
       GoodsQa goodsQa = new GoodsQa();
       BeanUtils.copyProperties(eto,goodsQa);
       goodsQa.setIsReply(GoodsQaReplyStateEnum.已回复.getCode());
       goodsQa.setIsShowContent(ShowQuizStateEnum.显示.getCode());
       repository.updateById(goodsQa);
    }

    @Override
    public void IsShowGoodsQaContent(PCMerchGoodsQaDTO.ShowContentETO eto) {

        if (eto == null){
            throw new BusinessException("参数不能为空！");
        }
        GoodsQa qa = new GoodsQa();
        BeanUtils.copyProperties(eto,qa);
        repository.updateById(qa);
    }

    @Override
    public void deleteGoodsQaReply(PCMerchGoodsQaDTO.IdDTO dto) {
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
