package com.github.w4o.xx.manage.param.sys.media;

import com.github.w4o.xx.core.base.BasePageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author Frank
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Schema(name = "媒体分页查询参数")
public class MediaPageParam extends BasePageParam {
    private Long categoryId;
    private String title;
    private Integer type;
}
