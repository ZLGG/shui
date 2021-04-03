package com.gs.lshly.biz.support.commodity.service.bbb.pc.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gs.lshly.biz.support.commodity.entity.GoodsInfo;
import com.gs.lshly.biz.support.commodity.entity.GoodsQa;
import com.gs.lshly.biz.support.commodity.mapper.GoodsQaMapper;
import com.gs.lshly.biz.support.commodity.mapper.view.GoodsQaView;
import com.gs.lshly.biz.support.commodity.repository.IGoodsInfoRepository;
import com.gs.lshly.biz.support.commodity.repository.IGoodsQaRepository;
import com.gs.lshly.biz.support.commodity.service.bbb.pc.IPCBbbGoodsQaService;
import com.gs.lshly.common.enums.GoodsQaReplyStateEnum;
import com.gs.lshly.common.enums.QuizTypeEnum;
import com.gs.lshly.common.enums.ShowQuizStateEnum;
import com.gs.lshly.common.exception.BusinessException;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.bbb.pc.commodity.dto.PCBbbGoodsQaDTO;
import com.gs.lshly.common.struct.bbb.pc.commodity.qto.PCBbbGoodsQaQTO;
import com.gs.lshly.common.struct.bbb.pc.commodity.vo.PCBbbGoodsQaVO;
import com.gs.lshly.common.struct.bbb.pc.user.vo.BbbUserVO;
import com.gs.lshly.common.struct.common.CommonShopVO;
import com.gs.lshly.common.struct.common.dto.RemindMerchantDTO;
import com.gs.lshly.common.utils.IpUtil;
import com.gs.lshly.middleware.mybatisplus.MybatisPlusUtil;
import com.gs.lshly.rpc.api.bbb.pc.user.IBbbUserIntegralRpc;
import com.gs.lshly.rpc.api.bbb.pc.user.IBbbUserRpc;
import com.gs.lshly.rpc.api.common.ICommonShopRpc;
import com.gs.lshly.rpc.api.common.IRemindMerchantRpc;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.http.conn.util.InetAddressUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
* <p>
*  服务实现类
* </p>
* @author Starry
* @since 2020-10-16
*/
@Component
public class PCBbbGoodsQaServiceImpl implements IPCBbbGoodsQaService {

    @Autowired
    private IGoodsQaRepository repository;
    @Autowired
    private GoodsQaMapper goodsQaMapper;
    @DubboReference
    private ICommonShopRpc commonShopRpc;
    @DubboReference
    private IBbbUserRpc userRpc;
    @DubboReference
    private IRemindMerchantRpc remindMerchantRpc;

    @Override
    public PageData<PCBbbGoodsQaVO.ShowListVO> pageData(PCBbbGoodsQaQTO.QTO qto) {
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
        List<PCBbbGoodsQaVO.ShowListVO> showListVOS = goodsQas.getRecords()
                .stream().map(e ->{
                    PCBbbGoodsQaVO.ShowListVO showListVO = new PCBbbGoodsQaVO.ShowListVO();
                    BeanUtils.copyProperties(e,showListVO);
                    //获取商家名称
                    showListVO.setMerchantName(getMerchantName(e.getShopId()));
                    return showListVO;
                }).collect(Collectors.toList());
        return new PageData<>(showListVOS,qto.getPageNum(),qto.getPageSize(),goodsQas.getTotal());
    }

    @Override
    public PageData<PCBbbGoodsQaVO.UserGoodsQaListVO> pageUserGoodsQa(PCBbbGoodsQaQTO.UserQTO qto) {
        if (ObjectUtils.isEmpty(qto.getJwtUserId())){
            throw new BusinessException("未登录！！");
        }
        //根据用户id查询用户名
        BbbUserVO.InnerUserInfoVO userInfoVO = userRpc.innerGetUserInfo(qto.getJwtUserId());
        if (ObjectUtils.isEmpty(userInfoVO)){
            throw new BusinessException("用户不存在，或者查询异常！！");
        }
        QueryWrapper<GoodsQaView> wrapper = MybatisPlusUtil.query();
        wrapper.eq("gq.operator",userInfoVO.getUserName());
        IPage<GoodsQaView> page = MybatisPlusUtil.pager(qto);
        IPage<GoodsQaView> goodsQas = goodsQaMapper.getUserGoodsQaListVO(page,wrapper);
        if (ObjectUtils.isEmpty(goodsQas)){
            return new PageData<>();
        }
        List<PCBbbGoodsQaVO.UserGoodsQaListVO> showListVOS = goodsQas.getRecords()
                .stream().map(e ->{
                    PCBbbGoodsQaVO.UserGoodsQaListVO showListVO = new PCBbbGoodsQaVO.UserGoodsQaListVO();
                    BeanUtils.copyProperties(e,showListVO);
                    showListVO.setGoodsImage(getImage(e.getGoodsImage()));
                    // 获取商家名称
                    showListVO.setMerchantName(getMerchantName(e.getShopId()));
                    return showListVO;
                }).collect(Collectors.toList());
        return new PageData<>(showListVOS,qto.getPageNum(),qto.getPageSize(),goodsQas.getTotal());
    }

    @Override
    public void addGoodsQa(PCBbbGoodsQaDTO.ETO eto) {
        if (eto == null){
            throw new BusinessException("参数不能为空！");
        }
        GoodsQa goodsQa = new GoodsQa();
        BeanUtils.copyProperties(eto, goodsQa);
        if (ObjectUtils.isNotEmpty(eto.getJwtUserId())){
            BbbUserVO.InnerUserInfoVO userInfoVO = userRpc.innerUserVo(eto);
            if (ObjectUtils.isNotEmpty(userInfoVO)){
                goodsQa.setOperator(StringUtils.isBlank(userInfoVO.getUserName())?"":userInfoVO.getUserName());
            }
        }
        goodsQa.setIsReply(GoodsQaReplyStateEnum.未回复.getCode());
        goodsQa.setIsShowQuizContent(ShowQuizStateEnum.显示.getCode());
        repository.save(goodsQa);
        //触发商品资询提醒
        remindMerchantRpc.addRemindMerchantForAskTalk(new RemindMerchantDTO.JustDTO(goodsQa.getShopId(),eto.getJwtUserId(),goodsQa.getId()));
    }

    @Override
    public PCBbbGoodsQaVO.CountQuizVO countQuiz(PCBbbGoodsQaQTO.GoodsIdQTO qto) {
        if (qto == null){
            throw new BusinessException("参数不能为空！");
        }
        PCBbbGoodsQaVO.CountQuizVO  countQuizVO = new PCBbbGoodsQaVO.CountQuizVO();
        countQuizVO.setGoodsQuizNum(count(qto.getGoodId(),QuizTypeEnum.商品咨询.getCode()) == 0?0:count(qto.getGoodId(),QuizTypeEnum.商品咨询.getCode()));
        countQuizVO.setInventoryDistributionNum(count(qto.getGoodId(),QuizTypeEnum.库存配送.getCode()) == 0?0:count(qto.getGoodId(),QuizTypeEnum.库存配送.getCode()));
        countQuizVO.setPaymentWayNum(count(qto.getGoodId(),QuizTypeEnum.支付方式.getCode()) ==0?0:count(qto.getGoodId(),QuizTypeEnum.支付方式.getCode()));
        countQuizVO.setInvoiceWarrantyNum(count(qto.getGoodId(),QuizTypeEnum.发票保修.getCode())==0?0:count(qto.getGoodId(),QuizTypeEnum.发票保修.getCode()));
        return countQuizVO;
    }


    @Override
    public void deleteGoodsQa(PCBbbGoodsQaDTO.IdListDTO dto) {
        if (ObjectUtils.isEmpty(dto) || ObjectUtils.isEmpty(dto.getIdList())){
            throw new BusinessException("参数为空！异常！");
        }
        QueryWrapper<GoodsQa> wrapper = MybatisPlusUtil.query();
        wrapper.in("id",dto.getIdList());
        wrapper.eq("operator",dto.getJwtUserId());
        repository.remove(wrapper);
    }


    private int count(String goodsId,Integer type){
        QueryWrapper<GoodsQa> qaQueryWrapper = MybatisPlusUtil.query();
        qaQueryWrapper.eq("good_id",goodsId);
        qaQueryWrapper.eq("quiz_type",type);
        int count = repository.count(qaQueryWrapper);
        return count;
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

    private String getMerchantName(String shopId){
        String name = "";
        CommonShopVO.MerchantVO merchantVO = commonShopRpc.merchantByShopId(shopId);
        if (ObjectUtils.isNotEmpty(merchantVO)){
            name = StringUtils.isEmpty(merchantVO.getMerchantName())?"":merchantVO.getMerchantName();
        }
        return name;
    }

}
