package com.github.w4o.xx.core.filter;

import com.baomidou.mybatisplus.core.incrementer.IdentifierGenerator;
import com.github.w4o.xx.core.util.id.IdGenerator;
import org.springframework.stereotype.Component;

/**
 * 自定义ID生成器
 *
 * @author Frank
 */
@Component
public class CustomIdGenerator implements IdentifierGenerator {
    @Override
    public Long nextId(Object entity) {
        return IdGenerator.nextCommonId();
    }
}
