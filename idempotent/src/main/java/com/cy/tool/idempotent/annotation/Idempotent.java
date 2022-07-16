package com.cy.tool.idempotent.annotation;

import com.cy.tool.idempotent.domain.IdempotentInfo;

import java.lang.annotation.*;

/**
 * @author ext.chenyi18
 * @ClassName Idempotent
 * @Description: 幂等性注解
 * @date 2022/7/16 21:36
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Idempotent {

    /**
     * 前缀，默认值为方法名
     */
    String prefix() default "";

    /**
     * springEl表达式
     * 获取到的属性值将作为KEY
     */
    String id();

    /**
     * TTl,单位秒
     */
    int expired() default IdempotentInfo.DEFAULT_DURATION;
}
