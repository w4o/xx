package com.github.w4o.xx.manage.controller;

import com.github.w4o.xx.core.base.CommonResult;
import com.github.w4o.xx.manage.service.UploadService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * 上传控制器
 *
 * @author Frank
 */
@Tag(name = "03. 上传")
@RestController
@RequestMapping("/upload")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Validated
@Slf4j
public class UploadController {

    private final UploadService uploadService;

    @Operation(summary = "上传图片")
    @PostMapping(value = "/image")
    public CommonResult<?> image(@RequestParam(value = "file") MultipartFile file) {
        return CommonResult.success(uploadService.uploadImage(file));
    }
}
