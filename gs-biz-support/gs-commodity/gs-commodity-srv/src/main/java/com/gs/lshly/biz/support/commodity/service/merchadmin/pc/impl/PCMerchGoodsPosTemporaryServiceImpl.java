package com.gs.lshly.biz.support.commodity.service.merchadmin.pc.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.gs.lshly.biz.support.commodity.entity.*;
import com.gs.lshly.biz.support.commodity.repository.*;
import com.gs.lshly.biz.support.commodity.service.merchadmin.pc.IPCMerchGoodsPosTemporaryService;
import com.gs.lshly.common.enums.GoodsStateEnum;
import com.gs.lshly.common.enums.TerminalEnum;
import com.gs.lshly.common.enums.commondity.GoodsIsReleaseEnum;
import com.gs.lshly.common.exception.BusinessException;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.common.struct.common.CommonShopVO;
import com.gs.lshly.common.struct.common.CommonStockVO;
import com.gs.lshly.common.struct.merchadmin.pc.commodity.dto.*;
import com.gs.lshly.common.struct.merchadmin.pc.commodity.qto.PCMerchGoodsPosTemporaryQTO;
import com.gs.lshly.common.struct.merchadmin.pc.commodity.vo.*;
import com.gs.lshly.common.struct.platadmin.foundation.vo.PicturesVO;
import com.gs.lshly.common.utils.BASE64DecodedMultipartFileUtil;
import com.gs.lshly.common.utils.ListUtil;
import com.gs.lshly.middleware.oss.service.IFileService;
import com.gs.lshly.rpc.api.common.ICommonShopRpc;
import com.gs.lshly.rpc.api.common.ICommonStockRpc;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.gs.lshly.middleware.mybatisplus.MybatisPlusUtil;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import sun.misc.BASE64Decoder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


/**
* <p>
*  服务实现类
* </p>
* @author Starry
* @since 2021-02-23
*/
@Component
public class PCMerchGoodsPosTemporaryServiceImpl implements IPCMerchGoodsPosTemporaryService {

    @Autowired
    private IGoodsPosTemporaryRepository repository;

    @Autowired
    private ISkuGoodInfoRepository skuGoodInfoRepository;

    @Autowired
    private IGoodsInfoRepository goodsInfoRepository;

    @Autowired
    private IGoodsSpecInfoRepository specInfoRepository;

    @Autowired
    private IGoodsTempalteRepository tempalteRepository;

    @Autowired
    private IGoodsShopNavigationRepository shopNavigationRepository;

    @Autowired
    private IGoodsAttributeInfoRepository attributeInfoRepository;

    @Autowired
    private IFileService fileService;


    @DubboReference
    private ICommonShopRpc commonShopRpc;

    @DubboReference
    private ICommonStockRpc commonStockRpc;

    @Override
    public PageData<PCMerchGoodsPosTemporaryVO.ListVO> pageData(PCMerchGoodsPosTemporaryQTO.QTO qto) {
        QueryWrapper<GoodsPosTemporary> wrapper = MybatisPlusUtil.query();
        //通过店铺id查询门店id
        CommonShopVO.SimpleVO simpleVO = commonShopRpc.shopDetails(qto.getJwtShopId());
        if (ObjectUtils.isEmpty(simpleVO)){
            throw new BusinessException("店铺数据不存在或者查询异常！");
        }
        if(StringUtils.isBlank(simpleVO.getPosShopId())){
            return new PageData<>();
        }
        wrapper.eq("pos_code",simpleVO.getPosShopId());

        IPage<GoodsPosTemporary> page = MybatisPlusUtil.pager(qto);
        repository.page(page, wrapper);
        return MybatisPlusUtil.toPageData(qto, PCMerchGoodsPosTemporaryVO.ListVO.class, page);
    }

    @Override
    public void addGoodsPosTemporary(PCMerchGoodsPosTemporaryDTO.ETO eto) {
        System.out.println("eto : "+JSONObject.toJSONString(eto));
        //数据校验
        checkData(eto);

        //通过spuId以及posCode查询该类商品是否已同步到临时数据库中
        QueryWrapper<GoodsPosTemporary> wrapper = MybatisPlusUtil.query();
        wrapper.eq("pos_code",eto.getPosCode());
        wrapper.eq("spu_id",eto.getSpuId());
        List<GoodsPosTemporary> posTemporaries = repository.list(wrapper);
        /**
         *若该类商品前面已同步过到临时数据库中
         * 1.判断是否该商品同步到临时数据库中，若有该商品则做以下处理
         * 2.然后判断该商品是否已发布了
         * 3.若未发布则不做其他处理
         * 4.若已经发布，则先通过该spuId查询到本系统的商品，并将查询到的商品做下架处理，以及更新本系统查询到的商品相关数据
         * 5.若没有通过该spuId查询到本系统商品，则不做任何处理
         * 6.更新该类商品的临时库数据
         */
        if (ObjectUtils.isNotEmpty(posTemporaries)){
            QueryWrapper<GoodsPosTemporary> temporaryWrapper = MybatisPlusUtil.query();
            temporaryWrapper.eq("pos_code",eto.getPosCode());
            temporaryWrapper.eq("spu_id",eto.getSpuId());
            temporaryWrapper.eq("pos_sKU69_code",eto.getPosSku69Code());
            GoodsPosTemporary posTemporary = repository.getOne(temporaryWrapper);
            if (null != posTemporary){
                handleSynchronizeData(posTemporary,eto);
                eto.setId(posTemporary.getId());
            }else {
                /**
                 * 若没有该商品的数据而临时库中有该spuId,则判断传过来的参数是否符合多规格的参数条件
                 * 1.若符合则同步骤2.3.4.5
                 * 2.若不符合则抛异常
                 */
                if (StringUtils.isBlank(eto.getSpecName())){
                    throw new BusinessException("多规格商品规格名称不能为空！");
                }
                if (StringUtils.isBlank(eto.getSpecValue())){
                    throw new BusinessException("多规格商品规格值不能为空！");
                }
                eto.setIsRelease(GoodsIsReleaseEnum.未发布.getCode());
            }
            saveInfo(eto);

        }
        //如果该POS门店的商品从未同步则只做同步到临时库处理
        if (ObjectUtils.isEmpty(posTemporaries)){
           eto.setIsRelease(GoodsIsReleaseEnum.未发布.getCode());
           saveInfo(eto);
        }
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteGoodsPosTemporary(PCMerchGoodsPosTemporaryDTO.IdListDTO dto) {
        if (ObjectUtils.isEmpty(dto) || ObjectUtils.isEmpty(dto.getIdList())){
            throw new BusinessException("请选择要删除的商品！");
        }
        QueryWrapper<GoodsPosTemporary> wrapper = MybatisPlusUtil.query();
        wrapper.in("id",dto.getIdList());
        repository.remove(wrapper);
    }

    @Override
    public PCMerchGoodsInfoVO.EditDetailVO getEditDetailEto(PCMerchGoodsPosTemporaryDTO.PosSpuIdDTO dto) {
        if (dto == null) {
            throw new BusinessException("参数不能为空！");
        }
        List<GoodsPosTemporary> posTemporaries = getPosTemporaryList(dto.getSpuId(),null);
        List<GoodsPosTemporary> posTemporaryList = getPosTemporaryList(dto.getSpuId(),GoodsIsReleaseEnum.未发布.getCode());
        GoodsPosTemporary posTemporary = posTemporaries.get(0);


        PCMerchGoodsInfoVO.EditDetailVO detailVO = new PCMerchGoodsInfoVO.EditDetailVO();
        QueryWrapper<GoodsInfo> wrapper = MybatisPlusUtil.query();
        wrapper.eq("pos_spu_id",dto.getSpuId());
        GoodsInfo goodsInfo = goodsInfoRepository.getOne(wrapper);
        if (null != goodsInfo){
            //获取商品信息
            BeanUtils.copyProperties(goodsInfo,detailVO);

            //商品拓展属性列表
            List<PCMerchGoodsAttributeInfoVO.ListVO> attributeList = getAttributeListVO(goodsInfo.getId());
            if (ObjectUtils.isNotEmpty(attributeList)){
                detailVO.setAttributeList(attributeList);
            }

            //运费模板ID
            QueryWrapper<GoodsTempalte> tempalteBoost = MybatisPlusUtil.query();
            tempalteBoost.eq("goods_id",goodsInfo.getId());
            GoodsTempalte tempalte = tempalteRepository.getOne(tempalteBoost);
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

            //sku商品列表
            List<PCMerchSkuGoodInfoVO.DetailVO> skuList = getSkuInfo(goodsInfo.getId(),goodsInfo.getShopId(),posTemporaryList);
            if (ObjectUtils.isNotEmpty(skuList) && StringUtils.isNotEmpty(skuList.get(0).getSpecsKey())){
                detailVO.setSkuVoList(skuList);
            }
            if (posTemporaries.size() > 1){
                detailVO.setSpecList(getSpecInfoList(posTemporaries));
            }

            //商品拓展规格列表
//            List<PCMerchGoodsSpecInfoVO.ListVO> specList = getSpecListVO(goodsInfo.getId(),posTemporaries);
//            if (ObjectUtils.isNotEmpty(specList)){
//                detailVO.setSpecList(specList);
//            }

            // spu库存
            detailVO.setSpuStock(getSpuStockNum(goodsInfo.getId(),dto.getJwtShopId()));

        }else {
            detailVO.setPosSpuId(dto.getSpuId());
            detailVO.setGoodsName(posTemporary.getName());
            detailVO.setSalePrice(posTemporary.getPrice());
            detailVO.setGoodsImage(posTemporary.getImages());
            //判断是否为多规格商品
            if (StringUtils.isNotBlank(posTemporary.getSpecName())){
               detailVO.setSkuVoList(getSkuInfoList(posTemporaries));
               detailVO.setSpecList(getSpecInfoList(posTemporaries));
            }
        }
        return detailVO;
    }


    @Override
    public List<PCMerchGoodsPosTemporaryVO.ListVO> getExportData(PCMerchGoodsPosTemporaryDTO.IdListDTO dto) {
        QueryWrapper<GoodsPosTemporary> wrapper = MybatisPlusUtil.query();
        wrapper.in("id",dto.getIdList());
        List<GoodsPosTemporary> goodsPosTemporaries = repository.list(wrapper);
        if (ObjectUtils.isNotEmpty(goodsPosTemporaries)){
            List<PCMerchGoodsPosTemporaryVO.ListVO> detailVOS = ListUtil.listCover(PCMerchGoodsPosTemporaryVO.ListVO.class,goodsPosTemporaries);
            return detailVOS;
        }
        return new ArrayList<>();
    }

    @Override
    public void modifyReleaseState(PCMerchGoodsPosTemporaryDTO.IdDTO dto) {
        if (null == dto || StringUtils.isBlank(dto.getId())){
            throw new BusinessException("iD不能为空！");
        }
        QueryWrapper<GoodsPosTemporary> wrapper = MybatisPlusUtil.query();
        wrapper.eq("id",dto.getId());

        GoodsPosTemporary posTemporary = new GoodsPosTemporary();
        posTemporary.setIsRelease(GoodsIsReleaseEnum.已发布.getCode());

        repository.update(posTemporary,wrapper);
    }

    private void checkData(PCMerchGoodsPosTemporaryDTO.ETO eto){
        if (ObjectUtils.isEmpty(eto)){
            throw new BusinessException("参数不能为空异常！");
        }
        if (StringUtils.isBlank(eto.getTimestamp())){
            throw new BusinessException("当前时间毫秒数不能为空！");
        }
        if (StringUtils.isBlank(eto.getNonce())){
            throw new BusinessException("6位随机字符串不能为空！");
        }
        if (StringUtils.isBlank(eto.getPosCode())){
            throw new BusinessException("店铺编号不能为空！");
        }
        if (StringUtils.isBlank(eto.getPosSku69Code())){
            throw new BusinessException("店铺sku69码不能为空！");
        }
        if (StringUtils.isBlank(eto.getSpuId())){
            throw new BusinessException("spuID不能为空！");
        }
        if (StringUtils.isBlank(eto.getName())){
            throw new BusinessException("商品名称不能为空！");
        }
        if (StringUtils.isBlank(eto.getStockChangeSerialNo())){
            throw new BusinessException("库存流水变更号不能为空！");
        }
        if (ObjectUtils.isEmpty(eto.getTotalStock())){
            throw new BusinessException("库存数量不能为空！");
        }
    }

    private void saveInfo(PCMerchGoodsPosTemporaryDTO.ETO eto){
        GoodsPosTemporary goodsPosTemporary = new GoodsPosTemporary();
        BeanUtils.copyProperties(eto, goodsPosTemporary);
        if (ObjectUtils.isNotEmpty(eto.getImages())){
            String imags = eto.getImages().replaceAll("img","imgSrc");
            imags = getImage(imags);
            goodsPosTemporary.setImages(imags);
        }
        repository.saveOrUpdate(goodsPosTemporary);
    }

    private void handleSynchronizeData(GoodsPosTemporary temporary,PCMerchGoodsPosTemporaryDTO.ETO eto){
        if (temporary.getIsRelease().equals(GoodsIsReleaseEnum.已发布.getCode())){
            QueryWrapper<GoodsInfo> wrapper = MybatisPlusUtil.query();
            wrapper.eq("pos_spu_id",temporary.getSpuId());
            GoodsInfo goodsInfo = goodsInfoRepository.getOne(wrapper);
            if (null != goodsInfo){
                QueryWrapper<SkuGoodInfo> wrapper1 = MybatisPlusUtil.query();
                wrapper1.eq("pos_spu_id",temporary.getSpuId());
                wrapper1.eq("good_id",goodsInfo.getId());


                GoodsInfo info = new GoodsInfo();
                info.setGoodsState(GoodsStateEnum.未上架.getCode());
                info.setId(goodsInfo.getId());
                if (StringUtils.isNotBlank(eto.getImages())){
                    String images = eto.getImages().replaceAll("img","imgSrc");
                    images = getImage(images);
                    info.setGoodsImage(images);
                }
                info.setGoodsName(eto.getName());
                goodsInfoRepository.updateById(info);

                UpdateWrapper<SkuGoodInfo> updateWrapper = MybatisPlusUtil.update();
                updateWrapper.eq("good_id",goodsInfo.getId());
                SkuGoodInfo sku = new SkuGoodInfo();
                sku.setSalePrice(eto.getPrice());
                sku.setState(GoodsStateEnum.未上架.getCode());
                skuGoodInfoRepository.update(sku,updateWrapper);

            }

        }

    }


    public static MultipartFile base64ToMultipart(String base64) {
        try {
            String[] baseStrs = base64.split(",");

            BASE64Decoder decoder = new BASE64Decoder();
            byte[] b = new byte[0];
            b = decoder.decodeBuffer(baseStrs[1]);

            for (int i = 0; i < b.length; ++i) {
                if (b[i] < 0) {
                    b[i] += 256;
                }
            }

            return new BASE64DecodedMultipartFileUtil(b, baseStrs[0]);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private String getImage(String images) {
        if (StringUtils.isNotBlank(images)) {
            JSONArray arr = JSONArray.parseArray(images);
            if (ObjectUtils.isEmpty(arr)) {
                return null;
            }
            List<PCMerchGoodsPosTemporaryVO.ImageVO> imageVOS = new ArrayList<>();
            for (int i = 0; i<arr.size();i++){
                PCMerchGoodsPosTemporaryVO.ImageVO imageVO = new PCMerchGoodsPosTemporaryVO.ImageVO();
                JSONObject obj = arr.getJSONObject(i);
                String imgUrlBase64 = obj.getString("imgSrc");
                if (ObjectUtils.isNotEmpty(imgUrlBase64)){
                    PicturesVO.DetailVO  detailVO = fileService.upload(base64ToMultipart(imgUrlBase64));
                    if (null != detailVO && StringUtils.isNotBlank(detailVO.getImageUrl())){
                        imageVO.setImgSrc(detailVO.getImageUrl());
                        imageVOS.add(imageVO);
                    }
                }

            }
            String img = JSONObject.toJSONString(imageVOS);

            return img;
        }
        return null;
    }

    private List<PCMerchGoodsSpecInfoVO.ListVO> getSpecListVO(String goodsId,List<GoodsPosTemporary> posTemporaries){
        QueryWrapper<GoodsSpecInfo> boost = MybatisPlusUtil.query();
        boost.eq("good_id",goodsId);
        List<GoodsSpecInfo> specInfos = specInfoRepository.list(boost);
        List<PCMerchGoodsSpecInfoVO.ListVO> listVOS = new ArrayList<>();
        if (ObjectUtils.isNotEmpty(specInfos)){
            for (GoodsSpecInfo goodsSpecInfo : specInfos){
                PCMerchGoodsSpecInfoVO.ListVO listVO = new PCMerchGoodsSpecInfoVO.ListVO();
                BeanUtils.copyProperties(goodsSpecInfo,listVO);
                listVOS.add(listVO);
            }
        }
        return listVOS;
    }

    private  List<PCMerchGoodsAttributeInfoVO.ListVO> getAttributeListVO(String goodsId){
        QueryWrapper<GoodsAttributeInfo> boost = MybatisPlusUtil.query();
        boost.eq("good_id",goodsId);
        List<GoodsAttributeInfo> attributeInfos = attributeInfoRepository.list(boost);
        List<PCMerchGoodsAttributeInfoVO.ListVO> listVOS = new ArrayList<>();
        if (ObjectUtils.isNotEmpty(attributeInfos)){
            for (GoodsAttributeInfo goodsAttributeInfo : attributeInfos){
                PCMerchGoodsAttributeInfoVO.ListVO listVO = new PCMerchGoodsAttributeInfoVO.ListVO();
                BeanUtils.copyProperties(goodsAttributeInfo,listVO);
                listVOS.add(listVO);
            }
            return listVOS;
        }
        return listVOS;
    }

    private List<PCMerchSkuGoodInfoVO.DetailVO> getSkuInfo(String goodsId,String shopId,List<GoodsPosTemporary> posTemporaries){
        QueryWrapper<SkuGoodInfo> skuBoost = MybatisPlusUtil.query();
        skuBoost.eq("good_id",goodsId);
        List<SkuGoodInfo> skuGoodInfos = skuGoodInfoRepository.list(skuBoost);
        List<PCMerchSkuGoodInfoVO.DetailVO> detailVOS = new ArrayList<>();
        if (ObjectUtils.isNotEmpty(skuGoodInfos)){
            for (SkuGoodInfo skuGoodInfo : skuGoodInfos){
                PCMerchSkuGoodInfoVO.DetailVO detailVO = new PCMerchSkuGoodInfoVO.DetailVO();
                BeanUtils.copyProperties(skuGoodInfo,detailVO);
                //获取sku存数量
                detailVO.setSkuStock(getSkuStockNum(shopId,skuGoodInfo.getId()));
                detailVOS.add(detailVO);
            }
            return detailVOS;
        }
        if (ObjectUtils.isNotEmpty(posTemporaries)){
            detailVOS.addAll(getSkuInfoList(posTemporaries));
        }

        return detailVOS;
    }

    private List<PCMerchSkuGoodInfoVO.DetailVO> getSkuInfoList(List<GoodsPosTemporary> posTemporaries){
        List<PCMerchSkuGoodInfoVO.DetailVO> detailVOS = new ArrayList<>();
        for (GoodsPosTemporary posTemporary : posTemporaries){
            PCMerchSkuGoodInfoVO.DetailVO detailVO = new PCMerchSkuGoodInfoVO.DetailVO();
            //获取sku存数量
            detailVO.setSkuGoodsNo(posTemporary.getPosSku69Code());
            detailVO.setSalePrice(posTemporary.getPrice());
            detailVO.setPosSpuId(posTemporary.getSpuId());
            String image = getSkuImage(posTemporary.getImages());
            detailVO.setImage(StringUtils.isBlank(image)?"":image);
            detailVO.setSpecsValue(posTemporary.getSpecValue());
            detailVO.setSkuStock(posTemporary.getTotalStock());
            detailVOS.add(detailVO);
        }
        return detailVOS;
    }

    private List<PCMerchGoodsSpecInfoVO.ListVO> getSpecInfoList(List<GoodsPosTemporary> posTemporaries){
        List<PCMerchGoodsSpecInfoVO.ListVO> listVOS = new ArrayList<>();
        List<String> specValues = posTemporaries.stream().map(GoodsPosTemporary::getSpecValue).distinct().collect(Collectors.toList());
        List<String> strings = new ArrayList<>();
        for (String specValue : specValues){
            List<String> list = Arrays.asList(specValue.split(","));
            strings.addAll(list);
        }
        strings = strings.stream().distinct().collect(Collectors.toList());
        for (int i =0;i<strings.size();i++){
            PCMerchGoodsSpecInfoVO.ListVO listVO = new PCMerchGoodsSpecInfoVO.ListVO();
            String [] strings1 = strings.get(i).split(":");
            listVO.setSpecValue(strings1[1]);
            listVO.setSpecName(strings1[0]);
            listVOS.add(listVO);
        }
        //按规格名称分组
        Map<String,List<PCMerchGoodsSpecInfoVO.ListVO>> map = listVOS.stream().collect(Collectors.groupingBy(PCMerchGoodsSpecInfoVO.ListVO::getSpecName));
        //组装
        listVOS = map.values().stream().map(e ->{
            PCMerchGoodsSpecInfoVO.ListVO specListVO = new PCMerchGoodsSpecInfoVO.ListVO();
            List<String> stringList = e.stream().map(PCMerchGoodsSpecInfoVO.ListVO::getSpecValue).collect(Collectors.toList());
            specListVO.setSpecName(e.get(0).getSpecName());
            specListVO.setSpecValue(StringUtils.join(stringList.toArray(),","));
            return specListVO;
        }).collect(Collectors.toList());
        return listVOS;

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

    private  String getSkuImage(String images){
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

    private List<GoodsPosTemporary>  getPosTemporaryList(String spuId,Integer releaseState){
        QueryWrapper<GoodsPosTemporary> temporaryWrapper = MybatisPlusUtil.query();
        temporaryWrapper.eq("spu_id",spuId);
        if (ObjectUtils.isNotEmpty(releaseState)){
            temporaryWrapper.eq("is_release",releaseState);
        }
        List<GoodsPosTemporary> posTemporaries = repository.list(temporaryWrapper);
        if (ObjectUtils.isEmpty(posTemporaries)){
            throw new BusinessException("数据查询异常！");
        }
        return posTemporaries;
    }


}
