package com.github.w4o.xx.manage.dto.sys.log;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author Frank
 */
@Data
@Schema(name = "日志分页数据模型")
public class LogPageDTO {

    @Schema(description = "用户名")
    private String username;

    @Schema(description = "用户操作")
    private String operation;

    @Schema(description = "请求方法")
    private String method;

    @Schema(description = "请求参数")
    private String params;

    @Schema(description = "执行时长(毫秒)")
    private Long time;

    @Schema(description = "成功")
    private Boolean succeed;

    @Schema(description = "IP地址")
    private String ip;

    @Schema(description = "浏览器UA信息")
    private String ua;

    @Schema(description = "创建时间")
    private LocalDateTime createTime;
}
