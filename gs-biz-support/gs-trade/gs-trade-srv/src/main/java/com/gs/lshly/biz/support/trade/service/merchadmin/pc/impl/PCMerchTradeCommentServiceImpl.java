package com.gs.lshly.biz.support.trade.service.merchadmin.pc.impl;

import com.aliyuncs.utils.StringUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.gs.lshly.biz.support.trade.entity.*;
import com.gs.lshly.biz.support.trade.mapper.TradeCommentMapper;
import com.gs.lshly.biz.support.trade.mapper.view.TradeAppealCommentView;
import com.gs.lshly.biz.support.trade.repository.ITradeCommentImgRepository;
import com.gs.lshly.biz.support.trade.repository.ITradeCommentRecordRepository;
import com.gs.lshly.biz.support.trade.repository.ITradeCommentRepository;
import com.gs.lshly.biz.support.trade.repository.ITradeGoodsRepository;
import com.gs.lshly.biz.support.trade.service.merchadmin.pc.IPCMerchTradeCommentImgService;
import com.gs.lshly.biz.support.trade.service.merchadmin.pc.IPCMerchTradeCommentRecordService;
import com.gs.lshly.biz.support.trade.service.merchadmin.pc.IPCMerchTradeCommentService;
import com.gs.lshly.common.enums.TradeAppealStateEnum;
import com.gs.lshly.common.enums.TradeCommentImgBelongEnum;
import com.gs.lshly.common.enums.TradeCommentRecordTypeEnum;
import com.gs.lshly.common.enums.TradeGoodsGradeEnum;
import com.gs.lshly.common.exception.BusinessException;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.merchadmin.pc.trade.dto.PCMerchTradeCommentDTO;
import com.gs.lshly.common.struct.merchadmin.pc.trade.dto.PCMerchTradeCommentImgDTO;
import com.gs.lshly.common.struct.merchadmin.pc.trade.dto.PCMerchTradeCommentRecordDTO;
import com.gs.lshly.common.struct.merchadmin.pc.trade.qto.PCMerchTradeCommentQTO;
import com.gs.lshly.common.struct.merchadmin.pc.trade.vo.PCMerchTradeCommentRecordVO;
import com.gs.lshly.common.struct.merchadmin.pc.trade.vo.PCMerchTradeCommentVO;
import com.gs.lshly.common.struct.platadmin.trade.vo.TradeCommentRecordVO;
import com.gs.lshly.middleware.mybatisplus.MybatisPlusUtil;
import com.gs.lshly.middleware.mybatisplus.MybatisPlusUtil;
import io.swagger.annotations.ApiModelProperty;
import net.bytebuddy.implementation.bytecode.Throw;
import org.omg.CORBA.OBJ_ADAPTER;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 *  服务实现类
 * </p>
 * @author Starry
 * @since 2020-11-16
 */
@Component
public class PCMerchTradeCommentServiceImpl implements IPCMerchTradeCommentService {

    @Autowired
    private ITradeCommentRepository repository;

    @Autowired
    private ITradeCommentRecordRepository recordRepository;

    @Autowired
    private ITradeCommentImgRepository commentImgRepository;

    @Autowired
    private IPCMerchTradeCommentRecordService commentRecordService;

    @Autowired
    private IPCMerchTradeCommentImgService commentImgService;

    @Autowired
    private TradeCommentMapper commentMapper;

    @Autowired
    private ITradeGoodsRepository tradeGoodsRepository;


    @Override
    public PageData<PCMerchTradeCommentVO.CommentAppealListVO> pageData(PCMerchTradeCommentQTO.AppealCommentQTO qto) {
        QueryWrapper<TradeAppealCommentView> boost = MybatisPlusUtil.query();
        boost.ne("tc.appeal_state",TradeAppealStateEnum.未申诉.getCode());
        if (ObjectUtils.isNotEmpty(qto.getAppealState()) && qto.getAppealState().intValue() == 10){
            boost.ne("tc.appeal_state",TradeAppealStateEnum.未申诉.getCode());
        }
        if (ObjectUtils.isNotEmpty(qto.getAppealState()) && qto.getAppealState().intValue() != 10){
            boost.eq("tc.appeal_state",qto.getAppealState());
        }
        if (ObjectUtils.isNotEmpty(qto.getProgress()) && qto.getProgress().equals(1)){
            boost.eq("tc.appeal_count",1);
        }
        if (ObjectUtils.isNotEmpty(qto.getProgress()) && qto.getProgress().equals(2)){
            boost.eq("tc.appeal_count",2);
        }
        if (ObjectUtils.isNotEmpty(qto.getGoodsName())){
            boost.like("tg.goods_name",qto.getGoodsName());
        }
        boost.eq("tc.shop_id",qto.getJwtShopId());

        IPage<TradeAppealCommentView> page = MybatisPlusUtil.pager(qto);
        IPage<TradeAppealCommentView> commentViewIPage = commentMapper.pageCommentAppeal(page,boost);
        if (ObjectUtils.isEmpty(commentViewIPage.getRecords())){
            return new PageData<>();
        }
        List<PCMerchTradeCommentVO.CommentAppealListVO> appealListVOS = new ArrayList<>();
        for(TradeAppealCommentView commentView : commentViewIPage.getRecords()){
            PCMerchTradeCommentVO.CommentAppealListVO commentAppealListVO= new PCMerchTradeCommentVO.CommentAppealListVO();
            BeanUtils.copyProperties(commentView,commentAppealListVO);

            //获取最新的申诉内容
            QueryWrapper<TradeCommentRecord> boost1 = MybatisPlusUtil.query();
            boost1.eq("comment_id",commentView.getId());
            boost1.eq("record_type",TradeCommentRecordTypeEnum.店铺.getCode());
            boost1.eq("appeal_state",TradeAppealStateEnum.申诉.getCode());
            boost.orderByDesc("cdate","id");

            List<TradeCommentRecord> commentRecords = recordRepository.list(boost1);
            if (ObjectUtils.isNotEmpty(commentRecords)){
                PCMerchTradeCommentRecordVO.AppealContentVO contentVO = new PCMerchTradeCommentRecordVO.AppealContentVO();
                TradeCommentRecord record = commentRecords.get(0);
                contentVO.setAppealContent(StringUtils.isEmpty(record.getContent())?"":record.getContent());
                contentVO.setAppealTime(record.getCdate());

                //根据评价记录id获取图片凭证
                QueryWrapper<TradeCommentImg> wrapperBoost = MybatisPlusUtil.query();
                wrapperBoost.eq("comment_id",commentView.getId());
                wrapperBoost.eq("comment_record_id",record.getId());
                List<TradeCommentImg> commentImgs = commentImgRepository.list(wrapperBoost);
                if (ObjectUtils.isNotEmpty(commentImgs)){
                    List<String> imgList = commentImgs.stream().map(TradeCommentImg::getCommentImg)
                            .collect(Collectors.toList());
                    contentVO.setImgVos(imgList);
                }
                commentAppealListVO.setAppealContentVO(contentVO);
            }
            appealListVOS.add(commentAppealListVO);
        }
        return new PageData<>(appealListVOS,qto.getPageNum(),qto.getPageSize(),appealListVOS.size());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void appealTradeComment(PCMerchTradeCommentDTO.AppealCommentETO dto) {
        if (dto == null){
            throw new BusinessException("参数为空异常！！");
        }
        //修改交易评论信息
        TradeComment tradeComment = repository.getById(dto.getCommentId());
        if (ObjectUtils.isEmpty(tradeComment)){
            throw new BusinessException("查询数据异常！！");
        }
        if (ObjectUtils.isNotEmpty(tradeComment.getAppealCount()) && tradeComment.getAppealCount().intValue() == 2){
            throw new BusinessException("该评论已申诉了两次不可在申诉了！！");
        }
        if (ObjectUtils.isNotEmpty(tradeComment.getAppealCount()) && tradeComment.getAppealCount().intValue() == 1 && ObjectUtils.isEmpty(dto.getImageEto())){
            throw new BusinessException("二次申诉必须上传申诉凭证！！！");
        }
        UpdateWrapper<TradeComment> boost = MybatisPlusUtil.update();
        boost.eq("id",dto.getCommentId());
        TradeComment comment = new TradeComment();
        comment.setAppealState(TradeAppealStateEnum.申诉.getCode());
        comment.setAppealType(dto.getAppealType());
        if (ObjectUtils.isEmpty(tradeComment.getAppealCount())){
            int appealCount = 0;
            comment.setAppealCount(++appealCount);
        }else {
            int appealCount = tradeComment.getAppealCount() +1;
            comment.setAppealCount(appealCount);
        }
        repository.update(comment,boost);

        //插入申诉记录信息
        PCMerchTradeCommentRecordDTO.ETO commentRecord = new PCMerchTradeCommentRecordDTO.ETO();
        commentRecord.setAppealState(TradeAppealStateEnum.申诉.getCode());
        commentRecord.setCommentId(dto.getCommentId());
        commentRecord.setContent(dto.getContent());
        commentRecord.setShopId(dto.getJwtShopId());
        commentRecord.setMerchantId(dto.getJwtMerchantId());
        commentRecord.setRecordType(TradeCommentRecordTypeEnum.店铺.getCode());
        commentRecordService.addTradeCommentRecord(commentRecord);

        //若上传了图片凭证则插入申诉图片凭证数据
        if (ObjectUtils.isNotEmpty(dto.getImageEto())){
            List<TradeCommentImg> commentImgs = new ArrayList<>();
            for (String img : dto.getImageEto()){
                TradeCommentImg commentImg = new TradeCommentImg();
                commentImg.setCommentId(dto.getCommentId());
                commentImg.setCommentRecordId(commentRecord.getId());
                if (comment.getAppealCount().intValue() == 1){
                    commentImg.setCommentImgBelong(TradeCommentImgBelongEnum.初次申诉.getCode());
                }
                if (comment.getAppealCount().intValue() == 2){
                    commentImg.setCommentImgBelong(TradeCommentImgBelongEnum.再次申诉.getCode());
                }
                commentImg.setCommentImg(img);
                commentImgs.add(commentImg);
            }
            commentImgRepository.saveBatch(commentImgs);
        }

    }

    @Override
    public PCMerchTradeCommentVO.AppealDetailVO getAppealDetailVO(PCMerchTradeCommentDTO.IdDTO dto) {
        //获取评价信息
        QueryWrapper<TradeComment> boost = MybatisPlusUtil.query();
        boost.eq("id",dto.getId());
        TradeComment comment = repository.getOne(boost);
        if (ObjectUtils.isEmpty(comment)){
            throw new BusinessException("数据异常！");
        }
        PCMerchTradeCommentVO.AppealDetailVO detailVO = new PCMerchTradeCommentVO.AppealDetailVO();
        detailVO.setCommentContent(StringUtils.isEmpty(comment.getUserComment())?"":comment.getUserComment());
        detailVO.setCommentTime(comment.getCdate());
        if (comment.getDescribeGrade().intValue() >0 && comment.getDescribeGrade().intValue() <=2){
            detailVO.setCommentGrade(TradeGoodsGradeEnum.差评.getRemark());
        }
        if (comment.getDescribeGrade().intValue() >2 && comment.getDescribeGrade().intValue() <=4){
            detailVO.setCommentGrade(TradeGoodsGradeEnum.中评.getRemark());
        }
        if (comment.getDescribeGrade().intValue() == 5){
            detailVO.setCommentGrade(TradeGoodsGradeEnum.好评.getRemark());
        }
        detailVO.setAppealState(comment.getAppealState());
        detailVO.setGoodsName(comment.getGoodsName());

        //获取申诉理由
        QueryWrapper<TradeCommentRecord> boost1 = MybatisPlusUtil.query();
        boost1.eq("comment_id",dto.getId());
        boost1.eq("record_type",TradeCommentRecordTypeEnum.店铺.getCode());
        boost1.orderByDesc("cdate","id");
        List<TradeCommentRecord> commentRecords = recordRepository.list(boost1);
        if (ObjectUtils.isEmpty(commentRecords)){
            throw new BusinessException("申诉记录异常");
        }
        TradeCommentRecord commentRecord = commentRecords.get(0);
        detailVO.setAppealIdea(StringUtils.isEmpty(commentRecord.getContent())?"":commentRecord.getContent());

        //获取交易商品信息
        QueryWrapper<TradeGoods> wrapperBoost = MybatisPlusUtil.query();
        wrapperBoost.eq("id",comment.getTradeGoodsId());
        TradeGoods goods = tradeGoodsRepository.getOne(wrapperBoost);
        if (ObjectUtils.isEmpty(goods)){
            throw new BusinessException("交易商品数据异常！");
        }
        detailVO.setGoodsImage(StringUtils.isEmpty(goods.getSkuImg())?"":goods.getSkuImg());
        detailVO.setPrice(ObjectUtils.isEmpty(goods.getPayAmount())? BigDecimal.ZERO:goods.getPayAmount());
        return detailVO;
    }




    @Override
    public void addTradeComment(PCMerchTradeCommentDTO.ETO eto) {
        TradeComment tradeComment = new TradeComment();
        BeanUtils.copyProperties(eto, tradeComment);
        repository.save(tradeComment);
    }


    @Override
    public void deleteTradeComment(PCMerchTradeCommentDTO.IdDTO dto) {
        repository.removeById(dto.getId());
    }
    @Override
    public void editTradeComment(PCMerchTradeCommentDTO.ETO eto) {
        TradeComment tradeComment = new TradeComment();
        BeanUtils.copyProperties(eto, tradeComment);
        repository.updateById(tradeComment);
    }

    @Override
    public PCMerchTradeCommentVO.DetailVO detailTradeComment(PCMerchTradeCommentDTO.IdDTO dto) {
        TradeComment tradeComment = repository.getById(dto.getId());
        PCMerchTradeCommentVO.DetailVO detailVo = new PCMerchTradeCommentVO.DetailVO();
        if(ObjectUtils.isEmpty(tradeComment)){
            throw new BusinessException("没有数据");
        }
        BeanUtils.copyProperties(tradeComment, detailVo);
        return detailVo;
    }

    @Override
    public PageData<PCMerchTradeCommentVO.CommentListListVO> commentList(PCMerchTradeCommentQTO.QTO qto) {
        IPage<PCMerchTradeCommentVO.CommentListListVO> pager = MybatisPlusUtil.pager(qto);
        QueryWrapper<PCMerchTradeCommentQTO.QTO> query = MybatisPlusUtil.query();
        if (org.apache.commons.lang3.StringUtils.isNotBlank(qto.getJwtShopId())){
            query.and(i->i.eq("tc.`shop_id`",qto.getJwtShopId()));
        }
        if (ObjectUtils.isNotEmpty(qto.getState())){
           // 10=有晒图 20=有回复 30=有内容
            switch (qto.getState()){
                case 20:query.ne("tc.`shop_reply`",null);break;
                case 30:query.ne("tc.`user_comment`",null);break;

            }
        }
        if (ObjectUtils.isNotEmpty(qto.getEndTime())||ObjectUtils.isNotEmpty(qto.getStartTime())){
            query.and(i->i.ge("tc.`cdate`",qto.getStartTime()).le("tc.`cdate`",qto.getEndTime()));
        }
        IPage<PCMerchTradeCommentVO.CommentListListVO> commentListListVOIPage = repository.selectListPage(pager, query);
        List<PCMerchTradeCommentVO.CommentListListVO> listVOS=new ArrayList<>();
        if (ObjectUtils.isEmpty(pager)||ObjectUtils.isEmpty(commentListListVOIPage)){
            return new PageData<>();
        }
        for (PCMerchTradeCommentVO.CommentListListVO listListVO:commentListListVOIPage.getRecords()){
            switch (listListVO.getDescribeGrade()){
                case 1:
                case 2:listListVO.setScore(TradeGoodsGradeEnum.差评.getCode());break;
                case 3:
                case 4:listListVO.setScore(TradeGoodsGradeEnum.中评.getCode());break;
                case 5:listListVO.setScore(TradeGoodsGradeEnum.好评.getCode());break;
            }
            switch (listListVO.getGoodsGrade()){
                case 10:listListVO.setScore(TradeGoodsGradeEnum.差评.getCode());break;
                case 20:listListVO.setScore(TradeGoodsGradeEnum.中评.getCode());break;
                case 30:listListVO.setScore(TradeGoodsGradeEnum.好评.getCode());break;
            }
            listListVO.setTotalAmount((ObjectUtils.isNotEmpty(listListVO.getSalePrice()) && ObjectUtils.isNotEmpty(listListVO.getQuantity()))?listListVO.getSalePrice().multiply(new BigDecimal(listListVO.getQuantity())):new BigDecimal(0));
            listListVO.setUserCommentImg(setImg(listListVO));
            listListVO.setUserAppendCommentImg(setAppendImg(listListVO));
            listVOS.add(listListVO);
            //设置图片
            //初评晒图
            QueryWrapper<TradeCommentImg> query1 = MybatisPlusUtil.query();
            query1.and(i->i.eq("comment_id",listListVO.getId()));
            query1.and(i->i.eq("comment_img_belong",10));
            List<TradeCommentImg> list = commentImgRepository.list(query1);
            if (ObjectUtils.isNotEmpty(list)){
                for (TradeCommentImg tradeCommentImg : list) {
                    ArrayList<String> img = new ArrayList<>();
                    img.add(tradeCommentImg.getCommentImg());
                    listListVO.setUserCommentImg(img);
                }

            }
            QueryWrapper<TradeCommentImg> query2 = MybatisPlusUtil.query();
            query2.and(i->i.eq("comment_id",listListVO.getId()));
            query2.and(i->i.eq("comment_img_belong",20));
            List<TradeCommentImg> list1 = commentImgRepository.list(query2);
            if (ObjectUtils.isNotEmpty(list1)){
                for (TradeCommentImg tradeCommentImg : list1) {
                    ArrayList<String> img = new ArrayList<>();
                    img.add(tradeCommentImg.getCommentImg());
                    listListVO.setUserAppendCommentImg(img);
                }
            }

        }
        return new PageData<>(listVOS,qto.getPageNum(),qto.getPageSize(),commentListListVOIPage.getTotal());
    }
    @Override
    public PCMerchTradeCommentVO.CommentDetailVO commentDetail(PCMerchTradeCommentDTO.IdDTO dto) {
        if (ObjectUtils.isEmpty(dto.getId())){
            throw new BusinessException("请传入id值");
        }
        TradeComment tradeComment = repository.getById(dto.getId());
        PCMerchTradeCommentVO.CommentDetailVO commentDetailVO = new PCMerchTradeCommentVO.CommentDetailVO();
        if (ObjectUtils.isNotEmpty(tradeComment)){
            BeanUtils.copyProperties(tradeComment,commentDetailVO);
            switch (tradeComment.getDescribeGrade()){
                case 1:
                case 2:commentDetailVO.setScore(TradeGoodsGradeEnum.差评.getCode());break;
                case 3:
                case 4:commentDetailVO.setScore(TradeGoodsGradeEnum.中评.getCode());break;
                case 5:commentDetailVO.setScore(TradeGoodsGradeEnum.好评.getCode());break;
            }
            switch (tradeComment.getGoodsGrade()){
                case 10:commentDetailVO.setScore(TradeGoodsGradeEnum.差评.getCode());break;
                case 20:commentDetailVO.setScore(TradeGoodsGradeEnum.中评.getCode());break;
                case 30:commentDetailVO.setScore(TradeGoodsGradeEnum.好评.getCode());break;
            }
            TradeGoods tradeGoods = tradeGoodsRepository.getById(tradeComment.getTradeGoodsId());
            if (ObjectUtils.isNotEmpty(tradeGoods)){
                commentDetailVO.setGoodsName(tradeGoods.getGoodsName());
                commentDetailVO.setPrice(tradeGoods.getPayAmount());
                commentDetailVO.setGoodsImage(StringUtils.isEmpty(tradeGoods.getSkuImg())?"":tradeGoods.getSkuImg());
            }
            //设置图片
            //初评晒图
            QueryWrapper<TradeCommentImg> query1 = MybatisPlusUtil.query();
            query1.and(i->i.eq("comment_id",tradeComment.getId()));
            query1.and(i->i.eq("comment_img_belong",10));
            List<TradeCommentImg> list = commentImgRepository.list(query1);
            if (ObjectUtils.isNotEmpty(list)){
                for (TradeCommentImg tradeCommentImg : list) {
                    ArrayList<String> img = new ArrayList<>();
                    img.add(tradeCommentImg.getCommentImg());
                    commentDetailVO.setCommentImage(img);
                }

            }
            QueryWrapper<TradeCommentImg> query2 = MybatisPlusUtil.query();
            query2.and(i->i.eq("comment_id",tradeComment.getId()));
            query2.and(i->i.eq("comment_img_belong",20));
            List<TradeCommentImg> list1 = commentImgRepository.list(query2);
            if (ObjectUtils.isNotEmpty(list1)){
                for (TradeCommentImg tradeCommentImg : list1) {
                    ArrayList<String> img = new ArrayList<>();
                    img.add(tradeCommentImg.getCommentImg());
                    commentDetailVO.setAppendImage(img);
                }
            }

        }
        return commentDetailVO;
    }

    @Override
    public PCMerchTradeCommentVO.CommentOverViewVO commentOverView(PCMerchTradeCommentDTO.CommentListETO dto) {

        QueryWrapper<TradeComment> query = MybatisPlusUtil.query();
        if (org.apache.commons.lang3.StringUtils.isNotBlank(dto.getJwtShopId())){
            query.and(i->i.eq("shop_id",dto.getJwtShopId()));
        }
        List<TradeComment> list = repository.list(query);
        PCMerchTradeCommentVO.CommentOverViewVO commentOverViewVO = new PCMerchTradeCommentVO.CommentOverViewVO();
        Integer describeGrade = 0;
        Integer deliveryGrade = 0;
        Integer serviceGrade = 0;
        if (ObjectUtils.isNotEmpty(list)){
            for (TradeComment tradeComment:list){
                describeGrade+=tradeComment.getDescribeGrade();
                deliveryGrade+=tradeComment.getDeliveryGrade();
                serviceGrade+=tradeComment.getServiceGrade();
            }
        }
        String descri = setGrade(describeGrade, list.size());
        if (org.apache.commons.lang3.StringUtils.isNotBlank(descri)){
            commentOverViewVO.setDescribeGrade(descri);
        }
        String delivery = setGrade(deliveryGrade, list.size());
        if (org.apache.commons.lang3.StringUtils.isNotBlank(delivery)){
            commentOverViewVO.setDeliveryGrade(delivery);
        }
        String service = setGrade(serviceGrade, list.size());
        if (org.apache.commons.lang3.StringUtils.isNotBlank(service)){
            commentOverViewVO.setServiceGrade(service);
        }
        // TODO 缺少数据
        commentOverViewVO.setDeliveryGradeLv("100%");
        commentOverViewVO.setDescribeGradeLv("100%");
        commentOverViewVO.setServiceGradeLv("100%");

        List<PCMerchTradeCommentVO.Grade> grades = new ArrayList<>();
        //好评
        setGrade(grades,TradeGoodsGradeEnum.好评.getCode(),GetWeekQuery(dto),GetMonths(dto,1), GetMonths(dto,6), GetSixMonthsAgo(dto));
        //中评
        setGrade(grades,TradeGoodsGradeEnum.中评.getCode(),GetWeekQuery(dto),GetMonths(dto,1), GetMonths(dto,6), GetSixMonthsAgo(dto));
        //差评
        setGrade(grades,TradeGoodsGradeEnum.差评.getCode(),GetWeekQuery(dto),GetMonths(dto,1), GetMonths(dto,6), GetSixMonthsAgo(dto));
        //总计
        setTotal(grades);
        commentOverViewVO.setGrades(grades);
        return commentOverViewVO;
    }

    @Override
    @Transactional
    public void commentReply(PCMerchTradeCommentDTO.CommentReplyDTO dto) {
        if (org.apache.commons.lang3.StringUtils.isBlank(dto.getId())){
            throw new BusinessException("请传入评论ID");
        }
        TradeComment tradeComment = repository.getById(dto.getId());
        if (ObjectUtils.isNotEmpty(tradeComment)){
            if (tradeComment.getShopReply()==null){
                //可以回复
                tradeComment.setShopReply(dto.getReply());
                tradeComment.setReplyTime(LocalDateTime.now());
                repository.saveOrUpdate(tradeComment);
            }
            else {
                throw new BusinessException("已经评论了，不能再评论");
            }
        }
    }

    @Override
    public void commentAppendReply(PCMerchTradeCommentDTO.CommentReplyDTO dto) {
        if (org.apache.commons.lang3.StringUtils.isBlank(dto.getId())){
            throw new BusinessException("请传入评论ID");
        }
        TradeComment tradeComment = repository.getById(dto.getId());
        if (ObjectUtils.isNotEmpty(tradeComment)){
            if (tradeComment.getShopAppendReply()==null){
                //可以回复
                tradeComment.setShopAppendReply(dto.getReply());
                tradeComment.setAppendReplyTime(LocalDateTime.now());
                repository.saveOrUpdate(tradeComment);
            }
            else {
                throw new BusinessException("已经评论了，不能再评论");
            }
        }
    }

    //6个月前
    private  QueryWrapper<TradeComment> GetSixMonthsAgo(PCMerchTradeCommentDTO.CommentListETO dto) {
        QueryWrapper<TradeComment> query = MybatisPlusUtil.query();//6月前
        query.and(i->i.le("cdate",LocalDateTime.now().minus(Period.ofMonths(6))));
        if (org.apache.commons.lang3.StringUtils.isNotBlank(dto.getJwtShopId())){
            query.and(i->i.eq("shop_id",dto.getJwtShopId()));
        }
        return query;
    }
    //最近一个月/6个月
    private QueryWrapper<TradeComment> GetMonths(PCMerchTradeCommentDTO.CommentListETO dto, int e) {
        QueryWrapper<TradeComment> query = MybatisPlusUtil.query();
        query.and(i->i.ge("cdate",LocalDateTime.now().minus(Period.ofMonths(e)))
                .le("cdate",LocalDateTime.now()));
        if (org.apache.commons.lang3.StringUtils.isNotBlank(dto.getJwtShopId())){
            query.and(i->i.eq("shop_id",dto.getJwtShopId()));
        }
        return query;
    }

    //1周
    private  QueryWrapper<TradeComment> GetWeekQuery(PCMerchTradeCommentDTO.CommentListETO dto) {
        QueryWrapper<TradeComment> query = MybatisPlusUtil.query();
        query.and(i->i.ge("cdate",LocalDateTime.now().minus(Period.ofDays(7)))
                .le("cdate",LocalDateTime.now()));
        if (org.apache.commons.lang3.StringUtils.isNotBlank(dto.getJwtShopId())){
            query.and(i->i.eq("shop_id",dto.getJwtShopId()));
        }
        return query;
    }

    //好评

    private void setGrade(List<PCMerchTradeCommentVO.Grade> grades, Integer code, QueryWrapper<TradeComment> query1, QueryWrapper<TradeComment> query2, QueryWrapper<TradeComment> query3, QueryWrapper<TradeComment> query4) {
        PCMerchTradeCommentVO.Grade grade = new PCMerchTradeCommentVO.Grade();
        grade.setGoodsGrade(TradeGoodsGradeEnum.好评.getCode());
        Integer week=getCountGrade( query1,code);
        grade.setWeek(week);
        Integer months=getCountGrade( query2,code);
        grade.setMonths(months);
        Integer sixmonths=getCountGrade( query3,code);
        grade.setSixMonths(sixmonths);
        Integer monthsago=getCountGrade( query4,code);
        grade.setSixMonthsAgo(monthsago);
        grade.setTotal(week+months+sixmonths+monthsago);
        grades.add(grade);

    }
    private Integer getCountGrade(QueryWrapper<TradeComment> query, Integer code) {
        query.and(i->i.eq("goods_grade",code));
        List<TradeComment> list = repository.list(query);
        if (ObjectUtils.isNotEmpty(list)){
            return list.size();
        }
        return 0;
    }

    private void setTotal(List<PCMerchTradeCommentVO.Grade> grades) {
        PCMerchTradeCommentVO.Grade Grade = new PCMerchTradeCommentVO.Grade();
        if (ObjectUtils.isNotEmpty(grades)){
            Integer week=0;
            Integer months=0;
            Integer sixMonths=0;
            Integer sixMonthsAgo=0;
            Integer total=0;
            for (PCMerchTradeCommentVO.Grade grade : grades) {
                week+=grade.getWeek();
                months+=grade.getMonths();
                sixMonths+=grade.getSixMonths();
                sixMonthsAgo+=grade.getSixMonthsAgo();
                total+=grade.getTotal();
            }
            Grade.setTotal(total);
            Grade.setMonths(months);
            Grade.setWeek(week);
            Grade.setSixMonths(sixMonths);
            Grade.setSixMonthsAgo(sixMonthsAgo);
            Grade.setGoodsGrade(40);
        }
        grades.add(Grade);
    }






    private String setGrade(Integer grade, int size) {
        DecimalFormat percent = new DecimalFormat("0.0");
        if (ObjectUtils.isNotEmpty(grade)){
            double v = (double) grade / size;
            String format = percent.format(v);
            return format;
        }
        return null;
    }

    private List<String> setAppendImg(PCMerchTradeCommentVO.CommentListListVO listListVO) {
        QueryWrapper<TradeCommentImg> query = MybatisPlusUtil.query();
        query.and(i->i.eq("comment_id",listListVO.getId()));
        query.and(i->i.eq("comment_img_belong", TradeCommentImgBelongEnum.追评.getCode()));
        List<TradeCommentImg> list = commentImgRepository.list(query);
        ArrayList<String> imgs = new ArrayList<>();
        for (TradeCommentImg tradeCommentImg:list){
            imgs.add(tradeCommentImg.getCommentImg());
        }
        return imgs;
    }

    private List<String> setImg(PCMerchTradeCommentVO.CommentListListVO listListVO) {
        QueryWrapper<TradeCommentImg> query = MybatisPlusUtil.query();
        query.and(i->i.eq("comment_id",listListVO.getId()));
        query.and(i->i.eq("comment_img_belong", TradeCommentImgBelongEnum.初评.getCode()));
        List<TradeCommentImg> list = commentImgRepository.list(query);
        ArrayList<String> imgs = new ArrayList<>();
        for (TradeCommentImg tradeCommentImg:list){
            imgs.add(tradeCommentImg.getCommentImg());
        }
        return imgs;
    }

}
