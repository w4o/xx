package com.github.w4o.xx.applet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @author Frank
 */
@SpringBootApplication
@EnableAspectJAutoProxy(proxyTargetClass = true, exposeProxy = true)
@ComponentScan("com.github.w4o.xx")
public class AppletApplication {
    public static void main(String[] args) {
        SpringApplication.run(AppletApplication.class, args);
    }
}
