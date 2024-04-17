package com.github.w4o.xx.applet.service.impl;

import com.github.w4o.xx.applet.common.config.AppConfig;
import com.github.w4o.xx.applet.service.HelloService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Frank
 */
@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class HelloServiceImpl implements HelloService {

    private final AppConfig appConfig;

    @Override
    public String hello() {
        return "hello applet api! " + appConfig.getVersion();
    }
}
