package com.github.w4o.xx.core.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.github.w4o.xx.core.base.BaseDataEntity;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * @author Frank
 */
@Getter
@Setter
@TableName(value = "mall_shopping_cart", autoResultMap = true)
public class MallShoppingCart extends BaseDataEntity {

    /**
     * 小程序用户id
     */
    private Long appletUserId;
    /**
     * 商品id
     */
    private Long goodsId;
    /**
     * 商品数量
     */
    private Integer quantity;
    /**
     * 是否选中
     */
    private Boolean checked;
    /**
     * 创建时间
     */
    private LocalDateTime createTime;
    /**
     * 最后更新时间
     */
    private LocalDateTime lastUpdateTime;
}
