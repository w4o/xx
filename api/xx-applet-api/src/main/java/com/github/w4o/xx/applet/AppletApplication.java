package com.github.w4o.xx.applet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author Frank
 */
@SpringBootApplication
@ComponentScan("com.github.w4o.xx")
public class AppletApplication {
    public static void main(String[] args) {
        SpringApplication.run(AppletApplication.class, args);
    }
}
