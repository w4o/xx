package com.github.w4o.xx.manage.param.cms.tag;

import com.github.w4o.xx.core.base.BasePageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author Frank
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Schema(name = "文章标签分页查询参数")
public class TagPageParam extends BasePageParam {
    private String search;
}
