package com.gs.lshly.biz.support.stock.service.common.impl;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.gs.lshly.biz.support.stock.entity.*;
import com.gs.lshly.biz.support.stock.enums.StockTemplateRegionLeveEnum;
import com.gs.lshly.biz.support.stock.enums.StockTemplateTypeEnum;
import com.gs.lshly.biz.support.stock.repository.*;
import com.gs.lshly.biz.support.stock.service.common.ICommonStockTemplateService;
import com.gs.lshly.common.enums.stock.StockDeliveryCostCalcWayEnum;
import com.gs.lshly.common.enums.stock.StockFreeDeliveryCalcWayEnum;
import com.gs.lshly.common.exception.BusinessException;
import com.gs.lshly.common.struct.common.stock.CommonDeliveryCostCalcParam;
import com.gs.lshly.common.struct.common.stock.CommonStockTemplateDTO;
import com.gs.lshly.common.struct.common.stock.CommonStockTemplateVO;
import com.gs.lshly.common.utils.BeanCopyUtils;
import com.gs.lshly.common.utils.JsonUtils;
import com.gs.lshly.middleware.mybatisplus.MybatisPlusUtil;
import com.gs.lshly.rpc.api.merchadmin.pc.commodity.IPCMerchGoodsStockRpc;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

@Service
@Slf4j
public class CommonStockTemplateServiceImpl implements ICommonStockTemplateService {

    @Autowired
    private IStockTemplateRepository repository;

    @Autowired
    private IStockTemplateQuotaRepository quotaRepository;

    @Autowired
    private IStockTemplateQuotaPriceRepository quotaPriceRepository;

    @Autowired
    private IStockTemplateRegionRepository regionRepository;

    @Autowired
    private IStockTemplateUnitRepository unitRepository;

    @Autowired
    private IStockTemplateFreeRepository freeRepository;

    @Autowired
    private IStockAddressRepository addressRepository;

    private CommonStockTemplateVO.RegionVO provinceRegionVO(Map<String, CommonStockTemplateVO.RegionVO> map, StockTemplateRegion province) {
        CommonStockTemplateVO.RegionVO regionVO = map.get(province.getProvinceId());
        if (regionVO == null) {
            regionVO = new CommonStockTemplateVO.RegionVO();
            regionVO.setId(province.getProvinceId()).setProvince(province.getProvince());
            map.put(province.getProvinceId(), regionVO);
        }
        return regionVO;
    }

    private CommonStockTemplateVO.RegionCVO cityRegionVO(StockTemplateRegion region) {
        CommonStockTemplateVO.RegionCVO regionCVO = new CommonStockTemplateVO.RegionCVO();
        regionCVO.setId(region.getCityId()).setCity(region.getCity());
        return regionCVO;
    }

    private Collection<CommonStockTemplateVO.RegionVO> fetchRegion(String childTemplateId) {
        List<StockTemplateRegion> regions = regionRepository.list(new QueryWrapper<StockTemplateRegion>().eq("child_template_id", childTemplateId));
        Map<String, CommonStockTemplateVO.RegionVO> map = new HashMap<>();
        for (StockTemplateRegion region : regions) {
            CommonStockTemplateVO.RegionVO provinceRegionVO = provinceRegionVO(map, region);
            if (StockTemplateRegionLeveEnum.市.getCode().equals(region.getRegionLeve())) {
                CommonStockTemplateVO.RegionCVO cityRegionVO = cityRegionVO(region);
                provinceRegionVO.getRegionCVO().add(cityRegionVO);
            }
        }
        return map.values();
    }

    private List<CommonStockTemplateVO.FreeVO> fetchFree(String templateId) {
        List<StockTemplateFree> freeList = freeRepository.list(new QueryWrapper<StockTemplateFree>().eq("template_id", templateId));
        List<CommonStockTemplateVO.FreeVO> frees = new ArrayList<>();
        for (StockTemplateFree item : freeList) {
            CommonStockTemplateVO.FreeVO vo = new CommonStockTemplateVO.FreeVO();
            vo.setId(item.getId());
            vo.setIsDefault(item.getIsDefault());
            vo.setMathType(item.getMathType());
            vo.setPriceValue(item.getPrice());
            vo.setUnitValue(item.getQuantity());
            //1.2.1 包邮条件对应的区域
            Collection<CommonStockTemplateVO.RegionVO> regions = fetchRegion(item.getId());
            vo.getRegionVOList().addAll(regions);
            //区域字符串化
            String regionStr = regionVOStr(vo.getRegionVOList());
            vo.setAddress(regionStr);
            frees.add(vo);
        }
        return frees;
    }

    private List<CommonStockTemplateVO.QuotaPriceVO> fetchPrice(String quotaId) {
        List<StockTemplateQuotaPrice> priceList = quotaPriceRepository.list(new QueryWrapper<StockTemplateQuotaPrice>().eq("quota_id", quotaId));
        List<CommonStockTemplateVO.QuotaPriceVO> prices = new ArrayList<>();
        for(StockTemplateQuotaPrice price : priceList) {
            CommonStockTemplateVO.QuotaPriceVO vo = new CommonStockTemplateVO.QuotaPriceVO();
            BeanCopyUtils.copyProperties(price, vo);
            prices.add(vo);
        }
        return prices;
    }

    @Override
    public CommonStockTemplateVO.ListDetailVO getDetail(String templdateId) {
        StockTemplate stockTemplate = repository.getById(templdateId);
        //主表信息设置
        CommonStockTemplateVO.ListDetailVO listDetailVO = new CommonStockTemplateVO.ListDetailVO();
        BeanCopyUtils.copyProperties(stockTemplate, listDetailVO);

        fillComplexTypeTemplate(listDetailVO);

        return listDetailVO;
    }

    /**
     * 填充运费模板
     * @param listDetailVO
     */
    @Override
    public void fillComplexTypeTemplate(CommonStockTemplateVO.ListDetailVO listDetailVO) {
        //完全买家承担
        if (StockTemplateTypeEnum.卖家承担.getCode().equals(listDetailVO.getTemplateType())) {
            return ;
        }
        //1，计件或计重
        if (StockTemplateTypeEnum.计件.getCode().equals(listDetailVO.getTemplateType()) || StockTemplateTypeEnum.计重.getCode().equals(listDetailVO.getTemplateType())) {
            //1.1，首件（重），续件（重）主记录
            List<StockTemplateUnit> unitList = unitRepository.list(new QueryWrapper<StockTemplateUnit>().eq("template_id", listDetailVO.getId()));
            for (StockTemplateUnit item : unitList) {
                CommonStockTemplateVO.UnitVO unitVO = new CommonStockTemplateVO.UnitVO();
                BeanCopyUtils.copyProperties(item, unitVO);
                //1.1.1，主记录所对应的应用区域
                Collection<CommonStockTemplateVO.RegionVO> regions = fetchRegion(item.getId());
                unitVO.getRegionVOList().addAll(regions);
                //区域字符串化
                String regionStr = regionVOStr(unitVO.getRegionVOList());
                unitVO.setAddress(regionStr);
                listDetailVO.getUnitVOList().add(unitVO);
            }
            //1.2，包邮条件
            List<CommonStockTemplateVO.FreeVO> frees = fetchFree(listDetailVO.getId());
            listDetailVO.setFreeVOList(frees);
        }
        //2，金额范围
        if (StockTemplateTypeEnum.金额范围.getCode().equals(listDetailVO.getTemplateType())) {
            List<StockTemplateQuota> listStockTemplateQuota = quotaRepository.list(new QueryWrapper<StockTemplateQuota>().eq("template_id", listDetailVO.getId()));
            //2.1，金额范围 主记录
            for (StockTemplateQuota item : listStockTemplateQuota) {
                CommonStockTemplateVO.QuotaVO quotaVO = new CommonStockTemplateVO.QuotaVO();
                BeanCopyUtils.copyProperties(item, quotaVO);
                //2.1.2 主记录所对应的区域
                Collection<CommonStockTemplateVO.RegionVO> regions = fetchRegion(item.getId());
                quotaVO.getRegionVOList().addAll(regions);
                //2.1.2 主记录所对应的价格区间
                quotaVO.getPriceVOList().addAll(fetchPrice(item.getId()));
                //区域字符串化
                String regionStr = regionVOStr(quotaVO.getRegionVOList());
                quotaVO.setAddress(regionStr);
                listDetailVO.getQuotaVOList().add(quotaVO);
            }
            //2.2，包邮条件
            List<CommonStockTemplateVO.FreeVO> frees = fetchFree(listDetailVO.getId());
            listDetailVO.setFreeVOList(frees);
        }
    }

    private String regionVOStr(List<CommonStockTemplateVO.RegionVO> regionVOList) {
        if (ObjectUtils.isEmpty(regionVOList)) {
            return "全国";
        } else {
            StringBuilder sbProvince = new StringBuilder();
            for (CommonStockTemplateVO.RegionVO province : regionVOList) {
                if (ObjectUtils.isEmpty(province.getRegionCVO())) {
                    sbProvince.append(province.getProvince()).append(";");
                } else {
                    sbProvince.append(province.getProvince()).append(":");
                    String cityStr = "%s";
                    StringBuilder sbCity = new StringBuilder();
                    for (CommonStockTemplateVO.RegionCVO city : province.getRegionCVO()) {
                        sbCity.append(city.getCity()).append(",");
                    }
                    if (sbCity.length() > 0) {
                        sbCity = sbCity.deleteCharAt(sbCity.length() - 1);
                    }
                    sbProvince.append(String.format(cityStr, sbCity.toString())).append(";");
                }
            }
            if (sbProvince.length() > 1) {
                sbProvince = sbProvince.deleteCharAt(sbProvince.length() - 1);
            }
            return sbProvince.toString();
        }
    }

    /**
     * 快递运费模板与地址转换运费-计算参数
     * @param sku
     * @param template
     * @param addressId
     * @return
     */
    @Override
    public CommonDeliveryCostCalcParam parseToDeliveryCostCalcParam(CommonStockTemplateVO.SkuAmountAndPriceVO sku, CommonStockTemplateVO.ListDetailVO template, String addressId) {
        StockAddress address = addressRepository.getById(addressId);
        if (address == null) {
            throw new BusinessException("地址未找到");
        }
        if (template == null) {
            throw new BusinessException("运费模板未找到");
        } else {
            log.info("运费模板：id={},name={}，计费类型为【{}-{}】", template.getId(), template.getTemplateName(), template.getTemplateType(), StockTemplateTypeEnum.text(template.getTemplateType()));
        }
        CommonDeliveryCostCalcParam param = new CommonDeliveryCostCalcParam();
        if (StockTemplateTypeEnum.卖家承担.getCode().equals(template.getTemplateType())) {
            return param.setCalcWay(StockDeliveryCostCalcWayEnum.固定运费).setCustomizeCost(BigDecimal.ZERO);
        }

        if (StockTemplateTypeEnum.计件.getCode().equals(template.getTemplateType())) {
            return unitParam(param, address, StockDeliveryCostCalcWayEnum.按件数, sku, template);
        }
        if (StockTemplateTypeEnum.计重.getCode().equals(template.getTemplateType())) {
            return unitParam(param, address, StockDeliveryCostCalcWayEnum.按重量, sku, template);
        }
        if (StockTemplateTypeEnum.金额范围.getCode().equals(template.getTemplateType())) {
            CommonStockTemplateVO.QuotaVO quotaVO = matchedQuotaRegion(address, template.getQuotaVOList());
            if (quotaVO != null) {
                log.info("地址：{} ，匹配到【金额范围】的运费计算规则：{}", JsonUtils.toJson(address), JsonUtils.toJson(quotaVO));
                BigDecimal stepPriceParam = matchStepPrice(sku, quotaVO.getPriceVOList());
                return param.setCalcWay(StockDeliveryCostCalcWayEnum.按金额).setStepPriceParam(stepPriceParam);
            } else {
                throw new BusinessException(String.format("地址：%s ，未能匹配到【金额范围】的运费计算规则，规则配置有误", JsonUtils.toJson(address)));
            }
        }
        throw new BusinessException(String.format("运费模板：id=%s,name=%s，计费类型为%s，配置有误", template.getId(), template.getTemplateName(), template.getTemplateType()));
    }

    private static BigDecimal matchStepPrice(CommonStockTemplateVO.SkuAmountAndPriceVO sku, List<CommonStockTemplateVO.QuotaPriceVO> priceVOList) {
        if (ObjectUtils.isEmpty(priceVOList)) {
            throw new BusinessException("【金额范围】运费计算规则内容为空");
        }
        //金额范围，规则排序，按最小值排序倒序
        Collections.sort(priceVOList, (o1, o2) -> o1.getMinValue().compareTo(o2.getMinValue())>=0 ? -1 : 1);
        for(CommonStockTemplateVO.QuotaPriceVO vo : priceVOList){
            if (vo.getPrice() == null || vo.getMinValue() == null || vo.getMaxValue() == null) {
                throw new BusinessException(String.format("【金额范围】运费计算规则内存在必填值为空，规则配置有误%s", JsonUtils.toJson(vo)));
            }
        }
        if (priceVOList.get(0).getMaxValue().compareTo(new BigDecimal("-1")) != 0) {
            throw new BusinessException("【金额范围】运费计算规则不存在最大区间，规则配置有误");
        }
        for (CommonStockTemplateVO.QuotaPriceVO vo : priceVOList){
            if (sku.getPrice().compareTo(vo.getMinValue()) >= 0) {
                log.info("sku总价：{}，匹配金额区间[{}-{}]，运费为：{}", sku.getPrice(), vo.getMinValue(), vo.getMaxValue(), vo.getPrice());
                return vo.getPrice();
            }
        }
        throw new BusinessException(String.format("sku总金额：%s 小于 规则最小金额 %s", sku.getPrice(), priceVOList.get(priceVOList.size() - 1).getPrice()));
    }

    public static void main(String[] args) {
        CommonStockTemplateVO.SkuAmountAndPriceVO sku = new CommonStockTemplateVO.SkuAmountAndPriceVO();
        sku.setPrice(new BigDecimal("7"));
        List<CommonStockTemplateVO.QuotaPriceVO> priceVOList = CollUtil.toList(
                new CommonStockTemplateVO.QuotaPriceVO().setMinValue(new BigDecimal("5")).setMaxValue(BigDecimal.TEN).setPrice(new BigDecimal("2")),
                new CommonStockTemplateVO.QuotaPriceVO().setMinValue(BigDecimal.ZERO).setMaxValue(new BigDecimal("5")).setPrice(new BigDecimal("3")),
                new CommonStockTemplateVO.QuotaPriceVO().setMinValue(BigDecimal.TEN).setMaxValue(new BigDecimal("-1")).setPrice(new BigDecimal("1")));
        System.out.println(matchStepPrice(sku, priceVOList));
    }

    /**
     * 按区域匹配金额范围运费计算规则
     * @param address
     * @param quotaVOList
     * @return
     */
    private CommonStockTemplateVO.QuotaVO matchedQuotaRegion(StockAddress address, List<CommonStockTemplateVO.QuotaVO> quotaVOList) {
        //默认免邮规则-全国范围
        CommonStockTemplateVO.QuotaVO defaultVO = null;
        //匹配到的免邮规则
        CommonStockTemplateVO.QuotaVO matchedVO = null;
        Collections.sort(quotaVOList, (o1, o2) -> ObjectUtils.isNotEmpty(o1.getRegionVOList()) && ObjectUtils.isEmpty(o1.getRegionVOList()) ? 1 : -1);
        log.info("所有【按金额范围】的规则：{}", JsonUtils.toJson(quotaVOList));
        for (CommonStockTemplateVO.QuotaVO vo : quotaVOList) {
            if (matchedVO != null) {
                continue;
            }
            if (ObjectUtils.isEmpty(vo.getRegionVOList())) {
                defaultVO = vo;
            }
            //区域排序(市在前、省在后)
            sortRegion(vo.getRegionVOList());
            if (matchedAddress(address, vo.getRegionVOList())) {
                matchedVO = vo;
            }
        }
        return matchedVO != null ? matchedVO : defaultVO;
    }

    /**
     * 按计重或计件及区域匹配运费计算参数
     * @param param
     * @param address
     * @param calcWayEnum
     * @param sku
     * @param stockTemplateDetail
     * @return
     */
    private CommonDeliveryCostCalcParam unitParam(CommonDeliveryCostCalcParam param, StockAddress address, StockDeliveryCostCalcWayEnum calcWayEnum,
                                                  CommonStockTemplateVO.SkuAmountAndPriceVO sku, CommonStockTemplateVO.ListDetailVO stockTemplateDetail) {
        String msgUnit = StockDeliveryCostCalcWayEnum.按件数 == calcWayEnum ? "按件数" : "按重量";
        //是否包邮
        if (isFree(calcWayEnum, sku, address, stockTemplateDetail.getFreeVOList())) {
            return param.setCalcWay(StockDeliveryCostCalcWayEnum.固定运费).setCustomizeCost(BigDecimal.ZERO);
        }
        //匹配区域计费规则
        CommonStockTemplateVO.UnitVO unitVO = matchedUnitRegion(calcWayEnum, address, stockTemplateDetail.getUnitVOList());
        if (unitVO != null) {
            log.info("地址：{} ，匹配到【{}】的运费计算规则：{}", msgUnit, JsonUtils.toJson(address), JsonUtils.toJson(unitVO));
            CommonDeliveryCostCalcParam.QuantityParam quantityParam = new CommonDeliveryCostCalcParam.QuantityParam()
                    .setFirst(new BigDecimal(ObjectUtils.isNotEmpty(unitVO.getFirstQuantity())?unitVO.getFirstQuantity() + "":0+"")).setFirstPrice(ObjectUtils.isNotEmpty(unitVO.getFirstPrice())?unitVO.getFirstPrice():BigDecimal.ZERO)
                    .setIncrease(new BigDecimal(ObjectUtils.isNotEmpty(unitVO.getIncreaseQuantity())?unitVO.getIncreaseQuantity() + "":0+"")).setIncreasePrice(ObjectUtils.isNotEmpty(unitVO.getIncreasePrice())?unitVO.getIncreasePrice():BigDecimal.ZERO);
            return param.setCalcWay(calcWayEnum).setQuantityParam(quantityParam);
        } else {
            throw new BusinessException(String.format("地址：%s ，未能匹配到【%s】的运费计算规则，规则配置有误", msgUnit, JsonUtils.toJson(address)));
        }
    }

    /**
     * 匹配区域按数量计算规则
     * @param calcWayEnum
     * @param address
     * @param unitVOList
     * @return
     */
    private CommonStockTemplateVO.UnitVO matchedUnitRegion(StockDeliveryCostCalcWayEnum calcWayEnum, StockAddress address, List<CommonStockTemplateVO.UnitVO> unitVOList) {
        //默认免邮规则-全国范围
        CommonStockTemplateVO.UnitVO defaultVO = null;
        //匹配到的免邮规则
        CommonStockTemplateVO.UnitVO matchedVO = null;
        String msgUnit = StockDeliveryCostCalcWayEnum.按件数 == calcWayEnum ? "件数" : "重量";
        Collections.sort(unitVOList, (o1, o2) -> ObjectUtils.isNotEmpty(o1.getRegionVOList()) && ObjectUtils.isEmpty(o1.getRegionVOList()) ? 1 : -1);
        log.info("所有按【{}】的规则：{}", msgUnit, JsonUtils.toJson(unitVOList));
        for (CommonStockTemplateVO.UnitVO vo : unitVOList) {
            if (matchedVO != null) {
                continue;
            }
            if (ObjectUtils.isEmpty(vo.getRegionVOList())) {
                defaultVO = vo;
            }
            //区域排序(市在前、省在后)
            sortRegion(vo.getRegionVOList());
            if (matchedAddress(address, vo.getRegionVOList())) {
                matchedVO = vo;
            }
        }
        return matchedVO != null ? matchedVO : defaultVO;
    }

    /**
     * 计算是否包邮
     * @param sku
     * @param address
     * @param freeVOList
     * @return
     */
    private boolean isFree(StockDeliveryCostCalcWayEnum calcWayEnum, CommonStockTemplateVO.SkuAmountAndPriceVO sku, StockAddress address, List<CommonStockTemplateVO.FreeVO> freeVOList) {
        if (freeVOList == null) {
            log.info("无包邮规则");
            return false;
        }
        BigDecimal unitValue = StockDeliveryCostCalcWayEnum.按件数 == calcWayEnum ? sku.getAmount() : sku.getWeight();
        String msgUnit = StockDeliveryCostCalcWayEnum.按件数 == calcWayEnum ? "件数" : "重量";
        //匹配包邮规则
        CommonStockTemplateVO.FreeVO matchedVO = matchedFreeRegion(address, freeVOList);
        if (matchedVO != null) {
            log.info("地址：{} ，匹配到包邮规则：{}", JsonUtils.toJson(address), JsonUtils.toJson(matchedVO));
            //判断是否能包邮
            if (StockFreeDeliveryCalcWayEnum.按数量.getCode().equals(matchedVO.getMathType())) {
                if (unitValue.compareTo(new BigDecimal(matchedVO.getUnitValue() + "")) <= 0) {
                    log.info("\t按数量：包邮。sku{}:{} <= 规则最大{}:{};", msgUnit, unitValue, msgUnit, matchedVO.getUnitValue());
                    return true;
                } else {
                    log.info("\t按数量：不包邮。sku{}:{} > 规则最大{}:{};", msgUnit, unitValue, msgUnit, matchedVO.getUnitValue());
                    return false;
                }
            }
            if (StockFreeDeliveryCalcWayEnum.按金额.getCode().equals(matchedVO.getMathType())) {
                if (sku.getPrice().compareTo(matchedVO.getPriceValue()) >= 0) {
                    log.info("\t按金额：包邮。sku金额:{} >= 规则最小金额:{};", sku.getPrice(), matchedVO.getPriceValue());
                    return true;
                } else {
                    log.info("\t按金额：不包邮。sku金额:{} >= 规则最小金额:{};", sku.getPrice(), matchedVO.getPriceValue());
                    return false;
                }
            }
            if (StockFreeDeliveryCalcWayEnum.按数量金额组合.getCode().equals(matchedVO.getMathType())) {
                if (unitValue.compareTo(new BigDecimal(matchedVO.getUnitValue() + "")) <= 0 &&
                        sku.getPrice().compareTo(matchedVO.getPriceValue()) >= 0) {
                    log.info("\t按按数量金额组合：包邮。sku{}:{} <= 规则最大{}:{} 【且】 sku金额:{} >= 规则最小金额:{};",
                            msgUnit, unitValue, msgUnit, matchedVO.getUnitValue(), sku.getPrice(), matchedVO.getPriceValue());
                    return true;
                } else {
                    log.info("\t按按数量金额组合：不包邮。sku{}:{} , 规则最大{}:{}, sku金额:{} , 规则最小金额:{};",
                            msgUnit, unitValue, msgUnit, matchedVO.getUnitValue(), sku.getPrice(), matchedVO.getPriceValue());
                    return false;
                }
            }
            throw new BusinessException("免邮规则计算方式错误");
        }
        log.info("地址：{} ，未能匹配到包邮规则", JsonUtils.toJson(address));
        return false;
    }

    /**
     * 匹配区域包邮规则
     * @param address
     * @param freeVOList
     * @return
     */
    private CommonStockTemplateVO.FreeVO matchedFreeRegion(StockAddress address, List<CommonStockTemplateVO.FreeVO> freeVOList) {
        //默认免邮规则-全国范围
        CommonStockTemplateVO.FreeVO defaultVO = null;
        //匹配到的免邮规则
        CommonStockTemplateVO.FreeVO matchedVO = null;
        Collections.sort(freeVOList, (o1, o2) -> ObjectUtils.isNotEmpty(o1.getRegionVOList()) && ObjectUtils.isEmpty(o1.getRegionVOList()) ? 1 : -1);
        log.info("所有【包邮】的规则：{}", JsonUtils.toJson(freeVOList));
        for (CommonStockTemplateVO.FreeVO vo : freeVOList) {
            if (matchedVO != null) {
                continue;
            }
            if (ObjectUtils.isEmpty(vo.getRegionVOList())) {
                defaultVO = vo;
            }
            //区域排序(市在前、省在后)
            sortRegion(vo.getRegionVOList());
            if (matchedAddress(address, vo.getRegionVOList())) {
                matchedVO = vo;
            }
        }
        return matchedVO != null ? matchedVO : defaultVO;
    }

    /**
     * 排序后的区域(市在前、省在后) 与 地址匹配
     * @param address
     * @param regions
     * @return
     */
    private boolean matchedAddress(StockAddress address, List<CommonStockTemplateVO.RegionVO> regions) {
        log.info("地址匹配：省{}-市{}\n 区域规则{}", address.getProvinceCode(), address.getCityCode(), JsonUtils.toJson(regions));
        for(CommonStockTemplateVO.RegionVO vo : regions) {
            if (ObjectUtils.isNotEmpty(vo.getRegionCVO())) {
                for (CommonStockTemplateVO.RegionCVO cvo : vo.getRegionCVO()) {
                    if (cvo.getId().equals(address.getCityCode())) {
                        return true;
                    }
                }
            } else {
                if (vo.getId().equals(address.getProvinceCode())) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 区域排序(市在前、省在后)
     * @param regions
     */
    private void sortRegion(List<CommonStockTemplateVO.RegionVO> regions) {
        Collections.sort(regions, (o1, o2) -> ObjectUtils.isNotEmpty(o1.getRegionCVO()) && ObjectUtils.isEmpty(o1.getRegionCVO()) ? 1 : -1);
    }

    @DubboReference
    private IPCMerchGoodsStockRpc goodsStockRpc;

    @Override
    public Integer usedCount(CommonStockTemplateDTO.IdDTO idDTO) {
        return goodsStockRpc.templateUsedCount(idDTO);
    }

    @Override
    public String queryStockAddress(String id) {
        return goodsStockRpc.queryStockAddress(id);
    }

    @Override
    public CommonStockTemplateVO.IdNameVO innerIdNameVO(String templateId) {
        QueryWrapper<StockTemplate> wrapper = MybatisPlusUtil.query();
        wrapper.eq("id",templateId);
        wrapper.select("id","template_name");
        StockTemplate template = repository.getOne(wrapper);
        if (ObjectUtils.isNotEmpty(template)){
            CommonStockTemplateVO.IdNameVO idNameVO = new CommonStockTemplateVO.IdNameVO();
            BeanUtils.copyProperties(template,idNameVO);
            return idNameVO;
        }
        return null;
    }
}
