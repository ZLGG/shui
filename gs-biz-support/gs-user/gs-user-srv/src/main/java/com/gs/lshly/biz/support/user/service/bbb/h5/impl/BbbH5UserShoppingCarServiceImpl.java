package com.gs.lshly.biz.support.user.service.bbb.h5.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.gs.lshly.biz.support.user.entity.UserShoppingCar;
import com.gs.lshly.biz.support.user.mapper.UserShoppingCarMapper;
import com.gs.lshly.biz.support.user.repository.IUserShoppingCarRepository;
import com.gs.lshly.biz.support.user.service.bbb.h5.IBbbH5UserShoppingCarService;
import com.gs.lshly.common.enums.QuantityLocationEnum;
import com.gs.lshly.common.enums.StockCheckStateEnum;
import com.gs.lshly.common.enums.TrueFalseEnum;
import com.gs.lshly.common.exception.BusinessException;
import com.gs.lshly.common.response.ResponseData;
import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.common.struct.bbb.h5.user.dto.BbbH5UserShoppingCarDTO;
import com.gs.lshly.common.struct.bbb.h5.user.qto.BbbH5UserShoppingCarQTO;
import com.gs.lshly.common.struct.bbb.h5.user.vo.BbbH5UserShoppingCarVO;
import com.gs.lshly.common.struct.common.CommonStockDTO;
import com.gs.lshly.common.struct.common.CommonStockVO;
import com.gs.lshly.common.utils.BeanCopyUtils;
import com.gs.lshly.common.utils.EnumUtil;
import com.gs.lshly.middleware.mybatisplus.MybatisPlusUtil;
import com.gs.lshly.rpc.api.bbb.h5.commodity.IBbbH5GoodsInfoRpc;
import com.gs.lshly.rpc.api.bbb.h5.commodity.IBbbH5GoodsSkuRpc;
import com.gs.lshly.rpc.api.bbb.h5.merchant.IBbbH5ShopRpc;
import com.gs.lshly.rpc.api.common.ICommonStockRpc;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
public class BbbH5UserShoppingCarServiceImpl implements IBbbH5UserShoppingCarService {

    @Autowired
    private IUserShoppingCarRepository repository;

    @Autowired
    private UserShoppingCarMapper userShoppingCarMapper;

    @DubboReference
    private IBbbH5ShopRpc bbcH5ShopRpc;

    @DubboReference
    private IBbbH5GoodsInfoRpc bbcH5GoodsInfoRpc;

    @DubboReference
    private ICommonStockRpc commonStockRpc;

    @DubboReference
    private IBbbH5GoodsSkuRpc bbbH5GoodsSkuRpc;


    @Override
    public List<BbbH5UserShoppingCarVO.ListVO> list(BbbH5UserShoppingCarQTO.QTO qto) {
        if(null == qto.getJwtUserId()){
            throw new BusinessException("没有登录");
        }
        QueryWrapper<UserShoppingCar> wrapper = MybatisPlusUtil.query();
        wrapper.eq("user_id",qto.getJwtUserId());
        List<UserShoppingCar> userShoppingCarList = repository.list(wrapper);
        //多规格合并显示存放map
        Map<String,BbbH5UserShoppingCarVO.ListVO> voMap = new HashMap<>();
        for (UserShoppingCar shoppingCarItem : userShoppingCarList) {
            BbbH5UserShoppingCarVO.ListVO listVO = voMap.get(shoppingCarItem.getShopId());
            if (null == listVO) {
                listVO = new BbbH5UserShoppingCarVO.ListVO();
                listVO.setShopId(shoppingCarItem.getShopId());
                listVO.setShopName(shoppingCarItem.getShopName());
                listVO.setGoodsList(new ArrayList<>());
                voMap.put(shoppingCarItem.getShopId(), listVO);
            }
            BbbH5UserShoppingCarVO.ShoppingCarItemVO shoppingCarItemVO = new BbbH5UserShoppingCarVO.ShoppingCarItemVO();
            BeanCopyUtils.copyProperties(shoppingCarItem, shoppingCarItemVO);
            if (StringUtils.isBlank(shoppingCarItemVO.getSpecValue())) {
                shoppingCarItemVO.setSpecValue("");
            }
            if (shoppingCarItemVO.getGoodsPrice() == null) {
                shoppingCarItemVO.setGoodsPrice(BigDecimal.ZERO);
            }
            listVO.getGoodsList().add(shoppingCarItemVO);
        }
        return new ArrayList<>(voMap.values());
    }

    @Override
    public  BbbH5UserShoppingCarVO.CountVO countShoppingCarGoods(BaseDTO dto) {
        if(StringUtils.isBlank(dto.getJwtUserId())){
            throw new BusinessException("没有登录");
        }
        QueryWrapper<UserShoppingCar> userShoppingCar = new QueryWrapper<>();
        userShoppingCar.eq("user_id",dto.getJwtUserId());
        int count = repository.count(userShoppingCar);
        BbbH5UserShoppingCarVO.CountVO countVO  = new BbbH5UserShoppingCarVO.CountVO();
        countVO.setCount(count);
        return countVO;
    }

    @Override
    public void addUserShoppingCar(BbbH5UserShoppingCarDTO.ETO eto) {
        if(null == eto.getJwtUserId()){
            throw new BusinessException("没有登录");
        }
        if(null == eto.getQuantity()){
            throw new BusinessException("数量不能为空");
        }
        QueryWrapper<UserShoppingCar> queryWrapper = MybatisPlusUtil.query();
        queryWrapper.eq("goods_id",eto.getGoodsId());
        queryWrapper.eq("sku_id",eto.getSkuId());
        queryWrapper.eq("user_id",eto.getJwtUserId());
        UserShoppingCar userShoppingCar =  repository.getOne(queryWrapper);
        if(null == userShoppingCar){
            userShoppingCar = new UserShoppingCar();
            BeanCopyUtils.copyProperties(eto, userShoppingCar);
            userShoppingCar.setIsSelect(TrueFalseEnum.是.getCode());
            userShoppingCar.setUserId(eto.getJwtUserId());
        }else{
            userShoppingCar.setQuantity(userShoppingCar.getQuantity() + eto.getQuantity()).setGoodsPrice(eto.getGoodsPrice());
        }
        //如果前端传递的参数不足够，后端补齐
        BbbH5UserShoppingCarVO.InnerSkuInfoVO skuInfoVO = bbbH5GoodsSkuRpc.getSkuInfo(userShoppingCar.getSkuId());
        if (StringUtils.isBlank(userShoppingCar.getSpecValue())) {
            userShoppingCar.setSpecValue(skuInfoVO.getSpecValue());
        }
        if (userShoppingCar.getGoodsPrice() == null) {
            userShoppingCar.setGoodsPrice(skuInfoVO.getSkuPrice());
        }
        if (StringUtils.isBlank(userShoppingCar.getSkuImage())) {
            userShoppingCar.setSkuImage(skuInfoVO.getSkuImage());
        }
        if (StringUtils.isBlank(userShoppingCar.getShopId())) {
            userShoppingCar.setShopId(skuInfoVO.getShopId());
        }
        if (StringUtils.isBlank(userShoppingCar.getShopName())) {
            userShoppingCar.setShopName(skuInfoVO.getShopName());
        }
        if (StringUtils.isBlank(userShoppingCar.getGoodsId())) {
            userShoppingCar.setGoodsId(skuInfoVO.getGoodsId());
        }
        if (StringUtils.isBlank(userShoppingCar.getGoodsName())) {
            userShoppingCar.setGoodsName(skuInfoVO.getGoodsName());
        }
        if (StringUtils.isBlank(userShoppingCar.getGoodsTitle())) {
            userShoppingCar.setGoodsTitle(skuInfoVO.getGoodsTitle());
        }

        //商品新增到购物车,检查库存
        ResponseData<CommonStockVO.InnerCheckStockVO> responseData = commonStockRpc.innerCheckStock(eto,eto.getShopId(),userShoppingCar.getSkuId(),userShoppingCar.getQuantity());
        if(responseData.isSuccess()){
            CommonStockVO.InnerCheckStockVO innerCheckStockVO = responseData.getData();
            if(StockCheckStateEnum.库存正常.getCode().equals(innerCheckStockVO.getCheckState())){
                repository.saveOrUpdate(userShoppingCar);
            } else {
                throw new BusinessException(StockCheckStateEnum.remark(innerCheckStockVO.getCheckState()));
            }
        } else {
            throw new BusinessException(responseData.getMessage());
        }
    }


    @Override
    public void deleteBatchUserShoppingCar(BbbH5UserShoppingCarDTO.IdListDTO dto) {
        if(null == dto.getJwtUserId()){
            throw new BusinessException("没有登录");
        }
        if(ObjectUtils.isNotEmpty(dto.getIdList())){
            QueryWrapper<UserShoppingCar> deleteWrapper = MybatisPlusUtil.query();
            deleteWrapper.in("id",dto.getIdList());
            deleteWrapper.eq("user_id",dto.getJwtUserId());
            userShoppingCarMapper.delete(deleteWrapper);
        }
    }

    @Override
    public void selectState(BbbH5UserShoppingCarDTO.SelectDTO eto) {
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
    public void selectStateAll(BbbH5UserShoppingCarDTO.SelectAllDTO eto) {
        if(null == eto.getJwtUserId()){
            throw new BusinessException("没有登录");
        }
        if(ObjectUtils.isNotEmpty(eto.getIdList())){
            QueryWrapper<UserShoppingCar> queryWrapper = new QueryWrapper<>();
            queryWrapper.in("id",eto.getIdList());
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
    public void changeQuantity(BbbH5UserShoppingCarDTO.QuantityDTO dto) {
        if(null == dto.getJwtUserId()){
            throw new BusinessException("没有登录");
        }
        if(StringUtils.isBlank(dto.getId())){
            throw new BusinessException("ID不能为空");
        }
        if(null != dto.getQuantity() && dto.getQuantity() < 1){
            throw new BusinessException("商品数量不能小于1");
        }
        QueryWrapper<UserShoppingCar> queryWrapper = MybatisPlusUtil.query();
        queryWrapper.eq("id",dto.getId());
        queryWrapper.eq("user_id",dto.getJwtUserId());
        UserShoppingCar userShoppingCar = repository.getOne(queryWrapper);
        if(null == userShoppingCar){
            throw new BusinessException("购物车数据不存在");
        }
        if(QuantityLocationEnum.增加.getCode().equals(dto.getLocation())){
            userShoppingCar.setQuantity(userShoppingCar.getQuantity() + dto.getQuantity());
        }
        if(QuantityLocationEnum.减少.getCode().equals(dto.getLocation())){
            userShoppingCar.setQuantity(userShoppingCar.getQuantity() - dto.getQuantity());
            if(userShoppingCar.getQuantity() < 1){
                userShoppingCar.setQuantity(1);
            }
        }
        //检查库存
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
    }

    @Override
    public ResponseData<List<BbbH5UserShoppingCarVO.InnerListVO>> innerShoppingCarlist(BbbH5UserShoppingCarDTO.InnerIdListDTO dto) {
        QueryWrapper<UserShoppingCar> queryWrapper = MybatisPlusUtil.query();
        queryWrapper.eq("is_select",TrueFalseEnum.是.getCode());
        queryWrapper.in("id",dto.getIdList());
        List<UserShoppingCar> userShoppingCarList = repository.list(queryWrapper);
        if(ObjectUtils.isEmpty(userShoppingCarList)){
            ResponseData.fail("没有找到购物车数据");
        }
        Map<String,BbbH5UserShoppingCarVO.InnerListVO> voMap = new HashMap<>();
        for(UserShoppingCar shoppingCarItem:userShoppingCarList){
            BbbH5UserShoppingCarVO.InnerListVO listVO =  voMap.get(shoppingCarItem.getShopId());
            if(null == listVO){
                listVO = new  BbbH5UserShoppingCarVO.InnerListVO();
                listVO.setShopId(shoppingCarItem.getShopId());
                listVO.setShopName(shoppingCarItem.getShopName());
                listVO.setGoodsList(new ArrayList<>());
                listVO.setSkuIdList(new ArrayList<>());
                voMap.put(shoppingCarItem.getShopId(),listVO);
            }
            BbbH5UserShoppingCarVO.InnerCarItemVO innerCarItemVO = new BbbH5UserShoppingCarVO.InnerCarItemVO();
            BeanCopyUtils.copyProperties(shoppingCarItem,innerCarItemVO);
            listVO.getGoodsList().add(innerCarItemVO);
            listVO.getSkuIdList().add(shoppingCarItem.getSkuId());
        }
        return ResponseData.data(new ArrayList<>(voMap.values()));
    }

    @Override
    public ResponseData<BbbH5UserShoppingCarVO.InnerSimpleVO> innerSimpleShoppingCarlist(BbbH5UserShoppingCarDTO.InnerIdListDTO dto) {
        QueryWrapper<UserShoppingCar> queryWrapper = MybatisPlusUtil.query();
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
        BbbH5UserShoppingCarVO.InnerSimpleVO innerSimpleVO = new  BbbH5UserShoppingCarVO.InnerSimpleVO();
        innerSimpleVO.setItemList(new ArrayList<>());
        for(UserShoppingCar shoppingCarItem:userShoppingCarList){
            innerSimpleVO.setShopId(shoppingCarItem.getShopId());
            BbbH5UserShoppingCarVO.InnerSimpleItem innerSimpleItem = new BbbH5UserShoppingCarVO.InnerSimpleItem();
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
            queryWrapper.in("id",shoppingCarList);
            userShoppingCarMapper.delete(queryWrapper);
            return true;
        }
        return false;
    }
}
