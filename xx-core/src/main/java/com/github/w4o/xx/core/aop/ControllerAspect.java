package com.github.w4o.xx.core.aop;

import com.github.w4o.xx.core.base.CommonResult;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * 控制器切面
 * 功能1：计算每个api执行时间（接口耗时）
 *
 * @author Frank
 */
@Aspect
@Component
public class ControllerAspect {

    @Around("execution(* com.github.w4o.xx.*.controller..*(..))")
    public Object doController(ProceedingJoinPoint joinPoint) throws Throwable {
        // 记录方法开始时间
        long startTime = System.currentTimeMillis();
        Object returnValue = joinPoint.proceed();
        if (returnValue instanceof CommonResult) {
            // 时间差，计算耗时
            long spendTime = System.currentTimeMillis() - startTime;
            ((CommonResult<?>) returnValue).setSpent(spendTime);
        }
        return returnValue;
    }
}
