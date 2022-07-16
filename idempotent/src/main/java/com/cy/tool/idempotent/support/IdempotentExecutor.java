package com.cy.tool.idempotent.support;

import com.cy.tool.idempotent.domain.IdempotentInfo;

/**
 * @author ext.chenyi18
 * @ClassName IdempotentExecutor
 * @Description: 执行器
 * @date 2022/7/16 21:36
 */
public interface IdempotentExecutor {

    Object execute() throws Throwable;

    IdempotentInfo getIdempotentInfo();
}
