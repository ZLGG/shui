package com.lakala.boss.api.entity.caps.response;

import com.lakala.boss.api.common.Header;
import com.lakala.boss.api.entity.caps.body.Caps001ResponseBody;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author : Jian Jang
 * @version V1.0.0
 * @Project: caps
 * @Package com.lakala.boss.api.entity.response
 * @Description: 资方头寸查询响应实体类
 * @date Date : 2019年12月25日 10:53
 */
@Data
@ToString
public class Caps001Response extends CapsBaseResponse implements Serializable {

    private static final long serialVersionUID = -6623809742602050486L;
    /***
     * 公共参数
     */
    private Header header;

    /***
     * 公共参数
     */
    private Caps001ResponseBody body;
}
