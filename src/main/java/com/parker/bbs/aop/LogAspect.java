package com.parker.bbs.aop;

import com.alibaba.fastjson.JSON;
import com.parker.bbs.annotation.LogAnnotation;
import com.parker.bbs.service.LogService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

@Aspect
@Component
public class LogAspect {

    @Autowired
    private LogService logService;

    @AfterReturning("execution(* com.parker.bbs.service.impl.*.*NeedLog(..))")
    public void afterReturning(JoinPoint joinPoint) throws Exception {
        Map annotationAttributes = getLogAnnotationAttributes(joinPoint);
        String operationType = (String)annotationAttributes.get("operationType");
        String operationTarget = (String)annotationAttributes.get("operationTarget");
        String operationInfo = (String)annotationAttributes.get("operationInfo");
        String operationArgs = getServiceMethodArgsToJSON(joinPoint);
        logService.insertLog(operationType, operationTarget, operationInfo, operationArgs);
    }


    public Map<String,String> getLogAnnotationAttributes(JoinPoint joinPoint) throws Exception {
        //返回类名
        String targetName = joinPoint.getTarget().getClass().getName();
        //返回调用的方法名
        String methodName = joinPoint.getSignature().getName();
        //获得传入的参数
        Object[] arguments = joinPoint.getArgs();
        //获得传入参数对应的类，因为存在同名的重载函数，需要靠参数的长度和类型来区分
        Class[] argumentsClass = new Class[arguments.length];
        for (int i=0; i<arguments.length;i++){
            argumentsClass[i] = arguments[i].getClass();
        }
        Class targetClass = Class.forName(targetName);
        //获得对应的方法，目的是为了获得注解里的参数
        Method method = targetClass.getMethod(methodName,argumentsClass);
        String operationType = method.getAnnotation(LogAnnotation.class).operationType().getValue();
        String operationTarget = method.getAnnotation(LogAnnotation.class).operationTarget().getValue();
        String operationInfo = method.getAnnotation(LogAnnotation.class).operationInfo();

        Map<String,String> map = new HashMap(4);
        map.put("operationType", operationType);
        map.put("operationTarget", operationTarget);
        map.put("operationInfo", operationInfo);
        return map;
    }

    public String getServiceMethodArgsToJSON(JoinPoint joinPoint){
        Object[] objects = joinPoint.getArgs();
        String operationArgs = JSON.toJSONString(objects);
        return operationArgs;
    }
}
