package com.gs.lshly.biz.support.commodity.rpc.bbb.pc;

import java.util.List;

import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import com.gs.lshly.biz.support.commodity.service.bbb.pc.IPCBbbGoodsCategoryService;
import com.gs.lshly.common.struct.bbb.pc.commodity.qto.PCBbbGoodsCategoryQTO;
import com.gs.lshly.common.struct.bbb.pc.commodity.vo.PCBbbGoodsCategoryVO;
import com.gs.lshly.rpc.api.bbb.pc.commodity.IPCBbbGoodsCategoryRpc;


/**
*
* @author Starry
* @since 2020-10-23
*/
@DubboService
public class PCBbbGoodsCategoryRpc implements IPCBbbGoodsCategoryRpc {
    @Autowired
    private IPCBbbGoodsCategoryService categoryService;


    @Override
    public PCBbbGoodsCategoryVO.CategoryMenuVO getCategoryMenuVO(PCBbbGoodsCategoryQTO.QTO qto) {
        return categoryService.getCategoryMenuVO(qto);
    }

    @Override
    public PCBbbGoodsCategoryVO.CategoryNavigationVO getCategoryNavigationVO(PCBbbGoodsCategoryQTO.CategoryNavigationQTO qto) {
        return categoryService.getCategoryNavigationVO(qto);
    }

    @Override
    public List<String> innerServiceLevel1IdList(List<String> level3List) {
        return categoryService.innerServiceLevel1IdList(level3List);
    }

}