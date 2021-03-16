package com.lakala.boss.api.entity.caps.response;

import lombok.Data;
import lombok.ToString;

import java.util.List;

/**
 * @author : L.T.P
 * @version V1.0.0
 * @Project: lkl-newboss-parent
 * @Package com.lakala.boss.api.entity.CapsResponse
 * @Description: 头寸调拨查询返回
 * @date Date : 2019年12月28日 10:45
 */
@Data
@ToString
public class Caps003Response {
    /**
     * 调拨明细
     */
    private List<AllocationDetails> allocationDetails;
}
