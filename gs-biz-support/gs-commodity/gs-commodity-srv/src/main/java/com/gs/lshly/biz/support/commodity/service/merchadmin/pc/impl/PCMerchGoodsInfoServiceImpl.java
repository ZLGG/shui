package com.gs.lshly.biz.support.commodity.service.merchadmin.pc.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.gs.lshly.biz.support.commodity.entity.*;
import com.gs.lshly.biz.support.commodity.mapper.GoodsCategoryMapper;
import com.gs.lshly.biz.support.commodity.mapper.GoodsInfoMapper;
import com.gs.lshly.biz.support.commodity.mapper.view.GoodSkuInfoView;
import com.gs.lshly.biz.support.commodity.repository.*;
import com.gs.lshly.biz.support.commodity.service.merchadmin.pc.*;
import com.gs.lshly.biz.support.commodity.service.platadmin.*;
import com.gs.lshly.common.enums.*;
import com.gs.lshly.common.enums.merchant.ShopStateEnum;
import com.gs.lshly.common.exception.BusinessException;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.common.struct.common.CommonShopVO;
import com.gs.lshly.common.struct.common.CommonStockDTO;
import com.gs.lshly.common.struct.common.CommonStockVO;
import com.gs.lshly.common.struct.common.stock.CommonStockTemplateVO;
import com.gs.lshly.common.struct.merchadmin.pc.commodity.dto.*;
import com.gs.lshly.common.struct.merchadmin.pc.commodity.qto.PCMerchGoodsInfoQTO;
import com.gs.lshly.common.struct.merchadmin.pc.commodity.qto.PCMerchGoodsStockQTO;
import com.gs.lshly.common.struct.merchadmin.pc.commodity.vo.*;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.vo.PCMerchShopVO;
import com.gs.lshly.common.struct.merchadmin.pc.stock.vo.PCMerchStockVO;
import com.gs.lshly.common.struct.platadmin.commodity.dto.GoodsBrandDTO;
import com.gs.lshly.common.struct.platadmin.commodity.dto.GoodsCategoryDTO;
import com.gs.lshly.common.struct.platadmin.commodity.vo.GoodsBrandVO;
import com.gs.lshly.common.struct.platadmin.commodity.vo.GoodsCategoryVO;
import com.gs.lshly.common.struct.platadmin.foundation.vo.SettingsVO;
import com.gs.lshly.common.utils.GoodsNoUtil;
import com.gs.lshly.common.utils.ListUtil;
import com.gs.lshly.middleware.mybatisplus.MybatisPlusUtil;
import com.gs.lshly.rpc.api.common.ICommonShopRpc;
import com.gs.lshly.rpc.api.common.ICommonStockRpc;
import com.gs.lshly.rpc.api.merchadmin.pc.merchant.IPCMerchShopRpc;
import com.gs.lshly.rpc.api.merchadmin.pc.stock.IPCMerchStockRpc;
import com.gs.lshly.rpc.api.merchadmin.pc.stock.IPCMerchStockTemplateRpc;
import com.gs.lshly.rpc.api.merchadmin.pc.trade.IPCMerchTradeRpc;
import com.gs.lshly.rpc.api.platadmin.foundation.ISettingsRpc;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.collections4.ListUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

/**
* <p>
*  服务实现类
* </p>
* @author Starry
* @since 2020-10-08
*/
@Component
public class PCMerchGoodsInfoServiceImpl implements IPCMerchGoodsInfoService {

    @Autowired
    private IGoodsInfoRepository repository;
    @Autowired
    private ISkuGoodInfoRepository skuGoodInfoRepository;
    @Autowired
    private IGoodsAttributeInfoService attributeInfoService;
    @Autowired
    private IGoodsExtendParamsService extendParamsService;
    @Autowired
    private IGoodsSpecInfoService goodsSpecInfoService;
    @Autowired
    private GoodsInfoMapper goodsInfoMapper;
    @Autowired
    private IPCMerchSkuGoodInfoService skuGoodInfoService;
    @Autowired
    private IGoodsTempalteRepository goodsTempalteRepository;
    @Autowired
    private IGoodsShopNavigationRepository navigationRepository;
    @Autowired
    private IGoodsAttributeInfoRepository attributeInfoRepository;
    @Autowired
    private IGoodsSpecInfoRepository goodsSpecInfoRepository;
    @Autowired
    private IGoodsExtendParamsRepository extendParamsRepository;
    @Autowired
    private IGoodsShopNavigationRepository shopNavigationRepository;
    @Autowired
    private IGoodsCategoryService categoryService;
    @Autowired
    private IGoodsCategoryRepository categoryRepository;
    @Autowired
    private IGoodsBrandService brandService;
    @Autowired
    private IPCMerchGoodsStockService alarmService;
    @Autowired
    private IPCMerchGoodsCategoryService merchGoodsCategoryService;
    @Autowired
    private IPCMerchGoodsBrandService merchGoodsBrandService;
    @Autowired
    private GoodsCategoryMapper categoryMapper;


    @DubboReference
    private IPCMerchShopRpc shopRpc;

    @DubboReference
    private IPCMerchStockRpc stockRpc;

    @DubboReference
    private IPCMerchStockTemplateRpc stockTemplateRpc;

    @DubboReference
    private IPCMerchTradeRpc tradeRpc;

    @DubboReference
    private ICommonShopRpc commonShopRpc;

    @DubboReference
    private ICommonStockRpc commonStockRpc;

    @DubboReference
    private ISettingsRpc settingsRpc;

    @Override
    public PageData<PCMerchGoodsInfoVO.SpuListVO> pageGoodsData(PCMerchGoodsInfoQTO.GoodsInfoParamsQTO qto) {
        QueryWrapper<PCMerchGoodsInfoVO.SpuListVO> wrapperBoost = MybatisPlusUtil.query();
        wrapperBoost.eq("gs.shop_id",qto.getJwtShopId());
        wrapperBoost.eq("gs.merchant_id",qto.getJwtMerchantId());
        if (StringUtils.isNotEmpty(qto.getTemplateId())){
            wrapperBoost.eq("gt.template_id",qto.getTemplateId());
        }
        if (ObjectUtils.isNotEmpty(qto.getUsePlatform())){
            wrapperBoost.eq("gs.use_platform",qto.getUsePlatform());
        }
        if (ObjectUtils.isNotEmpty(qto.getSalePrice1())){
            wrapperBoost.gt("gs.sale_price",qto.getSalePrice1());
        }
        if (ObjectUtils.isNotEmpty(qto.getSalePrice2())){
            wrapperBoost.lt("gs.sale_price",qto.getSalePrice2());
        }
        if (StringUtils.isNotEmpty(qto.getGoodsNo())){
            wrapperBoost.likeRight("gs.goods_no",qto.getGoodsNo());
        }
        if (StringUtils.isNotEmpty(qto.getGoodsName())){
            wrapperBoost.likeRight("gs.goods_name",qto.getGoodsName());
        }
        if (ObjectUtils.isNotEmpty(qto.getGoodsState()) && qto.getGoodsState().intValue() !=-1){
            wrapperBoost.eq("gs.goods_state",qto.getGoodsState());
        }
        if(StringUtils.isNotBlank(qto.getShopNavigation())){
            wrapperBoost.eq("gsn.shop_navigation",getShopNavigation(qto.getShopNavigation()).getNavigationTowId());
        }
        if(qto.getIsPointGood()!=null){
            wrapperBoost.eq("gs.is_point_good",qto.getIsPointGood());
        }
        if(qto.getIsInMemberGift()!=null){
            wrapperBoost.eq("gs.is_in_member_gift",qto.getIsInMemberGift());
        }
        if (ObjectUtils.isNotEmpty(qto.getSaleType()) && qto.getSaleType().intValue() !=-1){
            wrapperBoost.eq("gs.sale_type",qto.getSaleType());
        }

        IPage<PCMerchGoodsInfoVO.SpuListVO> page = MybatisPlusUtil.pager(qto);
        IPage<PCMerchGoodsInfoVO.SpuListVO> spuListVOIPage = goodsInfoMapper.getMerchantGoodsInfo(page,wrapperBoost);
        if (ObjectUtils.isEmpty(spuListVOIPage)){
            return new PageData<>();
        }
        for (PCMerchGoodsInfoVO.SpuListVO spuListVO : spuListVOIPage.getRecords()){
            //判断是否已经设置运费模板
            spuListVO.setHasTemplate(getHasTemplate(spuListVO.getId()));
            //填充商品库存数量
            spuListVO.setStockNum(getSpuStockNum(spuListVO.getId(),qto.getJwtShopId()));
            spuListVO.setGoodsImage(ObjectUtils.isEmpty(getImage(spuListVO.getGoodsImage()))?"":getImage(spuListVO.getGoodsImage()));

        }
        return MybatisPlusUtil.toPageData(qto, PCMerchGoodsInfoVO.SpuListVO.class,spuListVOIPage);
    }

    @Override
    public PageData<PCMerchGoodsInfoVO.StockAlarmGoodsVO> pageStockAlarmGoods(PCMerchGoodsInfoQTO.GoodsInfoParamsQTO qto) {
       //声明一个储存容器
        List<PCMerchGoodsInfoVO.StockAlarmGoodsVO> list = new ArrayList();

        //获取这个店铺的库存报警数
        PCMerchGoodsStockQTO.SkuAlarmQTO alarmQto = new PCMerchGoodsStockQTO.SkuAlarmQTO();
        BeanUtils.copyProperties(qto,alarmQto);
        alarmQto.setShopId(alarmQto.getJwtShopId());
        PCMerchGoodsStockVO.SkuAlarmDetailVO detailVO = alarmService.detailSkuStockAlarm(alarmQto);
        if (ObjectUtils.isEmpty(detailVO)){
            throw new BusinessException("该店铺还没有设置库存报警数，请先去设置！");
        }
        //获取库存报警的商品id列表
        List<PCMerchStockVO.InnerRoliceVO>  innerRoliceVOS = stockRpc.innerStockRolice(qto,detailVO.getStockNum(),qto.getJwtShopId());
        if (ObjectUtils.isEmpty(innerRoliceVOS)){
            return new PageData<>(list,qto.getPageNum(),qto.getPageSize(),list.size());
        }
        List<String> idList =innerRoliceVOS
                .stream().map(PCMerchStockVO.InnerRoliceVO::getGoodsId).distinct().collect(toList());
        //获取库存报警的商品信息
        QueryWrapper<GoodsInfo> boost = MybatisPlusUtil.query();
        boost.in("id",idList);
        IPage<GoodsInfo> page = MybatisPlusUtil.pager(qto);
        IPage<GoodsInfo> infoIPage = repository.page(page,boost);
        List<GoodsInfo> goodsInfos = infoIPage.getRecords();
        if (ObjectUtils.isEmpty(goodsInfos)){
            return new PageData<>();
        }

        for (GoodsInfo goodsInfo : goodsInfos){
            PCMerchGoodsInfoVO.StockAlarmGoodsVO stockAlarmGoodsVO = new PCMerchGoodsInfoVO.StockAlarmGoodsVO();
            BeanUtils.copyProperties(goodsInfo,stockAlarmGoodsVO);
            //判断是否已经设置运费模板
            stockAlarmGoodsVO.setHasTemplate(getHasTemplate(goodsInfo.getId()));
            //填充商品库存数量
            stockAlarmGoodsVO.setStockNum(getSpuStockNum(goodsInfo.getId(),qto.getJwtShopId()));

            stockAlarmGoodsVO.setGoodsImage(ObjectUtils.isEmpty(getImage(goodsInfo.getGoodsImage()))?"":getImage(goodsInfo.getGoodsImage()));
            list.add(stockAlarmGoodsVO);
        }
        return new PageData<>(list,qto.getPageNum(),qto.getPageSize(),infoIPage.getTotal());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public PCMerchGoodsInfoVO.GoodsIdVO addGoodsInfo(PCMerchGoodsInfoDTO.AddGoodsETO eto) {
        //数据校验
        checkAddGoodsData(eto);
        //判断编号的唯一性
        if (StringUtils.isNotEmpty(eto.getGoodsNo())){
            PCMerchGoodsInfoDTO.GoodNoDTO dto = new PCMerchGoodsInfoDTO.GoodNoDTO();
            dto.setGoodsNo(eto.getGoodsNo());
            if (this.countGoodsNo(dto)>0){
                throw new BusinessException("商品货号"+eto.getGoodsNo()+"已存在");
            }
        }
        GoodsInfo goodsInfo = new GoodsInfo();
        BeanUtils.copyProperties(eto,goodsInfo);
        goodsInfo.setGoodsNo(StringUtils.isEmpty(eto.getGoodsNo())? GoodsNoUtil.getGoodsNo():eto.getGoodsNo());
        goodsInfo.setIsShowOldPrice(ObjectUtils.isEmpty(goodsInfo.getIsShowOldPrice())? ShowOldPriceEnum.不显示原价.getCode():goodsInfo.getIsShowOldPrice());
        goodsInfo.setShopId(StringUtils.isBlank(eto.getShopId())?eto.getJwtShopId():eto.getShopId());
        goodsInfo.setMerchantId(StringUtils.isBlank(eto.getMerchantId())?eto.getJwtMerchantId():eto.getMerchantId());
        goodsInfo.setGoodsPriceUnit(GoodsPriceUnitEnum.千克.getRemark());
        //添加商品 开始销售数量为0
        goodsInfo.setSaleQuantity(0);
        boolean flag = repository.save(goodsInfo);
        if (!flag){
            throw new BusinessException("添加商品失败");
        }

        //声明拓展id容器
        StringBuffer attributeBuffer = new StringBuffer();
        StringBuffer paramsBuffer = new StringBuffer();
        StringBuffer specBuffer = new StringBuffer();

        //如果商品属性不为空则向拓展属性表中添加数据
        if (ObjectUtils.isNotEmpty(eto.getAttributeList())){
            for (PCMerchGoodsAttributeInfoDTO.ETO attributeInfo: eto.getAttributeList()) {
                attributeInfo.setGoodId(goodsInfo.getId());
                attributeInfo.setId("");
                String attributeId = attributeInfoService.addGoodsAttributeInfo(attributeInfo);

                //获取属性拓展id组
                attributeBuffer.append(attributeId+",");
            }
        }
        //如果商品参数不为空则向拓展参数表中添加数据

        if (ObjectUtils.isNotEmpty(eto.getParamsList())){
            for (PCMerchGoodsExtendParamsDTO.ETO paramsInfo: eto.getParamsList()){
                paramsInfo.setGoodId(goodsInfo.getId());
                paramsInfo.setId("");
                String paramsId = extendParamsService.addGoodsExtendParams(paramsInfo);

                //获取参数拓展id组
                paramsBuffer.append(paramsId+",");
            }
        }
        //商品关联拓展id
        JoinGoodsExtendIds(attributeBuffer,specBuffer,paramsBuffer,goodsInfo.getId());
        /*
         * 如果商品为多规格商品
         * 1.向拓展规格表中添加数据
         * 2.向sku商品库中添加数据
         */

        //建立商品以及sku与库存的关联
        List<CommonStockDTO.InnerChangeStockItem> items = new ArrayList<>();
        if (ObjectUtils.isNotEmpty( eto.getSpecList())) {
            for (PCMerchGoodsSpecInfoDTO.ETO specInfo : eto.getSpecList()) {
                specInfo.setGoodId(goodsInfo.getId());
                specInfo.setId("");
                String specId = goodsSpecInfoService.addGoodsSpecInfo(specInfo);

                //获取规格拓展id组
                specBuffer.append(specId + ",");

            }
            List<SkuGoodInfo> skuGoodInfos = new ArrayList<>();
            for (PCMerchSkuGoodInfoDTO.AddETO skuInfo : eto.getEtoList()) {
                SkuGoodInfo skuGoodInfo = new SkuGoodInfo();
                BeanUtils.copyProperties(skuInfo, skuGoodInfo);
                skuGoodInfo.setGoodId(goodsInfo.getId());
                skuGoodInfo.setSkuGoodsNo(StringUtils.isBlank(skuInfo.getSkuGoodsNo())?GoodsNoUtil.getGoodsNo():skuInfo.getSkuGoodsNo());
                skuGoodInfo.setState(eto.getGoodsState());
                skuGoodInfo.setShopId(StringUtils.isBlank(eto.getShopId())?eto.getJwtShopId():eto.getShopId());
                skuGoodInfo.setMerchantId(StringUtils.isBlank(eto.getMerchantId())?eto.getJwtMerchantId():eto.getMerchantId());
                skuGoodInfo.setId("");
                skuGoodInfos.add(skuGoodInfo);

                //添加sku商品信息
                skuGoodInfoRepository.save(skuGoodInfo);

                CommonStockDTO.InnerChangeStockItem stockItem = new CommonStockDTO.InnerChangeStockItem();
                stockItem.setGoodsId(goodsInfo.getId());
                stockItem.setSkuId(skuGoodInfo.getId());
                stockItem.setQuantity(skuInfo.getSkuStock()!=null?skuInfo.getSkuStock():0);
                items.add(stockItem);
            }

            //商品关联拓展id
            JoinGoodsExtendIds(attributeBuffer,specBuffer,paramsBuffer,goodsInfo.getId());


            //sku商品关联spec拓展id组
            JoinSkuSpecIds(specBuffer,goodsInfo.getId());

        }else {
            //商品关联拓展id
            JoinGoodsExtendIds(attributeBuffer,specBuffer,paramsBuffer,goodsInfo.getId());

            SkuGoodInfo skuGoodInfo = new SkuGoodInfo();
            skuGoodInfo.setGoodId(goodsInfo.getId());
            skuGoodInfo.setId("");
            skuGoodInfoRepository.save(skuGoodInfo);

            CommonStockDTO.InnerChangeStockItem stockItem = new CommonStockDTO.InnerChangeStockItem();
            stockItem.setGoodsId(goodsInfo.getId());
            stockItem.setSkuId(skuGoodInfo.getId());
            stockItem.setQuantity(eto.getSpuStock()!=null?eto.getSpuStock():0);
            items.add(stockItem);
        }
        //建立商品与运费模板的关联关系
        GoodsTempalte template = new GoodsTempalte();
        template.setGoodsId(goodsInfo.getId());
        template.setTemplateId(eto.getTemplateId());
        template.setStockSubtractType(eto.getStockSubtractType());
        goodsTempalteRepository.save(template);

        //建立商品与店铺自定义类目的关联关系
        if (StringUtils.isNotBlank(eto.getShopNavigationId())){
            createGoodsShopNaigationBind(eto.getJwtMerchantId(),eto.getJwtShopId(),goodsInfo.getId(),eto.getShopNavigationId(),TerminalEnum.BBB.getCode());
        }
        if (StringUtils.isNotBlank(eto.getShop2cNavigationId())){
            createGoodsShopNaigationBind(eto.getJwtMerchantId(),eto.getJwtShopId(),goodsInfo.getId(),eto.getShop2cNavigationId(),TerminalEnum.BBC.getCode());
        }


        initGoodsStock(eto, items);

        PCMerchGoodsInfoVO.GoodsIdVO goodsIdVO = new PCMerchGoodsInfoVO.GoodsIdVO();
        goodsIdVO.setGoodsId(goodsInfo.getId());
        return goodsIdVO;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteGoodsInfo(PCMerchGoodsInfoDTO.IdDTO dto) {
        if (ObjectUtils.isEmpty(dto)){
            throw new BusinessException("参数不能为空！！");
        }
        //TODO 如果商品是处于交易中并且订单的状态不是已完成那么不可以删除该商品
//        PCMerchTradeDTO.GoodsIdDTO idDTO = new PCMerchTradeDTO.GoodsIdDTO(dto.getId());
//        PCMerchTradeListVO.innerGoodsIdAndName listVo = tradeRpc.innergoodsIdCheck(idDTO);
//        if (ObjectUtils.isNotEmpty(listVo)){
//            throw new BusinessException("["+listVo.getGoodsName()+"]"+"正处于订单交易中不可删除！！");
//        }
        /*
         * 清除掉对应的商品信息以及与商品关联的信息
         */
        clearGoodsJoin(dto.getId());
    }
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void editGoodsInfo(PCMerchGoodsInfoDTO.AddGoodsETO eto) {

        //数据校验
        checkAddGoodsData(eto);

        //获取原来发布商品的传参对象
        PCMerchGoodsInfoVO.EditDetailVO editDetailVO = getEditDetailEto(new PCMerchGoodsInfoDTO.IdDTO(eto.getId()));

        eto.setGoodsState(GoodsStateEnum.未上架.getCode());

        UpdateWrapper<GoodsInfo> goodsBoost = MybatisPlusUtil.update();
        goodsBoost.eq("id",eto.getId());

        GoodsInfo goodsInfo = new GoodsInfo();
        BeanUtils.copyProperties(eto,goodsInfo);
        goodsInfo.setGoodsNo(StringUtils.isEmpty(eto.getGoodsNo())? GoodsNoUtil.getGoodsNo():eto.getGoodsNo());
        goodsInfo.setIsShowOldPrice(goodsInfo.getIsShowOldPrice().intValue() ==  0? ShowOldPriceEnum.不显示原价.getCode():goodsInfo.getIsShowOldPrice());
        goodsInfo.setShopId(eto.getJwtShopId());
        goodsInfo.setMerchantId(eto.getJwtMerchantId());
        goodsInfo.setGoodsPriceUnit(GoodsPriceUnitEnum.千克.getRemark());
        boolean flag = repository.update(goodsInfo,goodsBoost);

        if (!flag){
            throw new BusinessException("修改商品失败");
        }

        //声明拓展id容器
        StringBuffer attributeBuffer = new StringBuffer();
        StringBuffer paramsBuffer = new StringBuffer();
        StringBuffer specBuffer = new StringBuffer();

        deleteSurplusAttribute(editDetailVO.getAttributeList(),eto.getAttributeList());

        //如果商品属性不为空则修改拓展属性表中数据
        if (ObjectUtils.isNotEmpty(eto.getAttributeList())){

            for (PCMerchGoodsAttributeInfoDTO.ETO attributeInfo: eto.getAttributeList()) {

                attributeInfo.setGoodId(goodsInfo.getId());
                GoodsAttributeInfo info = new GoodsAttributeInfo();
                BeanUtils.copyProperties(attributeInfo,info);

                if (StringUtils.isNotEmpty(info.getId())){
                    UpdateWrapper<GoodsAttributeInfo> attributeBoost = MybatisPlusUtil.update();
                    attributeBoost.eq("id",info.getId());
                    attributeInfoRepository.update(info,attributeBoost);
                }else {
                    attributeInfoRepository.save(info);
                }

                String attributeId = info.getId();

                //获取属性拓展id组
                attributeBuffer.append(attributeId+",");
            }
        }
        //如果商品参数不为空则向拓展参数表中添加数据

        if (ObjectUtils.isNotEmpty(eto.getParamsList())){
            for (PCMerchGoodsExtendParamsDTO.ETO paramsInfo: eto.getParamsList()){
                paramsInfo.setGoodId(goodsInfo.getId());

                GoodsExtendParams info = new GoodsExtendParams();
                BeanUtils.copyProperties(paramsInfo,info);

                if (StringUtils.isNotEmpty(info.getId())){
                    UpdateWrapper<GoodsExtendParams> paramsBoost = MybatisPlusUtil.update();
                    paramsBoost.eq("id",info.getId());
                    extendParamsRepository.update(info,paramsBoost);
                }else {
                    extendParamsRepository.save(info);
                }

                String paramsId = info.getId();

                //获取参数拓展id组
                paramsBuffer.append(paramsId+",");
            }
        }
        //商品关联拓展id
        JoinGoodsExtendIds(attributeBuffer,specBuffer,paramsBuffer,goodsInfo.getId());
        /*
         * 如果商品为多规格商品
         * 1.向拓展规格表中修改数据
         * 2.向sku商品库中修改数据
         */

        //建立商品以及sku与库存的关联
        List<CommonStockDTO.InnerChangeStockItem> items = new ArrayList<>();
        if (ObjectUtils.isNotEmpty( eto.getSpecList())) {


            //获取传参规格值名称list
            List<String> list11 = editDetailVO.getSkuVoList()
                    .stream()
                    .map(PCMerchSkuGoodInfoVO.DetailVO::getSpecsValue).collect(toList());

            //获取修改后的规格值名称List
            List<String> list22 = eto.getEtoList()
                    .stream()
                    .map(PCMerchSkuGoodInfoDTO.AddETO::getSpecsValue).collect(toList());


            //获取传参规格名称list
            List<String> list1 = editDetailVO.getSpecList()
                    .stream()
                    .map(PCMerchGoodsSpecInfoVO.ListVO::getSpecName).collect(toList());

            //获取修改后的规格名称List
            List<String> list2 = eto.getSpecList()
                    .stream()
                    .map(PCMerchGoodsSpecInfoDTO.ETO::getSpecName).collect(toList());

            if (ObjectUtils.isEmpty(getJiaoJi(list1,list2))){

                //原来的规格已经修改需要删掉规格数据重新添加
                QueryWrapper<GoodsSpecInfo> boost = MybatisPlusUtil.query();
                boost.eq("good_id",goodsInfo.getId());
                goodsSpecInfoRepository.remove(boost);

            }else {
                List<PCMerchGoodsSpecInfoVO.ListVO> containsObj = editDetailVO.getSpecList()
                        .stream().filter(it -> getJiaoJi(list1,list2).contains(it.getSpecName()))
                        .collect(toList());

                for (PCMerchGoodsSpecInfoVO.ListVO listVO : containsObj){
                    for (PCMerchGoodsSpecInfoDTO.ETO specInfo : eto.getSpecList()) {
                        if (listVO.getSpecName().equals(specInfo.getSpecName())){
                            specInfo.setId(listVO.getId());
                        }
                    }
                }
            }
            for (PCMerchGoodsSpecInfoDTO.ETO specInfo : eto.getSpecList()) {
                specInfo.setGoodId(goodsInfo.getId());
                GoodsSpecInfo info = new GoodsSpecInfo();
                BeanUtils.copyProperties(specInfo,info);

                if (StringUtils.isNotEmpty(info.getId())){
                    UpdateWrapper<GoodsSpecInfo> specsBoost = MybatisPlusUtil.update();
                    specsBoost.eq("id",info.getId());
                    goodsSpecInfoRepository.update(info,specsBoost);
                }else {
                    goodsSpecInfoRepository.save(info);
                }

                String specId = info.getId();

                //获取规格拓展id组
                specBuffer.append(specId + ",");

            }
            if (eto.getEtoList() == null && eto.getEtoList().size() == 0) {
                throw new BusinessException("多规格商品规格属性不能为空！");
            }
            if (ObjectUtils.isEmpty(getJiaoJi(list11,list22))){
                //原来的规格已经修改需要删掉sku商品重新添加
                QueryWrapper<SkuGoodInfo> queryWrapperBoost = MybatisPlusUtil.query();
                queryWrapperBoost.eq("good_id",goodsInfo.getId());
                skuGoodInfoRepository.remove(queryWrapperBoost);
            }else{

                List<PCMerchSkuGoodInfoVO.DetailVO> containsObj2 = editDetailVO.getSkuVoList()
                        .stream().filter(it -> getJiaoJi(list11,list22).contains(it.getSpecsValue()))
                        .collect(toList());

                for (PCMerchSkuGoodInfoVO.DetailVO detailVO : containsObj2){
                    for (PCMerchSkuGoodInfoDTO.AddETO eto1 : eto.getEtoList()) {
                        if (detailVO.getSpecsValue().equals(eto1.getSpecsValue())){
                            eto1.setId(detailVO.getId());
                        }
                    }
                }
            }
            List<SkuGoodInfo> skuGoodInfos = new ArrayList<>();
            for (PCMerchSkuGoodInfoDTO.AddETO skuInfo : eto.getEtoList()) {

                SkuGoodInfo skuGoodInfo = new SkuGoodInfo();
                BeanUtils.copyProperties(skuInfo, skuGoodInfo);
                skuGoodInfo.setGoodId(goodsInfo.getId());
                skuGoodInfo.setState(eto.getGoodsState());
                skuGoodInfo.setMerchantId(goodsInfo.getMerchantId());
                skuGoodInfo.setShopId(goodsInfo.getShopId());
                skuGoodInfos.add(skuGoodInfo);

                //添加sku商品信息
                skuGoodInfoRepository.saveOrUpdate(skuGoodInfo);

                CommonStockDTO.InnerChangeStockItem stockItem = new CommonStockDTO.InnerChangeStockItem();
                stockItem.setGoodsId(goodsInfo.getId());
                stockItem.setSkuId(skuGoodInfo.getId());
                stockItem.setQuantity(skuInfo.getSkuStock()!=null?skuInfo.getSkuStock():0);
                items.add(stockItem);
            }
            //商品关联拓展id
            JoinGoodsExtendIds(attributeBuffer,specBuffer,paramsBuffer,goodsInfo.getId());


            //sku商品关联spec拓展id组
            JoinSkuSpecIds(specBuffer,goodsInfo.getId());

        }else {
            QueryWrapper<SkuGoodInfo> boost = MybatisPlusUtil.query();
            boost.eq("good_id",goodsInfo.getId());
            SkuGoodInfo skuGoodInfo = skuGoodInfoRepository.getOne(boost);
            CommonStockDTO.InnerChangeStockItem stockItem = new CommonStockDTO.InnerChangeStockItem();
            stockItem.setGoodsId(goodsInfo.getId());
            stockItem.setSkuId(skuGoodInfo.getId());
            stockItem.setQuantity(eto.getSpuStock()!=null?eto.getSpuStock():0);
            items.add(stockItem);
        }
        //修改商品与运费模板的关联关系
        UpdateWrapper<GoodsTempalte> templateBoost = MybatisPlusUtil.update();
        templateBoost.eq("goods_id",goodsInfo.getId());
        GoodsTempalte template = new GoodsTempalte();
        template.setTemplateId(eto.getTemplateId());
        template.setStockSubtractType(eto.getStockSubtractType());
        goodsTempalteRepository.update(template,templateBoost);

        //建立商品与店铺自定义类目的关联关系

        if (StringUtils.isNotBlank(eto.getShopNavigationId())){
            updateShopNavigationBind(eto.getJwtShopId(),eto.getJwtMerchantId(),goodsInfo.getId(),TerminalEnum.BBB.getCode(),eto.getShopNavigationId());
        }
        if (StringUtils.isNotBlank(eto.getShop2cNavigationId())){
            updateShopNavigationBind(eto.getJwtShopId(),eto.getJwtMerchantId(),goodsInfo.getId(),TerminalEnum.BBC.getCode(),eto.getShop2cNavigationId());
        }


        // 建立商品以及sku与库存的关联
        initGoodsStock(eto, items);

    }

    @Override
    public PCMerchGoodsInfoVO.DetailVO detailGoodsInfo(PCMerchGoodsInfoDTO.EditIdsDTO dto) {
        if (dto == null){
            throw new BusinessException("参数为空");
        }
        //查询spu商品信息
        QueryWrapper<GoodsInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id",dto.getId());
        queryWrapper.eq("shop_id",dto.getJwtShopId());
        GoodsInfo goodsInfo = repository.getOne(queryWrapper);

        if (goodsInfo == null){
            throw new BusinessException("查询异常！");
        }

        PCMerchGoodsInfoVO.DetailVO spuDetail = new PCMerchGoodsInfoVO.DetailVO();
        BeanUtils.copyProperties(goodsInfo,spuDetail);

        //查询spu下的sku信息
        QueryWrapper<SkuGoodInfo> wrapper = new QueryWrapper<>();
        wrapper.eq("good_id",dto.getId());
        wrapper.eq("shop_id",dto.getJwtShopId());
        List<SkuGoodInfo> skuGoodInfos = skuGoodInfoRepository.list(wrapper);

        List<PCMerchSkuGoodInfoVO.DetailVO> skuDetailList = new ArrayList<>();
        //获取sku的库存数
        if (skuGoodInfos != null && skuGoodInfos.size() > 0){
            for (SkuGoodInfo info:skuGoodInfos) {
                PCMerchSkuGoodInfoVO.DetailVO detailVO = new PCMerchSkuGoodInfoVO.DetailVO();
                BeanUtils.copyProperties(info,detailVO);
                // 填充sku库存数
                detailVO.setSkuStock(getSkuStockNum(dto.getJwtShopId(),info.getId()));

                skuDetailList.add(detailVO);
            }
        }
        //填充spu库存数
        spuDetail.setSpuStockNum(getSpuStockNum(goodsInfo.getId(),dto.getJwtShopId()));


        spuDetail.setDetailVOList(skuDetailList);

        //填充运费模板id
        QueryWrapper<GoodsTempalte> tempalteQueryWrapper = new QueryWrapper<>();
        tempalteQueryWrapper.eq("goods_id",dto.getId());
        GoodsTempalte tempalte = goodsTempalteRepository.getOne(tempalteQueryWrapper);
        spuDetail.setTemplateId(tempalte.getTemplateId());

        //填充店铺类目id
        QueryWrapper<GoodsShopNavigation> navigationBoost = MybatisPlusUtil.query();
        navigationBoost.eq("goods_id",goodsInfo.getId());
        List<GoodsShopNavigation> navigations = shopNavigationRepository.list(navigationBoost);
        if (ObjectUtils.isNotEmpty(navigations)){
            for (GoodsShopNavigation shopNavigation : navigations){
                if (ObjectUtils.isNotEmpty(shopNavigation.getTerminal()) && shopNavigation.getTerminal().equals(TerminalEnum.BBB.getCode())){
                    spuDetail.setShopCategoryId(shopNavigation.getShopNavigation());
                }
                if (ObjectUtils.isNotEmpty(shopNavigation.getTerminal()) && shopNavigation.getTerminal().equals(TerminalEnum.BBC.getCode())){
                    spuDetail.setShop2cCategoryId(shopNavigation.getShopNavigation());
                }
            }
        }
        return spuDetail;
    }

    @Override
    public void groundingGoods(PCMerchGoodsInfoDTO.IdListDTO dto) {
        Integer shopState = shopRpc.innerShopState(dto.getJwtShopId());
        if (ObjectUtils.isEmpty(shopState) || shopState.equals(ShopStateEnum.关闭状态.getCode())){
            throw new BusinessException("该店铺已经关闭,若要上架商品请先开通店铺！");
        }
        if (whetherOpenGoodsUpAudit()){
            upOrOnGoods(dto,GoodsStateEnum.待审核.getCode());
        }else if (!whetherOpenGoodsUpAudit()){
            if (ObjectUtils.isNotEmpty(dto.getFuPinGoodsIdList())){
                upOrOnGoods(dto,GoodsStateEnum.待审核.getCode());
            }else {
                upOrOnGoods(dto,GoodsStateEnum.已上架.getCode());
            }
        }
    }

    @Override
    public void underCarriageGoods(PCMerchGoodsInfoDTO.IdListDTO dto) {
        upOrOnGoods(dto,GoodsStateEnum.未上架.getCode());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteGoodsBatches(PCMerchGoodsInfoDTO.IdListDTO dto) {
        if (ObjectUtils.isEmpty(dto)){
            throw new BusinessException("参数不能为空！");
        }
        for (String goodsId:dto.getIdList()) {
            /*
             * 清除掉对应的商品信息以及与商品关联的信息
             */
            PCMerchGoodsInfoDTO.IdDTO idDTO = new PCMerchGoodsInfoDTO.IdDTO(goodsId);
            deleteGoodsInfo(idDTO);
        }
    }

    @Override
    public List<PCMerchGoodsInfoVO.ExcelGoodsDataVO> exportGoodsData(PCMerchGoodsInfoQTO.IdListQTO qto) {
        if (qto == null || qto.getIdList() == null || qto.getIdList().size() == 0){
            throw new BusinessException("参数不能为空！");
        }
        List<PCMerchGoodsInfoVO.ExcelGoodsDataVO> excelGoodsDataVOS = new ArrayList<>();

        List<GoodsInfo> goodsInfos = repository.listByIds(qto.getIdList());
        if (goodsInfos == null || goodsInfos.size() == 0){
            throw new BusinessException("查询异常！");
        }

        for (GoodsInfo goodsInfo : goodsInfos){
            PCMerchGoodsInfoVO.ExcelGoodsDataVO excelGoodsDataVO = new PCMerchGoodsInfoVO.ExcelGoodsDataVO();
            BeanUtils.copyProperties(goodsInfo,excelGoodsDataVO);

            if (StringUtils.isEmpty(goodsInfo.getCategoryId())){
                throw new BusinessException("数据异常！");
            }
            GoodsCategoryVO.ParentCategoryVO parentCategoryVO = categoryService.findParentCategoryVO(new GoodsCategoryDTO.IdDTO(goodsInfo.getCategoryId()));
            if(parentCategoryVO == null){
                throw new BusinessException("数据异常！");
            }

            //获取商品的类目名称
            excelGoodsDataVO.setCategoryLevel1Name(parentCategoryVO.getLev2Name());
            excelGoodsDataVO.setCategoryLevel2Name(parentCategoryVO.getLev1Name());
            excelGoodsDataVO.setCategoryLevel3Name(parentCategoryVO.getLevName());

            //获取品牌名称
            if (StringUtils.isEmpty(goodsInfo.getBrandId())){
                throw new BusinessException("数据异常！");
            }
            GoodsBrandVO.DetailVO brand = brandService.select(new GoodsBrandDTO.IdDTO(goodsInfo.getBrandId()));
            if (brand == null){
                throw new BusinessException("数据异常");
            }
            excelGoodsDataVO.setBrandName(brand.getBrandName());

            //获取发布平台
            if (goodsInfo.getUsePlatform().intValue() == GoodsUsePlatformEnums.B商城和C商城.getCode().intValue()){
                excelGoodsDataVO.setPublishPlatform("全部");
            }
            if (goodsInfo.getUsePlatform().intValue() == GoodsUsePlatformEnums.B商城.getCode().intValue()){
                excelGoodsDataVO.setPublishPlatform(GoodsUsePlatformEnums.B商城.getRemark());
            }
            if (goodsInfo.getUsePlatform().intValue() == GoodsUsePlatformEnums.C商城.getCode().intValue()){
                excelGoodsDataVO.setPublishPlatform(GoodsUsePlatformEnums.C商城.getRemark());
            }

            //获取是否显示原价
            if (goodsInfo.getIsShowOldPrice().intValue() == ShowOldPriceEnum.显示原价.getCode().intValue()){
                excelGoodsDataVO.setShowOrNoOldPrice(ShowOldPriceEnum.显示原价.getRemark());
            }
            if (goodsInfo.getIsShowOldPrice().intValue() == ShowOldPriceEnum.不显示原价.getCode().intValue()){
                excelGoodsDataVO.setShowOrNoOldPrice(ShowOldPriceEnum.不显示原价.getRemark());
            }

            //TODO 获取店铺分类的数据 excelGoodsDataVO.setShopNavigation();

            // 获取商品库存数
             excelGoodsDataVO.setStockNum(getSpuStockNum(goodsInfo.getId(),qto.getJwtShopId()));

            //TODO 获取库存计数方式 excelGoodsDataVO.setStockChargeWay();

            //获取计价单位
            excelGoodsDataVO.setChargeUnit(goodsInfo.getGoodsPriceUnit());

            //TODO 获取运费模板名称 excelGoodsDataVO.setTemplateName();

            //根据商品id对应的sku
            QueryWrapper<SkuGoodInfo> skuWrapper = new QueryWrapper<>();
            skuWrapper.eq("good_id",goodsInfo.getId());
            List<SkuGoodInfo> skuGoodInfos = skuGoodInfoRepository.list(skuWrapper);
            if (skuGoodInfos != null && skuGoodInfos.size()>0){
                for (SkuGoodInfo skuGoodInfo : skuGoodInfos) {
                    excelGoodsDataVO.setGoodsNo(skuGoodInfo.getSkuGoodsNo());
                    excelGoodsDataVO.setGoodsBarcode(skuGoodInfo.getBarcode());
                    excelGoodsDataVO.setSalePrice(skuGoodInfo.getSalePrice());
                    excelGoodsDataVO.setOldPrice(skuGoodInfo.getOldPrice());
                    excelGoodsDataVO.setCostPrice(skuGoodInfo.getCostPrice());
                    excelGoodsDataVO.setSpecValue(skuGoodInfo.getSpecsValue());

                    //获取sku商品库存数
                    excelGoodsDataVO.setStockNum(getSkuStockNum(skuGoodInfo.getShopId(),skuGoodInfo.getId()));

                    excelGoodsDataVOS.add(excelGoodsDataVO);
                }
            }else {
                excelGoodsDataVOS.add(excelGoodsDataVO);
            }
        }
        return excelGoodsDataVOS;
    }

    @Override
    public List<PCMerchGoodsInfoVO.ImportGoodsDataVO> downExcelModel() {
        List<PCMerchGoodsInfoVO.ImportGoodsDataVO> importGoodsDataVOS = new ArrayList<>();
        PCMerchGoodsInfoVO.ImportGoodsDataVO importGoodsDataVO = new PCMerchGoodsInfoVO.ImportGoodsDataVO();
        importGoodsDataVO.setCategoryLevel1Name("一级类目名称");
        importGoodsDataVO.setCategoryLevel2Name("二级类目名称");
        importGoodsDataVO.setCategoryLevel3Name("三级类目名称");
        importGoodsDataVO.setGoodsNo("10001");
        importGoodsDataVO.setTemplateName("包邮");
        importGoodsDataVO.setSpecValue("规格A名称:规格A的值,规格B名称:规格B的值");
        importGoodsDataVO.setAttributeValue("属性A名称:属性A的值,属性B名称:属性B的值");
        importGoodsDataVO.setChargeUnit("kg");
        importGoodsDataVO.setCostPrice("100");
        importGoodsDataVO.setSalePrice("100");
        importGoodsDataVO.setOldPrice("100");
        importGoodsDataVO.setGoodsTitle("商品描述");
        importGoodsDataVO.setGoodsName("商品名称");
        importGoodsDataVO.setGoodsValidDays("100");
        importGoodsDataVO.setGoodsWeight("1");
        importGoodsDataVO.setBrandName("品牌名称");
        importGoodsDataVO.setShopNavigationName("2b店铺分类名称");
        importGoodsDataVO.setShopNavigation2cName("2c店铺分类名称");
        importGoodsDataVO.setStockSubtractType("10");
        importGoodsDataVO.setStockNum("10");
        importGoodsDataVO.setIsPointGood("false");
        importGoodsDataVO.setPointPrice("0");
        importGoodsDataVO.setRemarks("办理备注");
        importGoodsDataVO.setIsInMemberGift("false");
        importGoodsDataVO.setInMemberPointPrice("0");
        importGoodsDataVO.setSaleType("0");
        importGoodsDataVO.setThirdProductId("信天游产品号");
        importGoodsDataVOS.add(importGoodsDataVO);
        return importGoodsDataVOS;
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addGoodsBatch(List<PCMerchGoodsInfoDTO.ExcelGoodsDataETO> list,BaseDTO baseDTO) {
        // TODO 数据校验
        if(list==null||list.size()<=0){
            throw new BusinessException("excel内容为空");
        }
        //同一商品为一组
        Map<String,List<PCMerchGoodsInfoDTO.ExcelGoodsDataETO>> listMap = list.parallelStream().filter(item->StringUtils.isNotBlank(item.getGoodsNo()))
        .collect(Collectors.groupingBy(PCMerchGoodsInfoDTO.ExcelGoodsDataETO::getGoodsNo));

        for (String keys : listMap.keySet()){
            List<PCMerchGoodsSpecInfoDTO.ETO> specList = new ArrayList<>();
            List<PCMerchSkuGoodInfoDTO.AddETO> skuList = new ArrayList<>();
            PCMerchGoodsInfoDTO.AddGoodsETO addGoodsETO = new PCMerchGoodsInfoDTO.AddGoodsETO();

            for (PCMerchGoodsInfoDTO.ExcelGoodsDataETO dataVO : listMap.get(keys)){
                BeanUtils.copyProperties(dataVO,addGoodsETO);

                //根据得到的店铺分类获取店铺id,商家id
                addGoodsETO.setMerchantId(baseDTO.getJwtMerchantId());
                addGoodsETO.setShopId(baseDTO.getJwtShopId());
                addGoodsETO.setGoodsPriceUnit(dataVO.getChargeUnit());
                addGoodsETO.setSalePrice(dataVO.getSalePrice());
                addGoodsETO.setStockSubtractType(dataVO.getStockSubtractType());
                addGoodsETO.setIsShowOldPrice(ShowOldPriceEnum.不显示原价.getCode());

                //根据三级类目获取三级类目id
                QueryWrapper<GoodsCategory> categoryWrapper = new QueryWrapper<>();
                categoryWrapper.eq("gs_category_name",dataVO.getCategoryLevel3Name());
                categoryWrapper.last("limit 0,1");
                GoodsCategory category = categoryRepository.getOne(categoryWrapper);
                String categoryId = category.getId();
                addGoodsETO.setCategoryId(categoryId);
                addGoodsETO.setUsePlatform(category.getUseFiled());

                //根据得到的品牌名称获取品牌id(有问题)
                GoodsBrandDTO.BrandNameDTO dto = new GoodsBrandDTO.BrandNameDTO(dataVO.getBrandName());
                String brandId = brandService.selectByName(dto).getId();
                addGoodsETO.setBrandId(brandId);

                //根据运费模板名称获取运费模板id
                CommonStockTemplateVO.IdNameVO idNameVO = stockTemplateRpc.getIdNameVo(baseDTO.getJwtShopId(),dataVO.getTemplateName());
                if (ObjectUtils.isEmpty(idNameVO)){
                    throw new BusinessException(dataVO.getGoodsName()+"的运费模板名称"+"填写错误！");
                }
                addGoodsETO.setTemplateId(idNameVO.getTemplateId());

                //填充属性列表(有问题)
                if (StringUtils.isNotEmpty(dataVO.getAttributeValue())){
                    List<PCMerchGoodsAttributeInfoDTO.ETO> etoList = getAttributeList(dataVO.getAttributeValue());
                    addGoodsETO.setAttributeList(etoList);
                }

                //填充规格列表(有问题)
                if (StringUtils.isNotEmpty(dataVO.getSpecValue())){
                    List<PCMerchGoodsSpecInfoDTO.ETO> etoList = getSpecInfoList(dataVO.getSpecValue());
                    specList.addAll(etoList);
                    addGoodsETO.setIsSingle(SingleStateEnum.多规格.getCode());
                }else {
                    addGoodsETO.setIsSingle(SingleStateEnum.单品.getCode());
                }

                addGoodsETO.setGoodsState(GoodsStateEnum.未上架.getCode());


                PCMerchSkuGoodInfoDTO.AddETO addETO = new PCMerchSkuGoodInfoDTO.AddETO();
                addETO.setMerchantId(baseDTO.getJwtMerchantId());
                addETO.setShopId(baseDTO.getJwtShopId());
                addETO.setState(GoodsStateEnum.未上架.getCode());

                if (StringUtils.isNotBlank(dataVO.getSpecValue())) {
                    addETO.setSkuStock(dataVO.getStockNum());
                    addETO.setCategoryId(categoryId);
                    addETO.setSpecsValue(dataVO.getSpecValue());
                    addETO.setCostPrice(dataVO.getCostPrice());
                    addETO.setImage("http://keitai-file2.oss-cn-beijing.aliyuncs.com/avatar/2020/11/12/bfffb015-f19b-4261-80c6-35058e61d0d3.jpg");
                    addETO.setOldPrice(dataVO.getOldPrice());
                    addETO.setSalePrice(dataVO.getSalePrice());
                }else {
                    addGoodsETO.setSpuStock(dataVO.getStockNum());
                }
                skuList.add(addETO);
            }
            addGoodsETO.setSpecList(getSpecList(specList));
            addGoodsETO.setEtoList(skuList);
            //添加到数据库
            addGoodsInfo(addGoodsETO);
        }
    }

    @Override
    public PCMerchGoodsInfoVO.GoodsNameVO getGoodsName(PCMerchGoodsInfoDTO.IdDTO dto) {
        if (dto == null){
            throw new BusinessException("参数不能为空！");
        }
        GoodsInfo goodsInfo = repository.getById(dto.getId());
        if (goodsInfo == null){
            throw new BusinessException("没有数据");
        }
        PCMerchGoodsInfoVO.GoodsNameVO goodsNameVO = new PCMerchGoodsInfoVO.GoodsNameVO();
        BeanUtils.copyProperties(goodsInfo,goodsNameVO);
        return goodsNameVO;
    }

    @Override
    public int countGoodsNo(PCMerchGoodsInfoDTO.GoodNoDTO dto) {
        QueryWrapper<GoodsInfo> queryWrapperBoost = MybatisPlusUtil.query();
        queryWrapperBoost.eq("goods_no",dto.getGoodsNo());
        int count = repository.count(queryWrapperBoost);
        return count;
    }

    @Override
    public PCMerchGoodsInfoVO.EditDetailVO getEditDetailEto(PCMerchGoodsInfoDTO.IdDTO dto) {
        if (dto == null){
            throw new BusinessException("参数不能为空！");
        }
        GoodsInfo goodsInfo = repository.getById(dto.getId());
        if (goodsInfo == null){
            throw new BusinessException("查询数据异常");
        }
        PCMerchGoodsInfoVO.EditDetailVO detailVO = new PCMerchGoodsInfoVO.EditDetailVO();
        //获取商品信息
        BeanUtils.copyProperties(goodsInfo,detailVO);

        //sku商品列表
        PCMerchSkuGoodInfoDTO.GoodIdDTO goodId = new PCMerchSkuGoodInfoDTO.GoodIdDTO();
        goodId.setGoodId(goodsInfo.getId());
        goodId.setJwtShopId(goodsInfo.getShopId());
        List<PCMerchSkuGoodInfoVO.DetailVO> skuList = skuGoodInfoService.getByGoodsId(goodId);
        if (ObjectUtils.isNotEmpty(skuList) && StringUtils.isNotEmpty(skuList.get(0).getSpecsKey())){
            detailVO.setSkuVoList(skuList);
        }
        //商品拓展属性列表
        PCMerchGoodsAttributeInfoDTO.GoodIdDTO goodIdDTO = new PCMerchGoodsAttributeInfoDTO.GoodIdDTO(goodId.getGoodId());
        List<PCMerchGoodsAttributeInfoVO.ListVO> attributeList = attributeInfoService.getListVO(goodIdDTO);
        if (ObjectUtils.isNotEmpty(attributeList)){
            detailVO.setAttributeList(attributeList);
        }
        //商品拓展规格列表
        PCMerchGoodsSpecInfoDTO.GoodIdDTO dto1 = new PCMerchGoodsSpecInfoDTO.GoodIdDTO(goodsInfo.getId());
        List<PCMerchGoodsSpecInfoVO.ListVO> specList = goodsSpecInfoService.specInfoListVO(dto1);
        if (ObjectUtils.isNotEmpty(specList)){
            detailVO.setSpecList(specList);
        }

        //商品拓展参数列表
        PCMerchGoodsExtendParamsDTO.GoodIdDTO goodIdDTO1 = new PCMerchGoodsExtendParamsDTO.GoodIdDTO(goodsInfo.getId());
        List<PCMerchGoodsExtendParamsVO.ListVO> extentParamList = extendParamsService.extendListVO(goodIdDTO1);
        if (ObjectUtils.isNotEmpty(extentParamList)){
            detailVO.setParamsList(extentParamList);
        }
        //运费模板ID
        QueryWrapper<GoodsTempalte> tempalteBoost = MybatisPlusUtil.query();
        tempalteBoost.eq("goods_id",goodsInfo.getId());
        GoodsTempalte tempalte = goodsTempalteRepository.getOne(tempalteBoost);
        if (tempalte == null){
            throw new BusinessException("查询异常");
        }
        detailVO.setTemplateId(tempalte.getTemplateId());

        //库存计数方式
        if (ObjectUtils.isNotEmpty(tempalte.getStockSubtractType())){
            detailVO.setStockChargeWay(tempalte.getStockSubtractType());
        }

        //店铺自定义类目id
        QueryWrapper<GoodsShopNavigation> navigationBoost = MybatisPlusUtil.query();
        navigationBoost.eq("goods_id",goodsInfo.getId());
        List<GoodsShopNavigation> navigations = shopNavigationRepository.list(navigationBoost);
        if (ObjectUtils.isNotEmpty(navigations)){
            for (GoodsShopNavigation shopNavigation : navigations){
                if (ObjectUtils.isNotEmpty(shopNavigation.getTerminal()) && shopNavigation.getTerminal().equals(TerminalEnum.BBB.getCode())){
                    detailVO.setShopNavigationId(shopNavigation.getShopNavigation());
                }
                if (ObjectUtils.isNotEmpty(shopNavigation.getTerminal()) && shopNavigation.getTerminal().equals(TerminalEnum.BBC.getCode())){
                    detailVO.setShop2cNavigationId(shopNavigation.getShopNavigation());
                }
            }
        }

        // spu库存
        detailVO.setSpuStock(getSpuStockNum(goodsInfo.getId(),dto.getJwtShopId()));
        return detailVO;
    }

    @Override
    public PageData<PCMerchGoodsInfoVO.ShopFloorCommodityVO> getShopFloorCommodityVO(PCMerchGoodsInfoQTO.ShopFloorQTO qto) {

        QueryWrapper<GoodsInfo> boost = MybatisPlusUtil.query();
        boost.eq("shop_id",qto.getJwtShopId());
        boost.eq("merchant_id",qto.getJwtMerchantId());

        boost.in("use_platform",qto.getUsePlatform(),GoodsUsePlatformEnums.B商城和C商城.getCode());
        boost.eq("goods_state",GoodsStateEnum.已上架.getCode());
        if (StringUtils.isNotEmpty(qto.getGoodsName())){
            boost.like("goods_name",qto.getGoodsName());
        }
        if (StringUtils.isNotEmpty(qto.getGoodsNo())){
            boost.like("goods_no",qto.getGoodsNo());
        }
        IPage page = MybatisPlusUtil.pager(qto);
        IPage<GoodsInfo> goodsInfoIPage = repository.page(page,boost);
        List<PCMerchGoodsInfoVO.ShopFloorCommodityVO> shopFloorCommodityVOS = new ArrayList<>();
        for (GoodsInfo goodsInfo : goodsInfoIPage.getRecords()){
            PCMerchGoodsInfoVO.ShopFloorCommodityVO floorCommodityVO = new PCMerchGoodsInfoVO.ShopFloorCommodityVO();
            BeanUtils.copyProperties(goodsInfo,floorCommodityVO);
            //获取类目名称
            if (StringUtils.isEmpty(goodsInfo.getCategoryId())){
                throw new BusinessException("商品信息数据异常");
            }
            PCMerchGoodsCategoryVO.ListVO listVO = merchGoodsCategoryService.getListVOById(new PCMerchGoodsCategoryDTO.IdDTO(goodsInfo.getCategoryId()));
            floorCommodityVO.setGoodsCategoryName(StringUtils.isEmpty(listVO.getGsCategoryName())?"":listVO.getGsCategoryName());

            //获取商品品牌
            PCMerchGoodsBrandVO.ListVO brandListVO = merchGoodsBrandService.getBrandVOById(new PCMerchGoodsBrandDTO.IdDTO(goodsInfo.getBrandId()));
            floorCommodityVO.setGoodsBrand(StringUtils.isEmpty(brandListVO.getBrandName())?"":brandListVO.getBrandName());
            shopFloorCommodityVOS.add(floorCommodityVO);
        }

        return new PageData<>(shopFloorCommodityVOS,qto.getPageNum(),qto.getPageSize(),shopFloorCommodityVOS.size());
    }

    @Override
    public PageData<PCMerchGoodsInfoVO.ShopNavigationCommodityVO> pageShopNavigationCommodityVO(PCMerchGoodsInfoQTO.ShopNavigationQTO qto) {
        QueryWrapper<PCMerchGoodsInfoVO.ShopNavigationCommodityVO> boost = MybatisPlusUtil.query();
        boost.eq("gs.shop_id",qto.getJwtShopId());
        boost.eq("gs.merchant_id",qto.getJwtMerchantId());
        boost.in("gs.use_platform",qto.getUsePlatform(),GoodsUsePlatformEnums.B商城和C商城.getCode());
        if (StringUtils.isNotBlank(qto.getShopNavigationId())){
            List<String> navigationIdList = commonShopRpc.shopNavigationIdList(qto.getShopNavigationId());
            if (ObjectUtils.isEmpty(navigationIdList)){
                return new PageData<>();
            }
            boost.in("gsn.shop_navigation",navigationIdList);
        }
        IPage<PCMerchGoodsInfoVO.ShopNavigationCommodityVO> page = MybatisPlusUtil.pager(qto);
        IPage<PCMerchGoodsInfoVO.ShopNavigationCommodityVO> commodityVOIPage = goodsInfoMapper.getShopNavigationCommodityVO(page,boost);
        if (ObjectUtils.isEmpty(commodityVOIPage) || ObjectUtils.isEmpty(commodityVOIPage.getRecords())){
            return new PageData<>();
        }
        return MybatisPlusUtil.toPageData(qto,commodityVOIPage);
    }

    @Override
    public PageData<PCMerchGoodsInfoVO.GoodsActiveVO> pageGoodsActiveVO(PCMerchGoodsInfoQTO.GoodsActiveQTO qto) {
        QueryWrapper<PCMerchGoodsInfoVO.GoodsActiveVO> wrapper = MybatisPlusUtil.query();
        wrapper.eq("gs.shop_id",qto.getJwtShopId());
        wrapper.eq("gs.merchant_id",qto.getJwtMerchantId());
        if (StringUtils.isNotBlank(qto.getGoodsNo())){
            wrapper.like("gs.goods_no",qto.getGoodsNo());
        }
        if (StringUtils.isNotBlank(qto.getGoodsName())){
            wrapper.like("gs.goods_name",qto.getGoodsName());
        }
        if (StringUtils.isNotBlank(qto.getCategoryId())){
            List<String> categoryList = listCategoryId(qto.getCategoryId());
            if (ObjectUtils.isEmpty(categoryList)){
                return new PageData<>();
            }
            wrapper.in("gs.category_id",categoryList);
        }
        if (StringUtils.isNotBlank(qto.getBrandName())){
            wrapper.like("gb.brand_name",qto.getBrandName());
        }
        IPage<PCMerchGoodsInfoVO.GoodsActiveVO> page = MybatisPlusUtil.pager(qto);
        IPage<PCMerchGoodsInfoVO.GoodsActiveVO> activeVOIPage = goodsInfoMapper.getGoodsActiveVO(page,wrapper);
        if (ObjectUtils.isEmpty(activeVOIPage) || ObjectUtils.isEmpty(activeVOIPage.getRecords())){
            return new PageData<>();
        }
        List<PCMerchGoodsInfoVO.GoodsActiveVO>  activeVOS  = activeVOIPage.getRecords().parallelStream()
                .map(e ->{
                    e.setGoodsImage(getImage(e.getGoodsImage()));
                    return e;
                }).collect(toList());

        return new PageData<>(activeVOS,qto.getPageNum(),qto.getPageSize(),activeVOIPage.getTotal());
    }

    @Override
    public PageData<PCMerchGoodsInfoVO.SkuActivePageVO> pageSkuActivePageVO(PCMerchGoodsInfoQTO.GoodsActiveQTO qto) {
        QueryWrapper<PCMerchGoodsInfoVO.SkuActivePageVO> wrapper = MybatisPlusUtil.query();
        wrapper.eq("gs.shop_id",qto.getJwtShopId());
        wrapper.eq("gs.merchant_id",qto.getJwtMerchantId());
        if (StringUtils.isNotBlank(qto.getGoodsNo())){
            wrapper.like("gs.goods_no",qto.getGoodsNo());
        }
        if (StringUtils.isNotBlank(qto.getGoodsName())){
            wrapper.like("gs.goods_name",qto.getGoodsName());
        }
        if (StringUtils.isNotBlank(qto.getCategoryId())){
            List<String> categoryList = listCategoryId(qto.getCategoryId());
            if (ObjectUtils.isEmpty(categoryList)){
                return new PageData<>();
            }
            wrapper.in("gs.category_id",categoryList);
        }
        if (StringUtils.isNotBlank(qto.getBrandName())){
            wrapper.like("gb.brand_name",qto.getBrandName());
        }
        IPage<PCMerchGoodsInfoVO.SkuActivePageVO> page = MybatisPlusUtil.pager(qto);
        IPage<PCMerchGoodsInfoVO.SkuActivePageVO> activeVOIPage = goodsInfoMapper.getSkuActivePageVO(page,wrapper);
        if (ObjectUtils.isEmpty(activeVOIPage) || ObjectUtils.isEmpty(activeVOIPage.getRecords())){
            return new PageData<>();
        }
        return new PageData<>(activeVOIPage.getRecords(),qto.getPageNum(),qto.getPageSize(),activeVOIPage.getTotal());
    }

    @Override
    public List<PCMerchGoodsInfoVO.SkuActiveVO> listSkuActiveVO(PCMerchGoodsInfoDTO.IdDTO dto) {
        if (ObjectUtils.isEmpty(dto) || StringUtils.isBlank(dto.getId())){
            throw new BusinessException("参数为空异常！！");
        }
        QueryWrapper<GoodsInfo> queryWrapper = MybatisPlusUtil.query();
        queryWrapper.eq("id",dto.getId());
        GoodsInfo goodsInfo = repository.getOne(queryWrapper);
        if (ObjectUtils.isEmpty(goodsInfo)){
            throw new BusinessException("数据异常！！！");
        }

        QueryWrapper<SkuGoodInfo> wrapper = MybatisPlusUtil.query();
        wrapper.eq("good_id",dto.getId());
        List<SkuGoodInfo> skuGoodInfos = skuGoodInfoRepository.list(wrapper);
        if (ObjectUtils.isEmpty(skuGoodInfos)){
            throw new BusinessException("数据异常！！！");
        }
        List<PCMerchGoodsInfoVO.SkuActiveVO> skuActiveVOList = skuGoodInfos.stream()
                .map(e ->{
                    PCMerchGoodsInfoVO.SkuActiveVO skuActiveVO = new PCMerchGoodsInfoVO.SkuActiveVO();
                    skuActiveVO.setSkuId(e.getId());
                    skuActiveVO.setGoodsName(goodsInfo.getGoodsName());
                    if (goodsInfo.getIsSingle().equals(SingleStateEnum.单品.getCode())){
                        skuActiveVO.setGoodsImage(getImage(goodsInfo.getGoodsImage()));
                        skuActiveVO.setSalePrice(goodsInfo.getSalePrice());
                    }else {
                        skuActiveVO.setGoodsImage(e.getImage());
                        skuActiveVO.setSalePrice(e.getSalePrice());
                    }
                    return skuActiveVO;
                }).collect(toList());
        return skuActiveVOList;
    }

    @Override
    public void setGoodsTemplate(PCMerchGoodsInfoDTO.SettingGoodsTemplateDTO dto) {
        //校验数据
        if (ObjectUtils.isEmpty(dto)){
            throw new BusinessException("参数为空，异常！！");
        }
        if (ObjectUtils.isEmpty(dto.getIdList())){
            throw new BusinessException("请选择要设置运费模板的商品");
        }
        if (ObjectUtils.isEmpty(dto.getTemplateId())){
            throw new BusinessException("请选择要设置的运费模板");
        }

        UpdateWrapper<GoodsTempalte> wrapper = MybatisPlusUtil.update();
        wrapper.in("goods_id",dto.getIdList());

        GoodsTempalte tempalte = new GoodsTempalte();
        tempalte.setTemplateId(dto.getTemplateId());

        goodsTempalteRepository.update(tempalte,wrapper);
    }

    @Override
    public List<PCMerchGoodsInfoVO.ShopCategoryGoodsVO> getShopCategoryGoodsVO(BaseDTO dto) {
        PCMerchShopVO.ShopSimpleVO shopSimpleVO = shopRpc.innerShopSimple(dto.getJwtShopId());
        if (null == shopSimpleVO){
            throw new BusinessException("店铺不存在或数据查询异常！");
        }
        List<PCMerchGoodsInfoVO.ShopCategoryGoodsVO> shopGoodsVOCategories = new ArrayList<>();
        //获取店铺申请的一级类目
        if (shopSimpleVO.getShopType().equals(ShopTypeEnum.运营商自营.getCode())){
            QueryWrapper<GoodsCategory> wrapperBoost = MybatisPlusUtil.query();
            wrapperBoost.eq("gs_category_level",GoodsCategoryLevelEnum.ONE.getCode());
            List<GoodsCategory> categories = categoryRepository.list(wrapperBoost);
            shopGoodsVOCategories = categories.parallelStream().map(e ->{
                PCMerchGoodsInfoVO.ShopCategoryGoodsVO shopCategoryGoodsVO = new PCMerchGoodsInfoVO.ShopCategoryGoodsVO();
                shopCategoryGoodsVO.setLevelId(e.getId());
                shopCategoryGoodsVO.setLevelParentId(StringUtils.isEmpty(e.getParentId())?"":e.getParentId());
                shopCategoryGoodsVO.setLevelName(e.getGsCategoryName());
                shopCategoryGoodsVO.setLevel2CategoryGoodsVOS(new ArrayList<>());
                return shopCategoryGoodsVO;
            }).collect(toList());
        }else {
            shopGoodsVOCategories = commonShopRpc.listLevelCategories(dto);
        }
        if (ObjectUtils.isEmpty(shopGoodsVOCategories)){
            return new ArrayList<>();
        }
        for (PCMerchGoodsInfoVO.ShopCategoryGoodsVO categoryGoodsVO :shopGoodsVOCategories){
            QueryWrapper<GoodsCategory> wrapper = MybatisPlusUtil.query();
            wrapper.eq("parent_id",categoryGoodsVO.getLevelId());
            wrapper.select("id","parent_id","gs_category_name");
            List<GoodsCategory> level2Categories = categoryRepository.list(wrapper);
            if (ObjectUtils.isNotEmpty(level2Categories)){
                List<PCMerchGoodsInfoVO.ShopLevel2CategoryGoodsVO> level2CategoryGoodsVOS = level2Categories.parallelStream()
                        .map(e ->{
                            PCMerchGoodsInfoVO.ShopLevel2CategoryGoodsVO level2CategoryGoodsVO= new PCMerchGoodsInfoVO.ShopLevel2CategoryGoodsVO();
                            level2CategoryGoodsVO.setLevel2Id(e.getId());
                            level2CategoryGoodsVO.setLevel2ParentId(StringUtils.isEmpty(e.getParentId())?"":e.getParentId());
                            level2CategoryGoodsVO.setLevel2Name(StringUtils.isEmpty(e.getGsCategoryName())?"":e.getGsCategoryName());
                            level2CategoryGoodsVO.setLevel3CategoryVOS(getShopLevel3CategoryVO(e.getId(),dto.getJwtShopId()));
                            return level2CategoryGoodsVO;
                        }).collect(toList());
                categoryGoodsVO.setLevel2CategoryGoodsVOS(level2CategoryGoodsVOS);
            }
        }
        //过滤掉没有商品的类目列表（暂时）


        return shopGoodsVOCategories;
    }


    @Override
    public List<PCMerchGoodsInfoVO.InnerServiceGoodsVO> innerServiceGoodsVO(PCMerchGoodsInfoDTO.IdsInnerServiceDTO dto) {
        return getInnerServiceGoodsVO(GoodsStateEnum.已上架.getCode(),dto);
    }

    @Override
    public List<PCMerchGoodsInfoVO.ListVO> innerServiceSpuGoodsInfo(PCMerchGoodsInfoDTO.IdsInnerServiceDTO dto) {
        return getInnerServiceSpuGoodsInfo(GoodsStateEnum.已上架.getCode(),dto);
    }

    @Override
    public List<PCMerchGoodsInfoVO.InnerServiceGoodsInfoVO> innerServiceGoodsInfo(PCMerchGoodsInfoDTO.IdsInnerServiceDTO dto) {
        return getInnerServiceGoodsInfo(GoodsStateEnum.已上架.getCode(),dto);
    }

    @Override
    public List<PCMerchGoodsInfoVO.InnerServiceGoodsVO> innerServiceAllGoodsVO(PCMerchGoodsInfoDTO.IdsInnerServiceDTO dto) {
        return getInnerServiceGoodsVO(null,dto);
    }

    @Override
    public List<PCMerchGoodsInfoVO.ListVO> innerServiceAllSpuGoodsInfo(PCMerchGoodsInfoDTO.IdsInnerServiceDTO dto) {
        return getInnerServiceSpuGoodsInfo(null,dto);
    }

    @Override
    public List<PCMerchGoodsInfoVO.InnerServiceGoodsInfoVO> innerServiceAllGoodsInfo(PCMerchGoodsInfoDTO.IdsInnerServiceDTO dto) {
        return getInnerServiceGoodsInfo(null,dto);
    }

    @Override
    public boolean innerServiceShopNavigation(List<String> shopNavigationIds) {
        QueryWrapper<GoodsShopNavigation> wrapperBoost = MybatisPlusUtil.query();
        wrapperBoost.in("shop_navigation",shopNavigationIds);
        int count = shopNavigationRepository.count(wrapperBoost);
        if (count >0){
            return true;
        }
        return false;
    }

    @Override
    public List<PCMerchSkuGoodInfoVO.ListVO> innerServiceSkuGoodsList(List<String> skuIds) {
        if (ObjectUtils.isNotEmpty(skuIds)) {
            QueryWrapper<SkuGoodInfo> wrapper = MybatisPlusUtil.query();
            wrapper.in("id", skuIds);
            List<SkuGoodInfo> skuGoodInfos = skuGoodInfoRepository.list(wrapper);
            if (ObjectUtils.isEmpty(skuGoodInfos)) {
                return new ArrayList<>();
            }
            List<PCMerchSkuGoodInfoVO.ListVO> listVOS = ListUtil.listCover(PCMerchSkuGoodInfoVO.ListVO.class, skuGoodInfos);
            return listVOS;
        }else {
            return null;
        }
    }

    @Override
    public PCMerchGoodsInfoVO.HomeCountGoodsVO innerHomeCountGoodsVO(BaseDTO dto) {
        QueryWrapper<GoodsInfo> wrapper = MybatisPlusUtil.query();
        wrapper.eq("gs.shop_id",dto.getJwtShopId());
        wrapper.eq("gs.merchant_id",dto.getJwtMerchantId());
        PCMerchGoodsInfoVO.HomeCountGoodsVO homeCountGoodsVO = goodsInfoMapper.getHomeCountGoodsVO(wrapper);
        //获取店铺商品库存报警数量

        PCMerchGoodsStockQTO.SkuAlarmQTO alarmQto = new PCMerchGoodsStockQTO.SkuAlarmQTO();
        BeanUtils.copyProperties(dto,alarmQto);
        alarmQto.setShopId(alarmQto.getJwtShopId());
        PCMerchGoodsStockVO.SkuAlarmDetailVO detailVO = alarmService.detailSkuStockAlarm(alarmQto);
        if (ObjectUtils.isEmpty(detailVO)){
           homeCountGoodsVO.setStockAlarmNum(0);
        }else {
            //获取库存报警的商品id列表
            List<PCMerchStockVO.InnerRoliceVO>  innerRoliceVOS = stockRpc.innerStockRolice(dto,detailVO.getStockNum(),dto.getJwtShopId());
            homeCountGoodsVO.setStockAlarmNum(ObjectUtils.isEmpty(innerRoliceVOS)?0:innerRoliceVOS.size());
        }
        return homeCountGoodsVO;
    }

    @Override
    public String saveGoodsAndGetId(GoodsInfo goodsInfo) {
        repository.saveOrUpdate(goodsInfo);
        return goodsInfo.getId();
    }

    @Override
    public PCMerchGoodsInfoVO.SkuIdByGoodsNoVO innerSkuIdByGoodsNo(String barcode) {
        if (StringUtils.isBlank(barcode)){
            return null;
        }
        PCMerchGoodsInfoVO.SkuIdByGoodsNoVO skuIdByGoodsNoVO = new PCMerchGoodsInfoVO.SkuIdByGoodsNoVO();
        QueryWrapper<SkuGoodInfo> query = MybatisPlusUtil.query();
        query.and(i->i.eq("sku_goods_no",barcode));
        query.last("limit 0,1");
        SkuGoodInfo one = skuGoodInfoRepository.getOne(query);
        if (ObjectUtils.isNotEmpty(one)){
            skuIdByGoodsNoVO.setSkuId(one.getId()).setGoodsId(one.getGoodId());
            return skuIdByGoodsNoVO;
        }
        return null;
    }

    private List<PCMerchGoodsAttributeInfoDTO.ETO> getAttributeList(String attributeInfos){
        List<String> stringList = Arrays.asList(attributeInfos.split(",")).stream().distinct().collect(toList());
        List<PCMerchGoodsAttributeInfoDTO.ETO> etoList = stringList.parallelStream()
                .map(e ->{
                    PCMerchGoodsAttributeInfoDTO.ETO eto = new PCMerchGoodsAttributeInfoDTO.ETO();
                    String [] arr = e.split(":");
                    eto.setAttributeName(arr[0]);
                    eto.setAttributeValue(arr[1]);
                    return eto;
                }).collect(toList());
        return etoList;
    }

    private List<PCMerchGoodsSpecInfoDTO.ETO> getSpecInfoList(String specInfos){
        List<String> stringList = Arrays.asList(specInfos.split(",")).stream().distinct().collect(toList());
        List<PCMerchGoodsSpecInfoDTO.ETO> etoList = stringList.parallelStream()
                .map(e ->{
                    PCMerchGoodsSpecInfoDTO.ETO eto = new PCMerchGoodsSpecInfoDTO.ETO();
                    String [] arr = e.split(":");
                    eto.setSpecName(arr[0]);
                    eto.setSpecValue(arr[1]);
                    return eto;
                }).collect(toList());
        return etoList;
    }

    private Integer getSkuStockNum(String shopId,String skuId){
        CommonStockVO.InnerStockVO stockVO = commonStockRpc.queryStock(new BaseDTO(),shopId,skuId).getData();
        if (stockVO != null && stockVO.getQuantity() != null){
            return  stockVO.getQuantity();
        }
        return 0;
    }

    private Integer getSpuStockNum(String goodsId,String shopId){
        QueryWrapper<SkuGoodInfo> boost = MybatisPlusUtil.query();
        boost.eq("good_id",goodsId);
        List<SkuGoodInfo> skuGoodInfos = skuGoodInfoRepository.list(boost);
        if (ObjectUtils.isEmpty(skuGoodInfos)){
            throw new BusinessException("查询数据异常");
        }
        int spuStockNum = 0;
        for (SkuGoodInfo skuGoodInfo : skuGoodInfos){
            spuStockNum += getSkuStockNum(shopId,skuGoodInfo.getId());
        }
        return spuStockNum;
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

    private void clearGoodsJoin(String id){
        //清除商品关联的运费模板信息
        QueryWrapper<GoodsTempalte> templateWrapper = new QueryWrapper<>();
        templateWrapper.eq("goods_id",id);
        goodsTempalteRepository.remove(templateWrapper);

        //清除商品关联的店铺分类信息
        QueryWrapper<GoodsShopNavigation> shopNavigationWrapper = new QueryWrapper<>();
        shopNavigationWrapper.eq("goods_id",id);
        navigationRepository.remove(shopNavigationWrapper);

        // TODO 清除商品关联的库存信息

        //清除商品关联的拓展信息
        QueryWrapper<GoodsAttributeInfo> attributeWrapper = new QueryWrapper<>();
        attributeWrapper.eq("good_id",id);
        attributeInfoRepository.remove(attributeWrapper);

        QueryWrapper<GoodsSpecInfo> specWrapper = new QueryWrapper<>();
        specWrapper.eq("good_id",id);
        goodsSpecInfoRepository.remove(specWrapper);

        QueryWrapper<GoodsExtendParams> paramsWrapper = new QueryWrapper<>();
        paramsWrapper.eq("good_id",id);
        extendParamsRepository.remove(paramsWrapper);

        //如果该商品是多规格的则清除商品关联的sku数据
        QueryWrapper<SkuGoodInfo> skuWrapper = new QueryWrapper<>();
        skuWrapper.eq("good_id",id);
        skuGoodInfoRepository.remove(skuWrapper);

        //清除商品信息
        QueryWrapper<GoodsInfo> goodsWrapper = MybatisPlusUtil.query();
        goodsWrapper.eq("id",id);
        repository.remove(goodsWrapper);
    }

    private void JoinGoodsExtendIds(StringBuffer attributeBuffer,StringBuffer specBuffer,StringBuffer paramsBuffer,String id){

        String attributeIds = "";
        String specIds = "";
        String paramsIds = "";
        if (StringUtils.isNotEmpty(attributeBuffer.toString())) {
            attributeIds = attributeBuffer.toString().substring(0, attributeBuffer.toString().lastIndexOf(","));
        }
        if (StringUtils.isNotEmpty(specBuffer.toString())){
            specIds = specBuffer.toString().substring(0, specBuffer.toString().lastIndexOf(","));
        }
        if (StringUtils.isNotEmpty(paramsBuffer.toString())) {
            paramsIds = paramsBuffer.toString().substring(0, paramsBuffer.toString().lastIndexOf(","));
        }
        GoodsInfo info = new GoodsInfo();
        info.setId(id);
        info.setAttributeInfoId(StringUtils.isEmpty(attributeIds)?"":attributeIds);
        info.setSpecInfoId(StringUtils.isEmpty(specIds)?"":specIds);
        info.setExtendParamsId(StringUtils.isEmpty(paramsIds)?"":paramsIds);

        repository.updateById(info);
    }

    private void JoinSkuSpecIds(StringBuffer specBuffer,String id){
        String specIds = "";
        if (StringUtils.isNotEmpty(specBuffer.toString())){
            specIds = specBuffer.toString().substring(0, specBuffer.toString().lastIndexOf(","));
        }
        SkuGoodInfo skuGoodInfo = new SkuGoodInfo();
        skuGoodInfo.setSpecsKey(specIds);
        QueryWrapper<SkuGoodInfo> wrapper = new QueryWrapper<>();
        wrapper.eq("good_id",id);
        skuGoodInfoRepository.update(skuGoodInfo,wrapper);
    }

    private void upOrOnGoods(PCMerchGoodsInfoDTO.IdListDTO dto, Integer state){
        if (ObjectUtils.isEmpty(dto)){
            throw new BusinessException("参数不能为空！");
        }
        //如果该商品是多规格商品则先修改sku商品然后再修改spu商品
        for (String goodsId:dto.getIdList()) {
            QueryWrapper<SkuGoodInfo> wrapper = new QueryWrapper<>();
            wrapper.eq("good_id",goodsId);
            List<SkuGoodInfo> infoList = skuGoodInfoRepository.list(wrapper);
            if (infoList != null && infoList.size()>0){
                for (SkuGoodInfo info:infoList) {

                    UpdateWrapper<SkuGoodInfo> updateWrapper = new UpdateWrapper<>();
                    updateWrapper.eq("id",info.getId());

                    SkuGoodInfo skuGoodInfo = new SkuGoodInfo();
                    skuGoodInfo.setState(state);

                    skuGoodInfoRepository.update(skuGoodInfo,updateWrapper);
                }
            }
        }
        for (String id:dto.getIdList()) {
            QueryWrapper<GoodsInfo> wrapper = new QueryWrapper<>();
            wrapper.eq("id",id);

            GoodsInfo goodsInfo = new GoodsInfo();
            goodsInfo.setGoodsState(state);
            //上架时间
            if (state.intValue() == GoodsStateEnum.已上架.getCode().intValue()){
                goodsInfo.setPublishTime(LocalDateTime.now());
            }
            repository.update(goodsInfo,wrapper);
        }
    }

    private boolean whetherOpenGoodsUpAudit(){
        SettingsVO.ListVO listVO = settingsRpc.detail(new BaseDTO());
        if (listVO != null && listVO.getIsReviewProduct().intValue() == SettingGoodsAuditEnum.开启商品上架审核.getCode().intValue()){
            return true;
        }
        return false;
    }

    private static List<String> getJiaoJi(List<String> list1,List<String> list2){

        List<String> intersection = list1.stream().
                filter(item -> list2.contains(item)).collect(toList());
        if (ObjectUtils.isEmpty(intersection)) {
            return null;
        }
        return intersection;
    }

    private void initGoodsStock(PCMerchGoodsInfoDTO.AddGoodsETO eto, List<CommonStockDTO.InnerChangeStockItem> items) {
        CommonStockDTO.InnerChangeStockDTO  dto = new CommonStockDTO.InnerChangeStockDTO();
        dto.setShopId(StringUtils.isEmpty(eto.getShopId())?eto.getJwtShopId():eto.getShopId());
        dto.setMerchantId(StringUtils.isEmpty(eto.getMerchantId())?eto.getJwtMerchantId():eto.getMerchantId());
        dto.setLocation(StockLocationEnum.初始化.getCode());
        dto.setDataFromType(StockDataFromTypeEnum.商家运维.getCode());
        dto.setGoodsItemList(ObjectUtils.isEmpty(items)?new ArrayList<>():items);
        commonStockRpc.innerChangeStock(dto);
    }

    private CommonShopVO.NavigationVO getShopNavigation(String id){
        CommonShopVO.NavigationVO  shopNavigation = commonShopRpc.shopNavigation(id);
        if (shopNavigation == null){
            throw new BusinessException("商品关联店铺自定义分类数据异常");
        }
        return shopNavigation;
    }

    private boolean getHasTemplate(String goodsId){
        QueryWrapper<GoodsTempalte> boost = MybatisPlusUtil.query();
        boost.eq("goods_id",goodsId);
        int count = goodsTempalteRepository.count(boost);
        if (count>0){
            return true;
        }
        return false;
    }

    private void deleteSurplusAttribute(List<PCMerchGoodsAttributeInfoVO.ListVO> beforeEto,List<PCMerchGoodsAttributeInfoDTO.ETO> afterEto){
        List<String> beforeIdList = ListUtil.getIdList(PCMerchGoodsAttributeInfoVO.ListVO.class,beforeEto);
        List<String> afterIdList = ListUtil.getIdList(PCMerchGoodsAttributeInfoDTO.ETO.class,afterEto);

        List<String> reduceIdList = beforeIdList.stream()
                .filter(item -> !afterIdList.contains(item))
                .collect(toList());
        if (ObjectUtils.isEmpty(reduceIdList)){
            return;
        }
        attributeInfoRepository.removeByIds(reduceIdList);
    }

    private void checkAddGoodsData(PCMerchGoodsInfoDTO.AddGoodsETO eto){
        Integer shopState = shopRpc.innerShopState(eto.getShopId());
        if (ObjectUtils.isEmpty(shopState) || shopState.equals(ShopStateEnum.关闭状态.getCode())){
            throw new BusinessException("店铺未开通！！");
        }
        if (eto == null){
            throw new BusinessException("参数不能为空");
        }
        if (StringUtils.isBlank(eto.getCategoryId())){
            throw new BusinessException("请选择分类");
        }
        if (StringUtils.isBlank(eto.getGoodsName())){
            throw new BusinessException("请填写商品标题");
        }
        if (StringUtils.isBlank(eto.getGoodsTitle())){
            throw new BusinessException("请填写商品副标题");
        }
        if (StringUtils.isBlank(eto.getCategoryId())){
            throw new BusinessException("请选择分类");
        }
        if (ObjectUtils.isEmpty(eto.getUsePlatform())){
            throw new BusinessException("请选择商品发布的平台");
        }
        if (StringUtils.isBlank(eto.getBrandId())){
            throw new BusinessException("请选择品牌，若没有品牌请先去绑定品牌");
        }
        if (ObjectUtils.isEmpty(eto.getStockSubtractType())){
            throw new BusinessException("请选择商品的库存计数方式");
        }
        if (ObjectUtils.isEmpty(eto.getGoodsValidDays())){
            throw new BusinessException("请填写有限期天数");
        }
        if (ObjectUtils.isEmpty(eto.getIsSingle())){
            throw new BusinessException("请选择商品是单规格还是多规格");
        }
        if (ObjectUtils.isEmpty(eto.getGoodsWeight())){
            throw new BusinessException("请填写商品的重量");
        }
        if (ObjectUtils.isEmpty(eto.getSalePrice())){
            throw new BusinessException("请填写商品的标准售价！");
        }
        if (ObjectUtils.isEmpty(eto.getOldPrice())){
            throw new BusinessException("请填写商品的原价");
        }
        if (ObjectUtils.isEmpty(eto.getCostPrice())){
            throw new BusinessException("请填写商品的成本价");
        }
        if (eto.getIsSingle().equals(SingleStateEnum.多规格.getCode()) && ObjectUtils.isEmpty(eto.getSpecList())){
            throw new BusinessException("多规格商品请选择规格！");
        }
        if (eto.getIsSingle().equals(SingleStateEnum.多规格.getCode()) && ObjectUtils.isEmpty(eto.getEtoList())){
            throw new BusinessException("多规格商品请填写sku商品信息");
        }
        if (eto.getIsSingle().equals(SingleStateEnum.多规格.getCode()) && ObjectUtils.isNotEmpty(eto.getEtoList())){
            for (PCMerchSkuGoodInfoDTO.AddETO addETO : eto.getEtoList()){
                if (ObjectUtils.isEmpty(addETO.getSalePrice())){
                    throw new BusinessException("请填写sku商品的标准售价！");
                }
                if (ObjectUtils.isEmpty(addETO.getOldPrice())){
                    throw new BusinessException("请填写sku商品的原价");
                }
                if (StringUtils.isNotEmpty(addETO.getSkuGoodsNo()) && StringUtils.isBlank(eto.getId())){
                    if (this.count(addETO.getSkuGoodsNo())>0){
                        throw new BusinessException("该sku商品货号"+eto.getGoodsNo()+"已存在");
                    }
                }
                if (StringUtils.isBlank(addETO.getImage())){
                    throw new BusinessException("请上传该规格商品的图片");
                }
            }
        }
        if (StringUtils.isEmpty(eto.getTemplateId())){
            throw new BusinessException("请选择运费模板");
        }
        if ((!eto.getUsePlatform().equals(GoodsUsePlatformEnums.C商城.getCode())) && StringUtils.isBlank(eto.getShopNavigationId())){
            throw new BusinessException("请选择店铺2b自定义类目");
        }
        if ((!eto.getUsePlatform().equals(GoodsUsePlatformEnums.B商城.getCode())) && StringUtils.isBlank(eto.getShop2cNavigationId())){
            throw new BusinessException("请选择店铺2c自定义类目");
        }
    }

    private int count(String skuGoodsNo){
        QueryWrapper<SkuGoodInfo> wrapper = MybatisPlusUtil.query();
        wrapper.eq("sku_goods_no",skuGoodsNo);
        int count = skuGoodInfoRepository.count(wrapper);
        return count;
    }

    private List<String> listCategoryId(String categoryId){
        QueryWrapper<GoodsCategory> wrapper = MybatisPlusUtil.query();
        wrapper.eq("id",categoryId);
        GoodsCategory category = categoryRepository.getOne(wrapper);
        List<String> categoryList = new ArrayList<>();
        if (category.getGsCategoryLevel().equals(GoodsCategoryLevelEnum.ONE.getCode())){
           List<String> categories = categoryMapper.selectLevel3CategoryList(categoryId);
           if (ObjectUtils.isNotEmpty(categories)){
               categoryList.addAll(categories);
           }
        }
        if (category.getGsCategoryLevel().equals(GoodsCategoryLevelEnum.TWO.getCode())){
            QueryWrapper<GoodsCategory> queryWrapper = MybatisPlusUtil.query();
            wrapper.eq("parent_id",categoryId);
            List<GoodsCategory> categories = categoryRepository.list(queryWrapper);
            if (ObjectUtils.isNotEmpty(categories)){
                List<String> idList = ListUtil.getIdList(GoodsCategory.class,categories);
                categoryList.addAll(idList);
            }
        }
        if (category.getGsCategoryLevel().equals(GoodsCategoryLevelEnum.THREE.getCode())){
            categoryList.add(categoryId);
        }
        return categoryList;
    }

    private List<PCMerchGoodsSpecInfoDTO.ETO> getSpecList(List<PCMerchGoodsSpecInfoDTO.ETO> etoList){
        List<PCMerchGoodsSpecInfoDTO.ETO> etos = etoList.stream().distinct().collect(toList());
        Map<String,List<PCMerchGoodsSpecInfoDTO.ETO>> map = etos.parallelStream().collect(Collectors.groupingBy(PCMerchGoodsSpecInfoDTO.ETO::getSpecName));
        //组装
        List<PCMerchGoodsSpecInfoDTO.ETO> specList = map.values().stream().map(e ->{
            PCMerchGoodsSpecInfoDTO.ETO specListVO = new PCMerchGoodsSpecInfoDTO.ETO();
            List<String> specValues = e.stream().map(PCMerchGoodsSpecInfoDTO.ETO::getSpecValue).collect(Collectors.toList());
            specListVO.setSpecName(e.get(0).getSpecName());
            specListVO.setSpecValue(StringUtils.join(specValues,","));
            return specListVO;
        }).collect(Collectors.toList());
        return specList;
    }

    private List<PCMerchGoodsInfoVO.ShopLevel3CategoryVO> getShopLevel3CategoryVO(String parentId,String shopId){
        //获取店铺下属于2b的商品三级分类
        QueryWrapper<GoodsCategory> queryWrapper = MybatisPlusUtil.query();
        queryWrapper.ne("a.use_filed",GoodsUsePlatformEnums.C商城.getCode());
        queryWrapper.eq("b.id",parentId);
        queryWrapper.select("a.id","a.parent_id","a.gs_category_name");
        List<GoodsCategory> level3Categories = categoryMapper.getLevel3CategoryList(queryWrapper);
        if (ObjectUtils.isEmpty(level3Categories)){
            return new ArrayList<>();
        }
        List<PCMerchGoodsInfoVO.ShopLevel3CategoryVO> shopLevel3CategoryVOS = level3Categories.parallelStream().map(e ->{
            PCMerchGoodsInfoVO.ShopLevel3CategoryVO shopLevel3CategoryVO = new PCMerchGoodsInfoVO.ShopLevel3CategoryVO();
            shopLevel3CategoryVO.setLevel3Id(e.getId());
            shopLevel3CategoryVO.setLevel3ParentId(e.getParentId());
            shopLevel3CategoryVO.setLevel3Name(e.getGsCategoryName());
            shopLevel3CategoryVO.setCategorySkuGoodsVOS(getCategorySkuGoodsVO(e.getId(),shopId));
            return shopLevel3CategoryVO;
        }).collect(toList());
        return shopLevel3CategoryVOS;
    }

    private List<PCMerchGoodsInfoVO.CategorySkuGoodsVO> getCategorySkuGoodsVO(String categoryId,String shopId){
        QueryWrapper<GoodSkuInfoView> wrapper = MybatisPlusUtil.query();
        wrapper.eq("gs.shop_id",shopId);
        wrapper.eq("gs.category_id",categoryId);
        wrapper.eq("gs.goods_state",GoodsStateEnum.已上架.getCode());
        wrapper.ne("gs.use_platform",GoodsUsePlatformEnums.C商城.getCode());
        List<GoodSkuInfoView> skuInfoViews = goodsInfoMapper.getGoodSkuInfoView(wrapper);
        if (ObjectUtils.isEmpty(skuInfoViews)){
            return new ArrayList<>();
        }
        List<PCMerchGoodsInfoVO.CategorySkuGoodsVO> categorySkuGoodsVOS = ListUtil.listCover(PCMerchGoodsInfoVO.CategorySkuGoodsVO.class,skuInfoViews);
        return categorySkuGoodsVOS;
    }

    private List<PCMerchGoodsInfoVO.InnerServiceGoodsVO> getInnerServiceGoodsVO(Integer state,PCMerchGoodsInfoDTO.IdsInnerServiceDTO dto) {
        List<GoodsInfo> goodsInfos = null;
        QueryWrapper<GoodsInfo> boost = MybatisPlusUtil.query();
        if (ObjectUtils.isNotEmpty(state)){
            boost.eq("goods_state",state);
        }
        if(GoodsQueryTypeEnum.店铺id.getCode().equals(dto.getQueryType())){
            if(ObjectUtils.isNotEmpty(dto.getShopIdList())){
                boost.in("shop_id",dto.getShopIdList());
            }
            goodsInfos = repository.list(boost);
        }
        if(GoodsQueryTypeEnum.商品id.getCode().equals(dto.getQueryType())){
            if(ObjectUtils.isNotEmpty(dto.getGoodsIdList())){
                boost.in("id",dto.getGoodsIdList());
            }
            goodsInfos = repository.list(boost);
        }
        if(ObjectUtils.isNotEmpty(goodsInfos)){
            return ListUtil.listCover(PCMerchGoodsInfoVO.InnerServiceGoodsVO.class,goodsInfos);
        }
        return new ArrayList<>();
    }

    private List<PCMerchGoodsInfoVO.ListVO> getInnerServiceSpuGoodsInfo(Integer state,PCMerchGoodsInfoDTO.IdsInnerServiceDTO dto) {

        List<GoodsInfo> goodsInfos = null;
        QueryWrapper<GoodsInfo> boost = MybatisPlusUtil.query();
        if (ObjectUtils.isNotEmpty(state)){
            boost.eq("goods_state",state);
        }
        if(GoodsQueryTypeEnum.店铺id.getCode().equals(dto.getQueryType())){
            if(ObjectUtils.isNotEmpty(dto.getShopIdList())){
                boost.in("shop_id",dto.getShopIdList());
            }
            goodsInfos = repository.list(boost);
        }
        if(GoodsQueryTypeEnum.商品id.getCode().equals(dto.getQueryType())){
            if(ObjectUtils.isNotEmpty(dto.getGoodsIdList())){
                boost.in("id",dto.getGoodsIdList());
            }
            goodsInfos = repository.list(boost);
        }
        if(ObjectUtils.isNotEmpty(goodsInfos)){
            return ListUtil.listCover(PCMerchGoodsInfoVO.ListVO.class,goodsInfos);
        }
        return new ArrayList<>();
    }

    private List<PCMerchGoodsInfoVO.InnerServiceGoodsInfoVO> getInnerServiceGoodsInfo(Integer state,PCMerchGoodsInfoDTO.IdsInnerServiceDTO dto) {
        List<PCMerchGoodsInfoVO.InnerServiceGoodsInfoVO> list = new ArrayList<>();
        List<String> goodsIdList = new ArrayList<>();
        Map<String,PCMerchGoodsInfoVO.InnerServiceGoodsInfoVO> voMap = new HashMap<>();
        List<GoodsInfo> goodsInfos = null;
        QueryWrapper<GoodsInfo> wrapperBoost = MybatisPlusUtil.query();
        if (ObjectUtils.isNotEmpty(state)){
            wrapperBoost.eq("goods_state",state);
        }
        if (dto.getQueryType().intValue() == GoodsQueryTypeEnum.商品id.getCode().intValue()) {
            if (ObjectUtils.isNotEmpty(dto.getGoodsIdList())){
                wrapperBoost.in("id",dto.getGoodsIdList());
                goodsInfos = repository.list(wrapperBoost);
            }
        }

        if (dto.getQueryType().intValue() == GoodsQueryTypeEnum.店铺id.getCode().intValue()) {
            if (ObjectUtils.isNotEmpty(dto.getShopIdList())){
                wrapperBoost.in("shop_id",dto.getShopIdList());
                goodsInfos = repository.list(wrapperBoost);
            }
        }
        if (ObjectUtils.isEmpty(goodsInfos)){
            return new ArrayList<>();
        }
        for(GoodsInfo goodsInfoItem:goodsInfos){
            PCMerchGoodsInfoVO.InnerServiceGoodsInfoVO goodsInfoVO = new PCMerchGoodsInfoVO.InnerServiceGoodsInfoVO();
            goodsInfoVO.setSkuList(new ArrayList<>());
            BeanUtils.copyProperties(goodsInfoItem,goodsInfoVO);
            goodsInfoVO.setGoodsImage(getImage(goodsInfoItem.getGoodsImage()));
            list.add(goodsInfoVO);
            voMap.put(goodsInfoItem.getId(),goodsInfoVO);
            goodsIdList.add(goodsInfoItem.getId());
        }

        if(ObjectUtils.isNotEmpty(goodsIdList)){
            QueryWrapper<SkuGoodInfo> boost = MybatisPlusUtil.query();
            boost.in("good_id", goodsIdList);
            boost.eq("state",GoodsStateEnum.已上架.getCode());
            List<SkuGoodInfo> skuGoodInfos = skuGoodInfoRepository.list(boost);

            for(SkuGoodInfo skuGoodInfoItem:skuGoodInfos){
                PCMerchGoodsInfoVO.InnerServiceGoodsInfoVO goodsInfoVO = voMap.get(skuGoodInfoItem.getGoodId());
                PCMerchSkuGoodInfoVO.ListVO listVO = new PCMerchSkuGoodInfoVO.ListVO();
                BeanUtils.copyProperties(skuGoodInfoItem,listVO);
                goodsInfoVO.getSkuList().add(listVO);
            }
        }
        return list;
    }

    private void createGoodsShopNaigationBind(String merchantId,String shopId,String goodsId,String navigationId,Integer terminal){
        GoodsShopNavigation shopNavigation = new GoodsShopNavigation();
        shopNavigation.setMerchantId(merchantId);
        shopNavigation.setShopId(shopId);
        shopNavigation.setGoodsId(goodsId);
        shopNavigation.setShopNavigation(navigationId);
        shopNavigation.setTerminal(terminal);
        shopNavigationRepository.save(shopNavigation);
    }

    private void updateShopNavigationBind(String shopId,String merchantId,String goodsId,Integer terminal,String navigationId){
        QueryWrapper<GoodsShopNavigation> wrapper = MybatisPlusUtil.query();
        wrapper.eq("goods_id",goodsId);
        wrapper.eq("terminal",terminal);
        wrapper.eq("shop_navigation",navigationId);
        GoodsShopNavigation goodsShopNavigation = shopNavigationRepository.getOne(wrapper);
        if (ObjectUtils.isEmpty(goodsShopNavigation)){
            GoodsShopNavigation shopNavigation = new GoodsShopNavigation();
            shopNavigation.setMerchantId(merchantId);
            shopNavigation.setShopId(shopId);
            shopNavigation.setGoodsId(goodsId);
            shopNavigation.setShopNavigation(navigationId);
            shopNavigation.setTerminal(terminal);
            shopNavigationRepository.save(shopNavigation);
        }else {
            UpdateWrapper<GoodsShopNavigation> updateWrapper = MybatisPlusUtil.update();
            wrapper.eq("goods_id",goodsId);
            wrapper.eq("terminal",terminal);
            GoodsShopNavigation shopNavigation = new GoodsShopNavigation();
            shopNavigation.setShopNavigation(navigationId);
            shopNavigationRepository.update(shopNavigation,updateWrapper);
        }

    }
}
