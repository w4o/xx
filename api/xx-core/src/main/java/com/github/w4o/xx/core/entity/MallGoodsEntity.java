package com.github.w4o.xx.core.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.github.w4o.xx.core.base.BaseDataEntity;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Frank
 */
@Getter
@Setter
@TableName(value = "mall_goods", autoResultMap = true)
public class MallGoodsEntity extends BaseDataEntity {

    /**
     * 商品分类id
     */
    private Long goodsCategoryId;
    /**
     * 商品编码
     */
    private String goodsCode;
    /**
     * 商品名称
     */
    private String goodsName;
    /**
     * 商品副标题
     */
    private String subtitle;
    /**
     * 商品主图
     */
    private String mainImage;
    /**
     * 商品详图，多图逗号分隔
     */
    private String subImages;
    /**
     * 商品详情
     */
    private String detail;
    /**
     * 商品原价
     */
    private Long oldPrice;
    /**
     * 商品现价
     */
    private Long price;
    /**
     * 商品库存
     */
    private Integer stock;
    /**
     * 商品状态 1：在售 2：下架
     */
    private Integer status;

    // 常量信息 ============================================================<<<
    /**
     * 商品状态 1：在售
     */
    public final static int STATUS_ON_SALE = 1;
    /**
     * 商品状态 2：下架
     */
    public final static int STATUS_OFF_SALE = 2;

}
