package com.gs.lshly.biz.support.commodity.rpc.merchadmin.pc;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.gs.lshly.biz.support.commodity.entity.GoodsInfo;
import com.gs.lshly.biz.support.commodity.entity.GoodsShopNavigation;
import com.gs.lshly.biz.support.commodity.entity.GoodsTempalte;
import com.gs.lshly.biz.support.commodity.entity.SkuGoodInfo;
import com.gs.lshly.biz.support.commodity.service.merchadmin.pc.*;
import com.gs.lshly.biz.support.commodity.service.platadmin.IGoodsAttributeInfoService;
import com.gs.lshly.biz.support.commodity.service.platadmin.IGoodsBrandService;
import com.gs.lshly.biz.support.commodity.service.platadmin.IGoodsSpecInfoService;
import com.gs.lshly.common.enums.*;
import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.common.struct.common.CommonShopDTO;
import com.gs.lshly.common.struct.common.CommonShopVO;
import com.gs.lshly.common.struct.common.CommonStockDTO;
import com.gs.lshly.common.struct.common.stock.CommonStockTemplateVO;
import com.gs.lshly.common.struct.merchadmin.pc.commodity.dto.*;
import com.gs.lshly.common.struct.merchadmin.pc.commodity.vo.PCMerchGoodsCategoryVO;
import com.gs.lshly.common.struct.platadmin.commodity.dto.GoodsBrandDTO;
import com.gs.lshly.common.utils.GoodsNoUtil;
import com.gs.lshly.middleware.redis.RedisUtil;
import com.gs.lshly.rpc.api.common.ICommonShopRpc;
import com.gs.lshly.rpc.api.common.ICommonSiteCustomerServiceRpc;
import com.gs.lshly.rpc.api.common.ICommonStockRpc;
import com.gs.lshly.rpc.api.merchadmin.pc.commodity.IPCMerchAdminGoodsBrandRpc;
import com.gs.lshly.rpc.api.merchadmin.pc.commodity.IPCMerchGoodsInfoImportRpc;
import com.gs.lshly.rpc.api.merchadmin.pc.stock.IPCMerchStockTemplateRpc;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.*;

import static java.util.stream.Collectors.toList;


/**
 * @author lxus
 */
@Slf4j
@DubboService
public class PCMerchGoodsInfoImportRpc implements IPCMerchGoodsInfoImportRpc {

    @DubboReference
    private ICommonShopRpc commonShopRpc;
    @DubboReference
    private IPCMerchStockTemplateRpc stockTemplateRpc;
    @DubboReference
    private ICommonStockRpc commonStockRpc;
    @DubboReference
    private IPCMerchAdminGoodsBrandRpc brandRpc;
    @DubboReference
    private ICommonSiteCustomerServiceRpc serviceRpc;

    @Autowired
    private IGoodsBrandService brandService;
    @Autowired
    private IPCMerchGoodsInfoService goodsInfoService;
    @Autowired
    private IGoodsAttributeInfoService attributeInfoService;
    @Autowired
    private IGoodsSpecInfoService specInfoService;
    @Autowired
    private IPCMerchSkuGoodInfoService skuGoodInfoService;
    @Autowired
    private IPCMerchGoodsShopNavigationService shopNavigationService;
    @Autowired
    private IPCMerchGoodsSpecDictionaryService specDictionaryService;
    @Autowired
    private IPCMerchGoodsCategoryService categoryService;

    @Autowired
    private RedisUtil redisUtil;



    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveOrUpdateData(GoodsExcelImportDTO data) {

        log.info("持久化导入sku和spu数据集合:{}", JSON.toJSONString(data));
        /**
         *  判断该条数据的spu商品是否已经存入数据库
         *  若已存在则对现有的spu商品表进行规格的修改
         *  若不存在则向spu表插入一条新的数据
         *
         *  1先处理spu
         *  1.1已有spu，查出来，是否需要更新
         *  1.2没有spu，构建spu，并持久化
         *  再处理sku
         */
        Object goodsKeyId = redisUtil.hget(data.getGoodsNo(),"商品id");
        if (ObjectUtils.isEmpty(goodsKeyId)){
            goodsKeyId = null;
        }
        if (ObjectUtils.isNotEmpty(goodsKeyId)){
            /**
             * 1.更新规格拓展信息
             * 2.新增sku信息
             * 3，新增商品库存
             */
            String goodsId = goodsKeyId.toString();
            //更新规格拓展信息
            updateSpecInfo(data,goodsId);
            //获取商品类目id

            PCMerchGoodsCategoryVO.innerCategoryVO categoryLevel3VO =categoryService.innerGetListVo(data.getCategoryLevel3Name(),null);
            String categoryId = categoryLevel3VO.getId();
            String skuId = saveSkuAndGetSkuId(data,goodsId,categoryId);
            //建立sku与规格拓展信息的关联
            createdBindSkuSpec(data,goodsId);
            //建立商品与库存的关联
            createStockBind(data,goodsId,skuId);
        }else {
            //新增
            GoodsInfo goodsInfo = new GoodsInfo();
            goodsInfo.setShopId(data.getJwtShopId());
            goodsInfo.setMerchantId(data.getJwtMerchantId());
            goodsInfo.setGoodsName(data.getGoodsName());
            goodsInfo.setGoodsTitle(data.getGoodsTitle());
            goodsInfo.setGoodsState(GoodsStateEnum.未上架.getCode());
            goodsInfo.setGoodsNo(GoodsNoUtil.getGoodsNo());
            goodsInfo.setGoodsPriceUnit("kg");
            goodsInfo.setGoodsImage(StringUtils.isEmpty(getDefaultImage(ImageSizeTypeEnum.小图.getCode()))?"":getDefaultImage(ImageSizeTypeEnum.小图.getCode()));
            goodsInfo.setSalePrice(new BigDecimal(data.getSalePrice()));
            goodsInfo.setCostPrice(new BigDecimal(data.getCostPrice()));
            goodsInfo.setOldPrice(new BigDecimal(data.getOldPrice()));
            goodsInfo.setGoodsValidDays(Integer.parseInt(data.getGoodsValidDays()));
            goodsInfo.setIsSingle(SingleStateEnum.多规格.getCode());
            goodsInfo.setGoodsWeight(new BigDecimal(data.getGoodsWeight()));
            goodsInfo.setIsShowOldPrice(ShowOldPriceEnum.不显示原价.getCode());
            //获取商品类目id
            PCMerchGoodsCategoryVO.innerCategoryVO categoryLevel3VO =categoryService.innerGetListVo(data.getCategoryLevel3Name(),null);
            System.out.println(categoryLevel3VO);
            String categoryId = categoryLevel3VO.getId();
            goodsInfo.setCategoryId(categoryId);
            goodsInfo.setUsePlatform(categoryLevel3VO.getUseFiled());
            //获取品牌id
            GoodsBrandDTO.BrandNameDTO dto = new GoodsBrandDTO.BrandNameDTO(data.getBrandName());
            String brandId = brandService.selectByName(dto).getId();
            goodsInfo.setBrandId(brandId);
            String goodsId = goodsInfoService.saveGoodsAndGetId(goodsInfo);


            //新增sku数据
            String skuId = saveSkuAndGetSkuId(data,goodsId,categoryId);

            //建立商品与运费模板以及与店铺自定义模板的关联
            createBind(data,categoryLevel3VO.getUseFiled(),goodsId);

            //添加商品所用的规格信息以及属性信息
            creteSpecAndAttributeBind(data,goodsId);

            //建立商品与库存的关联
            createStockBind(data,goodsId,skuId);

            redisUtil.hset(data.getGoodsNo(),"商品id",goodsId,5*60);
        }

    }

    @Override
    public String getShopId(BaseDTO dto) {
        return dto.getJwtShopId();
    }

    @Override
    public CommonShopVO.ShopGoodsCategoryVO categoryLevelVO(String categoryName, String shopId) {
        return commonShopRpc.innerGetGoodsCategoryVO(shopId,categoryName);
    }

    @Override
    public PCMerchGoodsCategoryVO.innerCategoryVO categoryVo(String categoryName, String parentId) {
        return categoryService.innerGetListVo(categoryName, parentId);
    }

    @Override
    public boolean countBrand(String brandName, String categoryId) {
        return brandRpc.innerGetBrandVO(brandName,categoryId);
    }

    @Override
    public int checkShopNavigation(String shopId, String shopNavigationName, Integer useFiled) {
        return commonShopRpc.checkShopNavigation(shopId, shopNavigationName, useFiled);
    }

    @Override
    public int checkTemplate(String shopId, String templateName) {
        return stockTemplateRpc.checkTemplate(shopId, templateName);
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

    private void initGoodsStock(GoodsExcelImportDTO excelImportDTO, List<CommonStockDTO.InnerChangeStockItem> items) {
        CommonStockDTO.InnerChangeStockDTO  dto = new CommonStockDTO.InnerChangeStockDTO();
        dto.setShopId(excelImportDTO.getJwtShopId());
        dto.setMerchantId(excelImportDTO.getJwtMerchantId());
        dto.setLocation(StockLocationEnum.初始化.getCode());
        dto.setDataFromType(StockDataFromTypeEnum.商家运维.getCode());
        dto.setGoodsItemList(ObjectUtils.isEmpty(items)?new ArrayList<>():items);
        commonStockRpc.innerChangeStock(dto);
    }

    private void JoinGoodsExtendIds(StringBuffer attributeBuffer,StringBuffer specBuffer,String id){

        String attributeIds = "";
        String specIds = "";
        if (StringUtils.isNotEmpty(attributeBuffer.toString())) {
            attributeIds = attributeBuffer.toString().substring(0, attributeBuffer.toString().lastIndexOf(","));
        }
        if (StringUtils.isNotEmpty(specBuffer.toString())){
            specIds = specBuffer.toString().substring(0, specBuffer.toString().lastIndexOf(","));
        }

        GoodsInfo info = new GoodsInfo();
        info.setId(id);
        info.setAttributeInfoId(StringUtils.isEmpty(attributeIds)?"":attributeIds);
        info.setSpecInfoId(StringUtils.isEmpty(specIds)?"":specIds);
        goodsInfoService.saveGoodsAndGetId(info);

    }

    private void JoinSkuSpecIds(StringBuffer specBuffer,String id){
        String specIds = "";
        if (StringUtils.isNotEmpty(specBuffer.toString())){
            specIds = specBuffer.toString().substring(0, specBuffer.toString().lastIndexOf(","));
        }
        SkuGoodInfo skuGoodInfo = new SkuGoodInfo();
        skuGoodInfo.setSpecsKey(specIds);
        skuGoodInfo.setGoodId(id);

        skuGoodInfoService.updateSkuInfo(skuGoodInfo);
    }

    private String saveSkuAndGetSkuId(GoodsExcelImportDTO data,String goodsId,String categoryId){
        SkuGoodInfo skuGoodInfo = new SkuGoodInfo();
        skuGoodInfo.setGoodId(goodsId);
        skuGoodInfo.setSkuGoodsNo(GoodsNoUtil.getGoodsNo());
        skuGoodInfo.setMerchantId(data.getJwtMerchantId());
        skuGoodInfo.setShopId(data.getJwtShopId());
        skuGoodInfo.setCategoryId(categoryId);
        skuGoodInfo.setSpecsValue(data.getSpecValue());
        skuGoodInfo.setSalePrice(new BigDecimal(data.getSalePrice()));
        skuGoodInfo.setOldPrice(new BigDecimal(data.getOldPrice()));
        skuGoodInfo.setCostPrice(new BigDecimal(data.getCostPrice()));
        skuGoodInfo.setState(GoodsStateEnum.未上架.getCode());
        skuGoodInfo.setImage(StringUtils.isEmpty(getDefaultImage(ImageSizeTypeEnum.微图.getCode()))?"":getDefaultImage(ImageSizeTypeEnum.微图.getCode()));
        skuGoodInfoService.addSkuInfo(skuGoodInfo);
        return skuGoodInfo.getId();
    }

    private  void createBind(GoodsExcelImportDTO data,Integer userFild,String goodsId){
        //根据运费模板名称获取运费模板id
        CommonStockTemplateVO.IdNameVO idNameVO = stockTemplateRpc.getIdNameVo(data.getJwtShopId(),data.getTemplateName());

        //建立商品与店铺自定义类目的关联关系
        if(StringUtils.isNotBlank(data.getShopNavigationName())){
            addGoodsShopNavigation(data.getJwtShopId(),data.getShopNavigationName(),data.getJwtMerchantId(),goodsId,TerminalEnum.BBB.getCode());
        }
        if (StringUtils.isNotBlank(data.getShopNavigation2cName())){
            addGoodsShopNavigation(data.getJwtShopId(),data.getShopNavigation2cName(),data.getJwtMerchantId(),goodsId,TerminalEnum.BBC.getCode());
        }


        //建立商品与运费模板的关联关系
        GoodsTempalte template = new GoodsTempalte();
        template.setGoodsId(goodsId);
        template.setTemplateId(idNameVO.getTemplateId());
        template.setStockSubtractType(Integer.parseInt(data.getStockSubtractType()));
        shopNavigationService.saveTempalteInnerService(template);


    }

    private void creteSpecAndAttributeBind(GoodsExcelImportDTO data,String goodsId){
        //声明拓展id容器
        StringBuffer attributeBuffer = new StringBuffer();
        StringBuffer specBuffer = new StringBuffer();

        //填充属性列表
        List<PCMerchGoodsAttributeInfoDTO.ETO> attributeList = getAttributeList(data.getAttributeValue());
        for (PCMerchGoodsAttributeInfoDTO.ETO attributeInfo: attributeList) {
            attributeInfo.setGoodId(goodsId);
            attributeInfo.setId("");
            String attributeId = attributeInfoService.addGoodsAttributeInfo(attributeInfo);

            //获取属性拓展id组
            attributeBuffer.append(attributeId+",");
        }
        List<PCMerchGoodsSpecInfoDTO.ETO> specInfoList = getSpecInfoList(data.getSpecValue());
        for (PCMerchGoodsSpecInfoDTO.ETO specInfo : specInfoList) {
            specInfo.setGoodId(goodsId);
            specInfo.setId("");
            String specId = specInfoService.addGoodsSpecInfo(specInfo);

            //获取规格拓展id组
            specBuffer.append(specId + ",");

        }

        //商品关联拓展id
        JoinGoodsExtendIds(attributeBuffer,specBuffer,goodsId);


        //sku商品关联spec拓展id组
        JoinSkuSpecIds(specBuffer,goodsId);
    }

    private  void createdBindSkuSpec(GoodsExcelImportDTO data,String goodsId){
        //声明拓展id容器
        StringBuffer specBuffer = new StringBuffer();

        List<PCMerchGoodsSpecInfoDTO.ETO> specInfoList = getSpecInfoList(data.getSpecValue());
        for (PCMerchGoodsSpecInfoDTO.ETO specInfo : specInfoList) {
            specInfo.setGoodId(goodsId);
            specInfo.setId("");
            String specId = specInfoService.addGoodsSpecInfo(specInfo);

            //获取规格拓展id组
            specBuffer.append(specId + ",");

        }
        //sku商品关联spec拓展id组
        JoinSkuSpecIds(specBuffer,goodsId);
    }

    private void createStockBind(GoodsExcelImportDTO data,String goodsId,String skuId){
        List<CommonStockDTO.InnerChangeStockItem> items = new ArrayList<>();
        CommonStockDTO.InnerChangeStockItem stockItem = new CommonStockDTO.InnerChangeStockItem();
        stockItem.setGoodsId(goodsId);
        stockItem.setSkuId(skuId);
        stockItem.setQuantity(Integer.parseInt(data.getStockNum()));
        items.add(stockItem);

        initGoodsStock(data,items);
    }

    private void updateSpecInfo(GoodsExcelImportDTO data,String goodsId){
        List<PCMerchGoodsSpecInfoDTO.ETO> specList = getSpecInfoList(data.getSpecValue());
        specDictionaryService.updateSpecInfo(specList,goodsId);
    }

    private void addGoodsShopNavigation(String shopId,String name,String merchantId,String goodsId,Integer useFiled){
        CommonShopDTO.NavigationByDTO navigationByDTO = new CommonShopDTO.NavigationByDTO();
        navigationByDTO.setLevel(ArticleCategoryLevelEnum.TWO.getCode());
        navigationByDTO.setShopId(shopId);
        navigationByDTO.setNavName(name);
        navigationByDTO.setTerminal(useFiled);
        CommonShopVO.NavigationVO navigationVO = commonShopRpc.getNavigationVO(navigationByDTO);

        GoodsShopNavigation shopNavigation = new GoodsShopNavigation();
        shopNavigation.setMerchantId(merchantId);
        shopNavigation.setShopId(shopId);
        shopNavigation.setGoodsId(goodsId);
        shopNavigation.setShopNavigation(navigationVO.getNavigationTowId());
        shopNavigation.setTerminal(shopNavigation.getTerminal());
        shopNavigationService.saveInnerService(shopNavigation);
    }

    private String getDefaultImage(Integer imageType){
      String imageUrl =  serviceRpc.getDefaultImage(imageType);
      return imageUrl;
    }
}
