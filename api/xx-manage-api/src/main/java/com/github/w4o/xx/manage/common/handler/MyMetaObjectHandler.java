package com.github.w4o.xx.manage.common.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.github.w4o.xx.core.base.BaseDataEntity;
import com.github.w4o.xx.manage.common.UserInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * 数据库共通字段自动填充处理
 *
 * @author Frank
 */
@Component
@Slf4j
public class MyMetaObjectHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        if (metaObject.getOriginalObject() instanceof BaseDataEntity) {
            this.setFieldValByName("createTime", LocalDateTime.now(), metaObject);
            this.setFieldValByName("lastUpdateTime", LocalDateTime.now(), metaObject);
            UserInfo userInfo = (UserInfo) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            this.setFieldValByName("createBy", userInfo.getUserId(), metaObject);
            this.setFieldValByName("lastUpdateBy", userInfo.getUserId(), metaObject);
        }
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        if (metaObject.getOriginalObject() instanceof BaseDataEntity) {
            this.setFieldValByName("lastUpdateTime", LocalDateTime.now(), metaObject);
            UserInfo userInfo = (UserInfo) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            this.setFieldValByName("lastUpdateBy", userInfo.getUserId(), metaObject);
        }
    }
}