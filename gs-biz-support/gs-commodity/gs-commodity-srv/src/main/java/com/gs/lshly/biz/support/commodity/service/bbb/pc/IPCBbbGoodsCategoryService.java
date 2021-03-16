package com.gs.lshly.biz.support.commodity.service.bbb.pc;

import com.gs.lshly.common.struct.bbb.pc.commodity.qto.PCBbbGoodsCategoryQTO;
import com.gs.lshly.common.struct.bbb.pc.commodity.vo.PCBbbGoodsCategoryVO;

import java.util.List;


public interface IPCBbbGoodsCategoryService {

    /**
     * 查询2B商城商品分类列表以及菜单列表
     * @return
     */
    PCBbbGoodsCategoryVO.CategoryMenuVO getCategoryMenuVO(PCBbbGoodsCategoryQTO.QTO qto);


    /**
     * 查询2B商城三级分类列表以及与三级类目或者二级类目关联的品牌
     * @param qto
     * @return
     */
    PCBbbGoodsCategoryVO.CategoryNavigationVO getCategoryNavigationVO(PCBbbGoodsCategoryQTO.CategoryNavigationQTO qto);



    //-------------------内部服务-----------------

    /**
     * 提供根据三级类目查询一级类目id列表
     * @param level3List
     * @return
     */
    List<String> innerServiceLevel1IdList(List<String> level3List);
}