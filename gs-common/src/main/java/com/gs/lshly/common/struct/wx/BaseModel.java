package com.gs.lshly.common.struct.wx;


import cn.hutool.json.JSONUtil;

/**
 * 抽象实体类
 *
 * @author peiyu
 */
public abstract class BaseModel implements Model {
    @Override
    public String toJsonString() {
        return JSONUtil.toJsonStr(this);
    }
}
