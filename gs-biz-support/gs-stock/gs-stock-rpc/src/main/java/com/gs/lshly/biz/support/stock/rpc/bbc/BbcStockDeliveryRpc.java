package com.gs.lshly.biz.support.stock.rpc.bbc;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.gs.lshly.biz.support.stock.service.bbc.IBbcStockDeliveryStyleService;
import com.gs.lshly.common.enums.StockDeliveryTypeEnum;
import com.gs.lshly.common.exception.BusinessException;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.common.struct.bbc.stock.dto.BbcStockDeliveryDTO;
import com.gs.lshly.common.struct.bbc.stock.vo.BbcStockDeliveryVO;
import com.gs.lshly.rpc.api.bbc.stock.IBbcStockDeliveryRpc;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

/**
* 店铺物流远程服务
* @author lxus
* @since 2020-11-02
*/
@DubboService
public class BbcStockDeliveryRpc implements IBbcStockDeliveryRpc {

    @Autowired
    private IBbcStockDeliveryStyleService  bbcStockDeliveryStyleService;

    @Override
    public BbcStockDeliveryVO.DeliveryAmountVO calculate(BbcStockDeliveryDTO.DeliveryAmountDTO dto) {
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