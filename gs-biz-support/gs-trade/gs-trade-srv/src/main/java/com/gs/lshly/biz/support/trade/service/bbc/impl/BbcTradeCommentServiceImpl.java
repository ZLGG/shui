package com.gs.lshly.biz.support.trade.service.bbc.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.gs.lshly.biz.support.trade.entity.Trade;
import com.gs.lshly.biz.support.trade.entity.TradeComment;
import com.gs.lshly.biz.support.trade.entity.TradeCommentImg;
import com.gs.lshly.biz.support.trade.entity.TradeGoods;
import com.gs.lshly.biz.support.trade.mapper.TradeCommentMapper;
import com.gs.lshly.biz.support.trade.repository.ITradeCommentImgRepository;
import com.gs.lshly.biz.support.trade.repository.ITradeCommentRepository;
import com.gs.lshly.biz.support.trade.repository.ITradeGoodsRepository;
import com.gs.lshly.biz.support.trade.repository.ITradeRepository;
import com.gs.lshly.biz.support.trade.service.bbc.IBbcTradeCommentService;
import com.gs.lshly.common.enums.OrderByTypeEnum;
import com.gs.lshly.common.enums.TradeAppealStateEnum;
import com.gs.lshly.common.enums.TradeStateEnum;
import com.gs.lshly.common.enums.TradeTrueFalseEnum;
import com.gs.lshly.common.exception.BusinessException;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.response.ResponseData;
import com.gs.lshly.common.struct.bbc.trade.dto.BbcTradeCommentBuildDTO;
import com.gs.lshly.common.struct.bbc.trade.dto.BbcTradeCommentDTO;
import com.gs.lshly.common.struct.bbc.trade.qto.BbcTradeCommentQTO;
import com.gs.lshly.common.struct.bbc.trade.vo.BbcTradeCommentListVO;
import com.gs.lshly.common.struct.bbc.user.vo.BbcUserVO;
import com.gs.lshly.middleware.mybatisplus.MybatisPlusUtil;
import com.gs.lshly.rpc.api.bbc.user.IBbcUserRpc;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

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
public class BbcTradeCommentServiceImpl implements IBbcTradeCommentService {

    private final ITradeCommentRepository repository;
    private final ITradeRepository tradeRepository;
    private final ITradeGoodsRepository tradeGoodsRepository;
    private final ITradeCommentImgRepository tradeCommentImgRepository;
    private final TradeCommentMapper tradeCommentMapper;

    @DubboReference
    private IBbcUserRpc bbcUserRpc;

    public BbcTradeCommentServiceImpl(ITradeCommentRepository repository, ITradeRepository tradeRepository,
                                      ITradeGoodsRepository tradeGoodsRepository,
                                      ITradeCommentImgRepository tradeCommentImgRepository, TradeCommentMapper tradeCommentMapper) {
        this.repository = repository;
        this.tradeRepository = tradeRepository;
        this.tradeGoodsRepository = tradeGoodsRepository;
        this.tradeCommentImgRepository = tradeCommentImgRepository;
        this.tradeCommentMapper = tradeCommentMapper;
    }


    @Override
    public ResponseData<BbcTradeCommentDTO.IdDTO> orderComment(BbcTradeCommentBuildDTO.DTO dto) {
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
        BbcUserVO.InnerUserInfoVO innerUserInfoVO = bbcUserRpc.innerGetUserInfo(dto.getJwtUserId());

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
            tradeComment.setOpenInfo(TradeTrueFalseEnum.是.getCode());
            String userName = dto.getJwtUserName();
            tradeComment.setUserName(userName.charAt(0) + "***" + userName.charAt(userName.length()-1));
        }else{
            tradeComment.setOpenInfo(TradeTrueFalseEnum.否.getCode());
            tradeComment.setUserName(dto.getJwtUserName());
        }
        tradeComment.setGoodsGrade(dto.getGoodsGrade());
        tradeComment.setTradeCreateTime(trade.getCreateTime());
        tradeComment.setDescribeGrade(dto.getDescribeGrade());
        tradeComment.setDeliveryGrade(dto.getDeliveryGrade());
        tradeComment.setServiceGrade(dto.getServiceGrade());
        tradeComment.setUserComment(dto.getUserComment());
        repository.save(tradeComment);
        //保存评论图片
        List<BbcTradeCommentBuildDTO.DTO.CommentImgData> commentImgDataList = dto.getCommentImgData();
        if(ObjectUtils.isNotEmpty(commentImgDataList)){
            List<TradeCommentImg> tradeCommentImgs = new ArrayList<>();
            for(BbcTradeCommentBuildDTO.DTO.CommentImgData commentImgData : commentImgDataList){
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
    public PageData<BbcTradeCommentListVO.ListVO> orderCommentPage(BbcTradeCommentQTO.CommentList qto) {
        QueryWrapper<BbcTradeCommentQTO.CommentList> wrapper = new QueryWrapper<>();
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
        IPage<BbcTradeCommentListVO.ListVO> page = MybatisPlusUtil.pager(qto);
        tradeCommentMapper.orderCommentPage(page,wrapper);
        List<BbcTradeCommentListVO.ListVO> voList = new ArrayList<>();
        for(BbcTradeCommentListVO.ListVO listVO : page.getRecords()){
            fillCommentListVO(listVO);
            voList.add(listVO);
        }
        return new PageData<>(voList, qto.getPageNum(), qto.getPageSize(), page.getTotal());
    }

    private void fillCommentListVO(BbcTradeCommentListVO.ListVO listVO) {
        QueryWrapper<TradeCommentImg> tradeCommentImgQueryWrapper = new QueryWrapper<>();
            tradeCommentImgQueryWrapper.eq("comment_id",listVO.getId());

        List<TradeCommentImg> tradeCommentImgs = tradeCommentImgRepository.list(tradeCommentImgQueryWrapper);
        List<BbcTradeCommentListVO.CommentImgVO> commentImgVOS = new ArrayList<>();

        for(TradeCommentImg tradeCommentImg : tradeCommentImgs){
            BbcTradeCommentListVO.CommentImgVO commentImgVO = new BbcTradeCommentListVO.CommentImgVO();
            BeanUtils.copyProperties(tradeCommentImg, commentImgVO);
            commentImgVOS.add(commentImgVO);
        }
        listVO.setCommentImgVOS(commentImgVOS);

    }

}
