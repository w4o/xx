package com.github.w4o.xx.manage.param.sys.dict;

import com.github.w4o.xx.core.base.BasePageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import jakarta.validation.constraints.NotNull;

/**
 * 字典数据分页查询请求参数
 *
 * @author Frank
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Schema(name = "字典数据分页查询参数")
public class DictDataPageParam extends BasePageParam {
    @NotNull
    @Schema(description = "字典类型id", example = "1")
    private Long dictTypeId;
}
