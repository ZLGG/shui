package com.gs.lshly.biz.support.user.service.bbb.pc.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.gs.lshly.biz.support.user.entity.UserShoppingCar;
import com.gs.lshly.biz.support.user.mapper.UserShoppingCarMapper;
import com.gs.lshly.biz.support.user.repository.IUserPrivateUserRepository;
import com.gs.lshly.biz.support.user.repository.IUserShoppingCarRepository;
import com.gs.lshly.biz.support.user.service.bbb.pc.IBbbUserShoppingCarService;
import com.gs.lshly.biz.support.user.service.bbb.pc.IPCBbbUserFrequentGoodsService;
import com.gs.lshly.common.enums.StockCheckStateEnum;
import com.gs.lshly.common.enums.TerminalEnum;
import com.gs.lshly.common.enums.TrueFalseEnum;
import com.gs.lshly.common.exception.BusinessException;
import com.gs.lshly.common.response.ResponseData;
import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.common.struct.bbb.pc.commodity.vo.PCBbbGoodsInfoVO;
import com.gs.lshly.common.struct.bbb.pc.user.dto.BbbUserShoppingCarDTO;
import com.gs.lshly.common.struct.bbb.pc.user.dto.PCBbbUserFrequentGoodsDTO;
import com.gs.lshly.common.struct.bbb.pc.user.qto.BbbUserShoppingCarQTO;
import com.gs.lshly.common.struct.bbb.pc.user.vo.BbbUserShoppingCarVO;
import com.gs.lshly.common.struct.common.CommonShopVO;
import com.gs.lshly.common.struct.common.CommonStockDTO;
import com.gs.lshly.common.struct.common.CommonStockVO;
import com.gs.lshly.common.utils.BeanCopyUtils;
import com.gs.lshly.common.utils.EnumUtil;
import com.gs.lshly.middleware.mybatisplus.MybatisPlusUtil;
import com.gs.lshly.rpc.api.bbb.pc.commodity.IPCBbbGoodsInfoRpc;
import com.gs.lshly.rpc.api.bbb.pc.merchant.IBbbShopRpc;
import com.gs.lshly.rpc.api.common.ICommonShopRpc;
import com.gs.lshly.rpc.api.common.ICommonStockRpc;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
* <p>
*  服务实现类
* </p>
* @author xxfc
* @since 2020-10-28
*/
@Component
public class BbbUserShoppingCarServiceImpl implements IBbbUserShoppingCarService {

    @Autowired
    private IUserShoppingCarRepository repository;
    @Autowired
    private UserShoppingCarMapper userShoppingCarMapper;
    @Autowired
    private IPCBbbUserFrequentGoodsService bbbUserFrequentGoodsService;
    @Autowired
    private IUserPrivateUserRepository userPrivateUserRepository;
    @DubboReference
    private IBbbShopRpc bbcShopRpc;
    @DubboReference
    private IPCBbbGoodsInfoRpc bbbGoodsInfoRpc;
    @DubboReference
    private ICommonStockRpc commonStockRpc;
    @DubboReference
    private ICommonShopRpc commonShopRpc;

    @Override
    public List<BbbUserShoppingCarVO.ListVO> list(BbbUserShoppingCarQTO.QTO qto) {
        if(null == qto.getJwtUserId()){
            throw new BusinessException("没有登录");
        }
        QueryWrapper<UserShoppingCar> wrapper = MybatisPlusUtil.query();
        wrapper.eq("terminal", TerminalEnum.BBB.getCode());
        wrapper.eq("user_id",qto.getJwtUserId());
        wrapper.orderByAsc("sku_id");
        List<UserShoppingCar> userShoppingCarList = repository.list(wrapper);
        List<BbbUserShoppingCarVO.ListVO> voList = new ArrayList<>();
        Map<String,BbbUserShoppingCarVO.ListVO> voMap = new HashMap();
        List<String> skuIdList = new ArrayList<>();
        List<String> shopIdList = new ArrayList<>();
        for (UserShoppingCar shoppingCarItem : userShoppingCarList) {
            if(!skuIdList.contains(shoppingCarItem.getSkuId())){
                skuIdList.add(shoppingCarItem.getSkuId());
            }
            BbbUserShoppingCarVO.ListVO listVO =  voMap.get(shoppingCarItem.getShopId());
            if(null == listVO){
                listVO = new BbbUserShoppingCarVO.ListVO();
                listVO.setShopId(shoppingCarItem.getShopId());
                shopIdList.add(shoppingCarItem.getShopId());
                voMap.put(shoppingCarItem.getShopId(),listVO);
            }
            BbbUserShoppingCarVO.ShoppingCarItemVO shoppingCarItemVO = new BbbUserShoppingCarVO.ShoppingCarItemVO();
            BeanUtils.copyProperties(shoppingCarItem,shoppingCarItemVO);
            listVO.getSkuGoodsList().add(shoppingCarItemVO);
        }
        voList.addAll(voMap.values());
        if(ObjectUtils.isNotEmpty(skuIdList)){
            //补齐店铺名称
            List<CommonShopVO.ShopIdNameVO> shopIdNameList = commonShopRpc.moreDetailShop(shopIdList);
            for(BbbUserShoppingCarVO.ListVO listVO:voList){
                for(CommonShopVO.ShopIdNameVO shopIdNameVO:shopIdNameList){
                    if(listVO.getShopId().equals(shopIdNameVO.getId())){
                        listVO.setShopName(shopIdNameVO.getShopName());
                    }
                }
            }
            List<PCBbbGoodsInfoVO.InnerServiceVO> innerGoodsList = bbbGoodsInfoRpc.innerServiceVOByIdList(skuIdList,qto);
            for(BbbUserShoppingCarVO.ListVO listVO: voList){
                for(BbbUserShoppingCarVO.ShoppingCarItemVO shoppingCarItemVo:listVO.getSkuGoodsList()){
                    for(PCBbbGoodsInfoVO.InnerServiceVO innerGoods:innerGoodsList){
                        if(innerGoods.getSkuId().equals(shoppingCarItemVo.getSkuId())){
                            BeanUtils.copyProperties(innerGoods,shoppingCarItemVo);
                            //最终销售价
                            shoppingCarItemVo.setSalePrice(this.utilsGetLastPrice(innerGoods,shoppingCarItemVo.getQuantity()));
                            //阶梯阶
                            shoppingCarItemVo.setStepPrice(innerGoods.getStepPrice());
                        }
                    }
                }
            }
            //返回购物车SKU当前的库存
            List<CommonStockVO.InnerStockVO>  stockVoList =  commonStockRpc.queryListStock(skuIdList);
            if(ObjectUtils.isNotEmpty(stockVoList)){
                for(BbbUserShoppingCarVO.ListVO shoppingCarVo:voList){
                    for(BbbUserShoppingCarVO.ShoppingCarItemVO shoppingCarItemVo:shoppingCarVo.getSkuGoodsList()){
                        for(CommonStockVO.InnerStockVO stockVO:stockVoList){
                            if(stockVO.getSkuId().equals(shoppingCarItemVo.getSkuId())){
                                shoppingCarItemVo.setStockNum(stockVO.getQuantity());
                            }
                        }
                    }
                }
            }
        }
        return voList;
    }

    @Override
    public  BbbUserShoppingCarVO.CountVO countShoppingCarGoods(BaseDTO dto) {
        if(StringUtils.isBlank(dto.getJwtUserId())){
            throw new BusinessException("没有登录");
        }
        QueryWrapper<UserShoppingCar> userShoppingCar = new QueryWrapper<>();
        userShoppingCar.eq("user_id",dto.getJwtUserId());
        int count = repository.count(userShoppingCar);
        BbbUserShoppingCarVO.CountVO countVO  = new BbbUserShoppingCarVO.CountVO();
        countVO.setCount(count);
        return countVO;
    }

    @Override
    public void addUserShoppingCar(BbbUserShoppingCarDTO.ETO eto) {
        if(null == eto.getJwtUserId()){
            throw new BusinessException("没有登录");
        }
        if(StringUtils.isBlank(eto.getShopId())){
            throw new BusinessException("店铺ID不能为空");

        }
        if(ObjectUtils.isEmpty(eto.getItemList())){
            throw new BusinessException("没有提交购买商品");
        }
        List<String> skuIdList = new ArrayList<>();
        List<UserShoppingCar> shoppingCarList = new ArrayList<>();
        CommonStockDTO.InnerCheckStockDTO innerCheckStockDTO = new CommonStockDTO.InnerCheckStockDTO();
        for(BbbUserShoppingCarDTO.ShoppingCarItemETO carItemETO:eto.getItemList()){
            //SKU-ID数组
            skuIdList.add(carItemETO.getSkuId());
            //购物车基本数据
            UserShoppingCar userShoppingCar = new UserShoppingCar();
            BeanUtils.copyProperties(carItemETO,userShoppingCar);
            userShoppingCar.setTerminal(TerminalEnum.BBB.getCode());
            userShoppingCar.setIsSelect(TrueFalseEnum.是.getCode());
            userShoppingCar.setShopId(eto.getShopId());
            userShoppingCar.setShopName(eto.getShopName());
            userShoppingCar.setUserId(eto.getJwtUserId());
            shoppingCarList.add(userShoppingCar);
            //组装检查库存需要的数据
            CommonStockDTO.InnerSkuGoodsInfoItem innerSkuGoodsCheck = new CommonStockDTO.InnerSkuGoodsInfoItem();
            innerSkuGoodsCheck.setGoodsId(carItemETO.getGoodsId());
            innerSkuGoodsCheck.setSkuId(carItemETO.getSkuId());
            innerSkuGoodsCheck.setQuantity(carItemETO.getQuantity());
            innerCheckStockDTO.getGoodsItemList().add(innerSkuGoodsCheck);
        }

        if(ObjectUtils.isEmpty(skuIdList)){
            throw new BusinessException("没有提交购买商品(SKU)");
        }
        //批量检查SKU库存
        ResponseData<CommonStockVO.InnerListCheckStockVO> responseDataCheck = commonStockRpc.innerListCheckStock(innerCheckStockDTO);
        if(!responseDataCheck.isSuccess()){
            throw new BusinessException(responseDataCheck.getMessage());
        }
        if(!StockCheckStateEnum.库存正常.getCode().equals(responseDataCheck.getData().getCheckState())){
            throw new BusinessException(StockCheckStateEnum.remark(responseDataCheck.getData().getCheckState()));
        }
        //移除当前用户相同的sku旧的购物车数据
        QueryWrapper<UserShoppingCar> queryWrapper = MybatisPlusUtil.query();
        queryWrapper.in("sku_id",skuIdList);
        queryWrapper.eq("user_id",eto.getJwtUserId());
        repository.remove(queryWrapper);
        List<PCBbbGoodsInfoVO.InnerServiceVO> innerSkuGoodsList = bbbGoodsInfoRpc.innerServiceVOByIdList(skuIdList,eto);
        for(UserShoppingCar userShoppingCar:shoppingCarList){
            for(PCBbbGoodsInfoVO.InnerServiceVO innerSkuGoodsVO:innerSkuGoodsList){
                if(userShoppingCar.getSkuId().equals(innerSkuGoodsVO.getSkuId())){
                    //设置阶梯价
                    BigDecimal gradePrice =  this.utilGetGradePrice(innerSkuGoodsVO,userShoppingCar.getQuantity());
                    userShoppingCar.setGoodsPrice(gradePrice);
                    break;
                }
            }
        }
        //保存购物车
        repository.saveBatch(shoppingCarList);
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public void addUserShoppingCar2(BbbUserShoppingCarDTO.AddShopingETO eto) {
        if(null == eto.getJwtUserId()){
            throw new BusinessException("没有登录");
        }
        if(ObjectUtils.isEmpty(eto.getSkuGoodsList())){
            throw new BusinessException("没有提交购买商品");
        }
        List<String> skuIdList = new ArrayList<>();
        for(BbbUserShoppingCarDTO.AddItem addItem:eto.getSkuGoodsList()) {
            if (!skuIdList.contains(addItem.getSkuId())) {
                skuIdList.add(addItem.getSkuId());
            }
        }

        QueryWrapper<UserShoppingCar> queryOld = MybatisPlusUtil.query();
        queryOld.in("sku_id",skuIdList);
        queryOld.eq("user_id",eto.getJwtUserId());
        queryOld.eq("terminal",TerminalEnum.BBB.getCode());
        List<UserShoppingCar> userShoppingOldCars = repository.list(queryOld);

        List<UserShoppingCar> shoppingAllCarList = new ArrayList<>();
        List<UserShoppingCar> shoppingNewCarList = new ArrayList<>();
        //常购清单
        PCBbbUserFrequentGoodsDTO.ETO  frequentGoodsDTO = new PCBbbUserFrequentGoodsDTO.ETO();

        CommonStockDTO.InnerCheckStockDTO innerCheckStockDTO = new CommonStockDTO.InnerCheckStockDTO();
        for(BbbUserShoppingCarDTO.AddItem addItem:eto.getSkuGoodsList()){
            //检查购物车，之前是否有这个sku-id项,如果有则只更新数量
            boolean checkFindOld = false;
            if(ObjectUtils.isNotEmpty(userShoppingOldCars)){
                for(UserShoppingCar userShoppingOldCar:userShoppingOldCars){
                    if(userShoppingOldCar.getSkuId().equals(addItem.getSkuId())){
                        userShoppingOldCar.setSkuId(addItem.getSkuId());
                        userShoppingOldCar.setQuantity(userShoppingOldCar.getQuantity() + addItem.getQuantity());
                        userShoppingOldCar.setUserId(eto.getJwtUserId());
                        userShoppingOldCar.setIsSelect(TrueFalseEnum.是.getCode());
                        userShoppingOldCar.setTerminal(TerminalEnum.BBB.getCode());
                        shoppingAllCarList.add(userShoppingOldCar);
                        checkFindOld = true;
                        break;
                    }
                }
            }
            if(checkFindOld == false){
                UserShoppingCar userShoppingCar = new UserShoppingCar();
                userShoppingCar.setSkuId(addItem.getSkuId());
                userShoppingCar.setQuantity(addItem.getQuantity());
                userShoppingCar.setUserId(eto.getJwtUserId());
                userShoppingCar.setIsSelect(TrueFalseEnum.是.getCode());
                userShoppingCar.setTerminal(TerminalEnum.BBB.getCode());
                shoppingAllCarList.add(userShoppingCar);
                shoppingNewCarList.add(userShoppingCar);
            }
        }
        repository.saveBatch(shoppingNewCarList);
        if(ObjectUtils.isEmpty(skuIdList)){
            throw new BusinessException("没有提交购买商品");
        }
        //补全购物车数据（冷数据只是临时数据）
        List<PCBbbGoodsInfoVO.InnerServiceVO> innerGoodsList = bbbGoodsInfoRpc.innerServiceVOByIdList(skuIdList,eto);
        for(PCBbbGoodsInfoVO.InnerServiceVO innerGoodsItem:innerGoodsList){
            for(UserShoppingCar userShoppingCar:shoppingAllCarList){
                if(innerGoodsItem.getSkuId().equals(userShoppingCar.getSkuId())){
                    BeanUtils.copyProperties(innerGoodsItem,userShoppingCar);
                    userShoppingCar.setGoodsPrice(innerGoodsItem.getSalePrice());
                }
            }
        }
        //组装检查库存需要的数据
        for(UserShoppingCar userShoppingCar:shoppingAllCarList){
            CommonStockDTO.InnerSkuGoodsInfoItem innerSkuGoodsCheck = new CommonStockDTO.InnerSkuGoodsInfoItem();
            innerSkuGoodsCheck.setGoodsId(userShoppingCar.getGoodsId());
            innerSkuGoodsCheck.setSkuId(userShoppingCar.getSkuId());
            innerSkuGoodsCheck.setQuantity(userShoppingCar.getQuantity());
            innerCheckStockDTO.getGoodsItemList().add(innerSkuGoodsCheck);
        }
        //批量检查SKU库存
        ResponseData<CommonStockVO.InnerListCheckStockVO> responseDataCheck = commonStockRpc.innerListCheckStock(innerCheckStockDTO);
        if(!responseDataCheck.isSuccess()){
            throw new BusinessException(responseDataCheck.getMessage());
        }
        if(!StockCheckStateEnum.库存正常.getCode().equals(responseDataCheck.getData().getCheckState())){
            throw new BusinessException(StockCheckStateEnum.remark(responseDataCheck.getData().getCheckState()));
        }
        repository.updateBatchById(shoppingAllCarList);

        //常购清单项
        frequentGoodsDTO.setJwtUserId(eto.getJwtUserId());
        for(UserShoppingCar userShoppingCar:shoppingAllCarList){
            PCBbbUserFrequentGoodsDTO.FrequentItem frequentItem = new PCBbbUserFrequentGoodsDTO.FrequentItem();
            frequentItem.setShopId(userShoppingCar.getShopId());
            frequentItem.setGoodsId(userShoppingCar.getGoodsId());
            frequentItem.setSkuId(userShoppingCar.getSkuId());
            frequentItem.setQuantity(userShoppingCar.getQuantity());
            frequentGoodsDTO.getFrequentItemList().add(frequentItem);
        }
        if(ObjectUtils.isNotEmpty(frequentGoodsDTO.getFrequentItemList())){
            bbbUserFrequentGoodsService.addMore(frequentGoodsDTO);
        }
    }

    @Override
    public void deleteBatchUserShoppingCar(BbbUserShoppingCarDTO.IdListDTO dto) {
        if(null == dto.getJwtUserId()){
            throw new BusinessException("没有登录");
        }
        if(ObjectUtils.isNotEmpty(dto.getIdList())){
            QueryWrapper<UserShoppingCar> deleteWrapper = MybatisPlusUtil.query();
            deleteWrapper.in("id",dto.getIdList());
            deleteWrapper.eq("terminal",TerminalEnum.BBB.getCode());
            deleteWrapper.eq("user_id",dto.getJwtUserId());
            userShoppingCarMapper.delete(deleteWrapper);
        }
    }

    @Override
    public void selectState(BbbUserShoppingCarDTO.SelectDTO eto) {
        if(StringUtils.isBlank(eto.getId())){
            return;
        }
        if(null == eto.getJwtUserId()){
            throw new BusinessException("没有登录");
        }
        if(StringUtils.isBlank(eto.getId())){
            throw new BusinessException("购物车ID不能为空");
        }
        QueryWrapper<UserShoppingCar> queryWrapper = MybatisPlusUtil.query();
        queryWrapper.eq("id",eto.getId());
        queryWrapper.eq("terminal",TerminalEnum.BBB.getCode());
        queryWrapper.eq("user_id",eto.getJwtUserId());
        UserShoppingCar userShoppingCar = repository.getOne(queryWrapper);
        if(null == userShoppingCar){
            return ;
        }
        if(null == userShoppingCar.getIsSelect()){
            userShoppingCar.setIsSelect(0);
        }
        if(TrueFalseEnum.否.getCode().equals(userShoppingCar.getIsSelect())){
            userShoppingCar.setIsSelect(TrueFalseEnum.是.getCode());
            //是选中状态 检查库存
            BaseDTO baseDTO = new BaseDTO();
            ResponseData<CommonStockVO.InnerCheckStockVO> responseData = commonStockRpc.innerCheckStock(baseDTO,userShoppingCar.getShopId(),userShoppingCar.getSkuId(),userShoppingCar.getQuantity());
            if(responseData.isSuccess()){
                CommonStockVO.InnerCheckStockVO innerCheckStockVO = responseData.getData();
                if(StockCheckStateEnum.库存正常.getCode().equals(innerCheckStockVO.getCheckState())){
                    repository.updateById(userShoppingCar);
                } else {
                    throw new BusinessException(StockCheckStateEnum.remark(innerCheckStockVO.getCheckState()));
                }
            } else {
                throw new BusinessException(responseData.getMessage());
            }
        }else if(TrueFalseEnum.是.getCode().equals(userShoppingCar.getIsSelect())){
            userShoppingCar.setIsSelect(TrueFalseEnum.否.getCode());
            repository.updateById(userShoppingCar);
        }
    }

    @Override
    public void selectStateAll(BbbUserShoppingCarDTO.SelectAllDTO eto) {
        if(null == eto.getJwtUserId()){
            throw new BusinessException("没有登录");
        }
        if(ObjectUtils.isNotEmpty(eto.getIdList())){
            QueryWrapper<UserShoppingCar> queryWrapper = new QueryWrapper<>();
            queryWrapper.in("id",eto.getIdList());
            queryWrapper.eq("terminal",TerminalEnum.BBB.getCode());
            queryWrapper.eq("user_id",eto.getJwtUserId());
            List<UserShoppingCar> userShoppingCarLit = repository.list(queryWrapper);
            if(ObjectUtils.isEmpty(userShoppingCarLit)){
                throw new BusinessException("无效的购物车商品");
            }
            if(!EnumUtil.checkEnumCode(eto.getIsSelect(),TrueFalseEnum.class)){
                throw new BusinessException("选中状态值错误");
            }
            if(TrueFalseEnum.否.getCode().equals(eto.getIsSelect())){
                UpdateWrapper<UserShoppingCar> updateWrapper = MybatisPlusUtil.update();
                updateWrapper.set("is_select",eto.getIsSelect());
                updateWrapper.in("id",eto.getIdList());
                updateWrapper.eq("terminal",TerminalEnum.BBB.getCode());
                updateWrapper.eq("user_id",eto.getJwtUserId());
                repository.update(updateWrapper);
            }else if(TrueFalseEnum.是.getCode().equals(eto.getIsSelect())){
                CommonStockDTO.InnerCheckStockDTO dto = new CommonStockDTO.InnerCheckStockDTO();
                List<CommonStockDTO.InnerSkuGoodsInfoItem> skuGoodsInfoItemList = new ArrayList<>();
                for(UserShoppingCar userShoppingCar:userShoppingCarLit){
                    CommonStockDTO.InnerSkuGoodsInfoItem skuGoodsInfoItem = new CommonStockDTO.InnerSkuGoodsInfoItem(userShoppingCar.getGoodsId(),
                            userShoppingCar.getSkuId(),userShoppingCar.getQuantity());
                    skuGoodsInfoItemList.add(skuGoodsInfoItem);
                }
                dto.setGoodsItemList(skuGoodsInfoItemList);
                ResponseData<CommonStockVO.InnerListCheckStockVO> responseData = commonStockRpc.innerListCheckStock(dto);
                if(responseData.isSuccess()){
                    CommonStockVO.InnerListCheckStockVO innerListCheckStockVO = responseData.getData();
                    if(StockCheckStateEnum.库存正常.getCode().equals(innerListCheckStockVO.getCheckState())){
                        UpdateWrapper<UserShoppingCar> updateWrapper = MybatisPlusUtil.update();
                        updateWrapper.set("is_select",eto.getIsSelect());
                        updateWrapper.in("id",eto.getIdList());
                        updateWrapper.eq("terminal",TerminalEnum.BBB.getCode());
                        updateWrapper.eq("user_id",eto.getJwtUserId());
                        repository.update(updateWrapper);
                    } else {
                        throw new BusinessException(StockCheckStateEnum.remark(innerListCheckStockVO.getCheckState()));
                    }
                }else{
                    throw new BusinessException(responseData.getMessage());
                }
            }
        }
    }

    @Override
    public BbbUserShoppingCarVO.ChangeQuantityVO changeQuantity(BbbUserShoppingCarDTO.QuantityDTO dto) {
        if(null == dto.getJwtUserId()){
            throw new BusinessException("没有登录");
        }
        if(StringUtils.isBlank(dto.getId())){
            throw new BusinessException("ID不能为空");
        }
        if(null != dto.getQuantity() && dto.getQuantity() < 1){
            throw new BusinessException("商品数量不能小于1");
        }
        BbbUserShoppingCarVO.ChangeQuantityVO changeQuantityVO = new BbbUserShoppingCarVO.ChangeQuantityVO();
        changeQuantityVO.setId(dto.getId());
        QueryWrapper<UserShoppingCar> queryWrapper = MybatisPlusUtil.query();
        queryWrapper.eq("id",dto.getId());
        queryWrapper.eq("terminal",TerminalEnum.BBB.getCode());
        queryWrapper.eq("user_id",dto.getJwtUserId());
        UserShoppingCar userShoppingCar = repository.getOne(queryWrapper);
        if(null == userShoppingCar){
            throw new BusinessException("购物车数据不存在");
        }
        userShoppingCar.setQuantity(dto.getQuantity() > 0 ? dto.getQuantity() : 1 );
        //检查库存
        BaseDTO baseDTO = new BaseDTO();
        ResponseData<CommonStockVO.InnerCheckStockVO> responseData = commonStockRpc.innerCheckStock(baseDTO,userShoppingCar.getShopId(),userShoppingCar.getSkuId(),userShoppingCar.getQuantity());
        if(!responseData.isSuccess()) {
            throw new BusinessException(responseData.getMessage());
        }
        CommonStockVO.InnerCheckStockVO innerCheckStockVO = responseData.getData();
        if(!StockCheckStateEnum.库存正常.getCode().equals(innerCheckStockVO.getCheckState())) {
            throw new BusinessException(StockCheckStateEnum.remark(innerCheckStockVO.getCheckState()));
        }
        PCBbbGoodsInfoVO.InnerServiceVO innerSkuGoodsVo = bbbGoodsInfoRpc.innerServiceVO(userShoppingCar.getSkuId(),dto);
        BigDecimal lastPrice = this.utilsGetLastPrice(innerSkuGoodsVo,userShoppingCar.getQuantity());
        userShoppingCar.setGoodsPrice(lastPrice);
        changeQuantityVO.setGoodsPrice(userShoppingCar.getGoodsPrice());
        changeQuantityVO.setTotalPrice(userShoppingCar.getGoodsPrice().multiply(new BigDecimal(userShoppingCar.getQuantity())));
        changeQuantityVO.setQuantity(userShoppingCar.getQuantity());
        //更新购物车
        repository.updateById(userShoppingCar);
       return changeQuantityVO;
    }

    @Override
    public PCBbbGoodsInfoVO.GetGoodsStepPriceVO getGoodsStepPrice(BbbUserShoppingCarDTO.QueryGradePriceDTO dto) {
        if(StringUtils.isBlank(dto.getJwtUserId())){
            throw new BusinessException("没有登录");
        }
        return bbbGoodsInfoRpc.innerGetStepPrice(dto.getSkuId());
    }

    /** 购买数量计算街梯价**/
    private BigDecimal utilGetGradePrice(PCBbbGoodsInfoVO.InnerServiceVO goodsInfo,Integer quantity){
        BigDecimal goodsPrice = goodsInfo.getSalePrice();
        if(ObjectUtils.isNotEmpty(goodsInfo.getStepPrice())){
            for(PCBbbGoodsInfoVO.GoodsStepPriceVO stepPrice:goodsInfo.getStepPrice()){
                if(quantity >= stepPrice.getStartNum() && quantity <= stepPrice.getEndNum()){
                    goodsPrice = stepPrice.getStepPrice();
                    break;
                }
                if (quantity > stepPrice.getEndNum()){
                    goodsPrice = stepPrice.getStepPrice();
                    continue;
                }
            }
        }

        return goodsPrice;
    }

    private BigDecimal utilsGetLastPrice(PCBbbGoodsInfoVO.InnerServiceVO innerService,Integer quantity){
        if(null != innerService.getWholesalePrice()){
            return innerService.getWholesalePrice();
        }
        if(ObjectUtils.isNotEmpty(innerService.getStepPrice())){
            return this.utilGetGradePrice(innerService,quantity);
        }
        if(null != innerService.getSalePrice()){
            return innerService.getSalePrice();
        }
        throw new BusinessException("商品价格出错了");
    }


    @Override
    public ResponseData<List<BbbUserShoppingCarVO.InnerListVO>> innerShoppingCarlist(BbbUserShoppingCarDTO.InnerIdListDTO dto) {
        QueryWrapper<UserShoppingCar> queryWrapper = MybatisPlusUtil.query();
        queryWrapper.eq("is_select",TrueFalseEnum.是.getCode());
        queryWrapper.eq("terminal",TerminalEnum.BBB.getCode());
        queryWrapper.in("id",dto.getIdList());
        List<UserShoppingCar> userShoppingCarList = repository.list(queryWrapper);
        if(ObjectUtils.isEmpty(userShoppingCarList)){
            ResponseData.fail("没有找到购物车数据");
        }
        Map<String,BbbUserShoppingCarVO.InnerListVO> voMap = new HashMap<>();
        for(UserShoppingCar shoppingCarItem:userShoppingCarList){
            BbbUserShoppingCarVO.InnerListVO listVO =  voMap.get(shoppingCarItem.getShopId());
            if(null == listVO){
                listVO = new  BbbUserShoppingCarVO.InnerListVO();
                listVO.setShopId(shoppingCarItem.getShopId());
                listVO.setShopName(shoppingCarItem.getShopName());
                listVO.setGoodsList(new ArrayList<>());
                listVO.setSkuIdList(new ArrayList<>());
                voMap.put(shoppingCarItem.getShopId(),listVO);
            }
            BbbUserShoppingCarVO.InnerCarItemVO innerCarItemVO = new BbbUserShoppingCarVO.InnerCarItemVO();
            BeanCopyUtils.copyProperties(shoppingCarItem,innerCarItemVO);
            listVO.getGoodsList().add(innerCarItemVO);
            listVO.getSkuIdList().add(shoppingCarItem.getSkuId());
        }
        return ResponseData.data(new ArrayList<>(voMap.values()));
    }

    @Override
    public ResponseData<BbbUserShoppingCarVO.InnerSimpleVO> innerSimpleShoppingCarlist(BbbUserShoppingCarDTO.InnerIdListDTO dto) {
        QueryWrapper<UserShoppingCar> queryWrapper = MybatisPlusUtil.query();
        queryWrapper.eq("terminal",TerminalEnum.BBB.getCode());
        queryWrapper.in("id",dto.getIdList());
        List<UserShoppingCar> userShoppingCarList = repository.list(queryWrapper);
        if(ObjectUtils.isEmpty(userShoppingCarList)){
            return ResponseData.fail("没有找到购物车数据");
        }
        List<String> shopIdList = new ArrayList<>();
        for(UserShoppingCar userShoppingCar:userShoppingCarList){
            if(!shopIdList.contains(userShoppingCar.getShopId())){
                shopIdList.add(userShoppingCar.getShopId());
            }
        }
        if(shopIdList.size() > 1){
            throw new BusinessException("购物车商品不是同一个商家的");
        }
        BbbUserShoppingCarVO.InnerSimpleVO innerSimpleVO = new  BbbUserShoppingCarVO.InnerSimpleVO();
        innerSimpleVO.setItemList(new ArrayList<>());
        for(UserShoppingCar shoppingCarItem:userShoppingCarList){
            innerSimpleVO.setShopId(shoppingCarItem.getShopId());
            BbbUserShoppingCarVO.InnerSimpleItem innerSimpleItem = new BbbUserShoppingCarVO.InnerSimpleItem();
            innerSimpleItem.setId(shoppingCarItem.getId());
            innerSimpleItem.setSkuId(shoppingCarItem.getSkuId());
            innerSimpleItem.setQuantity(shoppingCarItem.getQuantity());
            innerSimpleItem.setSalePrice(shoppingCarItem.getGoodsPrice());
            innerSimpleVO.getItemList().add(innerSimpleItem);
        }
        return ResponseData.data(innerSimpleVO);
    }

    @Override
    public boolean innerClearShopCarList(List<String> shoppingCarList) {
        if(ObjectUtils.isNotEmpty(shoppingCarList)){
            QueryWrapper<UserShoppingCar> queryWrapper = MybatisPlusUtil.query();
            queryWrapper.eq("terminal",TerminalEnum.BBB.getCode());
            queryWrapper.in("id",shoppingCarList);
            userShoppingCarMapper.delete(queryWrapper);
            return true;
        }
        return false;
    }
}
