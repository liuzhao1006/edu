package com.lz.toy.common.aspect;

import com.lz.toy.common.anno.Time;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @author liuzhao
 */
@Log4j
@Component
@Aspect
public class TimeAdvice {
    @Pointcut("@annotation(com.lz.toy.common.anno.Time)")
    public void timeAspect() {
    }

    @SneakyThrows
    @Around("timeAspect()")
    public Object currentMethodTime(ProceedingJoinPoint pjp) {
        Object result = null;
        MethodSignature methodSignature = (MethodSignature) pjp.getSignature();
        Method method = methodSignature.getMethod();
        Time annotation = method.getAnnotation(Time.class);
        if (annotation != null){
            long startTime = System.currentTimeMillis();
            result = pjp.proceed();
            long endTime = System.currentTimeMillis(); // 获取结束时间
            log.info(pjp.getTarget().getClass()+"."+pjp.getSignature().getName()+ "方法用时:" + (endTime - startTime));
        }
        return result;
    }


}