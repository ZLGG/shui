package com.gs.lshly.biz.support.user.service.bbb.pc.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.gs.lshly.biz.support.user.entity.UserFrequentSku;
import com.gs.lshly.biz.support.user.repository.IUserFrequentGoodsRepository;
import com.gs.lshly.biz.support.user.repository.IUserFrequentSkuRepository;
import com.gs.lshly.biz.support.user.service.bbb.pc.IPCBbbUserFrequentGoodsV2Service;
import com.gs.lshly.common.exception.BusinessException;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.bbb.pc.commodity.vo.PCBbbGoodsInfoVO;
import com.gs.lshly.common.struct.bbb.pc.user.dto.PCBbbUserFrequentGoodsV2DTO;
import com.gs.lshly.common.struct.bbb.pc.user.qto.PCBbbUserFrequentGoodsV2QTO;
import com.gs.lshly.common.struct.common.CommonShopVO;
import com.gs.lshly.common.utils.BeanCopyUtils;
import com.gs.lshly.common.utils.ListUtil;
import com.gs.lshly.middleware.mybatisplus.MybatisPlusUtil;
import com.gs.lshly.rpc.api.bbb.pc.commodity.IPCBbbGoodsInfoRpc;
import com.gs.lshly.rpc.api.common.ICommonShopRpc;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


/**
* <p>
*  服务实现类
* </p>
* @author xxfc
* @since 2020-12-10
*/
@Component
public class PCBbbUserFrequentGoodsV2ServiceImpl implements IPCBbbUserFrequentGoodsV2Service {

    @Autowired
    private IUserFrequentGoodsRepository repository;
    @Autowired
    private IUserFrequentSkuRepository frequentSkuRepository;
    @DubboReference
    private IPCBbbGoodsInfoRpc bbbGoodsInfoRpc;
    @DubboReference
    private ICommonShopRpc commonShopRpc;

    @Override
    public PageData<PCBbbGoodsInfoVO.InnerServiceVO> pageData(PCBbbUserFrequentGoodsV2QTO.QTO qto) {
        IPage<UserFrequentSku> page = MybatisPlusUtil.pager(qto);
        QueryWrapper<UserFrequentSku> frequentSkuQueryWrapper = MybatisPlusUtil.query();
        frequentSkuQueryWrapper.eq("user_id",qto.getJwtUserId());
        List<UserFrequentSku> frequentSkuList  = frequentSkuRepository.list(frequentSkuQueryWrapper);
        if(ObjectUtils.isEmpty(frequentSkuList)){
            return MybatisPlusUtil.toPageData(new ArrayList<>(),qto.getPageNum(),qto.getPageSize(),page.getTotal());
        }
        List<String> frequentSkuIdList = ListUtil.getIdList(UserFrequentSku.class,frequentSkuList,"skuId");
        List<PCBbbGoodsInfoVO.InnerServiceVO> innerGoodsSkuList =  bbbGoodsInfoRpc.innerServiceVOByIdList(frequentSkuIdList,qto);
        if(ObjectUtils.isEmpty(innerGoodsSkuList)){
            return MybatisPlusUtil.toPageData(new ArrayList<>(),qto.getPageNum(),qto.getPageSize(),page.getTotal());
        }
        //拼接常购数量
        for(PCBbbGoodsInfoVO.InnerServiceVO innerServiceVO:innerGoodsSkuList){
            for(UserFrequentSku userFrequentSku: frequentSkuList){
                if(userFrequentSku.getSkuId().equals(innerServiceVO.getSkuId())){
                    innerServiceVO.setSQuantity(userFrequentSku.getQuantity());
                    innerServiceVO.setSid(userFrequentSku.getId());
                    break;
                }
            }
        }
        return MybatisPlusUtil.toPageData(innerGoodsSkuList,qto.getPageNum(),qto.getPageSize(),page.getTotal());
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public void addMore(PCBbbUserFrequentGoodsV2DTO.ETO eto) {
        if(StringUtils.isBlank(eto.getJwtUserId())){
            throw new BusinessException("没有登录");
        }
        List<String> skuIdList = new ArrayList<>();
        List<UserFrequentSku> userFrequentSkuList = new ArrayList<>();
        for(PCBbbUserFrequentGoodsV2DTO.FrequentItem frequentItem :eto.getFrequentItemList()){
            skuIdList.add(frequentItem.getSkuId());
            UserFrequentSku userFrequentSku = new UserFrequentSku();
            BeanCopyUtils.copyProperties(frequentItem,userFrequentSku);
            userFrequentSkuList.add(userFrequentSku);
        }
        QueryWrapper<UserFrequentSku> userFrequentSkuQueryWrapper = MybatisPlusUtil.query();
        userFrequentSkuQueryWrapper.in("sku_id",skuIdList);
        userFrequentSkuQueryWrapper.eq("user_id",eto.getJwtUserId());
        frequentSkuRepository.remove(userFrequentSkuQueryWrapper);
        frequentSkuRepository.saveBatch(userFrequentSkuList);
    }

    @Override
    public void addOne(PCBbbUserFrequentGoodsV2DTO.OneETO dto) {
        if(StringUtils.isBlank(dto.getJwtUserId())){
            throw new BusinessException("没有登录");
        }
        if(StringUtils.isBlank(dto.getGoodsId()) || StringUtils.isBlank(dto.getGoodsId()) || StringUtils.isBlank(dto.getShopId())){
            throw new BusinessException("需要的参数不全");
        }
        CommonShopVO.ShopIdNameVO shopIdNameVO =  commonShopRpc.oneDetailShop(dto.getShopId());
        if(null == shopIdNameVO){
            throw new BusinessException("店铺不存在");
        }
        QueryWrapper<UserFrequentSku> frequentGoodsQueryWrapper = MybatisPlusUtil.query();
        frequentGoodsQueryWrapper.eq("sku_id",dto.getGoodsId());
        frequentGoodsQueryWrapper.eq("user_id",dto.getJwtUserId());
        UserFrequentSku userFrequentSku = frequentSkuRepository.getOne(frequentGoodsQueryWrapper);
        if(null == userFrequentSku){
            userFrequentSku = new UserFrequentSku();
            userFrequentSku.setQuantity(dto.getQuantity());
            userFrequentSku.setGoodsId(dto.getGoodsId());
            userFrequentSku.setSkuId(dto.getSkuId());
            userFrequentSku.setShopId(shopIdNameVO.getId());
            userFrequentSku.setMerchantId(shopIdNameVO.getMerchantId());
            frequentSkuRepository.save(userFrequentSku);
        }else{
            userFrequentSku.setQuantity(dto.getQuantity());
            frequentSkuRepository.updateById(userFrequentSku);
        }
    }

    @Override
    public void deleteUserFrequentGoods(PCBbbUserFrequentGoodsV2DTO.IdDTO dto) {
        QueryWrapper<UserFrequentSku> queryWrapper = MybatisPlusUtil.query();
        queryWrapper.eq("id",dto.getId());
        queryWrapper.eq("user_id",dto.getJwtUserId());
        UserFrequentSku userFrequentSku = frequentSkuRepository.getOne(queryWrapper);
        if(null == userFrequentSku){
            throw new BusinessException("常购商品不存在");
        }
        frequentSkuRepository.removeById(userFrequentSku.getId());
//        //SPU下面没有SKU,清除SPU
//        QueryWrapper<UserFrequentSku> queryListWrapper = MybatisPlusUtil.query();
//        queryListWrapper.eq("frequent_id",userFrequentSku.getFrequentId());
//        List<UserFrequentSku> checkList = frequentSkuRepository.list(queryListWrapper);
//        if(ObjectUtils.isEmpty(checkList)){
//            repository.removeById(userFrequentSku.getFrequentId());
//        }
    }

    @Override
    public void deleteBatch(PCBbbUserFrequentGoodsV2DTO.IdListDTO dto) {
        if(ObjectUtils.isEmpty(dto.getIdList())){
            throw new BusinessException("没有要删除的数据");
        }
        QueryWrapper<UserFrequentSku> queryWrapper = MybatisPlusUtil.query();
        queryWrapper.in("id",dto.getIdList());
        queryWrapper.eq("user_id",dto.getJwtUserId());
        frequentSkuRepository.remove(queryWrapper);
    }

}
