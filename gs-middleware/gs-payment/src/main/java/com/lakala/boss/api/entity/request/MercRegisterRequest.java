package com.lakala.boss.api.entity.request;

import com.lakala.boss.api.entity.response.MercRegisterResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * @author : L.T.P
 * @version V1.0.0
 * @Project: lkl-zf-ss-onlinepay-sdk
 * @Package com.lakala.boss.api.entity.request
 * @Description: 子商户进件接口请求参数
 * @date Date : 2019年10月14日 13:44
 */
@Data
@ToString
@EqualsAndHashCode(callSuper = true)
public class MercRegisterRequest extends BaseRequest<MercRegisterResponse> {

    /**
     * 业务类型
     */
    private String service = "MercRegister";

    /**
     * 请求时间 YYYYMMDDHHMISS
     */
    private String reqTime;

    /**
     * 子商户名称
     */
    private String merchantName;

    /**
     * 子商户简称
     */
    private String merchantShortName;

    /**
     * 营业执照生效日期
     * YYYYMMDD
     */
    private String mercIdEffDt;

    /**
     * 营业执照到期日期
     * YYYYMMDD
     */
    private String mercIdExpDt;

    /**
     * 注册/经营地址
     */
    private String regAddress;

    /**
     * 经营范围
     */
    private String busiRange;

    /**
     * 是否三证合一
     * 00-普通营业执照
     * 01-多合一营业执照
     */
    private String merchantIdType;

    /**
     * 信用代码\营业执照号
     */
    private String merchantIdNo;

    /**
     * 归属省
     */
    private String province;

    /**
     * 归属市
     */
    private String city;

    /**
     * 归属地区
     */
    private String region;

    /**
     * 行业大类
     */
    private String mccCd;

    /**
     * 行业细类
     */
    private String mccSubCd;

    /**
     * 企业邮箱
     */
    private String merchantEmail;

    /**
     * 成立日期
     */
    private String openDt;

    /**
     * 邮编
     */
    private String zip;

    /**
     * 固话
     */
    private String hotLine;

    /**
     * 商户性质
     */
    private String mercAttr;

    /**
     * 子商户管理员信息开始 （json）
     */
    private String administrator;

    /**
     * 法人信息,个体工商户不包含此类信息 （json）
     */
    private String owner;

    /**
     * 子商户联系人信息开始 （json）
     */
    private String contact;

    /**
     * 子商户结算信息开始 （json）
     */
    private String stlLst;

    /**
     * 分账手续费承担方式
     * 0-主商户承担，
     * 1-分账方承担，
     * 2-双方分担，双方分担时需上送主商户额外承担比例
     */
    private String feeChargeFlg;

    /**
     * 主商户额外承担比例 例： 30.45%写成 30.45
     */
    private String chargePrtg;

    /**
     * 分账规则
     */
    private String shareId;

    /**
     * 证照照片
     */
    private String certPhotoImg;

    /**
     * 补充照片1
     */
    private String supplyPhotoImg1;

    /**
     * 补充照片2
     */
    private String supplyPhotoImg2;

    /**
     * 补充照片3
     */
    private String supplyPhotoImg3;

    /**
     * 补充照片4
     */
    private String supplyPhotoImg4;

    @Override
    public Class<MercRegisterResponse> getResponseClass() {
        return MercRegisterResponse.class;
    }
}
