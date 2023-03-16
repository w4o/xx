package com.github.w4o.xx.front.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.github.w4o.xx.front.service.HelloService;

/**
 * @author Frank
 */
@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class HelloServiceImpl implements HelloService {

    @Override
    public String hello() {
        return "hello front api!";
    }
}
