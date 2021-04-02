package com.gs.lshly.biz.support.user.service.bbb.pc.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.gs.lshly.biz.support.user.entity.UserFrequentGoods;
import com.gs.lshly.biz.support.user.entity.UserFrequentSku;
import com.gs.lshly.biz.support.user.repository.IUserFrequentGoodsRepository;
import com.gs.lshly.biz.support.user.repository.IUserFrequentSkuRepository;
import com.gs.lshly.biz.support.user.service.bbb.pc.IPCBbbUserFrequentGoodsService;
import com.gs.lshly.common.exception.BusinessException;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.bbb.pc.commodity.vo.PCBbbGoodsInfoVO;
import com.gs.lshly.common.struct.bbb.pc.user.dto.PCBbbUserFrequentGoodsDTO;
import com.gs.lshly.common.struct.bbb.pc.user.qto.PCBbbUserFrequentGoodsQTO;
import com.gs.lshly.common.struct.bbb.pc.user.vo.PCBbbUserFrequentGoodsVO;
import com.gs.lshly.common.utils.BeanCopyUtils;
import com.gs.lshly.common.utils.ListUtil;
import com.gs.lshly.rpc.api.bbb.pc.commodity.IPCBbbGoodsInfoRpc;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.gs.lshly.middleware.mybatisplus.MybatisPlusUtil;
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
public class PCBbbUserFrequentGoodsServiceImpl implements IPCBbbUserFrequentGoodsService {

    @Autowired
    private IUserFrequentGoodsRepository repository;
    @Autowired
    private IUserFrequentSkuRepository frequentSkuRepository;

    @DubboReference
    private IPCBbbGoodsInfoRpc bbbGoodsInfoRpc;

    @Override
    public PageData<PCBbbUserFrequentGoodsVO.ListVO> pageData(PCBbbUserFrequentGoodsQTO.QTO qto) {
        QueryWrapper<UserFrequentGoods> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id",qto.getJwtUserId());
        wrapper.orderByDesc("cdate");
        IPage<UserFrequentGoods> page = MybatisPlusUtil.pager(qto);
        repository.page(page, wrapper);
        if(ObjectUtils.isEmpty(page.getRecords())){
            return MybatisPlusUtil.toPageData(new ArrayList<>(),qto.getPageNum(),qto.getPageSize(),page.getTotal());
        }
        List<String> frequentIdList = new ArrayList<>();
        List<PCBbbUserFrequentGoodsVO.ListVO> voList = new ArrayList<>();
        for(UserFrequentGoods frequentGoods:page.getRecords()){
            frequentIdList.add(frequentGoods.getId());
            PCBbbUserFrequentGoodsVO.ListVO listVO = new PCBbbUserFrequentGoodsVO.ListVO();
            listVO.setId(frequentGoods.getId());
            listVO.setGoodsId(frequentGoods.getGoodsId());
            voList.add(listVO);
        }
        QueryWrapper<UserFrequentSku> frequentSkuQueryWrapper = MybatisPlusUtil.query();
        frequentSkuQueryWrapper.in("frequent_id",frequentIdList);
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
        //常购SKU归组SPU
        for(PCBbbGoodsInfoVO.InnerServiceVO innerServiceVO:innerGoodsSkuList){
            for(PCBbbUserFrequentGoodsVO.ListVO listVO:voList){
                if(innerServiceVO.getGoodsId().equals(listVO.getGoodsId())){
                    listVO.getGoodsSkuList().add(innerServiceVO);
                }
            }
        }
        return MybatisPlusUtil.toPageData(voList,qto.getPageNum(),qto.getPageSize(),page.getTotal());
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public void addMore(PCBbbUserFrequentGoodsDTO.ETO eto) {
        if(StringUtils.isBlank(eto.getJwtUserId())){
            throw new BusinessException("没有登录");
        }
        List<String> goodsIdList = ListUtil.getIdList(PCBbbUserFrequentGoodsDTO.FrequentItem.class,eto.getFrequentItemList(),"goodsId");
        //查找已存在的SPU常购
        QueryWrapper<UserFrequentGoods> frequentGoodsQueryWrapper = MybatisPlusUtil.query();
        frequentGoodsQueryWrapper.in("goods_id",goodsIdList);
        frequentGoodsQueryWrapper.eq("user_id",eto.getJwtUserId());
        List<UserFrequentGoods> oldUserFrequentGoodsList = repository.list(frequentGoodsQueryWrapper);
        List<UserFrequentGoods> fullUserFrequentGoodsList = new ArrayList<>();
        List<UserFrequentGoods> createUserFrequentGoodsList = new ArrayList<>();
        fullUserFrequentGoodsList.addAll(oldUserFrequentGoodsList);
        for(PCBbbUserFrequentGoodsDTO.FrequentItem frequentItem:eto.getFrequentItemList()){
            boolean find = false;
            for(UserFrequentGoods userFrequentGoods: oldUserFrequentGoodsList){
                if(userFrequentGoods.getGoodsId().equals(frequentItem.getGoodsId())){
                   find = true;
                   break;
                }
            }
            if(find == false){
                UserFrequentGoods userFrequentGoods = new UserFrequentGoods();
                BeanCopyUtils.copyProperties(frequentItem,userFrequentGoods);
                userFrequentGoods.setUserId(eto.getJwtUserId());
                createUserFrequentGoodsList.add(userFrequentGoods);
                fullUserFrequentGoodsList.add(userFrequentGoods);
            }
        }
        if(ObjectUtils.isNotEmpty(createUserFrequentGoodsList)){
            repository.saveBatch(createUserFrequentGoodsList);
        }
        List<UserFrequentSku> createUserFrequentSkuList = new ArrayList<>();
        for(PCBbbUserFrequentGoodsDTO.FrequentItem frequentItem:eto.getFrequentItemList()){
            UserFrequentSku userFrequentSku = new UserFrequentSku();
            BeanCopyUtils.copyProperties(frequentItem,userFrequentSku);
            userFrequentSku.setUserId(eto.getJwtUserId());
            createUserFrequentSkuList.add(userFrequentSku);
        }
        for(UserFrequentSku userFrequentSku:createUserFrequentSkuList){
            for(UserFrequentGoods userFrequentGoods:fullUserFrequentGoodsList){
                if(userFrequentGoods.getGoodsId().equals(userFrequentSku.getGoodsId())){
                    userFrequentSku.setFrequentId(userFrequentGoods.getId());
                    break;
                }
            }
        }
        List<String> frequentIdList = ListUtil.getIdList(UserFrequentGoods.class,fullUserFrequentGoodsList);
        //保存SKU信息
        QueryWrapper<UserFrequentSku> frequentSkuQueryWrapper = MybatisPlusUtil.query();
        frequentSkuQueryWrapper.in("frequent_id",frequentIdList);
        List<UserFrequentSku> userFrequentSkuList = frequentSkuRepository.list(frequentSkuQueryWrapper);
        if(ObjectUtils.isEmpty(userFrequentSkuList)){
            frequentSkuRepository.saveBatch(createUserFrequentSkuList);
        }else{
            for(UserFrequentSku oldUserFrequentSku:userFrequentSkuList){
                for(UserFrequentSku userFrequentSku:createUserFrequentSkuList){
                    if(oldUserFrequentSku.getSkuId().equals(userFrequentSku.getSkuId())){
                        userFrequentSku.setId(oldUserFrequentSku.getId());
                        break;
                    }
                }
            }
            frequentSkuRepository.saveOrUpdateBatch(createUserFrequentSkuList);
        }
    }

    @Override
    public void addOne(PCBbbUserFrequentGoodsDTO.OneETO dto) {
        if(StringUtils.isBlank(dto.getJwtUserId())){
            throw new BusinessException("没有登录");
        }
        if(StringUtils.isBlank(dto.getGoodsId())){
            throw new BusinessException("商品ID不能为空");
        }
        QueryWrapper<UserFrequentGoods> frequentGoodsQueryWrapper = MybatisPlusUtil.query();
        frequentGoodsQueryWrapper.eq("goods_id",dto.getGoodsId());
        frequentGoodsQueryWrapper.eq("user_id",dto.getJwtUserId());
        UserFrequentGoods userFrequentGoods = repository.getOne(frequentGoodsQueryWrapper);
        if(null == userFrequentGoods){
            userFrequentGoods = new UserFrequentGoods();
            BeanCopyUtils.copyProperties(dto,userFrequentGoods);
            repository.save(userFrequentGoods);
        }
    }

    @Override
    public void deleteUserFrequentGoods(PCBbbUserFrequentGoodsDTO.IdDTO dto) {
        QueryWrapper<UserFrequentSku> queryWrapper = MybatisPlusUtil.query();
        queryWrapper.eq("id",dto.getId());
        queryWrapper.eq("user_id",dto.getJwtUserId());
        UserFrequentSku userFrequentSku = frequentSkuRepository.getOne(queryWrapper);
        if(null == userFrequentSku){
            throw new BusinessException("常购商品不存在");
        }
        frequentSkuRepository.removeById(userFrequentSku.getId());
        //SPU下面没有SKU,清除SPU
        QueryWrapper<UserFrequentSku> queryListWrapper = MybatisPlusUtil.query();
        queryListWrapper.eq("frequent_id",userFrequentSku.getFrequentId());
        List<UserFrequentSku> checkList = frequentSkuRepository.list(queryListWrapper);
        if(ObjectUtils.isEmpty(checkList)){
            repository.removeById(userFrequentSku.getFrequentId());
        }
    }

    @Override
    public void deleteBatch(PCBbbUserFrequentGoodsDTO.IdListDTO dto) {
        if(ObjectUtils.isEmpty(dto.getIdList())){
            throw new BusinessException("没有要删除的数据");
        }
        QueryWrapper<UserFrequentSku> queryWrapper = MybatisPlusUtil.query();
        queryWrapper.in("id",dto.getIdList());
        queryWrapper.eq("user_id",dto.getJwtUserId());
        List<UserFrequentSku> frequentSkuList = frequentSkuRepository.list(queryWrapper);
        if(ObjectUtils.isNotEmpty(frequentSkuList)){
            List<String> frequentIdList = new ArrayList<>();
            List<String> frequentSkuIdList = new ArrayList<>();
            for(UserFrequentSku frequentSku:frequentSkuList){
                if(!frequentIdList.contains(frequentSku.getFrequentId())){
                    frequentIdList.add(frequentSku.getFrequentId());
                }
                if(!frequentSkuIdList.contains(frequentSku.getId())){
                    frequentSkuIdList.add(frequentSku.getId());
                }
            }
            frequentSkuRepository.removeByIds(frequentSkuIdList);
            QueryWrapper<UserFrequentSku> queryListWrapper = MybatisPlusUtil.query();
            queryListWrapper.in("frequent_id",frequentIdList);
            List<UserFrequentSku> checkList = frequentSkuRepository.list(queryListWrapper);
            //没有找到SKU常购ID
            if(ObjectUtils.isEmpty(checkList)){
                repository.removeByIds(frequentIdList);
            }else {
                //如果有常购ID,找到那些SPU常购ID下没有SKU常购ID的数据，删除
                List<String> canDeleteIdList = new ArrayList<>();
                for(String frequentId:frequentIdList){
                    boolean check = true;
                    for(UserFrequentSku frequentSku:checkList){
                        if(frequentId.equals(frequentSku.getFrequentId())){
                            check = false;
                            break;
                        }
                    }
                    if(check == true){
                        canDeleteIdList.add(frequentId);
                    }
                }
                repository.removeByIds(canDeleteIdList);
            }
        }
    }

}
