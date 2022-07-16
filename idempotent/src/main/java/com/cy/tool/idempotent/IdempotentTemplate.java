package com.cy.tool.idempotent;


import com.cy.tool.idempotent.constants.ServiceCodeEnum;
import com.cy.tool.idempotent.domain.IdempotentInfo;
import com.cy.tool.idempotent.domain.Result;
import com.cy.tool.idempotent.exceptions.CannotAcquireLockException;
import com.cy.tool.idempotent.support.IdempotentExecutor;

/**
 * @author ext.chenyi18
 * @ClassName IdempotentTemplate
 * @Description: 幂等性模板
 * @date 2022/7/16 21:36
 */
public class IdempotentTemplate {

    private IdempotentManager idempotentManager;

    public IdempotentTemplate(IdempotentManager idempotentManager) {
        this.idempotentManager = idempotentManager;
    }

    public Object execute(IdempotentExecutor business) throws Throwable {
        IdempotentInfo idempotentInfo = business.getIdempotentInfo();
        if (idempotentInfo == null) {
            throw new RuntimeException("幂等操作信息为null");
        }
        Object result;
        try {
            this.idempotentManager.acquire(idempotentInfo);
            result = business.execute();
            this.idempotentManager.release(idempotentInfo.getKey());
        } catch (CannotAcquireLockException e) {
            return Result.getFailedResult(ServiceCodeEnum.NOT_FREQUENTLY_OPERATE.getMsg(), ServiceCodeEnum.NOT_FREQUENTLY_OPERATE.getCode());
        } catch (Throwable e) {
            this.idempotentManager.release(idempotentInfo.getKey());
            throw new Throwable(e);
        }
        return result;
    }

}
