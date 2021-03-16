package com.lakala.boss.api.entity.caps.request;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * @Project: lkl-newboss-parent
 * @Package: com.lakala.boss.api.entity.CapsRequest
 * @Description: 对账文件下载请求实体类
 * @author: LXF
 * @date Date: 2019年12月29日 9:42
 * @version: V1.0.0
 */
@Data
@ToString
public class Caps006Request implements Serializable {

    private static final long serialVersionUID = 3202044769244149843L;

    /**
     * 对账文件下载申请日期
     */
    private String transactionDate;
}
