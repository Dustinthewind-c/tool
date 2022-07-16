package com.cy.tool.idempotent.support;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.DefaultParameterNameDiscoverer;
import org.springframework.core.ParameterNameDiscoverer;
import org.springframework.expression.spel.support.StandardEvaluationContext;

import java.lang.reflect.Method;

/**
 * @author ext.chenyi18
 * @ClassName MethodIdempotentEvaluationContext
 * @Description: spel上下文
 * @date 2022/7/16 21:36
 */
public class MethodIdempotentEvaluationContext extends StandardEvaluationContext {

    private static final Logger LOGGER = LoggerFactory.getLogger(MethodIdempotentEvaluationContext.class);

    private ParameterNameDiscoverer parameterNameDiscoverer;

    private Method method;

    private Object[] args;

    public MethodIdempotentEvaluationContext(Method method, Object[] args) {
        this(new DefaultParameterNameDiscoverer(), method, args);
    }

    public MethodIdempotentEvaluationContext(ParameterNameDiscoverer parameterNameDiscoverer, Method method, Object[] args) {
        this.parameterNameDiscoverer = parameterNameDiscoverer;
        this.method = method;
        this.args = args;
    }

    @Override
    public Object lookupVariable(String name) {
        addArgumentsAsVariables();
        return super.lookupVariable(name);
    }

    private void addArgumentsAsVariables() {
        if (args.length == 0) {
            return;
        }
        String[] paramNames = parameterNameDiscoverer.getParameterNames(method);

        if (paramNames == null) {
            LOGGER.warn("Unable to resolve method parameter names for method: "
                    + method
                    + ". Debug symbol information is required if you are using parameter names in" +
                    " expressions.");
            return;
        }

        for (int i = 0; i < args.length; i++) {
            if (paramNames[i] != null) {
                setVariable(paramNames[i], args[i]);
            }
        }
    }

}
