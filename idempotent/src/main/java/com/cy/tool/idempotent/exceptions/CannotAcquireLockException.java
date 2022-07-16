package com.cy.tool.idempotent.exceptions;

/**
 * @author ext.chenyi18
 * @ClassName CannotAcquireLockException
 * @Description: 幂等性异常
 * @date 2022/7/16 21:50
 */
public class CannotAcquireLockException extends Exception {

    private static final long serialVersionUID = 597670608179321457L;

    public CannotAcquireLockException() {
    }

    public CannotAcquireLockException(Exception e) {
        super(e);
    }

    public CannotAcquireLockException(String msg) {
        super(msg);
    }
}
