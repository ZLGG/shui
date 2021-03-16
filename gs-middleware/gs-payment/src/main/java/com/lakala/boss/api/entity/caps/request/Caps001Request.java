package com.lakala.boss.api.entity.caps.request;

import com.lakala.boss.api.common.Header;
import com.lakala.boss.api.entity.caps.body.Caps001RequestBody;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author : Jian Jang
 * @version V1.0.0
 * @Project: caps
 * @Package com.lakala.boss.api.entity.request
 * @Description: 代付账号余额查询请求实体类
 * @date Date : 2019年12月25日 10:51
 */
@Data
@Builder
@ToString
public class Caps001Request implements Serializable {

    private static final long serialVersionUID = 8410383158879270501L;
    /**
     * 公共参数
     */
    private Header header;

    /**
     * 业务参数
     */
    private Caps001RequestBody body;
}
