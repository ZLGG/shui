package com.gs.lshly.biz.support.merchant.entity;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.math.BigDecimal;
import java.util.Date;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import java.time.LocalDateTime;

/**
 * <p>
 * 商家店铺
 * </p>
 *
 * @author xxfc
 * @since 2020-10-13
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("gs_shop")
public class Shop extends Model {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    private String id;

    /**
     * 商家ID
     */
    private String merchantId;

    /**
     * POS门店ID
     */
    private String posShopId;
    /**
     * 主帐号ID
     */
    private String mainAccountId;

    /**
     * 店铺Logo
     */
    private String shopLogo;

    /**
     * 店铺名称
     */
    private String shopName;

    /**
     * 店铺描述
     */
    private String shopDesc;

    /**
     * 店铺评分
     */
    private BigDecimal shopScore;

    /**
     * 店铺总销量
     */
    private Integer shopSalesVolume;

    /**
     * 店主姓名
     */
    private String shopManName;

    /**
     * 店主身份证号
     */
    private String shopManIdcardNo;

    /**
     * 店主身份证复印件(正)
     */
    private String shopManIdcardFront;

    /**
     * 店主身份证复印件(反)
     */
    private String shopManIdcardBack;

    /**
     * 店铺类型[10=品牌旗舰店 20=品牌专卖店 30=类目专营店 40=运营商自营 50=多品类通用型]
     */
    private Integer shopType;

    /**
     * 店铺类型名称
     */
    private String shopTypeName;

    /**
     * 店铺商家类型[10=2b 20=2c]
     */
    private Integer terminal;

    /**
     * 店铺经营品牌ID
     */
    private String brandId;

    /**
     * 店铺经营品牌名称
     */
    private String brandName;

    /**
     * 品牌经营授权复印件
     */
    private String brandCert;

    /**
     * 省ID
     */
    private String shopProvince;

    /**
     * 省名称
     */
    private String shopProvinceText;

    /**
     * 市ID
     */
    private String shopCity;

    /**
     * 市名称
     */
    private String shopCityText;

    /**
     * 县区ID
     */
    private String shopCounty;

    /**
     * 县区名称
     */
    private String shopCountyText;

    /**
     * 街道
     */
    private String shopStreet;

    /**
     * 经度
     */
    private BigDecimal shopLongitude;

    /**
     * 纬度
     */
    private BigDecimal shopLatitude;

    /**
     * 店铺地址
     */
    private String shopAddress;

    /**
     * 是否开启地图导航
     */
    private Integer isFixedMap;

    /**
     * 店铺联系人手机号
     */
    private String shopManPhone;

    /**
     * 店铺联系人邮箱
     */
    private String shopManEmail;

    /**
     * 商家来源[10=平台入驻]
     */
    private Integer shopMerchantFrom;

    /**
     * 店铺状态[10=开通 20=关闭]
     */
    private Integer shopState;

    /**
     * 开通时间
     */
    private LocalDateTime openTime;

    /**
     * 关闭时间
     */
    private LocalDateTime closeTime;

    /**
     * 关闭原因
     */
    private String closeWhy;

    /**
     * 邀请码
     */
    private String shareCode;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime cdate;

    /**
     * 更新时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime udate;

    /**
     * 逻辑删除标记
     */
    @TableField(fill = FieldFill.INSERT)
    @TableLogic
    private Boolean flag;

    /**
     * 距离（米）
     */
    @TableField(exist = false)
    private Integer meters;

}
