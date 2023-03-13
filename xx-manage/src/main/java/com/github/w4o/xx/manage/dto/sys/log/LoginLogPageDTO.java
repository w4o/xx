package com.github.w4o.xx.manage.dto.sys.log;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author Frank
 */
@Data
@Schema(name = "登陆日志分页数据模型")
public class LoginLogPageDTO {
    @Schema(description = "日志ID")
    private Long id;
    @Schema(description = "用户名")
    private String username;
    @Schema(description = "类型")
    private String status;
    @Schema(description = "IP地址")
    private String ip;
    @Schema(description = "浏览器UA信息")
    private String ua;
    @Schema(description = "创建时间")
    private LocalDateTime createTime;
}
