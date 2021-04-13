package com.gs.lshly.biz.support.user.service.bbc.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.gs.lshly.biz.support.user.entity.UserShoppingCar;
import com.gs.lshly.biz.support.user.mapper.UserShoppingCarMapper;
import com.gs.lshly.biz.support.user.repository.IUserShoppingCarRepository;
import com.gs.lshly.biz.support.user.service.bbc.IBbcUserShoppingCarService;
import com.gs.lshly.common.enums.QuantityLocationEnum;
import com.gs.lshly.common.enums.StockCheckStateEnum;
import com.gs.lshly.common.enums.TerminalEnum;
import com.gs.lshly.common.enums.TrueFalseEnum;
import com.gs.lshly.common.exception.BusinessException;
import com.gs.lshly.common.response.ResponseData;
import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.common.struct.bbc.commodity.qto.BbcGoodsInfoQTO;
import com.gs.lshly.common.struct.bbc.commodity.vo.BbcGoodsInfoVO;
import com.gs.lshly.common.struct.bbc.user.dto.BbcUserShoppingCarDTO;
import com.gs.lshly.common.struct.bbc.user.qto.BbcUserShoppingCarQTO;
import com.gs.lshly.common.struct.bbc.user.vo.BbcUserShoppingCarVO;
import com.gs.lshly.common.struct.bbc.user.vo.BbcUserShoppingCarVO.ShoppingCarItemVO;
import com.gs.lshly.common.struct.common.CommonStockDTO;
import com.gs.lshly.common.struct.common.CommonStockVO;
import com.gs.lshly.common.utils.BeanCopyUtils;
import com.gs.lshly.common.utils.EnumUtil;
import com.gs.lshly.middleware.mybatisplus.MybatisPlusUtil;
import com.gs.lshly.rpc.api.bbc.commodity.IBbcGoodsInfoRpc;
import com.gs.lshly.rpc.api.bbc.commodity.IBbcGoodsSkuRpc;
import com.gs.lshly.rpc.api.bbc.merchant.IBbcShopRpc;
import com.gs.lshly.rpc.api.common.ICommonStockRpc;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author xxfc
 * @since 2020-10-28
 */
@Component
public class BbcUserShoppingCarServiceImpl implements IBbcUserShoppingCarService {

    @Autowired
    private IUserShoppingCarRepository repository;

    @Autowired
    private UserShoppingCarMapper userShoppingCarMapper;

    @DubboReference
    private IBbcShopRpc bbcShopRpc;

    @DubboReference
    private IBbcGoodsInfoRpc bbcGoodsInfoRpc;

    @DubboReference
    private ICommonStockRpc commonStockRpc;

    @DubboReference
    private IBbcGoodsSkuRpc skuRpc;


    @Override
    public BbcUserShoppingCarVO.HomeVO list(BbcUserShoppingCarQTO.QTO qto) {
    	
    	BbcUserShoppingCarVO.HomeVO homeVO = new BbcUserShoppingCarVO.HomeVO();
        // 用户登录校验
        if (null == qto.getJwtUserId()) {
            throw new BusinessException("没有登录");
        }

        // 查询用户购物车信息
        QueryWrapper<UserShoppingCar> wrapper = MybatisPlusUtil.query();
        wrapper.eq("terminal", TerminalEnum.BBC.getCode());
        wrapper.eq("user_id", qto.getJwtUserId());
        
        Integer goodsType = qto.getGoodsType();
        if(goodsType!=null){
        	if(goodsType.equals(10)){	//普通商品
        		wrapper.eq("is_point_good", false);
        		wrapper.eq("is_in_member_gift", false);
        	}else if(goodsType.equals(20)){	//积分商品
        		wrapper.eq("is_point_good", true);
        	}else if(goodsType.equals(30)){//IN商品
        		wrapper.eq("is_in_member_gift", true);
        	}
        }
        wrapper.orderByAsc("sku_id");
        List<UserShoppingCar> userShoppingCarList = repository.list(wrapper);
        if (ObjectUtils.isEmpty(userShoppingCarList)) {
            return null;
        }

        Map<String, BbcUserShoppingCarVO.ListVO> voMap = new HashMap<>();
        List<BbcUserShoppingCarVO.ShoppingCarItemVO> tempList = new ArrayList<>();
        List<String> skuIdList = new ArrayList<>();
        // 遍历用户购物车信息
        for (UserShoppingCar shoppingCarItem : userShoppingCarList) {
            BbcUserShoppingCarVO.ListVO listVO = voMap.get(shoppingCarItem.getShopId());
            // 首次出现的商家，初始化
            if (null == listVO) {
                listVO = new BbcUserShoppingCarVO.ListVO();
                listVO.setShopId(shoppingCarItem.getShopId());
                listVO.setShopName(shoppingCarItem.getShopName());
                voMap.put(shoppingCarItem.getShopId(), listVO);
            }
            // 商品项信息
            BbcUserShoppingCarVO.ShoppingCarItemVO shoppingCarItemVO = new BbcUserShoppingCarVO.ShoppingCarItemVO();
            shoppingCarItemVO.setId(shoppingCarItem.getId());
            shoppingCarItemVO.setGoodsId(shoppingCarItem.getGoodsId());
            shoppingCarItemVO.setSkuId(shoppingCarItem.getSkuId());
            shoppingCarItemVO.setQuantity(shoppingCarItem.getQuantity());
            shoppingCarItemVO.setIsSelect(shoppingCarItem.getIsSelect());
            shoppingCarItemVO.setShopId(shoppingCarItem.getShopId());
            tempList.add(shoppingCarItemVO);
            skuIdList.add(shoppingCarItem.getSkuId());
        }

        List<ShoppingCarItemVO> deleteIdList = new ArrayList<>();
        if (ObjectUtils.isNotEmpty(skuIdList)) {
            // 获取商品最新的信息
            List<BbcGoodsInfoVO.InnerServiceVO> innerSkuGoodsList = bbcGoodsInfoRpc.innerServicePageShopGoods(new BbcGoodsInfoQTO.SkuIdListQTO().setSkuIdList(skuIdList));
            if (ObjectUtils.isNotEmpty(innerSkuGoodsList)) {
                for (BbcUserShoppingCarVO.ShoppingCarItemVO shoppingCarItemVO : tempList) {
                    //组装购物车里的数据
                    for (BbcGoodsInfoVO.InnerServiceVO innerSkuGoodVo : innerSkuGoodsList) {
                        if (shoppingCarItemVO.getSkuId().equals(innerSkuGoodVo.getSkuId())) {
                            shoppingCarItemVO.setGoodsId(innerSkuGoodVo.getGoodsId());
                            shoppingCarItemVO.setGoodsName(innerSkuGoodVo.getGoodsName());
                            shoppingCarItemVO.setGoodsTitle(innerSkuGoodVo.getGoodsTitle());
                            shoppingCarItemVO.setIsPointGood(innerSkuGoodVo.getIsPointGood());
                            shoppingCarItemVO.setGoodsPrice(innerSkuGoodVo.getSalePrice());
                            shoppingCarItemVO.setGoodsPointPrice(innerSkuGoodVo.getPointPrice());
                            shoppingCarItemVO.setSkuImage(innerSkuGoodVo.getGoodsImage());
                            shoppingCarItemVO.setSpecValue(innerSkuGoodVo.getSkuSpecValue());
                            shoppingCarItemVO.setExchangeType(innerSkuGoodVo.getExchangeType());
                            shoppingCarItemVO.setIsInMemberGift(innerSkuGoodVo.getIsInMemberGift());
                            shoppingCarItemVO.setInMemberPointPrice(innerSkuGoodVo.getInMemberPointPrice());
                            break;
                        }
                    }
                    //购物车里有数据，没有找到商品
                    if (StringUtils.isNotBlank(shoppingCarItemVO.getGoodsId())) {
                        BbcUserShoppingCarVO.ListVO listVO = voMap.get(shoppingCarItemVO.getShopId());
                        if (null != listVO) {
                            listVO.getGoodsList().add(shoppingCarItemVO);
                        }
                    } else {
                        deleteIdList.add(shoppingCarItemVO);
                    }
                }
            }
        }
        //清理数据库无效的购物车数据
        if (ObjectUtils.isNotEmpty(deleteIdList)) {
            //userShoppingCarMapper.deleteBatchIds(deleteIdList);
        	homeVO.setLoseList(deleteIdList);
        }
        //获取最终前效的购物车数据
        List<BbcUserShoppingCarVO.ListVO> voList = new ArrayList<>();
        for (BbcUserShoppingCarVO.ListVO listVO : voMap.values()) {
            if (null != listVO) {
                voList.add(listVO);
            }
        }
        homeVO.setCarList(voList);
        return homeVO;
    }

    @Override
    public BbcUserShoppingCarVO.CountVO countShoppingCarGoods(BaseDTO dto) {
        if (StringUtils.isBlank(dto.getJwtUserId())) {
            throw new BusinessException("没有登录");
        }
        QueryWrapper<UserShoppingCar> userShoppingCar = new QueryWrapper<>();
        userShoppingCar.eq("user_id",dto.getJwtUserId());
        userShoppingCar.eq("terminal",TerminalEnum.BBC.getCode());
        int count = userShoppingCarMapper.countShoppingCarGoods(userShoppingCar);
        BbcUserShoppingCarVO.CountVO countVO  = new BbcUserShoppingCarVO.CountVO();
//=======
//        userShoppingCar.eq("user_id", dto.getJwtUserId());
//        int count = repository.count(userShoppingCar);
//        BbcUserShoppingCarVO.CountVO countVO = new BbcUserShoppingCarVO.CountVO();
//>>>>>>> develop_v1.1.0_citydo_0402
        countVO.setCount(count);
        return countVO;
    }

    @Override
    public void addUserShoppingCar(BbcUserShoppingCarDTO.ETO eto) {
        if (null == eto.getJwtUserId()) {
            throw new BusinessException("没有登录");
        }
        if (null == eto.getQuantity()) {
            throw new BusinessException("数量不能为空");
        }

        // 查询商品
        BbcUserShoppingCarVO.InnerSkuInfoVO skuInfoVO = skuRpc.getSkuInfo(eto.getSkuId());
        if (skuInfoVO == null) {
            throw new BusinessException("商品不存在");
        }

        // 查询购物车
        QueryWrapper<UserShoppingCar> queryWrapper = MybatisPlusUtil.query();
        queryWrapper.eq("goods_id", skuInfoVO.getGoodsId());
        queryWrapper.eq("sku_id", eto.getSkuId());
        queryWrapper.eq("user_id", eto.getJwtUserId());
        UserShoppingCar userShoppingCar = repository.getOne(queryWrapper);
        if (null == userShoppingCar) {
            userShoppingCar = new UserShoppingCar();
            userShoppingCar.setGoodsId(skuInfoVO.getGoodsId());
            userShoppingCar.setGoodsName(skuInfoVO.getGoodsName());
            userShoppingCar.setGoodsTitle(skuInfoVO.getGoodsTitle());
            userShoppingCar.setIsPointGood(skuInfoVO.getIsPointGood());
            userShoppingCar.setGoodsPrice(skuInfoVO.getSkuPrice());
            userShoppingCar.setGoodsPointPrice(skuInfoVO.getPointPrice());
            userShoppingCar.setSkuId(eto.getSkuId());
            userShoppingCar.setSkuImage(skuInfoVO.getSkuImage());
            userShoppingCar.setSpecValue(skuInfoVO.getSpecValue());
            userShoppingCar.setShopId(skuInfoVO.getShopId());
            userShoppingCar.setShopName(skuInfoVO.getShopName());
            userShoppingCar.setQuantity(eto.getQuantity());
            userShoppingCar.setExchangeType(skuInfoVO.getExchangeType());
            userShoppingCar.setIsInMemberGift(skuInfoVO.getIsInMemberGift());
            userShoppingCar.setInMemberPointPrice(skuInfoVO.getInMemberPointPrice());
            userShoppingCar.setTerminal(TerminalEnum.BBC.getCode());
            userShoppingCar.setIsSelect(TrueFalseEnum.是.getCode());
            userShoppingCar.setUserId(eto.getJwtUserId());
        } else {
            userShoppingCar.setQuantity(userShoppingCar.getQuantity() + eto.getQuantity())
                    .setGoodsPrice(skuInfoVO.getSkuPrice())
                    .setGoodsPointPrice(skuInfoVO.getPointPrice())
                    .setInMemberPointPrice(skuInfoVO.getInMemberPointPrice());
        }

        //商品新增到购物车,检查库存
        ResponseData<CommonStockVO.InnerCheckStockVO> responseData = commonStockRpc.innerCheckStock(eto, skuInfoVO.getShopId(), userShoppingCar.getSkuId(), userShoppingCar.getQuantity());
        if (responseData.isSuccess()) {
            CommonStockVO.InnerCheckStockVO innerCheckStockVO = responseData.getData();
            if (StockCheckStateEnum.库存正常.getCode().equals(innerCheckStockVO.getCheckState())) {
                repository.saveOrUpdate(userShoppingCar);
            } else {
                throw new BusinessException(StockCheckStateEnum.remark(innerCheckStockVO.getCheckState()));
            }
        } else {
            throw new BusinessException(responseData.getMessage());
        }
    }


    @Override
    public void deleteBatchUserShoppingCar(BbcUserShoppingCarDTO.IdListDTO dto) {
        if (null == dto.getJwtUserId()) {
            throw new BusinessException("没有登录");
        }
        if (ObjectUtils.isNotEmpty(dto.getIdList())) {
            QueryWrapper<UserShoppingCar> deleteWrapper = MybatisPlusUtil.query();
            deleteWrapper.in("id", dto.getIdList());
            deleteWrapper.eq("terminal", TerminalEnum.BBC.getCode());
            deleteWrapper.eq("user_id", dto.getJwtUserId());
            userShoppingCarMapper.delete(deleteWrapper);
        }
    }

    @Override
    public void selectState(BbcUserShoppingCarDTO.SelectDTO eto) {
        if (StringUtils.isBlank(eto.getId())) {
            return;
        }
        if (null == eto.getJwtUserId()) {
            throw new BusinessException("没有登录");
        }
        if (StringUtils.isBlank(eto.getId())) {
            throw new BusinessException("购物车ID不能为空");
        }
        QueryWrapper<UserShoppingCar> queryWrapper = MybatisPlusUtil.query();
        queryWrapper.eq("id", eto.getId());
        queryWrapper.eq("terminal", TerminalEnum.BBC.getCode());
        queryWrapper.eq("user_id", eto.getJwtUserId());
        UserShoppingCar userShoppingCar = repository.getOne(queryWrapper);
        if (null == userShoppingCar) {
            return;
        }
        if (null == userShoppingCar.getIsSelect()) {
            userShoppingCar.setIsSelect(0);
        }
        if (TrueFalseEnum.否.getCode().equals(userShoppingCar.getIsSelect())) {
            userShoppingCar.setIsSelect(TrueFalseEnum.是.getCode());
            //是选中状态 检查库存
            BaseDTO baseDTO = new BaseDTO();
            ResponseData<CommonStockVO.InnerCheckStockVO> responseData = commonStockRpc.innerCheckStock(baseDTO, userShoppingCar.getShopId(), userShoppingCar.getSkuId(), userShoppingCar.getQuantity());
            if (responseData.isSuccess()) {
                CommonStockVO.InnerCheckStockVO innerCheckStockVO = responseData.getData();
                if (StockCheckStateEnum.库存正常.getCode().equals(innerCheckStockVO.getCheckState())) {
                    repository.updateById(userShoppingCar);
                } else {
                    throw new BusinessException(StockCheckStateEnum.remark(innerCheckStockVO.getCheckState()));
                }
            } else {
                throw new BusinessException(responseData.getMessage());
            }
        } else if (TrueFalseEnum.是.getCode().equals(userShoppingCar.getIsSelect())) {
            userShoppingCar.setIsSelect(TrueFalseEnum.否.getCode());
            repository.updateById(userShoppingCar);
        }
    }

    @Override
    public void selectStateAll(BbcUserShoppingCarDTO.SelectAllDTO eto) {
        if (null == eto.getJwtUserId()) {
            throw new BusinessException("没有登录");
        }
        if (ObjectUtils.isNotEmpty(eto.getIdList())) {
            QueryWrapper<UserShoppingCar> queryWrapper = new QueryWrapper<>();
            queryWrapper.in("id", eto.getIdList());
            queryWrapper.eq("terminal", TerminalEnum.BBC.getCode());
            queryWrapper.eq("user_id", eto.getJwtUserId());
            List<UserShoppingCar> userShoppingCarLit = repository.list(queryWrapper);
            if (ObjectUtils.isEmpty(userShoppingCarLit)) {
                throw new BusinessException("无效的购物车商品");
            }
            if (!EnumUtil.checkEnumCode(eto.getIsSelect(), TrueFalseEnum.class)) {
                throw new BusinessException("选中状态值错误");
            }
            if (TrueFalseEnum.否.getCode().equals(eto.getIsSelect())) {
                UpdateWrapper<UserShoppingCar> updateWrapper = MybatisPlusUtil.update();
                updateWrapper.set("is_select", eto.getIsSelect());
                updateWrapper.in("id", eto.getIdList());
                updateWrapper.eq("terminal", TerminalEnum.BBC.getCode());
                updateWrapper.eq("user_id", eto.getJwtUserId());
                repository.update(updateWrapper);
            } else if (TrueFalseEnum.是.getCode().equals(eto.getIsSelect())) {
                CommonStockDTO.InnerCheckStockDTO dto = new CommonStockDTO.InnerCheckStockDTO();
                List<CommonStockDTO.InnerSkuGoodsInfoItem> skuGoodsInfoItemList = new ArrayList<>();
                for (UserShoppingCar userShoppingCar : userShoppingCarLit) {
                    CommonStockDTO.InnerSkuGoodsInfoItem skuGoodsInfoItem = new CommonStockDTO.InnerSkuGoodsInfoItem(userShoppingCar.getGoodsId(),
                            userShoppingCar.getSkuId(), userShoppingCar.getQuantity());
                    skuGoodsInfoItemList.add(skuGoodsInfoItem);
                }
                dto.setGoodsItemList(skuGoodsInfoItemList);
                ResponseData<CommonStockVO.InnerListCheckStockVO> responseData = commonStockRpc.innerListCheckStock(dto);
                if (responseData.isSuccess()) {
                    CommonStockVO.InnerListCheckStockVO innerListCheckStockVO = responseData.getData();
                    if (StockCheckStateEnum.库存正常.getCode().equals(innerListCheckStockVO.getCheckState())) {
                        UpdateWrapper<UserShoppingCar> updateWrapper = MybatisPlusUtil.update();
                        updateWrapper.set("is_select", eto.getIsSelect());
                        updateWrapper.in("id", eto.getIdList());
                        updateWrapper.eq("terminal", TerminalEnum.BBC.getCode());
                        updateWrapper.eq("user_id", eto.getJwtUserId());
                        repository.update(updateWrapper);
                    } else {
                        throw new BusinessException(StockCheckStateEnum.remark(innerListCheckStockVO.getCheckState()));
                    }
                } else {
                    throw new BusinessException(responseData.getMessage());
                }
            }
        }
    }

    @Override
    public void changeQuantity(BbcUserShoppingCarDTO.QuantityDTO dto) {
        if (null == dto.getJwtUserId()) {
            throw new BusinessException("没有登录");
        }
        if (StringUtils.isBlank(dto.getId())) {
            throw new BusinessException("ID不能为空");
        }
        if (null != dto.getQuantity() && dto.getQuantity() < 1) {
            throw new BusinessException("商品数量不能小于1");
        }
        QueryWrapper<UserShoppingCar> queryWrapper = MybatisPlusUtil.query();
        queryWrapper.eq("id", dto.getId());
        queryWrapper.eq("terminal", TerminalEnum.BBC.getCode());
        queryWrapper.eq("user_id", dto.getJwtUserId());
        UserShoppingCar userShoppingCar = repository.getOne(queryWrapper);
        if (null == userShoppingCar) {
            throw new BusinessException("购物车数据不存在");
        }
        if (QuantityLocationEnum.增加.getCode().equals(dto.getLocation())) {
            userShoppingCar.setQuantity(userShoppingCar.getQuantity() + dto.getQuantity());
        }
        if (QuantityLocationEnum.减少.getCode().equals(dto.getLocation())) {
            userShoppingCar.setQuantity(userShoppingCar.getQuantity() - dto.getQuantity());
            if (userShoppingCar.getQuantity() < 1) {
                userShoppingCar.setQuantity(1);
            }
        }
        //检查库存
        BaseDTO baseDTO = new BaseDTO();
        ResponseData<CommonStockVO.InnerCheckStockVO> responseData = commonStockRpc.innerCheckStock(baseDTO, userShoppingCar.getShopId(), userShoppingCar.getSkuId(), userShoppingCar.getQuantity());
        if (responseData.isSuccess()) {
            CommonStockVO.InnerCheckStockVO innerCheckStockVO = responseData.getData();
            if (StockCheckStateEnum.库存正常.getCode().equals(innerCheckStockVO.getCheckState())) {
                repository.updateById(userShoppingCar);
            } else {
                throw new BusinessException(StockCheckStateEnum.remark(innerCheckStockVO.getCheckState()));
            }
        } else {
            throw new BusinessException(responseData.getMessage());
        }
    }

    @Override
    public ResponseData<List<BbcUserShoppingCarVO.InnerListVO>> innerShoppingCarlist(BbcUserShoppingCarDTO.InnerIdListDTO dto) {
        QueryWrapper<UserShoppingCar> queryWrapper = MybatisPlusUtil.query();
        queryWrapper.eq("is_select", TrueFalseEnum.是.getCode());
        queryWrapper.eq("terminal", TerminalEnum.BBC.getCode());
        queryWrapper.in("id", dto.getIdList());
        List<UserShoppingCar> userShoppingCarList = repository.list(queryWrapper);
        if (ObjectUtils.isEmpty(userShoppingCarList)) {
            ResponseData.fail("没有找到购物车数据");
        }
        Map<String, BbcUserShoppingCarVO.InnerListVO> voMap = new HashMap<>();
        for (UserShoppingCar shoppingCarItem : userShoppingCarList) {
            BbcUserShoppingCarVO.InnerListVO listVO = voMap.get(shoppingCarItem.getShopId());
            if (null == listVO) {
                listVO = new BbcUserShoppingCarVO.InnerListVO();
                listVO.setShopId(shoppingCarItem.getShopId());
                listVO.setShopName(shoppingCarItem.getShopName());
                listVO.setGoodsList(new ArrayList<>());
                listVO.setSkuIdList(new ArrayList<>());
                voMap.put(shoppingCarItem.getShopId(), listVO);
            }
            BbcUserShoppingCarVO.InnerCarItemVO innerCarItemVO = new BbcUserShoppingCarVO.InnerCarItemVO();
            BeanCopyUtils.copyProperties(shoppingCarItem, innerCarItemVO);
            listVO.getGoodsList().add(innerCarItemVO);
            listVO.getSkuIdList().add(shoppingCarItem.getSkuId());
        }
        return ResponseData.data(new ArrayList<>(voMap.values()));
    }

    @Override
    public ResponseData<BbcUserShoppingCarVO.InnerSimpleVO> innerSimpleShoppingCarlist(BbcUserShoppingCarDTO.InnerIdListDTO dto) {
        QueryWrapper<UserShoppingCar> queryWrapper = MybatisPlusUtil.query();
        queryWrapper.in("id", dto.getIdList());
        queryWrapper.eq("terminal", TerminalEnum.BBC.getCode());
        List<UserShoppingCar> userShoppingCarList = repository.list(queryWrapper);
        if (ObjectUtils.isEmpty(userShoppingCarList)) {
            return ResponseData.fail("没有找到购物车数据");
        }
        List<String> shopIdList = new ArrayList<>();
        for (UserShoppingCar userShoppingCar : userShoppingCarList) {
            if (!shopIdList.contains(userShoppingCar.getShopId())) {
                shopIdList.add(userShoppingCar.getShopId());
            }
        }
        if (shopIdList.size() > 1) {
            throw new BusinessException("购物车商品不是同一个商家的");
        }
        BbcUserShoppingCarVO.InnerSimpleVO innerSimpleVO = new BbcUserShoppingCarVO.InnerSimpleVO();
        innerSimpleVO.setItemList(new ArrayList<>());
        for (UserShoppingCar shoppingCarItem : userShoppingCarList) {
            innerSimpleVO.setShopId(shoppingCarItem.getShopId());
            BbcUserShoppingCarVO.InnerSimpleItem innerSimpleItem = new BbcUserShoppingCarVO.InnerSimpleItem();
            innerSimpleItem.setId(shoppingCarItem.getId());
            innerSimpleItem.setSkuId(shoppingCarItem.getSkuId());
            innerSimpleItem.setQuantity(shoppingCarItem.getQuantity());
            innerSimpleItem.setIsPointGood(shoppingCarItem.getIsPointGood());
            innerSimpleItem.setSalePrice(shoppingCarItem.getGoodsPrice());
            innerSimpleItem.setPointPrice(shoppingCarItem.getGoodsPointPrice());
            innerSimpleVO.getItemList().add(innerSimpleItem);
        }
        return ResponseData.data(innerSimpleVO);
    }

    @Override
    public boolean innerClearShopCarList(List<String> shoppingCarList) {
        if (ObjectUtils.isNotEmpty(shoppingCarList)) {
            QueryWrapper<UserShoppingCar> queryWrapper = MybatisPlusUtil.query();
            queryWrapper.in("id", shoppingCarList);
            queryWrapper.eq("terminal", TerminalEnum.BBC.getCode());
            userShoppingCarMapper.delete(queryWrapper);
            return true;
        }
        return false;
    }
}
