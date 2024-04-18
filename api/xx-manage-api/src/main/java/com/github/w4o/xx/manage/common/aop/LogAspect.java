package com.github.w4o.xx.manage.common.aop;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.github.w4o.xx.core.annotation.SysLog;
import com.github.w4o.xx.core.base.CommonResult;
import com.github.w4o.xx.core.entity.SysLogEntity;
import com.github.w4o.xx.core.util.RequestUtils;
import com.github.w4o.xx.manage.common.UserInfo;
import com.github.w4o.xx.service.mapper.SysLogMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * 系统日志切面
 *
 * @author Frank
 */
@Slf4j
@Aspect
@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class LogAspect {

    private final SysLogMapper sysLogMapper;

    @Around("@annotation(com.github.w4o.xx.core.annotation.SysLog)")
    public Object doController(ProceedingJoinPoint joinPoint) throws Throwable {
        // 记录方法开始时间
        long startTime = System.currentTimeMillis();
        boolean succeed = true;
        Object returnValue = null;
        Exception currentException = null;
        try {
            returnValue = joinPoint.proceed();
            if (returnValue instanceof CommonResult<?> result && (result.getCode() != CommonResult.SUCCESS_CODE)) {
                succeed = false;
            }
        } catch (Exception e) {
            succeed = false;
            currentException = e;
        }
        long spendTime = System.currentTimeMillis() - startTime;

        try {
            MethodSignature signature = (MethodSignature) joinPoint.getSignature();
            SysLog annotation = signature.getMethod().getAnnotation(SysLog.class);
            SysLogEntity sysLog = new SysLogEntity();
            sysLog.setIp(RequestUtils.getIpAddress());
            sysLog.setUa(RequestUtils.getUserAgent());
            sysLog.setOperation(annotation.value());
            sysLog.setTime(spendTime);
            sysLog.setSucceed(succeed);
            sysLog.setMethod(RequestUtils.getRequestMethod());
            UserInfo userInfo = (UserInfo) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            sysLog.setUsername(userInfo.getUsername());
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.registerModule(new JavaTimeModule());
            sysLog.setParams(objectMapper.writeValueAsString(joinPoint.getArgs()));
            sysLog.setCreateTime(LocalDateTime.now());
            sysLogMapper.insert(sysLog);
        } catch (Exception e) {
            log.error("系统日志插入异常", e);
        }

        if (currentException != null) {
            throw currentException;
        }

        return returnValue;
    }
}
