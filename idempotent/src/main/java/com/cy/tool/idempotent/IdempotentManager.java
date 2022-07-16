package com.cy.tool.idempotent;


import com.cy.tool.idempotent.domain.IdempotentInfo;
import com.cy.tool.idempotent.exceptions.CannotAcquireLockException;

/**
 * @author ext.chenyi18
 * @ClassName IdempotentManager
 * @Description: 幂等性处理接口
 * @date 2022/7/16 21:36
 */
public interface IdempotentManager {


    /**
     * @param idempotentInfo 幂等性实体
     * @throws CannotAcquireLockException 获取失败的异常
     * @Description 获取锁
     */
    void acquire(IdempotentInfo idempotentInfo) throws CannotAcquireLockException;

    /**
     * @param key 锁
     * @Description 释放锁
     */
    void release(String key);
    
}
