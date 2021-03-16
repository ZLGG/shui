package com.gs.lshly.biz.support.commodity.service.bbc.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.gs.lshly.biz.support.commodity.entity.GoodsQa;
import com.gs.lshly.biz.support.commodity.mapper.GoodsQaMapper;
import com.gs.lshly.biz.support.commodity.mapper.view.GoodsQaCountView;
import com.gs.lshly.biz.support.commodity.repository.IGoodsQaRepository;
import com.gs.lshly.biz.support.commodity.service.bbb.pc.IPCBbbGoodsQaService;
import com.gs.lshly.biz.support.commodity.service.bbc.IBbcGoodsQaService;
import com.gs.lshly.common.enums.GoodsQaReplyStateEnum;
import com.gs.lshly.common.enums.QuizTypeEnum;
import com.gs.lshly.common.enums.ShowQuizStateEnum;
import com.gs.lshly.common.exception.BusinessException;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.bbb.h5.user.vo.BbbH5UserVO;
import com.gs.lshly.common.struct.bbb.pc.commodity.dto.PCBbbGoodsQaDTO;
import com.gs.lshly.common.struct.bbb.pc.commodity.qto.PCBbbGoodsQaQTO;
import com.gs.lshly.common.struct.bbb.pc.commodity.vo.PCBbbGoodsQaVO;
import com.gs.lshly.common.struct.bbc.commodity.dto.BbcGoodsQaDTO;
import com.gs.lshly.common.struct.bbc.commodity.qto.BbcGoodsQaQTO;
import com.gs.lshly.common.struct.bbc.commodity.vo.BbcGoodsQaVO;
import com.gs.lshly.common.struct.bbc.user.vo.BbcUserVO;
import com.gs.lshly.common.struct.common.CommonShopVO;
import com.gs.lshly.middleware.mybatisplus.MybatisPlusUtil;
import com.gs.lshly.rpc.api.bbc.user.IBbcUserRpc;
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
* <p>
*  服务实现类
* </p>
* @author Starry
* @since 2020-10-16
*/
@Component
public class BbcGoodsQaServiceImpl implements IBbcGoodsQaService {

    @Autowired
    private IGoodsQaRepository repository;
    @Autowired
    private GoodsQaMapper goodsQaMapper;
    @DubboReference
    private ICommonShopRpc commonShopRpc;
    @DubboReference
    private IBbcUserRpc bbcUserRpc;

    @Override
    public PageData<BbcGoodsQaVO.ShowListVO> pageData(BbcGoodsQaQTO.QTO qto) {
        QueryWrapper<GoodsQa> qaQueryWrapper = MybatisPlusUtil.query();
        qaQueryWrapper.eq("good_id",qto.getGoodId());
        if (qto.getQuizType() != null && qto.getQuizType().intValue() !=0 && qto.getQuizType().intValue() !=-1){
            qaQueryWrapper.eq("quiz_type",qto.getQuizType());
        }
        qaQueryWrapper.eq("is_show_quiz_content", ShowQuizStateEnum.显示.getCode());
        IPage<GoodsQa> page = MybatisPlusUtil.pager(qto);
        IPage<GoodsQa> goodsQas = repository.page(page,qaQueryWrapper);
        if (ObjectUtils.isEmpty(goodsQas)){
            return new PageData<>();
        }
        List<BbcGoodsQaVO.ShowListVO> showListVOS = goodsQas.getRecords()
                .stream().map(e ->{
                    BbcGoodsQaVO.ShowListVO showListVO = new BbcGoodsQaVO.ShowListVO();
                    BeanUtils.copyProperties(e,showListVO);
                    //获取商家名称
                    CommonShopVO.MerchantVO merchantVO = commonShopRpc.merchantByShopId(e.getShopId());
                    showListVO.setMerchantName(StringUtils.isEmpty(merchantVO.getMerchantName())?"":merchantVO.getMerchantName());
                    QueryWrapper<GoodsQa> wrapper = MybatisPlusUtil.query();
                    wrapper.eq("good_id",e.getGoodId());
                    GoodsQaCountView countView = goodsQaMapper.countView(wrapper);
                    if (ObjectUtils.isNotEmpty(countView)){
                        BbcGoodsQaVO.CountQuizVO countQuizVO = new BbcGoodsQaVO.CountQuizVO();
                        BeanUtils.copyProperties(countView,countQuizVO);
                        showListVO.setCountQuizVO(countQuizVO);
                    }
                    return showListVO;
                }).collect(Collectors.toList());
        return new PageData<>(showListVOS,qto.getPageNum(),qto.getPageSize(),goodsQas.getTotal());
    }

    @Override
    public void addGoodsQa(BbcGoodsQaDTO.ETO eto) {
        if (eto == null){
            throw new BusinessException("参数不能为空！");
        }
        GoodsQa goodsQa = new GoodsQa();
        BeanUtils.copyProperties(eto, goodsQa);
        if (ObjectUtils.isNotEmpty(eto.getJwtUserId())){
            BbcUserVO.InnerUserInfoVO userInfoVO = bbcUserRpc.innerGetUserInfo(eto.getJwtUserId());
            if (ObjectUtils.isNotEmpty(userInfoVO)){
                goodsQa.setOperator(StringUtils.isBlank(userInfoVO.getUserName())?"":userInfoVO.getUserName());
            }
        }
        goodsQa.setIsReply(GoodsQaReplyStateEnum.未回复.getCode());
        goodsQa.setIsShowQuizContent(ShowQuizStateEnum.不显示.getCode());
        repository.save(goodsQa);
    }


}
