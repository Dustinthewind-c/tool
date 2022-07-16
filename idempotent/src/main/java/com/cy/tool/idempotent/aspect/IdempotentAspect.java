package com.cy.tool.idempotent.aspect;

import com.cy.tool.idempotent.support.DefaultIdempotentExecutor;
import com.cy.tool.idempotent.IdempotentManager;
import com.cy.tool.idempotent.IdempotentTemplate;
import com.cy.tool.idempotent.annotation.Idempotent;
import com.cy.tool.idempotent.constants.ServiceCodeEnum;
import com.cy.tool.idempotent.domain.Result;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;

import java.lang.reflect.Method;

/**
 * @author ext.chenyi18
 * @ClassName IdempotentAspect
 * @Description: 幂等性切面
 * @date 2022/7/6 22:19
 */
@Aspect
@Order(1)
public class IdempotentAspect {

    private static final Logger logger = LoggerFactory.getLogger(IdempotentAspect.class);

    private IdempotentTemplate idempotentTemplate;

    public IdempotentAspect(IdempotentManager idempotentManager) {
        this.idempotentTemplate = new IdempotentTemplate(idempotentManager);
    }

    @Pointcut("@annotation(com.cy.tool.idempotent.annotation.Idempotent)")
    public void point() {
    }

    @Around(value = "point()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        try {
            MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
            Method method = methodSignature.getMethod();
            Idempotent idempotent = method.getAnnotation(Idempotent.class);
            if (idempotent != null) {
                return idempotentTemplate.execute(new DefaultIdempotentExecutor(method, joinPoint.getArgs(), idempotent,()-> joinPoint.proceed()));
            }
            return joinPoint.proceed();
        } catch (Exception e) {
            logger.error("IdempotentAspect.around error", e);
            return Result.getFailedResult(ServiceCodeEnum.SYSTEM_ERROR.getMsg(), ServiceCodeEnum.SYSTEM_ERROR.getCode());
        }
    }


}
