package com.gs.lshly.biz.support.trade.service.merchadmin.pc.impl;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.gs.lshly.biz.support.trade.entity.MarketPtSeckill;
import com.gs.lshly.biz.support.trade.entity.MarketPtSeckillGoodsCategory;
import com.gs.lshly.biz.support.trade.entity.MarketPtSeckillGoodsSku;
import com.gs.lshly.biz.support.trade.entity.MarketPtSeckillGoodsSpu;
import com.gs.lshly.biz.support.trade.entity.MarketPtSeckillMerchant;
import com.gs.lshly.biz.support.trade.repository.IMarketPtSeckillGoodsCategoryRepository;
import com.gs.lshly.biz.support.trade.repository.IMarketPtSeckillGoodsSkuRepository;
import com.gs.lshly.biz.support.trade.repository.IMarketPtSeckillGoodsSpuRepository;
import com.gs.lshly.biz.support.trade.repository.IMarketPtSeckillMerchantRepository;
import com.gs.lshly.biz.support.trade.repository.IMarketPtSeckillRepository;
import com.gs.lshly.biz.support.trade.service.merchadmin.pc.IPCMerchMarketPtSeckillMerchantService;
import com.gs.lshly.biz.support.trade.service.platadmin.IMarketPtSeckillService;
import com.gs.lshly.common.enums.SeckillSignEnum;
import com.gs.lshly.common.exception.BusinessException;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.merchadmin.pc.commodity.dto.PCMerchGoodsInfoDTO;
import com.gs.lshly.common.struct.merchadmin.pc.commodity.vo.PCMerchGoodsCategoryVO;
import com.gs.lshly.common.struct.merchadmin.pc.commodity.vo.PCMerchGoodsInfoVO;
import com.gs.lshly.common.struct.merchadmin.pc.commodity.vo.PCMerchSkuGoodInfoVO;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.dto.PCMerchMarketPtSeckillGoodsSpuDTO;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.dto.PCMerchMarketPtSeckillMerchantDTO;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.qto.PCMerchMarketPtSeckillMerchantQTO;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.vo.PCMerchMarketPtSeckillMerchantVO;
import com.gs.lshly.common.struct.platadmin.trade.dto.MarketPtSeckillDTO;
import com.gs.lshly.common.struct.platadmin.trade.vo.MarketPtSeckillVO;
import com.gs.lshly.middleware.mybatisplus.MybatisPlusUtil;
import com.gs.lshly.rpc.api.merchadmin.pc.commodity.IPCMerchAdminGoodsCategoryRpc;
import com.gs.lshly.rpc.api.merchadmin.pc.commodity.IPCMerchAdminGoodsInfoRpc;
import com.gs.lshly.rpc.api.merchadmin.pc.commodity.IPCMerchAdminSkuGoodInfoRpc;

import lombok.extern.slf4j.Slf4j;

/**
* <p>
*  服务实现类
 *
 *  活动记录
* </p>
* @author zdf
* @since 2020-12-01
*/
@Component
@Slf4j
public class PCMerchMarketPtSeckillMerchantServiceImpl implements IPCMerchMarketPtSeckillMerchantService {

    @Autowired
    private IMarketPtSeckillMerchantRepository repository;

    @Autowired
    private IMarketPtSeckillGoodsSpuRepository goodsSpuRepository;

    @Autowired
    private IMarketPtSeckillGoodsSkuRepository goodsSkuRepository;
    
//    @Autowired
//    private IMarketPtSeckillService iMarketPtSeckillService;
    @Autowired
    private IMarketPtSeckillRepository iMarketPtSeckillRepository;
    @Autowired
    private IMarketPtSeckillGoodsCategoryRepository iMarketPtSeckillGoodsCategoryRepository;
    @Autowired
    private IMarketPtSeckillGoodsSpuRepository iMarketPtSeckillGoodsSpuRepository;
    @Autowired
    private IMarketPtSeckillGoodsSkuRepository iMarketPtSeckillGoodsSkuRepository;
    @Autowired
    private IMarketPtSeckillMerchantRepository iMarketPtSeckillMerchantRepository;
    @DubboReference
    private IPCMerchAdminGoodsInfoRpc ipcMerchAdminGoodsInfoRpc;
    @DubboReference
    private IPCMerchAdminSkuGoodInfoRpc ipcMerchAdminSkuGoodInfoRpc;
    @DubboReference
    private IPCMerchAdminGoodsCategoryRpc ipcMerchAdminGoodsCategoryRpc;
    @Override
    public PageData<PCMerchMarketPtSeckillMerchantVO.ListVO> pageData(PCMerchMarketPtSeckillMerchantQTO.QTO qto) {
        QueryWrapper<MarketPtSeckillMerchant> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("cdate");
        IPage<MarketPtSeckillMerchant> page = MybatisPlusUtil.pager(qto);
        repository.page(page, wrapper);
        return MybatisPlusUtil.toPageData(qto,PCMerchMarketPtSeckillMerchantVO.ListVO.class, page);
    }

    @Override
    public void addMarketPtSeckillMerchant(PCMerchMarketPtSeckillMerchantDTO.ETO eto) {
        if (ObjectUtils.isEmpty(eto)){
            throw new BusinessException("请填写信息");
        }
        MarketPtSeckillMerchant marketPtSeckillMerchant = new MarketPtSeckillMerchant();
        BeanUtils.copyProperties(eto, marketPtSeckillMerchant);
        marketPtSeckillMerchant.setShopId(eto.getJwtShopId());
        marketPtSeckillMerchant.setMerchantId(eto.getJwtMerchantId());
        repository.save(marketPtSeckillMerchant);
    }


    @Override
    public void deleteMarketPtSeckillMerchant(PCMerchMarketPtSeckillMerchantDTO.IdDTO dto) {
        repository.removeById(dto.getId());
    }
    @Override
    public void editMarketPtSeckillMerchant(PCMerchMarketPtSeckillMerchantDTO.ETO eto) {
        MarketPtSeckillMerchant marketPtSeckillMerchant = new MarketPtSeckillMerchant();
        BeanUtils.copyProperties(eto, marketPtSeckillMerchant);
        repository.updateById(marketPtSeckillMerchant);
    }

    @Override
    public PCMerchMarketPtSeckillMerchantVO.DetailVO detailMarketPtSeckillMerchant(PCMerchMarketPtSeckillMerchantDTO.IdDTO dto) {
        MarketPtSeckillMerchant marketPtSeckillMerchant = repository.getById(dto.getId());
        PCMerchMarketPtSeckillMerchantVO.DetailVO detailVo = new PCMerchMarketPtSeckillMerchantVO.DetailVO();
        if(ObjectUtils.isEmpty(marketPtSeckillMerchant)){
            throw new BusinessException("没有数据");
        }
        BeanUtils.copyProperties(marketPtSeckillMerchant, detailVo);
        return detailVo;
    }

    @Override
    public List<PCMerchMarketPtSeckillMerchantVO.ListVO> queryCountRecord(PCMerchMarketPtSeckillMerchantDTO.isRecordDTO dto) {
        QueryWrapper<MarketPtSeckillMerchant> Wrapper = new QueryWrapper<>();
        Wrapper.eq(" seckill_id",dto.getSeckillId()).eq("shop_id",dto.getShopId()).eq("merchant_id",dto.getMerchantId());
        List<MarketPtSeckillMerchant> marketPtSeckillMerchants = repository.getBaseMapper().selectList(Wrapper);
        List<PCMerchMarketPtSeckillMerchantVO.ListVO> listVOS = new ArrayList<>();
        for (MarketPtSeckillMerchant marketPtSeckillMerchant:marketPtSeckillMerchants){
            PCMerchMarketPtSeckillMerchantVO.ListVO listVO = new PCMerchMarketPtSeckillMerchantVO.ListVO();
            BeanUtils.copyProperties(marketPtSeckillMerchant,listVO);
            listVOS.add(listVO);
        }
        return listVOS;
    }

    @Override
    public PageData<PCMerchMarketPtSeckillMerchantVO.MyMerchantSeckill> queryMyList(PCMerchMarketPtSeckillMerchantQTO.QTO qto) {
        QueryWrapper<MarketPtSeckillMerchant> Wrapper = new QueryWrapper<>();
        Wrapper.eq("shop_id",qto.getJwtShopId()).eq("merchant_id",qto.getJwtMerchantId());
        List<MarketPtSeckillMerchant> marketPtSeckillMerchants = repository.getBaseMapper().selectList(Wrapper);
        List<PCMerchMarketPtSeckillMerchantVO.MyMerchantSeckill> listVOS = new ArrayList<>();
        Long now = LocalDateTime.now().toInstant(ZoneOffset.of("+8")).toEpochMilli();
        for (MarketPtSeckillMerchant marketPtSeckillMerchant:marketPtSeckillMerchants){
            PCMerchMarketPtSeckillMerchantVO.MyMerchantSeckill listVO = new PCMerchMarketPtSeckillMerchantVO.MyMerchantSeckill();
            BeanUtils.copyProperties(marketPtSeckillMerchant,listVO);
            //设置日期格式
            DateTimeFormatter sdf=DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            listVO.setSignTime(marketPtSeckillMerchant.getSignStartTime().format(sdf)+"~"+marketPtSeckillMerchant.getSignStartTime().format(sdf));
            listVO.setSeckillTime(marketPtSeckillMerchant.getSeckillStartTime().format(sdf)+"~"+marketPtSeckillMerchant.getSeckillEndTime().format(sdf));
            if (qto.getIsHistory()==1){
                System.out.println("历史报名");
                Long end = marketPtSeckillMerchant.getSeckillEndTime().toInstant(ZoneOffset.of("+8")).toEpochMilli();
                if (end<now){
                    listVOS.add(listVO);
                }
            }else {
                listVOS.add(listVO);
            }
        }
        return new PageData<>(listVOS,qto.getPageNum(),qto.getPageSize(),listVOS.size());
    }

    @Override
    @Transactional
    public void merchantSeckillSign(PCMerchMarketPtSeckillGoodsSpuDTO.Sign dto) {
        /**
         * //添加活动记录表
         * */
        //获取活动ID
        QueryWrapper<MarketPtSeckillMerchant> query = MybatisPlusUtil.query();
        query.and(i->i.eq("shop_id",dto.getJwtShopId()));
        query.and(i->i.eq("seckill_id",dto.getSeckillId()));
        List<MarketPtSeckillMerchant> list1 = iMarketPtSeckillMerchantRepository.list(query);
        if (list1.size()>0){
            throw new BusinessException("此商家已参与该活动");
        }
        String SeckillId = dto.getSeckillId();
        MarketPtSeckill Seckill = iMarketPtSeckillRepository.getById(SeckillId);
        MarketPtSeckillMerchant marketPtSeckillMerchant = new MarketPtSeckillMerchant();
        BeanUtils.copyProperties(Seckill,marketPtSeckillMerchant);
        marketPtSeckillMerchant.setId(null);
        marketPtSeckillMerchant.setSeckillId(Seckill.getId());
        marketPtSeckillMerchant.setSeckillEndTime(Seckill.getSeckillEndTime()).
                setSeckillStartTime(Seckill.getSeckillStartTime()).
                setSignEndTime(Seckill.getSignEndTime()).
                setSignStartTime(Seckill.getSignStartTime()).
                setName(Seckill.getName()).
                setLabel(Seckill.getLabel()).
                setSeckillDescribe(Seckill.getSeckillDescribe()).
                setUserBuyMax(Seckill.getBuyMax()).
                setShopGoodsMax(Seckill.getGoodsMax()).
                setCoverImage(Seckill.getCoverImage()).
                setSmsBefore(Seckill.getSmsBefore()).
                setSmsIsTell(Seckill.getSmsIsTell());
        marketPtSeckillMerchant.
                setShopId(dto.getJwtShopId()).
                setMerchantId(dto.getJwtMerchantId()).
                setState(SeckillSignEnum.待审核.getCode().toString());
        repository.save(marketPtSeckillMerchant);
        //添加商品报名表
        if (ObjectUtils.isNotEmpty(dto.getGoodsAll())) {
            for (PCMerchMarketPtSeckillGoodsSpuDTO.SignGoods goods : dto.getGoodsAll()) {
                MarketPtSeckillGoodsSpu goodsSup = new MarketPtSeckillGoodsSpu();
                goodsSup.setSeckillDescribe(marketPtSeckillMerchant.getSeckillDescribe()).
                        setGoodsId(goods.getGoodsId()).
                        setSeckillId(SeckillId).
                        setLabel(marketPtSeckillMerchant.getLabel()).
                        setName(marketPtSeckillMerchant.getName()).
                        setShopId(dto.getJwtShopId()).
                        setMerchantId(dto.getJwtMerchantId()).
                        setSeckillSalePrice(ObjectUtils.isNotEmpty(goods.getSeckillSalePrice())?goods.getSeckillSalePrice():BigDecimal.ZERO);
                QueryWrapper<MarketPtSeckillGoodsSpu> wrapper = new QueryWrapper<>();
                wrapper.eq("shop_id", goodsSup.getShopId()).
                        eq("goods_id", goodsSup.getGoodsId());
                List<MarketPtSeckillGoodsSpu> list = goodsSpuRepository.list(wrapper);
                if (list.size() > 0) {
                    Boolean flag = true;
                    Long now = LocalDateTime.now().toInstant(ZoneOffset.of("+8")).toEpochMilli();
                    for (MarketPtSeckillGoodsSpu goodsTime : list) {
                        String timeout = goodsTime.getSeckillId();
                        MarketPtSeckill SeckillRepositoryById = iMarketPtSeckillRepository.getById(timeout);
                        if (ObjectUtils.isNotEmpty(SeckillRepositoryById)) {
                            Long endtime = SeckillRepositoryById.getSeckillEndTime().toInstant(ZoneOffset.of("+8")).toEpochMilli();
                            if (now < endtime) {
                                flag = false;
                            }
                        }
                    }
                    if (flag) {
                        goodsSpuRepository.save(goodsSup);
                        SetSku(goodsSup,dto,SeckillId);
                    } else {
                        throw new BusinessException("该商品正在参加其它的活动");
                    }
                } else {
                    goodsSpuRepository.save(goodsSup);
                    SetSku(goodsSup,dto,SeckillId);
                }
            }
        }


    }

    private void SetSku(MarketPtSeckillGoodsSpu goodsSup, PCMerchMarketPtSeckillGoodsSpuDTO.Sign dto,String SeckillId) {
        //添加商品SPU表
        if (ObjectUtils.isNotEmpty(dto.getGoodsSPUAll())) {
            for (PCMerchMarketPtSeckillGoodsSpuDTO.SignGoodsSku goodsSku : dto.getGoodsSPUAll()) {
                MarketPtSeckillGoodsSku goodsSKKu = new MarketPtSeckillGoodsSku();
                goodsSKKu.setSeckillId(SeckillId).
                        setSeckillSaleSkuPrice(goodsSku.getSeckillSalePrice()).
                        setGoodsId(goodsSku.getGoodsId()).
                        setGoodsSpuItemId(goodsSup.getId()).
                        setMerchantId(dto.getJwtMerchantId()).
                        setShopId(dto.getJwtShopId()).
                        setSkuId(goodsSku.getSkuId());
                goodsSkuRepository.save(goodsSKKu);
            }
        }
    }

    @Override
    public MarketPtSeckillVO.MerchantViewDetails viewMyOrHistoryDetails(PCMerchMarketPtSeckillMerchantDTO.IdDTO dto) {
        //获取商家参与记录
        MarketPtSeckillMerchant signRecord = repository.getById(dto.getId());
        //获取活动信息
        MarketPtSeckillVO.CheckSeckill checkSeckill= GetSeckillInformation(signRecord.getSeckillId());
        //参加活动商品
        List<MarketPtSeckillVO.SeckillGoods> SeckillGoods = ParticipateSeckillGoods(dto, signRecord.getSeckillId());
        return new MarketPtSeckillVO.MerchantViewDetails(checkSeckill,SeckillGoods);
    }

    private MarketPtSeckillVO.CheckSeckill GetSeckillInformation(String SeckillId) {
        MarketPtSeckill SeckillInfo = iMarketPtSeckillRepository.getById( SeckillId);
        MarketPtSeckillVO.CheckSeckill checkSeckill = new MarketPtSeckillVO.CheckSeckill();
        BeanUtils.copyProperties(SeckillInfo,checkSeckill);
        QueryWrapper<MarketPtSeckillGoodsCategory> wrapper = new QueryWrapper<>();
        wrapper.eq("seckill_id", SeckillId);
        List<MarketPtSeckillGoodsCategory> list = iMarketPtSeckillGoodsCategoryRepository.list(wrapper);
        if (ObjectUtils.isNotEmpty(list)) {
            StringBuffer categoryString = new StringBuffer();
            List<MarketPtSeckillVO.CategoryJoinSearchVO> caList=new ArrayList<>();
            for (int i = 0; i < list.size(); i++) {
                PCMerchGoodsCategoryVO.innerCategoryVO innerCategoryVO = ipcMerchAdminGoodsCategoryRpc.innerGetListVo(list.get(i).getCategoryId());

                if (ObjectUtils.isNotEmpty(innerCategoryVO)){
                    MarketPtSeckillVO.CategoryJoinSearchVO categoryJoinSearchVO = new MarketPtSeckillVO.CategoryJoinSearchVO();
                    categoryJoinSearchVO.setGsCategoryName(innerCategoryVO.getGsCategoryName());
                    categoryJoinSearchVO.setId(list.get(i).getCategoryId());
                    caList.add(categoryJoinSearchVO);
                }
                if (i == list.size() - 1) {
                    categoryString.append(list.get(i).getCategoryId());
                } else {
                    categoryString.append(list.get(i).getCategoryId()).append(",");
                }
            }
            checkSeckill.setSeckillCategory(categoryString.toString());
            checkSeckill.setSeckillCategoryName(caList);
        }
        return checkSeckill;
    }

    private List<MarketPtSeckillVO.SeckillGoods> ParticipateSeckillGoods(PCMerchMarketPtSeckillMerchantDTO.IdDTO dto, String SeckillId) {
        List<MarketPtSeckillVO.SeckillGoods> SeckillGoodsList=new ArrayList<>();

        QueryWrapper<MarketPtSeckillGoodsSpu> wrapper = new QueryWrapper<>();
        wrapper.eq("seckill_id",SeckillId).
                eq("shop_id",dto.getJwtShopId());
        List<MarketPtSeckillGoodsSpu> spuList = iMarketPtSeckillGoodsSpuRepository.list(wrapper);
        if (ObjectUtils.isNotEmpty(spuList)) {
            for (MarketPtSeckillGoodsSpu goodsId : spuList) {
                MarketPtSeckillVO.SeckillGoods SeckillGoods = new MarketPtSeckillVO.SeckillGoods();
                List<String> strings = new ArrayList<>();
                strings.add(goodsId.getGoodsId());
                List<PCMerchGoodsInfoVO.InnerServiceGoodsInfoVO> innerServiceGoodsInfoVOS=null;
                if (ObjectUtils.isNotEmpty(strings)) {
                    innerServiceGoodsInfoVOS = ipcMerchAdminGoodsInfoRpc.innerServiceAllGoodsInfo(new PCMerchGoodsInfoDTO.IdsInnerServiceDTO(strings, null, 10));
                }
                PCMerchGoodsInfoVO.InnerServiceGoodsInfoVO innerServiceGoodsInfoVO=null;
                if (ObjectUtils.isNotEmpty(innerServiceGoodsInfoVOS)){
                    innerServiceGoodsInfoVO = innerServiceGoodsInfoVOS.get(0);
                }
                if (ObjectUtils.isNotEmpty(innerServiceGoodsInfoVO)){
                    SeckillGoods .setImageUrl(innerServiceGoodsInfoVO.getGoodsImage()).
                            setName(innerServiceGoodsInfoVO.getGoodsName()).
                            setSalePrice(innerServiceGoodsInfoVO.getSalePrice());
                }
                QueryWrapper<MarketPtSeckillGoodsSku> query = MybatisPlusUtil.query();
                query.and(i->i.eq("goods_spu_item_id",goodsId.getId()));
                List<MarketPtSeckillGoodsSku> list = goodsSkuRepository.list(query);
                if (ObjectUtils.isNotEmpty(list)) {
                    List<MarketPtSeckillVO.SeckillGoodsSku> skuList=new ArrayList<>();
                    for (MarketPtSeckillGoodsSku marketPtSeckillGoodsSku : list) {

                        if (ObjectUtils.isNotEmpty(innerServiceGoodsInfoVO)){
                            for (PCMerchSkuGoodInfoVO.ListVO listVO : innerServiceGoodsInfoVO.getSkuList()) {
                                if (listVO.getId().equals(marketPtSeckillGoodsSku.getSkuId())){
                                    System.out.println(listVO+"~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                                    MarketPtSeckillVO.SeckillGoodsSku SeckillGoodsSku = new MarketPtSeckillVO.SeckillGoodsSku();
                                    SeckillGoodsSku.setSeckillSaleSkuPrice(marketPtSeckillGoodsSku.getSeckillSaleSkuPrice()).
                                            setId(marketPtSeckillGoodsSku.getId()).
                                            setImageUrl(listVO.getImage()).
                                            setSpecsValue(listVO.getSpecsValue()).
                                            setName(innerServiceGoodsInfoVO.getGoodsName());
                                    skuList.add(SeckillGoodsSku);
                                }
                            }
                        }
                    }
                    SeckillGoods.setSkuInfo(skuList);

                }
                SeckillGoods.setSeckillSalePrice(goodsId.getSeckillSalePrice()).
                        setId(goodsId.getId());
                SeckillGoodsList.add(SeckillGoods);
            }
        }

        return SeckillGoodsList;
    }

    @Override
    public MarketPtSeckillVO.MerchantViewDetails viewSeckillListDetails(MarketPtSeckillDTO.IdDTO dto) {
        QueryWrapper<MarketPtSeckillMerchant> wrapper = new QueryWrapper<>();
        wrapper.eq("seckill_id",dto.getId()).
                eq("shop_id",dto.getJwtShopId()).
                eq("merchant_id",dto.getJwtMerchantId());
        MarketPtSeckillMerchant one = repository.getOne(wrapper);
        MarketPtSeckillVO.CheckSeckill checkSeckill = GetSeckillInformation(dto.getId());
        if (!ObjectUtils.isEmpty(one)){
            List<MarketPtSeckillVO.SeckillGoods> SeckillGoods = ParticipateSeckillGoods(new PCMerchMarketPtSeckillMerchantDTO.IdDTO(one.getId()), dto.getId());
            return new MarketPtSeckillVO.MerchantViewDetails(checkSeckill,SeckillGoods);
        }
        return new MarketPtSeckillVO.MerchantViewDetails(checkSeckill,null);
    }
}
