package com.gs.lshly.biz.support.trade.service.platadmin.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.gs.lshly.biz.support.trade.entity.Trade;
import com.gs.lshly.biz.support.trade.entity.TradeComment;
import com.gs.lshly.biz.support.trade.entity.TradeCommentImg;
import com.gs.lshly.biz.support.trade.entity.TradeCommentRecord;
import com.gs.lshly.biz.support.trade.mapper.TradeMapper;
import com.gs.lshly.biz.support.trade.mapper.view.TradeInfoView;
import com.gs.lshly.biz.support.trade.repository.ITradeCommentImgRepository;
import com.gs.lshly.biz.support.trade.repository.ITradeCommentRecordRepository;
import com.gs.lshly.biz.support.trade.repository.ITradeCommentRepository;
import com.gs.lshly.biz.support.trade.service.platadmin.ITradeCommentRecordService;
import com.gs.lshly.biz.support.trade.service.platadmin.ITradeCommentService;
import com.gs.lshly.common.enums.TradeAppealStateEnum;
import com.gs.lshly.common.enums.TradeCommentRecordTypeEnum;
import com.gs.lshly.common.enums.TradeGoodsGradeEnum;
import com.gs.lshly.common.enums.TradeQueryConditionEnum;
import com.gs.lshly.common.exception.BusinessException;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.common.struct.common.CommonShopVO;
import com.gs.lshly.common.struct.platadmin.commodity.dto.GoodsInfoDTO;
import com.gs.lshly.common.struct.platadmin.commodity.vo.GoodsInfoVO;
import com.gs.lshly.common.struct.platadmin.merchant.vo.ShopVO;
import com.gs.lshly.common.struct.platadmin.trade.dto.TradeCommentDTO;
import com.gs.lshly.common.struct.platadmin.trade.dto.TradeCommentRecordDTO;
import com.gs.lshly.common.struct.platadmin.trade.qto.TradeCommentQTO;
import com.gs.lshly.common.struct.platadmin.trade.vo.TradeCommentRecordVO;
import com.gs.lshly.common.struct.platadmin.trade.vo.TradeCommentVO;
import com.gs.lshly.common.struct.platadmin.trade.vo.TradeVO;
import com.gs.lshly.common.struct.platadmin.user.dto.UserDTO;
import com.gs.lshly.common.struct.platadmin.user.vo.UserVO;
import com.gs.lshly.middleware.mybatisplus.MybatisPlusUtil;
import com.gs.lshly.middleware.mybatisplus.MybatisPlusUtil;
import com.gs.lshly.middleware.mybatisplus.MybatisPlusUtil;
import com.gs.lshly.rpc.api.common.ICommonShopRpc;
import com.gs.lshly.rpc.api.platadmin.commodity.IGoodsInfoRpc;
import com.gs.lshly.rpc.api.platadmin.merchant.IShopRpc;
import com.gs.lshly.rpc.api.platadmin.user.IUserRpc;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
* <p>
*  服务实现类
* </p>
* @author Starry
* @since 2020-11-17
*/
@Component
public class TradeCommentServiceImpl implements ITradeCommentService {

    @Autowired
    private ITradeCommentRepository repository;
    @Autowired
    private ITradeCommentRecordRepository recordRepository;
    @Autowired
    private ITradeCommentImgRepository commentImgRepository;
    @Autowired
    private ITradeCommentRecordService recordService;
    @Autowired
    private TradeMapper tradeMapper;
    @DubboReference
    private IUserRpc iUserRpc;
    @DubboReference
    private IShopRpc iShopRpc;
    @DubboReference
    private IGoodsInfoRpc iGoodsInfoRpc;
    @DubboReference
    private ICommonShopRpc commonShopRpc;


    @Override
    public PageData<TradeCommentVO.CommentAppealListVO> pageData(TradeCommentQTO.AppealCommentQTO qto) {
        //获取申诉的交易评论
        QueryWrapper<TradeComment> boost = MybatisPlusUtil.query();
        if (ObjectUtils.isNotEmpty(qto.getQueryCondition()) && qto.getQueryCondition().equals(TradeQueryConditionEnum.全部.getCode())){
            boost.ne("appeal_state", TradeAppealStateEnum.未申诉.getCode());
        }
        if (ObjectUtils.isNotEmpty(qto.getQueryCondition()) && qto.getQueryCondition().equals(TradeQueryConditionEnum.待处理.getCode())){
            boost.eq("appeal_state", TradeAppealStateEnum.申诉.getCode());
        }
        if (ObjectUtils.isNotEmpty(qto.getQueryCondition()) && qto.getQueryCondition().equals(TradeQueryConditionEnum.已处理.getCode())){
            boost.in("appeal_state", TradeAppealStateEnum.通过.getCode(),TradeAppealStateEnum.驳回.getCode(),TradeAppealStateEnum.关闭.getCode());
        }
        boost.orderByDesc("cdate","udate");
        IPage<TradeComment> page = MybatisPlusUtil.pager(qto);
        IPage<TradeComment> commentIPage = repository.page(page,boost);

        if (ObjectUtils.isEmpty(commentIPage) || ObjectUtils.isEmpty(commentIPage.getRecords())){
            return new PageData<>();
        }

        List<TradeCommentVO.CommentAppealListVO> commentAppealListVOS = new ArrayList<>();
        for (TradeComment comment : commentIPage.getRecords()) {
            TradeCommentVO.CommentAppealListVO commentAppealListVO = new TradeCommentVO.CommentAppealListVO();
            BeanUtils.copyProperties(comment,commentAppealListVO);

            //获取评论对应申诉记录
            QueryWrapper<TradeCommentRecord> wrapperBoost = MybatisPlusUtil.query();
            wrapperBoost.ne("appeal_state",TradeAppealStateEnum.未申诉.getCode());
            wrapperBoost.eq("comment_id",comment.getId());
            wrapperBoost.orderByDesc("cdate");
            List<TradeCommentRecord> commentRecords = recordRepository.list(wrapperBoost);

            if (ObjectUtils.isEmpty(commentRecords)){
                throw new BusinessException("申诉记录数据异常");
            }

            //填充驳回理由
            commentAppealListVO.setRefuseIdea(commentRecords.get(0).getContent());

            //填充申诉时间
            commentAppealListVO.setAppealTime(commentRecords.get(0).getCdate());

            //填充最后修改时间
            commentAppealListVO.setAppealTime(commentRecords.get(0).getUdate());

            commentAppealListVOS.add(commentAppealListVO);

        }
        return new PageData<>(commentAppealListVOS,qto.getPageNum(),qto.getPageSize(),commentIPage.getTotal());
    }

    @Override
    public TradeCommentVO.CommentAppealDetailVO detailTradeComment(TradeCommentDTO.IdDTO dto) {
        //获取订单交易初评信息
        QueryWrapper<TradeComment> boost = MybatisPlusUtil.query();
        boost.eq("id",dto.getId());
        TradeComment comment = repository.getOne(boost);
        if (ObjectUtils.isEmpty(comment)){
            throw new BusinessException("用户评价数据异常！");
        }
        TradeCommentVO.CommentAppealDetailVO commentAppealDetailVO = new TradeCommentVO.CommentAppealDetailVO();

        //评价信息的商品评分
        if (comment.getDescribeGrade() >= 0 && comment.getDescribeGrade() <= 2){
            commentAppealDetailVO.setGoodsGrade(TradeGoodsGradeEnum.差评.getRemark());
        }else if (comment.getDescribeGrade() >= 3 && comment.getDescribeGrade() <= 4){
            commentAppealDetailVO.setGoodsGrade(TradeGoodsGradeEnum.中评.getRemark());
        }else if (comment.getDescribeGrade() == 5 ){
            commentAppealDetailVO.setGoodsGrade(TradeGoodsGradeEnum.好评.getRemark());
        }
        if (ObjectUtils.isNotEmpty(comment.getGoodsGrade())){
            commentAppealDetailVO.setGoodsGrade(TradeGoodsGradeEnum.get(comment.getGoodsGrade()));
        }
        //评价信息的评价内容
        commentAppealDetailVO.setUserComment(StringUtils.isEmpty(comment.getUserComment())?"":comment.getUserComment());

        //评价信息的评价时间
        commentAppealDetailVO.setCommentTime(comment.getCdate());

        TradeInfoView tradeInfoView = tradeMapper.getTradeInfo(comment.getTradeId());
        if (ObjectUtils.isEmpty(tradeInfoView)){
            throw new BusinessException("订单不存在");
        }
        TradeVO.TradeInfoVO tradeInfoVO = new TradeVO.TradeInfoVO();
        BeanUtils.copyProperties(tradeInfoView,tradeInfoVO);
        CommonShopVO.SimpleVO simpleVO = commonShopRpc.shopDetails(tradeInfoView.getShopId());
        if (ObjectUtils.isEmpty(simpleVO)){
            throw new BusinessException("店铺不存在！！");
        }
        tradeInfoVO.setShopName(ObjectUtils.isEmpty(simpleVO.getShopName())?"":simpleVO.getShopName());
        commentAppealDetailVO.setTradeInfoVO(tradeInfoVO);

        //申诉进度信息的申诉类型
        commentAppealDetailVO.setAppealType(comment.getAppealType());


        //申诉基本信息
        TradeCommentRecordDTO.CommentIdDTO commentIdDTO = new TradeCommentRecordDTO.CommentIdDTO(dto.getId());
        TradeCommentRecordVO.AppealRecordDetailVO recordDetailVO = recordService.getAppealDetailInfo(commentIdDTO);

        commentAppealDetailVO.setAppealRecordDetailVO(recordDetailVO);
        return commentAppealDetailVO;
    }

    @Override
    public void checkCommentAppeal(TradeCommentDTO.CheckCommentAppealDTO dto) {
        if (ObjectUtils.isEmpty(dto)){
            throw new BusinessException("参数不能为空！");
        }

        //添加平台对商家评论申诉的记录
        TradeCommentRecord record = new TradeCommentRecord();
        record.setRecordType(TradeCommentRecordTypeEnum.平台.getCode());
        record.setPlatformUserId(dto.getJwtUserId());
        record.setContent(StringUtils.isEmpty(dto.getRefuseIdea())?"":dto.getRefuseIdea());
        record.setCommentId(dto.getCommentId());
        record.setAppealState(dto.getCheckState());

        recordRepository.save(record);

        //修改评论申诉状态
        UpdateWrapper<TradeComment> boost = MybatisPlusUtil.update();
        boost.eq("id",dto.getCommentId());
        TradeComment comment = new TradeComment();
        comment.setAppealState(dto.getCheckState());

        repository.update(comment,boost);
    }

    @Override
    public PageData<TradeCommentVO.CommentListVO> commentList(TradeCommentQTO.CommentQTO qto) {
        IPage<TradeComment> pager = MybatisPlusUtil.pager(qto);
        QueryWrapper<TradeComment> query = MybatisPlusUtil.query();
        if (ObjectUtils.isNotEmpty(qto.getQueryCondition())){
            if (qto.getQueryCondition()==20){
                //好评
                query.or(i->i.eq("describe_grade",5).or().eq("goods_grade",TradeGoodsGradeEnum.好评.getCode()));

            }
            if (qto.getQueryCondition()==30){
                //中评
                query.or(i->i.eq("describe_grade",4).or().eq("describe_grade",3).or().eq("goods_grade",TradeGoodsGradeEnum.中评.getCode()));
            }
            if (qto.getQueryCondition()==40){
                //差评
                query.or(i->i.eq("describe_grade",2).or().eq("describe_grade",1).or().eq("describe_grade",0).or().eq("goods_grade",TradeGoodsGradeEnum.差评.getCode()));
            }
        }
        if (ObjectUtils.isNotEmpty(qto.getTradeId())){
            query.and(i->i.like("trade_id",qto.getTradeId()));
        }
        IPage<TradeComment> page = repository.page(pager, query);
        if (ObjectUtils.isEmpty(pager) || ObjectUtils.isEmpty(pager.getRecords())){
            return  new PageData<>();
        }
        List<String> shopIds = new ArrayList<>();
        List<String> goodsIds = new ArrayList<>();
        List<TradeCommentVO.CommentListVO> ListVO=page.getRecords().parallelStream().map(i->{
            TradeCommentVO.CommentListVO commentListVO = new TradeCommentVO.CommentListVO();
            BeanUtils.copyProperties(i,commentListVO);
            commentListVO.setReplyTime(commentListVO.getUdate());
            Trade trade = tradeMapper.selectById(i.getTradeId());
            if (ObjectUtils.isNotEmpty(trade)){
                commentListVO.setTradeCode(trade.getTradeCode());
            }
            UserVO.MiniVO mini=null;
            if (StringUtils.isNotBlank(i.getUserId())){
                mini = iUserRpc.mini(new UserDTO.IdDTO(i.getUserId()));
            }
            if (ObjectUtils.isNotEmpty(mini)){
                commentListVO.setUserName(mini.getUserName());
            }
            shopIds.add(i.getShopId());
            goodsIds.add(i.getGoodsId());
            return commentListVO;

        }).collect(Collectors.toList());
        List<ShopVO.InnerSimpleVO> innerSimpleVOS = iShopRpc.innerlistShopIdName(new BaseDTO(), shopIds);
        List<GoodsInfoVO.ListVO> listVOS = iGoodsInfoRpc.innerServiceSpuGoodsInfo(new GoodsInfoDTO.IdsInnerServiceDTO(goodsIds, null, 10));
        //设置店铺名字信息
        if (ObjectUtils.isNotEmpty(innerSimpleVOS)){
            setShopName(ListVO,innerSimpleVOS);
        }
        //设置商品title
        if (ObjectUtils.isNotEmpty(listVOS)){
            setGoodsTile(ListVO,listVOS);
        }
        return new PageData<>(ListVO,qto.getPageNum(),qto.getPageSize(),page.getTotal() );
    }

    private void setGoodsTile(List<TradeCommentVO.CommentListVO> listVO, List<GoodsInfoVO.ListVO> listVOS) {
        for (GoodsInfoVO.ListVO listVO1:listVOS){
            for (int i = 0; i < listVO.size(); i++) {
                if (listVO1.getId().equals(listVO.get(i).getGoodsId())){
                    listVO.get(i).setGoodsTitle(listVO1.getGoodsTitle());
                }
            }

        }
    }

    private void setShopName(List<TradeCommentVO.CommentListVO> listVO, List<ShopVO.InnerSimpleVO> innerSimpleVOS) {
        for (ShopVO.InnerSimpleVO innerSimpleVO:innerSimpleVOS){
            for (int i = 0; i < listVO.size(); i++) {
                if (listVO.get(i).getShopId().equals(innerSimpleVO.getId())){
                    listVO.get(i).setShopName(innerSimpleVO.getShopName());
                }
            }
        }
    }

    @Override
    public TradeCommentVO.CommentDetailVO commentDetail(TradeCommentQTO.CommentDetailQTO qto) {
        if(StringUtils.isBlank(qto.getId())){
            throw new BusinessException("传入ID");
        }
        TradeComment tradeComment = repository.getById(qto.getId());
        TradeCommentVO.CommentDetailVO commentDetailVO = new TradeCommentVO.CommentDetailVO();
        if (ObjectUtils.isNotEmpty(tradeComment)){

            BeanUtils.copyProperties(tradeComment,commentDetailVO);
            switch (tradeComment.getDescribeGrade()){
                case 1:
                case 2:commentDetailVO.setDescribeGrade(TradeGoodsGradeEnum.差评.getCode());break;
                case 3:
                case 4:commentDetailVO.setDescribeGrade(TradeGoodsGradeEnum.中评.getCode());break;
                case 5:commentDetailVO.setDescribeGrade(TradeGoodsGradeEnum.好评.getCode());break;
            }
            switch (tradeComment.getGoodsGrade()){
                case 10:commentDetailVO.setGoodsGrade(TradeGoodsGradeEnum.差评.getCode());break;
                case 20:commentDetailVO.setGoodsGrade(TradeGoodsGradeEnum.中评.getCode());break;
                case 30:commentDetailVO.setGoodsGrade(TradeGoodsGradeEnum.好评.getCode());break;
            }
            //初评晒图
            QueryWrapper<TradeCommentImg> query = MybatisPlusUtil.query();
            query.and(i->i.eq("comment_id",tradeComment.getId()));
            query.and(i->i.eq("comment_img_belong",10));
            List<TradeCommentImg> list = commentImgRepository.list(query);
            if (ObjectUtils.isNotEmpty(list)){
                for (TradeCommentImg tradeCommentImg : list) {
                    ArrayList<String> img = new ArrayList<>();
                    img.add(tradeCommentImg.getCommentImg());
                    commentDetailVO.setCommentImage(img);
                }

            }
            if (tradeComment.getUserAppendComment()!=null){
                commentDetailVO.setIsAddComment(10);
                //追评晒图
                QueryWrapper<TradeCommentImg> query1 = MybatisPlusUtil.query();
                query1.and(i->i.eq("comment_id",tradeComment.getId()));
                query1.and(i->i.eq("comment_img_belong",20));
                List<TradeCommentImg> list1 = commentImgRepository.list(query1);
                if (ObjectUtils.isNotEmpty(list1)){
                    for (TradeCommentImg tradeCommentImg : list1) {
                        ArrayList<String> img = new ArrayList<>();
                        img.add(tradeCommentImg.getCommentImg());
                        commentDetailVO.setAppendImage(img);
                    }
                }
            }else {
                commentDetailVO.setIsAddComment(20);
            }

        }
        return commentDetailVO;
    }

    @Override
    @Transactional
    public void delete(TradeCommentDTO.IdsDTO dto) {
       if (ObjectUtils.isEmpty(dto.getIds())){
           throw new BusinessException("请传入评论ID");
       }
        List<TradeComment> tradeComments = repository.listByIds(dto.getIds());
        if (ObjectUtils.isEmpty(tradeComments)){
           throw new BusinessException("没有这条评论");
       }
       repository.removeByIds(dto.getIds());
       QueryWrapper<TradeCommentImg> query = MybatisPlusUtil.query();
       QueryWrapper<TradeCommentRecord> queryWrapper = MybatisPlusUtil.query();
       queryWrapper.and(i->i.in("comment_id",dto.getIds()));
       query.and(i->i.in("comment_id",dto.getIds()));
       recordRepository.remove(queryWrapper);
       commentImgRepository.remove(query);



    }

}
