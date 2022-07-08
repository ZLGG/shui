package com.citydo.appraisal.result;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * @author vip
 * @date 2020-4-2 9:10
 */
@Data
public class R<T> {

    /**
     * 状态
     */
    private Boolean success;

    /**
     * 状态码
     */
    private Integer code;

    /**
     * 提示信息
     */
    private String message;


    private Object data;

//    /**
//     * 结果
//     */
//    private Map<String,Object> data = new HashMap<>(16);

    /**
     * 空构造
     */
    private R(){}

    /**
     * 操作成功
     * @return
     */
    public static R ok(){
        R r = new R();
        r.success = true;
        r.code = ResultCode.SUCCESS;
        r.message = "操作成功";
        return r;
    }

    /**
     * 操作失败
     * @return
     */
    public static R error(){
        R r = new R();
        r.success = false;
        r.code = ResultCode.ERROR;
        r.message = "操作错误";
        return r;
    }

    /**
     * 设置状态方法
     * @param success
     * @return
     */
    public R success(Boolean success){
        this.success = success;
        return this;
    }

    /**
     * 设置提示信息方法
     * @param message
     * @return
     */
    public R message(String message){
        this.message = message;
        return this;
    }

    /**
     * 设置状态码方法
     * @param code
     * @return
     */
    public R code(Integer code){
        this.code = code;
        return this;
    }

    /**
     * 设置数据方法
     * @param key
     * @param value
     * @return
     */
    public R data(String key,Object value){
        this.data = value;
        return this;
    }

    /**
     * 设置数据方法
     * @param data
     * @return
     */
    public R data(Map<String,Object> data){
        this.data = data;
        return this;
    }

}
