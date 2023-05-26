package com.github.w4o.xx.manage.service.impl;

import com.github.w4o.xx.manage.service.HelloService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Hello 服务实现类
 *
 * @author Frank
 */
@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class HelloServiceImpl implements HelloService {

    @Override
    public String hello() {
        log.debug("hello manage api! 成功访问");
        return "hello manage api!";
    }
}
