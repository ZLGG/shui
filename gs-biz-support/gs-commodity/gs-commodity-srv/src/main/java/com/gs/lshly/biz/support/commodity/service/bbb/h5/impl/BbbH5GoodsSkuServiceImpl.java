package com.gs.lshly.biz.support.commodity.service.bbb.h5.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.gs.lshly.biz.support.commodity.entity.GoodsInfo;
import com.gs.lshly.biz.support.commodity.entity.SkuGoodInfo;
import com.gs.lshly.biz.support.commodity.repository.IGoodsInfoRepository;
import com.gs.lshly.biz.support.commodity.repository.IGoodsTempalteRepository;
import com.gs.lshly.biz.support.commodity.repository.ISkuGoodInfoRepository;
import com.gs.lshly.biz.support.commodity.service.bbb.h5.IBbbH5GoodsSkuService;
import com.gs.lshly.common.exception.BusinessException;
import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.common.struct.bbb.h5.commodity.dto.BbbH5GoodsInfoDTO;
import com.gs.lshly.common.struct.bbb.h5.commodity.vo.BbbH5SkuGoodInfoVO;
import com.gs.lshly.common.struct.bbb.h5.stock.dto.BbbH5StockDeliveryDTO;
import com.gs.lshly.common.struct.bbb.h5.user.vo.BbbH5UserShoppingCarVO;
import com.gs.lshly.common.struct.common.CommonStockVO;
import com.gs.lshly.common.struct.common.stock.CommonStockTemplateVO;
import com.gs.lshly.middleware.mybatisplus.MybatisPlusUtil;
import com.gs.lshly.rpc.api.common.ICommonStockRpc;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;


/**
 * 2c端sku销售价格服务
 */
@Service
@Slf4j
public class BbbH5GoodsSkuServiceImpl implements IBbbH5GoodsSkuService {

    @Autowired
    private IGoodsTempalteRepository repository;

    @Autowired
    private IGoodsInfoRepository goodsInfoRepository;

    @Autowired
    private ISkuGoodInfoRepository skuGoodInfoRepository;


    @Override
    public String getGoodsStockTemplateId(String skuId) {
        return repository.baseMapper().getTemplateId(skuId);
    }

    @Override
    public CommonStockTemplateVO.SkuAmountAndPriceVO getSalePriceAndWeight(BbbH5StockDeliveryDTO.DeliverySKUDTO skuDTO) {
        CommonStockTemplateVO.SkuAmountAndPriceVO vo = new CommonStockTemplateVO.SkuAmountAndPriceVO();
        SkuGoodInfo sku = skuGoodInfoRepository.getById(skuDTO.getSkuId());
        vo.setSkuId(sku.getId()).setAmount(skuDTO.getAmount());
        if (sku.getSalePrice() != null) {
            vo.setPrice(sku.getSalePrice());
        }
        GoodsInfo goods = goodsInfoRepository.getById(sku.getGoodId());
        vo.setWeight(goods.getGoodsWeight());
        vo.setPrice(vo.getPrice() != null ? vo.getPrice() : goods.getSalePrice());
        //最后检查
        if (vo.getPrice() == null) {
            throw new BusinessException("sku价格为空");
        }
        if (vo.getAmount() == null) {
            throw new BusinessException("sku数量为空");
        }
        if (vo.getWeight() == null) {
            vo.setWeight(BigDecimal.ZERO);
            log.warn(String.format("sku:id=%s, %s; spu:id=%s, no=%s, name=%s,重量为空",sku.getId(), sku.getSpecsValue(), goods.getId(), goods.getGoodsNo(), goods.getGoodsName()));
        }
        return vo;
    }

    @Override
    public BbbH5UserShoppingCarVO.InnerSkuInfoVO getSkuInfo(String skuId) {
        BbbH5UserShoppingCarVO.InnerSkuInfoVO vo = skuGoodInfoRepository.baseMapper().getSkuDetailForB2b(skuId);
        if (StringUtils.isEmpty(vo.getSpecValue())){
            vo.setSkuImage(getImage(vo.getSkuImage()));
        }
        return vo;
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

}
