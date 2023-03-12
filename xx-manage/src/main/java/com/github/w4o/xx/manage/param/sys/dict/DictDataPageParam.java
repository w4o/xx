package com.github.w4o.xx.manage.param.sys.dict;

import com.github.w4o.xx.core.base.BasePageParam;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;

/**
 * 字典数据分页查询请求参数
 *
 * @author Frank
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class DictDataPageParam extends BasePageParam {
    @NotNull
    private Long dictTypeId;
}
