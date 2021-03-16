package com.gs.lshly.biz.support.trade.service.bbb.pc.impl;

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
import com.gs.lshly.biz.support.trade.service.bbb.pc.IBbbTradeCommentService;
import com.gs.lshly.common.enums.OrderByTypeEnum;
import com.gs.lshly.common.enums.TradeAppealStateEnum;
import com.gs.lshly.common.enums.TradeStateEnum;
import com.gs.lshly.common.enums.TradeTrueFalseEnum;
import com.gs.lshly.common.exception.BusinessException;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.response.ResponseData;
import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.common.struct.bbb.pc.merchant.vo.BbbShopVO;
import com.gs.lshly.common.struct.bbb.pc.trade.dto.BbbTradeCommentBuildDTO;
import com.gs.lshly.common.struct.bbb.pc.trade.dto.BbbTradeCommentDTO;
import com.gs.lshly.common.struct.bbb.pc.trade.dto.BbbTradeCommentQTO;
import com.gs.lshly.common.struct.bbb.pc.trade.vo.BbbTradeCommentListVO;
import com.gs.lshly.common.struct.bbb.pc.user.vo.BbbUserVO;
import com.gs.lshly.middleware.mybatisplus.MybatisPlusUtil;
import com.gs.lshly.rpc.api.bbb.pc.merchant.IBbbShopRpc;
import com.gs.lshly.rpc.api.bbb.pc.user.IBbbUserRpc;
import com.gs.lshly.rpc.api.platadmin.merchant.IShopRpc;
import io.swagger.annotations.ApiModelProperty;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class BbbTradeCommentServiceImpl implements IBbbTradeCommentService {
    @Autowired
    private  ITradeCommentRepository repository;
    @Autowired
    private  ITradeRepository tradeRepository;
    @Autowired
    private  ITradeGoodsRepository tradeGoodsRepository;
    @Autowired
    private  ITradeCommentImgRepository tradeCommentImgRepository;
    @Autowired
    private  TradeCommentMapper tradeCommentMapper;
    @DubboReference
    private IBbbUserRpc bbbUserRpc;
    @DubboReference
    private IBbbShopRpc iBbbShopRpc;
    @Override
    public ResponseData<BbbTradeCommentDTO.IdDTO> orderComment(BbbTradeCommentBuildDTO.DTO dto) {
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
        BbbUserVO.InnerUserInfoVO innerUserInfoVO = bbbUserRpc.innerGetUserInfo(dto.getJwtUserId());

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
        if (ObjectUtils.isNotEmpty(dto.getGoodsGrade())){
            if (dto.getGoodsGrade()>0&&dto.getGoodsGrade()<=2){
                tradeComment.setGoodsGrade(10);
            }else if (dto.getGoodsGrade()>2&&dto.getGoodsGrade()<=4){
                tradeComment.setGoodsGrade(20);
            }else if (dto.getGoodsGrade()==5){
                tradeComment.setGoodsGrade(30);
            }
        }
        tradeComment.setSkuSpecValue(tradeGoods.getSkuSpecValue());
        if (ObjectUtils.isNotEmpty(innerUserInfoVO)){
            tradeComment.setUserHeadImg(innerUserInfoVO.getHeadImg());
        }
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
        List<BbbTradeCommentBuildDTO.DTO.CommentImgData> commentImgDataList = dto.getCommentImgData();
        if(ObjectUtils.isNotEmpty(commentImgDataList)){
            List<TradeCommentImg> tradeCommentImgs = new ArrayList<>();
            for(BbbTradeCommentBuildDTO.DTO.CommentImgData commentImgData : commentImgDataList){
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
    public PageData<BbbTradeCommentListVO.ListVO> orderCommentPage(BbbTradeCommentQTO.CommentList qto) {
        QueryWrapper<BbbTradeCommentQTO.CommentList> wrapper = new QueryWrapper<>();
        wrapper.and(i -> i.ne("c.`appeal_state`", TradeAppealStateEnum.通过.getCode()));
        if(ObjectUtils.isNotEmpty(qto.getGoodsId())){
            wrapper.and(i -> i.eq("c.`goods_id`",qto.getGoodsId()));
        }
        if(ObjectUtils.isNotEmpty(qto.getSkuId())){
            wrapper.and(i -> i.eq("c.`sku_id`",qto.getSkuId()));
        }
        //降序
        wrapper.orderByDesc("c.`cdate`");
        IPage<BbbTradeCommentListVO.ListVO> page = MybatisPlusUtil.pager(qto);
        List<String> shopIds = new ArrayList<>();
        tradeCommentMapper.orderBbbCommentPage(page,wrapper);
        if (ObjectUtils.isEmpty(page) ){
            new PageData<>();
        }
        List<BbbTradeCommentListVO.ListVO> voList = new ArrayList<>();
        for(BbbTradeCommentListVO.ListVO listVO : page.getRecords()){
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

    private void setShopName(List<BbbTradeCommentListVO.ListVO> voList,List<String> shopIds) {
        if (ObjectUtils.isNotEmpty(voList) || ObjectUtils.isNotEmpty(shopIds)){
            List<BbbShopVO.ComplexDetailVO> complexDetailVOS = iBbbShopRpc.innerListComplexDetailShop(shopIds, new BaseDTO());
            if (ObjectUtils.isNotEmpty(complexDetailVOS)){
                for (BbbShopVO.ComplexDetailVO complexDetailVO:complexDetailVOS){
                    for (int i = 0; i < voList.size(); i++) {
                        if (voList.get(i).getShopId().equals(complexDetailVO.getId())){
                            voList.get(i).setShopName(complexDetailVO.getShopName());
                        }
                    }
                }
            }
        }

    }

    @Override
    @Transactional
    public BbbTradeCommentDTO.IdDTO orderCommentAdditional(BbbTradeCommentBuildDTO.AdditionalDTO dto) {
        TradeComment tradeComment = repository.getById(dto.getId());
        if (ObjectUtils.isEmpty(tradeComment)){
            throw new BusinessException("没有初评");
        }
        tradeComment.setUserAppendComment(dto.getUserAppendComment());
        tradeComment.setAppendCommentTime(LocalDateTime.now());
        //保存评论图片
        List<BbbTradeCommentBuildDTO.AdditionalDTO.CommentImgData> commentImgData1 = dto.getCommentImgData();
        if(ObjectUtils.isNotEmpty(commentImgData1)){
            List<TradeCommentImg> tradeCommentImgs = new ArrayList<>();
            for(BbbTradeCommentBuildDTO.AdditionalDTO.CommentImgData commentImgData : commentImgData1){
                TradeCommentImg tradeCommentImg = new TradeCommentImg();
                tradeCommentImg.setCommentId(tradeComment.getId());
                tradeCommentImg.setCommentImg(commentImgData.getCommentImg());
                tradeCommentImgs.add(tradeCommentImg);
            }
            tradeCommentImgRepository.saveBatch(tradeCommentImgs);
        }
        repository.saveOrUpdate(tradeComment);

        return new BbbTradeCommentDTO.IdDTO(tradeComment.getId());
    }

    @Override
    public void orderCommentAnonymous(BbbTradeCommentDTO.IdDTO dto) {
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
    @Transactional
    public void deleteComment(BbbTradeCommentDTO.IdDTO dto) {
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

    @Override
    public ResponseData<BbbTradeCommentListVO.GoodsGrade> goodsCommentGrade(BbbTradeCommentDTO.GoodsIdDTO dto) {
        if (StringUtils.isBlank(dto.getId())){
            throw new BusinessException("请传入商品ID");
        }
        QueryWrapper<TradeComment> query = MybatisPlusUtil.query();
        query.and(i->i.eq("goods_id",dto.getId()));
        List<TradeComment> list = repository.list(query);
        Integer total=0;
        if (ObjectUtils.isNotEmpty(list)){
            for (TradeComment tradeComment : list) {
                if (ObjectUtils.isNotEmpty(tradeComment.getDescribeGrade())){
                    total+=tradeComment.getDescribeGrade();
                }
            }
        }
        DecimalFormat percent = new DecimalFormat("0.0");
        String score=null;
        if (list.size()!=0){
            double v = (double) total / list.size();
            score = percent.format(v);
        }

        return ResponseData.data(new BbbTradeCommentListVO.GoodsGrade(dto.getId(),score) );
    }

    @Override
    public PageData<BbbTradeCommentListVO.ListVO> myOrderCommentPage(BbbTradeCommentQTO.CommentList qto) {
        QueryWrapper<BbbTradeCommentQTO.CommentList> wrapper = new QueryWrapper<>();
        wrapper.and(i -> i.ne("c.`appeal_state`", TradeAppealStateEnum.通过.getCode()));
        if(ObjectUtils.isNotEmpty(qto.getGoodsId())){
            wrapper.and(i -> i.eq("c.`goods_id`",qto.getGoodsId()));
        }
        if(ObjectUtils.isNotEmpty(qto.getSkuId())){
            wrapper.and(i -> i.eq("c.`sku_id`",qto.getSkuId()));
        }
        if(ObjectUtils.isNotEmpty(qto.getJwtUserId())){
            wrapper.and(i -> i.eq("c.`user_id`",qto.getJwtUserId()));
        }
        wrapper.orderByDesc("c.`cdate`");
        IPage<BbbTradeCommentListVO.ListVO> page = MybatisPlusUtil.pager(qto);
        List<String> shopIds = new ArrayList<>();
        tradeCommentMapper.orderBbbCommentPage(page,wrapper);
        if (ObjectUtils.isEmpty(page) ){
            new PageData<>();
        }
        List<BbbTradeCommentListVO.ListVO> voList = new ArrayList<>();
        for(BbbTradeCommentListVO.ListVO listVO : page.getRecords()){
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

    private void fillCommentListVO(BbbTradeCommentListVO.ListVO listVO) {
        QueryWrapper<TradeCommentImg> tradeCommentImgQueryWrapper = new QueryWrapper<>();
        tradeCommentImgQueryWrapper.eq("comment_id",listVO.getId());

        List<TradeCommentImg> tradeCommentImgs = tradeCommentImgRepository.list(tradeCommentImgQueryWrapper);
        List<BbbTradeCommentListVO.CommentImgVO> commentImgVOS = new ArrayList<>();

        for(TradeCommentImg tradeCommentImg : tradeCommentImgs){
            BbbTradeCommentListVO.CommentImgVO commentImgVO = new BbbTradeCommentListVO.CommentImgVO();
            BeanUtils.copyProperties(tradeCommentImg, commentImgVO);
            commentImgVOS.add(commentImgVO);
        }
        listVO.setCommentImgVOS(commentImgVOS);

    }
}
