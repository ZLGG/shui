package com.gs.lshly.biz.support.commodity.service.bbb.pc.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.gs.lshly.biz.support.commodity.entity.GoodsInfo;
import com.gs.lshly.biz.support.commodity.entity.SkuGoodInfo;
import com.gs.lshly.biz.support.commodity.repository.IGoodsInfoRepository;
import com.gs.lshly.biz.support.commodity.repository.IGoodsTempalteRepository;
import com.gs.lshly.biz.support.commodity.repository.ISkuGoodInfoRepository;
import com.gs.lshly.biz.support.commodity.service.bbb.pc.IPCBbbGoodsSkuService;
import com.gs.lshly.biz.support.commodity.service.bbc.IBbcGoodsSkuService;
import com.gs.lshly.common.exception.BusinessException;
import com.gs.lshly.common.struct.bbb.pc.stock.dto.BbbStockDeliveryDTO;
import com.gs.lshly.common.struct.bbb.pc.user.vo.BbbUserShoppingCarVO;
import com.gs.lshly.common.struct.bbc.stock.dto.BbcStockDeliveryDTO;
import com.gs.lshly.common.struct.bbc.user.vo.BbcUserShoppingCarVO;
import com.gs.lshly.common.struct.common.stock.CommonStockTemplateVO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;


/**
 * 2c端sku销售价格服务
 */
@Service
@Slf4j
public class PCBbbGoodsSkuServiceImpl implements IPCBbbGoodsSkuService {

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
    public CommonStockTemplateVO.SkuAmountAndPriceVO getSalePriceAndWeight(BbbStockDeliveryDTO.DeliverySKUDTO skuDTO) {
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
    public BbbUserShoppingCarVO.InnerSkuInfoVO getSkuInfo(String skuId) {
        BbbUserShoppingCarVO.InnerSkuInfoVO innerSkuInfoVO = new BbbUserShoppingCarVO.InnerSkuInfoVO();
        BbcUserShoppingCarVO.InnerSkuInfoVO vo = skuGoodInfoRepository.baseMapper().getSkuDetail(skuId);
        if (StringUtils.isEmpty(vo.getSpecValue())){
            vo.setSkuImage(getImage(vo.getSkuImage()));
        }
        BeanUtils.copyProperties(vo,innerSkuInfoVO);
        return innerSkuInfoVO;
    }

    private  String getImage(String images){
        if (images !=null){
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
