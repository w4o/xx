package com.github.w4o.xx.manage.controller.sys;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Frank
 */
@Slf4j
@Validated
@RestController
@RequestMapping("/sys/media")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Tag(name = "13. 媒体管理")
public class SysMediaController {
}
