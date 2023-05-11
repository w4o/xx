package com.github.w4o.xx.core.util.id;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @author Frank
 */
@Component
public class IdGenerator implements ApplicationContextAware {
    private static SnowflakeIdService snowflakeIdService;


    /**
     * 得到下一个通用 id
     *
     * @return 消息 id
     */
    public static Long nextCommonId() {
        return snowflakeIdService.nextId();
    }


    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        snowflakeIdService = applicationContext.getBean(SnowflakeIdService.class);
    }

}
