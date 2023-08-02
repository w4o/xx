package com.github.w4o.xx.manage.dto.sys.media;

import com.github.w4o.xx.core.base.dto.BaseDataDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author Frank
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Schema(name = "媒体数据模型")
public class MediaDTO extends BaseDataDTO {
    private Long mediaId;
    private String title;
    private String url;
    private String description;
}
