package com.parker.bbs.annotation;

import com.parker.bbs.util.OperationTarget;
import com.parker.bbs.util.OperationType;

import java.lang.annotation.*;

@Target({ElementType.PARAMETER, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface LogAnnotation {
    OperationType operationType();
    OperationTarget operationTarget();
    String operationInfo() default "";
}
