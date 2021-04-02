package com.gs.lshly.biz.support.stock.service.bbb.h5;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.common.struct.bbb.h5.stock.dto.BbbH5StockAddressDTO;
import com.gs.lshly.common.struct.bbb.h5.stock.qto.BbbH5StockAddressQTO;
import com.gs.lshly.common.struct.bbb.h5.stock.vo.BbbH5StockAddressVO;

/**
 * @date 2020-12-31
 * @author Starry
 */
public interface IBbbH5StockAddressService {


    /**
     * 保存地址
     * @param eto
     * @return
     */
    BbbH5StockAddressVO.DetailVO saveStockAddress(BbbH5StockAddressDTO.DetailETO eto);

    /**
     * 删除地址
     * @param dto
     */
    void deleteAddress(BbbH5StockAddressDTO.IdDTO dto);

    /**
     * 获取地址详情
     * @param dto
     * @return
     */
    BbbH5StockAddressVO.DetailVO detailStockAddress(BbbH5StockAddressDTO.IdAndTypeDTO dto);

    /**
     * 查询个人地址列表
     * @param qto
     * @return
     */
    PageData<BbbH5StockAddressVO.ListVO> pageAddressListVO(BbbH5StockAddressQTO.QTO qto);


    //-----------内部服务-------------

    /**
     * 获取默认地址
     * @param dto
     * @param addressType
     * @return
     */
    BbbH5StockAddressVO.DetailVO innerGetDefault(BaseDTO dto, Integer addressType);


    BbbH5StockAddressVO.DetailVO innerdetailStockAddress(BbbH5StockAddressDTO.IdAndTypeDTO dto);
}