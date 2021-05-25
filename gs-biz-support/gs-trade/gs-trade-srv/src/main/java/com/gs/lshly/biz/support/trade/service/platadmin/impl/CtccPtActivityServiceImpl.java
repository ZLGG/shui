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
    public void deleteGoods(List<CtccPtActivityDTO.DeleteGoodsDTO> list) {
        list.forEach(m -> {
            UpdateWrapper<CtccActivityGoods> wrapper = MybatisPlusUtil.update();
            wrapper.set("falg", true);
            wrapper.eq("activity_id", m.getActivityId());
            wrapper.eq("goods_id", m.getGoodsId());
            activityGoodsRepository.update(wrapper);
        });
    }

    @Override
    public void updateGoodsState(List<CtccPtActivityDTO.RemoveGoodsDTO> list) {
        list.forEach(m -> {
            UpdateWrapper<CtccActivityGoods> wrapper = MybatisPlusUtil.update();
            wrapper.set("goods_state", m.getGoodsState());
            wrapper.eq("activity_id", m.getActivityId());
            wrapper.eq("goods_id", m.getGoodsId());
            activityGoodsRepository.update(wrapper);
        });
    }

    @Override
    public PageData<CtccPtActivityVO.ActivityListVO> queryActivityList(CtccPtActivityDTO.ActivityListDTO dto) {
        QueryWrapper<CtccPtActivity> queryWrapper = MybatisPlusUtil.query();
        queryWrapper.eq("flag",false);
        queryWrapper.orderByDesc("end_time");
        IPage<CtccPtActivity> page = MybatisPlusUtil.pager(dto);
        // 查看活动信息
        IPage<CtccPtActivity> pageData = ctccPtActivityMapper.queryList(page,queryWrapper);
        List<CtccPtActivityVO.ActivityListVO> activityListVOList = ListUtil.listCover(CtccPtActivityVO.ActivityListVO.class, pageData.getRecords());
        // 查看活动商品信息
        activityListVOList.forEach(activity ->{
            List<BbcGoodsInfoVO.CtccGoodsDetailVO> ctccGoodsDetailVOList = new ArrayList<>();
                // 查询商品详情
                QueryWrapper<CtccActivityGoods> ew = new QueryWrapper<>();
                ew.eq("flag", false);
                ew.eq("activity_id",activity.getId());
                List<CtccActivityGoods> ctccPtActivityList = activityGoodsRepository.list(ew);
                if(CollectionUtil.isNotEmpty(ctccPtActivityList)){
                    for(CtccActivityGoods activityGoods:ctccPtActivityList){
                        BbcGoodsInfoVO.CtccGoodsDetailVO ctccGoodsDetailVO = new BbcGoodsInfoVO.CtccGoodsDetailVO();
                        String goodsId = activityGoods.getGoodsId();
                        BbcGoodsInfoVO.DetailVO detailVO= bbcGoodsInfoRpc.detailGoodsInfo(new BbcGoodsInfoDTO.IdDTO(goodsId));
                        BeanUtils.copyProperties(detailVO,ctccGoodsDetailVO);
                        ctccGoodsDetailVO.setGoodsState(activityGoods.getGoodsState());
                        ctccGoodsDetailVO.setShopName(detailVO.getGoodsShopDetailVO().getShopName());
                        ctccGoodsDetailVO.setBrandName(ctccPtActivityMapper.getBrandNameByGoodsId(goodsId));
                        ctccGoodsDetailVO.setCategoryName(ctccCategoryMapper.getCtccCategoryName(goodsId));
                        ctccGoodsDetailVOList.add(ctccGoodsDetailVO);
                    }
                }
            activity.setGoodsList(ctccGoodsDetailVOList);
        });
        return new PageData<>(activityListVOList, dto.getPageNum(), dto.getPageSize(), pageData.getTotal());
    }

    @Override
    public CtccPtActivityVO.DetailVO getActivityDetail(String id) {
        // 查看活动信息
        CtccPtActivity ctccPtActivity = ctccPtActivityRepository.getById(id);
        CtccPtActivityVO.DetailVO detailVO = new CtccPtActivityVO.DetailVO();
        BeanUtils.copyProperties(ctccPtActivity,detailVO);
        // 查看活动banner图组信息
        List<CtccPtActivityImages> images = imagesMapper.selectByActivityId(id);
        List<CtccPtActivityDTO.ImageGroupDTO> imageGroupDTOList = ListUtil.listCover(CtccPtActivityDTO.ImageGroupDTO.class, images);
        detailVO.setImageGroupDTOList(imageGroupDTOList);
        return detailVO;
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
    public void addActivityGoods(List<CtccPtActivityDTO.AddActivityGoodsDTO> list) {
        List<CtccActivityGoods> activityGoodsList = ListUtil.listCover(CtccActivityGoods.class, list);
        activityGoodsList.forEach(m -> m.setGoodsState(GoodsStateEnum.已上架.getCode()));
        activityGoodsRepository.saveBatch(activityGoodsList);
    }

    @Override
    public CtccPtActivityVO.CategoryListVO getCategoryList(CtccPtActivityDTO.CateGoryListDTO listDTO) {

        QueryWrapper<CtccCategory> wrapper = MybatisPlusUtil.query();
        wrapper.eq("subject", SubjectEnum.电信国际.getCode());
        wrapper.orderByAsc("idx");

        // 根据商品名称查询商品信息及对应类目
//        if (StringUtils.isNotBlank(listDTO.getGoodsName())) {
//            // 根据商品名称模糊匹配类目id
//            List<String> categoryIds = bbcGoodsInfoRpc.getCategoryIdsByName(listDTO.getGoodsName());
//            wrapper.in("id", categoryIds);
//        }

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
                        BbcGoodsInfoVO.DetailVO detailVO= bbcGoodsInfoRpc.detailGoodsInfo(new BbcGoodsInfoDTO.IdDTO(goodsId));
                        BeanUtils.copyProperties(detailVO,ctccGoodsDetailVO);
                        ctccGoodsDetailVOList.add(ctccGoodsDetailVO);
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
