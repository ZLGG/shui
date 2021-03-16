package com.gs.lshly.biz.support.stock.service.pos.Impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.gs.lshly.biz.support.stock.entity.Stock;
import com.gs.lshly.biz.support.stock.entity.StockLog;
import com.gs.lshly.biz.support.stock.entity.StockPosLog;
import com.gs.lshly.biz.support.stock.repository.IStockLogRepository;
import com.gs.lshly.biz.support.stock.repository.IStockPosLogRepository;
import com.gs.lshly.biz.support.stock.repository.IStockRepository;
import com.gs.lshly.biz.support.stock.service.pos.IPosStockService;

import com.gs.lshly.common.exception.BusinessException;
import com.gs.lshly.common.struct.merchadmin.pc.commodity.vo.PCMerchGoodsInfoVO;
import com.gs.lshly.common.struct.pos.dto.PosStockRequestDTO;
import com.gs.lshly.middleware.mybatisplus.MybatisPlusUtil;
import com.gs.lshly.rpc.api.merchadmin.pc.commodity.IPCMerchAdminGoodsInfoRpc;
import lombok.extern.slf4j.Slf4j;

import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
@Slf4j
public class PosStockServiceImpl implements IPosStockService {


    @DubboReference
    private IPCMerchAdminGoodsInfoRpc ipcMerchAdminGoodsInfoRpc;
    @Autowired
    private IStockPosLogRepository iStockPosLogRepository;
    @Autowired
    private IStockLogRepository iStockLogRepository;
    @Autowired
    private IStockRepository iStockRepository;
    @Override
    public void addAndFlush(PosStockRequestDTO.DTO posStockRequestDTO) {
        QueryWrapper<StockPosLog> query1 = MybatisPlusUtil.query();
        query1.and(i->i.eq("stock_change_serial_no",posStockRequestDTO.getStockChangeSerialNo()));
        List<StockPosLog> list = iStockPosLogRepository.list(query1);
        if (list.size()>0){
            return;
        }
        checkAndFlush(posStockRequestDTO);
        //存临时表
        StockPosLog stockPosLog = new StockPosLog();
        stockPosLog.setTimestamp(posStockRequestDTO.getTimestamp().toString());
        stockPosLog.setPosSku69Code(posStockRequestDTO.getPosSKU69Code());
        BeanUtils.copyProperties(posStockRequestDTO,stockPosLog);
        iStockPosLogRepository.save(stockPosLog);
        //处理逻辑
        StockLog stockLog = new StockLog();
        PCMerchGoodsInfoVO.SkuIdByGoodsNoVO skuIdByGoodsNoVO = ipcMerchAdminGoodsInfoRpc.innerSkuIdByGoodsNo(posStockRequestDTO.getPosSKU69Code());

        if (ObjectUtils.isNotEmpty(skuIdByGoodsNoVO)){
            QueryWrapper<Stock> query = MybatisPlusUtil.query();
            query.and(i->i.eq("sku_id",skuIdByGoodsNoVO.getSkuId()));
            query.last("limit 0,1");
            Stock one = iStockRepository.getOne(query);
            if (posStockRequestDTO.getFlushTotalStock() == 0) {
                //不刷新,修改主表，存变动表
                if (one.getQuantity()+posStockRequestDTO.getStockChangeAmount()>0) {
                    one.setQuantity(one.getQuantity()+posStockRequestDTO.getStockChangeAmount());
                    BeanUtils.copyProperties(one,stockLog);
                    stockLog.setId(posStockRequestDTO.getStockChangeSerialNo()).
                            setStockId(one.getId()).
                            setDataFromType(10).
                            setDataActionType(20).
                            setQuantityChange(posStockRequestDTO.getStockChangeAmount()).
                            setQuantityAll(one.getQuantity()).
                            setLocation(posStockRequestDTO.getStockChangeAmount()>0?10:20).
                            setRemark(posStockRequestDTO.getChangeCertificate());
                    iStockRepository.updateById(one);
                    iStockLogRepository.save(stockLog);
                }else {
                    throw new BusinessException("库存不足");
                }
            }else if (posStockRequestDTO.getFlushTotalStock() == 1){
                //刷新
                one.setQuantity(posStockRequestDTO.getTotalStock());
                BeanUtils.copyProperties(one,stockLog);
                stockLog.setId(posStockRequestDTO.getStockChangeSerialNo()).
                        setStockId(one.getId()).
                        setDataFromType(10).
                        setDataActionType(10).
                        setQuantityChange(posStockRequestDTO.getStockChangeAmount()).
                        setQuantityAll(one.getQuantity()).
                        setLocation(30).
                        setRemark(posStockRequestDTO.getChangeCertificate());
                iStockRepository.updateById(one);
                iStockLogRepository.save(stockLog);
            }
        }

    }

    private void checkAndFlush(PosStockRequestDTO.DTO  dto) {
        if (ObjectUtils.isEmpty(dto)){
            throw new BusinessException("参数为空");
        }
        if (ObjectUtils.isEmpty(dto.getTimestamp())){
            throw new BusinessException("时间戳错误");
        }
        if (ObjectUtils.isEmpty(dto.getNonce()) && dto.getNonce().length()!=6){
            throw new BusinessException("随机数错误");
        }
        if (ObjectUtils.isEmpty(dto.getPosCode())){
            throw new BusinessException("POS店编号为空");
        }
        if (ObjectUtils.isEmpty(dto.getPosSKU69Code())){
            throw new BusinessException("店铺商品sku 69为空");
        }
        if (ObjectUtils.isEmpty(dto.getStockChangeSerialNo())){
            throw new BusinessException("库存变动流水号为空");
        }
        if (ObjectUtils.isEmpty(dto.getStockChangeAmount())){
            throw new BusinessException("库存变化量为空");
        }
        if (ObjectUtils.isNotEmpty(dto.getFlushTotalStock())){
            if (dto.getFlushTotalStock()==1){
                if (ObjectUtils.isEmpty(dto.getTotalStock())){
                    throw new BusinessException("当前刷新平台库存，库存总量不能为空");
                }
            }
        }

    }
}
