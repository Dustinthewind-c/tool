package com.cy.tool.idempotent.util;

/**
 * @author ext.chenyi18
 * @description: Supplier
 * @date 2022/7/16 13:53
 */
@FunctionalInterface
public interface Supplier<T> {

    /**
     * Gets a result.
     *
     * @return a result
     */
    T get() throws Throwable;
}
