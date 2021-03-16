package com.gs.lshly.biz.support.stock.service.bbb.pc;

import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.common.struct.bbb.pc.stock.qto.BbbStockAddressQTO;
import com.gs.lshly.common.struct.bbb.pc.stock.vo.BbbStockAddressVO;
import com.gs.lshly.common.struct.bbb.pc.stock.dto.BbbStockAddressDTO;

import java.util.List;

public interface IBbbPcStockAddressService {

   String queryStockAddress(String templateId);

    BbbStockAddressVO.DetailVO getDefault(BaseDTO dto, Integer addressType);

    /**
     * 获取地址详情
     * @param dto
     * @return
     */
    BbbStockAddressVO.ListVO detailStockAddress(BbbStockAddressDTO.EditDTO dto);

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
     * 地址列表
     * @param dto
     * @return
     */
    List<BbbStockAddressVO.ListVO> listStockAddressVO(BbbStockAddressDTO.AddressTypeDTO dto);



    BbbStockAddressVO.ListVO innerDetailVO(BbbStockAddressDTO.IdDTO dto);
}