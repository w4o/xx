package com.github.w4o.xx.manage.dto.sys.dict;

import com.github.w4o.xx.core.base.dto.BaseDataDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author Frank
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Schema(name = "字典类型分页数据模型")
public class DictTypeDTO extends BaseDataDTO {
    @Schema(description = "字典类型ID")
    private Long dictTypeId;
    @Schema(description = "字典类型标签")
    private String label;
    @Schema(description = "字典类型名")
    private String name;
}
