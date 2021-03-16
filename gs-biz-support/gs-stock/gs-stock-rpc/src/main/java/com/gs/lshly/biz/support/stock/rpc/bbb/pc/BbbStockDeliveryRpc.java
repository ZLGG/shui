package com.gs.lshly.biz.support.stock.rpc.bbb.pc;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.gs.lshly.biz.support.stock.service.bbb.pc.IBbbStockDeliveryStyleService;
import com.gs.lshly.common.enums.StockDeliveryTypeEnum;
import com.gs.lshly.common.exception.BusinessException;
import com.gs.lshly.common.struct.bbb.pc.stock.vo.BbbStockDeliveryVO;
import com.gs.lshly.common.struct.bbb.pc.stock.dto.BbbStockDeliveryDTO;
import com.gs.lshly.rpc.api.bbb.pc.stock.IBbbStockDeliveryRpc;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

/**
* 店铺物流远程服务
* @author lxus
* @since 2020-11-02
*/
@DubboService
public class BbbStockDeliveryRpc implements IBbbStockDeliveryRpc {

    @Autowired
    private IBbbStockDeliveryStyleService iBbbStockDeliveryStyleService;

    @Override
    public BbbStockDeliveryVO.DeliveryAmountVO calculate(BbbStockDeliveryDTO.DeliveryAmountDTO dto) {
        if (!StockDeliveryTypeEnum.门店自提.getCode().equals(dto.getDeliveryType())
        && !StockDeliveryTypeEnum.门店配送.getCode().equals(dto.getDeliveryType())
        && !StockDeliveryTypeEnum.快递配送.getCode().equals(dto.getDeliveryType())) {
            throw new BusinessException("配送类型错误[10=快递 20=自提 30=门店配送]");
        }
        if (ObjectUtils.isEmpty(dto.getSkus())) {
            throw new BusinessException("商品列表为空");
        }
        return iBbbStockDeliveryStyleService.calculate(dto);
    }
}