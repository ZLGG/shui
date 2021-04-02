package com.gs.lshly.biz.support.commodity.service.bbb.h5.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.gs.lshly.biz.support.commodity.entity.GoodsQa;
import com.gs.lshly.biz.support.commodity.mapper.GoodsQaMapper;
import com.gs.lshly.biz.support.commodity.mapper.view.GoodsQaCountView;
import com.gs.lshly.biz.support.commodity.repository.IGoodsQaRepository;
import com.gs.lshly.biz.support.commodity.service.bbb.h5.IBbbH5GoodsQaService;
import com.gs.lshly.common.enums.GoodsQaReplyStateEnum;
import com.gs.lshly.common.enums.ShowQuizStateEnum;
import com.gs.lshly.common.exception.BusinessException;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.bbb.h5.commodity.dto.BbbH5GoodsQaDTO;
import com.gs.lshly.common.struct.bbb.h5.commodity.qto.BbbH5GoodsQaQTO;
import com.gs.lshly.common.struct.bbb.h5.commodity.vo.BbbH5GoodsQaVO;
import com.gs.lshly.common.struct.bbb.h5.user.vo.BbbH5UserVO;
import com.gs.lshly.common.struct.bbb.pc.user.vo.BbbUserVO;
import com.gs.lshly.common.struct.common.CommonShopVO;
import com.gs.lshly.common.struct.common.dto.RemindMerchantDTO;
import com.gs.lshly.middleware.mybatisplus.MybatisPlusUtil;
import com.gs.lshly.rpc.api.bbb.h5.user.IBbbH5UserRpc;
import com.gs.lshly.rpc.api.common.ICommonShopRpc;
import com.gs.lshly.rpc.api.common.IRemindMerchantRpc;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
* <p>
*  服务实现类
* </p>
* @author Starry
* @since 2020-10-16
*/
@Component
public class BbbH5GoodsQaServiceImpl implements IBbbH5GoodsQaService {

    @Autowired
    private IGoodsQaRepository repository;

    @Autowired
    private GoodsQaMapper goodsQaMapper;

    @DubboReference
    private ICommonShopRpc commonShopRpc;

    @DubboReference
    private IBbbH5UserRpc userRpc;

    @DubboReference
    private IRemindMerchantRpc remindMerchantRpc;

    @Override
    public PageData<BbbH5GoodsQaVO.ShowListVO> pageData(BbbH5GoodsQaQTO.QTO qto) {
        QueryWrapper<GoodsQa> qaQueryWrapper = MybatisPlusUtil.query();
        qaQueryWrapper.eq("good_id",qto.getGoodId());
        if (qto.getQuizType() != null && qto.getQuizType().intValue() !=0 && qto.getQuizType().intValue() !=-1){
            qaQueryWrapper.eq("quiz_type",qto.getQuizType());
        }
        qaQueryWrapper.eq("is_show_quiz_content", ShowQuizStateEnum.显示.getCode());
        qaQueryWrapper.orderByDesc("cdate","id");
        IPage<GoodsQa> page = MybatisPlusUtil.pager(qto);
        IPage<GoodsQa> goodsQas = repository.page(page,qaQueryWrapper);
        if (ObjectUtils.isEmpty(goodsQas)){
            return new PageData<>();
        }
        List<BbbH5GoodsQaVO.ShowListVO> showListVOS = goodsQas.getRecords()
                .stream().map(e ->{
                    BbbH5GoodsQaVO.ShowListVO showListVO = new BbbH5GoodsQaVO.ShowListVO();
                    BeanUtils.copyProperties(e,showListVO);
                    //获取商家名称
                    CommonShopVO.MerchantVO merchantVO = commonShopRpc.merchantByShopId(e.getShopId());
                    showListVO.setMerchantName(StringUtils.isEmpty(merchantVO.getMerchantName())?"":merchantVO.getMerchantName());
                    QueryWrapper<GoodsQa> wrapper = MybatisPlusUtil.query();
                    wrapper.eq("good_id",e.getGoodId());
                    wrapper.eq("is_show_quiz_content", ShowQuizStateEnum.显示.getCode());
                    GoodsQaCountView countView = goodsQaMapper.countView(wrapper);
                    if (ObjectUtils.isNotEmpty(countView)){
                        BbbH5GoodsQaVO.CountQuizVO countQuizVO = new BbbH5GoodsQaVO.CountQuizVO();
                        BeanUtils.copyProperties(countView,countQuizVO);
                        countQuizVO.setAllNum(countView.getGoodsQuizNum()+countView.getInventoryDistributionNum()+countView.getInvoiceWarrantyNum()+countView.getPaymentWayNum());
                        showListVO.setCountQuizVO(countQuizVO);
                    }
                    return showListVO;
                }).collect(Collectors.toList());
        return new PageData<>(showListVOS,qto.getPageNum(),qto.getPageSize(),goodsQas.getTotal());
    }

    @Override
    public void addGoodsQa(BbbH5GoodsQaDTO.ETO eto) {
        if (eto == null){
            throw new BusinessException("参数不能为空！");
        }
        GoodsQa goodsQa = new GoodsQa();
        BeanUtils.copyProperties(eto, goodsQa);
        if (ObjectUtils.isNotEmpty(eto.getJwtUserId())){
            BbbH5UserVO.InnerUserInfoVO userInfoVO = userRpc.innerGetUserInfo(eto.getJwtUserId());
            if (ObjectUtils.isNotEmpty(userInfoVO)){
                goodsQa.setOperator(StringUtils.isBlank(userInfoVO.getUserName())?"":userInfoVO.getUserName());
            }
        }
        goodsQa.setIsReply(GoodsQaReplyStateEnum.未回复.getCode());
        goodsQa.setIsShowQuizContent(ShowQuizStateEnum.显示.getCode());
        repository.save(goodsQa);

        //触发商家商品咨询提醒
        remindMerchantRpc.addRemindMerchantForAskTalk(new RemindMerchantDTO.JustDTO(goodsQa.getShopId(),eto.getJwtUserId(),goodsQa.getId()));
    }

}
