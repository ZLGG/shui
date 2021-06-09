package com.gs.lshly.biz.support.trade.service.platadmin.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.gs.lshly.biz.support.trade.entity.*;
import com.gs.lshly.biz.support.trade.mapper.CtccCategoryMapper;
import com.gs.lshly.biz.support.trade.mapper.CtccPtActivityImagesMapper;
import com.gs.lshly.biz.support.trade.mapper.CtccPtActivityMapper;
import com.gs.lshly.biz.support.trade.repository.ICtccActivityGoodsRepository;
import com.gs.lshly.biz.support.trade.repository.ICtccCategoryGoodsRepository;
import com.gs.lshly.biz.support.trade.repository.ICtccCategoryRepository;
import com.gs.lshly.biz.support.trade.repository.ICtccPtActivityRepository;
import com.gs.lshly.biz.support.trade.service.platadmin.ICtccPtActivityService;
import com.gs.lshly.common.enums.GoodsStateEnum;
import com.gs.lshly.common.enums.SubjectEnum;
import com.gs.lshly.common.enums.TrueFalseEnum;
import com.gs.lshly.common.exception.BusinessException;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.bbc.commodity.dto.BbcGoodsInfoDTO;
import com.gs.lshly.common.struct.bbc.commodity.vo.BbcGoodsInfoVO;
import com.gs.lshly.common.struct.platadmin.trade.dto.CtccPtActivityDTO;
import com.gs.lshly.common.struct.platadmin.trade.vo.CtccPtActivityVO;
import com.gs.lshly.common.utils.BeanCopyUtils;
import com.gs.lshly.common.utils.ListUtil;
import com.gs.lshly.middleware.mybatisplus.MybatisPlusUtil;
import com.gs.lshly.rpc.api.bbc.commodity.IBbcGoodsInfoRpc;
import com.gs.lshly.rpc.api.bbc.stock.IBbcStockRpc;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Author yangxi
 * @create 2021/5/8 9:55
 */
@Component
public class CtccPtActivityServiceImpl implements ICtccPtActivityService {
    @Autowired
    private ICtccPtActivityRepository ctccPtActivityRepository;
    @Autowired
    private ICtccActivityGoodsRepository activityGoodsRepository;
    @Autowired
    private ICtccCategoryGoodsRepository goodsRepository;
    @Autowired
    private IBbcGoodsInfoRpc bbcGoodsInfoRpc;
    @Autowired
    private ICtccCategoryRepository repository;
    @Autowired
    private CtccPtActivityMapper ctccPtActivityMapper;
    @Autowired
    private CtccPtActivityImagesMapper imagesMapper;
    @Autowired
    private CtccCategoryMapper ctccCategoryMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void modifyActivity(CtccPtActivityDTO.ModifyDTO modifyDTO) {
        if (modifyDTO.getImageGroupDTOList().size() > 5) {
            throw new BusinessException("Banner图片最多上传5张");
        }
        // 修改活动信息
        CtccPtActivity ctccPtActivity = new CtccPtActivity();
        BeanUtils.copyProperties(modifyDTO, ctccPtActivity);
        ctccPtActivity.setUserId(modifyDTO.getJwtUserId()).setGmtModify(new Date());
        ctccPtActivityRepository.updateById(ctccPtActivity);
        // 删除原有banner图片
        imagesMapper.delete(new QueryWrapper<CtccPtActivityImages>().eq("activity_id",modifyDTO.getId()));
        // 修改banner图片信息
        List<CtccPtActivityDTO.ImageGroupDTO> imageList = modifyDTO.getImageGroupDTOList();
        imageList.forEach(image -> {
            CtccPtActivityImages ctccPtActivityImages = new CtccPtActivityImages();
            ctccPtActivityImages.setActivityId(ctccPtActivity.getId());
            BeanUtils.copyProperties(image,ctccPtActivityImages);
            imagesMapper.insert(ctccPtActivityImages);
        });
    }

    @Override
    public void sortedGoods(CtccPtActivityDTO.SortedGoodsDTO dto) {
        UpdateWrapper<CtccActivityGoods> wrapper = MybatisPlusUtil.update();
        wrapper.set("idx", dto.getIdx());
        wrapper.eq("goods_id", dto.getGoodsId());
        activityGoodsRepository.update(wrapper);
    }

    @Override
    public void deleteGoods(CtccPtActivityDTO.DeleteGoodsDTO list) {
        list.getGoodsIdList().forEach(m -> {
            UpdateWrapper<CtccActivityGoods> wrapper = MybatisPlusUtil.update();
            wrapper.set("flag", true);
            wrapper.eq("goods_id", m);
            activityGoodsRepository.update(wrapper);
        });
    }

    @Override
    public void updateGoodsState(List<CtccPtActivityDTO.RemoveGoodsDTO> list) {
        list.forEach(m -> {
            UpdateWrapper<CtccActivityGoods> wrapper = MybatisPlusUtil.update();
            wrapper.set("goods_state", m.getGoodsState());
            wrapper.eq("goods_id", m.getGoodsId());
            activityGoodsRepository.update(wrapper);
        });
    }

    @Override
    public PageData<BbcGoodsInfoVO.CtccGoodsDetailVO> queryActivityList(CtccPtActivityDTO.ActivityListDTO dto) {
        // 查询电信国际活动商品
        QueryWrapper<CtccActivityGoods> queryWrapper = MybatisPlusUtil.query();
        queryWrapper.eq("flag",false);
        queryWrapper.orderByDesc("idx");
        if (StringUtils.isNotBlank(dto.getCategoryId())) {
            queryWrapper.eq("category_id", dto.getCategoryId());
        }
        if (StringUtils.isNotBlank(dto.getGoodsName())) {
            // 根据商品名称模糊匹配商品id
            List<String> goodsIds = bbcGoodsInfoRpc.getGoodsIdsByName(dto.getGoodsName());
            if(CollectionUtil.isNotEmpty(goodsIds)){
            	queryWrapper.in("goods_id",goodsIds);
            }else{
            	queryWrapper.in("goods_id","AAAAA");
            }
            
        }
        if (null != dto.getStatus()) {
            queryWrapper.eq("goods_state", dto.getStatus());
        }
        IPage<CtccActivityGoods> page = MybatisPlusUtil.pager(dto);
        IPage<CtccActivityGoods> pageData = activityGoodsRepository.page(page,queryWrapper);
        List<BbcGoodsInfoVO.CtccGoodsDetailVO> resultList = new ArrayList<>();
        // 查看活动商品信息
        pageData.getRecords().forEach(goods ->{
            // 查询商品详情
            BbcGoodsInfoVO.CtccGoodsDetailVO ctccGoodsDetailVO = new BbcGoodsInfoVO.CtccGoodsDetailVO();
            String goodsId = goods.getGoodsId();
            BbcGoodsInfoVO.DetailVO detailVO = bbcGoodsInfoRpc.detailGoodsInfo(new BbcGoodsInfoDTO.IdDTO(goodsId));
            BeanUtils.copyProperties(detailVO, ctccGoodsDetailVO);
            ctccGoodsDetailVO.setGoodsState(goods.getGoodsState());
            ctccGoodsDetailVO.setShopName(detailVO.getGoodsShopDetailVO().getShopName());
            ctccGoodsDetailVO.setBrandName(ctccPtActivityMapper.getBrandNameByGoodsId(goodsId));
            ctccGoodsDetailVO.setCategoryName(ctccCategoryMapper.getCtccCategoryName(goodsId));
            ctccGoodsDetailVO.setIdx(goods.getIdx());
            resultList.add(ctccGoodsDetailVO);
        });
        return new PageData<>(resultList, dto.getPageNum(), dto.getPageSize(), pageData.getTotal());
    }

    @Override
    public BbcGoodsInfoVO.CtccGoodsDetailVO getActivityDetail(String id) {

        BbcGoodsInfoVO.CtccGoodsDetailVO ctccGoodsDetailVO = new BbcGoodsInfoVO.CtccGoodsDetailVO();
        BbcGoodsInfoVO.DetailVO detailVO = bbcGoodsInfoRpc.detailGoodsInfo(new BbcGoodsInfoDTO.IdDTO(id));
        BeanUtils.copyProperties(detailVO, ctccGoodsDetailVO);
        ctccGoodsDetailVO.setGoodsState(ctccCategoryMapper.getGoodsState(id));
        ctccGoodsDetailVO.setShopName(detailVO.getGoodsShopDetailVO().getShopName());
        ctccGoodsDetailVO.setBrandName(ctccPtActivityMapper.getBrandNameByGoodsId(id));
        ctccGoodsDetailVO.setCategoryName(ctccCategoryMapper.getCtccCategoryName(id));
        ctccGoodsDetailVO.setStockQuantity(detailVO.getIsSingle() == 10 ? detailVO.getSingleSkuStock():detailVO.getSkuVO().getSkuStock());
        return ctccGoodsDetailVO;
    }

    @Override
    public void addCategoryGoods(List<CtccPtActivityDTO.AddCategoryGoodsDTO> list) {
        List<CtccCategoryGoods> categoryGoodsList = ListUtil.listCover(CtccCategoryGoods.class, list);
        categoryGoodsList.forEach(category ->{
            category.setSubject(SubjectEnum.电信国际.getCode())
                    .setTerminal(20);
        });
        goodsRepository.saveBatch(categoryGoodsList);
    }

    @Override
    public void addCategory(CtccPtActivityDTO.AddCategoryDTO dto) {
        CtccCategory ctccCategory = new CtccCategory()
                .setOperator(dto.getJwtUserId())
                .setIdx(dto.getIdx())
                .setImageUrl(dto.getImageUrl())
                .setLevel(1)
                .setName(dto.getName())
                .setRemark(dto.getRemark())
                .setSubject(SubjectEnum.电信国际.getCode())
                .setTerminal(20);
        repository.save(ctccCategory);
    }

    @Override
    public void addActivityGoods(List<CtccPtActivityDTO.AddGoodsDTO> list) {
        List<CtccActivityGoods> activityGoodsList = ListUtil.listCover(CtccActivityGoods.class, list);
        activityGoodsList.forEach(m -> {
            m.setGoodsState(GoodsStateEnum.已上架.getCode());
        });
        activityGoodsRepository.saveBatch(activityGoodsList);
    }

    @Override
    public CtccPtActivityVO.CategoryListVO getCategoryList(CtccPtActivityDTO.CateGoryListDTO listDTO) {

        QueryWrapper<CtccCategory> wrapper = MybatisPlusUtil.query();
        wrapper.eq("subject", SubjectEnum.电信国际.getCode());
        wrapper.orderByAsc("idx");
        if (StringUtils.isNotBlank(listDTO.getCategoryId())) {
            wrapper.eq("id", listDTO.getCategoryId());
        }

        // 根据商品名称查询商品信息及对应类目
        if (StringUtils.isNotBlank(listDTO.getGoodsName())) {
            // 根据商品名称模糊匹配类目id
            List<String> categoryIds = bbcGoodsInfoRpc.getCategoryIdsByName(listDTO.getGoodsName());
            if (ObjectUtils.isEmpty(categoryIds)) {
                return null;
            }
            wrapper.in("id", categoryIds);
        }

        CtccPtActivityVO.CategoryListVO categoryListVO = new CtccPtActivityVO.CategoryListVO();

        // 查询商品类别
        List<CtccCategory> ctccCategoryList = repository.list( wrapper);
        if(CollectionUtil.isNotEmpty(ctccCategoryList)){
            List<CtccPtActivityVO.CtccCategoryVO> categorys = new ArrayList<>();
            CtccPtActivityVO.CtccCategoryVO ctccCategoryVO = null;
            for(CtccCategory ctccCategory:ctccCategoryList){
                QueryWrapper<CtccCategoryGoods> wrapperGoods = MybatisPlusUtil.query();
                ctccCategoryVO = new CtccPtActivityVO.CtccCategoryVO();
                BeanCopyUtils.copyProperties(ctccCategory, ctccCategoryVO);
                // 查询类别对应商品
                wrapperGoods.eq("category_id", ctccCategory.getId());
                List<CtccCategoryGoods> ctccCategoryGoods = goodsRepository.list(wrapperGoods);
                // 查询商品详情
                List<BbcGoodsInfoVO.CtccGoodsDetailVO> ctccGoodsDetailVOList = new ArrayList<>();
                if(CollectionUtil.isNotEmpty(ctccCategoryGoods)){
                    for(CtccCategoryGoods ctccCategoryGood:ctccCategoryGoods){
                        BbcGoodsInfoVO.CtccGoodsDetailVO ctccGoodsDetailVO = new BbcGoodsInfoVO.CtccGoodsDetailVO();
                        String goodsId = ctccCategoryGood.getGoodsId();
                        BbcGoodsInfoVO.DetailVO detailVO = bbcGoodsInfoRpc.detailGoodsInfo(new BbcGoodsInfoDTO.IdDTO(goodsId));
                        // 根据商品名称模糊匹配商品信息
                        if (StringUtils.isNotBlank(listDTO.getGoodsName())) {
                            if (detailVO.getGoodsName().contains(listDTO.getGoodsName())) {
                                BeanUtils.copyProperties(detailVO,ctccGoodsDetailVO);
                                ctccGoodsDetailVOList.add(ctccGoodsDetailVO);
                            }
                        }else {
                            BeanUtils.copyProperties(detailVO,ctccGoodsDetailVO);
                            ctccGoodsDetailVOList.add(ctccGoodsDetailVO);
                        }
                    }
                }
                ctccCategoryVO.setGoodsList(ctccGoodsDetailVOList);
                categorys.add(ctccCategoryVO);
            }
            categoryListVO.setCategorys(categorys);
        }
        return categoryListVO;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addActivity(CtccPtActivityDTO.AddDTO addDTO) {
        if (addDTO.getImageGroupDTOList().size() > 5) {
            throw new BusinessException("Banner图片最多上传5张");
        }
        // 保存活动信息
        CtccPtActivity ctccPtActivity = new CtccPtActivity();
        BeanUtils.copyProperties(addDTO, ctccPtActivity);
        ctccPtActivity.setGmtCreate(new Date()).setGmtModify(new Date()).setFlag(false).setUserId(addDTO.getJwtUserId());
        ctccPtActivityRepository.save(ctccPtActivity);
        // 保存banner图片信息
        List<CtccPtActivityDTO.ImageGroupDTO> imageList = addDTO.getImageGroupDTOList();
        imageList.forEach(image -> {
            CtccPtActivityImages ctccPtActivityImages = new CtccPtActivityImages();
            ctccPtActivityImages.setActivityId(ctccPtActivity.getId());
            BeanUtils.copyProperties(image,ctccPtActivityImages);
            imagesMapper.insert(ctccPtActivityImages);
        });
    }
}
