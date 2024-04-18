package com.github.w4o.xx.manage.controller.mall;

import com.github.w4o.xx.core.controller.BaseController;
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
@RequestMapping("/mall/order")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Tag(name = "22. 订单管理")
public class MallOrderController extends BaseController {
}
