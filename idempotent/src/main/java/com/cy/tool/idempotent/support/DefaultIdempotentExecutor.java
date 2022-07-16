package com.cy.tool.idempotent.support;

import com.cy.tool.idempotent.annotation.Idempotent;
import com.cy.tool.idempotent.domain.IdempotentInfo;
import com.cy.tool.idempotent.util.Supplier;

import java.lang.reflect.Method;
import java.util.Objects;

/**
 * @author ext.chenyi18
 * @ClassName DefaultIdempotentExecutor
 * @Description: 默认执行器
 * @date 2022/7/16 21:36
 */
public class DefaultIdempotentExecutor implements IdempotentExecutor {

    private IdempotentInfo idempotentInfo;

    private Supplier supplier;

    public DefaultIdempotentExecutor(Method method, Object[] args, Idempotent idempotent, Supplier supplier) throws IllegalAccessException {
        String id = ExpressionResolver.resolveId(method, args, idempotent.id());
        this.idempotentInfo = IdempotentInfo.IdempotentInfoBuilder
                .anIdempotentInfo()
                .id(id)
                .method(method)
                .idempotent(idempotent)
                .build();
        this.supplier = Objects.requireNonNull(supplier, "supplier must not be null");
    }

    @Override
    public Object execute() throws Throwable {
        return supplier.get();
    }

    @Override
    public IdempotentInfo getIdempotentInfo() {
        return idempotentInfo;
    }
}
