package com.cy.tool.idempotent.support;


import com.cy.tool.idempotent.support.MethodIdempotentEvaluationContext;
import org.junit.platform.commons.util.StringUtils;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.spel.standard.SpelExpressionParser;

import java.lang.reflect.Method;

/**
 * @author ext.chenyi18
 * @ClassName ExpressionResolver
 * @Description: SpEL表达式解析
 * @date 2022/7/16 21:36
 */
public class ExpressionResolver {

    private static final SpelExpressionParser parser = new SpelExpressionParser();

    public static String resolveId(Method method, Object[] args, String id) {
        if (StringUtils.isBlank(id)) {
            throw new IllegalArgumentException("id must not be null!");
        }
        EvaluationContext context = new MethodIdempotentEvaluationContext(method, args);
        Expression expression = parser.parseExpression(id);
        return expression.getValue(context, String.class);
    }
}
