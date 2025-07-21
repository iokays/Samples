package com.iokays.sample.config;

import org.springframework.context.expression.AnnotatedElementKey;
import org.springframework.context.expression.CachedExpressionEvaluator;
import org.springframework.context.expression.MethodBasedEvaluationContext;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Distributed Expression Evaluator
 */
public class DistributedLockExpressionEvaluator extends CachedExpressionEvaluator {

    // 使用线程安全的ConcurrentHashMap
    // 因为多个线程同时访问时，会同时判断key是否存在，如果不存在，则创建，如果存在，则直接返回。
    private final Map<ExpressionKey, Expression> cache = new ConcurrentHashMap<>();

    /**
     * Create MethodBasedEvaluationContext
     * MethodBasedEvaluationContext是StandardEvaluationContext的一个子类。它主要是把方法参数也加到了变量中，使得用户可以直接通过#+参数名来获取值。常常用于解析注解中的SpEL表达式。
     * <p>
     * java编译后通过反射是拿不到真实的方法参数名称的，需要带上-parameters参数编译才行，不过Spring还另外基于ASM的方式解析字节码文件，
     * 获取字节码的本地方法表来获取方法真实参数。DefaultParameterNameDiscoverer实现类同时使用上面所说的两种方式来获取方法参数名。
     *
     * @param method
     * @param args
     * @return
     */
    public EvaluationContext createMethodBasedEvaluationContext(Method method, Object[] args) {
        //当你的应用场景用 #root, 取决于你是否根据 root对象可以用来生成key 是需要rootObject的。
        final var rootObject = new DistributedLockExpressionRootObject(method, args);
        return new MethodBasedEvaluationContext(rootObject, method, args, this.getParameterNameDiscoverer());
    }

    public Object eval(String expression, AnnotatedElementKey elementKey, EvaluationContext evalContext) {
        return getExpression(cache, elementKey, expression).getValue(evalContext);
    }

}
