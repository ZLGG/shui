package com.gs.lshly.rpc.api.bbb.pc.stock;

import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.common.struct.bbb.pc.stock.dto.BbbStockAddressDTO;
import com.gs.lshly.common.struct.bbb.pc.stock.qto.BbbStockAddressQTO;
import com.gs.lshly.common.struct.bbb.pc.stock.vo.BbbStockAddressVO;

import java.util.List;

/**
*配送地址
* @author tangxu
* @since 2020-12-14
*/
public interface IBbbPcStockAddressRpc {

    String queryStockAddress(String id);
    /**
     * 默认地址 addressType[10=收货 20=发票]
     * @param dto
     * @param addressType
     * @return
     */
    BbbStockAddressVO.DetailVO innerGetDefault(BaseDTO dto, Integer addressType);


    BbbStockAddressVO.DetailVO getDefault(BaseDTO baseDTO, Integer code);

    /**
     * 保存收货地址
     * @param eto
     */
    void saveStockAddress(BbbStockAddressDTO.DetailETO eto);

    /**
     * 查询个人收获列表
     * @param qto
     * @return
     */
    PageData<BbbStockAddressVO.ListVO> pageAddressListVO(BbbStockAddressQTO.QTO qto);

    /**
     * 设置默认收货地址
     * @param dto
     */
    void setDefaultStockAddress(BbbStockAddressDTO.SetDefaultDTO dto);

    /**
     * 删除地址
     * @param dto
     */
    void deleteStockAddress(BbbStockAddressDTO.IdDTO dto);


    /**
     * 获取地址详情
     * @param dto
     * @return
     */
    BbbStockAddressVO.ListVO detailStockAddress(BbbStockAddressDTO.EditDTO dto);



    /**
     * 地址列表
     * @param dto
     * @return
     */
    List<BbbStockAddressVO.ListVO> listStockAddressVO(BbbStockAddressDTO.AddressTypeDTO dto);



    BbbStockAddressVO.ListVO innerDetailVO(BbbStockAddressDTO.IdDTO dto);
}