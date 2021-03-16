package com.gs.lshly.biz.support.merchant.service.merchadmin.pc.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gs.lshly.biz.support.merchant.complex.ShopFloorComplexEditor;
import com.gs.lshly.biz.support.merchant.complex.ShopFloorComplexQuery;
import com.gs.lshly.biz.support.merchant.entity.ShopFloor;
import com.gs.lshly.biz.support.merchant.entity.ShopFloorGoods;
import com.gs.lshly.biz.support.merchant.mapper.ShopFloorGoodsMapper;
import com.gs.lshly.biz.support.merchant.repository.IShopFloorGoodsRepository;
import com.gs.lshly.biz.support.merchant.repository.IShopFloorRepository;
import com.gs.lshly.biz.support.merchant.service.merchadmin.pc.IPCMerchShopFloorService;
import com.gs.lshly.common.enums.GoodsQueryTypeEnum;
import com.gs.lshly.common.enums.PcH5Enum;
import com.gs.lshly.common.enums.TrueFalseEnum;
import com.gs.lshly.common.exception.BusinessException;
import com.gs.lshly.common.struct.merchadmin.pc.commodity.dto.PCMerchGoodsInfoDTO;
import com.gs.lshly.common.struct.merchadmin.pc.commodity.vo.PCMerchGoodsInfoVO;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.dto.PCMerchShopFloorDTO;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.qto.PCMerchShopFloorQTO;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.vo.PCMerchShopFloorVO;
import com.gs.lshly.common.utils.BeanCopyUtils;
import com.gs.lshly.common.utils.ListUtil;
import com.gs.lshly.middleware.mybatisplus.MybatisPlusUtil;
import com.gs.lshly.rpc.api.merchadmin.pc.commodity.IPCMerchAdminGoodsInfoRpc;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
* <p>
*  服务实现类
* </p>
* @author xxfc
* @since 2020-11-05
*/
@Component
public class PCMerchShopFloorServiceImpl implements IPCMerchShopFloorService {

    @Autowired
    private IShopFloorRepository repository;

    @Autowired
    private ShopFloorGoodsMapper shopFloorGoodsMapper;

    @Autowired
    private IShopFloorGoodsRepository shopFloorGoodsRepository;

    @DubboReference
    private IPCMerchAdminGoodsInfoRpc pCMerchAdminGoodsInfoRpc;

    @Override
    public List<PCMerchShopFloorVO.H5ListVO> h5List(PCMerchShopFloorQTO.H5QTO qto) {

        QueryWrapper<ShopFloor> wrapper = new QueryWrapper<>();
        wrapper.eq("terminal",qto.getTerminal());
        wrapper.eq("pc_show", PcH5Enum.NO.getCode());
        wrapper.eq("shop_id",qto.getJwtShopId());
        wrapper.orderByAsc("idx");
        List<ShopFloor> floorList = repository.list(wrapper);
        List<String> floorIdList = new ArrayList<>();
        Map<String, ShopFloorComplexQuery.ComplexMerchadminH5> voMap = new HashMap<>();

        for(ShopFloor floorItem:floorList){
            floorIdList.add(floorItem.getId());
            ShopFloorComplexQuery.ComplexMerchadminH5 complex = new ShopFloorComplexQuery.ComplexMerchadminH5();
            complex.setFloorGoodsList(new ArrayList<>());
            PCMerchShopFloorVO.H5ListVO listVO = new  PCMerchShopFloorVO.H5ListVO();
            listVO.setGoodsItemList(new ArrayList<>());
            BeanCopyUtils.copyProperties(floorItem,listVO);
            complex.setFloorVO(listVO);
            voMap.put(floorItem.getId(),complex);
        }
        if(ObjectUtils.isNotEmpty(floorIdList)){
            QueryWrapper<ShopFloorGoods> shopFloorGoodsQueryWrapper = MybatisPlusUtil.query();
            shopFloorGoodsQueryWrapper.in("shop_floor_id",floorIdList);
            List<ShopFloorGoods> floorGoodsList = shopFloorGoodsRepository.list(shopFloorGoodsQueryWrapper);
            List<String> goodsIdList = new ArrayList<>();
            for(ShopFloorGoods shopFloorGoodsItem:floorGoodsList){
                  //一个商品在多个楼层,商品服务只查一个就可以,先去重
                  if(!goodsIdList.contains(shopFloorGoodsItem.getGoodsId())){
                      goodsIdList.add(shopFloorGoodsItem.getGoodsId());
                  }
                  ShopFloorComplexQuery.ComplexMerchadminH5 complex = voMap.get(shopFloorGoodsItem.getShopFloorId());
                  if(null != complex){
                      complex.getFloorGoodsList().add(shopFloorGoodsItem);
                  }
            }

            if(ObjectUtils.isNotEmpty(goodsIdList)){
                PCMerchGoodsInfoDTO.IdsInnerServiceDTO innerDTO = new PCMerchGoodsInfoDTO.IdsInnerServiceDTO();
                innerDTO.setGoodsIdList(goodsIdList);
                innerDTO.setQueryType(GoodsQueryTypeEnum.商品id.getCode());
                List<PCMerchGoodsInfoVO.InnerServiceGoodsVO> innerGoodsList = pCMerchAdminGoodsInfoRpc.innerServiceGoodsVO(innerDTO);
                for(ShopFloorComplexQuery.ComplexMerchadminH5 complex:voMap.values()){
                    for(ShopFloorGoods floorGoodsItem:complex.getFloorGoodsList()){
                        //商品信息
                        for(PCMerchGoodsInfoVO.InnerServiceGoodsVO innerGoodsItem:innerGoodsList){
                            if(floorGoodsItem.getGoodsId().equals(innerGoodsItem.getId())){
                                PCMerchShopFloorVO.GoodsItem goodsItemVO = new PCMerchShopFloorVO.GoodsItem();
                                goodsItemVO.setId(innerGoodsItem.getId());
                                goodsItemVO.setGoodsName(innerGoodsItem.getGoodsName());
                                complex.getFloorVO().getGoodsItemList().add(goodsItemVO);
                            }
                        }
                    }
                }
            }
            List<PCMerchShopFloorVO.H5ListVO> voList = new ArrayList<>();
            for(ShopFloorComplexQuery.ComplexMerchadminH5 complex:voMap.values()){
                voList.add(complex.getFloorVO());
            }
            return voList;
        }
        return new ArrayList<>();
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public void h5Editor(PCMerchShopFloorDTO.H5ETO h5ETO) {
        if(ObjectUtils.isNotEmpty(h5ETO.getFloorList())){
            List<ShopFloor> shopFloorList =new ArrayList<>();
            List<ShopFloorComplexEditor> comlexList = new ArrayList<>();
            h5ETO.getFloorList().forEach(itemDTO ->{
                ShopFloor shopFloor = new ShopFloor();
                BeanCopyUtils.copyProperties(itemDTO, shopFloor);
                shopFloor.setPcShow(PcH5Enum.NO.getCode());
                shopFloor.setTerminal(h5ETO.getTerminal());
                shopFloor.setShopId(h5ETO.getJwtShopId());
                shopFloor.setMerchantId(h5ETO.getJwtMerchantId());
                if (TrueFalseEnum.是.getCode().equals(itemDTO.getIsNew())){
                    shopFloor.setId(null);
                }else{
                    if(ObjectUtils.isNotEmpty(h5ETO.getRemoveIdList())){
                        if(h5ETO.getRemoveIdList().contains(shopFloor.getId())){
                            throw new BusinessException("保存的数据和删除的数据冲突");
                        }
                    }
                }
                shopFloorList.add(shopFloor);
                ShopFloorComplexEditor comlex = new ShopFloorComplexEditor();
                comlex.setItemDTO(itemDTO);
                comlex.setShopFloor(shopFloor);
                comlexList.add(comlex);
            });
            repository.saveOrUpdateBatch(shopFloorList);
            List<ShopFloorGoods> shopFloorGoodsList = new ArrayList<>();
            for(ShopFloorComplexEditor comlexItem:comlexList){
                if(ObjectUtils.isNotEmpty(comlexItem.getItemDTO().getGoodsIdList())){
                    int idx = 0;
                    for(String goodsId:comlexItem.getItemDTO().getGoodsIdList()){
                        ShopFloorGoods floorGoodsItem = new ShopFloorGoods();
                        floorGoodsItem.setShopFloorId(comlexItem.getShopFloor().getId());
                        floorGoodsItem.setGoodsId(goodsId);
                        floorGoodsItem.setIdx(idx);
                        floorGoodsItem.setShopId(h5ETO.getJwtShopId());
                        floorGoodsItem.setMerchantId(h5ETO.getJwtMerchantId());
                        shopFloorGoodsList.add(floorGoodsItem);
                        idx ++;
                    }
                }
            }
            List<String> clearFloorIdList = ListUtil.getIdList(ShopFloor.class,shopFloorList);
            if(ObjectUtils.isNotEmpty(clearFloorIdList)){
                QueryWrapper<ShopFloorGoods> clearWrapper = MybatisPlusUtil.query();
                clearWrapper.in("shop_floor_id",clearFloorIdList);
                shopFloorGoodsMapper.delete(clearWrapper);
            }
            shopFloorGoodsRepository.saveBatch(shopFloorGoodsList);
        }
        //删除提交删除的数据
        if(ObjectUtils.isNotEmpty(h5ETO.getRemoveIdList())){
            repository.removeByIds(h5ETO.getRemoveIdList());
            QueryWrapper<ShopFloorGoods> deleteWrapper = MybatisPlusUtil.query();
            deleteWrapper.in("shop_floor_id",h5ETO.getRemoveIdList());
            shopFloorGoodsMapper.delete(deleteWrapper);
        }
    }

    @Override
    public List<PCMerchShopFloorVO.PCListVO> pcList(PCMerchShopFloorQTO.PCQTO qto) {
        QueryWrapper<ShopFloor> wrapper = new QueryWrapper<>();
        wrapper.eq("terminal",qto.getTerminal());
        wrapper.eq("pc_show", PcH5Enum.YES.getCode());
        wrapper.eq("shop_id",qto.getJwtShopId());
        wrapper.orderByAsc("idx");
        List<ShopFloor> floorList = repository.list(wrapper);
        List<String> floorIdList = new ArrayList<>();
        Map<String, ShopFloorComplexQuery.ComplexMerchadminPc> voMap = new HashMap<>();

        for(ShopFloor floorItem:floorList){
            floorIdList.add(floorItem.getId());
            ShopFloorComplexQuery.ComplexMerchadminPc complex = new ShopFloorComplexQuery.ComplexMerchadminPc();
            complex.setFloorGoodsList(new ArrayList<>());
            PCMerchShopFloorVO.PCListVO listVO = new  PCMerchShopFloorVO.PCListVO();
            listVO.setGoodsItemList(new ArrayList<>());
            BeanCopyUtils.copyProperties(floorItem,listVO);
            complex.setFloorVO(listVO);
            voMap.put(floorItem.getId(),complex);
        }
        if(ObjectUtils.isNotEmpty(floorIdList)){
            QueryWrapper<ShopFloorGoods> shopFloorGoodsQueryWrapper = MybatisPlusUtil.query();
            shopFloorGoodsQueryWrapper.in("shop_floor_id",floorIdList);
            List<ShopFloorGoods> floorGoodsList = shopFloorGoodsRepository.list(shopFloorGoodsQueryWrapper);
            List<String> goodsIdList = new ArrayList<>();
            for(ShopFloorGoods shopFloorGoodsItem:floorGoodsList){
                //一个商品在多个楼层,商品服务只查一个就可以,先去重
                if(!goodsIdList.contains(shopFloorGoodsItem.getGoodsId())){
                    goodsIdList.add(shopFloorGoodsItem.getGoodsId());
                }
                ShopFloorComplexQuery.ComplexMerchadminPc complex = voMap.get(shopFloorGoodsItem.getShopFloorId());
                if(null != complex){
                    complex.getFloorGoodsList().add(shopFloorGoodsItem);
                }
            }

            if(ObjectUtils.isNotEmpty(goodsIdList)){
                PCMerchGoodsInfoDTO.IdsInnerServiceDTO innerDTO = new PCMerchGoodsInfoDTO.IdsInnerServiceDTO();
                innerDTO.setGoodsIdList(goodsIdList);
                innerDTO.setQueryType(GoodsQueryTypeEnum.商品id.getCode());
                List<PCMerchGoodsInfoVO.InnerServiceGoodsVO> innerGoodsList = pCMerchAdminGoodsInfoRpc.innerServiceGoodsVO(innerDTO);
                for(ShopFloorComplexQuery.ComplexMerchadminPc complex:voMap.values()){
                    for(ShopFloorGoods floorGoodsItem:complex.getFloorGoodsList()){
                        //商品信息
                        for(PCMerchGoodsInfoVO.InnerServiceGoodsVO innerGoodsItem:innerGoodsList){
                            if(floorGoodsItem.getGoodsId().equals(innerGoodsItem.getId())){
                                PCMerchShopFloorVO.PCGoodsItem goodsItemVO = new PCMerchShopFloorVO.PCGoodsItem();
                                goodsItemVO.setId(innerGoodsItem.getId());
                                goodsItemVO.setGoodsName(innerGoodsItem.getGoodsName());
                                complex.getFloorVO().getGoodsItemList().add(goodsItemVO);
                            }
                        }
                    }
                }
            }
            List<PCMerchShopFloorVO.PCListVO> voList = new ArrayList<>();
            for(ShopFloorComplexQuery.ComplexMerchadminPc complex:voMap.values()){
                voList.add(complex.getFloorVO());
            }
            return voList;
        }
        return new ArrayList<>();
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public void pcEditor(PCMerchShopFloorDTO.PCETO pCETO) {
        QueryWrapper<ShopFloor> removeWrapper = MybatisPlusUtil.query();
        removeWrapper.eq("terminal",pCETO.getTerminal());
        removeWrapper.eq("pc_show", PcH5Enum.YES.getCode());
        removeWrapper.eq("shop_id",pCETO.getJwtShopId());
        List<ShopFloor> shopFloorList = repository.list(removeWrapper);
        List<String> shopFloorIdList = ListUtil.getIdList(ShopFloor.class,shopFloorList);
        //先清除所有的楼层 和 楼层下的商品
        if(ObjectUtils.isNotEmpty(shopFloorIdList)){
            repository.removeByIds(shopFloorIdList);
            QueryWrapper<ShopFloorGoods> shopFloorGoodsRemveWrapper = MybatisPlusUtil.query();
            shopFloorGoodsRemveWrapper.in("shop_floor_id",shopFloorIdList);
            shopFloorGoodsRepository.remove(shopFloorGoodsRemveWrapper);
        }
        //保存新的楼层  和 楼层下的商品
        if(ObjectUtils.isNotEmpty(pCETO.getFloorList())){
            for(PCMerchShopFloorDTO.PCItemETO itemETO:pCETO.getFloorList()){
                ShopFloor shopFloor = new ShopFloor();
                BeanUtils.copyProperties(itemETO,shopFloor);
                shopFloor.setTerminal(pCETO.getTerminal());
                shopFloor.setPcShow(PcH5Enum.YES.getCode());
                shopFloor.setShopId(pCETO.getJwtShopId());
                shopFloor.setMerchantId(pCETO.getJwtMerchantId());
                repository.save(shopFloor);
                if(ObjectUtils.isNotEmpty(itemETO.getGoodsItemList())){
                    List<ShopFloorGoods> shopFloorGoodsList = new ArrayList<>();
                    for(PCMerchShopFloorDTO.PCGoodsItem goodsItem:itemETO.getGoodsItemList()){
                        ShopFloorGoods shopFloorGoods = new ShopFloorGoods();
                        shopFloorGoods.setShopFloorId(shopFloor.getId());
                        shopFloorGoods.setGoodsId(goodsItem.getGoodsId());
                        shopFloorGoods.setIdx(goodsItem.getIdx());
                        shopFloorGoods.setShopId(pCETO.getJwtShopId());
                        shopFloorGoods.setMerchantId(pCETO.getJwtMerchantId());
                        shopFloorGoodsList.add(shopFloorGoods);
                    }
                    shopFloorGoodsRepository.saveBatch(shopFloorGoodsList);
                }
            }
        }
    }
}
