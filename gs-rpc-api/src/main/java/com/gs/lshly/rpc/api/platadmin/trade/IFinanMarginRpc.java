package com.gs.lshly.rpc.api.platadmin.trade;


import com.gs.lshly.common.struct.platadmin.trade.dto.FinanMarginDTO;
import com.gs.lshly.common.struct.platadmin.trade.vo.FinanMarginVO;

import java.util.List;

/**
 *
 * @author lxus
 * @since 2020/9/14
 */
public interface IFinanMarginRpc {

    boolean add(FinanMarginDTO.ETO dto);

    boolean delete(FinanMarginDTO.IdDTO idDto);

    boolean update(FinanMarginDTO.ETO dto);

    List<FinanMarginVO> findAll(FinanMarginDTO.ETO dto);

}
