package com.example.mybatisbach.config;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @Description 功能描述:用于controller层操作的AOP类
 * @author Administrator
 */
@Component
@Aspect
public class ControllerAop {

    @Pointcut("execution(public * com.example.mybatisbach.controller..*.*(..))")
    public void privilege() {
    }

    @Around("privilege()")
    public Object around(ProceedingJoinPoint pjd) throws Throwable {
        // 获取方法名
        String className = pjd.getSignature().getClass().getName();
        // 获取执行的方法名称
        String methodName = pjd.getSignature().getName();
        /** 初始化日志打印 */
        Logger log = LoggerFactory.getLogger(className);
        // 定义返回参数
        Object result = null;
        // 记录开始时间
        long start = System.currentTimeMillis();
        // 获取方法参数
        Object[] args = pjd.getArgs();
        String params = "请求报文为:";
        //获取请求参数集合并进行遍历拼接
        for (Object object : args) {
            params += object.toString() + ",";
        }
        params = params.substring(0, params.length() - 1);
        //打印请求参数参数 "log.info(className+"类的"+methodName + "的" + params);"
        log.info(params);
        // 执行目标方法
        result = pjd.proceed();
        // 打印返回报文  + (result instanceof Result ? (Result) result : result)
        log.info("返回报文为:" +result.toString());
        // 获取执行完的时间
        log.info(methodName + "方法的执行时长为:" + (System.currentTimeMillis() - start));
        return result;
    }

}