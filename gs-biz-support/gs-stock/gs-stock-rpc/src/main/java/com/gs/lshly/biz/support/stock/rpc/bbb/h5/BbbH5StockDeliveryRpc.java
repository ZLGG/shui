package com.gs.lshly.biz.support.stock.rpc.bbb.h5;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.gs.lshly.biz.support.stock.service.bbb.h5.IBbbH5StockDeliveryStyleService;
import com.gs.lshly.common.enums.StockDeliveryTypeEnum;
import com.gs.lshly.common.exception.BusinessException;
import com.gs.lshly.common.struct.bbb.h5.stock.dto.BbbH5StockDeliveryDTO;
import com.gs.lshly.common.struct.bbb.h5.stock.vo.BbbH5StockDeliveryVO;
import com.gs.lshly.rpc.api.bbb.h5.stock.IBbbH5StockDeliveryRpc;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

/**
* 店铺物流远程服务
* @author lxus
* @since 2020-11-02
*/
@DubboService
public class BbbH5StockDeliveryRpc implements IBbbH5StockDeliveryRpc {

    @Autowired
    private IBbbH5StockDeliveryStyleService bbcStockDeliveryStyleService;

    @Override
    public BbbH5StockDeliveryVO.DeliveryAmountVO calculate(BbbH5StockDeliveryDTO.DeliveryAmountDTO dto) {
        if (!StockDeliveryTypeEnum.门店自提.getCode().equals(dto.getDeliveryType())
        && !StockDeliveryTypeEnum.门店配送.getCode().equals(dto.getDeliveryType())
        && !StockDeliveryTypeEnum.快递配送.getCode().equals(dto.getDeliveryType())) {
            throw new BusinessException("配送类型错误[10=快递 20=自提 30=门店配送]");
        }
        if (ObjectUtils.isEmpty(dto.getSkus())) {
            throw new BusinessException("商品列表为空");
        }
        return bbcStockDeliveryStyleService.calculate(dto);
    }
}