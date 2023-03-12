package com.github.w4o.xx.core.filter;

import com.baomidou.mybatisplus.core.incrementer.IdentifierGenerator;
import com.github.w4o.xx.core.util.IdGenerator;
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
        IdGenerator idGenerator = new IdGenerator(0, 0);
        return idGenerator.nextId();
    }
}
