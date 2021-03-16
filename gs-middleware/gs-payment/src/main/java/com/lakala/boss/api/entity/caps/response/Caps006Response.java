package com.lakala.boss.api.entity.caps.response;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * @Project: lkl-newboss-parent
 * @Package: com.lakala.boss.api.entity.CapsResponse
 * @Description: 对账文件响应实体类
 * @author: LXF
 * @date Date: 2019年12月29日 9:45
 * @version: V1.0.0
 */
@Data
@ToString
public class Caps006Response implements Serializable {

    private static final long serialVersionUID = 9098710010824816571L;
    /**
     * 文件总笔数
     */
    private String tolNumber;
    /**
     * 文件总金额
     */
    private String tolAmount;
    /**
     * 文件流
     */
    private String fileBuffer;
    /**
     * 文件mac校验值
     */
    private String fileMac;
    /**
     * 预留字段1
     */
    private String backUpField1;
    /**
     * 预留字段2
     */
    private String backUpField2;
    /**
     * 预留字段3
     */
    private String backUpField3;
}
