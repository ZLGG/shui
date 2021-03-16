package com.gs.lshly.biz.support.trade.service.bbb.h5.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.gs.lshly.biz.support.trade.entity.Trade;
import com.gs.lshly.biz.support.trade.entity.TradeComment;
import com.gs.lshly.biz.support.trade.entity.TradeCommentImg;
import com.gs.lshly.biz.support.trade.entity.TradeGoods;
import com.gs.lshly.biz.support.trade.mapper.TradeCommentMapper;
import com.gs.lshly.biz.support.trade.repository.ITradeCommentImgRepository;
import com.gs.lshly.biz.support.trade.repository.ITradeCommentRepository;
import com.gs.lshly.biz.support.trade.repository.ITradeGoodsRepository;
import com.gs.lshly.biz.support.trade.repository.ITradeRepository;
import com.gs.lshly.biz.support.trade.service.bbb.h5.IBbbH5TradeCommentService;
import com.gs.lshly.common.enums.OrderByTypeEnum;
import com.gs.lshly.common.enums.TradeAppealStateEnum;
import com.gs.lshly.common.enums.TradeStateEnum;
import com.gs.lshly.common.enums.TradeTrueFalseEnum;
import com.gs.lshly.common.exception.BusinessException;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.response.ResponseData;
import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.common.struct.bbb.h5.merchant.qto.BbbH5ShopQTO;
import com.gs.lshly.common.struct.bbb.h5.merchant.vo.BbbH5ShopVO;
import com.gs.lshly.common.struct.bbb.h5.trade.dto.BbbH5TradeCommentBuildDTO;
import com.gs.lshly.common.struct.bbb.h5.trade.dto.BbbH5TradeCommentDTO;
import com.gs.lshly.common.struct.bbb.h5.trade.qto.BbbH5TradeCommentQTO;
import com.gs.lshly.common.struct.bbb.h5.trade.vo.BbbH5TradeCommentListVO;
import com.gs.lshly.common.struct.bbb.h5.user.vo.BbbH5UserVO;
import com.gs.lshly.common.struct.bbb.pc.merchant.vo.BbbShopVO;
import com.gs.lshly.common.struct.bbb.pc.trade.dto.BbbTradeCommentBuildDTO;
import com.gs.lshly.common.struct.bbb.pc.trade.dto.BbbTradeCommentDTO;
import com.gs.lshly.common.struct.bbb.pc.trade.vo.BbbTradeCommentListVO;
import com.gs.lshly.middleware.mybatisplus.MybatisPlusUtil;
import com.gs.lshly.rpc.api.bbb.h5.merchant.IBbbH5ShopRpc;
import com.gs.lshly.rpc.api.bbb.h5.user.IBbbH5UserRpc;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
* <p>
*  服务实现类
* </p>
* @author oy
* @since 2020-11-06
*/
@Component
public class BbbH5TradeCommentServiceImpl implements IBbbH5TradeCommentService {

    private final ITradeCommentRepository repository;
    private final ITradeRepository tradeRepository;
    private final ITradeGoodsRepository tradeGoodsRepository;
    private final ITradeCommentImgRepository tradeCommentImgRepository;
    private final TradeCommentMapper tradeCommentMapper;

    @DubboReference
    private IBbbH5UserRpc bbcUserRpc;
    @DubboReference
    private IBbbH5ShopRpc iBbbH5ShopRpc;

    public BbbH5TradeCommentServiceImpl(ITradeCommentRepository repository, ITradeRepository tradeRepository,
                                        ITradeGoodsRepository tradeGoodsRepository,
                                        ITradeCommentImgRepository tradeCommentImgRepository, TradeCommentMapper tradeCommentMapper) {
        this.repository = repository;
        this.tradeRepository = tradeRepository;
        this.tradeGoodsRepository = tradeGoodsRepository;
        this.tradeCommentImgRepository = tradeCommentImgRepository;
        this.tradeCommentMapper = tradeCommentMapper;
    }


    @Override
    public ResponseData<BbbH5TradeCommentDTO.IdDTO> orderComment(BbbH5TradeCommentBuildDTO.DTO dto) {
        if(ObjectUtils.isEmpty(dto)){
            throw new BusinessException("订单不存在");
        }
        //检查订单状态是否允许评论,订单状态:已完成
        Trade trade = tradeRepository.getById(dto.getTradeId());
        if(ObjectUtils.isEmpty(trade)){
            throw new BusinessException("订单不存在");
        }else if(trade.getTradeState().intValue() != TradeStateEnum.已完成.getCode()){
            throw new BusinessException("不允许评论");
        }

        //检查订单商品评论标识是否允许评论
        TradeGoods tradeGoods = tradeGoodsRepository.getById(dto.getTradeGoodsId());
        if(ObjectUtils.isEmpty(tradeGoods)){
            throw new BusinessException("订单不存在");
        }else if(tradeGoods.getCommentFlag().intValue() == TradeTrueFalseEnum.否.getCode()){
            throw new BusinessException("不允许评论");
        }
        BbbH5UserVO.InnerUserInfoVO innerUserInfoVO = bbcUserRpc.innerGetUserInfo(dto.getJwtUserId());

        //保存评论
        TradeComment tradeComment = new TradeComment();
        tradeComment.setTradeId(trade.getId());
        tradeComment.setTradeGoodsId(tradeGoods.getId());
        tradeComment.setUserId(dto.getJwtUserId());
        tradeComment.setShopId(trade.getShopId());
        tradeComment.setMerchantId(trade.getMerchantId());
        tradeComment.setGoodsId(tradeGoods.getGoodsId());
        tradeComment.setGoodsName(tradeGoods.getGoodsName());
        tradeComment.setSkuId(tradeGoods.getSkuId());
        tradeComment.setSkuSpecValue(tradeGoods.getSkuSpecValue());
        tradeComment.setUserHeadImg(innerUserInfoVO.getHeadImg());
        tradeComment.setOpenInfo(dto.getOpenInfo());
        if(dto.getOpenInfo().intValue() == TradeTrueFalseEnum.是.getCode()){
            String userName = dto.getJwtUserName();
            tradeComment.setUserName(userName.charAt(0) + "***" + userName.charAt(userName.length()-1));
        }else{
            tradeComment.setUserName(dto.getJwtUserName());
        }
        tradeComment.setTradeCreateTime(trade.getCreateTime());
        tradeComment.setDescribeGrade(dto.getDescribeGrade());
        tradeComment.setDeliveryGrade(dto.getDeliveryGrade());
        tradeComment.setServiceGrade(dto.getServiceGrade());
        tradeComment.setUserComment(dto.getUserComment());
        repository.save(tradeComment);
        //保存评论图片
        List<BbbH5TradeCommentBuildDTO.DTO.CommentImgData> commentImgDataList = dto.getCommentImgData();
        if(ObjectUtils.isNotEmpty(commentImgDataList)){
            List<TradeCommentImg> tradeCommentImgs = new ArrayList<>();
            for(BbbH5TradeCommentBuildDTO.DTO.CommentImgData commentImgData : commentImgDataList){
                TradeCommentImg tradeCommentImg = new TradeCommentImg();
                tradeCommentImg.setCommentId(tradeComment.getId());
                tradeCommentImg.setCommentImg(commentImgData.getCommentImg());
                tradeCommentImgs.add(tradeCommentImg);
            }
            tradeCommentImgRepository.saveBatch(tradeCommentImgs);
        }
        tradeGoods.setCommentFlag(TradeTrueFalseEnum.否.getCode());
        tradeGoodsRepository.saveOrUpdate(tradeGoods);

        return ResponseData.success();
    }

    @Override
    public PageData<BbbH5TradeCommentListVO.ListVO> orderCommentPage(BbbH5TradeCommentQTO.CommentList qto) {
        QueryWrapper<BbbH5TradeCommentQTO.CommentList> wrapper = new QueryWrapper<>();
        wrapper.and(i -> i.ne("c.`appeal_state`", TradeAppealStateEnum.通过.getCode()));
        if(ObjectUtils.isNotEmpty(qto.getGoodsId())){
            wrapper.and(i -> i.eq("c.`goods_id`",qto.getGoodsId()));
        }
        if(ObjectUtils.isNotEmpty(qto.getSkuId())){
            wrapper.and(i -> i.eq("c.`sku_id`",qto.getSkuId()));
        }
        if(ObjectUtils.isNotEmpty(qto.getOrderByType())){
            if(qto.getOrderByType().intValue() == OrderByTypeEnum.升序.getCode()){
                wrapper.orderByAsc("c.`cdate`");
            }else if(qto.getOrderByType().intValue() == OrderByTypeEnum.降序.getCode()){
                wrapper.orderByDesc("c.`cdate`");
            }
        }
        IPage<BbbH5TradeCommentListVO.ListVO> page = MybatisPlusUtil.pager(qto);
        //TODO 欧阳
        tradeCommentMapper.orderBbbH5CommentPage(page,wrapper);
        List<String> shopIds = new ArrayList<>();
        List<BbbH5TradeCommentListVO.ListVO> voList = new ArrayList<>();
        for(BbbH5TradeCommentListVO.ListVO listVO : page.getRecords()){
            fillCommentListVO(listVO);
            int daysNum=0;
            if(ObjectUtils.isNotEmpty(listVO.getAppendCommentTime())){
                daysNum=(int)(LocalDateTime.now().toLocalDate().toEpochDay() - listVO.getAppendCommentTime().toLocalDate().toEpochDay());
            }
            listVO.setAppendCommentTranslateTime(daysNum);
            shopIds.add(listVO.getShopId());
            voList.add(listVO);
        }
        setShopName(voList,shopIds);
        return new PageData<>(voList, qto.getPageNum(), qto.getPageSize(), page.getTotal());
    }

    @Override
    public BbbH5TradeCommentDTO.IdDTO orderCommentAdditional(BbbH5TradeCommentBuildDTO.AdditionalDTO dto) {
        TradeComment tradeComment = repository.getById(dto.getId());
        if (ObjectUtils.isEmpty(tradeComment)){
            throw new BusinessException("没有初评");
        }
        tradeComment.setUserAppendComment(dto.getUserAppendComment());
        tradeComment.setAppendCommentTime(LocalDateTime.now());
        //保存评论图片
        List<BbbH5TradeCommentBuildDTO.AdditionalDTO.CommentImgData> commentImgData1 = dto.getCommentImgData();
        if(ObjectUtils.isNotEmpty(commentImgData1)){
            List<TradeCommentImg> tradeCommentImgs = new ArrayList<>();
            for(BbbH5TradeCommentBuildDTO.AdditionalDTO.CommentImgData commentImgData : commentImgData1){
                TradeCommentImg tradeCommentImg = new TradeCommentImg();
                tradeCommentImg.setCommentId(tradeComment.getId());
                tradeCommentImg.setCommentImg(commentImgData.getCommentImg());
                tradeCommentImgs.add(tradeCommentImg);
            }
            tradeCommentImgRepository.saveBatch(tradeCommentImgs);
        }
        repository.saveOrUpdate(tradeComment);

        return new BbbH5TradeCommentDTO.IdDTO(tradeComment.getId());
    }

    @Override
    public void orderCommentAnonymous(BbbH5TradeCommentDTO.IdDTO dto) {
        TradeComment tradeComment = repository.getById(dto.getId());
        if (ObjectUtils.isEmpty(tradeComment)){
            throw new BusinessException("没有查询到该评价");
        }
        if (tradeComment.getOpenInfo().equals(TradeTrueFalseEnum.是.getCode())){
            throw new BusinessException("已经是匿名评论");
        }else {
            tradeComment.setOpenInfo(TradeTrueFalseEnum.是.getCode());
            tradeComment.setUserName(tradeComment.getUserName().charAt(0) + "***" + tradeComment.getUserName().charAt(tradeComment.getUserName().length()-1));
        }
        repository.saveOrUpdate(tradeComment);
    }

    @Override
    public void deleteComment(BbbH5TradeCommentDTO.IdDTO dto) {
        if (StringUtils.isBlank(dto.getId())){
            throw new BusinessException("请传入评价的ID");
        }
        //删除图片
        TradeComment tradeComment = repository.getById(dto.getId());
        if (ObjectUtils.isEmpty(tradeComment)){
            throw new BusinessException("没有评论");
        }
        QueryWrapper<TradeCommentImg> query = MybatisPlusUtil.query();
        query.and(i->i.eq("comment_id",dto.getId()));
        tradeCommentImgRepository.remove(query);
        repository.removeById(dto.getId());
    }

    private void setShopName( List<BbbH5TradeCommentListVO.ListVO> voList,List<String> shopIds) {
        if (ObjectUtils.isNotEmpty(voList) || ObjectUtils.isNotEmpty(shopIds)){
            List<BbbH5ShopVO.InnerDetailVO> innerDetailVOS = iBbbH5ShopRpc.innerListDetailShop(new BbbH5ShopQTO.InnerListShopQTO(shopIds));
            if (ObjectUtils.isNotEmpty(innerDetailVOS)){
                for (BbbH5ShopVO.InnerDetailVO complexDetailVO:innerDetailVOS){
                    for (int i = 0; i < voList.size(); i++) {
                        if (voList.get(i).getShopId().equals(complexDetailVO.getId())){
                            voList.get(i).setShopName(complexDetailVO.getShopName());
                        }
                    }
                }
            }
        }

    }

    private void fillCommentListVO(BbbH5TradeCommentListVO.ListVO listVO) {
        QueryWrapper<TradeCommentImg> tradeCommentImgQueryWrapper = new QueryWrapper<>();
            tradeCommentImgQueryWrapper.eq("comment_id",listVO.getId());

        List<TradeCommentImg> tradeCommentImgs = tradeCommentImgRepository.list(tradeCommentImgQueryWrapper);
        List<BbbH5TradeCommentListVO.CommentImgVO> commentImgVOS = new ArrayList<>();

        for(TradeCommentImg tradeCommentImg : tradeCommentImgs){
            BbbH5TradeCommentListVO.CommentImgVO commentImgVO = new BbbH5TradeCommentListVO.CommentImgVO();
            BeanUtils.copyProperties(tradeCommentImg, commentImgVO);
            commentImgVOS.add(commentImgVO);
        }
        listVO.setCommentImgVOS(commentImgVOS);

    }

}
