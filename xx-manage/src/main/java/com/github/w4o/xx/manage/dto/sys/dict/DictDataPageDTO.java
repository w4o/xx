package com.github.w4o.xx.manage.dto.sys.dict;

import com.github.w4o.xx.core.base.dto.BasePageDataDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author Frank
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Schema(name = "字典分页数据模型")
public class DictDataPageDTO extends BasePageDataDTO {
    @Schema(description = "字典ID")
    private Long dictId;
    @Schema(description = "字典标签")
    private String label;
    @Schema(description = "字典排序")
    private Integer sort;
    @Schema(description = "字典描述")
    private String description;
    @Schema(description = "字典值")
    private String value;
}
