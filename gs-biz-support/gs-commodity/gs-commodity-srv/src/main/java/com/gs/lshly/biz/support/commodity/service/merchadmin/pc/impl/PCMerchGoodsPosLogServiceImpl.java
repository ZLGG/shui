package com.gs.lshly.biz.support.commodity.service.merchadmin.pc.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.gs.lshly.biz.support.commodity.entity.GoodsPosLog;
import com.gs.lshly.biz.support.commodity.entity.GoodsPosSkuLog;
import com.gs.lshly.biz.support.commodity.repository.IGoodsPosLogRepository;
import com.gs.lshly.biz.support.commodity.repository.IGoodsPosSkuLogRepository;
import com.gs.lshly.biz.support.commodity.service.merchadmin.pc.IPCMerchGoodsPosLogService;
import com.gs.lshly.common.enums.SingleStateEnum;
import com.gs.lshly.common.enums.commondity.GoodsStockSubtractTypeEnum;
import com.gs.lshly.common.exception.BusinessException;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.merchadmin.pc.commodity.dto.PCMerchGoodsPosLogDTO;
import com.gs.lshly.common.struct.merchadmin.pc.commodity.qto.PCMerchGoodsPosLogQTO;
import com.gs.lshly.common.struct.merchadmin.pc.commodity.vo.PCMerchGoodsPosLogVO;
import com.gs.lshly.common.struct.merchadmin.pc.commodity.vo.PCMerchGoodsPosSkuLogVO;
import com.gs.lshly.common.utils.ListUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.gs.lshly.middleware.mybatisplus.MybatisPlusUtil;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;


/**
* <p>
*  服务实现类
* </p>
* @author Starry
* @since 2020-12-15
*/
@Component
public class PCMerchGoodsPosLogServiceImpl implements IPCMerchGoodsPosLogService {

    @Autowired
    private IGoodsPosLogRepository repository;
    @Autowired
    private IGoodsPosSkuLogRepository skuLogRepository;


    @Override
    public PageData<PCMerchGoodsPosLogVO.DetailListVO> pageData(PCMerchGoodsPosLogQTO.QTO qto) {
        QueryWrapper<GoodsPosLog> wrapper = MybatisPlusUtil.query();
        if (StringUtils.isNotBlank(qto.getNumIid())){
            wrapper.like("num_iid",qto.getNumIid());
        }
        if (StringUtils.isNotBlank(qto.getTitle())){
            wrapper.like("title",qto.getTitle());
        }
        IPage<GoodsPosLog> page = MybatisPlusUtil.pager(qto);
        IPage<GoodsPosLog> goodsPosLogIPage = repository.page(page,wrapper);
        if (ObjectUtils.isEmpty(goodsPosLogIPage) || ObjectUtils.isEmpty(goodsPosLogIPage.getRecords())){
            return new PageData<>();
        }
        List<PCMerchGoodsPosLogVO.DetailListVO> detailListVOS = getDetailListVO(goodsPosLogIPage.getRecords());
        return new PageData<>(detailListVOS,qto.getPageNum(),qto.getPageSize(),goodsPosLogIPage.getTotal());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addGoodsPosLog(List<PCMerchGoodsPosLogDTO.ETO> etos) {
        if (ObjectUtils.isNotEmpty(etos)){
            List<GoodsPosLog> posLogs = ListUtil.listCover(GoodsPosLog.class,etos);
            repository.saveBatch(posLogs);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteGoodsPosLog(PCMerchGoodsPosLogDTO.NumIidDTO dto) {
        if (ObjectUtils.isEmpty(dto)){
            throw new BusinessException("商品编号参数不能为空！！");
        }
        //删除skupos记录
        QueryWrapper<GoodsPosSkuLog> wrapper = MybatisPlusUtil.query();
        wrapper.eq("num_iid",dto.getNumIid());
        skuLogRepository.remove(wrapper);

        QueryWrapper<GoodsPosLog> queryWrapper = MybatisPlusUtil.query();
        queryWrapper.eq("num_iid",dto.getNumIid());
        repository.remove(queryWrapper);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteBatchGoodsPosLog(PCMerchGoodsPosLogDTO.NumIidListDTO dto) {
        //删除skupos记录
        QueryWrapper<GoodsPosSkuLog> wrapper = MybatisPlusUtil.query();
        wrapper.in("num_iid",dto.getListNumIid());
        skuLogRepository.remove(wrapper);

        QueryWrapper<GoodsPosLog> queryWrapper = MybatisPlusUtil.query();
        queryWrapper.in("num_iid",dto.getListNumIid());
        repository.remove(queryWrapper);
    }

    @Override
    public List<PCMerchGoodsPosLogVO.DetailListVO> getExportData(PCMerchGoodsPosLogDTO.IdListDTO dto) {
        if (ObjectUtils.isEmpty(dto.getIdList())){
            throw new BusinessException("请选择要导出的数据记录！");
        }
        QueryWrapper<GoodsPosLog> wrapper = MybatisPlusUtil.query();
        wrapper.in("id",dto.getIdList());
        List<GoodsPosLog> posLogs = repository.list(wrapper);
        if (ObjectUtils.isEmpty(posLogs)){
            return new ArrayList<>();
        }
        List<PCMerchGoodsPosLogVO.DetailListVO> listVOS = getDetailListVO(posLogs);
        return listVOS;
    }

    @Override
    public PCMerchGoodsPosLogVO.DetailVO detailGoodsPosLog(PCMerchGoodsPosLogDTO.IdDTO dto) {
        QueryWrapper<GoodsPosLog> wrapper = MybatisPlusUtil.query();
        wrapper.eq("id",dto.getId());
        GoodsPosLog goodsPosLog = repository.getOne(wrapper);
        if(ObjectUtils.isEmpty(goodsPosLog)){
            throw new BusinessException("数据查询异常！！");
        }
        PCMerchGoodsPosLogVO.DetailVO detailVo = new PCMerchGoodsPosLogVO.DetailVO();
        BeanUtils.copyProperties(goodsPosLog, detailVo);
        //填充商品图片
        detailVo.setGoodsImages(getGoodsImages(goodsPosLog.getItemImgs(),goodsPosLog.getPicUrl()));
        //填充库存扣减方式
        if (goodsPosLog.getSubStock().equals(0)){
            detailVo.setSubStock(GoodsStockSubtractTypeEnum.下单减库存.getCode());
        }
        if (goodsPosLog.getSubStock().equals(1)){
            detailVo.setSubStock(GoodsStockSubtractTypeEnum.支付减库存.getCode());
        }
        List<GoodsPosSkuLog> skuLogs = getGoodsPosSkuLog(goodsPosLog.getNumIid());

        //填充商品描述
        detailVo.setDesc(StringUtils.isNotBlank(goodsPosLog.getDesc())?goodsPosLog.getDesc():"");

        //填充商品规格类型
        List<String> list = list(skuLogs);
        if (ObjectUtils.isEmpty(list)){
            detailVo.setIsSingle(SingleStateEnum.单品.getCode());
            GoodsPosSkuLog skuLog = skuLogs.get(0);
            //填充商品价格
            detailVo.setPrice(skuLog.getPrice());

            //填充商品成本价格
            detailVo.setCostPrice(skuLog.getCostPrice());

            //填充商品市场价格
            detailVo.setMktPrice(skuLog.getMarketPrice());
        }else {
            detailVo.setIsSingle(SingleStateEnum.多规格.getCode());
            //填充商品规格信息
            List<PCMerchGoodsPosSkuLogVO.DetailVO> skuList = skuLogs.stream().map(e ->{
                PCMerchGoodsPosSkuLogVO.DetailVO sku = new PCMerchGoodsPosSkuLogVO.DetailVO();
                BeanUtils.copyProperties(e,sku);
                //填充sku图片
                sku.setSkuImgs(getImage(e.getSkuImgs()));
                return sku;
            }).collect(Collectors.toList());
            detailVo.setPosSkuLogVOS(skuList);

            detailVo.setSpecList(getSpecListVO(StringUtils.join(list,";")));
        }
        return detailVo;
    }

    private List<String>  list(List<GoodsPosSkuLog> skuLogs){
        if (ObjectUtils.isNotEmpty(skuLogs)){
            List<String> list = skuLogs.stream().map(GoodsPosSkuLog::getPropertiesName).filter(s ->!s.equals("")).collect(Collectors.toList());
            if (CollectionUtils.isEmpty(list)){
                return new ArrayList<>();
            }
            return list;
        }
        return new ArrayList<>();
    }

    private List<GoodsPosSkuLog> getGoodsPosSkuLog(String numIid){
        QueryWrapper<GoodsPosSkuLog> wrapper = MybatisPlusUtil.query();
        wrapper.eq("num_iid",numIid);
        List<GoodsPosSkuLog> skuLogs = skuLogRepository.list(wrapper);
        if (ObjectUtils.isEmpty(skuLogs)){
            return new ArrayList<>();
        }
        return skuLogs;
    }

    private List<String> getGoodsImages(String imageItems,String picUrl){
        List<String> list = new ArrayList<>();
        if (StringUtils.isNotBlank(imageItems)){
            JSONArray jsonArray = JSON.parseArray(imageItems);
            for (int i = 0;i <jsonArray.size();i++){
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String url = jsonObject.getString("url");
                list.add(url);
            }
        }
        if (StringUtils.isNotBlank(picUrl)){
            list.add(picUrl);
        }
        return list;
    }

    private static List<PCMerchGoodsPosSkuLogVO.SpecListVO> getSpecListVO(String specs) {
        List<PCMerchGoodsPosSkuLogVO.SpecListVO> specListVOS = new ArrayList<>();
        List<String> strings = Arrays.asList(specs.split(";"));
        if (ObjectUtils.isNotEmpty(strings)) {
            //去重
            List<String> list = strings.stream().distinct().collect(Collectors.toList());
            List<PCMerchGoodsPosSkuLogVO.SpecVO> specVOS = new ArrayList<>();
            for (int i = 0; i < list.size(); i++) {
                String[] asList = list.get(i).split(":");
                PCMerchGoodsPosSkuLogVO.SpecVO specVO = new PCMerchGoodsPosSkuLogVO.SpecVO();
                specVO.setSpecName(asList[asList.length - 2]);
                specVO.setSpecValue(asList[asList.length - 1]);
                specVOS.add(specVO);
            }
            //按规格名称分组
            Map<String,List<PCMerchGoodsPosSkuLogVO.SpecVO>> map = specVOS.stream().collect(Collectors.groupingBy(PCMerchGoodsPosSkuLogVO.SpecVO::getSpecName));
            //组装
            specListVOS = map.values().stream().map(e ->{
                PCMerchGoodsPosSkuLogVO.SpecListVO specListVO = new PCMerchGoodsPosSkuLogVO.SpecListVO();
                List<String> specValues = e.stream().map(PCMerchGoodsPosSkuLogVO.SpecVO::getSpecValue).collect(Collectors.toList());
                specListVO.setSpecName(e.get(0).getSpecName());
                specListVO.setSpecValues(specValues);
                return specListVO;
            }).collect(Collectors.toList());
        }
        return specListVOS;
    }

    private  String getImage(String skuImages){
        if (skuImages !=null){
            JSONArray arr = JSONArray.parseArray(skuImages);
            if (ObjectUtils.isEmpty(arr)){
                return "";
            }
            JSONObject obj = arr.getJSONObject(0);
            String imgUrl = obj.getString("url");
            return imgUrl;
        }
        return "";
    }

    private List<PCMerchGoodsPosLogVO.DetailListVO> getDetailListVO(List<GoodsPosLog> logs){
        List<PCMerchGoodsPosLogVO.DetailListVO> detailListVOS = logs.parallelStream().map(e ->{
                    PCMerchGoodsPosLogVO.DetailListVO listVO = new PCMerchGoodsPosLogVO.DetailListVO();
                    BeanUtils.copyProperties(e,listVO);
                    List<GoodsPosSkuLog> skuLogs = getGoodsPosSkuLog(e.getNumIid());
                    if (ObjectUtils.isEmpty(list(skuLogs))){
                        listVO.setIsSingle(SingleStateEnum.单品.getCode());
                    }else {
                        listVO.setIsSingle(SingleStateEnum.多规格.getCode());
                    }
                    return listVO;
                }).collect(Collectors.toList());
        return detailListVOS;
    }

}
