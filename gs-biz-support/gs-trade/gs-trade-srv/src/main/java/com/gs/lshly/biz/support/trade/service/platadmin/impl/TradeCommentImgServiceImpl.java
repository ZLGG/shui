package com.gs.lshly.biz.support.trade.service.platadmin.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.gs.lshly.biz.support.trade.entity.TradeCommentImg;
import com.gs.lshly.biz.support.trade.repository.ITradeCommentImgRepository;
import com.gs.lshly.biz.support.trade.service.platadmin.ITradeCommentImgService;
import com.gs.lshly.common.struct.platadmin.trade.qto.TradeCommentImgQTO;
import com.gs.lshly.common.struct.platadmin.trade.vo.TradeCommentImgVO;
import com.gs.lshly.middleware.mybatisplus.MybatisPlusUtil;

/**
* <p>
*  服务实现类
* </p>
* @author Starry
* @since 2020-11-17
*/
@Component
public class TradeCommentImgServiceImpl implements ITradeCommentImgService {

    @Autowired
    private ITradeCommentImgRepository repository;


    @Override
    public List<TradeCommentImgVO.ListVO> listImageVO(TradeCommentImgQTO.QTO qto) {
        QueryWrapper<TradeCommentImg> boost = MybatisPlusUtil.query();
        boost.eq("comment_id",qto.getCommentId());
        boost.eq("comment_img_belong",qto.getCommentImgBelong());
        boost.orderByDesc("cdate");
        List<TradeCommentImg> imgs = repository.list(boost);
        if (ObjectUtils.isEmpty(imgs)){
            return new ArrayList<>();
        }
        List<TradeCommentImgVO.ListVO> listVOS = imgs.stream()
                .map(e ->{
                    TradeCommentImgVO.ListVO listVO = new TradeCommentImgVO.ListVO();
                    BeanUtils.copyProperties(e,listVO);
                    return listVO;
                }).collect(Collectors.toList());
        return listVOS;
    }
}
